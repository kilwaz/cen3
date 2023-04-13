package data;

import java.util.ArrayList;
import java.util.List;

public class SelectResult {
    private List<SelectResultRow> results = new ArrayList<>();
    private Exception exception;

    public SelectResult() {
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

    public SelectResult exception(Exception exception) {
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
