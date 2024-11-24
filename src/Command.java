public class Command {
    private final String name;
    private final String[] arguments;

    public Command(String _name, String... _arguments) {
        name = _name;
        arguments = _arguments;
    }

    public Command(String c) {
        String[] sections = c.split(" ");

        name = sections[0];
        arguments = new String[sections.length - 1];

        System.arraycopy(sections, 1, arguments, 0, arguments.length);
    }

    public String getName() {
        return name;
    }

    public String[] getArgs() {
        return arguments;
    }
}
