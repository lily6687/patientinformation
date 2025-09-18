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
public class SpecialtreatmentDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "是否接收特殊治疗")
    private Boolean styn;

    @ApiModelProperty(value = "机械通气（是/否）")
    private Boolean mvyn;

    @ApiModelProperty(value = "开始日期")
    private Timestamp mvstdat;

    @ApiModelProperty(value = "结束日期")
    private Timestamp mvendat;

    @ApiModelProperty(value = "详细治疗信息")
    private String stoth;

    @ApiModelProperty(value = "ECMO（是/否）")
    private Boolean emyn;

    @ApiModelProperty(value = "开始日期.1")
    private Timestamp emstdat;

    @ApiModelProperty(value = "结束日期.1")
    private Timestamp emendat;

    @ApiModelProperty(value = "详细治疗信息.1")
    private String emoth;

    @ApiModelProperty(value = "高流量鼻导管氧（是/否）")
    private Boolean hfncoyn;

    @ApiModelProperty(value = "开始日期.2")
    private Timestamp hfstdat;

    @ApiModelProperty(value = "结束日期.2")
    private Timestamp hfendat;

    @ApiModelProperty(value = "详细治疗信息.2")
    private String hfncooth;

    @ApiModelProperty(value = "无创通气（是/否）")
    private Boolean nonivyn;

    @ApiModelProperty(value = "开始日期.3")
    private Timestamp nosatdat;

    @ApiModelProperty(value = "结束日期.3")
    private Timestamp noendat;

    @ApiModelProperty(value = "详细治疗信息.3")
    private String nonivoth;

    @ApiModelProperty(value = "持续性肾脏替代疗法（CRRT）（是/否）")
    private Boolean crrtyn;

    @ApiModelProperty(value = "开始日期.4")
    private Timestamp crstdat;

    @ApiModelProperty(value = "结束日期.4")
    private Timestamp crendat;

    @ApiModelProperty(value = "详细治疗信息.4")
    private String crrtoth;

    @ApiModelProperty(value = "康复者血浆治疗（是/否）")
    private Boolean rptyn;

    @ApiModelProperty(value = "开始日期.5")
    private Timestamp rpstdat;

    @ApiModelProperty(value = "结束日期.5")
    private Timestamp rpendat;

    @ApiModelProperty(value = "详细治疗信息.5")
    private String rptoth;

    @ApiModelProperty(value = "血液净化治疗（是/否）")
    private Boolean bptyn;

    @ApiModelProperty(value = "开始日期.6")
    private Timestamp bpstdat;

    @ApiModelProperty(value = "结束日期.6")
    private Timestamp bpendat;

    @ApiModelProperty(value = "详细治疗信息.6")
    private String bptoth;
}