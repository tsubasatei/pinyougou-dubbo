package com.xt.pinyougou.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xt
 * @since 2020-01-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码，加密存储")
    private String password;

    @ApiModelProperty(value = "注册手机号")
    private String phone;

    @ApiModelProperty(value = "注册邮箱")
    private String email;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime created;

    private LocalDateTime updated;

    @ApiModelProperty(value = "会员来源：1:PC，2：H5，3：Android，4：IOS，5：WeChat")
    private String sourceType;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    private String name;

    @ApiModelProperty(value = "使用状态（Y正常 N非正常）")
    private String status;

    @ApiModelProperty(value = "头像地址")
    private String headPic;

    @ApiModelProperty(value = "QQ号码")
    private String qq;

    @ApiModelProperty(value = "账户余额")
    private BigDecimal accountBalance;

    @ApiModelProperty(value = "手机是否验证 （0否  1是）")
    private String isMobileCheck;

    @ApiModelProperty(value = "邮箱是否检测（0否  1是）")
    private String isEmailCheck;

    @ApiModelProperty(value = "性别，1男，2女")
    private String sex;

    @ApiModelProperty(value = "会员等级")
    private Integer userLevel;

    @ApiModelProperty(value = "积分")
    private Integer points;

    @ApiModelProperty(value = "经验值")
    private Integer experienceValue;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;


}
