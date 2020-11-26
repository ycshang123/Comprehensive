package com.soft1851.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FansCountsVO {
    private Integer manCounts;
    private Integer womanCounts;
}
