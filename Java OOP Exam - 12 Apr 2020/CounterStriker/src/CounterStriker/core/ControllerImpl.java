package core;

import common.ExceptionMessages;
import common.OutputMessages;
import models.field.Field;
import models.field.FieldImpl;
import models.guns.Gun;
import models.guns.Pistol;
import models.guns.Rifle;
import models.players.CounterTerrorist;
import models.players.Player;
import models.players.Terrorist;
import repositories.GunRepository;
import repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ControllerImpl implements Controller {
    private GunRepository guns;
    private PlayerRepository players;
    Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun = null;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name, bulletsCount);
                break;
            case "Rifle":
                gun = new Rifle(name, bulletsCount);
                break;

            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);
        }
        this.guns.add(gun);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Player player = null;
        Gun gunForPlayer = (Gun) this.guns.findByName(gunName);
        if (gunForPlayer == null) {
            throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }
        switch (type) {
            case "Terrorist":
                player = new Terrorist(username, health, armor, gunForPlayer);
                break;
            case "CounterTerrorist":
                player = new CounterTerrorist(username, health, armor, gunForPlayer);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        this.players.add(player);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        Collection<Player> playersREadyToplay = new ArrayList<>();
        this.players.getModels().stream().filter(Player::isAlive).forEach(playersREadyToplay::add);
        return this.field.start(playersREadyToplay);
    }

    @Override
    public String report() {
        StringBuilder reporting = new StringBuilder();

        for (Player player : this.players.getModels()) {
            reporting.append(player.toString()).append(System.lineSeparator());
        }

        return reporting.toString().trim();
    }

}
