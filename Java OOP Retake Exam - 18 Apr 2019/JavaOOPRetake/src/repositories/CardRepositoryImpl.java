package repositories;

import models.cards.interfaces.Card;
import repositories.interfaces.CardRepository;

import java.util.ArrayList;
import java.util.List;

public class CardRepositoryImpl implements CardRepository {

    private int count;
    private List<Card> cards;

    public CardRepositoryImpl() {
        this.count = 0;
        this.cards = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.cards.size();
    }

    @Override
    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public void add(Card card) {
        NullCard(card);
        for (Card card1 : cards) {
            if (card1.getName().equals(card.getName())) {
                throw new IllegalArgumentException
                        (String.format("Card %s already exists!", card.getName()));
            }
        }
        this.count++;
        cards.add(card);

    }

    @Override
    public boolean remove(Card card) {
        NullCard(card);
        this.count--;
        return cards.remove(card);
    }


    @Override
    public Card find(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }
        return null;
    }

    private void NullCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null!");
        }
    }
}
