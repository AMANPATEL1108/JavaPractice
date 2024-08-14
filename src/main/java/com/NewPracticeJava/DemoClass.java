package com.NewPracticeJava;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.Driver;

import static java.lang.Character.getName;

public class DemoClass {
    public static void main(String[] args) throws Exception {
//    Pqr  obj=new Pqr();
//        System.out.println("Hello");
//    Class.forName("com.mysql.jdbc.Driver");

        StudentDAO dao=new StudentDAO();
//        Student s1=dao.getStudent(11);
        Student s2=new Student();
        s2.rollno=15;
        s2.sname="Hasrh";
        dao.connect();
        dao.addStudent(s2);
//        System.out.println(s1.sname);
//        dao.removeStudent(15);

    }
}

class StudentDAO {
    Connection con=null;
                public void connect() {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "mysql");
            }catch (Exception ex){
                System.out.println(ex);            }

  }

    public Student getStudent(int rollno) throws ClassNotFoundException {
        try {
            String query = "select sname from studentone where rollno=" + rollno;
            Student s = new Student();
            s.rollno = rollno;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String name = rs.getString(1);
            s.sname = name;
            return s;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void addStudent(Student s) {
    String query="insert into studentone values(?,?)";
    PreparedStatement pst;
        try {

            pst=con.prepareStatement(query);
            pst.setInt(1,s.rollno);
            pst.setString(2,s.sname);
            pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);        }
    }
}

class  Student{
    int rollno;
    String sname;
}

//class Pqr{
//    static {
//        System.out.println("in static");
//    }
//
//    {
//        System.out.println("in instance");
//    }
//}
