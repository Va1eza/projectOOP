package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GUI extends Application {

 private String[] options = {"Option 1", "Option 2", "Option 3"};
 private int currentIndex = 0;
 private Label resultLabel;
 private String firstName;
 private String lastName;
 private boolean voted = false;
 private Set<String> votedOptions = new HashSet<>();
 @Override
 public void start(Stage primaryStage) {
     primaryStage.setTitle("Authorization");

     
     TextField firstNameField = new TextField();
     firstNameField.setPromptText("Enter first name");
     TextField lastNameField = new TextField();
     lastNameField.setPromptText("Enter last name");

     
     Button loginButton = new Button("Login");
     loginButton.setOnAction(e -> {
         firstName = firstNameField.getText().trim();
         lastName = lastNameField.getText().trim();
         if (!firstName.isEmpty() && !lastName.isEmpty()) {
             FileWriterHelper.saveUser(firstName, lastName);
             showMainApp(primaryStage);
         } else {
             showAlert("Error", "Enter first name and last name", Alert.AlertType.ERROR);
         }
     });

     VBox loginLayout = new VBox(10);
     loginLayout.setPadding(new Insets(10));
     loginLayout.getChildren().addAll(new Label("Enter first name and last name"), firstNameField, lastNameField, loginButton);
     Scene loginScene = new Scene(loginLayout, 300, 150);
     primaryStage.setScene(loginScene);
     primaryStage.show();
 }

 private void showMainApp(Stage primaryStage) {
     primaryStage.setTitle("Choice Statistics");
     Label optionLabel = new Label(options[currentIndex]);
     Button voteButton = new Button("Vote");
     voteButton.setOnAction(e -> {
         if (!votedOptions.contains(options[currentIndex])) {
             FileWriterHelper.saveResult(firstName, lastName, options[currentIndex]);
             votedOptions.add(options[currentIndex]);
             voted = true;
         }
     });

     Button nextButton = new Button("Next Option");
     nextButton.setOnAction(e -> {
         currentIndex = (currentIndex + 1) % options.length;
         optionLabel.setText(options[currentIndex]);
         voted = false; 
     });

     Button denyButton = new Button("Deny");
     denyButton.setOnAction(e -> {
         FileWriterHelper.removeUserChoice(firstName, lastName, options[currentIndex]);
         voted = false;
         votedOptions.remove(options[currentIndex]);
     });


     Button exitButton = new Button("Exit");
     exitButton.setOnAction(e -> primaryStage.close());

     Button showResultButton = new Button("Show Result");
     showResultButton.setOnAction(e -> {
         Map<String, Integer> optionCounts = FileReaderHelper.readResults(firstName, lastName, options);
         updateResultLabel(optionCounts);
     });

     Button showAllResultsButton = new Button("Show All Results");
     showAllResultsButton.setOnAction(e -> {
         Map<String, Integer> optionCounts = FileReaderHelper.readAllResults(options);
         updateResultLabel(optionCounts);
     });

     resultLabel = new Label();

     VBox layout = new VBox(10);
     layout.setPadding(new Insets(10));
     layout.getChildren().addAll(optionLabel, voteButton, nextButton, denyButton, showResultButton, showAllResultsButton, resultLabel, exitButton);
     Scene scene = new Scene(layout, 300, 200);
     primaryStage.setScene(scene);
     primaryStage.show();
 }

 private void updateResultLabel(Map<String, Integer> optionCounts) {
     StringBuilder result = new StringBuilder();
     for (String option : options) {
         int count = optionCounts.getOrDefault(option, 0);
         result.append(option).append(": ").append(count).append("\n");
     }
     resultLabel.setText(result.toString());
 }

 private void showAlert(String title, String message, Alert.AlertType alertType) {
     Alert alert = new Alert(alertType);
     alert.setTitle(title);
     alert.setHeaderText(null);
     alert.setContentText(message);
     alert.showAndWait();
 }

 public static void main(String[] args) {
     launch(args);
 }
}




