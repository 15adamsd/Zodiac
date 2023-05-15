/*
 * AD80StarSigns.java
 * This is a GUI project that takes all inputed
 * birthdays and stores them into an object array
 * printing out the formatted list.
 * 
 * Author: Danielle Adams
 * Last Updated: 11/22/2021
 */

package adams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AD80StarSignsGUI extends Application {
  
  private Label prompt;                      // prompt for both sets of information
  private TextField firstNameTF;             // text field object of first name
  private String firstName;                  // string first name to be used in other classes
  private TextField lastNameTF;              // text field of last name
  private String lastName;                   // string of last name
  private ComboBox<String> monthCB;          // combo box for user's month selection
  private String month;                      // String version of month
  private ComboBox<String> dayCB;            // user day selection combo box
  private String day;                        // String version of day selection
  private Button addButton;                  // adds others' information into zodiac array list (button)
  private Button calcButton;                 // calculates compatibility
  private Button contButton;                 // validates user input
  private GridPane gridPane;                 // the set up of the GUI
  private boolean isValidInput;              // checks and uses input validation
  private TextArea list;                     // holds zodiacSigns array list
  private ArrayList<AD80Zodiac> zodiacSigns; // array list of zodiac objects added by user
  private AD80Zodiac user;                   // zodiac object for user
  private AD80Zodiac other;                  // zodiac object for others
  private String compat;                     // string to store compatibility
  //=====================================================================================================
  public void start (Stage stage) {
    
    // prompts for user's information input process
    zodiacSigns = new ArrayList<>();                  
    prompt = new Label("Enter your information:");
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    prompt.setStyle("-fx-text-fill: #FFFFFF; -fx-font-family: 'monospace';"
        + "-fx-font-weight: 900;");
    firstNameTF = new TextField("First Name");
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    firstNameTF.setStyle("-fx-background-color: #F1D7FE; -fx-font-family: 'monospace';"
        + "-fx-font-weight: 900;");
    lastNameTF = new TextField("Last Name");
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    lastNameTF.setStyle("-fx-background-color: #F1D7FE; -fx-font-family: 'monospace';"
        + "-fx-font-weight: 900;");
    monthCB = new ComboBox<>();
    monthCB.setValue("Month");
    monthCB.getItems().addAll("Month", "January", "February", 
        "March", "April", "May", "June","July", "August", 
        "September", "October", "November", "December");
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    monthCB.setStyle("-fx-background-color: #F1D7FE; -fx-text-fill: white; -fx-font-family: 'monospace';"
        + "-fx-font-weight: 900;");
    dayCB = new ComboBox<>();
    dayCB.setValue("Day");
    dayCB.getItems().addAll("Day", "1", "2", "3", "4", "5", "6", 
        "7", "8", "9", "10", "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20", "21", "22", "23", "24", 
        "25", "26", "27", "28", "29", "30", "31");
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    dayCB.setStyle("-fx-background-color: #F1D7FE; -fx-text-fill: white; -fx-font-family: 'monospace';"
        + "-fx-font-weight: 900;");
    
    // event handler for next step of process after user's information
    // this validates input and then proceeds for others' input
    contButton = new Button("Continue");
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    contButton.setStyle("-fx-background-color: #64CFD9; -fx-font-family: 'monospace';"
        + "-fx-font-weight: 900;");
    contButton.setOnAction(e -> contCalc());

    // addButton that allows user to
    // add as many Zodiac objects as they want
    addButton = new Button("Add");
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    addButton.setStyle("-fx-background-color: #64CFD9; -fx-font-family: 'monospace';"
        + "-fx-font-weight: 900;");
    addButton.setOnAction(e -> addZodiac());
    
    //calculate button for compatibility
    calcButton = new Button("Calculate");
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    calcButton.setStyle("-fx-background-color: #64CFD9; -fx-font-family: 'monospace';"
        + "-fx-font-weight: 900;");
    calcButton.setOnAction(e -> calcCompatList());
    
    // text area for displayed data
    list = new TextArea();
    list.setPrefWidth(600);
    list.setPrefHeight(800);
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    list.setStyle("-fx-font-family: 'monospace';"
        + "-fx-font-weight: 900;");
    
    // image !!!!!!!!!!!!!!!!!!!!!!!!!!!!!IMAGE FOR EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    InputStream stream = null;
    try {
      stream = new FileInputStream("src/neat.jpeg");
    } catch (FileNotFoundException e1) {
      Alert fileNotFound = new Alert(AlertType.ERROR, "There was an error loading the file.");
      fileNotFound.showAndWait();
      stage.close();
    }
    Image image = new Image(stream);
    ImageView imageView = new ImageView();
    imageView.setImage(image);
    imageView.setX(10);
    imageView.setY(10);
    imageView.setFitWidth(200);
    imageView.setPreserveRatio(true);
    
    // set up of grid pane
    // !!!!!!!!!!!!!!!!!!SETUP OF FIRST SCREEN EXTRA CREDIT WORK!!!!!!!!!!!!!!
    gridPane = new GridPane();
    gridPane.setMinSize(550, 100);
    gridPane.setPadding(new Insets(10, 10, 10, 10));
    gridPane.setVgap(5);
    gridPane.setHgap(5);
    gridPane.setAlignment(Pos.CENTER);
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    gridPane.setStyle("-fx-background-color: #BF9BFB;");
    
    gridPane.add(prompt, 0, 0);
    gridPane.add(imageView, 7, 0);
    gridPane.add(firstNameTF, 0, 1);
    gridPane.add(lastNameTF, 0, 2);
    gridPane.add(monthCB, 0, 3);
    gridPane.add(dayCB, 0, 4);
    gridPane.add(contButton, 1, 5);
    
    Scene scene = new Scene(gridPane);
    stage.setScene(scene);
    stage.setTitle("Astro Compatibility Calculator");
    stage.show();  
  } // end start
 //============================================================================================= 
  // method that occurs for contButton to validate user's info
  private boolean isValid() {
    isValidInput = true;
    if (firstNameTF.getText().equals("First Name") ||
        firstNameTF.getText().isEmpty()) {
      
      Alert alertFirstName = new Alert(AlertType.ERROR, "Please enter your first name.");
      alertFirstName.showAndWait();
      firstNameTF.requestFocus();
      firstNameTF.selectAll();
      isValidInput = false;
    }
    else if (lastNameTF.getText().equals("Last Name") ||
        lastNameTF.getText().isEmpty()){
      Alert alertLastName = new Alert(AlertType.ERROR, "Please enter your last name.");
      alertLastName.showAndWait();
      lastNameTF.requestFocus();
      lastNameTF.selectAll();
      isValidInput = false;
    }
    else if (monthCB.getSelectionModel().getSelectedItem().equals("Month")) {
      Alert alertMonth = new Alert(AlertType.ERROR, "Please select month.");
      alertMonth.showAndWait();
      monthCB.requestFocus();
      isValidInput = false;
    }
    else if (dayCB.getSelectionModel().getSelectedItem().equals("Day")) {
      Alert alertDay = new Alert(AlertType.ERROR, "Please select day.");
      alertDay.showAndWait();
      dayCB.requestFocus();
      isValidInput = false;
    }
    else {
      isValidInput = true;   
    }
    return isValidInput;
  } // end isValidUser
  //===========================================================================================================
  // method for setting stage for other objects and creation of user zodiac object
  private void contCalc() {
    isValid();
    if (isValidInput == true) {
      user = new AD80Zodiac();
      firstName = firstNameTF.getText();
      user.setFirstName(firstName);
      lastName = lastNameTF.getText();
      user.setLastName(lastName);
      month = monthCB.getSelectionModel().getSelectedItem();
      user.setMonth(month);
      day = dayCB.getSelectionModel().getSelectedItem();
      user.setDay(day);
      user.calcZodiac(month, day);
      gridPane.getChildren().clear();
      Alert alertUserComf = new Alert(AlertType.CONFIRMATION, "You entered: " + firstName + " " +
          lastName + " | " + month + " " + 
          dayCB.getSelectionModel().getSelectedItem());
      alertUserComf.showAndWait();
      
      // set up of window for other
      prompt.setText("Enter other information: ");
      firstNameTF.setText("First Name");
      lastNameTF.setText("Last Name");
      monthCB.setValue("Month");
      dayCB.setValue("Day");
      list.setEditable(false);
      
      // image !!!!!!!!!!!!!!!!!!!!!!!!!!!!!IMAGE FOR EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      InputStream stream = null;
      try {
        stream = new FileInputStream("src/neat.jpeg");
      } catch (FileNotFoundException e1) {
        Alert fileNotFound = new Alert(AlertType.ERROR, "There was an error loading the file.");
        fileNotFound.showAndWait();
      }
      Image image = new Image(stream);
      ImageView imageView = new ImageView();
      imageView.setImage(image);
      imageView.setX(10);
      imageView.setY(10);
      imageView.setFitWidth(200);
      imageView.setPreserveRatio(true);
      
      // !!!!!!!!!!!!!!!!!SETUP OF SECOND SCREEN EXTRAC CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!
      gridPane.add(prompt, 0, 0);
      gridPane.add(imageView, 2, 0);
      gridPane.add(firstNameTF, 0, 1);
      gridPane.add(lastNameTF, 0, 2);
      gridPane.add(monthCB, 0, 3);
      gridPane.add(dayCB, 0, 4);
      gridPane.add(addButton, 1, 5);
      gridPane.add(calcButton, 2, 5);
      gridPane.add(list, 1, 1, 4, 4);
      firstNameTF.requestFocus();
      firstNameTF.selectAll();
    }
  } // end contCalc
  //=============================================================================================================
  // when addButton is selected this allows user to add multiple objects into a Zodiac Array List
  // that is to be printed when calcButton is selected
  private void addZodiac() {
    isValid();
    if (isValidInput == true) {
      other = new AD80Zodiac();
      firstName = firstNameTF.getText();
      other.setFirstName(firstName);
      lastName = lastNameTF.getText();
      other.setLastName(lastName);
      month = monthCB.getSelectionModel().getSelectedItem();
      other.setMonth(month);
      day = dayCB.getSelectionModel().getSelectedItem();
      other.setDay(day);
      other.calcZodiac(month, day);
      compat = user.calcCompatibility(other);
      other.setCompat(compat);
      zodiacSigns.add(other);
      firstNameTF.setText("First Name");
      lastNameTF.setText("Last Name");
      monthCB.setValue("Month");
      dayCB.setValue("Day");
      list.setEditable(false);
      list.appendText(other.toString());
    }
  } // end addZodiac
 //=============================================================================================================
  // creates a screen w list text area of displayed compatibility
  public void calcCompatList() {

    Button restartButton = new Button("Restart");
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CSS FORMATTING EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    restartButton.setStyle("-fx-background-color: #64CFD9; -fx-font-family: 'monospace';"
        + "-fx-font-weight: 900;");
    restartButton.setOnAction(e -> restart());
    gridPane.getChildren().clear();
    list.setText(user.getFirstName() + " " + user.getLastName() + " | " +
    user.getSignStr() +" ^-^\n" + "--------------------------------------" + "\n" +
        toStringLast(zodiacSigns));
    
    // image !!!!!!!!!!!!!!!!!!!!!!!!!!!!!IMAGE FOR EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    InputStream stream = null;
    try {
      stream = new FileInputStream("src/sec.png");
    } catch (FileNotFoundException e1) {
      Alert fileNotFound = new Alert(AlertType.ERROR, "There was an error loading the file.");
      fileNotFound.showAndWait();
    }
    Image image = new Image(stream);
    ImageView imageView = new ImageView();
    imageView.setImage(image);
    imageView.setX(10);
    imageView.setY(10);
    imageView.setFitWidth(200);
    imageView.setPreserveRatio(true);
    
    // !!!!!!!!!!!!!!!!!SETUP OF THIRD SCREEN EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!
    gridPane.add(imageView, 7, 1);
    gridPane.add(list, 0, 1);
    gridPane.add(restartButton, 3, 7);
  } // end calcCompatList
 //=============================================================================================================
  // allows the user to hit restart and keep redoing the process until they decided to exit. 
  // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!RESTART BUTTON EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  public void restart() {
    gridPane.getChildren().clear();
    zodiacSigns.clear();
    list.setText("");
    prompt.setText("Enter your information:");
    firstNameTF.setText("First Name");
    lastNameTF.setText("Last Name");
    monthCB.setValue("Month");
    dayCB.setValue("Day");
    
    // image !!!!!!!!!!!!!!!!!!!!!!!!!!!!!IMAGE FOR EXTRA CREDIT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    InputStream stream = null;
    try {
      stream = new FileInputStream("src/neat.jpeg");
    } catch (FileNotFoundException e1) {
      Alert fileNotFound = new Alert(AlertType.ERROR, "There was an error loading the file.");
      fileNotFound.showAndWait();
    }
    Image image = new Image(stream);
    ImageView imageView = new ImageView();
    imageView.setImage(image);
    imageView.setX(10);
    imageView.setY(10);
    imageView.setFitWidth(200);
    imageView.setPreserveRatio(true);
    
    gridPane.add(prompt, 0, 0);
    gridPane.add(imageView, 7, 0);
    gridPane.add(firstNameTF, 0, 1);
    gridPane.add(lastNameTF, 0, 2);
    gridPane.add(monthCB, 0, 3);
    gridPane.add(dayCB, 0, 4);
    gridPane.add(contButton, 1, 5); 
  } // end restart
//===============================================================================================================
  // this is what is printed in the text area when calcButton is pressed
  public String toStringLast(ArrayList<AD80Zodiac> zodiacSigns) {
    String str = "";
    for (AD80Zodiac zodiac : zodiacSigns) {
      str += "\n" + zodiac.getFirstName() + " " + zodiac.getLastName() + 
         " | " + zodiac.getSignStr() + "\n" + zodiac.getCompat() + "\n";
    }
    return str;
  } // end toStringLast
  //==============================================================================================================
  public static void main (String[] args) {
    launch(args); // launches program
  } // end main
 
} // end class


