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
* @date 2025-09-11
**/
@Data
public class PatientoutcomeDto implements Serializable {

    @ApiModelProperty(value = "访视")
    private String visit;

    @ApiModelProperty(value = "ID")
    private Long columnId;

    @ApiModelProperty(value = "患者编号")
    private String subjid;

    @ApiModelProperty(value = "治疗结局（是/否）")
    private Boolean trtoutyn;

    @ApiModelProperty(value = "检查日期（年/月/日）")
    private Timestamp trchkdat;

    @ApiModelProperty(value = "痊愈（是/否）")
    private Boolean curedyn;

    @ApiModelProperty(value = "日期（年/月/日）")
    private Timestamp cureddat;

    @ApiModelProperty(value = "备注")
    private String curedoth;

    @ApiModelProperty(value = "病情加重（是/否）")
    private Boolean worseyn;

    @ApiModelProperty(value = "日期（年/月/日）.1")
    private Timestamp worsedat;

    @ApiModelProperty(value = "备注.1")
    private String worseoth;

    @ApiModelProperty(value = "转院治疗（是/否）")
    private Boolean trftrtyn;

    @ApiModelProperty(value = "日期（年/月/日）.2")
    private Timestamp trfdat;

    @ApiModelProperty(value = "备注.2")
    private String trfoth;

    @ApiModelProperty(value = "转入ICU（是/否）")
    private Boolean icuyn;

    @ApiModelProperty(value = "日期（年/月/日）.3")
    private Timestamp icudat;

    @ApiModelProperty(value = "备注.3")
    private String icuoth;

    @ApiModelProperty(value = "呼吸衰竭（是/否）")
    private Boolean rpfyn;

    @ApiModelProperty(value = "日期（年/月/日）.4")
    private Timestamp rpfdat;

    @ApiModelProperty(value = "备注.4")
    private String rpfoth;

    @ApiModelProperty(value = "死亡（是/否）")
    private Boolean deadyn;

    @ApiModelProperty(value = "日期（年/月/日）.5")
    private Timestamp deaddat;

    @ApiModelProperty(value = "备注.5")
    private String deadoth;

    @ApiModelProperty(value = "休克（是/否）")
    private Boolean shokyn;

    @ApiModelProperty(value = "日期（年/月/日）.6")
    private Timestamp shokdat;

    @ApiModelProperty(value = "备注.6")
    private String shokoth;

    @ApiModelProperty(value = "其他（是/否）")
    private Boolean othyn;

    @ApiModelProperty(value = "日期（年/月/日）.7")
    private Timestamp othdat;

    @ApiModelProperty(value = "备注.7")
    private String othoth;

    @ApiModelProperty(value = "病原学检测类型")
    private String tstype;

    @ApiModelProperty(value = "病原学检测结果（新型冠状病毒感染、甲型流感、人感染高致病性禽流感、登革热及百日咳）")
    private String tsres;

    @ApiModelProperty(value = "是否为核酸检测（是/否）")
    private Boolean natyn;

    @ApiModelProperty(value = "核酸CT值")
    private Float natct;

    @ApiModelProperty(value = "备注.8")
    private String nat3oth;

    @ApiModelProperty(value = "病原学转阴时间")
    private String pnegdt;
}