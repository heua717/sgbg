package com.sgbg.api.controller;

import com.sgbg.api.response.UserRes;
import com.sgbg.service.interfaces.IUserService;
import com.sgbg.domain.User;
import com.sgbg.common.exception.EmptyListException;
import com.sgbg.common.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        Assert.notNull(userService, "userService 개체가 반드시 필요!");
        this.userService = userService;
    }

//    @Operation()
//    @GetMapping("/")
//    public UserRes get(@PathVariable int id) {
//
//            logger.error("NOT FOUND ID: ", id);
//            throw new NotFoundException(id + " 회원 정보를 찾을 수 없습니다.");
//        }
//    }
}
