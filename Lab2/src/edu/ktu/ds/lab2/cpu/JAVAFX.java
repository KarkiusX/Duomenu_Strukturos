package edu.ktu.ds.lab2.cpu;

import edu.ktu.ds.lab2.gui.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;

public class JAVAFX extends Application {
    public static void main(String[] args) {
        JAVAFX.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        MainWindow.createAndShowGui(primaryStage);
    }
}
