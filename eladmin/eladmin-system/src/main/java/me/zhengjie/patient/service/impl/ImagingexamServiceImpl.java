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

import me.zhengjie.patient.domain.Imagingexam;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.patient.repository.ImagingexamRepository;
import me.zhengjie.patient.service.ImagingexamService;
import me.zhengjie.patient.service.dto.ImagingexamDto;
import me.zhengjie.patient.service.dto.ImagingexamQueryCriteria;
import me.zhengjie.patient.service.mapstruct.ImagingexamMapper;
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
public class ImagingexamServiceImpl implements ImagingexamService {

    private final ImagingexamRepository imagingexamRepository;
    private final ImagingexamMapper imagingexamMapper;

    @Override
    public PageResult<ImagingexamDto> queryAll(ImagingexamQueryCriteria criteria, Pageable pageable){
        Page<Imagingexam> page = imagingexamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(imagingexamMapper::toDto));
    }

    @Override
    public List<ImagingexamDto> queryAll(ImagingexamQueryCriteria criteria){
        return imagingexamMapper.toDto(imagingexamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ImagingexamDto findById(Long columnId) {
        Imagingexam imagingexam = imagingexamRepository.findById(columnId).orElseGet(Imagingexam::new);
        ValidationUtil.isNull(imagingexam.getColumnId(),"Imagingexam","columnId",columnId);
        return imagingexamMapper.toDto(imagingexam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Imagingexam resources) {
        imagingexamRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Imagingexam resources) {
        Imagingexam imagingexam = imagingexamRepository.findById(resources.getColumnId()).orElseGet(Imagingexam::new);
        ValidationUtil.isNull( imagingexam.getColumnId(),"Imagingexam","id",resources.getColumnId());
        imagingexam.copy(resources);
        imagingexamRepository.save(imagingexam);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long columnId : ids) {
            imagingexamRepository.deleteById(columnId);
        }
    }

    @Override
    public void download(List<ImagingexamDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ImagingexamDto imagingexam : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("患者编号", imagingexam.getSubjid());
            map.put("影像学检查（是/否）", imagingexam.getMichkyn());
            map.put("检查类型", imagingexam.getMitype());
            map.put("检查日期（年/月/日）", imagingexam.getMichkdat());
            map.put("检查结果（正常/异常）", imagingexam.getMires());
            map.put("备注", imagingexam.getMioth());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}