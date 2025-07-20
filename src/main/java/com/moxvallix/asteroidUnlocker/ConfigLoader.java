package com.moxvallix.asteroidUnlocker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

public class ConfigLoader {
    public Config config;

    public ConfigLoader(File configFile) {
        if (configFile.exists()) {
            config = loadConfigFromFile(configFile);
        } else {
            createConfigFile(configFile);
            config = new Config();
        }
    }

    private void createConfigFile(File file) {
        try {
            boolean newFile = file.createNewFile();

            if (newFile) {
                Config config = new Config();
                String json = AsteroidUnlocker.GSON.toJson(config);

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(json);
                bw.close(); // Be sure to close BufferedWriter
            }

        } catch (IOException e) {
            return;
        }
    }

    private Config loadConfigFromFile(File file) {
        try {
            String json = Files.readString(file.toPath());
            return AsteroidUnlocker.GSON.fromJson(json, Config.class);
        } catch (IOException e) {
            return new Config();
        }
    }
}

class Config {
    public int version = 1;
    public boolean bypassCheats = false;
    public Set<String> unlocks = Set.of("camera-tweaks", "freecam", "nametags");
    public Set<String> locks = Set.of();
}
