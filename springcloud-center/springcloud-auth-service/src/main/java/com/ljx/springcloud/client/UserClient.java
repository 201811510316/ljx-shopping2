package com.ljx.springcloud.client;

import com.ljx.springcloud.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="user-service")
public interface UserClient extends UserApi {
}
