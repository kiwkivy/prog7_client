package commands;

public enum CommandType {
    ADD("add"),
    CLEAR("clear"),
    COUNT_BY_COLOR("count_by_color"),
    EXECUTE_SCRIPT("execute_script"),
    EXIT("exit"),
    FILTER_STARTS_WITH_NAME("filter_starts_with_name"),
    HELP("help"),
    INFO("info"),
    PRINT_FIELD_DESCENDING_CAVE("print_field_descending_cave"),
    REMOVE_BY_ID("remove_by_id"),
    REMOVE_LOWER("remove_lower"),
    REORDER("reorder"),
    SHOW("show"),
    UPDATE("update"),
    START("start");


    private String name;

    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
