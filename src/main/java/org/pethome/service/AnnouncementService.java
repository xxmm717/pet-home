package org.pethome.service;

import org.pethome.pojo.Announcement;

import java.util.List;

public interface AnnouncementService {
    //获取公告列表信息
    List<Announcement> getList();
}
