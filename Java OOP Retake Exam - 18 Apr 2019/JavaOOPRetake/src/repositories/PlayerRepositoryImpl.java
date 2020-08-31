package repositories;

import models.players.interfaces.Player;
import repositories.interfaces.PlayerRepository;


import java.util.ArrayList;
import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepository {

    private int count;
    private List<Player> players;

    public PlayerRepositoryImpl() {
        this.count = 0;
        this.players = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public void add(Player player) {
        nullPlayer(player);
        for (Player player1 : players) {
            if (player.getUsername().equals(player1.getUsername())) {
                throw new IllegalArgumentException
                        (String.format("Player %s already exists!", player.getUsername()));
            }

        }
        this.count++;
        players.add(player);
    }


    @Override
    public boolean remove(Player player) {
        nullPlayer(player);

        return players.remove(player);
    }

    @Override
    public Player find(String name) {
        for (Player player : players) {
            if (player.getUsername().equals(name)) {
                return player;
            }
        }
        return null;
    }

    private void nullPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
    }
}
