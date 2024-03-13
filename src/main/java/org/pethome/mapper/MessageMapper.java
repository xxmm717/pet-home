package org.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.pethome.pojo.Message;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
