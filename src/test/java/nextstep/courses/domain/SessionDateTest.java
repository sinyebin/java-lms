package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionDateTest {
    @DisplayName("시작일이 종료일 보다 뒤에 있을시 에러 발생")
    @Test
    void 강의기간_에러() {
        LocalDateTime startDay = LocalDateTime.of(2024, 10, 28, 0, 0);
        LocalDateTime endDay = LocalDateTime.of(2024, 10, 9, 0, 0);
        assertThatThrownBy(() -> {
            new SessionDate(startDay, endDay);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
