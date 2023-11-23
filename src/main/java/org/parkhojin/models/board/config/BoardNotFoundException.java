package org.parkhojin.models.board.config;

import org.parkhojin.commons.Utils;
import org.parkhojin.commons.exceptions.AlertException;
import org.springframework.http.HttpStatus;

public class BoardNotFoundException extends AlertException {

    public BoardNotFoundException() {
        super(Utils.getMessage("NotFound.board", "error"));
        setStatus(HttpStatus.NOT_FOUND);
    }
}
