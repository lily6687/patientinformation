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
import me.zhengjie.patient.domain.Imagingexam;
import me.zhengjie.patient.service.ImagingexamService;
import me.zhengjie.patient.service.dto.ImagingexamQueryCriteria;
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
import me.zhengjie.patient.service.dto.ImagingexamDto;

/**
* @website https://eladmin.vip
* @author zhengjie
* @date 2025-09-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "影像学检查")
@RequestMapping("/api/imagingexam")
public class ImagingexamController {

    private final ImagingexamService imagingexamService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('imagingexam:list')")
    public void exportImagingexam(HttpServletResponse response, ImagingexamQueryCriteria criteria) throws IOException {
        imagingexamService.download(imagingexamService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询影像学检查")
    @PreAuthorize("@el.check('imagingexam:list')")
    public ResponseEntity<PageResult<ImagingexamDto>> queryImagingexam(ImagingexamQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(imagingexamService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增影像学检查")
    @ApiOperation("新增影像学检查")
    @PreAuthorize("@el.check('imagingexam:add')")
    public ResponseEntity<Object> createImagingexam(@Validated @RequestBody Imagingexam resources){
        imagingexamService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改影像学检查")
    @ApiOperation("修改影像学检查")
    @PreAuthorize("@el.check('imagingexam:edit')")
    public ResponseEntity<Object> updateImagingexam(@Validated @RequestBody Imagingexam resources){
        imagingexamService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除影像学检查")
    @ApiOperation("删除影像学检查")
    @PreAuthorize("@el.check('imagingexam:del')")
    public ResponseEntity<Object> deleteImagingexam(@ApiParam(value = "传ID数组[]") @RequestBody Long[] ids) {
        imagingexamService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}