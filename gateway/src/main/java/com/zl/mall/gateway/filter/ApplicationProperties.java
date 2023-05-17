package com.zl.mall.gateway.filter;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhongliang
 */
@Configuration
@ConfigurationProperties(prefix = "auth")
public class ApplicationProperties {	
    
    private List<String> whiteList;
    
    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

}