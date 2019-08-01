package data;

import error.Error;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.json.JSONObject;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class DataBank {
    private static Logger log = AppLogger.logger();

    private static HashMap<String, HashMap<String, Object>> programVariables = new HashMap<>();
    private static HashMap<String, HashMap<String, Object>> programInstances = new HashMap<>();

    public static void saveVariable(String name, Object object, String referenceID) {
        HashMap<String, Object> programVariable = programVariables.get(referenceID);
        if (programVariable == null) {
            programVariable = new HashMap<>();
        }
        programVariable.put(name, object);
        programVariables.put(referenceID, programVariable);
    }

    public static Object loadVariable(String name, String referenceID) {
        HashMap<String, Object> programVariable = programVariables.get(referenceID);
        if (programVariable != null) {
            return programVariable.get(name);
        }
        return null;
    }

    public static void resetInstanceObject(String referenceID) {
        programInstances.remove(referenceID);
    }

    public static void saveInstanceObject(String referenceID, String name, Object instance) {
        HashMap<String, Object> instances = programInstances.get(referenceID);
        if (instances == null) {
            instances = new HashMap<>();
        }

        instances.put(name, instance);
        programInstances.put(referenceID, instances);
    }

    public static Object getInstanceObject(String referenceID, String name) {
        HashMap<String, Object> instances = programInstances.get(referenceID);
        if (instances != null) {
            return instances.get(name);
        }

        return null;
    }

    public static SelectResult runSelectQuery(SelectQuery selectQuery) {
        return runSelectQuery(DBConnectionManager.getInstance().getApplicationConnection(), selectQuery);
    }

    public static SelectResult runSelectQuery(DBConnection dbConnection, SelectQuery selectQuery) {
        SelectResult selectResult = new SelectResult();
        try {
            if (dbConnection.isConnected()) {
                PreparedStatement preparedStatement = dbConnection.getPreparedStatement(selectQuery.getQuery());
                if (preparedStatement != null) {
                    setParameters(preparedStatement, selectQuery);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        SelectResultRow selectResultRow = new SelectResultRow();

                        for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
                            if (resultSet.getMetaData().getColumnType(i) == -4) {  // Column type '-4' is BLOB
                                // '-Blob' is appended to the end of the result to show that it is the blob representation of the object out of the database
                                selectResultRow.addColumn(resultSet.getMetaData().getColumnName(i) + "-Blob", resultSet.getBlob(i));
                                // '-String' is appended to the end of the result to show that it is the string representation of the object out of the database
                                selectResultRow.addColumn(resultSet.getMetaData().getColumnName(i) + "-String", resultSet.getString(i));
                                selectResultRow.addColumn(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                            } else {
                                selectResultRow.addColumn(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                            }
                        }

                        selectResult.addResultRow(selectResultRow);
                    }
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        } catch (SQLException ex) {
            Error.SELECT_QUERY.record().additionalInformation(selectQuery.getQuery()).create(ex);
            if (!dbConnection.isConnected() && dbConnection.isApplicationConnection()) { // If we are not connected anymore, report this to the user status bar
                dbConnection.connect();
                runSelectQuery(dbConnection, selectQuery);
            }
        }

        return selectResult;
    }

    public static UpdateResult runUpdateQuery(UpdateQuery updateQuery) {
        return runUpdateQuery(DBConnectionManager.getInstance().getApplicationConnection(), updateQuery);
    }

    public static UpdateResult runUpdateQuery(DBConnection dbConnection, UpdateQuery updateQuery) {
        UpdateResult updateResult = new UpdateResult();
        try {
            if (dbConnection.isConnected()) {
                PreparedStatement preparedStatement = dbConnection.getPreparedStatement(updateQuery.getQuery());
                if (preparedStatement != null) {
                    setParameters(preparedStatement, updateQuery);
                    updateResult.setResultNumber(preparedStatement.executeUpdate());
                    preparedStatement.close();
                }
            } else {
                if (dbConnection.isApplicationConnection()) {
                    DBConnectionManager.getInstance().getApplicationConnection().getConnection().setAutoCommit(false);
                    // Reconnect to the DB
                    DBConnectionManager.getInstance().createApplicationConnection();
                    runUpdateQuery(DBConnectionManager.getInstance().getApplicationConnection(), updateQuery);
                }
            }
        } catch (SQLException ex) {
            StringBuilder params = new StringBuilder();

            params.append("Parameters:\n");

            Integer paramCount = 1;
            for (Object param : updateQuery.getParameters()) {
                params.append("\n\t\t").append(paramCount).append(": ").append(param);
                paramCount++;
            }

            Error.UPDATE_QUERY.record().additionalInformation(updateQuery.getQuery()).additionalInformation(params.toString()).create(ex);
            if (!dbConnection.isConnected() && dbConnection.isApplicationConnection()) { // If we are not connected anymore, report this to the user status bar
                dbConnection.connect();
            }
        }

        return updateResult;
    }

    private static void setParameters(PreparedStatement preparedStatement, Query query) throws SQLException {
        if (preparedStatement != null) {
            Integer valueCount = 1;
            for (Object value : query.getParameters()) {
                if (value instanceof Boolean) {
                    preparedStatement.setBoolean(valueCount, (Boolean) value);
                } else if (value instanceof Double) {
                    preparedStatement.setDouble(valueCount, (Double) value);
                } else if (value instanceof InputStream) {
                    preparedStatement.setBlob(valueCount, (InputStream) value);
                } else if (value instanceof Integer) {
                    preparedStatement.setInt(valueCount, (Integer) value);
                } else if (value instanceof String) {
                    preparedStatement.setString(valueCount, (String) value);
                } else if (value instanceof UUID) {
                    preparedStatement.setString(valueCount, value.toString());
                } else if (value instanceof DateTime) {
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(((DateTime) value).getMillis());
                    preparedStatement.setTimestamp(valueCount, timestamp);
                } else if (value instanceof JSONObject) {
                    preparedStatement.setString(valueCount, value.toString());
                } else if (value == null) {
                    preparedStatement.setObject(valueCount, null);
                }

                valueCount++;
            }
        }
    }
}