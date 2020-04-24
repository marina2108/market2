package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String url = "jdbc:mysql://195.19.44.146:3306/user30?serverTimezone=UTC";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,"user30","user30");
            //System.out.println("Connected...");
            statement = connection.createStatement();


            resultSet = statement.executeQuery("SELECT * FROM Market JOIN Main ON Market.id=Main.market JOIN Good ON Main.good=Good.id JOIN Provider ON " +

                                                 "Main.provider=Provider.id WHERE Market.name='" + name + "'");
            System.out.println("В магазине " + name + " доступно:");
            while (resultSet.next()) {
                System.out.println("    " + resultSet.getInt("quantity") + " " + resultSet.getString("Good.name") + " код: "
                        + resultSet.getLong("code") + " по цене: " + resultSet.getInt("price") +
                        ". Завевезено " + resultSet.getDate("dute") + " из " + resultSet.getString("Provider.name"));
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
