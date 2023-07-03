package utils;

public class ConfigurationExporter {
    private static ConfigurationExporter instance = null;

    private ConfigurationExporter() {
    }

    public static ConfigurationExporter getInstance() {
        if (instance == null) {
            instance = new ConfigurationExporter();
        }

        return instance;
    }

    public void exportConfiguration() {

    }
}