package org.pethome.service;

import org.pethome.pojo.Announcement;

import java.util.List;

public interface AnnouncementService {
    //获取公告列表信息
    List<Announcement> getList();
    //根据公告id获取详情信息
    Announcement getDetail(Long id);
}
