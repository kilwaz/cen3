package data.model;

import clarity.definition.RecordDefinition;
import error.Error;
import utils.managers.DatabaseObjectManager;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class ConfigurableDatabaseObject extends DatabaseObject {

    private RecordDefinition recordDefinition;

    public ConfigurableDatabaseObject() {

    }

    public ConfigurableDatabaseObject(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
    }

    public ConfigurableDatabaseObject(UUID uuid) {
        //this.uuid = uuid;
        //this.load();
    }

    public void setRecordDefinition(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
    }

    public static <DBObject extends DatabaseObject> DBObject load(UUID uuid, Class<DBObject> clazz) {
        var databaseObjectManager = DatabaseObjectManager.getInstance();
        DatabaseObject loadedObject;

        if (databaseObjectManager.objectExists(uuid)) { // Check if it already exists within cache
            loadedObject = databaseObjectManager.getDatabaseObject(uuid);
        } else {
            loadedObject = create(clazz);
            loadedObject.setUuid(uuid);
            databaseObjectManager.addObject(loadedObject);
            loadedObject.load();
        }

        return (DBObject) loadedObject;
    }

    public static <DBObject extends DatabaseObject> DBObject create(Class<DBObject> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            Error.CREATE_NEW_INSTANCE_ERROR.record().create(ex);
        }

        return null;
    }

    public void save() {
        try {
            new DatabaseAction<>().save(this, new ConfigurableDatabaseLink(this.recordDefinition));
        } catch (DatabaseNotEnabled ex) {
            Error.DATABASE_NOT_ENABLED_EXCEPTION.record().create(ex);
        }
    }

    public void delete() {
        try {
            new DatabaseAction<>().delete(this, (DatabaseLink) DatabaseLink.getLinkClass(this.getClass()).getDeclaredConstructor().newInstance());
        } catch (DatabaseNotEnabled ex) {
            Error.DATABASE_NOT_ENABLED_EXCEPTION.record().create(ex);
        } catch (NullPointerException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            Error.DATABASE_DELETE_CLASS_INIT.record().additionalInformation("Class " + this.getClass()).create(ex);
        }
    }

    public void load() {
        try {
            new DatabaseAction<>().load(this, (DatabaseLink) DatabaseLink.getLinkClass(this.getClass()).getDeclaredConstructor().newInstance());
        } catch (DatabaseNotEnabled ex) {
            Error.DATABASE_NOT_ENABLED_EXCEPTION.record().create(ex);
        } catch (NullPointerException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            Error.DATABASE_LOAD_CLASS_INIT.record().additionalInformation("Class " + this.getClass()).create(ex);
        }
    }

}
