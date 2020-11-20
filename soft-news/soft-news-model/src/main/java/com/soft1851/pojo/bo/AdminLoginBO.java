package com.soft1851.pojo.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * 管理员登录BO类
 */
public class AdminLoginBO {
    private String username;
    private String password;
    private String img64;
}
