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
* @date 2025-09-11
**/
@Entity
@Data
@Table(name="abgexam")
public class Abgexam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`BGYN`")
    @ApiModelProperty(value = "血气分析（是/否）")
    private Boolean bgyn;

    @Column(name = "`BGCHKDAT`")
    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp bgchkdat;

    @Column(name = "`BG1RES`")
    @ApiModelProperty(value = "血氧饱和度-检查结果")
    private Float bg1res;

    @Column(name = "`BG1UNIT`")
    @ApiModelProperty(value = "血氧饱和度-单位")
    private String bg1unit;

    @Column(name = "`BG1CLSIG`")
    @ApiModelProperty(value = "血氧饱和度-结果判定")
    private String bg1clsig;

    @Column(name = "`BG1OTH`")
    @ApiModelProperty(value = "血氧饱和度-备注")
    private String bg1oth;

    @Column(name = "`BG2RES`")
    @ApiModelProperty(value = "血氧分压-检查结果")
    private Float bg2res;

    @Column(name = "`BG2UNIT`")
    @ApiModelProperty(value = "血氧分压-单位")
    private String bg2unit;

    @Column(name = "`BG2CLSIG`")
    @ApiModelProperty(value = "血氧分压-结果判定")
    private String bg2clsig;

    @Column(name = "`BG2OTH`")
    @ApiModelProperty(value = "血氧分压-备注")
    private String bg2oth;

    @Column(name = "`BG3RES`")
    @ApiModelProperty(value = "血液酸碱度-检查结果")
    private Float bg3res;

    @Column(name = "`BG3UNIT`")
    @ApiModelProperty(value = "血液酸碱度-单位")
    private String bg3unit;

    @Column(name = "`BG3CLSIG`")
    @ApiModelProperty(value = "血液酸碱度-结果判定")
    private String bg3clsig;

    @Column(name = "`BG3OTH`")
    @ApiModelProperty(value = "血液酸碱度-备注")
    private String bg3oth;

    public void copy(Abgexam source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
