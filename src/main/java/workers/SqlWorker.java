package workers;

import java.sql.*;
import java.util.List;

public class SqlWorker {

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "7787";

    public static boolean check(List<String> arg) {

        boolean isCheck = true;

        String query = "select year, title, author from books where year=" + arg.get(2) + " and author='" + arg.get(1) + "' and title='" + arg.get(0) + "'";
        // открываем подключение к MySQL server
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // получаем объект для запроса
            Statement stmt = connection.createStatement();
            // отправляем запрос
            ResultSet rs = stmt.executeQuery(query);
            // false если данная книга не была найдена
            if (!rs.next()) isCheck = false;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return isCheck;
    }

    public static void write(List<String> arg) {
        String query = "INSERT INTO test.books (title, author, year) \n" +
                " VALUES ('" + arg.get(0) + "', '" + arg.get(1) + "'," + arg.get(2) + ");";
        // открываем подключение к MySQL server
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // получаем объект для запроса
            Statement stmt = connection.createStatement();
            // отправляем запрос
            stmt.executeUpdate(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

}