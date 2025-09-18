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
public class SpecialtreatmentQueryCriteria{

    /** 精确 */
    @Query
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "是否接收特殊治疗")
    private Boolean styn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "机械通气（是/否）")
    private Boolean mvyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "ECMO（是/否）")
    private Boolean emyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "高流量鼻导管氧（是/否）")
    private Boolean hfncoyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "无创通气（是/否）")
    private Boolean nonivyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "持续性肾脏替代疗法（CRRT）（是/否）")
    private Boolean crrtyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "康复者血浆治疗（是/否）")
    private Boolean rptyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "血液净化治疗（是/否）")
    private Boolean bptyn;
}