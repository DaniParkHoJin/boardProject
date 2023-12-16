package org.parkhojin.models.board;

import org.parkhojin.commons.Utils;
import org.parkhojin.commons.exceptions.AlertBackException;
import org.springframework.http.HttpStatus;

public class BoardDataNotFoundException extends AlertBackException {
    public BoardDataNotFoundException() {
        super(Utils.getMessage("NotFound.boardData", "error"));
        setStatus(HttpStatus.NOT_FOUND);
    }
}
