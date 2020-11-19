package com.soft1851.files.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传服务接口
 */
public interface UploadService {
    /**
     * fdfs文件上传
     * @param file
     * @param fileExtName
     * @return
     * @throws Exception
     */

    String uploadFiles(MultipartFile file,String fileExtName) throws Exception;
}

