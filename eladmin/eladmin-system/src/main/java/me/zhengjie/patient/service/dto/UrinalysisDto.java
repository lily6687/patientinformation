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
public class UrinalysisDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "尿常规（是/否）")
    private Boolean uryn;

    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp urchkdat;

    @ApiModelProperty(value = "尿白细胞-检查结果")
    private String ur1res;

    @ApiModelProperty(value = "尿白细胞-单位")
    private String ur1unit;

    @ApiModelProperty(value = "尿白细胞-结果判定")
    private String ur1clsig;

    @ApiModelProperty(value = "尿白细胞-备注")
    private String ur1oth;

    @ApiModelProperty(value = "尿红细胞-检查结果")
    private String ur2res;

    @ApiModelProperty(value = "尿红细胞-单位")
    private String ur2unit;

    @ApiModelProperty(value = "尿红细胞-结果判定")
    private String ur2clsig;

    @ApiModelProperty(value = "尿红细胞-备注")
    private String ur2oth;

    @ApiModelProperty(value = "尿糖-检查结果")
    private String ur3res;

    @ApiModelProperty(value = "尿糖-单位")
    private String ur3unit;

    @ApiModelProperty(value = "尿糖-结果判定")
    private String ur3clsig;

    @ApiModelProperty(value = "尿糖-备注")
    private String ur3oth;
}