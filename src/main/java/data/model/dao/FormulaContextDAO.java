package data.model.dao;

import clarity.definition.FormulaContext;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;

import java.util.ArrayList;
import java.util.List;

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

    public List<FormulaContext> getAllFormulaContexts() {
        List<FormulaContext> formulaContexts = new ArrayList<>();

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from formula_context")
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            formulaContexts.add(FormulaContext.load(DAO.UUIDFromString(uuid), FormulaContext.class));
        }

        return formulaContexts;
    }
}