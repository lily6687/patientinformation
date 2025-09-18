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
* @date 2025-09-11
**/
@Data
public class PatientoutcomeQueryCriteria{

    /** 精确 */
    @Query
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "治疗结局（是/否）")
    private Boolean trtoutyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "痊愈（是/否）")
    private Boolean curedyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "病情加重（是/否）")
    private Boolean worseyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "转院治疗（是/否）")
    private Boolean trftrtyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "转入ICU（是/否）")
    private Boolean icuyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "呼吸衰竭（是/否）")
    private Boolean rpfyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "死亡（是/否）")
    private Boolean deadyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "休克（是/否）")
    private Boolean shokyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "是否为核酸检测（是/否）")
    private Boolean natyn;
}