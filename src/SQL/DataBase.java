package SQL;

import java.sql.*;

public class DataBase {
    private final String host = "localhost";
    private final String port = "5432";
    private final String dbName = "postgres";
    private final String login = "postgres";
    private final String password = "01304";

    private Connection dbCon; // подключение к бд

    private Connection getDBConnection() {
        String str = "jdbc:postgresql://" + host + ":"
                + port + "/" + dbName;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Соединение установлено");
        } catch (ClassNotFoundException e) {
            System.out.println("Соединение не установлено");
        }
        try {
            dbCon = DriverManager.getConnection(str, login, password);
        } catch (SQLException e) {
            System.out.println("Неверный путь (логин и пароль)");
        }
        return dbCon;
    }

    public void isConnection() throws SQLException, ClassNotFoundException {
        dbCon = getDBConnection();
        System.out.println(dbCon.isValid(1000));
    }

    public void createTable(String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (id INT PRIMARY KEY, name VARCHAR(50), password VARCHAR(15))";

        try {
            Statement statement = getDBConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица создалась");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTable(String table) {
        try {
            Statement statement = getDBConnection().createStatement();
            int rows = statement.executeUpdate("INSERT INTO "
                    + table + "(id, name, password) " +
                    "VALUES (5, 'contact_data', 123);");
            System.out.printf("Добавлено %d строк ", rows);
        } catch (SQLException e) {
            System.out.println("Не удалось добавить");
        }
    }

    public void selectTable(String tableName) throws SQLException {
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from " + tableName);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            System.out.printf("%d. %s. %s. \n", id, name, password);
        }
    }

    public void deleteTable(String tableName) throws SQLException {
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("delete from" + tableName + "where id = 1");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            System.out.printf("%d. %s. %s. \n", id, name, password); }
    }
}