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
import me.zhengjie.patient.domain.Urinalysis;
import me.zhengjie.patient.service.UrinalysisService;
import me.zhengjie.patient.service.dto.UrinalysisQueryCriteria;
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
import me.zhengjie.patient.service.dto.UrinalysisDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "尿常规")
@RequestMapping("/api/urinalysis")
public class UrinalysisController {

    private final UrinalysisService urinalysisService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('urinalysis:list')")
    public void exportUrinalysis(HttpServletResponse response, UrinalysisQueryCriteria criteria) throws IOException {
        urinalysisService.download(urinalysisService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询尿常规")
    @PreAuthorize("@el.check('urinalysis:list')")
    public ResponseEntity<PageResult<UrinalysisDto>> queryUrinalysis(UrinalysisQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(urinalysisService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增尿常规")
    @ApiOperation("新增尿常规")
    @PreAuthorize("@el.check('urinalysis:add')")
    public ResponseEntity<Object> createUrinalysis(@Validated @RequestBody Urinalysis resources){
        urinalysisService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改尿常规")
    @ApiOperation("修改尿常规")
    @PreAuthorize("@el.check('urinalysis:edit')")
    public ResponseEntity<Object> updateUrinalysis(@Validated @RequestBody Urinalysis resources){
        urinalysisService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除尿常规")
    @ApiOperation("删除尿常规")
    @PreAuthorize("@el.check('urinalysis:del')")
    public ResponseEntity<Object> deleteUrinalysis(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        urinalysisService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}