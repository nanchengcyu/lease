package cn.nanchengyu.lease.web.admin.controller.apartment;


import cn.nanchengyu.lease.common.constant.RedisConstant;
import cn.nanchengyu.lease.common.result.Result;
import cn.nanchengyu.lease.model.entity.RoomInfo;
import cn.nanchengyu.lease.model.enums.ReleaseStatus;
import cn.nanchengyu.lease.web.admin.vo.room.RoomDetailVo;
import cn.nanchengyu.lease.web.admin.vo.room.RoomItemVo;
import cn.nanchengyu.lease.web.admin.vo.room.RoomQueryVo;
import cn.nanchengyu.lease.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/admin/room")
public class RoomController {
    @Autowired
    private RedisTemplate<String, Object> template;
    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo) {
        //删除缓存 逻辑应该写到Service中 此处省略
        String key = RedisConstant.APP_ROOM_PREFIX + new RoomSubmitVo().getId();
        template.delete(key);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, RoomQueryVo queryVo) {
        return Result.ok();
    }

    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok();
    }

    @Operation(summary = "根据id删除房间信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        String key = RedisConstant.APP_ROOM_PREFIX + id;
        template.delete(key);
        return Result.ok();
    }

    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(Long id, ReleaseStatus status) {
        return Result.ok();
    }

    @GetMapping("listBasicByApartmentId")
    @Operation(summary = "根据公寓id查询房间列表")
    public Result<List<RoomInfo>> listBasicByApartmentId(Long id) {
        return Result.ok();
    }

}


















