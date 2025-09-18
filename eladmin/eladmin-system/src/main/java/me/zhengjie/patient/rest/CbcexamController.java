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
import me.zhengjie.patient.domain.Cbcexam;
import me.zhengjie.patient.service.CbcexamService;
import me.zhengjie.patient.service.dto.CbcexamQueryCriteria;
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
import me.zhengjie.patient.service.dto.CbcexamDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-11
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "血常规")
@RequestMapping("/api/cbcexam")
public class CbcexamController {

    private final CbcexamService cbcexamService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('cbcexam:list')")
    public void exportCbcexam(HttpServletResponse response, CbcexamQueryCriteria criteria) throws IOException {
        cbcexamService.download(cbcexamService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询血常规")
    @PreAuthorize("@el.check('cbcexam:list')")
    public ResponseEntity<PageResult<CbcexamDto>> queryCbcexam(CbcexamQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(cbcexamService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增血常规")
    @ApiOperation("新增血常规")
    @PreAuthorize("@el.check('cbcexam:add')")
    public ResponseEntity<Object> createCbcexam(@Validated @RequestBody Cbcexam resources){
        cbcexamService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改血常规")
    @ApiOperation("修改血常规")
    @PreAuthorize("@el.check('cbcexam:edit')")
    public ResponseEntity<Object> updateCbcexam(@Validated @RequestBody Cbcexam resources){
        cbcexamService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除血常规")
    @ApiOperation("删除血常规")
    @PreAuthorize("@el.check('cbcexam:del')")
    public ResponseEntity<Object> deleteCbcexam(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        cbcexamService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}