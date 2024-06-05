package cn.nanchengyu.lease.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: MinioProperties
 * Package: nanchengyu.lease.common.minio
 * Description:
 *
 * @Author 南城余
 * @Create 2024/6/4 16:23
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

}
