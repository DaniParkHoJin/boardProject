package org.parkhojin.controllers.members;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.parkhojin.commons.CommonProcess;
import org.parkhojin.commons.Utils;
import org.parkhojin.models.member.MemberSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements CommonProcess {

    private final Utils utils;

    private final MemberSaveService saveService;
    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form, Model model) {
        commonProcess(model, Utils.getMessage("회원가입", "common"));

        return utils.tpl("member/join");
    }
    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors, Model model){
         commonProcess(model, Utils.getMessage("회원가입","common"));
        saveService.join(form,errors);

        if (errors.hasErrors()) { // 실패시
            return utils.tpl("member/join");
        }
        // 회원가입 성공
        return "redirect:/member/login";

    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "redirectURL", required = false) String redirectURL, Model model) {
        commonProcess(model, Utils.getMessage("로그인","common"));

        model.addAttribute("redirectURL", redirectURL);

        return utils.tpl("member/login");
    }


}