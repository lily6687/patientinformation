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
package me.zhengjie.patient.service.impl;

import me.zhengjie.patient.domain.Patientoutcome;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.PatientoutcomeRepository;
import me.zhengjie.patient.service.PatientoutcomeService;
import me.zhengjie.patient.service.dto.PatientoutcomeDto;
import me.zhengjie.patient.service.dto.PatientoutcomeQueryCriteria;
import me.zhengjie.patient.service.mapstruct.PatientoutcomeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import me.zhengjie.utils.PageResult;

/**
* @website https://eladmin.vip
* @description 服务实现
* @author zhengjie
* @date 2025-09-11
**/
@Service
@RequiredArgsConstructor
public class PatientoutcomeServiceImpl implements PatientoutcomeService {

    private final PatientoutcomeRepository patientoutcomeRepository;
    private final PatientoutcomeMapper patientoutcomeMapper;

    @Override
    public PageResult<PatientoutcomeDto> queryAll(PatientoutcomeQueryCriteria criteria, Pageable pageable){
        Page<Patientoutcome> page = patientoutcomeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(patientoutcomeMapper::toDto));
    }

    @Override
    public List<PatientoutcomeDto> queryAll(PatientoutcomeQueryCriteria criteria){
        return patientoutcomeMapper.toDto(patientoutcomeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PatientoutcomeDto findById(Long columnId) {
        Patientoutcome patientoutcome = patientoutcomeRepository.findById(columnId).orElseGet(Patientoutcome::new);
        ValidationUtil.isNull(patientoutcome.getColumnId(),"Patientoutcome","columnId",columnId);
        return patientoutcomeMapper.toDto(patientoutcome);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Patientoutcome resources) {
        patientoutcomeRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Patientoutcome resources) {
        Patientoutcome patientoutcome = patientoutcomeRepository.findById(resources.getColumnId()).orElseGet(Patientoutcome::new);
        ValidationUtil.isNull( patientoutcome.getColumnId(),"Patientoutcome","id",resources.getColumnId());
        patientoutcome.copy(resources);
        patientoutcomeRepository.save(patientoutcome);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            patientoutcomeRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<PatientoutcomeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PatientoutcomeDto patientoutcome : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("访视", patientoutcome.getVisit());
            map.put("患者编号", patientoutcome.getSubjid());
            map.put("治疗结局（是/否）", patientoutcome.getTrtoutyn());
            map.put("检查日期（年/月/日）", patientoutcome.getTrchkdat());
            map.put("痊愈（是/否）", patientoutcome.getCuredyn());
            map.put("日期（年/月/日）", patientoutcome.getCureddat());
            map.put("备注", patientoutcome.getCuredoth());
            map.put("病情加重（是/否）", patientoutcome.getWorseyn());
            map.put("日期（年/月/日）.1", patientoutcome.getWorsedat());
            map.put("备注.1", patientoutcome.getWorseoth());
            map.put("转院治疗（是/否）", patientoutcome.getTrftrtyn());
            map.put("日期（年/月/日）.2", patientoutcome.getTrfdat());
            map.put("备注.2", patientoutcome.getTrfoth());
            map.put("转入ICU（是/否）", patientoutcome.getIcuyn());
            map.put("日期（年/月/日）.3", patientoutcome.getIcudat());
            map.put("备注.3", patientoutcome.getIcuoth());
            map.put("呼吸衰竭（是/否）", patientoutcome.getRpfyn());
            map.put("日期（年/月/日）.4", patientoutcome.getRpfdat());
            map.put("备注.4", patientoutcome.getRpfoth());
            map.put("死亡（是/否）", patientoutcome.getDeadyn());
            map.put("日期（年/月/日）.5", patientoutcome.getDeaddat());
            map.put("备注.5", patientoutcome.getDeadoth());
            map.put("休克（是/否）", patientoutcome.getShokyn());
            map.put("日期（年/月/日）.6", patientoutcome.getShokdat());
            map.put("备注.6", patientoutcome.getShokoth());
            map.put("其他（是/否）", patientoutcome.getOthyn());
            map.put("日期（年/月/日）.7", patientoutcome.getOthdat());
            map.put("备注.7", patientoutcome.getOthoth());
            map.put("病原学检测类型", patientoutcome.getTstype());
            map.put("病原学检测结果（新型冠状病毒感染、甲型流感、人感染高致病性禽流感、登革热及百日咳）", patientoutcome.getTsres());
            map.put("是否为核酸检测（是/否）", patientoutcome.getNatyn());
            map.put("核酸CT值", patientoutcome.getNatct());
            map.put("备注.8", patientoutcome.getNat3oth());
            map.put("病原学转阴时间", patientoutcome.getPnegdt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}