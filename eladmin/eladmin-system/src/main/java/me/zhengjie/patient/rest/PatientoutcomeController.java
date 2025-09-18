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
import me.zhengjie.patient.domain.Patientoutcome;
import me.zhengjie.patient.service.PatientoutcomeService;
import me.zhengjie.patient.service.dto.PatientoutcomeQueryCriteria;
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
import me.zhengjie.patient.service.dto.PatientoutcomeDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-11
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "治疗结局")
@RequestMapping("/api/patientoutcome")
public class PatientoutcomeController {

    private final PatientoutcomeService patientoutcomeService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('patientoutcome:list')")
    public void exportPatientoutcome(HttpServletResponse response, PatientoutcomeQueryCriteria criteria) throws IOException {
        patientoutcomeService.download(patientoutcomeService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询治疗结局")
    @PreAuthorize("@el.check('patientoutcome:list')")
    public ResponseEntity<PageResult<PatientoutcomeDto>> queryPatientoutcome(PatientoutcomeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(patientoutcomeService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增治疗结局")
    @ApiOperation("新增治疗结局")
    @PreAuthorize("@el.check('patientoutcome:add')")
    public ResponseEntity<Object> createPatientoutcome(@Validated @RequestBody Patientoutcome resources){
        patientoutcomeService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改治疗结局")
    @ApiOperation("修改治疗结局")
    @PreAuthorize("@el.check('patientoutcome:edit')")
    public ResponseEntity<Object> updatePatientoutcome(@Validated @RequestBody Patientoutcome resources){
        patientoutcomeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除治疗结局")
    @ApiOperation("删除治疗结局")
    @PreAuthorize("@el.check('patientoutcome:del')")
    public ResponseEntity<Object> deletePatientoutcome(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        patientoutcomeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}