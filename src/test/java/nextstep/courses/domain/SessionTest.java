package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SessionTest {
    @DisplayName("세션 준비중")
    @Test
    void 세션_준비중() {
        Session session = new FreeSession("세션", LocalDateTime.of(2024, 10, 28, 0, 0),
                LocalDateTime.of(2024, 11, 28, 0, 0));
        session.readySession();
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.READY);
    }

    @DisplayName("세션 모집중")
    @Test
    void 세션_모집중() {
        Session session = new FreeSession("세션", LocalDateTime.of(2024, 10, 28, 0, 0),
                LocalDateTime.of(2024, 11, 28, 0, 0));
        session.openSession();
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.OPEN);
    }

    @DisplayName("세션 종료")
    @Test
    void 세션_종료() {
        Session session = new FreeSession("세션", LocalDateTime.of(2024, 10, 28, 0, 0),
                LocalDateTime.of(2024, 11, 28, 0, 0));
        session.closeSession();
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.CLOSED);
    }

    @DisplayName("세션 오픈이 안되어서 수강생 등록 실패")
    @Test
    void 수강생_등록_에러() {
        Session session = new FreeSession("세션", LocalDateTime.of(2024, 10, 28, 0, 0),
                LocalDateTime.of(2024, 11, 28, 0, 0));
        assertThatThrownBy(() -> {
            session.register(JAVAJIGI, Optional.empty());
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
