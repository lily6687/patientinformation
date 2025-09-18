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

import me.zhengjie.patient.domain.Historyallergy;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.HistoryallergyRepository;
import me.zhengjie.patient.service.HistoryallergyService;
import me.zhengjie.patient.service.dto.HistoryallergyDto;
import me.zhengjie.patient.service.dto.HistoryallergyQueryCriteria;
import me.zhengjie.patient.service.mapstruct.HistoryallergyMapper;
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
public class HistoryallergyServiceImpl implements HistoryallergyService {

    private final HistoryallergyRepository historyallergyRepository;
    private final HistoryallergyMapper historyallergyMapper;

    @Override
    public PageResult<HistoryallergyDto> queryAll(HistoryallergyQueryCriteria criteria, Pageable pageable){
        Page<Historyallergy> page = historyallergyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(historyallergyMapper::toDto));
    }

    @Override
    public List<HistoryallergyDto> queryAll(HistoryallergyQueryCriteria criteria){
        return historyallergyMapper.toDto(historyallergyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public HistoryallergyDto findById(Long columnId) {
        Historyallergy historyallergy = historyallergyRepository.findById(columnId).orElseGet(Historyallergy::new);
        ValidationUtil.isNull(historyallergy.getColumnId(),"Historyallergy","columnId",columnId);
        return historyallergyMapper.toDto(historyallergy);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Historyallergy resources) {
        historyallergyRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Historyallergy resources) {
        Historyallergy historyallergy = historyallergyRepository.findById(resources.getColumnId()).orElseGet(Historyallergy::new);
        ValidationUtil.isNull( historyallergy.getColumnId(),"Historyallergy","id",resources.getColumnId());
        historyallergy.copy(resources);
        historyallergyRepository.save(historyallergy);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            historyallergyRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<HistoryallergyDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (HistoryallergyDto historyallergy : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", historyallergy.getSubjid());
            map.put("诊断", historyallergy.getDiag());
            map.put("既往病史（是/否）", historyallergy.getMhyn());
            map.put("症状/疾病名称", historyallergy.getMhnam());
            map.put("过敏史（是/否）", historyallergy.getAhyn());
            map.put("过敏原", historyallergy.getAhalrgn());
            map.put("合并疾病（是/否）", historyallergy.getCm1yn());
            map.put("合并症名称", historyallergy.getCm1trt());
            map.put("从不吸烟/已经戒烟/现在吸烟", historyallergy.getMhsmokyn());
            map.put("戒烟_____年", historyallergy.getMhqsmdur());
            map.put("吸烟_____年", historyallergy.getMhsmkdur());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}