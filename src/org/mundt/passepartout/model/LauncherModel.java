package org.mundt.passepartout.model;

import java.util.List;

public class LauncherModel {
    private String name;
    private String keyFormat;
    private List<String> terminalCommandList;
    private String shellCommand;

    public LauncherModel(String name, String keyFormat, List<String> terminalCommandList, String shellCommand) {
        this.name = name;
        this.keyFormat = keyFormat;
        this.terminalCommandList = terminalCommandList;
        this.shellCommand = shellCommand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyFormat() {
        return keyFormat;
    }

    public void setKeyFormat(String keyFormat) {
        this.keyFormat = keyFormat;
    }

    public List<String> getTerminalCommandList() {
        return terminalCommandList;
    }

    public void setTerminalCommandList(List<String> terminalCommandList) {
        this.terminalCommandList = terminalCommandList;
    }

    public String getShellCommand() {
        return shellCommand;
    }

    public void setShellCommand(String shellCommand) {
        this.shellCommand = shellCommand;
    }

    @Override
    public String toString() {
        return name;
    }
}
