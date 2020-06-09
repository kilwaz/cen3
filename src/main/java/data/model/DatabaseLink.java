package data.model;

import data.model.objects.annotations.DatabaseLinkClass;
import error.Error;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DatabaseLink {
    private static HashMap<Class, Class> linkClasses = new HashMap<>();

    static {
        Set<Class<?>> links = new Reflections("data.model.links").getTypesAnnotatedWith(DatabaseLinkClass.class);

        for (Class<?> link : links) {
            linkClasses.put(link.getAnnotation(DatabaseLinkClass.class).linkClass(), link);
        }
    }

    private String tableName = "";
    private List<ModelColumn> modelColumns = new ArrayList<>();
    private List<DeleteColumn> onDeleteColumns = new ArrayList<>();
    private Class linkClass;

    public DatabaseLink() {
        if (!(this instanceof ConfigurableDatabaseLink)) { // Configurable database links are configured from Definitions and handled by ConfigurableDatabaseLink constructor
            DatabaseLinkClass databaseLinkAnnotation = getClass().getAnnotation(DatabaseLinkClass.class);
            this.tableName = databaseLinkAnnotation.tableName();
            this.linkClass = databaseLinkAnnotation.linkClass();
        }
    }

    public static Class getLinkClass(Class clazz) {
        return linkClasses.get(clazz);
    }

    public String getTableName() {
        return tableName;
    }

    public List<ModelColumn> getModelColumns() {
        return modelColumns;
    }

    public List<DeleteColumn> getOnDeleteColumns() {
        return onDeleteColumns;
    }

    public void link(String databaseColumn, Method objectSaveMethod, Method objectLoadMethod) {
        modelColumns.add(new ModelColumn(databaseColumn, objectSaveMethod, objectLoadMethod, ModelColumn.STANDARD_COLUMN));
    }

    public void linkBlob(String databaseColumn, Method objectSaveMethod, Method objectLoadMethod) {
        modelColumns.add(new ModelColumn(databaseColumn, objectSaveMethod, objectLoadMethod, ModelColumn.BLOB_COLUMN));
    }

    public Class getLinkClass() {
        return linkClass;
    }

    public Method method(String methodName, Class<?>... parameterTypes) {
        try {
            return getLinkClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException ex) {
            Error.DATA_LINK_METHOD_NOT_FOUND.record()
                    .additionalInformation("Class: " + getLinkClass().getName())
                    .additionalInformation("MethodName: " + methodName)
                    .create(ex);
        }

        return null;
    }

    public Method externalMethod(String methodName, Class clazz, Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException ex) {
            Error.DATA_LINK_METHOD_NOT_FOUND.record().create(ex);
        }

        return null;
    }

    public void onDelete(String columnName, Class clazz) {
        onDeleteColumns.add(new DeleteColumn(columnName, clazz));
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setLinkClass(Class linkClass) {
        this.linkClass = linkClass;
    }

    public void setModelColumns(List<ModelColumn> modelColumns) {
        this.modelColumns = modelColumns;
    }
}
