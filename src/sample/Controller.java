package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.sql.*;

public class Controller {
    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;


    @FXML
    TextField name;
    @FXML
    TextField mobile;
    @FXML
    TextField address;
    @FXML
    TextField payment;
    @FXML
    ComboBox policySelector;
    @FXML
    TableColumn policyNumber;
    @FXML
    TableColumn customerName;
    @FXML
    TableColumn customerMobile;
    @FXML
    TableColumn customerpolicyType;
    @FXML
    TableColumn customerAddress;
    @FXML
    TableColumn customerPayment;


String insertstmnt= "\"INSERT INTO RECORDS(name,mobile,policy,address,policy_payment) VALUES()\"";
    public void clicked() throws SQLException {
        try {
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance","root","");
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO RECORDS(name,mobile,policy,address,policy_payment) VALUES('"+ name.getText()+ "',"+mobile.getText()+",'"+policySelector.getValue()+"','"+address.getText()+"',"+payment.getText()+")");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(conn.getSchema());


    }
}
