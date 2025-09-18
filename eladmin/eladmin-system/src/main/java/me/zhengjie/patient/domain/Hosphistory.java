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
* @date 2025-09-11
**/
@Entity
@Data
@Table(name="hosphistory")
public class Hosphistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`NASTDAT`")
    @ApiModelProperty(value = "发病日期（年/月/日）")
    private Timestamp nastdat;

    @Column(name = "`NADOCDAT`")
    @ApiModelProperty(value = "就诊日期（年/月/日）")
    private Timestamp nadocdat;

    @Column(name = "`NAHOSDAT`")
    @ApiModelProperty(value = "入院日期（年/月/日）")
    private Timestamp nahosdat;

    @Column(name = "`DISCYN`")
    @ApiModelProperty(value = "出院记录（是/否）")
    private Boolean discyn;

    @Column(name = "`DISCDAT`")
    @ApiModelProperty(value = "出院日期（年/月/日）")
    private Timestamp discdat;

    @Column(name = "`CC`")
    @ApiModelProperty(value = "主诉")
    private String cc;

    @Column(name = "`HPI`")
    @ApiModelProperty(value = "现病史")
    private String hpi;

    @Column(name = "`TRTDESC`")
    @ApiModelProperty(value = "诊疗过程描述")
    private String trtdesc;

    @Column(name = "`DISCSUPP`")
    @ApiModelProperty(value = "出院情况")
    private String discsupp;

    public void copy(Hosphistory source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
