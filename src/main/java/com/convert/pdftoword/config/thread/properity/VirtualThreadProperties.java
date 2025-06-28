package com.convert.pdftoword.config.thread.properity;

import com.convert.pdftoword.config.thread.VirtualTreadConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.pdfbox.jbig2.util.log.Logger;
import org.apache.pdfbox.jbig2.util.log.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "spring.virtual.thread")
public class VirtualThreadProperties {
    private String threadNamePrefix = "vt-";
    private Logger logger =  LoggerFactory.getLogger(VirtualTreadConfig.class);

}
