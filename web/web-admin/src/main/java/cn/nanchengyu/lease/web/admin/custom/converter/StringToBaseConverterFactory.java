package cn.nanchengyu.lease.web.admin.custom.converter;

import cn.nanchengyu.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * ClassName: StringToBaseConverterFactory
 * Package: cn.nanchengyu.lease.web.admin.custom.converter
 * Description:
 *
 * @Author 南城余
 * @Create 2024/5/31 16:51
 * @Version 1.0
 */
@Component
public class StringToBaseConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(String code) {
                T[] enumConstants = targetType.getEnumConstants();
                for (T enumConstant : enumConstants) {
                    if (enumConstant.getCode().equals(Integer.valueOf(code))){
                        return enumConstant;
                    }
                }
                throw new IllegalArgumentException("无法识别的code：" + code);
            }
        };
    }
}
