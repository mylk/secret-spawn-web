package io.github.mylk.secretspawnweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.github.mylk.secretspawn.manager.SettingsManager;
import io.github.mylk.secretspawn.manager.SecretManager;
import io.github.mylk.secretspawn.model.Settings;
import io.github.mylk.secretspawn.model.Secret;

import io.github.mylk.secretspawnweb.model.SecretPreferences;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String generate(@ModelAttribute SecretPreferences secretPreferences, Model model) {
        String[] args = new String[]{
            "-length", secretPreferences.getLength().toString(),
            "-source", secretPreferences.getSource(),
            "-format", secretPreferences.getFormat(),
        };
        SettingsManager settingsManager = new SettingsManager();
        settingsManager.defineOptions();

        Settings settings = new Settings();
        try {
            settings = settingsManager.parse(args);

            SecretManager secretManager = new SecretManager();
            secretManager.setSettings(settings);
            Secret secret = new Secret();
            secret = secretManager.spawn();

            model.addAttribute("secret", secret.getContentTransformed());
            model.addAttribute("title", secret.getTitle());
            model.addAttribute("url", secret.getUrl());
            model.addAttribute("content", secret.getContentPlain());
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }

        return "result";
    }
}
