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
public class CombinedmedicationDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "合并用药（是/否）")
    private Boolean cm3yn;

    @ApiModelProperty(value = "药物名称")
    private String cm3trt;

    @ApiModelProperty(value = "开始日期")
    private Timestamp cm3stdat;

    @ApiModelProperty(value = "结束日期")
    private Timestamp cm3endat;

    @ApiModelProperty(value = "用药目的")
    private String cm3ord;

    @ApiModelProperty(value = "剂量")
    private String cm3dose;

    @ApiModelProperty(value = "给药方式")
    private String cm3route;

    @ApiModelProperty(value = "备注")
    private String cm3oth;
}