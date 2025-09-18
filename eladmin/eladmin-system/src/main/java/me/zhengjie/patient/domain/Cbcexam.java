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
@Table(name="cbcexam")
public class Cbcexam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`BLYN`")
    @ApiModelProperty(value = "血常规（是/否）")
    private Boolean blyn;

    @Column(name = "`BLCHKDAT`")
    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp blchkdat;

    @Column(name = "`BL1RES`")
    @ApiModelProperty(value = "中性粒细胞计数-检查结果")
    private Float bl1res;

    @Column(name = "`BL1UNIT`")
    @ApiModelProperty(value = "中性粒细胞计数-单位")
    private String bl1unit;

    @Column(name = "`BL1CLSIG`")
    @ApiModelProperty(value = "中性粒细胞计数-结果判定")
    private String bl1clsig;

    @Column(name = "`BL1OTH`")
    @ApiModelProperty(value = "中性粒细胞计数备注")
    private String bl1oth;

    @Column(name = "`BL2RES`")
    @ApiModelProperty(value = "淋巴细胞计数-检查结果")
    private Float bl2res;

    @Column(name = "`BL2UNIT`")
    @ApiModelProperty(value = "淋巴细胞计数-单位")
    private String bl2unit;

    @Column(name = "`BL2CLSIG`")
    @ApiModelProperty(value = "淋巴细胞计数-结果判定")
    private String bl2clsig;

    @Column(name = "`BL2OTH`")
    @ApiModelProperty(value = "淋巴细胞计数-备注")
    private String bl2oth;

    @Column(name = "`BL3RES`")
    @ApiModelProperty(value = "红细胞计数-检查结果")
    private Float bl3res;

    @Column(name = "`BL3UNIT`")
    @ApiModelProperty(value = "红细胞计数-单位")
    private String bl3unit;

    @Column(name = "`BL3CLSIG`")
    @ApiModelProperty(value = "红细胞计数-结果判定")
    private String bl3clsig;

    @Column(name = "`BL3OTH`")
    @ApiModelProperty(value = "红细胞计数-备注")
    private String bl3oth;

    @Column(name = "`BL4RES`")
    @ApiModelProperty(value = "血红蛋白-检查结果")
    private Float bl4res;

    @Column(name = "`BL4UNIT`")
    @ApiModelProperty(value = "血红蛋白-单位")
    private String bl4unit;

    @Column(name = "`BL4CLSIG`")
    @ApiModelProperty(value = "血红蛋白-结果判定")
    private String bl4clsig;

    @Column(name = "`BL4OTH`")
    @ApiModelProperty(value = "备注")
    private String bl4oth;

    @Column(name = "`BL5RES`")
    @ApiModelProperty(value = "红细胞压积-检查结果")
    private Float bl5res;

    @Column(name = "`BL5UNIT`")
    @ApiModelProperty(value = "红细胞压积-单位")
    private String bl5unit;

    @Column(name = "`BL5CLSIG`")
    @ApiModelProperty(value = "红细胞压积-结果判定")
    private String bl5clsig;

    @Column(name = "`BL5OTH`")
    @ApiModelProperty(value = "备注.1")
    private String bl5oth;

    @Column(name = "`BL6RES`")
    @ApiModelProperty(value = "白细胞计数-检查结果")
    private Float bl6res;

    @Column(name = "`BL6UNIT`")
    @ApiModelProperty(value = "白细胞计数-单位")
    private String bl6unit;

    @Column(name = "`BL6CLSIG`")
    @ApiModelProperty(value = "白细胞计数-结果判定")
    private String bl6clsig;

    @Column(name = "`BL6OTH`")
    @ApiModelProperty(value = "白细胞计数-备注")
    private String bl6oth;

    @Column(name = "`BL7RES`")
    @ApiModelProperty(value = "血小板计数-检查结果")
    private Float bl7res;

    @Column(name = "`BL7UNIT`")
    @ApiModelProperty(value = "血小板计数-单位")
    private String bl7unit;

    @Column(name = "`BL7CLSIG`")
    @ApiModelProperty(value = "血小板计数-结果判定")
    private String bl7clsig;

    @Column(name = "`BL7OTH`")
    @ApiModelProperty(value = "血小板计数-备注")
    private String bl7oth;

    public void copy(Cbcexam source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
