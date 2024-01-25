package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChecksRepository extends BaseRepository {

    private static List<UrlCheck> entities = new ArrayList<>();

    public static void save(UrlCheck urlCheck) throws SQLException {
        var sql = "INSERT INTO url_checks(title, created_at, h1, description, status_code, url_id) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, urlCheck.getTitle());
            stmt.setTimestamp(2, urlCheck.getCreatedAt());
            stmt.setString(3, urlCheck.getH1());
            stmt.setString(4, urlCheck.getDescription());
            stmt.setInt(5, urlCheck.getStatusCode());
            stmt.setLong(6, urlCheck.getUrlId());
            stmt.executeUpdate();
            var generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned ID after saving an entity");
            }
            urlCheck.setId((long) entities.size() + 1);
            entities.add(urlCheck);
        }

    }

    public static List<UrlCheck> getEntitiesById(Long keyId) throws SQLException {
        List<UrlCheck> result = new ArrayList<>();
        var sql = "SELECT * FROM url_checks WHERE url_id = ?";
        try (var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, keyId);
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var statusCode = resultSet.getInt("status_code");
                var createdAt = resultSet.getTimestamp("created_at");
                var urlId = resultSet.getLong("url_id");
                var urlCheck = new UrlCheck(createdAt, title, h1, description, statusCode, urlId);
                urlCheck.setId(id);
                result.add(urlCheck);
            }
        }
        return result;
    }


}
