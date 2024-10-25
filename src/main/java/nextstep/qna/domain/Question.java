package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

import static nextstep.qna.domain.DeleteHistory.deleteFromQuestion;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
    }

    public Long getId() {
        return id;
    }


    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }


    private void validateDeleteQuestion(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        answers.validateDeleteQuestionAnswers(loginUser);
    }

    public DeleteHistories deleteQuestion(NsUser loginUser, LocalDateTime deleteTime) throws CannotDeleteException {
        validateDeleteQuestion(loginUser);
        DeleteHistories deleteHistories = new DeleteHistories();
        this.deleted = true;

        deleteHistories.addDeleteHistory(deleteFromQuestion(this.id, this.writer, deleteTime));
        DeleteHistories answerDeleteHistories = answers.deleteQuestionAllAnswers(deleteTime);
        deleteHistories.addAll(answerDeleteHistories);
        
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
