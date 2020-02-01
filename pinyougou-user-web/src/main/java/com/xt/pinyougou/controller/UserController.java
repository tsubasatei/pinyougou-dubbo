package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.User;
import com.xt.pinyougou.service.UserService;
import com.xt.pinyougou.util.PhoneFormatCheckUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xt
 * @since 2020-01-30
 */
@Api(value = "user", description = "用户管理-消费端")
@RestController
@RequestMapping("/consumer/user")
public class UserController {

    @Reference
    private UserService userService;

    @ApiOperation(value = "添加品牌", notes = "添加品牌")
    @PostMapping("/{smsCode}")
    public Result add(@RequestBody User user, @ApiParam(value = "验证码", required = true) @PathVariable("smsCode") String smsCode) {
        Result result = new Result();
        try {
            // 判断验证码是否正确
            boolean b = userService.checkSmsCode(user.getPhone(), smsCode);
            if (!b) {
                return new Result(false, "验证码不正确");
            }
            boolean flag = userService.add(user);
            result.setSuccess(flag);
            if (flag) {
                result.setMessage("添加成功");
            } else {
                result.setMessage("添加失败");
            }
        } catch (Exception e) {
            result = new Result(false, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码")
    @GetMapping("/sendCode/{phone}")
    public Result sendCode(@ApiParam(value = "手机号", required = true) @PathVariable("phone") String phone) {
        //判断手机号格式
        if(!PhoneFormatCheckUtils.isPhoneLegal(phone)){
            return new Result(false, "手机号格式不正确");
        }
        try {
            userService.createSmsCode(phone);//生成验证码
            return new Result(true, "验证码发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "验证码发送失败");
        }

    }

}

