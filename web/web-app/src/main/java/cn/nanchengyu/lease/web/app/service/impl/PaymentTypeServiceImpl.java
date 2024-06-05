package cn.nanchengyu.lease.web.app.service.impl;

import cn.nanchengyu.lease.model.entity.PaymentType;
import cn.nanchengyu.lease.web.app.mapper.PaymentTypeMapper;
import cn.nanchengyu.lease.web.app.service.PaymentTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
* @createDate 2023-07-26 11:12:39
*/
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType>
    implements PaymentTypeService{

}



