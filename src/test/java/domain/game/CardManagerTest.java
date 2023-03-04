package domain.game;

import domain.CardSelector;
import domain.card.CardManager;
import domain.card.Deck;
import domain.card.RandomUniqueCardSelector;
import domain.participant.Participants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CardManagerTest {

    private final Deck deck = Deck.create(new RandomUniqueCardSelector());
    private final Participants participants = Participants.create(List.of("a"));

    @Test
    @DisplayName("create()는 덱과 참가자 정보를 받으면 게임 관리자를 생성한다")
    void create_givenDeckAndParticipants_thenSuccess() {
        final CardManager cardManager = assertDoesNotThrow(() -> CardManager.create(deck, participants));

        assertThat(cardManager)
                .isExactlyInstanceOf(CardManager.class);
    }

    @Test
    @DisplayName("giveCards()는 참가자의 순서를 받으면, 카드를 건네준다")
    void giveCards_givenParticipantOrder_thenSuccess() {
        final CardManager cardManager = CardManager.create(deck, participants);

        assertThatCode(() -> cardManager.giveCards(0, 2))
                .doesNotThrowAnyException();
    }
}
