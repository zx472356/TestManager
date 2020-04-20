package com.zz.qtshow.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QtzsController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
