package Commands;

public enum CommandType {
    ADD("add"),
    CLEAR("clear"),
    COUNT_BY_COLOR("count_by_color"),
    EXECUTE_SCRIPT("execute_script"),
    EXIT("exit"),
    FILTER_STARTS_WITH_NAME("filter_starts_with_name"),
    HELP("help"),
    INFO("info"),
    INSERT_AT("insert_at"),
    PRINT_FIELD_DESCENDING_CAVE("print_field_descending_cave"),
    REMOVE_BY_ID("remove_by_id"),
    REMOVE_LOWER("remove_lower"),
    REORDER("reorder"),
    SAVE("save"),
    SHOW("show"),
    UPDATE("update");



    private String name;

    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
