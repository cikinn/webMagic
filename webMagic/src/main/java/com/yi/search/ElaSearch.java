package com.yi.search;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ElaSearch {

    private TransportClient client;

    @Before
    public void before() throws UnknownHostException {


        Settings settings = Settings.builder().put("cluster.name", "my-application").build();


        // 2 连接集群
        client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        // 3 打印集群名称
        System.out.println(client.toString());

    }

    /**
     * 新增
     */
    @Test
    public void test1(){
        client.prepareSearch("cikinnindex").get();
    }
}

