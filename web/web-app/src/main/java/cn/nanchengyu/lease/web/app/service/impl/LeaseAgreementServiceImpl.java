package cn.nanchengyu.lease.web.app.service.impl;

import cn.nanchengyu.lease.model.entity.LeaseAgreement;
import cn.nanchengyu.lease.web.app.mapper.LeaseAgreementMapper;
import cn.nanchengyu.lease.web.app.service.LeaseAgreementService;
import cn.nanchengyu.lease.web.app.vo.agreement.AgreementItemVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {
    @Autowired
    private LeaseAgreementMapper mapper;
    @Override
    public List<AgreementItemVo> listItemByPhone(String phone) {

        return mapper.listItemByPhone(phone);
    }
}




