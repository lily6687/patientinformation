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
package me.zhengjie.patient.service;

import me.zhengjie.patient.domain.Combinedmedication;
import me.zhengjie.patient.service.dto.CombinedmedicationDto;
import me.zhengjie.patient.service.dto.CombinedmedicationQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;

/**
* @website https://eladmin.vip
* @description 服务接口
* @author zhengjie
* @date 2025-09-16
**/
public interface CombinedmedicationService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    PageResult<CombinedmedicationDto> queryAll(CombinedmedicationQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<CombinedmedicationDto>
    */
    List<CombinedmedicationDto> queryAll(CombinedmedicationQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param columnId ID
     * @return CombinedmedicationDto
     */
    CombinedmedicationDto findById(Long columnId);

    /**
    * 创建
    * @param resources /
    */
    void create(Combinedmedication resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Combinedmedication resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<CombinedmedicationDto> all, HttpServletResponse response) throws IOException;
}