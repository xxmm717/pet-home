package org.pethome.service;

import org.pethome.pojo.Announcement;
import org.pethome.vo.MessageVo;

import java.util.List;

public interface AnnouncementService {
    //获取公告列表信息
    List<Announcement> getList();
    //根据公告id获取详情信息
    Announcement getDetail(Long id);
    //点赞
    void like(Long id);
    //获取公告的评论详情
    List<MessageVo> getMessage(Long id);
    //发表留言
    void leave(Long id, Long userId,String message);
}
