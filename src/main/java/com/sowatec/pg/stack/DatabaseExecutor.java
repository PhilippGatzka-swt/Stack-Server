package main.java.com.sowatec.pg.stack;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseExecutor {



    public static boolean registerUser(UserDBO userDBO) {
        try {
            PreparedStatement insert = DatabaseConnector.getConnection().prepareStatement("insert into t_user (username, email,password) values (?,?,?)");
            insert.setString(1, userDBO.getUsername());
            insert.setString(2, userDBO.getEmail());
            insert.setString(3, userDBO.getPassword());
            insert.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
