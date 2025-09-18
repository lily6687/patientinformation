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
@Table(name="specialtreatment")
public class Specialtreatment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`STYN`")
    @ApiModelProperty(value = "是否接收特殊治疗")
    private Boolean styn;

    @Column(name = "`MVYN`")
    @ApiModelProperty(value = "机械通气（是/否）")
    private Boolean mvyn;

    @Column(name = "`MVSTDAT`")
    @ApiModelProperty(value = "开始日期")
    private Timestamp mvstdat;

    @Column(name = "`MVENDAT`")
    @ApiModelProperty(value = "结束日期")
    private Timestamp mvendat;

    @Column(name = "`STOTH`")
    @ApiModelProperty(value = "详细治疗信息")
    private String stoth;

    @Column(name = "`EMYN`")
    @ApiModelProperty(value = "ECMO（是/否）")
    private Boolean emyn;

    @Column(name = "`EMSTDAT`")
    @ApiModelProperty(value = "开始日期.1")
    private Timestamp emstdat;

    @Column(name = "`EMENDAT`")
    @ApiModelProperty(value = "结束日期.1")
    private Timestamp emendat;

    @Column(name = "`EMOTH`")
    @ApiModelProperty(value = "详细治疗信息.1")
    private String emoth;

    @Column(name = "`HFNCOYN`")
    @ApiModelProperty(value = "高流量鼻导管氧（是/否）")
    private Boolean hfncoyn;

    @Column(name = "`HFSTDAT`")
    @ApiModelProperty(value = "开始日期.2")
    private Timestamp hfstdat;

    @Column(name = "`HFENDAT`")
    @ApiModelProperty(value = "结束日期.2")
    private Timestamp hfendat;

    @Column(name = "`HFNCOOTH`")
    @ApiModelProperty(value = "详细治疗信息.2")
    private String hfncooth;

    @Column(name = "`NONIVYN`")
    @ApiModelProperty(value = "无创通气（是/否）")
    private Boolean nonivyn;

    @Column(name = "`NOSATDAT`")
    @ApiModelProperty(value = "开始日期.3")
    private Timestamp nosatdat;

    @Column(name = "`NOENDAT`")
    @ApiModelProperty(value = "结束日期.3")
    private Timestamp noendat;

    @Column(name = "`NONIVOTH`")
    @ApiModelProperty(value = "详细治疗信息.3")
    private String nonivoth;

    @Column(name = "`CRRTYN`")
    @ApiModelProperty(value = "持续性肾脏替代疗法（CRRT）（是/否）")
    private Boolean crrtyn;

    @Column(name = "`CRSTDAT`")
    @ApiModelProperty(value = "开始日期.4")
    private Timestamp crstdat;

    @Column(name = "`CRENDAT`")
    @ApiModelProperty(value = "结束日期.4")
    private Timestamp crendat;

    @Column(name = "`CRRTOTH`")
    @ApiModelProperty(value = "详细治疗信息.4")
    private String crrtoth;

    @Column(name = "`RPTYN`")
    @ApiModelProperty(value = "康复者血浆治疗（是/否）")
    private Boolean rptyn;

    @Column(name = "`RPSTDAT`")
    @ApiModelProperty(value = "开始日期.5")
    private Timestamp rpstdat;

    @Column(name = "`RPENDAT`")
    @ApiModelProperty(value = "结束日期.5")
    private Timestamp rpendat;

    @Column(name = "`RPTOTH`")
    @ApiModelProperty(value = "详细治疗信息.5")
    private String rptoth;

    @Column(name = "`BPTYN`")
    @ApiModelProperty(value = "血液净化治疗（是/否）")
    private Boolean bptyn;

    @Column(name = "`BPSTDAT`")
    @ApiModelProperty(value = "开始日期.6")
    private Timestamp bpstdat;

    @Column(name = "`BPENDAT`")
    @ApiModelProperty(value = "结束日期.6")
    private Timestamp bpendat;

    @Column(name = "`BPTOTH`")
    @ApiModelProperty(value = "详细治疗信息.6")
    private String bptoth;

    public void copy(Specialtreatment source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
