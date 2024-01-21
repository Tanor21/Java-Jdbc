package org.norcorp;
import java.sql.*;

// Java Database Connectivity theory

/*
    JDBC :
    1 - import the package ----> java.sql.*
    2 - load & register the driver --> com.mysql.jdbc.Driver
    3 - create connection ----> Connection
    4 - create a statement ---> Statement
        a - normal statement
        b - prepared statement
        c - callable statement
    5 - execute the query ---->
    6 - process result    ---->
    7 - close
 */

/*
    insert into developers values (4, "Omar");
    select name from developers where iddevelopers=3;
 */

/*
    Design Pattern
    DAO ----> Data Access Object
 */

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/aliens";
        String uname = "root";
        String pass = "root";
        String queryFetchDevelopers = "select * from developers";
        int iddevelopers = 7;
        String name = "Abubakr";
        //String queryInsertDeveloper = "insert into developers values (" + iddevelopers + ",'" + name +"')";
        String queryInsertDeveloper = "insert into developers values (?,?)";

        Class.forName("com.mysql.cj.jdbc.Driver");  // class forName norcorp
        Connection con = DriverManager.getConnection(url, uname, pass);
        //Statement st = con.createStatement();
        PreparedStatement st = con.prepareStatement(queryInsertDeveloper) ; // PreparedStatement
        st.setInt(1, iddevelopers);
        st.setString(2, name);
        // Fetch
        ResultSet rs = st.executeQuery(queryFetchDevelopers); // DDL(Data Query Langage), DML(Data Query Langage), DQL(Data Query Langage)
        // Insert (Update)
        int count = st.executeUpdate(); // queryInsertDeveloper

        System.out.println(count + " row/s affected");

        String userData = "";

        while (rs.next()) {
            userData = rs.getInt(1) + " : " + rs.getString(2);
            System.out.println(userData);
        }

        st.close();
        con.close();
    }
}