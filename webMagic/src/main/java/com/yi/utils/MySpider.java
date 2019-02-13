package com.yi.utils;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.scheduler.RedisScheduler;

public class MySpider {

    public static void main(String[] args) {
        Spider.create(new MyProcessor())
                .addUrl("https://blog.csdn.net/weixin_42022555/article/details/83615760")
                .addPipeline(new ConsolePipeline())
                .setScheduler(new RedisScheduler("127.0.0.1"))
                .run();
    }
}
