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
@Table(name="immutests")
public class Immutests implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`IMYN`")
    @ApiModelProperty(value = "免疫学指标（是/否）")
    private Boolean imyn;

    @Column(name = "`IM1DAT`")
    @ApiModelProperty(value = "C-反应蛋白-检查日期（年/月/日）")
    private Timestamp im1dat;

    @Column(name = "`IM1RES`")
    @ApiModelProperty(value = "C-反应蛋白-检查结果")
    private Float im1res;

    @Column(name = "`IM1UNIT`")
    @ApiModelProperty(value = "C-反应蛋白-单位")
    private String im1unit;

    @Column(name = "`IM1CLSIG`")
    @ApiModelProperty(value = "C-反应蛋白-结果判定")
    private String im1clsig;

    @Column(name = "`IM1OTH`")
    @ApiModelProperty(value = "C-反应蛋白-备注")
    private String im1oth;

    @Column(name = "`IM2DAT`")
    @ApiModelProperty(value = "血沉-检查日期（年/月/日）")
    private Timestamp im2dat;

    @Column(name = "`IM2RES`")
    @ApiModelProperty(value = "血沉-检查结果")
    private Float im2res;

    @Column(name = "`IM2UNIT`")
    @ApiModelProperty(value = "血沉-单位")
    private String im2unit;

    @Column(name = "`IM2CLSIG`")
    @ApiModelProperty(value = "血沉-结果判定")
    private String im2clsig;

    @Column(name = "`IM2OTH`")
    @ApiModelProperty(value = "血沉-备注")
    private String im2oth;

    @Column(name = "`IM3DAT`")
    @ApiModelProperty(value = "降钙素原-检查日期（年/月/日）")
    private Timestamp im3dat;

    @Column(name = "`IM3RES`")
    @ApiModelProperty(value = "降钙素原-检查结果")
    private Float im3res;

    @Column(name = "`IM3UNIT`")
    @ApiModelProperty(value = "降钙素原-单位")
    private String im3unit;

    @Column(name = "`IM3CLSIG`")
    @ApiModelProperty(value = "降钙素原-结果判定")
    private String im3clsig;

    @Column(name = "`IM3OTH`")
    @ApiModelProperty(value = "降钙素原-备注")
    private String im3oth;

    public void copy(Immutests source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
