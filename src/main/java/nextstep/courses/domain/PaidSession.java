package nextstep.courses.domain;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    public PaidSession(String title, LocalDateTime startDay, LocalDateTime endDay, int maxCapacity) {
        super(title, startDay, endDay, SessionStatus.READY, new Students(maxCapacity));
    }

    public PaidSession(String title, LocalDateTime startDay, LocalDateTime endDay, SessionStatus sessionStatus, int maxCapacity) {
        super(title, startDay, endDay, sessionStatus, new Students(maxCapacity));
    }

    public PaidSession(String title, LocalDateTime startDay, LocalDateTime endDay, SessionStatus sessionStatus, Students students) {
        super(title, startDay, endDay, sessionStatus, students);
    }

    public PaidSession(Long id, String title, LocalDateTime startDay, LocalDateTime endDay, SessionStatus sessionStatus, Students students) {
        super(id, title, startDay, endDay, sessionStatus, students);
    }

}
