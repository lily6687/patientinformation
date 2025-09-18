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
* @date 2025-09-16
**/
@Data
public class ImmutestsDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "免疫学指标（是/否）")
    private Boolean imyn;

    @ApiModelProperty(value = "C-反应蛋白-检查日期（年/月/日）")
    private Timestamp im1dat;

    @ApiModelProperty(value = "C-反应蛋白-检查结果")
    private Float im1res;

    @ApiModelProperty(value = "C-反应蛋白-单位")
    private String im1unit;

    @ApiModelProperty(value = "C-反应蛋白-结果判定")
    private String im1clsig;

    @ApiModelProperty(value = "C-反应蛋白-备注")
    private String im1oth;

    @ApiModelProperty(value = "血沉-检查日期（年/月/日）")
    private Timestamp im2dat;

    @ApiModelProperty(value = "血沉-检查结果")
    private Float im2res;

    @ApiModelProperty(value = "血沉-单位")
    private String im2unit;

    @ApiModelProperty(value = "血沉-结果判定")
    private String im2clsig;

    @ApiModelProperty(value = "血沉-备注")
    private String im2oth;

    @ApiModelProperty(value = "降钙素原-检查日期（年/月/日）")
    private Timestamp im3dat;

    @ApiModelProperty(value = "降钙素原-检查结果")
    private Float im3res;

    @ApiModelProperty(value = "降钙素原-单位")
    private String im3unit;

    @ApiModelProperty(value = "降钙素原-结果判定")
    private String im3clsig;

    @ApiModelProperty(value = "降钙素原-备注")
    private String im3oth;
}