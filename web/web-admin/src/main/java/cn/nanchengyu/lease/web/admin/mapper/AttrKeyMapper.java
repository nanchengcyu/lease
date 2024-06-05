package cn.nanchengyu.lease.web.admin.mapper;


import cn.nanchengyu.lease.model.entity.AttrKey;
import cn.nanchengyu.lease.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity nanchengyu.lease.model.AttrKey
*/
@Mapper
public interface AttrKeyMapper extends BaseMapper<AttrKey> {

    List<AttrKeyVo> listAttrInfo();
}




