package org.mundt.passepartout;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mundt.passepartout.control.MainControl;
import org.mundt.passepartout.model.ConfigurationModel;
import org.mundt.passepartout.util.ConfigurationUtil;

public class App extends Application {
    private static ConfigurationModel configurationModel;

    public static void main(String[] args) {
        configurationModel = ConfigurationUtil.getFromDefaultConfigurationFile();
        launch(args);
    }

    public static ConfigurationModel getConfiguration() {
        return configurationModel;
    }

    public static void setConfigurationModel(ConfigurationModel configurationModel) {
        App.configurationModel = configurationModel;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = new MainControl();
        primaryStage.setTitle("Passepartout");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
