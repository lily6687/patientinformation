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

import me.zhengjie.patient.domain.Respsymptoms;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.RespsymptomsRepository;
import me.zhengjie.patient.service.RespsymptomsService;
import me.zhengjie.patient.service.dto.RespsymptomsDto;
import me.zhengjie.patient.service.dto.RespsymptomsQueryCriteria;
import me.zhengjie.patient.service.mapstruct.RespsymptomsMapper;
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
public class RespsymptomsServiceImpl implements RespsymptomsService {

    private final RespsymptomsRepository respsymptomsRepository;
    private final RespsymptomsMapper respsymptomsMapper;

    @Override
    public PageResult<RespsymptomsDto> queryAll(RespsymptomsQueryCriteria criteria, Pageable pageable){
        Page<Respsymptoms> page = respsymptomsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(respsymptomsMapper::toDto));
    }

    @Override
    public List<RespsymptomsDto> queryAll(RespsymptomsQueryCriteria criteria){
        return respsymptomsMapper.toDto(respsymptomsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public RespsymptomsDto findById(Long columnId) {
        Respsymptoms respsymptoms = respsymptomsRepository.findById(columnId).orElseGet(Respsymptoms::new);
        ValidationUtil.isNull(respsymptoms.getColumnId(),"Respsymptoms","columnId",columnId);
        return respsymptomsMapper.toDto(respsymptoms);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Respsymptoms resources) {
        respsymptomsRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Respsymptoms resources) {
        Respsymptoms respsymptoms = respsymptomsRepository.findById(resources.getColumnId()).orElseGet(Respsymptoms::new);
        ValidationUtil.isNull( respsymptoms.getColumnId(),"Respsymptoms","id",resources.getColumnId());
        respsymptoms.copy(resources);
        respsymptomsRepository.save(respsymptoms);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            respsymptomsRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<RespsymptomsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (RespsymptomsDto respsymptoms : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", respsymptoms.getSubjid());
            map.put("呼吸道症状（是/否）", respsymptoms.getResptsyn());
            map.put("检查日期（年/月/日）", respsymptoms.getRechkdat());
            map.put("咳嗽（是/否）", respsymptoms.getCoughyn());
            map.put("干咳（是/否）", respsymptoms.getDrycouyn());
            map.put("乏力（是/否）", respsymptoms.getFatgyn());
            map.put("鼻塞 （是/否）", respsymptoms.getStufnsyn());
            map.put("流涕（是/否）", respsymptoms.getRunnsyn());
            map.put("厌食 （是/否）", respsymptoms.getAnrxyn());
            map.put("腹泻 （是/否）", respsymptoms.getDiarhyn());
            map.put("气喘（是/否）", respsymptoms.getAstmyn());
            map.put("畏寒（是/否）", respsymptoms.getChilyn());
            map.put("咽痛（是/否）", respsymptoms.getSothryn());
            map.put("检查日期（年/月/日）.1", respsymptoms.getBrchkdat());
            map.put("呼吸（次/分）", respsymptoms.getBrethfrq());
            map.put("体温（是/否）", respsymptoms.getTempyn());
            map.put("检查日期（年/月/日）.2", respsymptoms.getTechkdat());
            map.put("体温（℃）", respsymptoms.getTemp());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}