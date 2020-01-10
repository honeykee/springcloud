package com.honeykee.user.service.service;

import com.honeykee.common.pojo.module.user.UserPrincipal;
import com.honeykee.common.utils.mapper.JsonMapper;
import com.honeykee.user.service.entity.User;
import com.honeykee.user.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-01 17:26
 * @since JDK 1.8
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private JsonMapper jsonMapper = JsonMapper.nonEmptyMapper() ;

    public UserPrincipal getUserByUserName( String userName){
        User user = userRepository.findByUsernameEquals( userName );
        if(user == null ){
            return null;
        }

        log.info( "query user result : {}", jsonMapper.toJson( user )  );
        UserPrincipal userPrincipal = mapUserToUserPrincipal(user);
        return userPrincipal;
    }

    private UserPrincipal mapUserToUserPrincipal( User user ) {

        UserPrincipal userPrincipal = modelMapper.map( user, UserPrincipal.class );
        userPrincipal.setUserId( String.valueOf( user.getId() ) );

        log.info( " mapper user Principal ... {}", userPrincipal );
        return userPrincipal;
    }
}
