package org.mundt.passepartout.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FormEntryControl extends VBox {
    @FXML
    private Label descriptionLabel;

    @FXML
    private TextField paramTextField;

    private String paramName;
    private ServiceControl serviceControl;

    public FormEntryControl(ServiceControl serviceControl, String paramName, String description) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/formEntry.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.serviceControl = serviceControl;
        this.paramName = paramName;

        descriptionLabel.setText(description);
    }

    public void launch() {
        serviceControl.launch();
    }

    public String getParamName() {
        return paramName;
    }

    public String getParamValue() {
        return paramTextField.getText();
    }
}
