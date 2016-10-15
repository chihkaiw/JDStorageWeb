package com.danny.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.danny.core.Const.Const;

@Configuration
@Component
@EnableAutoConfiguration
public class InventoryService {

  // private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

  public String getDesc() {

    // logger.debug("getDesc() is executed!");

    return Const.PROJECT_NAME;

  }

  public String getTitle(String name) {

    // logger.debug("getTitle() is executed! $name : {}", name);

    if (StringUtils.isEmpty(name)) {
      return Const.HOME_PAGE_GREETING;
    } else {
      return "Hello " + name;
    }

  }

}
