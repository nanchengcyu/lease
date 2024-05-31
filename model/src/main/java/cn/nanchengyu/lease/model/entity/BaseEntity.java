package cn.nanchengyu.lease.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    //表中共有字段 其他实体类extends这个类即可
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonIgnore
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time",fill=FieldFill.UPDATE)
    @JsonIgnore
    private Date updateTime;

    @Schema(description = "逻辑删除")
    @TableLogic
    @JsonIgnore // 由com.fasterxml.jackson.annotation提供 序列化json字符串时不会展示到前端
    @TableField("is_deleted")
    private Byte isDeleted;

}