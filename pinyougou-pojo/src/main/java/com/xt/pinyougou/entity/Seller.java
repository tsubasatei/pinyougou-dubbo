package com.xt.pinyougou.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_seller")
@ApiModel(value="Seller对象", description="商家")
public class Seller implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "seller_id")
    private String sellerId;

    @ApiModelProperty(value = "公司名")
    private String name;

    @ApiModelProperty(value = "店铺名称")
    private String nickName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "EMAIL")
    private String email;

    @ApiModelProperty(value = "公司手机")
    private String mobile;

    @ApiModelProperty(value = "公司电话")
    private String telephone;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "详细地址")
    private String addressDetail;

    @ApiModelProperty(value = "联系人姓名")
    private String linkmanName;

    @ApiModelProperty(value = "联系人QQ")
    private String linkmanQq;

    @ApiModelProperty(value = "联系人电话")
    private String linkmanMobile;

    @ApiModelProperty(value = "联系人EMAIL")
    private String linkmanEmail;

    @ApiModelProperty(value = "营业执照号")
    private String licenseNumber;

    @ApiModelProperty(value = "税务登记证号")
    private String taxNumber;

    @ApiModelProperty(value = "组织机构代码")
    private String orgNumber;

    @ApiModelProperty(value = "公司地址")
    private Long address;

    @ApiModelProperty(value = "公司LOGO图")
    private String logoPic;

    @ApiModelProperty(value = "简介")
    private String brief;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "法定代表人")
    private String legalPerson;

    @ApiModelProperty(value = "法定代表人身份证")
    private String legalPersonCardId;

    @ApiModelProperty(value = "开户行账号名称")
    private String bankUser;

    @ApiModelProperty(value = "开户行")
    private String bankName;

    @ApiModelProperty(value = "银行卡号")
    private String bankCard;


}
