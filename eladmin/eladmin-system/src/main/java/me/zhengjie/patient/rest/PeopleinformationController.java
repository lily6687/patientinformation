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
import me.zhengjie.patient.domain.Peopleinformation;
import me.zhengjie.patient.service.PeopleinformationService;
import me.zhengjie.patient.service.dto.PeopleinformationQueryCriteria;
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
import me.zhengjie.patient.service.dto.PeopleinformationDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "人口学资料")
@RequestMapping("/api/peopleinformation")
public class PeopleinformationController {

    private final PeopleinformationService peopleinformationService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('peopleinformation:list')")
    public void exportPeopleinformation(HttpServletResponse response, PeopleinformationQueryCriteria criteria) throws IOException {
        peopleinformationService.download(peopleinformationService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询人口学资料")
    @PreAuthorize("@el.check('peopleinformation:list')")
    public ResponseEntity<PageResult<PeopleinformationDto>> queryPeopleinformation(PeopleinformationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(peopleinformationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增人口学资料")
    @ApiOperation("新增人口学资料")
    @PreAuthorize("@el.check('peopleinformation:add')")
    public ResponseEntity<Object> createPeopleinformation(@Validated @RequestBody Peopleinformation resources){
        peopleinformationService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改人口学资料")
    @ApiOperation("修改人口学资料")
    @PreAuthorize("@el.check('peopleinformation:edit')")
    public ResponseEntity<Object> updatePeopleinformation(@Validated @RequestBody Peopleinformation resources){
        peopleinformationService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除人口学资料")
    @ApiOperation("删除人口学资料")
    @PreAuthorize("@el.check('peopleinformation:del')")
    public ResponseEntity<Object> deletePeopleinformation(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        peopleinformationService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}