package com.soft1851.api.controller.article;


import com.soft1851.pojo.bo.NewArticleBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(value = "文章业务的controller",tags = {"文章业务的controller"})
@RequestMapping("article")
public interface ArticleControllerApi {
    @ApiOperation(value = "用户发文",notes = "用户发文",httpMethod = "POST")
    @PostMapping("createArticle")
    GraceResult createArticle(@RequestBody @Valid NewArticleBO newArticleBO, BindingResult result);

    /**
     * 管理员审核文章
     * @param articleId
     * @param passOrNot
     * @return
     */
    @PostMapping("/doReview")
    @ApiOperation(value = "管理员审核文章",notes = "管理员审核文章",httpMethod = "POST")
    GraceResult doReview(@RequestParam String articleId,@RequestParam Integer passOrNot);

    /**
     * 用户删除文章
     * @param userId
     * @param articleId
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value = "用户删除文章",notes = "用户删除文章",httpMethod = "POST")
    GraceResult delete(@RequestParam String userId,@RequestParam String articleId);

    @PostMapping("/withdraw")
    @ApiOperation(value = "用户撤回文章",notes = "用户撤回文章",httpMethod = "POST")
    GraceResult withdraw(@RequestParam String userId,@RequestParam String articleId);

    @GetMapping("detail")
    @ApiOperation(value = "文章详情查询",notes = "文章详情查询",httpMethod = "GET")
    GraceResult detail(@RequestParam String articleId);


    @PostMapping("readArticle")
    @ApiOperation(value = "阅读文章，累加阅读量",notes = "阅读文章，添加阅读量",httpMethod = "POST")
    GraceResult readArticle(@RequestParam String articleId, HttpServletRequest request);

}
