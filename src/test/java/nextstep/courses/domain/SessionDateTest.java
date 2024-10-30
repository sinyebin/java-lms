package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionDateTest {
    @DisplayName("시작일이 종료일 보다 뒤에 있을시 에러 발생")
    @Test
    void 강의기간_에러() {
        LocalDate startDay = LocalDate.of(2024, 10, 28);
        LocalDate endDay = LocalDate.of(2024, 10, 9);
        assertThatThrownBy(() -> {
            new SessionDate(startDay, endDay);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
