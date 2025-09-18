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
public class RespsymptomsDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "呼吸道症状（是/否）")
    private Boolean resptsyn;

    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp rechkdat;

    @ApiModelProperty(value = "咳嗽（是/否）")
    private Boolean coughyn;

    @ApiModelProperty(value = "干咳（是/否）")
    private Boolean drycouyn;

    @ApiModelProperty(value = "乏力（是/否）")
    private Boolean fatgyn;

    @ApiModelProperty(value = "鼻塞 （是/否）")
    private Boolean stufnsyn;

    @ApiModelProperty(value = "流涕（是/否）")
    private Boolean runnsyn;

    @ApiModelProperty(value = "厌食 （是/否）")
    private Boolean anrxyn;

    @ApiModelProperty(value = "腹泻 （是/否）")
    private Boolean diarhyn;

    @ApiModelProperty(value = "气喘（是/否）")
    private Boolean astmyn;

    @ApiModelProperty(value = "畏寒（是/否）")
    private Boolean chilyn;

    @ApiModelProperty(value = "咽痛（是/否）")
    private Boolean sothryn;

    @ApiModelProperty(value = "检查日期（年/月/日）.1")
    private Timestamp brchkdat;

    @ApiModelProperty(value = "呼吸（次/分）")
    private Integer brethfrq;

    @ApiModelProperty(value = "体温（是/否）")
    private Boolean tempyn;

    @ApiModelProperty(value = "检查日期（年/月/日）.2")
    private Timestamp techkdat;

    @ApiModelProperty(value = "体温（℃）")
    private Float temp;
}