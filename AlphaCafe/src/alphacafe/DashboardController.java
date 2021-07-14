package alphacafe;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashboardController implements Initializable {

    @FXML
    private TextField fullname;
    @FXML
    private TextField phoneUser;
    @FXML
    private TextField priceNo;
    @FXML
    private ComboBox<String> timeUser;
    @FXML
    private Button addUser;
    @FXML
    private DatePicker dateUser;
    @FXML
    private TextField compNoUser;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> fullnameColUser;
    @FXML
    private TableColumn<User, String> phoneColUser;
    @FXML
    private TableColumn<User, String> compNoUserCol;
    @FXML
    private TableColumn<User, String> TimeColUser;
    @FXML
    private TableColumn<User, String> priceColUser;
    @FXML
    private TableColumn<User, String> dateColUser;
    @FXML
    private TextField compNameComp;
    @FXML
    private TextField compPriceComp;
    @FXML
    private DatePicker compDateComp;
    @FXML
    private TextField compNoComp;
    @FXML
    private TableColumn<computer, String> compNameColComp;
    @FXML
    private TableColumn<computer, String> compNoColComp;
    @FXML
    private TableColumn<computer, String> compPriceCol;
    @FXML
    private TableColumn<computer, String> compdateCol;
    @FXML
    private TableView<computer> computerTable;
    @FXML
    private TextField productname;
    @FXML
    private TextField productCost;
    @FXML
    private ComboBox<String> productCartegory;
    @FXML
    private DatePicker boughtDate;
    @FXML
    private TextField productNo;
    @FXML
    private TableView<products> productTable;
    @FXML
    private TableColumn<products, String> productNameCol;
    @FXML
    private TableColumn<products, String> productCartegoryCol;
    @FXML
    private TableColumn<products, String> productNoCol;
    @FXML
    private TableColumn<products, String> productPriceCol;
    @FXML
    private TableColumn<products, String> boughtDateCol;
    @FXML
    private TabPane AdminTab;
    @FXML
    private Button signout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            usersTable();
            computersTable();
            productsTable();
        } catch (Exception e) {
        }
        ObservableList<String> time = FXCollections.observableArrayList(
                "15 min",
                "30 min",
                "45 min",
                "1 hour",
                "2 hours",
                "3 hours",
                "4 hours",
                "5 hours"
        );
        timeUser.setItems(time);

        ObservableList<String> product = FXCollections.observableArrayList(
                "Hardware",
                "Stationary",
                "Electronics"
        );
        productCartegory.setItems(product);

    }

//    method to add the new user
    @FXML
    private void addUsersHere(ActionEvent event) {
        try {
            if (fullname.getText().isEmpty() || phoneUser.getText().isEmpty() || compNoUser.getText().isEmpty() || priceNo.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill All fields!!");
                alert.setTitle("Empty Fields");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {
                String name = fullname.getText();
                String phon = phoneUser.getText();
                java.sql.Date date = java.sql.Date.valueOf(dateUser.getValue());
                String tim = timeUser.getValue();
                String cost = priceNo.getText();
                String comp = compNoUser.getText();

                User newUser = new User(name, phon, tim, cost, date, comp);

                if (newUser.AssignComputer(cost, tim)) {

                    System.out.println("low");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Check the Amount!!");
                    alert.setTitle("Amount is Low");
                    alert.setHeaderText(null);
                    alert.showAndWait();

                } else {

                    newUser.addUser();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("User added successfully!!!!");
                    alert.setTitle("Added User");
                    alert.setHeaderText(null);
                    alert.show();

                    fullname.setText("");
                    phoneUser.setText("");
                    dateUser.setValue(null);
                    timeUser.setValue(null);
                    priceNo.setText("");
                    compNoUser.setText("");
                    usersTable();
                }

            }
        } catch (Exception e) {
        }
    }

    //    fetch data for the users
    public ObservableList<User> getUserList() throws SQLException {
        ObservableList<User> userlist = FXCollections.observableArrayList();
        Connection conn = DBconnection.getConnection();
        String query = "Select * from user";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            User user;
            while (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("phone"), rs.getString("time"), rs.getString("amount"), rs.getDate("date"), rs.getString("compNo"));
                userlist.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return userlist;
    }

//    assigning data to users table here
    public void usersTable() throws SQLException {
        ObservableList<User> list = (ObservableList<User>) getUserList();
        fullnameColUser.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        phoneColUser.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        compNoUserCol.setCellValueFactory(new PropertyValueFactory<User, String>("number"));
        TimeColUser.setCellValueFactory(new PropertyValueFactory<User, String>("time"));
        priceColUser.setCellValueFactory(new PropertyValueFactory<User, String>("amount"));
        dateColUser.setCellValueFactory(new PropertyValueFactory<User, String>("date"));

        userTable.setItems(list);
    }

    //    converting date formats
    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
//public variable to catch data
    public String Name;
    public java.sql.Date dates;

//    on click users table here
    @FXML
    private void clickUserHere(MouseEvent event) {
        fullname.setText(userTable.getSelectionModel().getSelectedItem().name);
        phoneUser.setText(userTable.getSelectionModel().getSelectedItem().getPhone());  // the use of accessor or getter here
        compNoUser.setText(userTable.getSelectionModel().getSelectedItem().number);
        priceNo.setText(userTable.getSelectionModel().getSelectedItem().amount);
        timeUser.setValue(userTable.getSelectionModel().getSelectedItem().getTime());
        dates = userTable.getSelectionModel().getSelectedItem().date;

        dateUser.setValue(LOCAL_DATE(dates.toString()));

        Name = userTable.getSelectionModel().getSelectedItem().name;
    }

//    delete the usr here
    @FXML
    private void deleteUserHere(ActionEvent event) {
        try (Connection conn = DBconnection.getConnection()) {

            // delete string
            String query = "DELETE FROM user WHERE username=?";
            // delete statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, Name);

            // Execute the preparedstatement
            preparedStmt.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deleted Successfully!!");
            alert.setTitle("Deleted");
            alert.setHeaderText(null);
            alert.show();

            fullname.setText("");
            phoneUser.setText("");
            timeUser.setValue(null);
            priceNo.setText("");
            compNoUser.setText("");
            usersTable();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("Deleting the User");
    }

//    update User Here
    @FXML
    private void updateUserHere(ActionEvent event) {
        try (Connection conn = DBconnection.getConnection()) {

            // update string over here
            String query = "UPDATE user SET username=?, phone=?, time=?, amount=?, date=?, compNo=? where username=?";

            // statements here
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, fullname.getText());
            preparedStmt.setString(2, phoneUser.getText());
            java.sql.Date date = java.sql.Date.valueOf(dateUser.getValue());
            preparedStmt.setString(3, timeUser.getValue());
            preparedStmt.setString(4, priceNo.getText());

            preparedStmt.setDate(5, date);
            preparedStmt.setString(6, compNoUser.getText());
            preparedStmt.setString(7, Name);

            // Execute the preparedstatement
            preparedStmt.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Updated Successfully!!!!");
            alert.setTitle("Updates");
            alert.setHeaderText(null);
            alert.show();

            fullname.setText("");
            phoneUser.setText("");
            dateUser.setValue(null);
            timeUser.setValue(null);
            priceNo.setText("");
            compNoUser.setText("");

            usersTable();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("Updated user");
    }

//    adding computers here
    @FXML
    private void addComputer(ActionEvent event) {
        try {
            if (compNameComp.getText().isEmpty() || compPriceComp.getText().isEmpty() || compNoComp.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill All fields!!");
                alert.setTitle("Empty Fields");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {
                String compName = compNameComp.getText();
                String compPrice = compPriceComp.getText();
                java.sql.Date date = java.sql.Date.valueOf(compDateComp.getValue());
                String comPNo = compNoComp.getText();

                computer newEvent = new computer(compName, comPNo, compPrice, date);
                newEvent.addComputer();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("computer added successfully!!!!");
                alert.setTitle("Added Computer");
                alert.setHeaderText(null);
                alert.show();

                compNameComp.setText("");
                compPriceComp.setText("");
                compDateComp.setValue(null);
                compNoComp.setText("");
                computersTable();
            }
        } catch (Exception e) {
        }
    }

//    onclick computers table here
    @FXML
    private void clickcomputer(MouseEvent event) {
        compNameComp.setText(computerTable.getSelectionModel().getSelectedItem().name);
        compPriceComp.setText(computerTable.getSelectionModel().getSelectedItem().amount);  // the use of accessor or getter here
        compNoComp.setText(computerTable.getSelectionModel().getSelectedItem().number);
        dates = computerTable.getSelectionModel().getSelectedItem().date;

        compDateComp.setValue(LOCAL_DATE(dates.toString()));

        Name = computerTable.getSelectionModel().getSelectedItem().name;
    }

    //    fetch data for computer
    public ObservableList<computer> getComputerList() throws SQLException {
        ObservableList<computer> complist = FXCollections.observableArrayList();
        Connection conn = DBconnection.getConnection();
        String query = "Select * from computer";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            computer comp;
            while (rs.next()) {
                comp = new computer(rs.getString("name"), rs.getString("comNpNo"), rs.getString("amount"), rs.getDate("date"));
                complist.add(comp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return complist;
    }

//    assigning data to computer table
    public void computersTable() throws SQLException {
        ObservableList<computer> list = (ObservableList<computer>) getComputerList();
        compNameColComp.setCellValueFactory(new PropertyValueFactory<computer, String>("name"));
        compNoColComp.setCellValueFactory(new PropertyValueFactory<computer, String>("number"));
        compPriceCol.setCellValueFactory(new PropertyValueFactory<computer, String>("amount"));
        compdateCol.setCellValueFactory(new PropertyValueFactory<computer, String>("date"));

        computerTable.setItems(list);
    }

//    deleting the computer here
    @FXML
    private void deleteComputer(ActionEvent event) {
        try (Connection conn = DBconnection.getConnection()) {

            //delete string
            String query = "DELETE FROM computer WHERE name=?";
            // delete statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, Name);

            // Execute the preparedstatement
            preparedStmt.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deleted Successfully!!");
            alert.setTitle("Deleted");
            alert.setHeaderText(null);
            alert.show();

            compNameComp.setText("");
            compPriceComp.setText("");
            compDateComp.setValue(null);
            compNoComp.setText("");
            computersTable();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("Deleting the computer");
    }

//    updating computer here
    @FXML
    private void updateComputer(ActionEvent event) {
        try (Connection conn = DBconnection.getConnection()) {

            // update string over here
            String query = "UPDATE computer SET name=?, comNpNo=?, amount=?, date=? where name=?";

            // statements here
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, compNameComp.getText());
            preparedStmt.setString(2, compNoComp.getText());
            java.sql.Date date = java.sql.Date.valueOf(compDateComp.getValue());
            preparedStmt.setString(3, compPriceComp.getText());
            preparedStmt.setDate(4, date);
            preparedStmt.setString(5, Name);

            // Execute the preparedstatement
            preparedStmt.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Updated Successfully!!!!");
            alert.setTitle("Updates");
            alert.setHeaderText(null);
            alert.show();

            compNameComp.setText("");
            compPriceComp.setText("");
            compDateComp.setValue(null);
            compNoComp.setText("");
            computersTable();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("Updated computer");
    }

//    adding products here
    @FXML
    private void addProduct(ActionEvent event) {
        try {
            if (productname.getText().isEmpty() || productNo.getText().isEmpty() || productCost.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill All fields!!");
                alert.setTitle("Empty Fields");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {
                String name = productname.getText();
                String num = productNo.getText();
                java.sql.Date date = java.sql.Date.valueOf(boughtDate.getValue());
                String cart = productCartegory.getValue();
                String cost = productCost.getText();

//                creating the object for products prodName, cartegory, date, amount, productNo
                products newProduct = new products(name, cart, date, cost, num);
                newProduct.addProduct();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Product added successfully!!!!");
                alert.setTitle("Added Products");
                alert.setHeaderText(null);
                alert.show();

                productname.setText("");
                productNo.setText("");
                boughtDate.setValue(null);
                productCartegory.setValue(null);
                productCost.setText("");
                productsTable();
            }
        } catch (Exception e) {
        }
    }

    //    fetch data for products
    public ObservableList<products> getProducts() throws SQLException {
        ObservableList<products> productList = FXCollections.observableArrayList();
        Connection conn = DBconnection.getConnection();
        String query = "Select * from product";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            products product;
            while (rs.next()) {
                product = new products(rs.getString("prodName"), rs.getString("cartegory"), rs.getDate("date"), rs.getString("amount"), rs.getString("productNo"));
                productList.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return productList;
    }

//    assigning data to products table
    public void productsTable() throws SQLException {
        ObservableList<products> list = (ObservableList<products>) getProducts();
        productNameCol.setCellValueFactory(new PropertyValueFactory<products, String>("name"));
        productCartegoryCol.setCellValueFactory(new PropertyValueFactory<products, String>("cartegory"));
        productNoCol.setCellValueFactory(new PropertyValueFactory<products, String>("number"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<products, String>("amount"));
        boughtDateCol.setCellValueFactory(new PropertyValueFactory<products, String>("date"));

        productTable.setItems(list);
    }

//    onclick products table here
    @FXML
    private void clickProducts(MouseEvent event) {

        productname.setText(productTable.getSelectionModel().getSelectedItem().name);
        productNo.setText(productTable.getSelectionModel().getSelectedItem().number);
        productCost.setText(productTable.getSelectionModel().getSelectedItem().amount);
        productCartegory.setValue(productTable.getSelectionModel().getSelectedItem().getCartegory()); //getCartegory accessor
        dates = productTable.getSelectionModel().getSelectedItem().date;

        boughtDate.setValue(LOCAL_DATE(dates.toString()));

        Name = productTable.getSelectionModel().getSelectedItem().name;
    }

//delete products here
    @FXML
    private void deleteProducts(ActionEvent event) {
        try (Connection conn = DBconnection.getConnection()) {

            // delete string
            String query = "DELETE FROM product WHERE prodName=?";
            // delete statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, Name);

            // Execute the preparedstatement
            preparedStmt.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deleted Successfully!!");
            alert.setTitle("Deleted");
            alert.setHeaderText(null);
            alert.show();

            productname.setText("");
            productNo.setText("");
            boughtDate.setValue(null);
            productCartegory.setValue(null);
            productCost.setText("");
            productsTable();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("Deleting the product");
    }

//    updating the product here
    @FXML
    private void updateProducts(ActionEvent event) {
        try (Connection conn = DBconnection.getConnection()) {

            // update string over here
            String query = "UPDATE product SET prodName=?, cartegory=?, date=?, amount=?, productNo=? where prodName=?";

            // statements here
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, productname.getText());
            preparedStmt.setString(2, productCartegory.getValue());
            java.sql.Date date = java.sql.Date.valueOf(boughtDate.getValue());
            preparedStmt.setDate(3, date);
            preparedStmt.setString(4, productCost.getText());
            preparedStmt.setString(5, productNo.getText());

            preparedStmt.setString(6, Name);

            // Execute the preparedstatement
            preparedStmt.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Updated Successfully!!!!");
            alert.setTitle("Updates");
            alert.setHeaderText(null);
            alert.show();

            productname.setText("");
            productNo.setText("");
            boughtDate.setValue(null);
            productCartegory.setValue(null);
            productCost.setText("");
            productsTable();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("Updated Product");
    }

    @FXML
    private void goHome(ActionEvent event) {
        AdminTab.getSelectionModel().select(0);
    }

    @FXML
    private void goUsers(ActionEvent event) {
        AdminTab.getSelectionModel().select(1);

    }

    @FXML
    private void goComputer(ActionEvent event) {
        AdminTab.getSelectionModel().select(2);

    }

    @FXML
    private void goProducts(ActionEvent event) {
        AdminTab.getSelectionModel().select(3);

    }

    @FXML
    private void goLogin(ActionEvent event) {
        try {
            
                FXMLLoader form = new FXMLLoader(getClass().getResource("Login.fxml"));
                Stage stage = (Stage) signout.getScene().getWindow();
                Scene scene = new Scene(form.load());
                stage.setScene(scene);
        } catch (Exception e) {
        }
    }

}
