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
package me.zhengjie.patient.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.patient.domain.Respsymptoms;
import me.zhengjie.patient.service.RespsymptomsService;
import me.zhengjie.patient.service.dto.RespsymptomsQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;
import me.zhengjie.patient.service.dto.RespsymptomsDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "呼吸道症状")
@RequestMapping("/api/respsymptoms")
public class RespsymptomsController {

    private final RespsymptomsService respsymptomsService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('respsymptoms:list')")
    public void exportRespsymptoms(HttpServletResponse response, RespsymptomsQueryCriteria criteria) throws IOException {
        respsymptomsService.download(respsymptomsService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询呼吸道症状")
    @PreAuthorize("@el.check('respsymptoms:list')")
    public ResponseEntity<PageResult<RespsymptomsDto>> queryRespsymptoms(RespsymptomsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(respsymptomsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增呼吸道症状")
    @ApiOperation("新增呼吸道症状")
    @PreAuthorize("@el.check('respsymptoms:add')")
    public ResponseEntity<Object> createRespsymptoms(@Validated @RequestBody Respsymptoms resources){
        respsymptomsService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改呼吸道症状")
    @ApiOperation("修改呼吸道症状")
    @PreAuthorize("@el.check('respsymptoms:edit')")
    public ResponseEntity<Object> updateRespsymptoms(@Validated @RequestBody Respsymptoms resources){
        respsymptomsService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除呼吸道症状")
    @ApiOperation("删除呼吸道症状")
    @PreAuthorize("@el.check('respsymptoms:del')")
    public ResponseEntity<Object> deleteRespsymptoms(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        respsymptomsService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}