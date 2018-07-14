package io.github.mylk.secretspawnweb.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String generate(@Valid @ModelAttribute SecretPreferences secretPreferences,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String validationError = String.format("Field \"%s\" %s", bindingResult.getFieldError().getField(),
                bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("error", validationError);
            return "result";
        }

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
