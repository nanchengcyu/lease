package cn.nanchengyu.lease.web.admin.controller.apartment;


import cn.nanchengyu.lease.common.result.Result;
import cn.nanchengyu.lease.model.entity.AttrKey;
import cn.nanchengyu.lease.model.entity.AttrValue;
import cn.nanchengyu.lease.web.admin.service.AttrKeyService;
import cn.nanchengyu.lease.web.admin.service.AttrValueService;
import cn.nanchengyu.lease.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
public class AttrController {
    @Autowired
    private AttrKeyService attrKeyService;
    @Autowired
    private AttrValueService attrValueService;
    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {
        attrKeyService.saveOrUpdate(attrKey);
        return Result.ok();
    }

    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue) {
        attrValueService.saveOrUpdate(attrValue);
        return Result.ok();
    }


    @Operation(summary = "查询全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo() {
        //VO 返回前端需要的接口对象属性
        List<AttrKeyVo> list = attrKeyService.listAttrInfo();
        return Result.ok(list);
    }

    @Operation(summary = "根据id删除属性名称")
    @DeleteMapping("key/deleteById")
    public Result removeAttrKeyById(@RequestParam Long attrKeyId) {
        //删除属性名称
        attrKeyService.removeById(attrKeyId);
        //删除属性值
        attrValueService.remove(new QueryWrapper<AttrValue>().eq("attr_key_id", attrKeyId));
        return Result.ok();
    }

    @Operation(summary = "根据id删除属性值")
    @DeleteMapping("value/deleteById")
    public Result removeAttrValueById(@RequestParam Long id) {

        attrValueService.removeById(id);
        return Result.ok();
    }

}
