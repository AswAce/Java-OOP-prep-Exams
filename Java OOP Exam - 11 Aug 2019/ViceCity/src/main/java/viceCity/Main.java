package viceCity;

import viceCity.core.ControllerImp;
import viceCity.core.EngineImpl;
import viceCity.core.interfaces.Controller;
import viceCity.core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {
        Controller controller = new  ControllerImp();
        Engine engine = new EngineImpl(controller);
        engine.run();

    }
}
