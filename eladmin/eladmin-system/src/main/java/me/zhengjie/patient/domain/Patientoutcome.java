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
@Table(name="patientoutcome")
public class Patientoutcome implements Serializable {

    @Column(name = "`VISIT`")
    @ApiModelProperty(value = "访视")
    private String visit;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`column_id`")
    @ApiModelProperty(value = "ID")
    private Long columnId;

    @Column(name = "`SUBJID`")
    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @Column(name = "`TRTOUTYN`")
    @ApiModelProperty(value = "治疗结局（是/否）")
    private Boolean trtoutyn;

    @Column(name = "`TRCHKDAT`")
    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp trchkdat;

    @Column(name = "`CUREDYN`")
    @ApiModelProperty(value = "痊愈（是/否）")
    private Boolean curedyn;

    @Column(name = "`CUREDDAT`")
    @ApiModelProperty(value = "日期（年/月/日）")
    private Timestamp cureddat;

    @Column(name = "`CUREDOTH`")
    @ApiModelProperty(value = "备注")
    private String curedoth;

    @Column(name = "`WORSEYN`")
    @ApiModelProperty(value = "病情加重（是/否）")
    private Boolean worseyn;

    @Column(name = "`WORSEDAT`")
    @ApiModelProperty(value = "日期（年/月/日）.1")
    private Timestamp worsedat;

    @Column(name = "`WORSEOTH`")
    @ApiModelProperty(value = "备注.1")
    private String worseoth;

    @Column(name = "`TRFTRTYN`")
    @ApiModelProperty(value = "转院治疗（是/否）")
    private Boolean trftrtyn;

    @Column(name = "`TRFDAT`")
    @ApiModelProperty(value = "日期（年/月/日）.2")
    private Timestamp trfdat;

    @Column(name = "`TRFOTH`")
    @ApiModelProperty(value = "备注.2")
    private String trfoth;

    @Column(name = "`ICUYN`")
    @ApiModelProperty(value = "转入ICU（是/否）")
    private Boolean icuyn;

    @Column(name = "`ICUDAT`")
    @ApiModelProperty(value = "日期（年/月/日）.3")
    private Timestamp icudat;

    @Column(name = "`ICUOTH`")
    @ApiModelProperty(value = "备注.3")
    private String icuoth;

    @Column(name = "`RPFYN`")
    @ApiModelProperty(value = "呼吸衰竭（是/否）")
    private Boolean rpfyn;

    @Column(name = "`RPFDAT`")
    @ApiModelProperty(value = "日期（年/月/日）.4")
    private Timestamp rpfdat;

    @Column(name = "`RPFOTH`")
    @ApiModelProperty(value = "备注.4")
    private String rpfoth;

    @Column(name = "`DEADYN`")
    @ApiModelProperty(value = "死亡（是/否）")
    private Boolean deadyn;

    @Column(name = "`DEADDAT`")
    @ApiModelProperty(value = "日期（年/月/日）.5")
    private Timestamp deaddat;

    @Column(name = "`DEADOTH`")
    @ApiModelProperty(value = "备注.5")
    private String deadoth;

    @Column(name = "`SHOKYN`")
    @ApiModelProperty(value = "休克（是/否）")
    private Boolean shokyn;

    @Column(name = "`SHOKDAT`")
    @ApiModelProperty(value = "日期（年/月/日）.6")
    private Timestamp shokdat;

    @Column(name = "`SHOKOTH`")
    @ApiModelProperty(value = "备注.6")
    private String shokoth;

    @Column(name = "`OTHYN`")
    @ApiModelProperty(value = "其他（是/否）")
    private Boolean othyn;

    @Column(name = "`OTHDAT`")
    @ApiModelProperty(value = "日期（年/月/日）.7")
    private Timestamp othdat;

    @Column(name = "`OTHOTH`")
    @ApiModelProperty(value = "备注.7")
    private String othoth;

    @Column(name = "`TSTYPE`")
    @ApiModelProperty(value = "病原学检测类型")
    private String tstype;

    @Column(name = "`TSRES`")
    @ApiModelProperty(value = "病原学检测结果（新型冠状病毒感染、甲型流感、人感染高致病性禽流感、登革热及百日咳）")
    private String tsres;

    @Column(name = "`NATYN`")
    @ApiModelProperty(value = "是否为核酸检测（是/否）")
    private Boolean natyn;

    @Column(name = "`NATCT`")
    @ApiModelProperty(value = "核酸CT值")
    private Float natct;

    @Column(name = "`NAT3OTH`")
    @ApiModelProperty(value = "备注.8")
    private String nat3oth;

    @Column(name = "`PNEGDT`")
    @ApiModelProperty(value = "病原学转阴时间")
    private String pnegdt;

    public void copy(Patientoutcome source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
