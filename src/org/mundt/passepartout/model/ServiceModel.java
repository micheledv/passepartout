package org.mundt.passepartout.model;

import java.util.Map;

public class ServiceModel {
    private String actionName;
    private Map<String, String> params;

    public ServiceModel(String action, Map<String, String> params) {
        this.actionName = action;
        this.params = params;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "ServiceModel{" +
                "actionName='" + actionName + '\'' +
                ", params=" + params +
                '}';
    }
}
