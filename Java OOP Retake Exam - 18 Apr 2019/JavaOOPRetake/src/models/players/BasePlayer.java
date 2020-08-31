package models.players;

import models.cards.interfaces.Card;
import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;

public abstract class BasePlayer implements Player {
    private String name;
    private int health;
    private CardRepository cardRepository;
    private boolean isDead;

    protected BasePlayer(CardRepository cardRepository, String name, int health) {
        setName(name);
        setHealth(health);
        this.cardRepository = cardRepository;
        this.isDead = false;
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player's username cannot be null or an empty string. ");
        }
        this.name = name;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    @Override
    public CardRepository getCardRepository() {
        return this.cardRepository;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int healthPoints) {
        if (healthPoints < 0) {
            throw new IllegalArgumentException("Player's health bonus cannot be less than zero. ");
        }
        this.health = healthPoints;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public void takeDamage(int damagePoints) {
        if (damagePoints < 0) {
            throw new IllegalArgumentException("Damage points cannot be less than zero.");
        }
        if (this.getHealth() - damagePoints >= 0) {
            this.setHealth(this.getHealth() - damagePoints);
        } else {
            this.setHealth(0);

        }
        if (this.getHealth()<=0){
            this.setDead(true);
        }

    }

    @Override
    public String toString() {
        StringBuilder player = new StringBuilder("Username: " + this.getUsername() + " - Health: " + this.getHealth() + " - Cards " +
                this.getCardRepository().getCount()).append(System.lineSeparator());

        for (Card card : this.getCardRepository().getCards()) {
            player.append(card.toString()).append(System.lineSeparator());
        }
        player.append("###").append("\n ");
        return player.toString().trim();
    }
}
