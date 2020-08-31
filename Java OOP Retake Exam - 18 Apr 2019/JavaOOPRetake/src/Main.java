import core.EngineImpl;
import core.ManagerControllerImpl;
import core.interfaces.Engine;
import core.interfaces.ManagerController;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Engine engine = new EngineImpl();
        engine.run();
    }
}
