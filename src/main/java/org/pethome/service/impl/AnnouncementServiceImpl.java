package org.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.pethome.mapper.AnnouncementMapper;
import org.pethome.pojo.Announcement;
import org.pethome.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    //获取公告列表信息
    @Override
    public List<Announcement> getList() {

        LambdaQueryWrapper<Announcement> lqw = new LambdaQueryWrapper<>();

        return announcementMapper.selectList(lqw);
    }
}
