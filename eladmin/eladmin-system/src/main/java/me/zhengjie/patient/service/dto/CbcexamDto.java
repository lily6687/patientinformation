/*
*  Copyright 2019-2025 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.patient.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
* @website https://eladmin.vip
* @description /
* @author zhengjie
* @date 2025-09-11
**/
@Data
public class CbcexamDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "血常规（是/否）")
    private Boolean blyn;

    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp blchkdat;

    @ApiModelProperty(value = "中性粒细胞计数-检查结果")
    private Float bl1res;

    @ApiModelProperty(value = "中性粒细胞计数-单位")
    private String bl1unit;

    @ApiModelProperty(value = "中性粒细胞计数-结果判定")
    private String bl1clsig;

    @ApiModelProperty(value = "中性粒细胞计数备注")
    private String bl1oth;

    @ApiModelProperty(value = "淋巴细胞计数-检查结果")
    private Float bl2res;

    @ApiModelProperty(value = "淋巴细胞计数-单位")
    private String bl2unit;

    @ApiModelProperty(value = "淋巴细胞计数-结果判定")
    private String bl2clsig;

    @ApiModelProperty(value = "淋巴细胞计数-备注")
    private String bl2oth;

    @ApiModelProperty(value = "红细胞计数-检查结果")
    private Float bl3res;

    @ApiModelProperty(value = "红细胞计数-单位")
    private String bl3unit;

    @ApiModelProperty(value = "红细胞计数-结果判定")
    private String bl3clsig;

    @ApiModelProperty(value = "红细胞计数-备注")
    private String bl3oth;

    @ApiModelProperty(value = "血红蛋白-检查结果")
    private Float bl4res;

    @ApiModelProperty(value = "血红蛋白-单位")
    private String bl4unit;

    @ApiModelProperty(value = "血红蛋白-结果判定")
    private String bl4clsig;

    @ApiModelProperty(value = "备注")
    private String bl4oth;

    @ApiModelProperty(value = "红细胞压积-检查结果")
    private Float bl5res;

    @ApiModelProperty(value = "红细胞压积-单位")
    private String bl5unit;

    @ApiModelProperty(value = "红细胞压积-结果判定")
    private String bl5clsig;

    @ApiModelProperty(value = "备注.1")
    private String bl5oth;

    @ApiModelProperty(value = "白细胞计数-检查结果")
    private Float bl6res;

    @ApiModelProperty(value = "白细胞计数-单位")
    private String bl6unit;

    @ApiModelProperty(value = "白细胞计数-结果判定")
    private String bl6clsig;

    @ApiModelProperty(value = "白细胞计数-备注")
    private String bl6oth;

    @ApiModelProperty(value = "血小板计数-检查结果")
    private Float bl7res;

    @ApiModelProperty(value = "血小板计数-单位")
    private String bl7unit;

    @ApiModelProperty(value = "血小板计数-结果判定")
    private String bl7clsig;

    @ApiModelProperty(value = "血小板计数-备注")
    private String bl7oth;
}