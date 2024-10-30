package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionImageTest {
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    @Test
    void 이미지_사이즈_검증() {
        assertThatThrownBy(() -> {
            new SessionImage(1025, 600, 400, "jpg");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이미지의 크기는 300x200 이상이어야 하며, 비율은 3:2 여야 한다.")
    @Test
    void 이미지_비율_검증() {
        assertAll(
                () -> assertThatThrownBy(() -> {
                    new SessionImage(1025, 150, 100, "jpg");
                }).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> {
                    new SessionImage(1025, 600, 500, "jpg");
                }).isInstanceOf(IllegalArgumentException.class)
        );
    }

    @DisplayName("이미지 타입은 gif, jpg(jpeg), png, svg만 허용된다.")
    @Test
    void 이미지_타입_검증() {
        assertThatThrownBy(() -> {
            new SessionImage(1025, 600, 400, "jpgs");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
