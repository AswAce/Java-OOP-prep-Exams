package models.battleFields;

import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.interfaces.Player;
import models.players.Beginner;

public class BattleFieldImpl implements Battlefield {
    @Override
    public void fight(Player attackPlayer, Player enemyPlayer) {
        if (attackPlayer.isDead() || enemyPlayer.isDead()) {
            throw new IllegalArgumentException("Player is dead!");
        }
        beggingLuckPlayer(attackPlayer);
        beggingLuckPlayer(enemyPlayer);
        upgradeHealthFromCards(attackPlayer);
        upgradeHealthFromCards(enemyPlayer);
        fightPlayers(attackPlayer, enemyPlayer);

    }

    private void fightPlayers(Player attackPlayer, Player enemyPlayer) {
        int roundAtack = 0;
        int roundEnemy = 0;
        int atackersStartHealth = attackPlayer.getHealth();
        int defStartHealth = enemyPlayer.getHealth();
        while (!attackPlayer.isDead() && !enemyPlayer.isDead()
        ) {
            if (attackPlayer.getCardRepository().getCount() == roundAtack) {
                roundAtack = 0;
            }
            if (enemyPlayer.getCardRepository().getCount() == roundEnemy) {
                roundEnemy= 0;
            }


            int attackerDMG = attackPlayer.getCardRepository().getCards().get(roundAtack).getDamagePoints();
            int enemyDMG = enemyPlayer.getCardRepository().getCards().get(roundEnemy).getDamagePoints();
            enemyPlayer.takeDamage(attackerDMG);
            if (!enemyPlayer.isDead()) {
                attackPlayer.takeDamage(enemyDMG);
            }


            roundAtack++;
            roundEnemy++;
        }
        if (!attackPlayer.isDead()) {
            attackPlayer.setHealth(atackersStartHealth);
        }
        if (!enemyPlayer.isDead()) {
            enemyPlayer.setHealth(defStartHealth);
        }
    }

    private void upgradeHealthFromCards(Player player) {
        int bonusHealth = 0;
        for (Card card : player.getCardRepository().getCards()) {
            bonusHealth += card.getHealthPoints();
        }
        player.setHealth(player.getHealth() + bonusHealth);
    }

    private void beggingLuckPlayer(Player player) {
        if (player instanceof Beginner) {
            player.setHealth(player.getHealth() + 40);
            player.getCardRepository().
                    getCards().
                    forEach(e -> e.setDamagePoints(30 + e.getDamagePoints()));
        }
    }
}
