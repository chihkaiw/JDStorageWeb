package com.danny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

  @RequestMapping("/web")
  public String hello() {
    return "errorMessage";
  }

  @RequestMapping("/hello")
  public String hello(Model model,
      @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
    model.addAttribute("name", name);
    return "hello";
  }

}
