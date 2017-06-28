package org.mundt.passepartout.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.mundt.passepartout.App;
import org.mundt.passepartout.model.ConfigurationModel;
import org.mundt.passepartout.model.LauncherModel;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MainControl extends VBox {
    @FXML
    private TabPane serversContainer;

    @FXML
    private ChoiceBox launcherChoiceBox;

    public MainControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        final ConfigurationModel configuration = App.getConfiguration();

        ObservableList<LauncherModel> launchers = FXCollections.observableList(configuration.getLaunchers());
        launcherChoiceBox.setItems(launchers);

        final List<ServerControl> serverControls = configuration.getServers().stream()
                .map(server -> {
                    final ServerControl serverControl = new ServerControl(this, server);
                    serversContainer.getTabs().add(serverControl);
                    return serverControl;
                })
                .collect(Collectors.toList());

        launcherChoiceBox.setOnAction(actionEvent -> {
            final LauncherModel launcher = (LauncherModel) launcherChoiceBox.getSelectionModel().getSelectedItem();
            serverControls.forEach(serverControl -> serverControl.checkKeyCompatibility(launcher));
        });

        launcherChoiceBox.getSelectionModel().selectFirst();
    }

    public LauncherModel getSelectedLauncher() {
        return (LauncherModel) launcherChoiceBox.getSelectionModel().getSelectedItem();
    }
}
