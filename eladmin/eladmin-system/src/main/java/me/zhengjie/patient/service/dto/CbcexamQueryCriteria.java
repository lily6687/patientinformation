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
* @date 2025-09-11
**/
@Data
public class CbcexamQueryCriteria{

    /** 精确 */
    @Query
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    /** 精确 */
    @Query
    @ApiModelProperty(value = "血常规（是/否）")
    private Boolean blyn;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp blchkdat;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    @ApiModelProperty(value = "中性粒细胞计数-检查结果")
    private Float bl1res;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    @ApiModelProperty(value = "淋巴细胞计数-检查结果")
    private Float bl2res;

    /** 小于等于 */
    @Query(type = Query.Type.LESS_THAN)
    @ApiModelProperty(value = "红细胞计数-检查结果")
    private Float bl3res;

    /** 小于等于 */
    @Query(type = Query.Type.LESS_THAN)
    @ApiModelProperty(value = "血红蛋白-检查结果")
    private Float bl4res;

    /** 小于等于 */
    @Query(type = Query.Type.LESS_THAN)
    @ApiModelProperty(value = "红细胞压积-检查结果")
    private Float bl5res;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    @ApiModelProperty(value = "白细胞计数-检查结果")
    private Float bl6res;

    /** 小于等于 */
    @Query(type = Query.Type.LESS_THAN)
    @ApiModelProperty(value = "血小板计数-检查结果")
    private Float bl7res;
}