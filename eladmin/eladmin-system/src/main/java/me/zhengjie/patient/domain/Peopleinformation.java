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
package me.zhengjie.patient.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author zhengjie
* @date 2025-09-15
**/
@Entity
@Data
@Table(name="peopleinformation")
public class Peopleinformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`NAME`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "姓名")
    private String name;

    @Column(name = "`SUBJID`",unique = true,nullable = false)
    @NotBlank
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`SEX`")
    @ApiModelProperty(value = "性别（男/女）")
    private String sex;

    @Column(name = "`BRTHDAT`")
    @ApiModelProperty(value = "出生日期（年/月/日）")
    private Timestamp brthdat;

    @Column(name = "`AGE`")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @Column(name = "`WGT`")
    @ApiModelProperty(value = "体重（kg）")
    private Float wgt;

    @Column(name = "`HGT`")
    @ApiModelProperty(value = "身高（cm）")
    private Float hgt;

    @Column(name = "`NATIONALID`")
    @ApiModelProperty(value = "证件号码")
    private String nationalid;

    @Column(name = "`TEL`")
    @ApiModelProperty(value = "电话号码")
    private String tel;

    @Column(name = "`ADDR`")
    @ApiModelProperty(value = "地址")
    private String addr;

    @Column(name = "`HOSPITAL`")
    @ApiModelProperty(value = "医院名称")
    private String hospital;

    public void copy(Peopleinformation source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
