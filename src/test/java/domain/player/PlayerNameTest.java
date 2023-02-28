package domain.player;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PlayerNameTest {

    @ParameterizedTest(name = "create()는 파라미터로 입력된 name이 null이거나 비어 있으면 예외가 발생한다")
    @NullAndEmptySource
    void create_givenEmptyName_thenFail(final String name) {
        assertThatThrownBy(() -> PlayerName.create(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }

    @ParameterizedTest(name = "create()는 파라미터로 입력된 name의 길이가 1~20글자 사이가 아니라면 예외가 발생한다")
    @ValueSource(strings = {"abcdeabcdeabcdeabcdea"})
    void create_givenInvalidName_thenFail(final String invalidName) {
        assertThatThrownBy(() -> PlayerName.create(invalidName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 1글자에서 20글자 사이여야 합니다.");
    }
}