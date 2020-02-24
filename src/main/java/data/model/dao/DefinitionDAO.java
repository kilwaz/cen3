package data.model.dao;


import clarity.definition.Definition;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DefinitionDAO {
    private static Logger log = AppLogger.logger();

    public DefinitionDAO() {
    }

    public Definition getDefinitionByName(String name) {
        Definition definition = null;

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from definition where name = ?")
                .addParameter(name)
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            definition = Definition.load(DAO.UUIDFromString(uuid), Definition.class);
        }

        return definition;
    }

    public List<Definition> getAllDefinitions() {
        List<Definition> definitions = new ArrayList<>();

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from definition").execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            definitions.add(Definition.load(DAO.UUIDFromString(uuid), Definition.class));
        }

        return definitions;
    }
}
