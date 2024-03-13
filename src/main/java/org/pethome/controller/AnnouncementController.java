package org.pethome.controller;

import org.pethome.pojo.Announcement;
import org.pethome.service.AnnouncementService;
import org.pethome.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/announce")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 获取知识公告列表信息
     * @return
     */
    @GetMapping("/list")
    public Map getList(){
        List<Announcement> announcementList = announcementService.getList();
        return R.ok("list",announcementList);
    }

    /**
     * 根据公告id获取详情信息
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Map getDetail(@RequestParam Long id){
        Announcement announcement = announcementService.getDetail(id);
        return R.ok("detail",announcement);
    }
}
