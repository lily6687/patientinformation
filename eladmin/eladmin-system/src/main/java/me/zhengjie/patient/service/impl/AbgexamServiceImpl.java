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

import me.zhengjie.patient.domain.Abgexam;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.AbgexamRepository;
import me.zhengjie.patient.service.AbgexamService;
import me.zhengjie.patient.service.dto.AbgexamDto;
import me.zhengjie.patient.service.dto.AbgexamQueryCriteria;
import me.zhengjie.patient.service.mapstruct.AbgexamMapper;
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
public class AbgexamServiceImpl implements AbgexamService {

    private final AbgexamRepository abgexamRepository;
    private final AbgexamMapper abgexamMapper;

    @Override
    public PageResult<AbgexamDto> queryAll(AbgexamQueryCriteria criteria, Pageable pageable){
        Page<Abgexam> page = abgexamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(abgexamMapper::toDto));
    }

    @Override
    public List<AbgexamDto> queryAll(AbgexamQueryCriteria criteria){
        return abgexamMapper.toDto(abgexamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public AbgexamDto findById(Long columnId) {
        Abgexam abgexam = abgexamRepository.findById(columnId).orElseGet(Abgexam::new);
        ValidationUtil.isNull(abgexam.getColumnId(),"Abgexam","columnId",columnId);
        return abgexamMapper.toDto(abgexam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Abgexam resources) {
        abgexamRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Abgexam resources) {
        Abgexam abgexam = abgexamRepository.findById(resources.getColumnId()).orElseGet(Abgexam::new);
        ValidationUtil.isNull( abgexam.getColumnId(),"Abgexam","id",resources.getColumnId());
        abgexam.copy(resources);
        abgexamRepository.save(abgexam);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            abgexamRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<AbgexamDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (AbgexamDto abgexam : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", abgexam.getSubjid());
            map.put("血气分析（是/否）", abgexam.getBgyn());
            map.put("检查日期（年/月/日）", abgexam.getBgchkdat());
            map.put("血氧饱和度-检查结果", abgexam.getBg1res());
            map.put("血氧饱和度-单位", abgexam.getBg1unit());
            map.put("血氧饱和度-结果判定", abgexam.getBg1clsig());
            map.put("血氧饱和度-备注", abgexam.getBg1oth());
            map.put("血氧分压-检查结果", abgexam.getBg2res());
            map.put("血氧分压-单位", abgexam.getBg2unit());
            map.put("血氧分压-结果判定", abgexam.getBg2clsig());
            map.put("血氧分压-备注", abgexam.getBg2oth());
            map.put("血液酸碱度-检查结果", abgexam.getBg3res());
            map.put("血液酸碱度-单位", abgexam.getBg3unit());
            map.put("血液酸碱度-结果判定", abgexam.getBg3clsig());
            map.put("血液酸碱度-备注", abgexam.getBg3oth());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}