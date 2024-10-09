package org.insight;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConnectionProvider {

    private JdbcTemplate connection;

    // Construtor
    public ConnectionProvider() {
        // Carregar variáveis de ambiente do arquivo .env
        Dotenv dotenv = Dotenv.load();

        String host = dotenv.get("DB_HOST");
        String port = dotenv.get("DB_PORT");
        String dbName = dotenv.get("DB_NAME");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");


        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }
}
