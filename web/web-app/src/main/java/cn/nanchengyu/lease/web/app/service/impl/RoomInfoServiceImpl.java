package cn.nanchengyu.lease.web.app.service.impl;

import cn.nanchengyu.lease.model.entity.BrowsingHistory;
import cn.nanchengyu.lease.model.entity.RoomInfo;
import cn.nanchengyu.lease.web.app.mapper.BrowsingHistoryMapper;
import cn.nanchengyu.lease.web.app.mapper.RoomInfoMapper;
import cn.nanchengyu.lease.web.app.service.RoomInfoService;
import cn.nanchengyu.lease.web.app.vo.room.RoomItemVo;
import cn.nanchengyu.lease.web.app.vo.room.RoomQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}




