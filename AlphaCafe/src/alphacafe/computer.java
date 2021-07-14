package alphacafe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class computer extends NetCafe {

    private String capacity;

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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

    public void addComputer() {
        try (Connection conn = DBconnection.getConnection()) {

            //insert Strin here
            String query = " insert into computer (name, comNpNo, amount, date)"
                    + " values (?, ?, ?, ?)";
            // statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, number);
            preparedStmt.setString(3, amount);
            preparedStmt.setDate(4, date);

            // Execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("Computer added successfully");
    }
}
