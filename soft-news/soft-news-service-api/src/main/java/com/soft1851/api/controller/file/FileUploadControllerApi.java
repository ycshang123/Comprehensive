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

    /**
     * 多文件上传
     * @param userId
     * @param files
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "上传多个文件",notes="上传多个文件",httpMethod = "POST")
    @PostMapping("/uploadSomeFiles")
    GraceResult uploadSomeFiles (@RequestParam String userId, MultipartFile[] files) throws Exception;

    /**
     * 管理员人脸入库
     * @param username
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "管理员人脸入库", notes = "管理员人脸入库", httpMethod = "POST")
    @PostMapping("/uploadToGridFS")
    GraceResult uploadToGridFs(@RequestParam String username, @RequestParam(value = "file") MultipartFile multipartFile) throws Exception;
}
