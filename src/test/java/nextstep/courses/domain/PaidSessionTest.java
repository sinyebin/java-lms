package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaidSessionTest {
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    @Test
    void 최대_수강인원_초과_에러() {
        Session session = new PaidSession("세션", LocalDateTime.of(2024, 10, 28, 0, 0),
                LocalDateTime.of(2024, 11, 28, 0, 0), SessionStatus.OPEN, 0);
        assertThatThrownBy(() -> {
            session.register(JAVAJIGI);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
