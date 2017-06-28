package org.mundt.passepartout.util;

import org.apache.commons.text.StrSubstitutor;
import org.mundt.passepartout.model.ActionModel;
import org.mundt.passepartout.model.KeyModel;
import org.mundt.passepartout.model.LauncherModel;
import org.mundt.passepartout.model.ServerModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Runtime.getRuntime;

public class LauncherUtil {
    public static void launch(LauncherModel launcher, ServerModel server, KeyModel key, ActionModel action, Map<String, String> params) {
        params.put("host", server.getHost());
        params.put("user", server.getUser());
        params.put("keyPath", key.getPaths().get(launcher.getKeyFormat()));

        StrSubstitutor sub = new StrSubstitutor(params);

        String actionCommand = sub.replace(action.getCommand());
        params.put("actionCommand", '"' + actionCommand + '"');
        String shellCommand = sub.replace(launcher.getShellCommand());
        params.put("shellCommand", shellCommand);

        final List<String> terminalCommandList = launcher.getTerminalCommandList();
        String[] terminalCommandArray = terminalCommandList.stream()
                .map(sub::replace)
                .collect(Collectors.toList())
                .toArray(new String[terminalCommandList.size()]);

        try {
            final Process process = getRuntime().exec(terminalCommandArray);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
