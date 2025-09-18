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
import me.zhengjie.patient.domain.Historyallergy;
import me.zhengjie.patient.service.HistoryallergyService;
import me.zhengjie.patient.service.dto.HistoryallergyQueryCriteria;
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
import me.zhengjie.patient.service.dto.HistoryallergyDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "过敏史")
@RequestMapping("/api/historyallergy")
public class HistoryallergyController {

    private final HistoryallergyService historyallergyService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('historyallergy:list')")
    public void exportHistoryallergy(HttpServletResponse response, HistoryallergyQueryCriteria criteria) throws IOException {
        historyallergyService.download(historyallergyService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询过敏史")
    @PreAuthorize("@el.check('historyallergy:list')")
    public ResponseEntity<PageResult<HistoryallergyDto>> queryHistoryallergy(HistoryallergyQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(historyallergyService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增过敏史")
    @ApiOperation("新增过敏史")
    @PreAuthorize("@el.check('historyallergy:add')")
    public ResponseEntity<Object> createHistoryallergy(@Validated @RequestBody Historyallergy resources){
        historyallergyService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改过敏史")
    @ApiOperation("修改过敏史")
    @PreAuthorize("@el.check('historyallergy:edit')")
    public ResponseEntity<Object> updateHistoryallergy(@Validated @RequestBody Historyallergy resources){
        historyallergyService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除过敏史")
    @ApiOperation("删除过敏史")
    @PreAuthorize("@el.check('historyallergy:del')")
    public ResponseEntity<Object> deleteHistoryallergy(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        historyallergyService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}