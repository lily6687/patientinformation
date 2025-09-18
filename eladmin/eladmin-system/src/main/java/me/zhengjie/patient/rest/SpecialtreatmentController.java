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
import me.zhengjie.patient.domain.Specialtreatment;
import me.zhengjie.patient.service.SpecialtreatmentService;
import me.zhengjie.patient.service.dto.SpecialtreatmentQueryCriteria;
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
import me.zhengjie.patient.service.dto.SpecialtreatmentDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "治疗情况_特殊治疗")
@RequestMapping("/api/specialtreatment")
public class SpecialtreatmentController {

    private final SpecialtreatmentService specialtreatmentService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('specialtreatment:list')")
    public void exportSpecialtreatment(HttpServletResponse response, SpecialtreatmentQueryCriteria criteria) throws IOException {
        specialtreatmentService.download(specialtreatmentService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询治疗情况_特殊治疗")
    @PreAuthorize("@el.check('specialtreatment:list')")
    public ResponseEntity<PageResult<SpecialtreatmentDto>> querySpecialtreatment(SpecialtreatmentQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(specialtreatmentService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增治疗情况_特殊治疗")
    @ApiOperation("新增治疗情况_特殊治疗")
    @PreAuthorize("@el.check('specialtreatment:add')")
    public ResponseEntity<Object> createSpecialtreatment(@Validated @RequestBody Specialtreatment resources){
        specialtreatmentService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改治疗情况_特殊治疗")
    @ApiOperation("修改治疗情况_特殊治疗")
    @PreAuthorize("@el.check('specialtreatment:edit')")
    public ResponseEntity<Object> updateSpecialtreatment(@Validated @RequestBody Specialtreatment resources){
        specialtreatmentService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除治疗情况_特殊治疗")
    @ApiOperation("删除治疗情况_特殊治疗")
    @PreAuthorize("@el.check('specialtreatment:del')")
    public ResponseEntity<Object> deleteSpecialtreatment(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        specialtreatmentService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}