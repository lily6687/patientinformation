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

import me.zhengjie.exception.EntityExistException;
import me.zhengjie.patient.domain.Peopleinformation;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.PeopleinformationRepository;
import me.zhengjie.patient.service.PeopleinformationService;
import me.zhengjie.patient.service.dto.PeopleinformationDto;
import me.zhengjie.patient.service.dto.PeopleinformationQueryCriteria;
import me.zhengjie.patient.service.mapstruct.PeopleinformationMapper;
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
* @date 2025-09-15
**/
@Service
@RequiredArgsConstructor
public class PeopleinformationServiceImpl implements PeopleinformationService {

    private final PeopleinformationRepository peopleinformationRepository;
    private final PeopleinformationMapper peopleinformationMapper;

    @Override
    public PageResult<PeopleinformationDto> queryAll(PeopleinformationQueryCriteria criteria, Pageable pageable){
        Page<Peopleinformation> page = peopleinformationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(peopleinformationMapper::toDto));
    }

    @Override
    public List<PeopleinformationDto> queryAll(PeopleinformationQueryCriteria criteria){
        return peopleinformationMapper.toDto(peopleinformationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PeopleinformationDto findById(Long columnId) {
        Peopleinformation peopleinformation = peopleinformationRepository.findById(columnId).orElseGet(Peopleinformation::new);
        ValidationUtil.isNull(peopleinformation.getColumnId(),"Peopleinformation","columnId",columnId);
        return peopleinformationMapper.toDto(peopleinformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Peopleinformation resources) {
        if(peopleinformationRepository.findBySubjid(resources.getSubjid()) != null){
            throw new EntityExistException(Peopleinformation.class,"SUBJID",resources.getSubjid());
        }
        peopleinformationRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Peopleinformation resources) {
        Peopleinformation peopleinformation = peopleinformationRepository.findById(resources.getColumnId()).orElseGet(Peopleinformation::new);
        ValidationUtil.isNull( peopleinformation.getColumnId(),"Peopleinformation","id",resources.getColumnId());
        Peopleinformation peopleinformation1 = peopleinformationRepository.findBySubjid(resources.getSubjid());
        if(peopleinformation1 != null && !peopleinformation1.getColumnId().equals(peopleinformation.getColumnId())){
            throw new EntityExistException(Peopleinformation.class,"SUBJID",resources.getSubjid());
        }
        peopleinformation.copy(resources);
        peopleinformationRepository.save(peopleinformation);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            peopleinformationRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<PeopleinformationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PeopleinformationDto peopleinformation : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("姓名", peopleinformation.getName());
            map.put("患者编号", peopleinformation.getSubjid());
            map.put("性别（男/女）", peopleinformation.getSex());
            map.put("出生日期（年/月/日）", peopleinformation.getBrthdat());
            map.put("年龄", peopleinformation.getAge());
            map.put("体重（kg）", peopleinformation.getWgt());
            map.put("身高（cm）", peopleinformation.getHgt());
            map.put("证件号码", peopleinformation.getNationalid());
            map.put("电话号码", peopleinformation.getTel());
            map.put("地址", peopleinformation.getAddr());
            map.put("医院名称", peopleinformation.getHospital());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}