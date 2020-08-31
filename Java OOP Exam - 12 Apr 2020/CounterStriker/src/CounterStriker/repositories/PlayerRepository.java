package repositories;

import common.ExceptionMessages;
import models.players.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerRepository implements Repository<Player> {
    private List<Player> players;

    public PlayerRepository() {
        this.players = new ArrayList<>();
    }

    @Override
    public Collection<Player> getModels() {
        return this.players.stream().sorted(
                (p1, p2) -> {
                    String player1 = p1.getClass().getSimpleName();
                    String player2 = p2.getClass().getSimpleName();
                    if (player2.equals(player1)) {
                        return p1.getUsername().compareTo(p2.getUsername());
                    }
                    return player1.compareTo(player2);
                }


        ).collect(Collectors.toList());
    }

    @Override
    public void add(Player model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_REPOSITORY);
        }
        this.players.add(model);
    }

    @Override
    public boolean remove(Player model) {
        return this.players.remove(model);
    }

    @Override
    public Player findByName(String name) {
        for (Player player : players) {
            if (player.getUsername().equals(name)) {
                return player;
            }
        }
        return null;
    }
}
