package data;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import utils.managers.DatabaseTransactionManager;

import java.util.ArrayList;
import java.util.List;

public class UpdateQuery implements Query {
    private String query;
    private List<Object> parameters = new ArrayList<>();

    private static Logger log = AppLogger.logger();

    public UpdateQuery(String query) {
        this.query = query;
    }

    public Query addParameter(Object value) {
        parameters.add(value);
        return this;
    }

    public String getQuery() {
        return query;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public Object execute() {
        DatabaseTransactionManager.getInstance().addUpdate(this);
//        log.info(query);
        return DataBank.runUpdateQuery(this);
    }
}
