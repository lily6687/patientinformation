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
public class HistoryallergyQueryCriteria{

    /** 精确 */
    @Query
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "既往病史（是/否）")
    private Boolean mhyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "过敏史（是/否）")
    private Boolean ahyn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "合并疾病（是/否）")
    private Boolean cm1yn;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "从不吸烟/已经戒烟/现在吸烟")
    private Boolean mhsmokyn;
}