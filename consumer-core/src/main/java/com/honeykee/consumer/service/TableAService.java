package com.honeykee.consumer.service;

import com.honeykee.common.utils.aop.annotation.AroundLog;
import com.honeykee.common.utils.mapper.JsonMapper;
import com.honeykee.consumer.entity.TableAEntity;
import com.honeykee.consumer.repository.TableARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-05 13:55
 * @since JDK 1.8
 */

@Slf4j
@Service
public class TableAService {

    private JsonMapper mapper = JsonMapper.nonDefaultMapper();

    @Autowired
    private TableARepository tableARepository;

    public TableAEntity getRecord( Long id){
        Optional< TableAEntity > optional = tableARepository.findById( id );
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @AroundLog(description = "新增Record", printResult = true )
    public void addRecord(TableAEntity tableAEntity){
        System.out.println("------------------");
    }


    @Transactional(rollbackFor = Exception.class)
    public void updateRecord(Long id, String firstName, String lastName, String address,Long newId ){

        TableAEntity newEntity = new TableAEntity();
        newEntity.setId( newId );
        newEntity.setFirstName( "111" );
        newEntity.setLastName( "222" );
        newEntity.setAddress( "333" );
        tableARepository.saveAndFlush( newEntity );
        log.info( "[zcs]save newEntity... " );

        TableAEntity entity = tableARepository.getOne( id );
        entity.setFirstName( firstName );
        entity.setLastName( lastName );
        entity.setAddress( address );

        //one duplicate key update,并没有
        tableARepository.saveAndFlush( entity );
        log.info( "[zcs]save entity... " );


        //@Lock配合@Transactional使用 for update
        List< TableAEntity > allRecord = tableARepository.findAllByFirstNameAndLastName( firstName, lastName );
        allRecord.stream().forEach( entityTmp -> {
            entityTmp.setFirstName( firstName );
            entityTmp.setLastName( lastName );
            entityTmp.setAddress( address );
        } );

        tableARepository.saveAll( allRecord );
        log.info( "[zcs]save allRecord... size :[{}]" , allRecord.size() );

//        log.info( "[zcs] update success :[{}]" , mapper.toJson( allRecord ) );
    }

}
