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

import me.zhengjie.patient.domain.Combinedmedication;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.CombinedmedicationRepository;
import me.zhengjie.patient.service.CombinedmedicationService;
import me.zhengjie.patient.service.dto.CombinedmedicationDto;
import me.zhengjie.patient.service.dto.CombinedmedicationQueryCriteria;
import me.zhengjie.patient.service.mapstruct.CombinedmedicationMapper;
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
public class CombinedmedicationServiceImpl implements CombinedmedicationService {

    private final CombinedmedicationRepository combinedmedicationRepository;
    private final CombinedmedicationMapper combinedmedicationMapper;

    @Override
    public PageResult<CombinedmedicationDto> queryAll(CombinedmedicationQueryCriteria criteria, Pageable pageable){
        Page<Combinedmedication> page = combinedmedicationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(combinedmedicationMapper::toDto));
    }

    @Override
    public List<CombinedmedicationDto> queryAll(CombinedmedicationQueryCriteria criteria){
        return combinedmedicationMapper.toDto(combinedmedicationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public CombinedmedicationDto findById(Long columnId) {
        Combinedmedication combinedmedication = combinedmedicationRepository.findById(columnId).orElseGet(Combinedmedication::new);
        ValidationUtil.isNull(combinedmedication.getColumnId(),"Combinedmedication","columnId",columnId);
        return combinedmedicationMapper.toDto(combinedmedication);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Combinedmedication resources) {
        combinedmedicationRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Combinedmedication resources) {
        Combinedmedication combinedmedication = combinedmedicationRepository.findById(resources.getColumnId()).orElseGet(Combinedmedication::new);
        ValidationUtil.isNull( combinedmedication.getColumnId(),"Combinedmedication","id",resources.getColumnId());
        combinedmedication.copy(resources);
        combinedmedicationRepository.save(combinedmedication);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            combinedmedicationRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<CombinedmedicationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CombinedmedicationDto combinedmedication : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", combinedmedication.getSubjid());
            map.put("合并用药（是/否）", combinedmedication.getCm3yn());
            map.put("药物名称", combinedmedication.getCm3trt());
            map.put("开始日期", combinedmedication.getCm3stdat());
            map.put("结束日期", combinedmedication.getCm3endat());
            map.put("用药目的", combinedmedication.getCm3ord());
            map.put("剂量", combinedmedication.getCm3dose());
            map.put("给药方式", combinedmedication.getCm3route());
            map.put("备注", combinedmedication.getCm3oth());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}