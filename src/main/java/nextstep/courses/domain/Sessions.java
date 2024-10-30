package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sessions {
    private final List<Session> sessions;
    private final Long courseId;

    public Sessions(Long courseId) {
        this(courseId, new ArrayList<>());
    }

    public Sessions(Long courseId, List<Session> sessions) {
        this.courseId = courseId;
        this.sessions = new ArrayList<>(sessions);
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sessions);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sessions other = (Sessions) obj;
        return Objects.equals(this.sessions, other.sessions);
    }
}
