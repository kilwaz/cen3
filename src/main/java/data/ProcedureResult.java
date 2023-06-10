package data;

import java.util.ArrayList;
import java.util.List;

public class ProcedureResult {
    private List<SelectResultRow> results = new ArrayList<>();
    private Exception exception;

    public ProcedureResult() {
    }

    public void addResultRow(SelectResultRow selectResultRow) {
        results.add(selectResultRow);
    }

    public List<SelectResultRow> getResults() {
        return results;
    }

    public Exception getException() {
        return exception;
    }

    public ProcedureResult exception(Exception exception) {
        this.exception = exception;
        return this;
    }

    public Boolean hasException() {
        return exception != null;
    }

    public Boolean hasNoResults() {
        return results.size() == 0;
    }
}
