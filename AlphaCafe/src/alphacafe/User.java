package alphacafe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User extends NetCafe {

    private String time;
    private String phone;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

//    method to add new user
    public void addUser() {
        try (Connection conn = DBconnection.getConnection()) {

            //insert Strin here
            String query = " insert into user (username, phone, time, amount, date, compNo)"
                    + " values (?, ?, ?, ?, ?, ?)";
            // statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, phone);
            preparedStmt.setString(3, time);
            preparedStmt.setString(4, amount);
            preparedStmt.setDate(5, date);
            preparedStmt.setString(4, number);

            // Execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("User added successfully");
    }
}

