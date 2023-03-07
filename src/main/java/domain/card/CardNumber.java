package domain.card;

import java.util.Arrays;
import java.util.List;

public enum CardNumber {

    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    KING(10),
    QUEEN(10),
    JACK(10);

    private final int score;

    CardNumber(final int score) {
        this.score = score;
    }

    static List<CardNumber> findTotalCardNumber() {
        return Arrays.asList(values());
    }

    public int score() {
        return score;
    }

    boolean checkAce() {
        return this == ACE;
    }
}
