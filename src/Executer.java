public class Executer {
    private final ICpu cpu;

    public Executer(ICpu _cpu) {
        cpu = _cpu;
    }

    public void run(Program program) throws Exception {
        for (int i = 0; i < program.size(); i++) {
            cpu.execute(program.get(i));
        }
    }
}
