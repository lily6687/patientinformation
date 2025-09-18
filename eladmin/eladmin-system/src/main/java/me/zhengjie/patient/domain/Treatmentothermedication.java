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
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author zhengjie
* @date 2025-09-16
**/
@Entity
@Data
@Table(name="treatmentothermedication")
public class Treatmentothermedication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`CM2YN`")
    @ApiModelProperty(value = "是否使用其他药物（是/否）")
    private Boolean cm2yn;

    @Column(name = "`CM2CAT`")
    @ApiModelProperty(value = "西药药物类型")
    private String cm2cat;

    @Column(name = "`CM2FEB`")
    @ApiModelProperty(value = "退烧药")
    private String cm2feb;

    @Column(name = "`CM2ANVI`")
    @ApiModelProperty(value = "抗病毒药")
    private String cm2anvi;

    @Column(name = "`CM2ANBIO`")
    @ApiModelProperty(value = "抗生素")
    private String cm2anbio;

    @Column(name = "`CE2REFLU`")
    @ApiModelProperty(value = "常规补液治疗")
    private String ce2reflu;

    @Column(name = "`CM2HORMO`")
    @ApiModelProperty(value = "激素")
    private String cm2hormo;

    @Column(name = "`CE2OTHER`")
    @ApiModelProperty(value = "其他药物")
    private String ce2other;

    @Column(name = "`CM2ODESC`")
    @ApiModelProperty(value = "请描述")
    private String cm2odesc;

    @Column(name = "`CM2OTH`")
    @ApiModelProperty(value = "备注")
    private String cm2oth;

    @Column(name = "`CM3TRT`")
    @ApiModelProperty(value = "中医药名称")
    private String cm3trt;

    @Column(name = "`CM3OTH`")
    @ApiModelProperty(value = "其他_请注明：")
    private String cm3oth;

    @Column(name = "`CMCHYN`")
    @ApiModelProperty(value = "是否有更改治疗方案")
    private Boolean cmchyn;

    @Column(name = "`CMCHDAT`")
    @ApiModelProperty(value = "是_更改时间：_____")
    private Timestamp cmchdat;

    @Column(name = "`CMCHOTH`")
    @ApiModelProperty(value = "是_请描述更改后治疗方案：")
    private String cmchoth;

    public void copy(Treatmentothermedication source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
