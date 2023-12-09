package org.parkhojin.models.board;

import org.parkhojin.commons.Utils;
import org.parkhojin.commons.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class RequiredPasswordCheckException extends CommonException {
    public RequiredPasswordCheckException() {
        super(Utils.getMessage("Required.guestPw.check", "validation"), HttpStatus.UNAUTHORIZED);
    }
}