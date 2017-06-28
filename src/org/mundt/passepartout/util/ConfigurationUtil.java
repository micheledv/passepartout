package org.mundt.passepartout.util;

import com.google.gson.Gson;
import org.mundt.passepartout.model.ConfigurationModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigurationUtil {
    public static ConfigurationModel getFromDefaultConfigurationFile() {
        final Path currentPath = Paths.get(System.getProperty("user.dir"));
        final Path configurationPath = Paths.get(currentPath.toString(), "passepartout.json");
        try {
            final FileReader fileReader = new FileReader(configurationPath.toString());
            return new Gson().fromJson(fileReader, ConfigurationModel.class).indexAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
