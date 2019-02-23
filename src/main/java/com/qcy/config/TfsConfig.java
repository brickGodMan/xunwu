package com.qcy.config;

import com.taobao.common.tfs.DefaultTfsManager;
import com.taobao.common.tfs.TfsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TFSConfig
 * @Description TODO
 * @Author qiancy
 * @Date 2019/2/22 10:01
 * @Version 1.0
 **/
@Configuration
public class TfsConfig {
    @Value("${tfs.client.maxWaitThread}")
    private Integer maxWaitThread;
    @Value("${tfs.client.timeout}")
    private Integer timeout;
    @Value("${tfs.client.nsip}")
    private String nsip;
    @Value("${tfs.client.maxCacheItemCount}")
    private Integer maxCacheItemCount;
    @Value("${tfs.client.maxCacheTime}")
    private Integer maxCacheTime;
    @Value("${tfs.client.namespace}")
    private Integer namespace;
    @Value("${tfs.client.tfsClusterIndex}")
    private char tfsClusterIndex;

    @Bean(name = "tfsManager")
    public TfsManager tfsManager() {
        DefaultTfsManager tfsManager = new DefaultTfsManager();
        tfsManager.setMaxWaitThread(maxWaitThread);
        tfsManager.setTimeout(timeout);
        tfsManager.setNsip(nsip);
        tfsManager.setMaxCacheItemCount(maxCacheItemCount);
        tfsManager.setMaxCacheTime(maxCacheTime);
        tfsManager.setNamespace(namespace);
        tfsManager.setTfsClusterIndex(tfsClusterIndex);
        return tfsManager;
    }
}
