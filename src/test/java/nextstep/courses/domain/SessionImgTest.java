package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionImgTest {
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    @Test
    void 이미지_사이즈_검증() {
        assertThatThrownBy(() -> {
            new SessionImg(1025);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
