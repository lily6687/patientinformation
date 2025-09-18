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
import me.zhengjie.patient.domain.Combinedmedication;
import me.zhengjie.patient.service.CombinedmedicationService;
import me.zhengjie.patient.service.dto.CombinedmedicationQueryCriteria;
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
import me.zhengjie.patient.service.dto.CombinedmedicationDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "合并用药")
@RequestMapping("/api/combinedmedication")
public class CombinedmedicationController {

    private final CombinedmedicationService combinedmedicationService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('combinedmedication:list')")
    public void exportCombinedmedication(HttpServletResponse response, CombinedmedicationQueryCriteria criteria) throws IOException {
        combinedmedicationService.download(combinedmedicationService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询合并用药")
    @PreAuthorize("@el.check('combinedmedication:list')")
    public ResponseEntity<PageResult<CombinedmedicationDto>> queryCombinedmedication(CombinedmedicationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(combinedmedicationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增合并用药")
    @ApiOperation("新增合并用药")
    @PreAuthorize("@el.check('combinedmedication:add')")
    public ResponseEntity<Object> createCombinedmedication(@Validated @RequestBody Combinedmedication resources){
        combinedmedicationService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改合并用药")
    @ApiOperation("修改合并用药")
    @PreAuthorize("@el.check('combinedmedication:edit')")
    public ResponseEntity<Object> updateCombinedmedication(@Validated @RequestBody Combinedmedication resources){
        combinedmedicationService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除合并用药")
    @ApiOperation("删除合并用药")
    @PreAuthorize("@el.check('combinedmedication:del')")
    public ResponseEntity<Object> deleteCombinedmedication(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        combinedmedicationService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}