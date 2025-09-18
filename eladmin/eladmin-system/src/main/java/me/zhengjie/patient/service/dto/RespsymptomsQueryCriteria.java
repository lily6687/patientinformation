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
import java.util.List;
import me.zhengjie.annotation.Query;
import io.swagger.annotations.ApiModelProperty;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-16
**/
@Data
public class RespsymptomsQueryCriteria{

    /** 精确 */
    @Query
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "呼吸道症状（是/否）")
    private Boolean resptsyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "咳嗽（是/否）")
    private Boolean coughyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "干咳（是/否）")
    private Boolean drycouyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "乏力（是/否）")
    private Boolean fatgyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "鼻塞 （是/否）")
    private Boolean stufnsyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "流涕（是/否）")
    private Boolean runnsyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "厌食 （是/否）")
    private Boolean anrxyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "腹泻 （是/否）")
    private Boolean diarhyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "气喘（是/否）")
    private Boolean astmyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "畏寒（是/否）")
    private Boolean chilyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "咽痛（是/否）")
    private Boolean sothryn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "呼吸（次/分）")
    private Integer brethfrq;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "体温（是/否）")
    private Boolean tempyn;
}