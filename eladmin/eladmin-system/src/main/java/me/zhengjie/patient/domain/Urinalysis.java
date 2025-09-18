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
@Table(name="urinalysis")
public class Urinalysis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`URYN`")
    @ApiModelProperty(value = "尿常规（是/否）")
    private Boolean uryn;

    @Column(name = "`URCHKDAT`")
    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp urchkdat;

    @Column(name = "`UR1RES`")
    @ApiModelProperty(value = "尿白细胞-检查结果")
    private String ur1res;

    @Column(name = "`UR1UNIT`")
    @ApiModelProperty(value = "尿白细胞-单位")
    private String ur1unit;

    @Column(name = "`UR1CLSIG`")
    @ApiModelProperty(value = "尿白细胞-结果判定")
    private String ur1clsig;

    @Column(name = "`UR1OTH`")
    @ApiModelProperty(value = "尿白细胞-备注")
    private String ur1oth;

    @Column(name = "`UR2RES`")
    @ApiModelProperty(value = "尿红细胞-检查结果")
    private String ur2res;

    @Column(name = "`UR2UNIT`")
    @ApiModelProperty(value = "尿红细胞-单位")
    private String ur2unit;

    @Column(name = "`UR2CLSIG`")
    @ApiModelProperty(value = "尿红细胞-结果判定")
    private String ur2clsig;

    @Column(name = "`UR2OTH`")
    @ApiModelProperty(value = "尿红细胞-备注")
    private String ur2oth;

    @Column(name = "`UR3RES`")
    @ApiModelProperty(value = "尿糖-检查结果")
    private String ur3res;

    @Column(name = "`UR3UNIT`")
    @ApiModelProperty(value = "尿糖-单位")
    private String ur3unit;

    @Column(name = "`UR3CLSIG`")
    @ApiModelProperty(value = "尿糖-结果判定")
    private String ur3clsig;

    @Column(name = "`UR3OTH`")
    @ApiModelProperty(value = "尿糖-备注")
    private String ur3oth;

    public void copy(Urinalysis source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
