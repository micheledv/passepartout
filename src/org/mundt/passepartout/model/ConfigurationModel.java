package org.mundt.passepartout.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigurationModel {
    private List<KeyModel> keys;
    private List<LauncherModel> launchers;
    private List<ActionModel> actions;
    private List<ServerModel> servers;

    private Map<String, ActionModel> actionsMap;
    private Map<String, KeyModel> keysMap;

    public ConfigurationModel(List<KeyModel> keys, List<LauncherModel> launchers, List<ActionModel> actions, List<ServerModel> servers) {
        setKeys(keys);
        this.launchers = launchers;
        setActions(actions);
        this.servers = servers;
    }

    public List<KeyModel> getKeys() {
        return keys;
    }

    private void setKeys(List<KeyModel> keys) {
        this.keys = keys;
        indexKeys();
    }

    public KeyModel getKeyByName(String name) {
        return keysMap.get(name);
    }

    public List<LauncherModel> getLaunchers() {
        return launchers;
    }

    public void setLaunchers(List<LauncherModel> launchers) {
        this.launchers = launchers;
    }

    public List<ActionModel> getActions() {
        return actions;
    }

    private void setActions(List<ActionModel> actions) {
        this.actions = actions;
        indexActions();
    }

    public ActionModel getActionByName(String name) {
        return actionsMap.get(name);
    }

    public List<ServerModel> getServers() {
        return servers;
    }

    public void setServers(List<ServerModel> servers) {
        this.servers = servers;
    }

    public ConfigurationModel indexAll() {
        indexActions();
        indexKeys();
        return this;
    }

    private void indexActions() {
        actionsMap = actions.stream()
                .collect(Collectors.groupingBy(ActionModel::getName))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().get(0)
                ));
    }

    private void indexKeys() {
        keysMap = keys.stream()
                .collect(Collectors.groupingBy(KeyModel::getName))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().get(0)
                ));
    }

    @Override
    public String toString() {
        return "ConfigurationModel{" +
                "keys=" + keys +
                ", launchers=" + launchers +
                ", actions=" + actions +
                ", servers=" + servers +
                '}';
    }
}
