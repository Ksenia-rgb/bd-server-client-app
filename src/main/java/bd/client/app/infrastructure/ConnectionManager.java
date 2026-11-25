package bd.client.app.infrastructure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static final Dotenv dotenv = Dotenv.load();
    private static HikariDataSource dataSource;

    public static void init() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getDbUrl());
        hikariConfig.setUsername(getDbUser());
        hikariConfig.setPassword(getDbPassword());
        hikariConfig.setMaximumPoolSize(getPoolSize());

        dataSource = new HikariDataSource();

        try (Connection connection = dataSource.getConnection()) {
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new IllegalStateException("ConnectionManager was not inializes");
        }
        return dataSource.getConnection();
    }

    public static void shutdownConnection() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    private static String getDbUrl() {
        String res = "jdbc:" + dotenv.get("db.type");
        res += "://" + dotenv.get("db.host");
        res += ":" + dotenv.get("db.port");
        res += "/" + dotenv.get("db.dbname");
        return res;
    }

    private static String getDbUser() {
        return dotenv.get("db.user");
    }

    private static String getDbPassword() {
        return dotenv.get("db.password");
    }

    private static int getPoolSize() {
        return Integer.parseInt(dotenv.get("db.pool.size"));
    }
}
