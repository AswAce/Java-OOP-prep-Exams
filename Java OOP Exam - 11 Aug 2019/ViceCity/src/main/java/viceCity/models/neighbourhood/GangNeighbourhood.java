package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        for (Player civilPlayer : civilPlayers) {
            for (Gun gun : mainPlayer.getGunRepository().getModels()) {
                while (civilPlayer.isAlive() && gun.canFire()) {
                    civilPlayer.takeLifePoints(gun.fire());
                }

                if (!civilPlayer.isAlive()) {
                    break;
                }
            }
        }

        for (Player civilPlayer : civilPlayers) {
            if (!civilPlayer.isAlive()) {
                continue;
            }

            for (Gun gun : civilPlayer.getGunRepository().getModels()) {
                while (mainPlayer.isAlive() && gun.canFire()) {
                    mainPlayer.takeLifePoints(gun.fire());
                }

                if (!mainPlayer.isAlive()) {
                    break;
                }
            }

            if (!mainPlayer.isAlive()) {
                break;
            }
        }
    }


//        for (Gun model : mainPlayer.getGunRepository().getModels()) {
//            while (model.canFire() && model.getTotalBullets() > 0) {
//                for (Player civilPlayer : civilPlayers) {
//                    while (civilPlayer.isAlive() && model.canFire() && model.getTotalBullets() > 0) {
//                        civilPlayer.takeLifePoints(model.fire());
//                    }
//                    civilPlayers.removeIf(e -> !e.isAlive());
//                }
//
//            }
//        }

}

