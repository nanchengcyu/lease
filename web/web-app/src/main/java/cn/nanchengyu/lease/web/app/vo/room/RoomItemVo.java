package cn.nanchengyu.lease.web.app.vo.room;


import cn.nanchengyu.lease.model.entity.ApartmentInfo;
import cn.nanchengyu.lease.model.entity.LabelInfo;
import cn.nanchengyu.lease.web.app.vo.graph.GraphVo;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "APP房间列表实体")
@Data
public class RoomItemVo {
    //1 表
    @Schema(description = "房间id")
    private Long id;

    @Schema(description = "房间号")
    private String roomNumber;

    @Schema(description = "租金（元/月）")
    private BigDecimal rent;
    //2 表

    @Schema(description = "房间图片列表")
    private List<GraphVo> graphVoList;
    // 3 表
    @Schema(description = "房间标签列表")
    private List<LabelInfo> labelInfoList;
    // 4 表
    @Schema(description = "房间所属公寓信息")
    private ApartmentInfo apartmentInfo;

}
