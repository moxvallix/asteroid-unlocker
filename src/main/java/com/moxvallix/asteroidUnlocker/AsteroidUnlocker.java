package com.moxvallix.asteroidUnlocker;

import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Modules;
import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class AsteroidUnlocker extends MeteorAddon {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final String CONFIG_DIR = "config/asteroid-unlocker";
    public static final File FOLDER = FabricLoader.getInstance().getGameDir().resolve(CONFIG_DIR).toFile();

    @Override
    public void onInitialize() {
        // Pre-load
        if (!FOLDER.exists()) {
            FOLDER.getParentFile().mkdirs();
            FOLDER.mkdir();
        }

        configureModules(getConfig());
    }

    @Override
    public String getPackage() {
        return "com.moxvallix.asteroidUnlocker";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("moxvallix", "asteroid-unlocker");
    }

    public Config getConfig() {
        File path = new File(FOLDER.getPath() + "/config.json");
        return new ConfigLoader(path).config;
    }

    public void configureModules(Config config) {
        if (config.bypassCheats) {
            MeteorClient.BYPASS_CHEATS = true;
            return;
        }
        unlockModules(config);
        lockModules(config);
    }

    private void unlockModules(Config config) {
        Modules modules = Modules.get();

        config.unlocks.forEach(name -> {
            Module module = modules.get(name);
            if (module == null) { return; }

            module.isCheat = false;
        });
    }

    private void lockModules(Config config) {
        Modules modules = Modules.get();

        config.locks.forEach(name -> {
            Module module = modules.get(name);
            if (module == null) { return; }

            module.isCheat = true;
        });
    }
}
