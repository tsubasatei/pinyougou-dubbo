package com.xt.pinyougou.util;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

/**
 * FastDFS 工具类
 */
@Component
public class FastDFSClientUtil {

    @Value("${fdfs.fileServerUrl}")
    private String fileServerUrl;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 文件上传
     *
     * storePath
     * "group": "group1",
     * "path": "M00/00/01/wKj4RFvuZlOALpzMAAJZc0IRPSY16.docx",
     * "fullPath": "group1/M00/00/01/wKj4RFvuZlOALpzMAAJZc0IRPSY16.docx"
     */
    public String uploadFile(MultipartFile file) throws IOException {

        /**
         * 设置文件信息
         * 文件信息可以为空 直接传null即可
         */
        Set<MetaData> mataDatas = new HashSet<>();
        mataDatas.add(new MetaData("author", "xt"));
        mataDatas.add(new MetaData("description", "附件上传"));

        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), mataDatas);

        String url = getResAccessUrl(storePath);
        logger.info("上传图片的地址：" + url);
        return url;
    }

    /**
     * 文件删除
     *
     * @param path group1/M00/00/00/wKj4RVvuZQWAAS5RAAJZc0IRPSY48.docx
     * @return
     */
    public void delFile(String path) {
        String[] strings = path.split(fileServerUrl);
        logger.info("删除的图片地址：" + strings[1]);
        // 第一种删除：参数：完整地址
        fastFileStorageClient.deleteFile(strings[1]);

        // 第二种删除：参数：组名加文件路径
        // fastFileStorageClient.deleteFile(group, path);
    }

    /**
     * 文件下载
     *
     * @param group group1
     * @param path M00/00/01/wKj4RFvuZlOALpzMAAJZc0IRPSY16.docx
     * @param fileName wKj4RFvuZlOALpzMAAJZc0IRPSY16.docx
     * @param response
     * @throws IOException
     */
    public void downLoad(String group, String path, String fileName, HttpServletResponse response) throws IOException {

        logger.info("下载图片：" + fileName);
        // 获取文件
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, new DownloadByteArray());

        //设置相应类型application/octet-stream （注：applicatoin/octet-stream 为通用，一些其它的类型苹果浏览器下载内容可能为空）
        response.reset();
        response.setContentType("application/octet-stream");
        //设置头信息                 Content-Disposition为属性名  附件形式打开下载文件   指定名称  为 设定的fileName
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        // 写入到流
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.close();
    }

    /**
     * 封装文件完整 URL 地址
     *
     * @param storePath
     * @return
     */
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = fileServerUrl + "/" + storePath.getFullPath();
        return fileUrl;
    }
}