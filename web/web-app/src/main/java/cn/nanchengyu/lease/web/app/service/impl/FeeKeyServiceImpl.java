package cn.nanchengyu.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nanchengyu.lease.model.entity.FeeKey;
import cn.nanchengyu.lease.web.app.service.FeeKeyService;
import cn.nanchengyu.lease.web.app.mapper.FeeKeyMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service实现
* @createDate 2023-07-26 11:12:39
*/
@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey>
    implements FeeKeyService{

}




