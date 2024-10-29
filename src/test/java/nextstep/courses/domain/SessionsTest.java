package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SessionsTest {

    @DisplayName("세션 추가")
    @Test
    void 세션추가() {
        Sessions sessions = new Sessions(0L, Arrays.asList(new PaidSession("세션", LocalDateTime.of(2024, 10, 28, 0, 0),
                LocalDateTime.of(2024, 11, 28, 0, 0), 0, 10000L)));
        sessions.addSession(new FreeSession("세션", LocalDateTime.of(2024, 10, 28, 0, 0),
                LocalDateTime.of(2024, 11, 28, 0, 0)));
        assertThat(sessions).isEqualTo(new Sessions(0L, Arrays.asList(new PaidSession("세션", LocalDateTime.of(2024, 10, 28, 0, 0),
                LocalDateTime.of(2024, 11, 28, 0, 0), 0, 10000L), new FreeSession("세션", LocalDateTime.of(2024, 10, 28, 0, 0),
                LocalDateTime.of(2024, 11, 28, 0, 0)))));
    }

}
