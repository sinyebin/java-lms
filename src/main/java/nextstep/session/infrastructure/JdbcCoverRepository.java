package nextstep.session.infrastructure;

import nextstep.session.domain.Cover;
import nextstep.session.domain.CoverRepository;
import nextstep.session.domain.ImageFilePath;
import nextstep.session.domain.Resolution;
import nextstep.session.dto.CoverVO;
import nextstep.utils.DbTimestampUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository("coverRepository")
public class JdbcCoverRepository implements CoverRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcCoverRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Cover cover) {
        String coverInsertQeury = "insert into cover (session_id, width, height, file_path, file_name, file_extension, byte_size, writer_id, deleted, created_at, last_modified_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        CoverVO coverVO = cover.toVO();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(coverInsertQeury, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, coverVO.getSessionId());
            ps.setInt(2, coverVO.getWidth());
            ps.setInt(3, coverVO.getHeight());
            ps.setString(4, coverVO.getFilePath());
            ps.setString(5, coverVO.getFileName());
            ps.setString(6, coverVO.getFileExtension());
            ps.setLong(7, coverVO.getByteSize());
            ps.setString(8, coverVO.getWriterId());
            ps.setBoolean(9, coverVO.isDeleted());
            ps.setTimestamp(10, DbTimestampUtils.toTimestamp(coverVO.getCreatedAt()));
            ps.setTimestamp(11, DbTimestampUtils.toTimestamp(coverVO.getLastModifiedAt()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Cover findById(long coverID) {
        String coverFindByIdQuery =
                "select id as id, " +
                        "session_id as sessionId, " +
                        "width as width, " +
                        "height as height, " +
                        "file_path as filePath, " +
                        "file_name as fileName, " +
                        "file_extension as fileExtension, " +
                        "byte_size as byteSize, " +
                        "writer_id as writerId, " +
                        "deleted as deleted, " +
                        "created_at as createdAt, " +
                        "last_modified_at as lastModifiedAt " +
                        "from cover " +
                        "where id = ?";

        return getCover(coverID, coverFindByIdQuery);
    }

    @Override
    public Cover findBySessionId(long sessionId) {
        String coverFindBySessionIdQuery =
                "select id as id, " +
                        "session_id as sessionId, " +
                        "width as width, " +
                        "height as height, " +
                        "file_path as filePath, " +
                        "file_name as fileName, " +
                        "file_extension as fileExtension, " +
                        "byte_size as byteSize, " +
                        "writer_id as writerId, " +
                        "deleted as deleted, " +
                        "created_at as createdAt, " +
                        "last_modified_at as lastModifiedAt " +
                        "from cover " +
                        "where session_id = ?";

        return getCover(sessionId, coverFindBySessionIdQuery);
    }

    private Cover getCover(long sessionId, String coverFindBySessionIdQuery) {
        RowMapper<Cover> rowMapper = (rs, rowNum) -> new Cover(
                rs.getLong("id"),
                rs.getLong("sessionId"),
                new Resolution(rs.getInt("width"), rs.getInt("height")),
                new ImageFilePath(rs.getString("filePath"), rs.getString("fileName"), rs.getString("fileExtension")),
                rs.getLong("byteSize"),
                rs.getString("writerId"),
                rs.getBoolean("deleted"),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("createdAt")),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("lastModifiedAt")));
        return jdbcTemplate.queryForObject(coverFindBySessionIdQuery, rowMapper, sessionId);
    }

    @Override
    public int updateDeleteStatus(long coverId, boolean deleteStatus) {
        String updateByCoverIdQuery = "UPDATE cover SET deleted = ? WHERE id = ?";
        return jdbcTemplate.update(updateByCoverIdQuery, deleteStatus, coverId);
    }
}
