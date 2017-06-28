package org.mundt.passepartout.model;

import java.util.Map;

public class KeyModel {
    private String name;
    private Map<String, String> paths;

    public KeyModel(String name, Map<String, String> paths) {
        this.name = name;
        this.paths = paths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, String> paths) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "KeyModel{" +
                "name='" + name + '\'' +
                ", paths=" + paths +
                '}';
    }
}
