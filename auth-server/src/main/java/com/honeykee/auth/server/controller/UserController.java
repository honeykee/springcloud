package com.honeykee.auth.server.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

/**
 * @author zcs
 */
@RestController
@RequestMapping
@Api(tags = "Auth-server-User")
public class UserController {

	@ApiOperation( value = "user-api")
	@RequestMapping(value = "/user/current", method = RequestMethod.GET)
	@ApiImplicitParam(name = "Authorization", paramType = "header", required = true, value = "Token", dataType = "String", defaultValue = "bearer ")
	public Principal getUser(Principal principal) {
		return principal;
	}

}
