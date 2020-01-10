package com.xt.pinyougou.controller;

import com.xt.bean.Result;
import com.xt.pinyougou.util.FastDFSClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(value = "文件管理", description = "文件管理-消费端")
@RestController
@RequestMapping("/consumer/file")
public class FileController {

    @Autowired
    private FastDFSClientUtil fastDFSClientUtil;

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping("/upload")
    public Result upload(@ApiParam(value = "文件", required = true) MultipartFile file) {
        try {
            String url = fastDFSClientUtil.uploadFile(file);
            return new Result(true, url);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "上传失败");
        }
    }

}
