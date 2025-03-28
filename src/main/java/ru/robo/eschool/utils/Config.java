package ru.robo.eschool.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

//Creator: Robo_Start
//Creation date & time: 3/28/2025 10:56 PM
public class Config {
    public static Config instance = new Config();

    public static Config getInstance() throws NullPointerException {
        if (instance == null) throw new NullPointerException();
        return instance;
    }

    @JsonProperty("port")
    public int port;

    public Config() {
    }

    public void UpdateConfig() {
        try {
            Config cfg = new Gson().fromJson(new FileReader("config.json"), Config.class);

            this.port = cfg.port;
        } catch (FileNotFoundException e) {
            ResetDefault();
            UpdateConfig();
        }
    }

    public void ResetDefault() {
        String[] webs = {"firstSet", "quickSet", "login"};
        try {
            if (!Files.exists(Path.of("config.json"))) {
                Files.copy(
                        Objects.requireNonNull(Config.class.getResourceAsStream("/default/config.json")),
                        Path.of("config.json"), StandardCopyOption.REPLACE_EXISTING);
            }
            Files.createDirectories(Path.of("web"));
            for (String web : webs) {
                if (!Files.exists(Path.of("web/", web, ".html"))) {
                    Files.copy(
                            Objects.requireNonNull(Config.class.getResourceAsStream("/default/web/" + web + ".html")),
                            Path.of("web/" + web + ".html"), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
