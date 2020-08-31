package core;

import core.interfaces.ManagerController;
import models.battleFields.BattleFieldImpl;
import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;
import repositories.CardRepositoryImpl;
import repositories.interfaces.PlayerRepository;
import repositories.PlayerRepositoryImpl;

import java.lang.reflect.InvocationTargetException;


public class ManagerControllerImpl implements ManagerController {
    private CardRepository cardRepository;
    private PlayerRepository playerRepository;
    private Battlefield battlefield;

    public ManagerControllerImpl() {
        this.cardRepository = new CardRepositoryImpl();
        this.playerRepository = new PlayerRepositoryImpl();
        this.battlefield = new BattleFieldImpl();
    }

    @Override
    public String addPlayer(String type, String username) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> playerClass = Class.forName("models.players." + type);
//
        Player player = (Player) playerClass.getConstructor(CardRepository.class, String.class).newInstance
                (new CardRepositoryImpl(), username);
//
        this.playerRepository.add(player);

        //Може да трябва със свутча да се оправи
        return String.format("Successfully added player of type %s with username: %s", type, username
        );


    }

    @Override
    public String addCard(String type, String name) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cardCLass = Class.forName("models.cards." + type);
        Card card = (Card) cardCLass.getConstructor(String.class).newInstance(name);
        this.cardRepository.add(card);
        // Може да трябва със суитч
        return String.format("Successfully added card of type %sCard with name: %s", type, name
        );
    }

    @Override
    public String addPlayerCard(String username, String cardName) {
        Card cardToAddToPlayerDeck = cardRepository.find(cardName);


        this.playerRepository.find(username).getCardRepository().getCards().add(cardToAddToPlayerDeck);

        return String.format("Successfully added card: %s to user: %s", cardName, username
        );
    }

    @Override
    public String fight(String attackUser, String enemyUser) {
        battlefield.fight(playerRepository.find(attackUser),
                playerRepository.find(enemyUser));

        return String.format("Attack user health %s - Enemy user health %s"
                , playerRepository.find(attackUser).getHealth(),
                playerRepository.find(enemyUser).getHealth()

        );
    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : playerRepository.getPlayers()) {
            stringBuilder.append(player.toString()).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
