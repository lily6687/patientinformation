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
import me.zhengjie.patient.domain.Abgexam;
import me.zhengjie.patient.service.AbgexamService;
import me.zhengjie.patient.service.dto.AbgexamQueryCriteria;
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
import me.zhengjie.patient.service.dto.AbgexamDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-11
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "血气分析")
@RequestMapping("/api/abgexam")
public class AbgexamController {

    private final AbgexamService abgexamService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('abgexam:list')")
    public void exportAbgexam(HttpServletResponse response, AbgexamQueryCriteria criteria) throws IOException {
        abgexamService.download(abgexamService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询血气分析")
    @PreAuthorize("@el.check('abgexam:list')")
    public ResponseEntity<PageResult<AbgexamDto>> queryAbgexam(AbgexamQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(abgexamService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增血气分析")
    @ApiOperation("新增血气分析")
    @PreAuthorize("@el.check('abgexam:add')")
    public ResponseEntity<Object> createAbgexam(@Validated @RequestBody Abgexam resources){
        abgexamService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改血气分析")
    @ApiOperation("修改血气分析")
    @PreAuthorize("@el.check('abgexam:edit')")
    public ResponseEntity<Object> updateAbgexam(@Validated @RequestBody Abgexam resources){
        abgexamService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除血气分析")
    @ApiOperation("删除血气分析")
    @PreAuthorize("@el.check('abgexam:del')")
    public ResponseEntity<Object> deleteAbgexam(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        abgexamService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}