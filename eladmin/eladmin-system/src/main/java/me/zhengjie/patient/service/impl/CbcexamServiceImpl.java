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

import me.zhengjie.patient.domain.Cbcexam;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.CbcexamRepository;
import me.zhengjie.patient.service.CbcexamService;
import me.zhengjie.patient.service.dto.CbcexamDto;
import me.zhengjie.patient.service.dto.CbcexamQueryCriteria;
import me.zhengjie.patient.service.mapstruct.CbcexamMapper;
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
public class CbcexamServiceImpl implements CbcexamService {

    private final CbcexamRepository cbcexamRepository;
    private final CbcexamMapper cbcexamMapper;

    @Override
    public PageResult<CbcexamDto> queryAll(CbcexamQueryCriteria criteria, Pageable pageable){
        Page<Cbcexam> page = cbcexamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(cbcexamMapper::toDto));
    }

    @Override
    public List<CbcexamDto> queryAll(CbcexamQueryCriteria criteria){
        return cbcexamMapper.toDto(cbcexamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public CbcexamDto findById(Long columnId) {
        Cbcexam cbcexam = cbcexamRepository.findById(columnId).orElseGet(Cbcexam::new);
        ValidationUtil.isNull(cbcexam.getColumnId(),"Cbcexam","columnId",columnId);
        return cbcexamMapper.toDto(cbcexam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Cbcexam resources) {
        cbcexamRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Cbcexam resources) {
        Cbcexam cbcexam = cbcexamRepository.findById(resources.getColumnId()).orElseGet(Cbcexam::new);
        ValidationUtil.isNull( cbcexam.getColumnId(),"Cbcexam","id",resources.getColumnId());
        cbcexam.copy(resources);
        cbcexamRepository.save(cbcexam);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            cbcexamRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<CbcexamDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CbcexamDto cbcexam : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", cbcexam.getSubjid());
            map.put("血常规（是/否）", cbcexam.getBlyn());
            map.put("检查日期（年/月/日）", cbcexam.getBlchkdat());
            map.put("中性粒细胞计数-检查结果", cbcexam.getBl1res());
            map.put("中性粒细胞计数-单位", cbcexam.getBl1unit());
            map.put("中性粒细胞计数-结果判定", cbcexam.getBl1clsig());
            map.put("中性粒细胞计数备注", cbcexam.getBl1oth());
            map.put("淋巴细胞计数-检查结果", cbcexam.getBl2res());
            map.put("淋巴细胞计数-单位", cbcexam.getBl2unit());
            map.put("淋巴细胞计数-结果判定", cbcexam.getBl2clsig());
            map.put("淋巴细胞计数-备注", cbcexam.getBl2oth());
            map.put("红细胞计数-检查结果", cbcexam.getBl3res());
            map.put("红细胞计数-单位", cbcexam.getBl3unit());
            map.put("红细胞计数-结果判定", cbcexam.getBl3clsig());
            map.put("红细胞计数-备注", cbcexam.getBl3oth());
            map.put("血红蛋白-检查结果", cbcexam.getBl4res());
            map.put("血红蛋白-单位", cbcexam.getBl4unit());
            map.put("血红蛋白-结果判定", cbcexam.getBl4clsig());
            map.put("备注", cbcexam.getBl4oth());
            map.put("红细胞压积-检查结果", cbcexam.getBl5res());
            map.put("红细胞压积-单位", cbcexam.getBl5unit());
            map.put("红细胞压积-结果判定", cbcexam.getBl5clsig());
            map.put("备注.1", cbcexam.getBl5oth());
            map.put("白细胞计数-检查结果", cbcexam.getBl6res());
            map.put("白细胞计数-单位", cbcexam.getBl6unit());
            map.put("白细胞计数-结果判定", cbcexam.getBl6clsig());
            map.put("白细胞计数-备注", cbcexam.getBl6oth());
            map.put("血小板计数-检查结果", cbcexam.getBl7res());
            map.put("血小板计数-单位", cbcexam.getBl7unit());
            map.put("血小板计数-结果判定", cbcexam.getBl7clsig());
            map.put("血小板计数-备注", cbcexam.getBl7oth());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}