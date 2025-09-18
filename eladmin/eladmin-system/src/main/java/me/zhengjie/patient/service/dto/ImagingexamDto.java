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
public class ImagingexamDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "影像学检查（是/否）")
    private Boolean michkyn;

    @ApiModelProperty(value = "检查类型")
    private String mitype;

    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp michkdat;

    @ApiModelProperty(value = "检查结果（正常/异常）")
    private String mires;

    @ApiModelProperty(value = "备注")
    private String mioth;
}