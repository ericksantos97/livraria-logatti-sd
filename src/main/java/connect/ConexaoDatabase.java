package connect;

import java.io.Serializable;
import java.sql.*;

public class ConexaoDatabase implements Serializable {

    private static final long serialVersionUID = -6476698366784841470L;

    private Connection connection;
    private Statement statement;

    public ConexaoDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/logatti-sd", "postgres", "12345");
            statement = connection.createStatement();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute(final String sql) {
        try {
            statement.execute(sql);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeWithResponse(final String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
