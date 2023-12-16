package org.parkhojin.controllers.main;

import lombok.RequiredArgsConstructor;
import org.parkhojin.commons.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final Utils utils;
    @GetMapping("/")
    public String index(){

        return utils.tpl("main/index");
    }
}
