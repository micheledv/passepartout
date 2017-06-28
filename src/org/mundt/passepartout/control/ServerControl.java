package org.mundt.passepartout.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Tab;
import org.mundt.passepartout.App;
import org.mundt.passepartout.model.ConfigurationModel;
import org.mundt.passepartout.model.KeyModel;
import org.mundt.passepartout.model.LauncherModel;
import org.mundt.passepartout.model.ServerModel;

import java.io.IOException;

class ServerControl extends Tab {
    private final ServerModel server;
    private final MainControl mainControl;

    @FXML
    private Accordion servicesContainer;

    public ServerControl(MainControl mainControl, ServerModel server) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/server.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainControl = mainControl;
        this.server = server;

        final ConfigurationModel configuration = App.getConfiguration();

        this.server.getServices().forEach(service -> {
            servicesContainer.getPanes().add(new ServiceControl(this, service));
        });

        this.setText(this.server.getName());
    }

    public ServerModel getServer() {
        return server;
    }

    public MainControl getMainControl() {
        return mainControl;
    }

    public void checkKeyCompatibility(LauncherModel launcher) {
        KeyModel serverKey = App.getConfiguration().getKeyByName(server.getKeyName());
        if (serverKey.getPaths().containsKey(launcher.getKeyFormat())) {
            setDisable(false);
        } else {
            setDisable(true);
        }
    }
}
