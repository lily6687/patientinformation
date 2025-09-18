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
public class MetapanelDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "血生化 （是/否）")
    private Boolean bcyn;

    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp bcchkdat;

    @ApiModelProperty(value = "谷草转氨酶(AST) -检查结果")
    private Float bc1res;

    @ApiModelProperty(value = "谷草转氨酶(AST) -单位")
    private String blc1unit;

    @ApiModelProperty(value = "谷草转氨酶(AST) -结果判定")
    private String bc1clsig;

    @ApiModelProperty(value = "谷草转氨酶(AST) -备注")
    private String bc1oth;

    @ApiModelProperty(value = "谷丙转氨酶（ALT）-检查结果")
    private Float bc2res;

    @ApiModelProperty(value = "谷丙转氨酶（ALT）-单位")
    private String bc2unit;

    @ApiModelProperty(value = "谷丙转氨酶（ALT）-结果判定")
    private String bc2clsig;

    @ApiModelProperty(value = "谷丙转氨酶（ALT）-备注")
    private String bc2oth;

    @ApiModelProperty(value = "肌酸激酶（CK）-检查结果")
    private Float bc3res;

    @ApiModelProperty(value = "肌酸激酶（CK）单位")
    private String bc3unit;

    @ApiModelProperty(value = "肌酸激酶（CK）-结果判定")
    private String bc3clsig;

    @ApiModelProperty(value = "肌酸激酶（CK）-备注")
    private String bc3oth;

    @ApiModelProperty(value = "肌酸激酶同工酶（CKMB）-检查结果")
    private Float bc4res;

    @ApiModelProperty(value = "肌酸激酶同工酶（CKMB）-单位")
    private String bc4unit;

    @ApiModelProperty(value = "肌酸激酶同工酶（CKMB）-结果判定")
    private String bc4clsig;

    @ApiModelProperty(value = "肌酸激酶同工酶（CKMB）-备注")
    private String bc4oth;

    @ApiModelProperty(value = "碱性磷酸酶（ALP）-检查结果")
    private Float bc5res;

    @ApiModelProperty(value = "碱性磷酸酶（ALP）-单位")
    private String bc5unit;

    @ApiModelProperty(value = "碱性磷酸酶（ALP）-结果判定")
    private String bc5clsig;

    @ApiModelProperty(value = "碱性磷酸酶（ALP）-备注")
    private String bc5oth;

    @ApiModelProperty(value = "乳酸脱氢酶（LDH）-检查结果")
    private Float bc6res;

    @ApiModelProperty(value = "乳酸脱氢酶（LDH）-单位")
    private String bc6unit;

    @ApiModelProperty(value = "乳酸脱氢酶（LDH）-结果判定")
    private String bc6clsig;

    @ApiModelProperty(value = "乳酸脱氢酶（LDH）-备注")
    private String bc6oth;

    @ApiModelProperty(value = "尿素氮（UREA）-检查结果")
    private Float bc7res;

    @ApiModelProperty(value = "尿素氮（UREA）-单位")
    private String bc7unit;

    @ApiModelProperty(value = "尿素氮（UREA）-结果判定")
    private String bc7clsig;

    @ApiModelProperty(value = "尿素氮（UREA）-备注")
    private String bc7oth;

    @ApiModelProperty(value = "血肌酐（CREA）-检查结果")
    private Float bc8res;

    @ApiModelProperty(value = "血肌酐（CREA）-单位")
    private String bc8unit;

    @ApiModelProperty(value = "血肌酐（CREA）-结果判定")
    private String bc8clsig;

    @ApiModelProperty(value = "血肌酐（CREA）-备注")
    private String bc8oth;

    @ApiModelProperty(value = "Ca2+检查结果")
    private Float bc9res;

    @ApiModelProperty(value = "Ca2+单位")
    private String bc9unit;

    @ApiModelProperty(value = "Ca2+结果判定")
    private String bc9clsig;

    @ApiModelProperty(value = "Ca2+备注")
    private String bc9oth;

    @ApiModelProperty(value = "Na+检查结果")
    private Float bc10res;

    @ApiModelProperty(value = "Na+单位")
    private String bc10uni;

    @ApiModelProperty(value = "Na+结果判定")
    private String bc10clsi;

    @ApiModelProperty(value = "Na+备注")
    private String bc10rem;

    @ApiModelProperty(value = "Cl-检查结果")
    private Float bc11res;

    @ApiModelProperty(value = "Cl-单位")
    private String bc11uni;

    @ApiModelProperty(value = "Cl-结果判定")
    private String bc11clsi;

    @ApiModelProperty(value = "Cl-备注")
    private String bc11rem;

    @ApiModelProperty(value = "K+检查结果")
    private Float bc12res;

    @ApiModelProperty(value = "K+单位")
    private String bc12uni;

    @ApiModelProperty(value = "K+结果判定")
    private String bc12clsi;

    @ApiModelProperty(value = "K+备注")
    private String bc12rem;
}