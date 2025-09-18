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

import me.zhengjie.patient.domain.Treatmentothermedication;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.TreatmentothermedicationRepository;
import me.zhengjie.patient.service.TreatmentothermedicationService;
import me.zhengjie.patient.service.dto.TreatmentothermedicationDto;
import me.zhengjie.patient.service.dto.TreatmentothermedicationQueryCriteria;
import me.zhengjie.patient.service.mapstruct.TreatmentothermedicationMapper;
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
* @date 2025-09-16
**/
@Service
@RequiredArgsConstructor
public class TreatmentothermedicationServiceImpl implements TreatmentothermedicationService {

    private final TreatmentothermedicationRepository treatmentothermedicationRepository;
    private final TreatmentothermedicationMapper treatmentothermedicationMapper;

    @Override
    public PageResult<TreatmentothermedicationDto> queryAll(TreatmentothermedicationQueryCriteria criteria, Pageable pageable){
        Page<Treatmentothermedication> page = treatmentothermedicationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(treatmentothermedicationMapper::toDto));
    }

    @Override
    public List<TreatmentothermedicationDto> queryAll(TreatmentothermedicationQueryCriteria criteria){
        return treatmentothermedicationMapper.toDto(treatmentothermedicationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public TreatmentothermedicationDto findById(Long columnId) {
        Treatmentothermedication treatmentothermedication = treatmentothermedicationRepository.findById(columnId).orElseGet(Treatmentothermedication::new);
        ValidationUtil.isNull(treatmentothermedication.getColumnId(),"Treatmentothermedication","columnId",columnId);
        return treatmentothermedicationMapper.toDto(treatmentothermedication);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Treatmentothermedication resources) {
        treatmentothermedicationRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Treatmentothermedication resources) {
        Treatmentothermedication treatmentothermedication = treatmentothermedicationRepository.findById(resources.getColumnId()).orElseGet(Treatmentothermedication::new);
        ValidationUtil.isNull( treatmentothermedication.getColumnId(),"Treatmentothermedication","id",resources.getColumnId());
        treatmentothermedication.copy(resources);
        treatmentothermedicationRepository.save(treatmentothermedication);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            treatmentothermedicationRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<TreatmentothermedicationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TreatmentothermedicationDto treatmentothermedication : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", treatmentothermedication.getSubjid());
            map.put("是否使用其他药物（是/否）", treatmentothermedication.getCm2yn());
            map.put("西药药物类型", treatmentothermedication.getCm2cat());
            map.put("退烧药", treatmentothermedication.getCm2feb());
            map.put("抗病毒药", treatmentothermedication.getCm2anvi());
            map.put("抗生素", treatmentothermedication.getCm2anbio());
            map.put("常规补液治疗", treatmentothermedication.getCe2reflu());
            map.put("激素", treatmentothermedication.getCm2hormo());
            map.put("其他药物", treatmentothermedication.getCe2other());
            map.put("请描述", treatmentothermedication.getCm2odesc());
            map.put("备注", treatmentothermedication.getCm2oth());
            map.put("中医药名称", treatmentothermedication.getCm3trt());
            map.put("其他_请注明：", treatmentothermedication.getCm3oth());
            map.put("是否有更改治疗方案", treatmentothermedication.getCmchyn());
            map.put("是_更改时间：_____", treatmentothermedication.getCmchdat());
            map.put("是_请描述更改后治疗方案：", treatmentothermedication.getCmchoth());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}