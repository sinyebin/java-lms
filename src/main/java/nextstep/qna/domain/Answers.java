package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void validateDeleteQuestionAnswers(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateDeleteQuestionAnswer(loginUser);
        }
    }

    public DeleteHistories deleteQuestionAllAnswers(LocalDateTime deleteTime) {
        DeleteHistories deleteHistories = new DeleteHistories();
        for (Answer answer : answers) {
            deleteHistories.addDeleteHistory(answer.deleteAnswer(deleteTime));
        }
        return deleteHistories;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(answers);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Answers answers1 = (Answers) obj;
        return Objects.equals(answers, answers1.answers);
    }
}
