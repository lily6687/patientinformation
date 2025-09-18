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
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author zhengjie
* @date 2025-09-16
**/
@Entity
@Data
@Table(name="historyallergy")
public class Historyallergy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`DIAG`")
    @ApiModelProperty(value = "诊断")
    private String diag;

    @Column(name = "`MHYN`")
    @ApiModelProperty(value = "既往病史（是/否）")
    private Boolean mhyn;

    @Column(name = "`MHNAM`")
    @ApiModelProperty(value = "症状/疾病名称")
    private String mhnam;

    @Column(name = "`AHYN`")
    @ApiModelProperty(value = "过敏史（是/否）")
    private Boolean ahyn;

    @Column(name = "`AHALRGN`")
    @ApiModelProperty(value = "过敏原")
    private String ahalrgn;

    @Column(name = "`CM1YN`")
    @ApiModelProperty(value = "合并疾病（是/否）")
    private Boolean cm1yn;

    @Column(name = "`CM1TRT`")
    @ApiModelProperty(value = "合并症名称")
    private String cm1trt;

    @Column(name = "`MHSMOKYN`")
    @ApiModelProperty(value = "从不吸烟/已经戒烟/现在吸烟")
    private Boolean mhsmokyn;

    @Column(name = "`MHQSMDUR`")
    @ApiModelProperty(value = "戒烟_____年")
    private Integer mhqsmdur;

    @Column(name = "`MHSMKDUR`")
    @ApiModelProperty(value = "吸烟_____年")
    private Integer mhsmkdur;

    public void copy(Historyallergy source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
