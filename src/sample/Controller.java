package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;

    @FXML
    Label message;
    @FXML
    ListView listViewID;
    @FXML
    ListView listViewName;
    @FXML
    ListView listViewNumber;
    @FXML
    ListView listViewPolicyType;
    @FXML
    ListView listViewAddress;
    @FXML
    ListView listViewPayment;
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


String insertstmnt= "\"INSERT INTO RECORDS(name,mobile,policy,address,policy_payment) VALUES()\"";
    public void clicked() {
        try {
            stmt.execute("INSERT INTO RECORDS(name,mobile,policy,address,policy_payment) VALUES('"+ name.getText()+ "',"+mobile.getText()+",'"+policySelector.getValue()+"','"+address.getText()+"',"+payment.getText()+")");
        } catch (SQLException throwables) {
            if (message.getText()==""){
                 message.setText("Error: Info Missing");
            }     else{
                   message.setText("");
            }

        }

    }

    public void show() throws SQLException {
        rs= stmt.executeQuery("SELECT * FROM RECORDS");
            
            listViewID.getItems().clear();
            listViewName.getItems().clear();
            listViewNumber.getItems().clear();
            listViewPolicyType.getItems().clear();
            listViewAddress.getItems().clear();
            listViewPayment.getItems().clear();

        while(rs.next()){
            listViewID.getItems().add(rs.getString("policy_no"));
            listViewName.getItems().add(rs.getString("name"));
            listViewNumber.getItems().add(rs.getString("mobile"));
            listViewPolicyType.getItems().add(rs.getString("policy"));
            listViewAddress.getItems().add(rs.getString("address"));
            listViewPayment.getItems().add(rs.getString("policy_payment"));
        }
        System.out.println();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance","root","");
            stmt = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
