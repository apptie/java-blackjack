package domain.participant;

public class Player extends Participant {

    private static final String INVALID_NAME = "딜러";

    private Player(final String name) {
        super(name);
    }

    public static Player create(final String name) {
        validatePlayerName(name);
        return new Player(name);
    }

    private static void validatePlayerName(final String name) {
        if (INVALID_NAME.equals(name)) {
            throw new IllegalArgumentException("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
        }
    }
}