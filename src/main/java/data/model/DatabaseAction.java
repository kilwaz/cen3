package data.model;

import clarity.Record;
import clarity.definition.*;
import data.*;
import data.model.dao.DAO;
import data.model.objects.json.JSONContainer;
import error.Error;
import log.AppLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.json.JSONObject;
import utils.ApplicationParams;
import utils.managers.DatabaseObjectManager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseAction<DBObject extends DatabaseObject, DBLink extends DatabaseLink> {
    private static Logger log = AppLogger.logger();

    private static ConcurrentHashMap<String, DelayedLoad> delayedLoadedObjects = new ConcurrentHashMap<>();

    void save(DBObject dbObject, DBLink dbLink) throws DatabaseNotEnabled {
        if (!ApplicationParams.getDatabaseEnabled()) {
            throw new DatabaseNotEnabled("Save operation attempted with database disabled");
        }

        // Building the update query string
        var updateQueryBuilder = new StringBuilder();
        updateQueryBuilder.append("update ")
                .append(dbLink.getTableName());
        Boolean firstColumn = true;

        List<DatabaseColumnModel> databaseColumnModelList = new ArrayList<>();
        if (dbLink instanceof ConfigurableDatabaseLink) { // Configurable Database Link
            ConfigurableDatabaseLink configurableDbLink = (ConfigurableDatabaseLink) dbLink;
            DefinitionTableModel definitionTableModel = configurableDbLink.getRecordDefinition().getDefinitionTableMode();

            HashMap<String, DatabaseColumnModel> definedModel = definitionTableModel.getDefinedModelHashMap();
            databaseColumnModelList = new ArrayList<>(definedModel.values());

            for (DatabaseColumnModel databaseColumnModel : databaseColumnModelList) {
                if (firstColumn) {
                    updateQueryBuilder.append(" set ")
                            .append(databaseColumnModel.getColumnName())
                            .append(" = ?");
                    firstColumn = false;
                } else {
                    updateQueryBuilder.append(", ")
                            .append(databaseColumnModel.getColumnName())
                            .append(" = ?");
                }
            }
        } else { // Pre-defined Database Link
            for (ModelColumn modelColumn : dbLink.getModelColumns()) {
                if (firstColumn) {
                    updateQueryBuilder.append(" set ")
                            .append(modelColumn.getColumnName())
                            .append(" = ?");
                    firstColumn = false;
                } else {
                    updateQueryBuilder.append(", ")
                            .append(modelColumn.getColumnName())
                            .append(" = ?");
                }
            }
        }

        updateQueryBuilder.append(" where uuid = ?");

        // Building the update query object
        UpdateQuery updateQuery = new UpdateQuery(updateQueryBuilder.toString());

        if (dbLink instanceof ConfigurableDatabaseLink) { // Configurable Database Link
            if (dbObject instanceof Record) {
                Record record = (Record) dbObject;

                for (DatabaseColumnModel databaseColumnModel : databaseColumnModelList) {
                    updateQuery.addParameter(record.get(databaseColumnModel.getColumnName()).get().getValue());
                }
            }
        } else { // Pre-defined Database Link
            for (ModelColumn modelColumn : dbLink.getModelColumns()) {
                try {
                    updateQuery.addParameter(modelColumn.getObjectSaveMethod().invoke(dbObject));
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Error.DATABASE_OBJECT_METHOD_NOT_FOUND
                            .record()
                            .additionalInformation("Column: " + modelColumn.getColumnName())
                            .additionalInformation("Method: " + modelColumn.getObjectSaveMethod().getName())
                            .additionalInformation("Linked Class: " + dbLink.getLinkClass())
                            .create(ex);
                }
            }
        }

        // The uuid to update
        updateQuery.addParameter(dbObject.getUuidString());

        // Execute the update query
        var updateResult = (UpdateResult) updateQuery.execute();

        // If record does not exist insert a new one..
        if (updateResult.getResultNumber() == 0) {
            // Build the insert statement
            StringBuilder insertQueryBuilder = new StringBuilder();
            insertQueryBuilder.append("insert into ")
                    .append(dbLink.getTableName())
                    .append("(");

            firstColumn = true;

            if (dbLink instanceof ConfigurableDatabaseLink) { // Configurable Database Link
                insertQueryBuilder.append("uuid,");

                for (DatabaseColumnModel databaseColumnModel : databaseColumnModelList) {
                    if (firstColumn) {
                        insertQueryBuilder.append(databaseColumnModel.getColumnName());
                        firstColumn = false;
                    } else {
                        insertQueryBuilder.append(",").append(databaseColumnModel.getColumnName());
                    }
                }

                insertQueryBuilder.append(")")
                        .append(" values (?, ?")  // First item here is uuid of newly inserted config db item
                        .append(StringUtils.repeat(", ?", databaseColumnModelList.size() - 1))
                        .append(")");
            } else { // Pre-defined Database Link
                for (ModelColumn modelColumn : dbLink.getModelColumns()) {
                    if (firstColumn) {
                        insertQueryBuilder.append(modelColumn.getColumnName());
                        firstColumn = false;
                    } else {
                        insertQueryBuilder.append(",").append(modelColumn.getColumnName());
                    }
                }

                insertQueryBuilder.append(")")
                        .append(" values (?")
                        .append(StringUtils.repeat(", ?", dbLink.getModelColumns().size() - 1))
                        .append(")");
            }

            // Create query object and fill in parameters
            UpdateQuery insertQuery = new UpdateQuery(insertQueryBuilder.toString());

            if (dbLink instanceof ConfigurableDatabaseLink) { // Configurable Database Link
                if (dbObject instanceof Record) {
                    Record record = (Record) dbObject;

                    // The uuid to update, always is the first param
                    insertQuery.addParameter(dbObject.getUuidString());

                    for (DatabaseColumnModel databaseColumnModel : databaseColumnModelList) {
                        insertQuery.addParameter(record.get(databaseColumnModel.getColumnName()).get().getValue());
                    }
                }
            } else { // Pre-defined Database Link
                for (ModelColumn modelColumn : dbLink.getModelColumns()) {
                    try {
                        insertQuery.addParameter(modelColumn.getObjectSaveMethod().invoke(dbObject));
                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        Error.DATABASE_OBJECT_METHOD_NOT_FOUND.record().create(ex);
                    }
                }
            }

            // Run the insert query
            insertQuery.execute();
        }
    }

    void delete(DBObject dbObject, DBLink dbLink) throws DatabaseNotEnabled {
        if (!ApplicationParams.getDatabaseEnabled()) {
            throw new DatabaseNotEnabled("Save operation attempted with database disabled");
        }

        // Create query object and fill in parameters
        var deleteQuery = new UpdateQuery("delete from " + dbLink.getTableName() + " where uuid = ?");
        for (ModelColumn modelColumn : dbLink.getModelColumns()) {
            try {
                if ("uuid".equals(modelColumn.getColumnName())) {
                    deleteQuery.addParameter(modelColumn.getObjectSaveMethod().invoke(dbObject));
                }
            } catch (IllegalAccessException | InvocationTargetException ex) {
                Error.DATABASE_OBJECT_METHOD_NOT_FOUND.record().create(ex);
            }
        }

        // Run the delete query
        deleteQuery.execute();
    }

    void load(DBObject dbObject, DBLink dbLink) throws DatabaseNotEnabled {
        if (!ApplicationParams.getDatabaseEnabled()) {
            throw new DatabaseNotEnabled("Save operation attempted with database disabled");
        }

        // Building the select query string
        var selectQueryBuilder = new StringBuilder();
        selectQueryBuilder.append("select ");
        var firstColumn = true;
        for (ModelColumn modelColumn : dbLink.getModelColumns()) {
            if (firstColumn) {
                selectQueryBuilder
                        .append(modelColumn.getColumnName())
                        .append(" ");
                firstColumn = false;
            } else {
                selectQueryBuilder.append(", ")
                        .append(modelColumn.getColumnName())
                        .append(" ");
            }
        }
        selectQueryBuilder
                .append("from ")
                .append(dbLink.getTableName())
                .append(" where uuid = ?");

        var selectResult = (SelectResult) new SelectQuery(selectQueryBuilder.toString())
                .addParameter(dbObject.getUuidString())
                .execute();

        for (var resultRow : selectResult.getResults()) {
            for (var modelColumn : dbLink.getModelColumns()) {
                try {
                    if (modelColumn.getObjectLoadMethod() != null) {
                        var loadMethodParameter = modelColumn.getObjectLoadMethod().getParameterTypes();
                        Class loadParameterClass = null;

                        if (loadMethodParameter.length > 0) {
                            loadParameterClass = loadMethodParameter[0];
                        }
                        if (loadParameterClass != null) {
                            if (loadParameterClass.equals(String.class)) {  // STRING
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getString(modelColumn.getColumnName()));
                            } else if (loadParameterClass.equals(Double.class)) { // DOUBLE
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getDouble(modelColumn.getColumnName()));
                            } else if (loadParameterClass.equals(DateTime.class)) { // DATE TIME
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getDateTime(modelColumn.getColumnName()));
                            } else if (loadParameterClass.equals(Integer.class)) { // INTEGER
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getInt(modelColumn.getColumnName()));
                            } else if (loadParameterClass.equals(Boolean.class)) { // BOOLEAN
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getBoolean(modelColumn.getColumnName()));
                            } else if (loadParameterClass.equals(JSONObject.class)) { // JSON OBJECT
                                var jsonStr = resultRow.getString(modelColumn.getColumnName());
                                if (jsonStr != null) {
                                    var jsonContainer = new JSONContainer(jsonStr);
                                    modelColumn.getObjectLoadMethod().invoke(dbObject, jsonContainer.toJSONObject());
                                }
                            } else if (loadParameterClass.equals(UUID.class)) { // UUID
                                String uuid = resultRow.getString(modelColumn.getColumnName());
                                if (uuid != null && !uuid.isEmpty()) {
                                    modelColumn.getObjectLoadMethod().invoke(dbObject, DAO.UUIDFromString(uuid));
                                }
                                // TODO: FROM THESE ELSE IF STATEMENTS ON, THINK OF A GENERIC WAY TO HANDLE THESE SITUATIONS
                            } else if (loadParameterClass.equals(Definition.class)) { // Definition
                                String uuidStr = resultRow.getString(modelColumn.getColumnName());
                                if (uuidStr != null && !uuidStr.isEmpty()) {
                                    Definition definition = loadCachedObject(uuidStr, Definition.class);
                                    modelColumn.getObjectLoadMethod().invoke(dbObject, definition);
                                }
                            } else if (loadParameterClass.equals(RecordDefinition.class)) { // RecordDefinition
                                String uuidStr = resultRow.getString(modelColumn.getColumnName());
                                if (uuidStr != null && !uuidStr.isEmpty()) {
                                    RecordDefinition recordDefinition = loadCachedObject(uuidStr, RecordDefinition.class);
                                    modelColumn.getObjectLoadMethod().invoke(dbObject, recordDefinition);
                                }
                            } else if (loadParameterClass.equals(RecordDefinitionChild.class)) { // RecordDefinitionChild
                                String uuidStr = resultRow.getString(modelColumn.getColumnName());
                                if (uuidStr != null && !uuidStr.isEmpty()) {
                                    RecordDefinitionChild recordDefinitionChild = loadCachedObject(uuidStr, RecordDefinitionChild.class);
                                    modelColumn.getObjectLoadMethod().invoke(dbObject, recordDefinitionChild);
                                }
                                // TODO: ***********************************************************************************
                            } else if (loadParameterClass.equals(Object.class)) { // OBJECT
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getColumnObject(modelColumn.getColumnName()));
                            }
                        }
                    }
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Error.DATABASE_OBJECT_METHOD_NOT_FOUND.record().create(ex);
                } catch (IllegalArgumentException ex) {
                    Error.DATABASE_OBJECT_METHOD_NOT_FOUND.record().additionalInformation("Method name: " + modelColumn.getObjectLoadMethod().getName()).create(ex);
                }
            }
        }
    }

    private <DBObject extends DatabaseObject> DBObject loadCachedObject(String uuidStr, Class<DBObject> clazz) {
        DatabaseObjectManager databaseObjectManager = DatabaseObjectManager.getInstance();
        if (uuidStr != null && !uuidStr.isEmpty()) {
            UUID uuid = DAO.UUIDFromString(uuidStr);
            DatabaseObject databaseObject = null;

            if (databaseObjectManager.objectExists(uuid)) {
                databaseObject = databaseObjectManager.getDatabaseObject(uuid);
            } else {
                if (!delayedLoadedObjects.containsKey(uuid.toString())) {
                    delayedLoadedObjects.put(uuid.toString(), new DelayedLoad(uuid.toString(), clazz, null));
                    databaseObject = DatabaseObject.load(uuid, clazz);
                    databaseObjectManager.addObject(databaseObject);
                    delayedLoadedObjects.remove(uuid.toString());
                }
            }

            return (DBObject) databaseObject;
        }
        return null;
    }
}