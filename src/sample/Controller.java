package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Timer;

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


    Button deleteOk;


    TextField deleteText;
    TextField text;



    public void clicked() throws SQLException {


        try {
            stmt.execute("INSERT INTO RECORDS(name,mobile,policy,address,policy_payment) VALUES('"+ name.getText()+ "','"+mobile.getText()+"','"+policySelector.getValue()+"','"+address.getText()+"',"+payment.getText()+")");
            message.setText("Data Inserted");
        } catch (SQLException throwables) {

                 message.setText("Error: Info Missing");


        }
        show();

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

    public void delete() throws IOException {
        Label message= new Label("Enter Customer ID to delete");
        text = new TextField();
        text.setLayoutX(400);
        text.setLayoutY(20);
        message.setLayoutX(50);
        message.setLayoutY(20);
        deleteOk= new Button("OK");

        deleteOk.setOnAction(event -> {
            try {
                stmt.execute("DELETE FROM RECORDS WHERE POLICY_NO="+text.getText()+"");
                show();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        Button cancel=new Button("Cancel");

        deleteOk.setLayoutX(450);
        deleteOk.setLayoutY(100);
        cancel.setLayoutX(500);
        cancel.setLayoutY(100);
        Stage stage=new Stage();
            AnchorPane root = new AnchorPane();
            root.getChildren().add(cancel);
            root.getChildren().addAll(deleteOk,message,text);

            root.setMinSize(200, 200);
            stage.setScene(new Scene(root, 600, 200));



            stage.show();
            stage.setResizable(false);
        cancel.setOnAction(event -> stage.close());


    }

    public void changed(){

        Label message= new Label("Enter Customer ID to Alter");
        Label name= new Label("Enter new name");
        name.setLayoutY(60);
        name.setLayoutX(50);
        Label phone= new Label("Enter new phone Number");
        phone.setLayoutY(100);
        phone.setLayoutX(50);

        TextField t1= new TextField("Name");

        t1.setLayoutX(400);
        t1.setLayoutY(60);

        TextField t2= new TextField("Phone Number");
        t2.setLayoutX(400);
        t2.setLayoutY(100);

        text = new TextField("Enter ID");

        text.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });
        text.setLayoutX(400);
        text.setLayoutY(20);
        message.setLayoutX(50);
        message.setLayoutY(20);
        deleteOk= new Button("OK");

        deleteOk.setOnAction(event -> {
            try {
              stmt.execute("UPDATE RECORDS SET NAME = '"+t1.getText()+"', MOBILE = '"+t2.getText()+"' WHERE POLICY_NO = "+text.getText()+"");
                show();



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        Button cancel=new Button("Cancel");

        deleteOk.setLayoutX(450);
        deleteOk.setLayoutY(150);
        cancel.setLayoutX(500);
        cancel.setLayoutY(150);
        Stage stage=new Stage();
        AnchorPane root = new AnchorPane();
        root.getChildren().add(cancel);
        root.getChildren().addAll(deleteOk,message,text,name,phone,t1,t2);

        root.setMinSize(200, 200);
        stage.setScene(new Scene(root, 600, 200));



        stage.show();
        stage.setResizable(false);
        cancel.setOnAction(event -> stage.close());
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
