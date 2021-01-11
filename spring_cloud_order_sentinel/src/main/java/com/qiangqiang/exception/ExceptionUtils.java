package com.qiangqiang.exception;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.qiangqiang.entity.Order;
import com.qiangqiang.entity.Product;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

import java.util.ArrayList;
import java.util.List;

public class ExceptionUtils {

    /**
     * 静态方法
     * 返回值: SentinelClientHttpResponse
     * 参数 : request , byte[] , clientRquestExcetion , blockException
     */
    //限流熔断业务逻辑
    public static SentinelClientHttpResponse handleBlock(HttpRequest request, byte[] body,
                                                         ClientHttpRequestExecution execution, BlockException ex) {
        Product product1 = new Product(1, "手机restTemplate兜底",1);
        Product product2 = new Product(2, "电脑restTemplate兜底",1);
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);

        return new SentinelClientHttpResponse(JSON.toJSONString(list));
    }

    //异常降级业务逻辑
    public static SentinelClientHttpResponse fallback(HttpRequest request, byte[] body,
                                                            ClientHttpRequestExecution execution, BlockException ex) {
        Product product1 = new Product(1, "手机restTemplate兜底",1);
        Product product2 = new Product(2, "电脑restTemplate兜底",1);
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);

        return new SentinelClientHttpResponse(JSON.toJSONString(list));
    }

}
