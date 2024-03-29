package data.model;

import clarity.definition.RecordState;
import data.model.objects.json.JSONMappable;
import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import utils.managers.DatabaseObjectManager;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class DatabaseObject {
    private static Logger log = AppLogger.logger();
    private UUID uuid = UUID.randomUUID();

    public DatabaseObject() {
        // Here the UUID is generated for us
    }

    public DatabaseObject(UUID uuid) {
        this.uuid = uuid;
        this.load();
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
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException ex) {
            Error.CREATE_NEW_INSTANCE_ERROR.record().create(ex);
        }

        return null;
    }

    @JSONMappable("uuid")
    public UUID getUuid() { // Careful about using this compared to getUuidString()
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setUuidFromString(String uuidStr) {
        uuid = UUID.fromString(uuidStr);
    }

    public String getUuidStringWithoutHyphen() {
        return uuid.toString().replace("-", "");
    }

    public String getUuidString() {
        return uuid.toString();
    }

    public void save(int state) {
        try {
            new DatabaseAction<>().save(this, (DatabaseLink) DatabaseLink.getLinkClass(this.getClass()).getDeclaredConstructor().newInstance(), state);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException ex) {
            Error.DATABASE_SAVE_CLASS_INIT.record().additionalInformation("Class " + this.getClass()).create(ex);
        } catch (DatabaseNotEnabled ex) {
            Error.DATABASE_NOT_ENABLED_EXCEPTION.record().create(ex);
        } catch (NullPointerException ex) {
            Error.DATABASE_SAVE_CLASS_INIT.record()
                    .additionalInformation("Class/DBLink likely not defined")
                    .additionalInformation("Class " + this.getClass()).create(ex);
        }
    }

    public void save() {
        save(RecordState.RAW);
    }

    public void delete(int state) {
        try {
            new DatabaseAction<>().delete(this, (DatabaseLink) DatabaseLink.getLinkClass(this.getClass()).getDeclaredConstructor().newInstance(), state);
        } catch (DatabaseNotEnabled ex) {
            Error.DATABASE_NOT_ENABLED_EXCEPTION.record().create(ex);
        } catch (NullPointerException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException ex) {
            Error.DATABASE_DELETE_CLASS_INIT.record().additionalInformation("Class " + this.getClass()).create(ex);
        }
    }

    public void delete() {
        delete(RecordState.RAW);
    }

    public void load(int state) {
        try {
            new DatabaseAction<>().load(this, (DatabaseLink) DatabaseLink.getLinkClass(this.getClass()).getDeclaredConstructor().newInstance(), state);
        } catch (DatabaseNotEnabled ex) {
            Error.DATABASE_NOT_ENABLED_EXCEPTION.record().create(ex);
        } catch (NullPointerException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException ex) {
            Error.DATABASE_LOAD_CLASS_INIT.record().additionalInformation("Class " + this.getClass()).create(ex);
        }
    }

    public void load() {
        load(RecordState.STATIC);
    }
}
