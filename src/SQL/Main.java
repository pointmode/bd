package SQL;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataBase db = new DataBase();
        db.isConnection();
        db.createTable("tickets");
        db.addTable("tickets");
        db.selectTable("tickets");
        db.deleteTable("tickets");
//      aircrafts, airports, flights, seats, tickets
    }
}