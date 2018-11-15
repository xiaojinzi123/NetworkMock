package com.move.mock;

import com.move.mock.bean.BaseUrlBean;
import com.move.mock.service.baseurl.BaseUrlCache;
import com.move.mock.service.baseurl.BaseUrlService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        BaseUrlService service = contextRefreshedEvent.getApplicationContext().getBean(BaseUrlService.class);
        List<BaseUrlBean> baseUrlBeans = service.queryAll();
        BaseUrlCache.getInstance().syncData(baseUrlBeans);
    }
}