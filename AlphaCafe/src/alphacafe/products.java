package alphacafe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class products extends NetCafe {

    private String cartegory;

    public String getCartegory() {
        return cartegory;
    }

    public void setCartegory(String cartegory) {
        this.cartegory = cartegory;
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

//    add new product
    public void addProduct() {
        try (Connection conn = DBconnection.getConnection()) {

            //insert Strin here
            String query = " insert into product (prodName, cartegory, date, amount, productNo)"
                    + " values (?, ?, ?, ?, ?, ?)";
            // statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, cartegory);
            preparedStmt.setDate(3, date);
            preparedStmt.setString(4, amount);
            preparedStmt.setString(5, number);

            // Execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("product added successfully");
    }
}
