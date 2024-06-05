package cn.nanchengyu.lease.web.admin.mapper;

import cn.nanchengyu.lease.model.entity.FeeKey;
import cn.nanchengyu.lease.web.admin.vo.fee.FeeKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity nanchengyu.lease.model.FeeKey
*/
public interface FeeKeyMapper extends BaseMapper<FeeKey> {

    List<FeeKeyVo> feeInfoList();
}




