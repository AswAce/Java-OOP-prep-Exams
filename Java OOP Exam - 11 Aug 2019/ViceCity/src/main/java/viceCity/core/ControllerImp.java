package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ControllerImp implements Controller {
    private ArrayDeque<Gun> gunRepository;
    private Player mainPlayer;
    private List<Player> civilPlayers;
    private Neighbourhood neighbourhood;

    public ControllerImp() {
        this.gunRepository = new ArrayDeque<>();
        this.civilPlayers = new ArrayList<>();
        this.mainPlayer = new MainPlayer();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        civilPlayers.add(new CivilPlayer(name));

        return String.format(ConstantMessages.PLAYER_ADDED, name);

    }

    @Override
    public String addGun(String type, String name) {
        if (!type.equals("Pistol") && !type.equals("Rifle")) {
            return ConstantMessages.GUN_TYPE_INVALID;
        }
        if (type.equals("Pistol")) {
            this.gunRepository.offer(new Pistol(name));
        } else {
            this.gunRepository.offer(new Rifle(name));
        }

        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (this.gunRepository.isEmpty()) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }
        Gun gunForPlayer = this.gunRepository.poll();
        if (name.equals("Vercetti")) {

            this.mainPlayer.getGunRepository().add(gunForPlayer);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER,
                    gunForPlayer.getName(), "Tommy Vercetti");
        }
        if (this.civilPlayers.stream().noneMatch(e -> e.getName().equals(name))) {
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
            ////Може да не работи като хората
        } else {
            for (Player civilPlayer : this.civilPlayers) {
                if (civilPlayer.getName().equals(name)) {
                    civilPlayer.getGunRepository().add(gunForPlayer);
                }
            }
            return String.format(
                    ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gunForPlayer.getName(), name);
        }

    }

    @Override
    public String fight() {
        neighbourhood.action(this.mainPlayer, this.civilPlayers);

        boolean fight = false;
        for (Player civilPlayer : this.civilPlayers) {
            if (civilPlayer.getLifePoints() < 50) {
                fight = true;
            }
        }
        if (this.mainPlayer.getLifePoints() < 100 || fight) {
            int deathPlayers = 0;
            for (Player civilPlayer : this.civilPlayers) {
                if (!civilPlayer.isAlive()) {
                    deathPlayers++;
                }
            }

            StringBuilder builder = new StringBuilder(ConstantMessages.FIGHT_HAPPENED).
                    append(System.lineSeparator());
            builder.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, this.mainPlayer.getLifePoints()
            )).append(System.lineSeparator());
            builder.append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deathPlayers
            )).append(System.lineSeparator());
            this.civilPlayers.removeIf(civilPlayer -> !civilPlayer.isAlive());

            builder.append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, this.civilPlayers.size()
            ));

            return builder.toString().trim();

        }

        return ConstantMessages.FIGHT_HOT_HAPPENED;
    }
}



