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

import me.zhengjie.patient.domain.Urinalysis;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.UrinalysisRepository;
import me.zhengjie.patient.service.UrinalysisService;
import me.zhengjie.patient.service.dto.UrinalysisDto;
import me.zhengjie.patient.service.dto.UrinalysisQueryCriteria;
import me.zhengjie.patient.service.mapstruct.UrinalysisMapper;
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
public class UrinalysisServiceImpl implements UrinalysisService {

    private final UrinalysisRepository urinalysisRepository;
    private final UrinalysisMapper urinalysisMapper;

    @Override
    public PageResult<UrinalysisDto> queryAll(UrinalysisQueryCriteria criteria, Pageable pageable){
        Page<Urinalysis> page = urinalysisRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(urinalysisMapper::toDto));
    }

    @Override
    public List<UrinalysisDto> queryAll(UrinalysisQueryCriteria criteria){
        return urinalysisMapper.toDto(urinalysisRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public UrinalysisDto findById(Long columnId) {
        Urinalysis urinalysis = urinalysisRepository.findById(columnId).orElseGet(Urinalysis::new);
        ValidationUtil.isNull(urinalysis.getColumnId(),"Urinalysis","columnId",columnId);
        return urinalysisMapper.toDto(urinalysis);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Urinalysis resources) {
        urinalysisRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Urinalysis resources) {
        Urinalysis urinalysis = urinalysisRepository.findById(resources.getColumnId()).orElseGet(Urinalysis::new);
        ValidationUtil.isNull( urinalysis.getColumnId(),"Urinalysis","id",resources.getColumnId());
        urinalysis.copy(resources);
        urinalysisRepository.save(urinalysis);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            urinalysisRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<UrinalysisDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UrinalysisDto urinalysis : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", urinalysis.getSubjid());
            map.put("尿常规（是/否）", urinalysis.getUryn());
            map.put("检查日期（年/月/日）", urinalysis.getUrchkdat());
            map.put("尿白细胞-检查结果", urinalysis.getUr1res());
            map.put("尿白细胞-单位", urinalysis.getUr1unit());
            map.put("尿白细胞-结果判定", urinalysis.getUr1clsig());
            map.put("尿白细胞-备注", urinalysis.getUr1oth());
            map.put("尿红细胞-检查结果", urinalysis.getUr2res());
            map.put("尿红细胞-单位", urinalysis.getUr2unit());
            map.put("尿红细胞-结果判定", urinalysis.getUr2clsig());
            map.put("尿红细胞-备注", urinalysis.getUr2oth());
            map.put("尿糖-检查结果", urinalysis.getUr3res());
            map.put("尿糖-单位", urinalysis.getUr3unit());
            map.put("尿糖-结果判定", urinalysis.getUr3clsig());
            map.put("尿糖-备注", urinalysis.getUr3oth());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}