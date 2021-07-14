
package alphacafe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

//    method to add the new user
    @FXML
    private void addUsersHere(ActionEvent event) {
         try {
            if (fullname.getText().isEmpty() || phoneUser.getText().isEmpty() || priceNo.getText().isEmpty()  || timeUser.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill All fields!!");
                alert.setTitle("Empty Fields");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {
                String full = fullname.getText();
                String price = priceNo.getText();
                String comp = compNoUser.getText();
                java.sq.Date date  = java.sql.Date.valueOf(dateUser.getvalue());
                String cartegory = timeUser.getValue();

                user newStadiumVenu = new user(fullname, location, Seat, cost, capacity, cartegory, account);
                newStadiumVenu.stadiumVenueRegister();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Satdium/Venue Registered Successfully!!");
                alert.setTitle("Registered Stadium/Venue");
                alert.setHeaderText(null);
                alert.show();

                stadiumFullname.setText("");
                stadiumLocation.setText("");
                stadiumSeats.setText("");
                stadiumPrice.setText("");
                stadiumAccount.setText("");
                stadiumCartegory.setValue(null);
                stadiumCapacity.setText("");
                stadiumTable();
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void deleteUserHere(ActionEvent event) {
    }

    @FXML
    private void updateUserHere(ActionEvent event) {
    }
   
}
