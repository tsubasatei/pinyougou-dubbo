package com.xt.pinyougou.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * CAS的配置参数
 */
@Component
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CasProperties {
	
        @Value("${security.cas.server.host.url}")
        private String casServerUrl;
 
        @Value("${security.cas.server.host.login_url}")
        private String casServerLoginUrl;
 
        @Value("${security.cas.server.host.logout_url}")
        private String casServerLogoutUrl;
 
        @Value("${security.app.server.host.url}")
        private String appServerUrl;
 
        @Value("${security.app.login.url}")
        private String appLoginUrl;
 
        @Value("${security.app.logout.url}")
        private String appLogoutUrl;
}