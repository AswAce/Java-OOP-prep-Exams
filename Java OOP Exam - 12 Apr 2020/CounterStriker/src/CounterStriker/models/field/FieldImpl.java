package models.field;

import common.OutputMessages;
import models.players.CounterTerrorist;
import models.players.Player;
import models.players.Terrorist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FieldImpl implements Field {
    private List<Player> terrorists;
    private List<Player> counterTerrorists;

    public FieldImpl() {
        this.counterTerrorists = new ArrayList<>();
        this.terrorists = new ArrayList<>();
    }

    @Override
    public String start(Collection<Player> players) {
        setTeams(players);
        while (!this.terrorists.isEmpty() && !this.counterTerrorists.isEmpty()) {
            fireStarts(this.terrorists, this.counterTerrorists);
            fireStarts(this.counterTerrorists, this.terrorists);
            this.terrorists.removeIf(terrorist -> !terrorist.isAlive());
            this.counterTerrorists.removeIf(counterTerrorist -> !counterTerrorist.isAlive());
        }
        if (this.counterTerrorists.isEmpty()) {
            return OutputMessages.TERRORIST_WINS;
        }
        return OutputMessages.COUNTER_TERRORIST_WINS;
    }

    private void fireStarts(List<Player> shooting, List<Player> shooed) {
        for (Player shootingPlayer : shooting) {
            if (shootingPlayer.isAlive()) {
                for (Player shooedPlayer : shooed) {
                    shooedPlayer.takeDamage(shootingPlayer.getGun().fire());
                }
            }
        }
    }

    private void setTeams(Collection<Player> players) {
        for (Player player : players) {
            if (player instanceof Terrorist) {
                this.terrorists.add(player);
            } else {
                this.counterTerrorists.add(player);
            }
        }
    }
}
