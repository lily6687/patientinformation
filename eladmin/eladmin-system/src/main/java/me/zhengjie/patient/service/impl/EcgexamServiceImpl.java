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

import me.zhengjie.patient.domain.Ecgexam;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.EcgexamRepository;
import me.zhengjie.patient.service.EcgexamService;
import me.zhengjie.patient.service.dto.EcgexamDto;
import me.zhengjie.patient.service.dto.EcgexamQueryCriteria;
import me.zhengjie.patient.service.mapstruct.EcgexamMapper;
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
* @author me.zhengjie
* @date 2025-09-11
**/
@Service
@RequiredArgsConstructor
public class EcgexamServiceImpl implements EcgexamService {

    private final EcgexamRepository ecgexamRepository;
    private final EcgexamMapper ecgexamMapper;

    @Override
    public PageResult<EcgexamDto> queryAll(EcgexamQueryCriteria criteria, Pageable pageable){
        Page<Ecgexam> page = ecgexamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ecgexamMapper::toDto));
    }

    @Override
    public List<EcgexamDto> queryAll(EcgexamQueryCriteria criteria){
        return ecgexamMapper.toDto(ecgexamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public EcgexamDto findById(Long columnId) {
        Ecgexam ecgexam = ecgexamRepository.findById(columnId).orElseGet(Ecgexam::new);
        ValidationUtil.isNull(ecgexam.getColumnId(),"Ecgexam","columnId",columnId);
        return ecgexamMapper.toDto(ecgexam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Ecgexam resources) {
        ecgexamRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Ecgexam resources) {
        Ecgexam ecgexam = ecgexamRepository.findById(resources.getColumnId()).orElseGet(Ecgexam::new);
        ValidationUtil.isNull( ecgexam.getColumnId(),"Ecgexam","id",resources.getColumnId());
        ecgexam.copy(resources);
        ecgexamRepository.save(ecgexam);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            ecgexamRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<EcgexamDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (EcgexamDto ecgexam : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", ecgexam.getSubjid());
            map.put("心电图（是/否）", ecgexam.getEgyn());
            map.put("检查日期（年/月/日）", ecgexam.getEgchkdat());
            map.put("检查结果（正常/异常）", ecgexam.getEgres());
            map.put("备注", ecgexam.getEgoth());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}