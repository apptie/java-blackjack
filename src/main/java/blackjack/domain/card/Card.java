package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private Type type;
    private Denomination denomination;

    public Card(Type type, Denomination denomination) {
        this.type = type;
        this.denomination = denomination;
    }

    public Type getType() {
        return type;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public int value() {
        return denomination.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, denomination);
    }

    @Override
    public String toString() {
        return denomination.getName() + type.getName();
    }
}
