package nextstep.courses.domain;

import java.util.List;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

import static nextstep.courses.domain.SessionState.PREPARING;
import static nextstep.courses.domain.SessionState.RECRUITING;

public class Session extends BaseEntity {
    private Course course;
    private SessionDuration duration;
    private Images coverImages;
    private SessionPayType payType;
    private SessionState state;
    private Long fee;
    private SessionStudent students;

    public static Session defaultOf(Long id, Course course, LocalDate startDate, LocalDate endDate, Image coverImage, SessionPayType sessionPayType, Integer maxStudent, Long sessionFee) {
        return new Session(id, course, new SessionDuration(startDate, endDate), new Images(coverImage), sessionPayType, PREPARING, maxStudent, sessionFee, new SessionStudent(maxStudent));
    }

    public Session(Course course, SessionDuration duration, Image coverImage,
        SessionPayType payType, SessionState state, Integer maxStudent, Long fee,
        SessionStudent students) {
        this(course, duration, new Images(coverImage), payType, state, maxStudent, fee, students);
    }

    public Session(Course course, SessionDuration duration, Images coverImages,
        SessionPayType payType, SessionState state, Integer maxStudent, Long fee,
        SessionStudent students) {
        validatePayType(payType, maxStudent, fee);
        this.course = course;
        this.duration = duration;
        this.coverImages = coverImages;
        this.payType = payType;
        this.state = state;
        this.fee = fee;
        this.students = students;
    }

    public Session(Long id, Course course, SessionDuration duration, Images coverImages,
                   SessionPayType payType, SessionState state, Integer maxStudent, Long fee,
                   SessionStudent students) {
        validatePayType(payType, maxStudent, fee);
        this.id = id;
        this.course = course;
        this.duration = duration;
        this.coverImages = coverImages;
        this.payType = payType;
        this.state = state;
        this.fee = fee;
        this.students = students;
    }

    public void openRegister(){
        state = RECRUITING;
    }

    public void addStudent(NsUser newStudent, Payment payment){
        checkRegisterableState();
        checkSessionCapacity();
        checkAlreadyPaid(payment);
        this.students.add(newStudent);
    }

    public Long getCourseId(){
        return this.course.id;
    }

    public Images getImages(){
        return this.coverImages;
    }

    public LocalDate getStartDate(){
        return this.duration.getStartDate();
    }

    public LocalDate getEndDate(){
        return this.duration.getEndDate();
    }

    public String getPayType(){
        return this.payType.name();
    }

    public String getState(){
        return this.state.name();
    }

    public Long getFee(){
        return this.fee;
    }

    public void setId(Long id){
        this.id = id;
    }

    public List<Long> getStudentsId(){
        return this.students.getIds();
    }

    private void validatePayType(SessionPayType sessionPayType, Integer maxStudent, Long sessionFee) {
        if(sessionPayType.isPaid() && maxStudent < 1){
            throw new IllegalArgumentException("유료 강의는 최대 수강인원 설정이 필요합니다.");
        }

        if(sessionPayType.isPaid() && sessionFee <= 0){
            throw new IllegalArgumentException("유료 강의는 수강료 설정이 필요합니다.");
        }
    }

    private void checkSessionCapacity() {
        if(payType.isPaid() && !students.canAcceptNewStudent()){
            throw new IllegalArgumentException("더이상 신규 학생을 받을 수 없습니다.");
        }
    }

    private void checkRegisterableState() {
        if(!state.isRecruiting() || !duration.isRecruitingNow()){
            throw new IllegalArgumentException("현재 모집 중이 아닙니다.");
        }
    }


    private void checkAlreadyPaid(Payment payment){
        if(payType.isPaid() && !payment.isSame(fee)){
            throw new IllegalArgumentException("수강료가 일치하지 않습니다.");
        }
    }

}
