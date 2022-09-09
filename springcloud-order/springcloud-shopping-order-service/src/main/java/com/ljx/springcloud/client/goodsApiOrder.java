package com.ljx.springcloud.client;

import com.ljx.springcloud.api.goodsApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(value = "springcloud-shopping-items-service")
public interface goodsApiOrder extends goodsApi {

}
