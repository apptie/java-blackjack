package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

class ParticipantCardTest {

    private Card card;
    private ParticipantCard participantCard;

    @BeforeEach
    void init() {
        card = Card.create(CardPattern.HEART, CardNumber.ACE);
        participantCard = ParticipantCard.create();
    }

    @Test
    @DisplayName("create()는 호출하면, ParticipantCard를 생성한다")
    void create_whenCall_thenSuccess() {
        assertThatCode(() -> ParticipantCard.create())
                .doesNotThrowAnyException();

        assertThat(ParticipantCard.create())
                .isExactlyInstanceOf(ParticipantCard.class);
    }

    @Test
    @DisplayName("addCard()는 카드를 건네주면 참가자의 카드에 추가한다")
    void addCard_givenCard_whenSuccess() {
        // when
        participantCard.addCard(card);

        // then
        assertThat(participantCard)
                .extracting("cards", as(list(Card.class)))
                .hasSize(1);
    }

    @Test
    @DisplayName("getFirst()는 호출하면, 참가자의 첫 번째 카드를 조회한다")
    void getFirst_whenCall_thenReturnFirstCard() {
        // given
        participantCard.addCard(card);

        // when
        Card actual = participantCard.getFirstCard();

        // then
        assertThat(actual).isSameAs(card);
    }

    @MethodSource(value = "domain.helper.ParticipantArguments#makeCards")
    @ParameterizedTest(name = "calculateScore()는 호출하면 점수를 계산한다")
    void calculateScore_whenCall_thenReturnScore(final List<Card> cards, final int expected) {
        // given
        cards.forEach(participantCard::addCard);

        // when
        int score = participantCard.calculateScore();

        // then
        assertThat(score)
                .isSameAs(expected);
    }
}
