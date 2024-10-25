package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("삭제 권한 없음")
    @Test
    void 삭제_권한x() {
        assertThatThrownBy(() -> {
            Q1.deleteQuestion(NsUserTest.SANJIGI, LocalDateTime.now());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 삭제")
    @Test
    void 질문_삭제() throws CannotDeleteException {
        Q1.deleteQuestion(NsUserTest.JAVAJIGI, LocalDateTime.now());
        assertThat(Q1.isDeleted()).isTrue();
    }
}
