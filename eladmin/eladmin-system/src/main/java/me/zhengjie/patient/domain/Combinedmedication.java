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
@Table(name="combinedmedication")
public class Combinedmedication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`CM3YN`")
    @ApiModelProperty(value = "合并用药（是/否）")
    private Boolean cm3yn;

    @Column(name = "`CM3TRT`")
    @ApiModelProperty(value = "药物名称")
    private String cm3trt;

    @Column(name = "`CM3STDAT`")
    @ApiModelProperty(value = "开始日期")
    private Timestamp cm3stdat;

    @Column(name = "`CM3ENDAT`")
    @ApiModelProperty(value = "结束日期")
    private Timestamp cm3endat;

    @Column(name = "`CM3ORD`")
    @ApiModelProperty(value = "用药目的")
    private String cm3ord;

    @Column(name = "`CM3DOSE`")
    @ApiModelProperty(value = "剂量")
    private String cm3dose;

    @Column(name = "`CM3ROUTE`")
    @ApiModelProperty(value = "给药方式")
    private String cm3route;

    @Column(name = "`CM3OTH`")
    @ApiModelProperty(value = "备注")
    private String cm3oth;

    public void copy(Combinedmedication source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
