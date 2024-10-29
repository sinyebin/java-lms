package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionImgTest {
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    @Test
    void 이미지_사이즈_검증() {
        assertThatThrownBy(() -> {
            new SessionImg(1025, 600, 400);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이미지의 크기는 300x200 이상이어야 하며, 비율은 3:2 여야 한다.")
    @Test
    void 이미지_비율_검증() {
        assertAll(
                () -> assertThatThrownBy(() -> {
                    new SessionImg(1025, 150, 100);
                }).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> {
                    new SessionImg(1025, 600, 500);
                }).isInstanceOf(IllegalArgumentException.class)
        );
    }
}
