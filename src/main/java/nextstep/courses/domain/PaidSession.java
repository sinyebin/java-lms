package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class PaidSession extends Session {
    private final Long fee;

    public PaidSession(String title, LocalDateTime startDay, LocalDateTime endDay, int maxCapacity, Long fee) {
        this(title, startDay, endDay, SessionStatus.READY, new Students(maxCapacity), fee);
    }

    public PaidSession(String title, LocalDateTime startDay, LocalDateTime endDay, SessionStatus sessionStatus, int maxCapacity, Long fee) {
        this(title, startDay, endDay, sessionStatus, new Students(maxCapacity), fee);
    }

    public PaidSession(String title, LocalDateTime startDay, LocalDateTime endDay, SessionStatus sessionStatus, Students students, Long fee) {
        this(0L, title, startDay, endDay, sessionStatus, students, fee);
    }

    public PaidSession(Long id, String title, LocalDateTime startDay, LocalDateTime endDay, SessionStatus sessionStatus, Students students, Long fee) {
        super(id, title, startDay, endDay, sessionStatus, students);
        this.fee = fee;
    }

    @Override
    protected boolean requiresPayment() {
        return true;
    }

    @Override
    protected Long getFee() {
        return fee;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
