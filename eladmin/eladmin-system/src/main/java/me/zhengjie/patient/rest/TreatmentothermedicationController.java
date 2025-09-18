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
import me.zhengjie.patient.domain.Treatmentothermedication;
import me.zhengjie.patient.service.TreatmentothermedicationService;
import me.zhengjie.patient.service.dto.TreatmentothermedicationQueryCriteria;
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
import me.zhengjie.patient.service.dto.TreatmentothermedicationDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "治疗情况_其他药物")
@RequestMapping("/api/treatmentothermedication")
public class TreatmentothermedicationController {

    private final TreatmentothermedicationService treatmentothermedicationService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('treatmentothermedication:list')")
    public void exportTreatmentothermedication(HttpServletResponse response, TreatmentothermedicationQueryCriteria criteria) throws IOException {
        treatmentothermedicationService.download(treatmentothermedicationService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询治疗情况_其他药物")
    @PreAuthorize("@el.check('treatmentothermedication:list')")
    public ResponseEntity<PageResult<TreatmentothermedicationDto>> queryTreatmentothermedication(TreatmentothermedicationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(treatmentothermedicationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增治疗情况_其他药物")
    @ApiOperation("新增治疗情况_其他药物")
    @PreAuthorize("@el.check('treatmentothermedication:add')")
    public ResponseEntity<Object> createTreatmentothermedication(@Validated @RequestBody Treatmentothermedication resources){
        treatmentothermedicationService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改治疗情况_其他药物")
    @ApiOperation("修改治疗情况_其他药物")
    @PreAuthorize("@el.check('treatmentothermedication:edit')")
    public ResponseEntity<Object> updateTreatmentothermedication(@Validated @RequestBody Treatmentothermedication resources){
        treatmentothermedicationService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除治疗情况_其他药物")
    @ApiOperation("删除治疗情况_其他药物")
    @PreAuthorize("@el.check('treatmentothermedication:del')")
    public ResponseEntity<Object> deleteTreatmentothermedication(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        treatmentothermedicationService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}