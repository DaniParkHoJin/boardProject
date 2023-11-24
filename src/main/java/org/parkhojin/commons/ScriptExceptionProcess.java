package org.parkhojin.commons;

import jakarta.servlet.http.HttpServletResponse;
import org.parkhojin.commons.exceptions.AlertBackException;
import org.parkhojin.commons.exceptions.AlertException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 예외를 자바스크립트 처리하는 공통 인터페이스
 *
 */
public interface ScriptExceptionProcess {
    @ExceptionHandler(AlertException.class)
    default String scriptHandler(AlertException e, Model model, HttpServletResponse response) {

        response.setStatus(e.getStatus().value());
        String script = String.format("alert('%s');", e.getMessage());
        if (e instanceof AlertBackException) {
            script += "history.back();";
        }

        model.addAttribute("script", script);

        return "common/_execute_script";
    }
}