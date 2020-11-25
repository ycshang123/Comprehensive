package com.soft1851.article.task;

import com.soft1851.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskPublishArticle {
    private final ArticleService articleService;

    /**
     * 添加定时任务，注明定时任务表达式，这里表达式的含义是每10秒执行一次
     *
     */
    @Scheduled(cron = "0/10 * * * * ?")
    private void publishArticles(){
        System.out.println("执行定时任务"+ LocalDateTime.now());
//        调用文章service,把当前时间应该发布的定时文章，状态改为即时
        articleService.updateAppointToPublish();
    }
}
