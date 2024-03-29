package org.pethome.controller;

import org.pethome.config.UserHolder;
import org.pethome.pojo.Announcement;
import org.pethome.service.AnnouncementService;
import org.pethome.utils.R;
import org.pethome.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 点赞
     * @param id
     * @return
     */
    @PostMapping("/like")
    public Map like(@RequestParam Long id){
        announcementService.like(id);
        return R.ok("点赞成功");
    }

    /**
     * 获取公告的评论详情
     * @param id
     * @return
     */
    @GetMapping("/message")
    public Map getMessage(@RequestParam Long id){
        List<MessageVo> messageVoList = announcementService.getMessage(id);
        return R.ok("messageList",messageVoList);
    }

    /**
     * 发表留言
     * @param id
     * @return
     */
    @PostMapping("/leave")
    public Map leave(@RequestParam Long id,@RequestParam String message){
        Long userId = UserHolder.getUserId();
        announcementService.leave(id,userId,message);
        return R.ok("发表成功");
    }
}
