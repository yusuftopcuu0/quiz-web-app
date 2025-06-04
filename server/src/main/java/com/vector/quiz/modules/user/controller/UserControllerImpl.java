package com.vector.quiz.modules.user.controller;

import com.vector.quiz.common.constants.Paths;
import com.vector.quiz.common.controller.impl.RestBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Paths.PUBLIC_BASE_PATH + "/user")
public class UserControllerImpl extends RestBaseController implements IUserController {


}
