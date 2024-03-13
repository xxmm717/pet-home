package org.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.pethome.pojo.Announcement;

@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {
}
