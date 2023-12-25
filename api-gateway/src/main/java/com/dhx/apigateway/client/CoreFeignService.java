package com.dhx.apigateway.client;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.model.to.InterfaceTo;
import com.dhx.apicommon.model.to.UserTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author adorabled4
 * @className InterfaceClient
 * @date : 2023/12/25/ 09:45
 **/
@FeignClient(name = "api-core")
public interface CoreFeignService {

//    服务消费者这边feign调用时，在所有参数前加上@RequestParam注解。
    @GetMapping(value = "/apicore/feign/user/interface/count")
    boolean invokeCount(@RequestParam("userId") long userId,
                        @RequestParam("interfaceInfoId") long interfaceInfoId,
                        @RequestBody BaseResponse baseResponse);

    @GetMapping(value = "/apicore/feign/user/interface/left")
    Integer getUserLeftNum(@RequestParam("userId") Long userId);

    @GetMapping(value = "/apicore/feign/user/key")
    UserTo getUserByAK(@RequestParam("accessKey") String accessKey);

    @GetMapping(value = "/apicore/feign/user/token")
    UserTo getUserByToken(@RequestParam("token") String token,
                          @RequestParam HttpHeaders headers);

    @GetMapping(value = "/apicore/feign/interface/info")
    InterfaceTo getInterfaceInfo(@RequestParam("url") String url,
                                 @RequestParam("method") String method);

    @GetMapping(value = "/apicore/feign/interface/call/count")
    void interfaceCallCount(@RequestParam("interfaceId") long interfaceId);

    @GetMapping(value = "/apicore/feign/test")
    String testFeign();
}