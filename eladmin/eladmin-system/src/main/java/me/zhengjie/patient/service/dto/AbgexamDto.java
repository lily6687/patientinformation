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
public class AbgexamDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "血气分析（是/否）")
    private Boolean bgyn;

    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp bgchkdat;

    @ApiModelProperty(value = "血氧饱和度-检查结果")
    private Float bg1res;

    @ApiModelProperty(value = "血氧饱和度-单位")
    private String bg1unit;

    @ApiModelProperty(value = "血氧饱和度-结果判定")
    private String bg1clsig;

    @ApiModelProperty(value = "血氧饱和度-备注")
    private String bg1oth;

    @ApiModelProperty(value = "血氧分压-检查结果")
    private Float bg2res;

    @ApiModelProperty(value = "血氧分压-单位")
    private String bg2unit;

    @ApiModelProperty(value = "血氧分压-结果判定")
    private String bg2clsig;

    @ApiModelProperty(value = "血氧分压-备注")
    private String bg2oth;

    @ApiModelProperty(value = "血液酸碱度-检查结果")
    private Float bg3res;

    @ApiModelProperty(value = "血液酸碱度-单位")
    private String bg3unit;

    @ApiModelProperty(value = "血液酸碱度-结果判定")
    private String bg3clsig;

    @ApiModelProperty(value = "血液酸碱度-备注")
    private String bg3oth;
}