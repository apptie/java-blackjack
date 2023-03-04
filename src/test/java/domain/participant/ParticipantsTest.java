package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantsTest {

    private Participants participants;

    @BeforeEach
    void init() {
        final List<String> playerNames = List.of("a", "b", "c", "d", "e");

        participants = Participants.create(playerNames);
    }

    @Nested
    class CreateStaticMethodTest {

        @MethodSource(value = "domain.helper.ParticipantArguments#validPlayerNames")
        @ParameterizedTest(name = "유효한 수의 플레이어 이름 컬렉션을 받으면 참가자 이름을 생성한다")
        void create_givenPlayerNames_thenSuccess(final List<String> playerNames) {
            final Participants participants = assertDoesNotThrow(() -> Participants.create(playerNames));

            assertThat(participants)
                    .isInstanceOf(Participants.class);
        }

        @MethodSource(value = "domain.helper.ParticipantArguments#invalidPlayerNames")
        @ParameterizedTest(name = "7명 초과의 플레이어 이름 컬렉션을 받으면 예외가 발생한다")
        void create_givenPlayerNames_thenFail(final List<String> playerNames) {
            assertThatThrownBy(() -> Participants.create(playerNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }

        @Test
        @DisplayName("중복된 플레이어 이름을 받으면 예외가 발생한다")
        void create_givenDuplicateNames_thenFail() {
            final List<String> duplicateNames = List.of("a", "b", "c", "d", "e", "a ");

            assertThatThrownBy(() -> Participants.create(duplicateNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
        }

    }

    @ParameterizedTest(name = "addCard()는 플레이어의 순서와 카드를 전달하면 정상적으로 실행된다.")
    @ValueSource(ints = {0, 1, 2, 3})
    void addCard_givenParticipantOrderAndCard_thenSuccess(final int participantOrder) {
        // given
        final Card card = Card.create(CardPattern.CLOVER, CardNumber.QUEEN);

        // when, then
        assertThatCode(() -> participants.addCard(participantOrder, card))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findDealer()는 호출하면 참가자들 중 딜러를 찾아 반환한다")
    void findDealer_whenCall_thenReturnDealer() {
        Participant dealer = participants.findDealer();

        assertThat(dealer).isExactlyInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("findPlayers()는 호출하면 참가자들 중 플레이어를 찾아 반환한다")
    void findPlayers_whenCall_thenReturnPlayers() {
        List<Participant> players = participants.findPlayers();

        players.forEach(player -> assertThat(player)
                .isExactlyInstanceOf(Player.class));
    }

    @Test
    @DisplayName("size()는 호출하면 모든 참가자의 수를 반환한다")
    void size_whenCall_thenReturnParticipantSize() {
        // when
        final int actual = participants.size();

        // then
        assertThat(actual)
                .isSameAs(6);
    }
}
