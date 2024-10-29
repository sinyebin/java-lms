package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public abstract class Session {
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

    public void register(NsUser student, Optional<Payment> payment) {
        if (this.sessionStatus != SessionStatus.OPEN) {
            throw new IllegalArgumentException(CAN_NOT_REGISTER_SESSION);
        }

        if (requiresPayment()) {
            Payment actualPayment = payment.orElseThrow(() -> new IllegalArgumentException("유료 세션에서는 결제가 필요합니다."));
            actualPayment.validateForSessionAndUser(id, student.getId(), getFee());
        }
        students.addStudent(student);
    }

    protected abstract boolean requiresPayment();

    protected Long getFee() {
        return null;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, sessionDate, sessionStatus);
    }
}
