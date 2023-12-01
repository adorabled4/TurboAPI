package com.dhx.apiinterface.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.util.IpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author adorabled4
 * @className IpController
 * @date : 2023/04/15/ 15:55
 **/
@RestController
@RequestMapping("/ip")
public class IpController {


    /**
     * 获取IPv4地址的location
     * @param ipAddr
     * @return
     */
    @GetMapping("/ana")
    public BaseResponse<String> analysisIP(@RequestParam("ip")String ipAddr){
        try{
            InetAddress inetAddress = InetAddress.getByName(ipAddr);

            if (inetAddress instanceof Inet4Address) {
                String location = IpUtil.getIpLocation(ipAddr);
                return ResultUtil.success(location);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不是合法的IPv4地址!");
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取请求者的IP地址
     * @param request
     * @return
     */
    @GetMapping("/me")
    public BaseResponse<String> getIpOfRequest(HttpServletRequest request){
        String ipAddr = IpUtil.getIpAddr(request);
        return ResultUtil.success(ipAddr);
    }
}
