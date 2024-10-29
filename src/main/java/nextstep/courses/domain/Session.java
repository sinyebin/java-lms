package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private final Long id;
    private final String title;
    private final SessionDate sessionDate;
    private SessionStatus sessionStatus;

    public Session(String title, LocalDateTime startDay, LocalDateTime endDay) {
        this(0L, title, startDay, endDay);
    }

    public Session(Long id, String title, LocalDateTime startDay, LocalDateTime endDay) {
        this.id = id;
        this.title = title;
        this.sessionDate = new SessionDate(startDay, endDay);
        this.sessionStatus = SessionStatus.OPEN;
    }

    public void readySession() {
        changeSessionStatus(SessionStatus.READY);
    }

    public void openSession() {
        changeSessionStatus(SessionStatus.OPEN);
    }

    public void closeSession() {
        changeSessionStatus(SessionStatus.CLOSED);
    }

    private void changeSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public SessionStatus getSessionStatus() {
        return this.sessionStatus;
    }

    
}
