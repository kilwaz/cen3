package data.model.dao;

import clarity.definition.FormulaContext;
import clarity.definition.FormulaContextGroup;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FormulaContextGroupDAO {
    private static Logger log = AppLogger.logger();

    public FormulaContextGroupDAO() {
    }

    public List<FormulaContextGroup> getFormulaContextGroupByFormulaContext(FormulaContext formulaContext) {
        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from formula_context_group where formula_context_id = ?")
                .addParameter(formulaContext.getUuidString()) // 1
                .execute();
        return processQueryResults(selectResult);
    }

    private List<FormulaContextGroup> processQueryResults(SelectResult selectResult) {
        List<FormulaContextGroup> formulaContextGroups = new ArrayList<>();
        for (SelectResultRow resultRow : selectResult.getResults()) {
            FormulaContextGroup formulaContextGroup = FormulaContextGroup.load(DAO.UUIDFromString(resultRow.getString("uuid")), FormulaContextGroup.class);
            formulaContextGroups.add(formulaContextGroup);
        }
        return formulaContextGroups;
    }
}