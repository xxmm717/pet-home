package org.pethome.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_message")
public class Message {
    private Long messageId;
    private Long userId;
    private Long announcementId;
    private String message;
    private Date datetime;
}
