package data.model.dao;

import java.util.UUID;
import error.Error;
public class DAO {
    public static UUID UUIDFromString(String uuidString) {
        try {
            if (uuidString != null && !uuidString.isEmpty()) {
                return UUID.fromString(uuidString);
            } else {
                Error.UUID_CREATE_FAILED.record().additionalInformation("UUID was empty or null").create();
            }
        } catch (IllegalArgumentException ex) {
            Error.UUID_CREATE_FAILED.record().additionalInformation("UUID String:" + uuidString).create(ex);
        }

        return null;
    }
}
