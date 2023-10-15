package com.example.lab1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ScrollbarWindow extends Application {


    private final Consumer<Double> submitCallback;
    private final Runnable cancelCallback;

    public ScrollbarWindow(Consumer<Double> submitCallback, Runnable cancelCallback) {
        this.submitCallback = submitCallback;
        this.cancelCallback = cancelCallback;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Scrollbar Window");

        ScrollBar scrollbar = new ScrollBar();
        Label label = new Label();

        Button submitButton = new Button("Submit");

        // Add a listener to the scrollbar's value property
        scrollbar.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText("Scrollbar Value: " + getFormattedScrollbarValue(scrollbar));
        });


        submitButton.setOnAction(e -> {
            System.out.println("Submit button clicked");
            // Add your submit logic here
            label.setText("Scrollbar Value: " + getFormattedScrollbarValue(scrollbar));
            submitCallback.accept(getFormattedScrollbarValue(scrollbar));
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            System.out.println("Cancel button clicked");
            // Add your cancel logic here
            label.setText("");
            cancelCallback.run();
        });

        VBox vbox = new VBox(10, scrollbar, submitButton, cancelButton, label);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    private double getFormattedScrollbarValue(ScrollBar scrollbar) {
        double value = scrollbar.getValue();
        double roundedValue = Math.round(value * 100.0) / 100.0;
        return roundedValue;
    }
}
