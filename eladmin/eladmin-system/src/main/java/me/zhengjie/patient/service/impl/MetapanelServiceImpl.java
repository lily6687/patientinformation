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

import me.zhengjie.patient.domain.Metapanel;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.MetapanelRepository;
import me.zhengjie.patient.service.MetapanelService;
import me.zhengjie.patient.service.dto.MetapanelDto;
import me.zhengjie.patient.service.dto.MetapanelQueryCriteria;
import me.zhengjie.patient.service.mapstruct.MetapanelMapper;
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
public class MetapanelServiceImpl implements MetapanelService {

    private final MetapanelRepository metapanelRepository;
    private final MetapanelMapper metapanelMapper;

    @Override
    public PageResult<MetapanelDto> queryAll(MetapanelQueryCriteria criteria, Pageable pageable){
        Page<Metapanel> page = metapanelRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(metapanelMapper::toDto));
    }

    @Override
    public List<MetapanelDto> queryAll(MetapanelQueryCriteria criteria){
        return metapanelMapper.toDto(metapanelRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public MetapanelDto findById(Long columnId) {
        Metapanel metapanel = metapanelRepository.findById(columnId).orElseGet(Metapanel::new);
        ValidationUtil.isNull(metapanel.getColumnId(),"Metapanel","columnId",columnId);
        return metapanelMapper.toDto(metapanel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Metapanel resources) {
        metapanelRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Metapanel resources) {
        Metapanel metapanel = metapanelRepository.findById(resources.getColumnId()).orElseGet(Metapanel::new);
        ValidationUtil.isNull( metapanel.getColumnId(),"Metapanel","id",resources.getColumnId());
        metapanel.copy(resources);
        metapanelRepository.save(metapanel);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            metapanelRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<MetapanelDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MetapanelDto metapanel : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", metapanel.getSubjid());
            map.put("血生化 （是/否）", metapanel.getBcyn());
            map.put("检查日期（年/月/日）", metapanel.getBcchkdat());
            map.put("谷草转氨酶(AST) -检查结果", metapanel.getBc1res());
            map.put("谷草转氨酶(AST) -单位", metapanel.getBlc1unit());
            map.put("谷草转氨酶(AST) -结果判定", metapanel.getBc1clsig());
            map.put("谷草转氨酶(AST) -备注", metapanel.getBc1oth());
            map.put("谷丙转氨酶（ALT）-检查结果", metapanel.getBc2res());
            map.put("谷丙转氨酶（ALT）-单位", metapanel.getBc2unit());
            map.put("谷丙转氨酶（ALT）-结果判定", metapanel.getBc2clsig());
            map.put("谷丙转氨酶（ALT）-备注", metapanel.getBc2oth());
            map.put("肌酸激酶（CK）-检查结果", metapanel.getBc3res());
            map.put("肌酸激酶（CK）单位", metapanel.getBc3unit());
            map.put("肌酸激酶（CK）-结果判定", metapanel.getBc3clsig());
            map.put("肌酸激酶（CK）-备注", metapanel.getBc3oth());
            map.put("肌酸激酶同工酶（CKMB）-检查结果", metapanel.getBc4res());
            map.put("肌酸激酶同工酶（CKMB）-单位", metapanel.getBc4unit());
            map.put("肌酸激酶同工酶（CKMB）-结果判定", metapanel.getBc4clsig());
            map.put("肌酸激酶同工酶（CKMB）-备注", metapanel.getBc4oth());
            map.put("碱性磷酸酶（ALP）-检查结果", metapanel.getBc5res());
            map.put("碱性磷酸酶（ALP）-单位", metapanel.getBc5unit());
            map.put("碱性磷酸酶（ALP）-结果判定", metapanel.getBc5clsig());
            map.put("碱性磷酸酶（ALP）-备注", metapanel.getBc5oth());
            map.put("乳酸脱氢酶（LDH）-检查结果", metapanel.getBc6res());
            map.put("乳酸脱氢酶（LDH）-单位", metapanel.getBc6unit());
            map.put("乳酸脱氢酶（LDH）-结果判定", metapanel.getBc6clsig());
            map.put("乳酸脱氢酶（LDH）-备注", metapanel.getBc6oth());
            map.put("尿素氮（UREA）-检查结果", metapanel.getBc7res());
            map.put("尿素氮（UREA）-单位", metapanel.getBc7unit());
            map.put("尿素氮（UREA）-结果判定", metapanel.getBc7clsig());
            map.put("尿素氮（UREA）-备注", metapanel.getBc7oth());
            map.put("血肌酐（CREA）-检查结果", metapanel.getBc8res());
            map.put("血肌酐（CREA）-单位", metapanel.getBc8unit());
            map.put("血肌酐（CREA）-结果判定", metapanel.getBc8clsig());
            map.put("血肌酐（CREA）-备注", metapanel.getBc8oth());
            map.put("Ca2+检查结果", metapanel.getBc9res());
            map.put("Ca2+单位", metapanel.getBc9unit());
            map.put("Ca2+结果判定", metapanel.getBc9clsig());
            map.put("Ca2+备注", metapanel.getBc9oth());
            map.put("Na+检查结果", metapanel.getBc10res());
            map.put("Na+单位", metapanel.getBc10uni());
            map.put("Na+结果判定", metapanel.getBc10clsi());
            map.put("Na+备注", metapanel.getBc10rem());
            map.put("Cl-检查结果", metapanel.getBc11res());
            map.put("Cl-单位", metapanel.getBc11uni());
            map.put("Cl-结果判定", metapanel.getBc11clsi());
            map.put("Cl-备注", metapanel.getBc11rem());
            map.put("K+检查结果", metapanel.getBc12res());
            map.put("K+单位", metapanel.getBc12uni());
            map.put("K+结果判定", metapanel.getBc12clsi());
            map.put("K+备注", metapanel.getBc12rem());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}