package application.controller;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DbConnection {



    public Connection Connect() {
        try {

            String url = "jdbc:mysql://localhost/gama_hospital?useSSL=false";
            String user = "root";
            String password = "JuventusStadium1996";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            return conn;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
		return null;


    }


}
