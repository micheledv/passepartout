package org.mundt.passepartout.model;

import java.util.Map;

public class ActionModel {
    private String name;
    private String description;
    private String command;
    private Map<String, String> params;

    public ActionModel(String name, String description, String command, Map<String, String> params) {
        this.name = name;
        this.description = description;
        this.command = command;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "ActionModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", command='" + command + '\'' +
                ", params=" + params +
                '}';
    }
}
