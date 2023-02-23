package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String url       = "jdbc:mysql://localhost:3306/newdb";
            String user      = "developer";
            String password  = "passwordhere";

            conn = DriverManager.getConnection(url, user, password);

            String Drop = "DROP TABLE IF EXISTS newdb.students;";
            ps = conn.prepareStatement(Drop);
            ps.execute();

            String query = "CREATE TABLE IF NOT EXISTS newdb.students( " +
                    "  student_id INT(10) NOT NULL AUTO_INCREMENT, " +
                    "  last_name VARCHAR(30), " +
                    "  first_name VARCHAR(30), " +
                    "CONSTRAINT students_pk PRIMARY KEY (student_id)" +
                    ")";
            ps = conn.prepareStatement(query);
            ps.execute();

            String students = "INSERT INTO `newdb`.`students` (`first_name`, `last_name`) VALUES('giuseppe','albanese')";
            ps = conn.prepareStatement(students);
            ps.execute();

            String students2 = "INSERT INTO `newdb`.`students` (`first_name`, `last_name`) VALUES('giammo','bianchi')";
            ps = conn.prepareStatement(students2);
            ps.execute();

            String students3 = "INSERT INTO `newdb`.`students` (`first_name`, `last_name`) VALUES('marco','merrino')";
            ps = conn.prepareStatement(students3);
            ps.execute();

            String students4 = "INSERT INTO `newdb`.`students` (`first_name`, `last_name`) VALUES('daniele','rossi')";
            ps = conn.prepareStatement(students4);
            ps.execute();

            String query1 = "select first_name ,last_name FROM students";
            rs = ps.executeQuery(query1);
            ArrayList<String> surname = new ArrayList<String>();

            while (rs.next()){
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                System.out.println("name is "+ firstName );
                surname.add(lastName);
            }
            System.out.println("surname: "+ surname);


            String query2 = "ALTER TABLE newdb.students ADD COLUMN country VARCHAR(30) AFTER first_name;";
            ps = conn.prepareStatement(query2);
            ps.execute();

            String italy = "UPDATE `newdb`.`students` SET `country` = 'italy' WHERE (`student_id` = '1');";
            ps = conn.prepareStatement(italy);
            ps.execute();

            String italy1 = "UPDATE `newdb`.`students` SET `country` = 'italy' WHERE (`student_id` = '3');";
            ps = conn.prepareStatement(italy1);
            ps.execute();

            String Germany  = "UPDATE `newdb`.`students` SET `country` = 'Germany' WHERE (`student_id` = '2');";
            ps = conn.prepareStatement(Germany);
            ps.execute();

            String Germany1 = "UPDATE `newdb`.`students` SET `country` = 'Germany' WHERE (`student_id` = '4');";
            ps = conn.prepareStatement(Germany1);
            ps.execute();

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null ){
                    rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if(conn != null) {
                    conn.close();
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}