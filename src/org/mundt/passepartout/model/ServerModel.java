package org.mundt.passepartout.model;

import java.util.List;

public class ServerModel {
    private String name;
    private String keyName;
    private String user;
    private String host;
    private List<ServiceModel> services;

    public ServerModel(String name, String keyName, String user, String host, List<ServiceModel> services) {
        this.name = name;
        this.keyName = keyName;
        this.user = user;
        this.host = host;
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> service) {
        this.services = service;
    }

    @Override
    public String toString() {
        return "ServerModel{" +
                "name='" + name + '\'' +
                ", keyName='" + keyName + '\'' +
                ", user='" + user + '\'' +
                ", host='" + host + '\'' +
                ", services=" + services +
                '}';
    }
}
