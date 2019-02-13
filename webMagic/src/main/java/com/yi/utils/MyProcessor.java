package com.yi.utils;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class MyProcessor implements PageProcessor {

    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-zA-Z0-9]+/article/details/[0-9]{8}").all());

        String title = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1/text()").get();
        String content = page.getHtml().xpath("//*[@id=\"content_views\"]").get();

        if (StringUtils.isBlank(title) || StringUtils.isBlank(content)) {
            page.setSkip(true);
        } else {
            page.putField("title", title);
            page.putField("content", content);
        }
    }

    public Site getSite() {
        return Site.me().setSleepTime(100).setRetryTimes(3);
    }


}
