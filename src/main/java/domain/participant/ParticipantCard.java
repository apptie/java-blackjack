package domain.participant;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

import static domain.card.CardNumber.ACE;

public class ParticipantCard {

    private static final int FIRST_CARD_INDEX = 0;
    private static final int ACE_HIGH_POINTS = 11;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_SIZE = 2;

    private final List<Card> cards;

    private ParticipantCard() {
        this.cards = new ArrayList<>();
    }

    public static ParticipantCard create() {
        return new ParticipantCard();
    }

    void addCard(final Card card) {
        cards.add(card);
    }

    Card getFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }

    int calculateScore() {
        int score = sumCards();

        if (score <= ACE_HIGH_POINTS && hasAce()) {
            score += (ACE_HIGH_POINTS - ACE.findNumber());
        }
        return score;
    }

    boolean checkBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    boolean checkBlackJack() {
        return cards.size() == BLACKJACK_SIZE && calculateScore() == BLACKJACK_SCORE;
    }

    private int sumCards() {
        return cards.stream()
                .mapToInt(Card::findCardNumber)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::checkAce);
    }

    List<Card> getCards() {
        return List.copyOf(cards);
    }
}
