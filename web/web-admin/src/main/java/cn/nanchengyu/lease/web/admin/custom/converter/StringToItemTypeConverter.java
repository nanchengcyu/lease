package cn.nanchengyu.lease.web.admin.custom.converter;

import cn.nanchengyu.lease.model.enums.ItemType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * ClassName: StringToItemTypeConverter
 * Package: nanchengyu.lease.web.admin.custom.converter
 * Description:
 * 自定义类型转换器
 *
 * @Author 南城余
 * @Create 2024/5/31 16:24
 * @Version 1.0
 */
@Component
public class StringToItemTypeConverter implements Converter<String, ItemType> {
    @Override
    public ItemType convert(String code) {
//        if ("1".equals(code)) {
//
//            return ItemType.APARTMENT;
//        } else if ("2".equals(code)){
//            return ItemType.ROOM;
//        }
        ItemType[] values = ItemType.values();
        for (ItemType itemType : values) {
            if(itemType.getCode().equals(Integer.valueOf(code)))
                return itemType;
        }
        throw new IllegalArgumentException("无法识别的code：" + code);
    }


}
