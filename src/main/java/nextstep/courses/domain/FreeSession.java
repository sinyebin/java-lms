package nextstep.courses.domain;

import java.time.LocalDateTime;

public class FreeSession extends Session {
    public FreeSession(String title, LocalDateTime startDay, LocalDateTime endDay) {
        super(title, startDay, endDay);
    }

    public FreeSession(String title, LocalDateTime startDay, LocalDateTime endDay, SessionStatus sessionStatus, Students students) {
        super(title, startDay, endDay, sessionStatus, students);
    }

    public FreeSession(Long id, String title, LocalDateTime startDay, LocalDateTime endDay, SessionStatus sessionStatus, Students students) {
        super(id, title, startDay, endDay, sessionStatus, students);
    }


}
