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
import me.zhengjie.patient.domain.Ecgexam;
import me.zhengjie.patient.service.EcgexamService;
import me.zhengjie.patient.service.dto.EcgexamQueryCriteria;
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
import me.zhengjie.patient.service.dto.EcgexamDto;

/**
* @website https://eladmin.vip
* @author me.zhengjie
* @date 2025-09-11
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "心电图")
@RequestMapping("/api/ecgexam")
public class EcgexamController {

    private final EcgexamService ecgexamService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('ecgexam:list')")
    public void exportEcgexam(HttpServletResponse response, EcgexamQueryCriteria criteria) throws IOException {
        ecgexamService.download(ecgexamService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询心电图")
    @PreAuthorize("@el.check('ecgexam:list')")
    public ResponseEntity<PageResult<EcgexamDto>> queryEcgexam(EcgexamQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(ecgexamService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增心电图")
    @ApiOperation("新增心电图")
    @PreAuthorize("@el.check('ecgexam:add')")
    public ResponseEntity<Object> createEcgexam(@Validated @RequestBody Ecgexam resources){
        ecgexamService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改心电图")
    @ApiOperation("修改心电图")
    @PreAuthorize("@el.check('ecgexam:edit')")
    public ResponseEntity<Object> updateEcgexam(@Validated @RequestBody Ecgexam resources){
        ecgexamService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除心电图")
    @ApiOperation("删除心电图")
    @PreAuthorize("@el.check('ecgexam:del')")
    public ResponseEntity<Object> deleteEcgexam(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        ecgexamService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}