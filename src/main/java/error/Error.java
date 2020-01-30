package error;

public enum Error {
    // ***** DEFINED ERRORS *****
    SDE_FILE_NOT_FOUND(1, "File not found"),
    CANNOT_RESOLVE_HOST_THIS_MACHINE(2, "Unable to get the named host for the current machine"),
    SQL_BLOB(3, "Error getting SQL Blob input stream"),
    OPEN_DATABASE_CONNECTION(4, "Error opening connection to database"),
    SELECT_QUERY(5, "Error with select query"),
    UPDATE_QUERY(6, "Error with update query"),
    STOP_JOBS(7, "Error stopping all jobs"),
    JOB_MANAGER(8, "Error creating job manager"),
    SCHEDULE_JOB(9, "Error scheduling job"),
    STOP_JOB(148, "Error stopping a jobs"),
    DATABASE_TRANSACTION(97, "Database transaction had problems"),
    PREPARE_QUERY(17, "Error in getting query"),
    CLOSE_DATABASE_CONNECTION(15, "Error closing connection"),
    DATABASE_MIGRATE_SQL_FAILED(99, "Flyway database migration failed from SQL"),
    DATABASE_REBUILD_FAILED(100, "Database rebuild failed"),
    DATABASE_MIGRATE_FAILED(101, "Flyway database migration failed - Database connection might not exist"),
    LOG_OUTPUT(42, "Error decoding log path"),
    RESOURCE_PATH(61, "Error getting resource path"),
    UUID_CREATE_FAILED(91, "UUID could not be converted from string"),
    CREATE_NEW_INSTANCE_ERROR(94, "Unable to create a new instance via the blank constructor"),
    DATABASE_DELETE_CLASS_INIT(90, "Unable to delete object"),
    DATABASE_SAVE_CLASS_INIT(89, "Unable to save object"),
    DATABASE_LOAD_CLASS_INIT(93, "Unable to load object"),
    DATA_LINK_METHOD_NOT_FOUND(87, "While creating the database model we could not find a required method"),
    DATABASE_OBJECT_METHOD_NOT_FOUND(88, "Could not find method for DatabaseObject when trying to update database"),
    FILE_UPLOAD_ERROR(89, "File could not be uploaded"),
    SDE_JOIN_THREAD(58, "Error joining SDE Thread"),
    NOT_VALID_JSON(32, "Not valid JSON"),
    PROCESS_LISTEN(33, "Exception listening to process"),
    GET_EXCEPTION(200, "Exception within GET method"),
    POST_EXCEPTION(201, "Exception within POST method"),
    PUT_EXCEPTION(202, "Exception within PUT method"),
    DELETE_EXCEPTION(203, "Exception within DELETE method"),
    OPTIONS_EXCEPTION(204, "Exception within OPTIONS method"),
    GENERIC_REQUEST_EXCEPTION(205, "Exception within request"),
    DATABASE_NOT_ENABLED_EXCEPTION(206, "Database action attempted with database set to disabled"),
    GENERIC_WEBSOCKET_EXCEPTION(207, "Exception within websocket"),
    WEBSOCKET_RESPONSE_EXCEPTION(208, "Error sending websocket response"),
    DATA_LOAD_EXCEPTION(209, "Error loading initial data into the game"),
    WEBSOCKET_PARSE_METHOD(210, "Method could not be found for data"),
    TYPE_SCRIPT_GENERATION(211, "Error generating TypeScript files from Data Files"),
    TYPE_SCRIPT_BUILD_FOLDER_URI(212, "Problem finding the typescript source code folder in the project to generate files to"),
    WEBSOCKET_JSON_RESPONSE(213, "Unable to generate response json for websocket"),

    // CLARITY
    CLARITY_REFERENCE_NOT_FOUND(214, "Clarity - reference not found"),
    CLARITY_NULL_DEFINITION(215, "Attempted to load null definition"),
    CLARITY_SOLVE_EXPRESSION(217, "Unable to solve expression"),
    CLARITY_DUPLICATE(218, "Error duplicating node to instanced node"),
    CLARITY_INCORRECT_CHILD_ASSIGNMENT_TO_NODE_TYPE(219, "Attempted to assign a child to an incorrect node type"),
    CLARITY_MISSING_FUNCTION_PARAMETER_NUMBER(220, "Function missing defined parameter number annotation"),

    CLARITY_INCORRECT_NUMBER_OF_PARAMETERS(216, "Incorrect number of parameters for function");

    private Integer code;
    private String description;

    Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public RecordedError record() {
        return new RecordedError(code, description, this.name());
    }
}
