package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionDate {
    private final String ERROR_START_DAY_MESSAGE = "종료일이 시작일보다 과거일수 없습니다.";
    private final LocalDateTime sessionStart;
    private final LocalDateTime sessionEnd;

    public SessionDate(LocalDateTime sessionStart, LocalDateTime sessionEnd) {
        if (sessionStart.isAfter(sessionEnd)) {
            throw new IllegalArgumentException(ERROR_START_DAY_MESSAGE);
        }
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
    }
}
