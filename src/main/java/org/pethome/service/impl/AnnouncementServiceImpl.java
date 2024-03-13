package org.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.pethome.mapper.AnnouncementMapper;
import org.pethome.mapper.MessageMapper;
import org.pethome.mapper.UserMapper;
import org.pethome.pojo.Announcement;
import org.pethome.pojo.Message;
import org.pethome.pojo.User;
import org.pethome.service.AnnouncementService;
import org.pethome.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;

    //获取公告列表信息
    @Override
    public List<Announcement> getList() {

        LambdaQueryWrapper<Announcement> lqw = new LambdaQueryWrapper<>();

        return announcementMapper.selectList(lqw);
    }
    //根据公告id获取详情信息
    @Override
    public Announcement getDetail(Long id) {
        LambdaQueryWrapper<Announcement> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Announcement::getAnnouncementId,id);
        return announcementMapper.selectOne(lqw);
    }
    //点赞
    @Override
    public void like(Long id) {
        LambdaQueryWrapper<Announcement> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Announcement::getAnnouncementId,id);
        Announcement announcement = announcementMapper.selectOne(lqw);
        Long like = announcement.getLiked() + 1;
        announcement.setLiked(like);
        announcementMapper.update(announcement,lqw);
    }
    //获取公告的评论详情
    @Override
    public List<MessageVo> getMessage(Long id) {
        //根据公告id获取留言信息
        LambdaQueryWrapper<Message> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Message::getAnnouncementId,id);
        List<Message> messages = messageMapper.selectList(lqw);
        List<MessageVo> messageVoList = new ArrayList<>();
        //根据留言查看用户信息
        for (Message message : messages){
            LambdaQueryWrapper<User> lqw2 = new LambdaQueryWrapper<>();
            lqw2.eq(User::getUserId,message.getUserId());
            User user = userMapper.selectOne(lqw2);
            MessageVo messageVo = new MessageVo();
            messageVo.setMessage(message.getMessage());
            messageVo.setUsername(user.getUsername());
            messageVo.setAvatar(user.getAvatar());
            messageVo.setDatetime(message.getDatetime());

            messageVoList.add(messageVo);
        }

        return messageVoList;
    }
    //发表留言
    @Override
    public void leave(Long id, Long userId,String message) {
        Message messageObj = new Message();
        messageObj.setUserId(userId);
        messageObj.setAnnouncementId(id);
        messageObj.setMessage(message);
        messageObj.setDatetime(new Date());
        messageMapper.insert(messageObj);
    }
}
