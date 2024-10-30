package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.Objects;

public class FreeSession extends Session {
    private static final String PAYMENT_ERROR_MESSAGE = "무료 세션에서는 결제 금액이 0원이어야 합니다.";

    public FreeSession(String title, LocalDate startDay, LocalDate endDay) {
        super(title, startDay, endDay);
    }

    public FreeSession(String title, LocalDate startDay, LocalDate endDay, SessionStatus sessionStatus, Students students) {
        super(title, startDay, endDay, sessionStatus, students);
    }

    public FreeSession(Long id, String title, LocalDate startDay, LocalDate endDay, SessionStatus sessionStatus, Students students) {
        super(id, title, startDay, endDay, sessionStatus, students);
    }

    @Override
    protected void validateRegistration(NsUser student, Payment payment) {
        if (payment.getAmount() != 0) {
            throw new IllegalArgumentException(PAYMENT_ERROR_MESSAGE);
        }
    }

    @Override
    protected boolean requiresPayment() {
        return false;
    }

    @Override
    protected Long getFee() {
        return 0L;
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
