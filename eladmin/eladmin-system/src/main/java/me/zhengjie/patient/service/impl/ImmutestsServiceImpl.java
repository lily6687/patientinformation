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

import me.zhengjie.patient.domain.Immutests;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.ImmutestsRepository;
import me.zhengjie.patient.service.ImmutestsService;
import me.zhengjie.patient.service.dto.ImmutestsDto;
import me.zhengjie.patient.service.dto.ImmutestsQueryCriteria;
import me.zhengjie.patient.service.mapstruct.ImmutestsMapper;
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
public class ImmutestsServiceImpl implements ImmutestsService {

    private final ImmutestsRepository immutestsRepository;
    private final ImmutestsMapper immutestsMapper;

    @Override
    public PageResult<ImmutestsDto> queryAll(ImmutestsQueryCriteria criteria, Pageable pageable){
        Page<Immutests> page = immutestsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(immutestsMapper::toDto));
    }

    @Override
    public List<ImmutestsDto> queryAll(ImmutestsQueryCriteria criteria){
        return immutestsMapper.toDto(immutestsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ImmutestsDto findById(Long columnId) {
        Immutests immutests = immutestsRepository.findById(columnId).orElseGet(Immutests::new);
        ValidationUtil.isNull(immutests.getColumnId(),"Immutests","columnId",columnId);
        return immutestsMapper.toDto(immutests);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Immutests resources) {
        immutestsRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Immutests resources) {
        Immutests immutests = immutestsRepository.findById(resources.getColumnId()).orElseGet(Immutests::new);
        ValidationUtil.isNull( immutests.getColumnId(),"Immutests","id",resources.getColumnId());
        immutests.copy(resources);
        immutestsRepository.save(immutests);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            immutestsRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<ImmutestsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ImmutestsDto immutests : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", immutests.getSubjid());
            map.put("免疫学指标（是/否）", immutests.getImyn());
            map.put("C-反应蛋白-检查日期（年/月/日）", immutests.getIm1dat());
            map.put("C-反应蛋白-检查结果", immutests.getIm1res());
            map.put("C-反应蛋白-单位", immutests.getIm1unit());
            map.put("C-反应蛋白-结果判定", immutests.getIm1clsig());
            map.put("C-反应蛋白-备注", immutests.getIm1oth());
            map.put("血沉-检查日期（年/月/日）", immutests.getIm2dat());
            map.put("血沉-检查结果", immutests.getIm2res());
            map.put("血沉-单位", immutests.getIm2unit());
            map.put("血沉-结果判定", immutests.getIm2clsig());
            map.put("血沉-备注", immutests.getIm2oth());
            map.put("降钙素原-检查日期（年/月/日）", immutests.getIm3dat());
            map.put("降钙素原-检查结果", immutests.getIm3res());
            map.put("降钙素原-单位", immutests.getIm3unit());
            map.put("降钙素原-结果判定", immutests.getIm3clsig());
            map.put("降钙素原-备注", immutests.getIm3oth());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}