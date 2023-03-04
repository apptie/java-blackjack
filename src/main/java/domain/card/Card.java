package domain.card;

import java.util.Objects;

public class Card {

    private final CardPattern pattern;
    private final CardNumber number;

    private Card(final CardPattern pattern, final CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public static Card create(final CardPattern pattern, final CardNumber number) {
        return new Card(pattern, number);
    }

    public int findCardNumber() {
        return number.findNumber();
    }

    public boolean checkAce() {
        return number.checkAce();
    }

    public CardPattern getCardPattern() {
        return pattern;
    }

    public CardNumber getCardNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Card)) {
            return false;
        }
        Card targetCard = (Card) target;
        return pattern == targetCard.pattern && number == targetCard.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }
}
