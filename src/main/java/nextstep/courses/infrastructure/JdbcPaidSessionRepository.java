package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("paidSessionRepository")
public class JdbcPaidSessionRepository extends JdbcSessionRepository<PaidSession> implements PaidSessionRepository {

    public JdbcPaidSessionRepository(JdbcOperations jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Long saveBaseSession(PaidSession session, Long courseId) {
        Long sessionId = super.saveBaseSession(session, courseId);

        // PaidSession 전용 테이블에 삽입하는 쿼리
        String paidSessionSql = "INSERT INTO paid_session (id, max_capacity, fee) VALUES (?, ?, ?)";
        jdbcTemplate.update(paidSessionSql, sessionId, session.getMaxCapacity(), session.getFee());

        return sessionId;
    }

    @Override
    public PaidSession findById(Long id) {
        String sql = "SELECT s.id, s.title, s.session_start, s.session_end, s.image_size, s.image_width, s.image_height, s.image_type, s.session_status, s.course_id, " +
                "p.max_capacity, p.fee FROM session s JOIN paid_session p ON s.id = p.id WHERE s.id = ?";
        RowMapper<PaidSession> rowMapper = (rs, rowNum) -> new PaidSession(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getDate("session_start").toLocalDate(),
                rs.getDate("session_end").toLocalDate(),
                rs.getString("session_status") != null ? SessionStatus.valueOf(rs.getString("session_status")) : null,
                new Students(rs.getInt("max_capacity")),
                rs.getLong("fee"),
                new SessionImage(
                        rs.getInt("image_size"),
                        rs.getInt("image_width"),
                        rs.getInt("image_height"),
                        rs.getString("image_type")
                )
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
