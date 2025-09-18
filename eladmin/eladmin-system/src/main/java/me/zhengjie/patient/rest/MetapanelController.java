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
import me.zhengjie.patient.domain.Metapanel;
import me.zhengjie.patient.service.MetapanelService;
import me.zhengjie.patient.service.dto.MetapanelQueryCriteria;
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
import me.zhengjie.patient.service.dto.MetapanelDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "血生化")
@RequestMapping("/api/metapanel")
public class MetapanelController {

    private final MetapanelService metapanelService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('metapanel:list')")
    public void exportMetapanel(HttpServletResponse response, MetapanelQueryCriteria criteria) throws IOException {
        metapanelService.download(metapanelService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询血生化")
    @PreAuthorize("@el.check('metapanel:list')")
    public ResponseEntity<PageResult<MetapanelDto>> queryMetapanel(MetapanelQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(metapanelService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增血生化")
    @ApiOperation("新增血生化")
    @PreAuthorize("@el.check('metapanel:add')")
    public ResponseEntity<Object> createMetapanel(@Validated @RequestBody Metapanel resources){
        metapanelService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改血生化")
    @ApiOperation("修改血生化")
    @PreAuthorize("@el.check('metapanel:edit')")
    public ResponseEntity<Object> updateMetapanel(@Validated @RequestBody Metapanel resources){
        metapanelService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除血生化")
    @ApiOperation("删除血生化")
    @PreAuthorize("@el.check('metapanel:del')")
    public ResponseEntity<Object> deleteMetapanel(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        metapanelService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}