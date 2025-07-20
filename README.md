# Asteroid Unlocker

A plugin for [Asteroid](https://github.com/optipack/asteroid) that allows players to configure what modules are classed as cheats.

## Configuration
The configuration file is found at `.minecraft/config/asteroid-unlocker/config.json`.

The default configuration is as follows:
```json
{
  "version": 1,
  "bypassCheats": false,
  "unlocks": [
    "camera-tweaks",
    "freecam",
    "nametags"
  ],
  "locks": []
}
```

### Config Fields
- `version`: Version of the config file. Increments whenever the format changes.
- `bypassCheats`: If set to `true`, Asteroid considers all modules to not be cheats.
- `unlocks`: List of module names to "unlock" (mark as a non-cheat).
- `locks`: List of module names to "lock" (mark as a cheat).
