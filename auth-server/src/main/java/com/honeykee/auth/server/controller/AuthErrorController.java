package com.honeykee.auth.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-28 11:34
 * @since JDK 1.8
 */
@Slf4j
@Controller
@SessionAttributes("authorizationRequest")
public class AuthErrorController {

    @RequestMapping("/oauth/error")
    public String error(@RequestParam Map<String, String> parameters){
        String url = parameters.get("redirect_uri");
        log.info("重定向: {}", url);
        return "redirect:" + url + "?error=1";
    }
}
