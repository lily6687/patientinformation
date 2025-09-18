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
* @date 2025-09-15
**/
@Data
public class PeopleinformationDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "性别（男/女）")
    private String sex;

    @ApiModelProperty(value = "出生日期（年/月/日）")
    private Timestamp brthdat;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "体重（kg）")
    private Float wgt;

    @ApiModelProperty(value = "身高（cm）")
    private Float hgt;

    @ApiModelProperty(value = "证件号码")
    private String nationalid;

    @ApiModelProperty(value = "电话号码")
    private String tel;

    @ApiModelProperty(value = "地址")
    private String addr;

    @ApiModelProperty(value = "医院名称")
    private String hospital;
}