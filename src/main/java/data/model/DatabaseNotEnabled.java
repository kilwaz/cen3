package data.model;

class DatabaseNotEnabled extends Exception {
    DatabaseNotEnabled(String errorMessage) {
        super(errorMessage);
    }
}
