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
import me.zhengjie.patient.domain.Immutests;
import me.zhengjie.patient.service.ImmutestsService;
import me.zhengjie.patient.service.dto.ImmutestsQueryCriteria;
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
import me.zhengjie.patient.service.dto.ImmutestsDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "免疫学指标")
@RequestMapping("/api/immutests")
public class ImmutestsController {

    private final ImmutestsService immutestsService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('immutests:list')")
    public void exportImmutests(HttpServletResponse response, ImmutestsQueryCriteria criteria) throws IOException {
        immutestsService.download(immutestsService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询免疫学指标")
    @PreAuthorize("@el.check('immutests:list')")
    public ResponseEntity<PageResult<ImmutestsDto>> queryImmutests(ImmutestsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(immutestsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增免疫学指标")
    @ApiOperation("新增免疫学指标")
    @PreAuthorize("@el.check('immutests:add')")
    public ResponseEntity<Object> createImmutests(@Validated @RequestBody Immutests resources){
        immutestsService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改免疫学指标")
    @ApiOperation("修改免疫学指标")
    @PreAuthorize("@el.check('immutests:edit')")
    public ResponseEntity<Object> updateImmutests(@Validated @RequestBody Immutests resources){
        immutestsService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除免疫学指标")
    @ApiOperation("删除免疫学指标")
    @PreAuthorize("@el.check('immutests:del')")
    public ResponseEntity<Object> deleteImmutests(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        immutestsService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}