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

import me.zhengjie.patient.domain.Hosphistory;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.HosphistoryRepository;
import me.zhengjie.patient.service.HosphistoryService;
import me.zhengjie.patient.service.dto.HosphistoryDto;
import me.zhengjie.patient.service.dto.HosphistoryQueryCriteria;
import me.zhengjie.patient.service.mapstruct.HosphistoryMapper;
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
public class HosphistoryServiceImpl implements HosphistoryService {

    private final HosphistoryRepository hosphistoryRepository;
    private final HosphistoryMapper hosphistoryMapper;

    @Override
    public PageResult<HosphistoryDto> queryAll(HosphistoryQueryCriteria criteria, Pageable pageable){
        Page<Hosphistory> page = hosphistoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(hosphistoryMapper::toDto));
    }

    @Override
    public List<HosphistoryDto> queryAll(HosphistoryQueryCriteria criteria){
        return hosphistoryMapper.toDto(hosphistoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public HosphistoryDto findById(Long columnId) {
        Hosphistory hosphistory = hosphistoryRepository.findById(columnId).orElseGet(Hosphistory::new);
        ValidationUtil.isNull(hosphistory.getColumnId(),"Hosphistory","columnId",columnId);
        return hosphistoryMapper.toDto(hosphistory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Hosphistory resources) {
        hosphistoryRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Hosphistory resources) {
        Hosphistory hosphistory = hosphistoryRepository.findById(resources.getColumnId()).orElseGet(Hosphistory::new);
        ValidationUtil.isNull( hosphistory.getColumnId(),"Hosphistory","id",resources.getColumnId());
        hosphistory.copy(resources);
        hosphistoryRepository.save(hosphistory);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            hosphistoryRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<HosphistoryDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (HosphistoryDto hosphistory : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", hosphistory.getSubjid());
            map.put("发病日期（年/月/日）", hosphistory.getNastdat());
            map.put("就诊日期（年/月/日）", hosphistory.getNadocdat());
            map.put("入院日期（年/月/日）", hosphistory.getNahosdat());
            map.put("出院记录（是/否）", hosphistory.getDiscyn());
            map.put("出院日期（年/月/日）", hosphistory.getDiscdat());
            map.put("主诉", hosphistory.getCc());
            map.put("现病史", hosphistory.getHpi());
            map.put("诊疗过程描述", hosphistory.getTrtdesc());
            map.put("出院情况", hosphistory.getDiscsupp());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}