//package com.dhx.apicore.controller;
//
//import com.dhx.apicommon.common.BaseResponse;
//import com.dhx.apicommon.model.to.InterfaceTo;
//import com.dhx.apicommon.model.to.UserTo;
//import com.dhx.apicommon.service.InnerInterfaceService;
//import com.dhx.apicommon.service.InnerUserInterfaceInfoService;
//import com.dhx.apicommon.service.InnerUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @author adorabled4
// * @className UserInterfaceController
// * @date : 2023/12/25/ 12:32
// **/
//@RestController
//@Slf4j
//@RequestMapping("/feign")
//public class UserInterfaceController {
//
//    @Resource
//    InnerUserService innerUserService;
//    @Resource
//    InnerInterfaceService innerInterfaceService;
//    @Resource
//    InnerUserInterfaceInfoService innerUserInterfaceInfoService;
//
//    @GetMapping(value = "/user/interface/count")
//    boolean invokeCount(long userId, long interfaceInfoId, BaseResponse baseResponse) {
//        return innerUserInterfaceInfoService.invokeCount(userId, interfaceInfoId, baseResponse);
//    }
//
//    @GetMapping(value = "/user/interface/left")
//    Integer getUserLeftNum(Long userId) {
//        return innerUserInterfaceInfoService.getUserLeftNum(userId);
//    }
//
//    @GetMapping(value = "/user/key")
//    UserTo getUserByAK(String accessKey) {
//        return innerUserService.getUserEntityByAccessKey(accessKey);
//    }
//
//    @GetMapping(value = "/user/token")
//    UserTo getUserByToken(String token, HttpHeaders headers) {
//        return innerUserService.getUserEntityByAccessToken(token, headers);
//    }
//
//    @GetMapping(value = "/interface/info")
//    InterfaceTo getInterfaceInfo(String url, String method) {
//        return innerInterfaceService.getInterfaceInfo(url, method);
//    }
//
//    @GetMapping(value = "/interface/call/count")
//    void interfaceCallCount(long interfaceId) {
//        innerInterfaceService.interfaceCallCount(interfaceId);
//    }
//
//    @GetMapping(value = "/test")
//    String test(){
//        return "<h1>aaaaaaa</h1>";
//    }
//}
