package org.parkhojin.controllers.admins;

import lombok.Data;

@Data
public class BoardSearch {

    private int page = 1;
    private int limit = 20;

}
