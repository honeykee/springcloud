package com.honeykee.consumer.controller;

import com.honeykee.common.pojo.module.result.GeneralContentResult;
import com.honeykee.common.utils.result.ResultCode;
import com.honeykee.common.utils.result.ResultDesc;
import com.honeykee.common.utils.result.ResultUtil;
import com.honeykee.consumer.entity.TableAEntity;
import com.honeykee.consumer.service.TableAService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-05 13:57
 * @since JDK 1.8
 */


@RestController
@Slf4j
@Api(tags = "TableA表Controller")
public class TableAController {

    @Autowired
    private TableAService tableAService;

    @ApiOperation( value = "insert 插入",notes = "note 插入")
    @GetMapping(value = "/noauth/tableA/record/get/{id}")
    public GeneralContentResult<TableAEntity> getRecordFromTableA( @PathVariable(value = "id") Long id ){

        TableAEntity record = tableAService.getRecord( id );
        tableAService.addRecord(null);
        return ResultUtil.buildGeneralContentResult( ResultCode.SUCCESS, ResultDesc.SUCCESS, record );
    }




    @ApiOperation( value = "update 更新" )
    @GetMapping(value = "/noauth/tableA/record/update")
    public GeneralContentResult<TableAEntity> updateRecordFromTableA( @RequestParam(value = "id") Long id,
                                                                      @RequestParam(value = "firstName") String firstName,
                                                                      @RequestParam(value = "lastName") String lastName,
                                                                      @RequestParam(value = "address") String address ,
                                                                      @RequestParam(value = "newId") Long newId ) {

        tableAService.updateRecord(id , firstName, lastName ,address, newId );
        return ResultUtil.buildGeneralContentResult( ResultCode.SUCCESS, ResultDesc.SUCCESS, null );
    }

}
