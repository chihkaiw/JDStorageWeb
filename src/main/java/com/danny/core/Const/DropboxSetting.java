package com.danny.core.Const;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("dropbox")
public class DropboxSetting {

  private String appKey;
  private String appSecret;
  private String appAuthAccessToken;

  public String getAppKey() {
    return appKey;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public String getAppAuthToken() {
    return appAuthAccessToken;
  }
}
