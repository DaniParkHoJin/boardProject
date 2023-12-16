package org.parkhojin.models.comment;

import org.parkhojin.commons.Utils;
import org.parkhojin.commons.exceptions.AlertBackException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends AlertBackException {
    public CommentNotFoundException() {
        super(Utils.getMessage("NotFound.comment","error"));
        setStatus(HttpStatus.NOT_FOUND);
    }
}
