package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private final String CAN_NOT_REGISTER_SESSION = "수강신청 모집중이 아닙니다.";
    private final Long id;
    private final String title;
    private final SessionDate sessionDate;
    private final Students students;
    private SessionStatus sessionStatus;

    public Session(String title, LocalDateTime startDay, LocalDateTime endDay) {
        this(0L, title, startDay, endDay, SessionStatus.READY, new Students());
    }

    public Session(String title, LocalDateTime startDay, LocalDateTime endDay, SessionStatus sessionStatus, Students students) {
        this(0L, title, startDay, endDay, sessionStatus, students);
    }

    public Session(Long id, String title, LocalDateTime startDay, LocalDateTime endDay, SessionStatus sessionStatus, Students students) {
        this.id = id;
        this.title = title;
        this.sessionDate = new SessionDate(startDay, endDay);
        this.sessionStatus = sessionStatus;
        this.students = students;
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

    public void register(NsUser student) {
        if (this.sessionStatus != SessionStatus.OPEN) {
            throw new IllegalArgumentException(CAN_NOT_REGISTER_SESSION);
        }
        students.addStudent(student);
    }
}
