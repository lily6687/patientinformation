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
@Table(name="respsymptoms")
public class Respsymptoms implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`RESPTSYN`")
    @ApiModelProperty(value = "呼吸道症状（是/否）")
    private Boolean resptsyn;

    @Column(name = "`RECHKDAT`")
    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp rechkdat;

    @Column(name = "`COUGHYN`")
    @ApiModelProperty(value = "咳嗽（是/否）")
    private Boolean coughyn;

    @Column(name = "`DRYCOUYN`")
    @ApiModelProperty(value = "干咳（是/否）")
    private Boolean drycouyn;

    @Column(name = "`FATGYN`")
    @ApiModelProperty(value = "乏力（是/否）")
    private Boolean fatgyn;

    @Column(name = "`STUFNSYN`")
    @ApiModelProperty(value = "鼻塞 （是/否）")
    private Boolean stufnsyn;

    @Column(name = "`RUNNSYN`")
    @ApiModelProperty(value = "流涕（是/否）")
    private Boolean runnsyn;

    @Column(name = "`ANRXYN`")
    @ApiModelProperty(value = "厌食 （是/否）")
    private Boolean anrxyn;

    @Column(name = "`DIARHYN`")
    @ApiModelProperty(value = "腹泻 （是/否）")
    private Boolean diarhyn;

    @Column(name = "`ASTMYN`")
    @ApiModelProperty(value = "气喘（是/否）")
    private Boolean astmyn;

    @Column(name = "`CHILYN`")
    @ApiModelProperty(value = "畏寒（是/否）")
    private Boolean chilyn;

    @Column(name = "`SOTHRYN`")
    @ApiModelProperty(value = "咽痛（是/否）")
    private Boolean sothryn;

    @Column(name = "`BRCHKDAT`")
    @ApiModelProperty(value = "检查日期（年/月/日）.1")
    private Timestamp brchkdat;

    @Column(name = "`BRETHFRQ`")
    @ApiModelProperty(value = "呼吸（次/分）")
    private Integer brethfrq;

    @Column(name = "`TEMPYN`")
    @ApiModelProperty(value = "体温（是/否）")
    private Boolean tempyn;

    @Column(name = "`TECHKDAT`")
    @ApiModelProperty(value = "检查日期（年/月/日）.2")
    private Timestamp techkdat;

    @Column(name = "`TEMP`")
    @ApiModelProperty(value = "体温（℃）")
    private Float temp;

    public void copy(Respsymptoms source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
