package org.example;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner sc  =  new Scanner(System.in);
        ResourceBundle rb = ResourceBundle.getBundle("ConnectionDeatials");
        Connection connection;
        Statement statement;
        try {
            connection = DriverManager.getConnection(rb.getString("url"), rb.getString("user"), rb.getString("password"));
//            System.out.println("Done");

            statement=connection.createStatement();
            while(true){
                System.out.println("Press 1 for New Reservation");
                System.out.println("Press 2 for View Reservations");
                System.out.println("Press 3 for Get Room no. ");
                System.out.println("Press 4 for Update Reservation");
                System.out.println("Press 5 for Delete Reservation");
                System.out.println("Press 6 for Exit");
                System.out.println();
                int choice = sc.nextInt();
                switch (choice){
                    case 1:
                        newReservation(statement,sc);
                        break;
                    case 2:
                        checkReservation(statement);
                        break;
                    case 3:
                        getRoom(statement,sc);
                        break;
                    case 4:
                        updateReservation(statement,sc);
                        break;
                    case 5:
                        deleteReservation(statement,sc);
                        break;
                    case 6:
                        exitFromProgram(connection,statement,sc);
                        break;
                    default:
                        System.out.println("Invalid Choice , Try Again");
                }

            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
        }
    }

    private static void deleteReservation(Statement statement, Scanner sc) {
        System.out.println("Enter id for Delete ");
        int id = sc.nextInt();
        String query  = "delete  from  reservations where id  = "+id;
        try{
            statement.executeUpdate(query);
            System.out.println("Delete Sucessfully ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateReservation(Statement statement, Scanner sc) {
        System.out.println("Enter id of guest which you wnt to update ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Guest new  Name :");
        String name = sc.nextLine();
        System.out.println("Room new  Number : ");
        String rn = sc.nextLine();
        System.out.println("Enter new Mobile(10 digits) ");
        String mobile = sc.nextLine();
        String query = "update reservations set name = '" + name +"',room_no = '" +rn+"',mobile_no = '"+mobile +"' where id = "+id;
        try{
            int rowAffected = statement.executeUpdate(query);
            if(rowAffected>0){
                System.out.println("Updation Sucessful");
            }
            else {
                System.out.println("Updation Failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getRoom(Statement statement, Scanner sc) {
        System.out.println("Enter name to get id or room  number ");
        sc.nextLine();
        String s = sc.nextLine();
        String query = "select * from  reservations where name = " + "'"+s+"';";
        try {
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(rs.getString("id")+"\t"+rs.getString("name")+"\t"+rs.getString("room_no")+"\t"+rs.getString("mobile_no")+"\t"+rs.getTimestamp("date").toString());
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception Occured !!");
            System.out.println(e.getMessage());
        }
    }

    private static void checkReservation(Statement statement) {
        String query = "select * from reservations";
        try {
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(rs.getString("id")+"\t"+rs.getString("name")+"\t"+rs.getString("room_no")+"\t"+rs.getString("mobile_no")+"\t"+rs.getTimestamp("date").toString());
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception Occured !!");
            System.out.println(e.getMessage());
        }
    }

    private static void newReservation(Statement statement, Scanner sc) {
        sc.nextLine();
        System.out.println("Enter Guest Name :");
        String name = sc.nextLine();
        System.out.println("Room Number : ");
        String rn = sc.nextLine();
        System.out.println("Enter Mobile(10 digits) ");
        String mobile = sc.nextLine();
        String query = "insert into reservations(name,room_no,mobile_no) values (" +"'"+name +"'"+","+"'"+rn+"'"+","+"'"+mobile+"'"+");";
        try {
            int rowAffected = statement.executeUpdate(query);
            if(rowAffected>0){
                System.out.println(rowAffected+" Row Affected");
                System.out.println("Insertion Sucessfully !!");
            }
            else{
                System.out.println("Insertion Failed");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void exitFromProgram(Connection connection, Statement statement, Scanner sc) {
        try {
            connection.close();
            statement.close();
            sc.close();
            int i=0;
            System.out.print("Thank you");
            while(i<5){
                System.out.print(".");
                Thread.sleep(500);
                i++;
            }
            exit(1);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}