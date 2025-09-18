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

import me.zhengjie.patient.domain.Specialtreatment;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.SpecialtreatmentRepository;
import me.zhengjie.patient.service.SpecialtreatmentService;
import me.zhengjie.patient.service.dto.SpecialtreatmentDto;
import me.zhengjie.patient.service.dto.SpecialtreatmentQueryCriteria;
import me.zhengjie.patient.service.mapstruct.SpecialtreatmentMapper;
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
public class SpecialtreatmentServiceImpl implements SpecialtreatmentService {

    private final SpecialtreatmentRepository specialtreatmentRepository;
    private final SpecialtreatmentMapper specialtreatmentMapper;

    @Override
    public PageResult<SpecialtreatmentDto> queryAll(SpecialtreatmentQueryCriteria criteria, Pageable pageable){
        Page<Specialtreatment> page = specialtreatmentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(specialtreatmentMapper::toDto));
    }

    @Override
    public List<SpecialtreatmentDto> queryAll(SpecialtreatmentQueryCriteria criteria){
        return specialtreatmentMapper.toDto(specialtreatmentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public SpecialtreatmentDto findById(Long columnId) {
        Specialtreatment specialtreatment = specialtreatmentRepository.findById(columnId).orElseGet(Specialtreatment::new);
        ValidationUtil.isNull(specialtreatment.getColumnId(),"Specialtreatment","columnId",columnId);
        return specialtreatmentMapper.toDto(specialtreatment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Specialtreatment resources) {
        specialtreatmentRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Specialtreatment resources) {
        Specialtreatment specialtreatment = specialtreatmentRepository.findById(resources.getColumnId()).orElseGet(Specialtreatment::new);
        ValidationUtil.isNull( specialtreatment.getColumnId(),"Specialtreatment","id",resources.getColumnId());
        specialtreatment.copy(resources);
        specialtreatmentRepository.save(specialtreatment);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            specialtreatmentRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<SpecialtreatmentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SpecialtreatmentDto specialtreatment : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", specialtreatment.getSubjid());
            map.put("是否接收特殊治疗", specialtreatment.getStyn());
            map.put("机械通气（是/否）", specialtreatment.getMvyn());
            map.put("开始日期", specialtreatment.getMvstdat());
            map.put("结束日期", specialtreatment.getMvendat());
            map.put("详细治疗信息", specialtreatment.getStoth());
            map.put("ECMO（是/否）", specialtreatment.getEmyn());
            map.put("开始日期.1", specialtreatment.getEmstdat());
            map.put("结束日期.1", specialtreatment.getEmendat());
            map.put("详细治疗信息.1", specialtreatment.getEmoth());
            map.put("高流量鼻导管氧（是/否）", specialtreatment.getHfncoyn());
            map.put("开始日期.2", specialtreatment.getHfstdat());
            map.put("结束日期.2", specialtreatment.getHfendat());
            map.put("详细治疗信息.2", specialtreatment.getHfncooth());
            map.put("无创通气（是/否）", specialtreatment.getNonivyn());
            map.put("开始日期.3", specialtreatment.getNosatdat());
            map.put("结束日期.3", specialtreatment.getNoendat());
            map.put("详细治疗信息.3", specialtreatment.getNonivoth());
            map.put("持续性肾脏替代疗法（CRRT）（是/否）", specialtreatment.getCrrtyn());
            map.put("开始日期.4", specialtreatment.getCrstdat());
            map.put("结束日期.4", specialtreatment.getCrendat());
            map.put("详细治疗信息.4", specialtreatment.getCrrtoth());
            map.put("康复者血浆治疗（是/否）", specialtreatment.getRptyn());
            map.put("开始日期.5", specialtreatment.getRpstdat());
            map.put("结束日期.5", specialtreatment.getRpendat());
            map.put("详细治疗信息.5", specialtreatment.getRptoth());
            map.put("血液净化治疗（是/否）", specialtreatment.getBptyn());
            map.put("开始日期.6", specialtreatment.getBpstdat());
            map.put("结束日期.6", specialtreatment.getBpendat());
            map.put("详细治疗信息.6", specialtreatment.getBptoth());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}