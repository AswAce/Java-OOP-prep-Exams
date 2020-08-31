package motocrossWorldChampionship;

import motocrossWorldChampionship.core.ChampionshipControllerImpl;
import motocrossWorldChampionship.core.EngineImpl;
import motocrossWorldChampionship.core.Motorcycle.Power;
import motocrossWorldChampionship.core.Race.RaceImpl;
import motocrossWorldChampionship.core.Rider.RiderImpl;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

public class Main {
    public static void main(String[] args) {
        Engine engine=new EngineImpl();
        engine.run();


//        ChampionshipController championshipController = new ChampionshipControllerImpl();
//
//        championshipController.createRace("Asenasw", 3);
//
//        championshipController.createMotorcycle("Power","Harly", 80);
//        championshipController.createMotorcycle("Power","Harly2", 81);
//        championshipController.createMotorcycle("Power","Harly3", 82);
//        championshipController.createRider("Asen");
//        championshipController.createRider("Asen2");
//        championshipController.createRider("Asen3");
//        championshipController.addMotorcycleToRider("Asen","Harly2");
//        championshipController.addMotorcycleToRider("Asen2","Harly2");
//        championshipController.addMotorcycleToRider("Asen3","Harly3");
//        championshipController.addRiderToRace("Asenasw","Asen");
//        championshipController.addRiderToRace("Asenasw","Asen2");
//        championshipController.addRiderToRace("Asenasw","Asen3");
//
//        System.out.println(championshipController.startRace("Asenasw"));
    }
}
