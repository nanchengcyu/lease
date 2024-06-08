package cn.nanchengyu.lease.web.app.service.impl;

import cn.nanchengyu.lease.common.constant.RedisConstant;
import cn.nanchengyu.lease.common.redis.RedisConfiguration;
import cn.nanchengyu.lease.model.entity.*;
import cn.nanchengyu.lease.model.enums.ItemType;
import cn.nanchengyu.lease.web.app.mapper.BrowsingHistoryMapper;
import cn.nanchengyu.lease.web.app.mapper.GraphInfoMapper;
import cn.nanchengyu.lease.web.app.mapper.RoomInfoMapper;
import cn.nanchengyu.lease.web.app.service.RoomInfoService;
import cn.nanchengyu.lease.web.app.vo.apartment.ApartmentItemVo;
import cn.nanchengyu.lease.web.app.vo.attr.AttrValueVo;
import cn.nanchengyu.lease.web.app.vo.fee.FeeValueVo;
import cn.nanchengyu.lease.web.app.vo.graph.GraphVo;
import cn.nanchengyu.lease.web.app.vo.room.RoomDetailVo;
import cn.nanchengyu.lease.web.app.vo.room.RoomItemVo;
import cn.nanchengyu.lease.web.app.vo.room.RoomQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.rmi.dgc.Lease;
import java.util.Date;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
@Slf4j
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {
    @Autowired
    private RoomInfoMapper roomInfoMapper;
    @Autowired
    private BrowsingHistoryMapper browsingHistoryMapper;
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private RedisTemplate<String, Object> template;

    @Override
    public IPage<RoomItemVo> pageItem(Page<RoomItemVo> page, RoomQueryVo queryVo) {

        return roomInfoMapper.pageItem(page, queryVo);
    }

    @Override
    public IPage<RoomItemVo> pageItemByApartmentId(Page<RoomItemVo> page, Long id) {

        return roomInfoMapper.pageItemByApartmentId(page, id);
    }

    @Override
    public void saveHistory(Long userId, Long id) {
        //保存房间浏览历史
        //查询是否是首次访问，若为则保存，否则更新这条记录的时间即可
        LambdaQueryWrapper<BrowsingHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BrowsingHistory::getRoomId, id);
        queryWrapper.eq(BrowsingHistory::getUserId, userId);
        BrowsingHistory RoomHistoryByUser = browsingHistoryMapper.selectOne(queryWrapper);
        BrowsingHistory browsingHistory = new BrowsingHistory();
        if (RoomHistoryByUser == null) {
            browsingHistory.setUserId(userId);
            browsingHistory.setRoomId(id);
            browsingHistory.setBrowseTime(new Date());
            browsingHistoryMapper.insert(browsingHistory);
        } else {
            browsingHistory.setUpdateTime(new Date());
            browsingHistory.setBrowseTime(new Date());
            browsingHistoryMapper.updateById(browsingHistory);
        }

    }


    @Override
    public RoomDetailVo getDetailById(Long id) {
        //Redis 性能优化 将数据库缓存的数据保存到Redis中 实现查询效率增快
        //以房间ID为key RoomDetailVo为value
        String key = RedisConstant.APP_ROOM_PREFIX + id;
        RoomDetailVo roomDetailVo = (RoomDetailVo) template.opsForValue().get(key);
        if (roomDetailVo == null){
            //则执行下方查询语句
            //缓存查询出来的信息数据
            template.opsForValue().set(key,roomDetailVo);
        }else {
            return roomDetailVo;
        }
//        //1.查询房间信息
//        RoomInfo roomInfo = roomInfoMapper.selectById(id);
//        if (roomInfo == null) {
//            return null;
//        }
//        //2.查询图片
//        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.ROOM, id);
//        //3.查询租期
//        List<LeaseTerm> leaseTermList = leaseTermMapper.selectListByRoomId(id);
//        //4.查询配套
//        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByRoomId(id);
//        //5.查询标签
//        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByRoomId(id);
//        //6.查询支付方式
//        List<PaymentType> paymentTypeList = paymentTypeMapper.selectListByRoomId(id);
//        //7.查询基本属性
//        List<AttrValueVo> attrValueVoList = attrValueMapper.selectListByRoomId(id);
//        //8.查询杂费信息
//        List<FeeValueVo> feeValueVoList = feeValueMapper.selectListByApartmentId(roomInfo.getApartmentId());
//        //9.查询公寓信息
//        ApartmentItemVo apartmentItemVo = apartmentInfoService.selectApartmentItemVoById(roomInfo.getApartmentId());
//
//        RoomDetailVo roomDetailVo = new RoomDetailVo();
//        BeanUtils.copyProperties(roomInfo, roomDetailVo);
//
//        roomDetailVo.setApartmentItemVo(apartmentItemVo);
//        roomDetailVo.setGraphVoList(graphVoList);
//        roomDetailVo.setAttrValueVoList(attrValueVoList);
//        roomDetailVo.setFacilityInfoList(facilityInfoList);
//        roomDetailVo.setLabelInfoList(labelInfoList);
//        roomDetailVo.setPaymentTypeList(paymentTypeList);
//        roomDetailVo.setFeeValueVoList(feeValueVoList);
//        roomDetailVo.setLeaseTermList(leaseTermList);

//        return roomDetailVo;
    return null;
    }
}




