package data.model.dao;

import clarity.definition.FormulaContext;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;

public class FormulaContextDAO {
    public FormulaContextDAO() {
    }

    public FormulaContext getFormulaContextName(String name) {
        FormulaContext formulaContext = null;

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from formula_context where name = ?")
                .addParameter(name)
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            formulaContext = FormulaContext.load(DAO.UUIDFromString(uuid), FormulaContext.class);
        }

        return formulaContext;
    }
}