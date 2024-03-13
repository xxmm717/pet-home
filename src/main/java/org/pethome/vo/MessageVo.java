package org.pethome.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MessageVo {
    private String message;
    private String username;
    private String avatar;
    private Date datetime;
}
