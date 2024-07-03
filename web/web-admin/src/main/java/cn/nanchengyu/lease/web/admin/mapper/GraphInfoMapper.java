package cn.nanchengyu.lease.web.admin.mapper;


import cn.nanchengyu.lease.model.entity.GraphInfo;
import cn.nanchengyu.lease.model.enums.ItemType;
import cn.nanchengyu.lease.web.admin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity nanchengyu.lease.model.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long itemId);
}




