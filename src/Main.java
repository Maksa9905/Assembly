import java.util.SortedMap;

public class Main {
    public static void main(String[] args) throws Exception {
        Program program = new Program();

        program.add(new Command("init 10 20"));
        program.add(new Command("init", "11", "25"));
        program.add(new Command("init", "40", "5"));
        program.add(new Command("ld", "a", "10"));
        program.add(new Command("ld", "b", "11"));
        program.add(new Command("ld", "c", "40"));
        program.add(new Command("add"));
        program.add(new Command("print"));
        program.add(new Command("mv a d"));
        program.add(new Command("mv b c"));
        program.add(new Command("div"));
        program.add(new Command("print"));

        ICpu cpu = BCpu.build();
        Executer executer = new Executer(cpu);
        executer.run(program);
        System.out.print('\n');

        System.out.println("Все команды:");
        program.forEach(c -> System.out.print(c.getName() + ' '));
        System.out.println('\n');

        System.out.println("Диапазон в памяти:");
        System.out.print(program.getRangeOfMemory()[0] + "-" + program.getRangeOfMemory()[1]);
        System.out.println('\n');

        System.out.println("Самая популярная инструкция: ");
        System.out.print(program.getPopularInstructionName());
        System.out.println('\n');

        System.out.println("Инструкрции в порядке возрастания: ");
        for (CommandCount commandCount: program.getSortedCommandsCount()) {
            commandCount.print();
        }
        System.out.println('\n');

    }
}