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
public class TreatmentothermedicationDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "是否使用其他药物（是/否）")
    private Boolean cm2yn;

    @ApiModelProperty(value = "西药药物类型")
    private String cm2cat;

    @ApiModelProperty(value = "退烧药")
    private String cm2feb;

    @ApiModelProperty(value = "抗病毒药")
    private String cm2anvi;

    @ApiModelProperty(value = "抗生素")
    private String cm2anbio;

    @ApiModelProperty(value = "常规补液治疗")
    private String ce2reflu;

    @ApiModelProperty(value = "激素")
    private String cm2hormo;

    @ApiModelProperty(value = "其他药物")
    private String ce2other;

    @ApiModelProperty(value = "请描述")
    private String cm2odesc;

    @ApiModelProperty(value = "备注")
    private String cm2oth;

    @ApiModelProperty(value = "中医药名称")
    private String cm3trt;

    @ApiModelProperty(value = "其他_请注明：")
    private String cm3oth;

    @ApiModelProperty(value = "是否有更改治疗方案")
    private Boolean cmchyn;

    @ApiModelProperty(value = "是_更改时间：_____")
    private Timestamp cmchdat;

    @ApiModelProperty(value = "是_请描述更改后治疗方案：")
    private String cmchoth;
}