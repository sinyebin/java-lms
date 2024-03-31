package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer){
        this.answers.add(answer);
    }

    public void delete(NsUser nsUser) throws CannotDeleteException {
        for(Answer answer : this.answers){
            answer.delete(nsUser);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answers)) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }

    public List<DeleteHistory> saveDeleteHistory() {
        return this.answers.stream()
                .map(Answer::saveDeleteHistory)
                .collect(Collectors.toList());
    }
}
