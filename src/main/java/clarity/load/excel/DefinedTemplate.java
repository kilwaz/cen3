package clarity.load.excel;

import clarity.definition.Definition;
import data.model.DatabaseObject;
import data.model.dao.DefinedBridgeDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DefinedTemplate extends DatabaseObject {
    private static Logger log = AppLogger.logger();

    private HashMap<String, DefinedBridge> definedBridgeHashMap = null;
    private String name;
    private Definition primaryKey = null;

    public DefinedTemplate() {
        super();
    }

    public DefinedTemplate(UUID uuid) {
        super(uuid);
    }

    public HashMap<String, DefinedBridge> getDefinedBridgeHashMap() {
        if (this.definedBridgeHashMap == null) {
            this.definedBridgeHashMap = new HashMap<>();
            DefinedBridgeDAO definedBridgeDAO = new DefinedBridgeDAO();
            List<DefinedBridge> definedBridges = definedBridgeDAO.getDefinedBridgesByDefinedTemplate(this);

            for (DefinedBridge definedBridge : definedBridges) {
                this.definedBridgeHashMap.put(definedBridge.getColumnTitle(), definedBridge);
            }
        }

        return this.definedBridgeHashMap;
    }

    public DefinedBridge getDefinedBridgeByName(String name) {
        return getDefinedBridgeHashMap().get(name);
    }

    public List<DefinedBridge> getDefinedBridges() {
        return new ArrayList<>(getDefinedBridgeHashMap().values());
    }

    public void name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Definition getPrimaryKey() {
        return primaryKey;
    }

    public UUID getPrimaryKeyUUID() {
        if (primaryKey != null) {
            return primaryKey.getUuid();
        } else {
            return null;
        }
    }

    public void primaryKey(Definition primaryKey) {
        this.primaryKey = primaryKey;
        this.save();
    }
}