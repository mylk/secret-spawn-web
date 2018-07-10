package io.github.mylk.secretspawnweb.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.mylk.secretspawn.SecretSpawnApplication;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
    public String index(Model model, HttpServletRequest request) {
        if (request.getMethod().equals("POST")) {
            String[] args = new String[]{"-format", "hackish"};
            SecretSpawnApplication.main(args);
        }
        return "index";
    }
}
