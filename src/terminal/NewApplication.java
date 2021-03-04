/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

/**
 *
 * @author sahan_k
 */
public class NewApplication extends Application {

    Stage window;
    Scene scene1, scene2;
    GridPane root, root2;

    private String vailadeError;
    private String numRegX = "^[0-9]+$";
    private String numStrigRegex = "^[a-zA-Z0-9]+$";

    Label lbPin = new Label("Enter Pin");
    Label lbCard_no = new Label("Enter Card No");
    Label lbTk = new Label("Enter Terminal Key");
    Label lbTmkc1 = new Label("Enter Terminal Master Key C1");
    Label lbTmkc2 = new Label("Enter Terminal Master Key C2");
    Label lbTmkc3 = new Label("Enter Terminal Master Key C3");

    Label lbCleanPinBlock = new Label("Clean Pin Block");
    Label lbEncryptedPinBlock = new Label("Encrypted Pin Block");

    Label lbValCleanPinBlock = new Label("");
    Label lbValEncryptedPinBlock = new Label("");

    PasswordField fldPin = new PasswordField();
    TextField fldCard_no = new TextField();
    TextField fldTk = new TextField();
    TextField fldTmkc1 = new TextField();
    TextField fldTmkc2 = new TextField();
    TextField fldTmkc3 = new TextField();

    Button btnProcess = new Button("Process");
    Button btnReset = new Button("Reset");
    Button btnback = new Button("Back");

    public NewApplication() {
    }

    public static void main(String[] args) {
        launch(NewApplication.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        doTasks();

    }

    public void doTasks() {
        root = new GridPane();
//        root.setGridLinesVisible(true);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

        root2 = new GridPane();
//        root2.setGridLinesVisible(true);
        root2.setAlignment(Pos.CENTER);
        root2.setHgap(10);
        root2.setVgap(10);

        scene1 = new Scene(root, 400, 400);

        root.addRow(0, lbPin, fldPin);
        root.addRow(1, lbCard_no, fldCard_no);
        root.addRow(2, lbTk, fldTk);
        root.addRow(3, lbTmkc1, fldTmkc1);
        root.addRow(4, lbTmkc2, fldTmkc2);
        root.addRow(5, lbTmkc3, fldTmkc3);
        root.addRow(6, new Label(), new Label());
//        root.addRow(7, new Label(), new Label());
        root.addRow(8, btnReset, btnProcess);
        window.setTitle("PIN ENCRYPTION!");
        window.setScene(scene1);
        window.show();

        scene2 = new Scene(root2, 400, 200);

        root2.addRow(0, lbCleanPinBlock, new Label());
        root2.addRow(1, new Label(), lbValCleanPinBlock);
        root2.addRow(2, lbEncryptedPinBlock, new Label());
        root2.addRow(3, new Label(), lbValEncryptedPinBlock);
        root2.addRow(4, btnback);

        btnProcess.setOnAction(new EventHandler<ActionEvent>() {
            Alert a = new Alert(Alert.AlertType.WARNING);

            @Override
            public void handle(ActionEvent event) {
                if (vaildateInuts()) {
                    displayEntered();
                    lbValCleanPinBlock.setText("12341234123412341234123412341234");
                    lbValEncryptedPinBlock.setText("12341234123412341234123412341234");
                    window.setScene(scene2);
                } else {

                    a.setContentText(vailadeError);
                    // show the dialog 
                    a.show();
                    System.out.println("The Error Is  :" + getVailadeError());
                    setVailadeError("");
                }

            }
        });

        btnback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetValues();
                window.setScene(scene1);

            }
        });
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetValues();
            }
        });
    }

    public boolean vaildateInuts() {
        Boolean result = true;
        String pin = fldPin.getText().trim();
        String cardNo = fldCard_no.getText().trim();
        String tk = fldTk.getText().trim();
        String tmkc1 = fldTmkc1.getText().trim();
        String tmkc2 = fldTmkc2.getText().trim();
        String tmkc3 = fldTmkc3.getText().trim();

        if (pin.isEmpty() || pin.equals("")) {
            this.vailadeError = "Pin Value Is empty Enter Pin Value";
            result = false;
        } else if (!(pin.trim().length() == 4 || pin.trim().length() == 6)) {
            this.vailadeError = "Pin Value is 4 or 6 Digit Number";
            result = false;
        } else if (!pin.matches(numRegX)) {
            this.vailadeError = "Pin is Not in Correct Format ";
            result = false;
        } else if (cardNo.isEmpty() || cardNo.equals("")) {
            this.vailadeError = "Card No Value Is empty Enter Card No Value";
            result = false;
        } else if (!(cardNo.trim().length() == 16)) {
            this.vailadeError = "Card No length is  16";
            result = false;
        } else if (!cardNo.matches(numRegX)) {
            this.vailadeError = "CArd No is Not in Correct Format ";
            result = false;
        } else if (tk.isEmpty() || tk.equals("")) {
            this.vailadeError = "Terminal Key Value Is empty Enter Terminal Key Value";
            result = false;
        } else if (!(tk.trim().length() == 32)) {
            this.vailadeError = "Terminal Key lenth is 32";
            result = false;
        } else if (!tk.matches(numStrigRegex)) {
            this.vailadeError = "Terminal Key is Not in Correct Format ";
            result = false;
        } else if (tmkc1.isEmpty() || tmkc1.equals("")) {
            this.vailadeError = "Terminal Master Key 1 Value Is empty Enter Terminal Master Key 1 Value";
            result = false;
        } else if (!(tmkc1.trim().length() == 32)) {
            this.vailadeError = "Terminal  Master Key 1 lenth is  32";
            result = false;
        } else if (!tmkc1.matches(numStrigRegex)) {
            this.vailadeError = "Terminal  Master Key 1  is Not in Correct Format ";
            result = false;
        } else if (tmkc2.isEmpty() || tmkc2.equals("")) {
            this.vailadeError = "Terminal Master Key 2 Value Is empty Enter Terminal Master Key 2 Value";
            result = false;
        } else if (!(tmkc2.trim().length() == 32)) {
            this.vailadeError = "Terminal  Master Key 2 lenth is  32";
            result = false;
        } else if (!tmkc2.matches(numStrigRegex)) {
            this.vailadeError = "Terminal  Master Key 2  is Not in Correct Format ";
            result = false;
        } else if (tmkc3.isEmpty() || tmkc3.equals("")) {
            this.vailadeError = "Terminal Master Key 3 Value Is empty Enter Terminal Master Key 3 Value";
            result = false;
        } else if (!(tmkc3.trim().length() == 32)) {
            this.vailadeError = "Terminal  Master Key 3 lenth is  32";
            result = false;
        } else if (!tmkc3.matches(numStrigRegex)) {
            this.vailadeError = "Terminal  Master Key 3  is Not in Correct Format ";
            result = false;
        }

        return result;
    }

    public void resetValues() {
        fldPin.setText("");
        fldCard_no.setText("");
        fldTk.setText("");
        fldTmkc1.setText("");
        fldTmkc2.setText("");
        fldTmkc3.setText("");
    }

    public void displayEntered() {
        String pin = fldPin.getText();
        String cardNo = fldCard_no.getText();
        String tk = fldTk.getText();
        String tmkc1 = fldTmkc1.getText();
        String tmkc2 = fldTmkc2.getText();
        String tmkc3 = fldTmkc3.getText();
        System.out.println("The PIN!                    \t" + pin);
        System.out.println("The Card No!                \t " + cardNo);
        System.out.println("The Terminal key!           \t" + tk);
        System.out.println("The Terminal Master key c1! \t " + tmkc1);
        System.out.println("The Terminal Master key c2! \t" + tmkc2);
        System.out.println("The Terminal Master key c3! \t " + tmkc3);
    }

    public String getVailadeError() {
        return vailadeError;
    }

    public void setVailadeError(String vailadeError) {
        this.vailadeError = vailadeError;
    }

}
