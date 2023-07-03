package data.model.dao;


import clarity.load.excel.DefinedTemplate;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DefinedTemplateDAO {
    private static Logger log = AppLogger.logger();

    public DefinedTemplateDAO() {
    }

    public DefinedTemplate getDefinedTemplateByName(String name) {
        DefinedTemplate definedTemplate = null;

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from defined_template where name = ?")
                .addParameter(name)
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            definedTemplate = DefinedTemplate.load(DAO.UUIDFromString(uuid), DefinedTemplate.class);
        }

        return definedTemplate;
    }

    public List<DefinedTemplate> getAllDefinedTemplates() {
        List<DefinedTemplate> definedTemplates = new ArrayList<>();

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from defined_template")
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            definedTemplates.add(DefinedTemplate.load(DAO.UUIDFromString(uuid), DefinedTemplate.class));
        }

        return definedTemplates;
    }
}
