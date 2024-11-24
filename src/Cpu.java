import java.util.HashMap;
import java.util.Map;

public class Cpu implements ICpu {
    private final int[] memo = new int[1024];
    private final Map<String, Integer> registers = new HashMap<>();

    public Cpu() {
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);
    }

    public void execute(Command c) {
        String name = c.getName();
        String[] arguments = c.getArgs();

        switch (name) {
            case "init": // инициализация памяти по адресу значением
                memo[Integer.parseInt(arguments[0])] = Integer.parseInt(arguments[1]);
                break;
            case "mv": // копирование из регистра в регистр
                registers.put(arguments[0], registers.get(arguments[1]));
                break;
            case "ld": // загрузка данных из памяти в регистр
                registers.put(arguments[0], memo[Integer.parseInt(arguments[1])]);
                break;
            case "st": // загрузка данных из регистра в память
                memo[Integer.parseInt(arguments[1])] = registers.get(arguments[0]);
                break;
            case "mult": // берет значение в регистре "a" умножает с значением из регистра "b" и записывает в "d"
                registers.put("d", registers.get("a") * registers.get("b"));
                break;
            case "div": // берет значение в регистре "a" делит на значение из регистра "b" и записывает в "d"
                registers.put("d", registers.get("a") / registers.get("c"));
                break;
            case "add": // берет значение в регистре "a" складывают с значением из регистра "b" и записывает в "d"
                registers.put("d", registers.get("a") + registers.get("b"));
                break;
            case "sub": // берет значение в регистре "a" вычитает из него значение из регистра "b" и записывает в "d"
                registers.put("d", registers.get("a") - registers.get("b"));
                break;
            case "print":
                System.out.println(registers);
                break;
            default:
                System.out.println("Неизвестная команда");
        }
    }
}
