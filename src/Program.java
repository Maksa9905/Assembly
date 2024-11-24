import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Program implements Iterable<Command> {
    private final ArrayList<Command> commands;

    private int minMem = 1025;
    private int maxMem = -1;

    public Program() {
        commands = new ArrayList<Command>();
    }

    public Program(ArrayList<Command> _commands) {
        commands = _commands;
    }

    public void add(Command command) {
        commands.add(command);

        if (command.getName().equals("init")) {
            int memory = Integer.parseInt(command.getArgs()[0]);

            minMem = Math.min(minMem, memory);
            maxMem = Math.max(maxMem, memory);
        }
    }

    public Command get(int i) throws Exception {
        if (i < 0 || i > commands.size()) {
            throw new Exception("index is out of range");
        }

        return commands.get(i);
    }

    public int size() {
        return commands.size();
    }

    private final Comparator<CommandCount> commandsCountComparator = new Comparator<CommandCount>() {
        @Override
        public int compare(CommandCount o1, CommandCount o2) {
            return o1.getCount() - o2.getCount();
        }
    };

    private final Function<Map.Entry<String, Integer>, CommandCount> commandCountMapper = new Function<>() {
        @Override
        public CommandCount apply(Map.Entry<String, Integer> entry) {
            return new CommandCount(entry.getKey(), entry.getValue());
        }
    };

    public ArrayList<CommandCount> getSortedCommandsCount() {
        return commands.stream().collect(Collectors.groupingBy(
                        Command::getName,
                        Collectors.summingInt(command -> 1)))
                .entrySet()
                .stream().map(commandCountMapper)
                .sorted(commandsCountComparator)
                .collect(Collectors.toCollection(ArrayList::new));
    };

    public String getPopularInstructionName() {
        return getSortedCommandsCount().getLast().getCommandName();
    };

    public Integer[] getRangeOfMemory() {
        return new Integer[]{minMem, maxMem};
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }

//    private final Comparator<Map.Entry<String, Integer>> commandsComparator = new Comparator<>() {
//        @Override
//        public int compare(Map.Entry<String, Integer> command1, Map.Entry<String, Integer> command2) {
//            return command1.getValue() - command2.getValue();
//        }
//    };


    //    private Map<String, Integer> getCommandsCountMap() {
//        return commands.stream()
//                .collect(Collectors.groupingBy(Command::getName, Collectors.summingInt(command -> 1)));
//    }

//    public String getPopularCommandName() {
//        return Collections.max(getCommandsCountMap().entrySet(), Map.Entry.comparingByValue()).getKey();
//
//
////         return Objects.requireNonNull(
////                        commandsMap
////                                .entrySet()
////                                .stream()
////                                .max(Map.Entry.comparingByValue())
////                                .orElse(null)
////                )
////                .getKey();
//    }

//    public List<Map.Entry<String, Integer>> getPopularInstructionsList() {
//        Map<String, Integer> commandsMap = getCommandsCountMap();
//
//        return commandsMap
//                .entrySet()
//                .stream()
//                .sorted(commandsComparator).toList();
//    };
}

