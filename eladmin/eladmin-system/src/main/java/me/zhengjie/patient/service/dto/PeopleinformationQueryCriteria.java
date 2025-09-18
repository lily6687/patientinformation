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
import java.util.List;
import me.zhengjie.annotation.Query;
import io.swagger.annotations.ApiModelProperty;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-15
**/
@Data
public class PeopleinformationQueryCriteria{

    /** 精确 */
    @Query
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "性别（男/女）")
    private String sex;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    @ApiModelProperty(value = "出生日期（年/月/日）")
    private Timestamp brthdat;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    @ApiModelProperty(value = "体重（kg）")
    private Float wgt;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    @ApiModelProperty(value = "身高（cm）")
    private Float hgt;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "医院名称")
    private String hospital;
}