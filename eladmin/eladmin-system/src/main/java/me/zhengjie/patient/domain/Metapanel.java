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
@Table(name="metapanel")
public class Metapanel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`BCYN`")
    @ApiModelProperty(value = "血生化 （是/否）")
    private Boolean bcyn;

    @Column(name = "`BCCHKDAT`")
    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp bcchkdat;

    @Column(name = "`BC1RES`")
    @ApiModelProperty(value = "谷草转氨酶(AST) -检查结果")
    private Float bc1res;

    @Column(name = "`BLC1UNIT`")
    @ApiModelProperty(value = "谷草转氨酶(AST) -单位")
    private String blc1unit;

    @Column(name = "`BC1CLSIG`")
    @ApiModelProperty(value = "谷草转氨酶(AST) -结果判定")
    private String bc1clsig;

    @Column(name = "`BC1OTH`")
    @ApiModelProperty(value = "谷草转氨酶(AST) -备注")
    private String bc1oth;

    @Column(name = "`BC2RES`")
    @ApiModelProperty(value = "谷丙转氨酶（ALT）-检查结果")
    private Float bc2res;

    @Column(name = "`BC2UNIT`")
    @ApiModelProperty(value = "谷丙转氨酶（ALT）-单位")
    private String bc2unit;

    @Column(name = "`BC2CLSIG`")
    @ApiModelProperty(value = "谷丙转氨酶（ALT）-结果判定")
    private String bc2clsig;

    @Column(name = "`BC2OTH`")
    @ApiModelProperty(value = "谷丙转氨酶（ALT）-备注")
    private String bc2oth;

    @Column(name = "`BC3RES`")
    @ApiModelProperty(value = "肌酸激酶（CK）-检查结果")
    private Float bc3res;

    @Column(name = "`BC3UNIT`")
    @ApiModelProperty(value = "肌酸激酶（CK）单位")
    private String bc3unit;

    @Column(name = "`BC3CLSIG`")
    @ApiModelProperty(value = "肌酸激酶（CK）-结果判定")
    private String bc3clsig;

    @Column(name = "`BC3OTH`")
    @ApiModelProperty(value = "肌酸激酶（CK）-备注")
    private String bc3oth;

    @Column(name = "`BC4RES`")
    @ApiModelProperty(value = "肌酸激酶同工酶（CKMB）-检查结果")
    private Float bc4res;

    @Column(name = "`BC4UNIT`")
    @ApiModelProperty(value = "肌酸激酶同工酶（CKMB）-单位")
    private String bc4unit;

    @Column(name = "`BC4CLSIG`")
    @ApiModelProperty(value = "肌酸激酶同工酶（CKMB）-结果判定")
    private String bc4clsig;

    @Column(name = "`BC4OTH`")
    @ApiModelProperty(value = "肌酸激酶同工酶（CKMB）-备注")
    private String bc4oth;

    @Column(name = "`BC5RES`")
    @ApiModelProperty(value = "碱性磷酸酶（ALP）-检查结果")
    private Float bc5res;

    @Column(name = "`BC5UNIT`")
    @ApiModelProperty(value = "碱性磷酸酶（ALP）-单位")
    private String bc5unit;

    @Column(name = "`BC5CLSIG`")
    @ApiModelProperty(value = "碱性磷酸酶（ALP）-结果判定")
    private String bc5clsig;

    @Column(name = "`BC5OTH`")
    @ApiModelProperty(value = "碱性磷酸酶（ALP）-备注")
    private String bc5oth;

    @Column(name = "`BC6RES`")
    @ApiModelProperty(value = "乳酸脱氢酶（LDH）-检查结果")
    private Float bc6res;

    @Column(name = "`BC6UNIT`")
    @ApiModelProperty(value = "乳酸脱氢酶（LDH）-单位")
    private String bc6unit;

    @Column(name = "`BC6CLSIG`")
    @ApiModelProperty(value = "乳酸脱氢酶（LDH）-结果判定")
    private String bc6clsig;

    @Column(name = "`BC6OTH`")
    @ApiModelProperty(value = "乳酸脱氢酶（LDH）-备注")
    private String bc6oth;

    @Column(name = "`BC7RES`")
    @ApiModelProperty(value = "尿素氮（UREA）-检查结果")
    private Float bc7res;

    @Column(name = "`BC7UNIT`")
    @ApiModelProperty(value = "尿素氮（UREA）-单位")
    private String bc7unit;

    @Column(name = "`BC7CLSIG`")
    @ApiModelProperty(value = "尿素氮（UREA）-结果判定")
    private String bc7clsig;

    @Column(name = "`BC7OTH`")
    @ApiModelProperty(value = "尿素氮（UREA）-备注")
    private String bc7oth;

    @Column(name = "`BC8RES`")
    @ApiModelProperty(value = "血肌酐（CREA）-检查结果")
    private Float bc8res;

    @Column(name = "`BC8UNIT`")
    @ApiModelProperty(value = "血肌酐（CREA）-单位")
    private String bc8unit;

    @Column(name = "`BC8CLSIG`")
    @ApiModelProperty(value = "血肌酐（CREA）-结果判定")
    private String bc8clsig;

    @Column(name = "`BC8OTH`")
    @ApiModelProperty(value = "血肌酐（CREA）-备注")
    private String bc8oth;

    @Column(name = "`BC9RES`")
    @ApiModelProperty(value = "Ca2+检查结果")
    private Float bc9res;

    @Column(name = "`BC9UNIT`")
    @ApiModelProperty(value = "Ca2+单位")
    private String bc9unit;

    @Column(name = "`BC9CLSIG`")
    @ApiModelProperty(value = "Ca2+结果判定")
    private String bc9clsig;

    @Column(name = "`BC9OTH`")
    @ApiModelProperty(value = "Ca2+备注")
    private String bc9oth;

    @Column(name = "`BC10RES`")
    @ApiModelProperty(value = "Na+检查结果")
    private Float bc10res;

    @Column(name = "`BC10UNI`")
    @ApiModelProperty(value = "Na+单位")
    private String bc10uni;

    @Column(name = "`BC10CLSI`")
    @ApiModelProperty(value = "Na+结果判定")
    private String bc10clsi;

    @Column(name = "`BC10REM`")
    @ApiModelProperty(value = "Na+备注")
    private String bc10rem;

    @Column(name = "`BC11RES`")
    @ApiModelProperty(value = "Cl-检查结果")
    private Float bc11res;

    @Column(name = "`BC11UNI`")
    @ApiModelProperty(value = "Cl-单位")
    private String bc11uni;

    @Column(name = "`BC11CLSI`")
    @ApiModelProperty(value = "Cl-结果判定")
    private String bc11clsi;

    @Column(name = "`BC11REM`")
    @ApiModelProperty(value = "Cl-备注")
    private String bc11rem;

    @Column(name = "`BC12RES`")
    @ApiModelProperty(value = "K+检查结果")
    private Float bc12res;

    @Column(name = "`BC12UNI`")
    @ApiModelProperty(value = "K+单位")
    private String bc12uni;

    @Column(name = "`BC12CLSI`")
    @ApiModelProperty(value = "K+结果判定")
    private String bc12clsi;

    @Column(name = "`BC12REM`")
    @ApiModelProperty(value = "K+备注")
    private String bc12rem;

    public void copy(Metapanel source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
