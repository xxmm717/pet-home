package org.pethome.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_announcement")
public class Announcement {
    private Long announcementId;
    private String title;
    private String content;
    private String picture;
    private Date datetime;
}
