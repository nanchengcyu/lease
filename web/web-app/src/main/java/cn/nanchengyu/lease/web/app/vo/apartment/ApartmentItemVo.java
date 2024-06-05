package cn.nanchengyu.lease.web.app.vo.apartment;


import cn.nanchengyu.lease.model.entity.ApartmentInfo;
import cn.nanchengyu.lease.model.entity.LabelInfo;
import cn.nanchengyu.lease.web.app.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "App端公寓信息")
public class ApartmentItemVo extends ApartmentInfo {

    private List<LabelInfo> labelInfoList;

    private List<GraphVo> graphVoList;

    private BigDecimal minRent;
}
