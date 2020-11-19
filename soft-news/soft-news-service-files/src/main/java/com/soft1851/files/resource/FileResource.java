package com.soft1851.files.resource;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:file-${spring.profiles.active}.properties")
@ConfigurationProperties(prefix = "file")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileResource {
    private String host;
    private String endpoint;
    private String bucketName;
    private String objectName;
    private String ossHost;
}
