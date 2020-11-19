package com.soft1851.api.controller.file;


import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "文件上传controller",tags = {"文件上传controller"})
@RequestMapping("fs")
public interface FileUploadControllerApi {
    @ApiOperation(value = "上传用户头像",notes="上传用户头像",httpMethod = "POST")
    @PostMapping("UploadFace")
    GraceResult uploadFace(@RequestParam String userId, MultipartFile file) throws  Exception;
}
