package com.vector.quiz.modules.user.controller;

import com.vector.quiz.common.config.ApiPaths;
import com.vector.quiz.common.controller.impl.RestBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.User.ROOT)
public class UserControllerImpl extends RestBaseController implements IUserController {


}
