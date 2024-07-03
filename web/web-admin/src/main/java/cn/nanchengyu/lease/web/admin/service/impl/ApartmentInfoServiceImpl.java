package cn.nanchengyu.lease.web.admin.service.impl;


import cn.nanchengyu.lease.model.entity.ApartmentInfo;
import cn.nanchengyu.lease.model.enums.ItemType;
import cn.nanchengyu.lease.web.admin.mapper.ApartmentInfoMapper;
import cn.nanchengyu.lease.web.admin.mapper.FeeValueMapper;
import cn.nanchengyu.lease.web.admin.mapper.GraphInfoMapper;
import cn.nanchengyu.lease.web.admin.service.ApartmentInfoService;
import cn.nanchengyu.lease.web.admin.vo.apartment.ApartmentDetailVo;
import cn.nanchengyu.lease.web.admin.vo.fee.FeeValueVo;
import cn.nanchengyu.lease.web.admin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private FeeValueMapper feeValueMapper;

    @Override
    public ApartmentDetailVo getDetailById(Long id) {
        ApartmentInfo apartmentInfo = this.getById(id);
        if (apartmentInfo == null) {
            return null;
        }

        //1.查询 图片列表
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, id);
        //1.查询 标签列表
        //1.查询 配套列表
        //1.查询 杂费列表
        List<FeeValueVo> feeValueVoList = feeValueMapper.selectListByApartmentId(id);

        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfo,apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setFeeValueVoList(feeValueVoList);
        return apartmentDetailVo;
    }
}




