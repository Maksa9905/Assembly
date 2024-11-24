public class CommandCount {
    private final String commandName;
    private Integer count = 0;

    public CommandCount(String _commandName) {
        commandName = _commandName;
    }

    public CommandCount(String _commandName, Integer _count) {
        commandName = _commandName;
        count = _count;
    }

    public Integer getCount() {
        return count;
    }

    public String getCommandName() {
        return commandName;
    }

    public Integer incrementCount() {
        count = count + 1;
        return count;
    }

    public void print() {
        System.out.println('{' + getCommandName() + ':' + getCount() + '}');
    }
}
