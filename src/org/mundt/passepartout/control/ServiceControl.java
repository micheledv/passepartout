package org.mundt.passepartout.control;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import org.mundt.passepartout.App;
import org.mundt.passepartout.model.*;
import org.mundt.passepartout.util.LauncherUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class ServiceControl extends TitledPane {
    private final List<FormEntryControl> formEntryControls;
    private final ServerControl serverControl;
    private final ServiceModel service;
    private final ActionModel action;
    @FXML
    private VBox mainContainer;
    @FXML
    private VBox formEntriesContainer;

    public ServiceControl(ServerControl serverControl, ServiceModel service) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/service.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.serverControl = serverControl;
        this.service = service;

        final ConfigurationModel configuration = App.getConfiguration();

        final String actionName = service.getActionName();
        action = configuration.getActionByName(actionName);

        this.setText(action.getDescription());

        final ObservableList<Node> formEntriesControls = formEntriesContainer.getChildren();

        formEntryControls = buildUserParamsControls();
        formEntriesControls.addAll(formEntryControls);

        if (formEntriesControls.isEmpty()) {
            formEntriesContainer.getChildren().add(0, new Label("Nothing to configure for this service."));
        }
    }

    @FXML
    public void launch() {
        final ConfigurationModel configuration = App.getConfiguration();

        final ServerModel server = serverControl.getServer();

        final String keyName = server.getKeyName();
        final KeyModel key = configuration.getKeyByName(keyName);

        final LauncherModel launcher = serverControl.getMainControl().getSelectedLauncher();

        Map<String, String> params = getCompleteActionParams();

        LauncherUtil.launch(launcher, server, key, action, params);
    }

    private Map<String, String> getCompleteActionParams() {
        final Map<String, String> completeActionParams = new HashMap<>();
        final Map<String, String> userParams = formEntryControls.stream()
                .collect(Collectors.toMap(
                        FormEntryControl::getParamName,
                        FormEntryControl::getParamValue
                ));
        completeActionParams.putAll(service.getParams());
        completeActionParams.putAll(userParams);
        return completeActionParams;
    }

    private List<FormEntryControl> buildUserParamsControls() {
        final Set<String> userParamsKeySet = action.getParams().keySet();
        final Set<String> serviceParamsKeySet = service.getParams().keySet();
        userParamsKeySet.removeAll(serviceParamsKeySet);
        return action.getParams().entrySet().stream()
                .filter(e -> !serviceParamsKeySet.contains(e.getKey()))
                .map(e -> new FormEntryControl(this, e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
