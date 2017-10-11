package com.github.tb280320889.security.demo.controller;

import lombok.extern.slf4j.Slf4j;

import com.github.tb280320889.security.demo.domain.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangBin on 2017/10/11.
 */

@RestController
@Slf4j
public class UserController {


  /**
   * @param userQueryCondition
   * @return
   */
  @GetMapping("user")
  public List<User> listUser(UserQueryCondition userQueryCondition, @PageableDefault(page = 1, size = 15, sort = "username,asc") Pageable pageable) {

    log.info("userQueryCondition  : {} ", userQueryCondition);
    log.info("pageSize : {} ", pageable.getPageSize());
    log.info("pageNumber : {} ", pageable.getPageNumber());
    log.info("sort : {} ", pageable.getSort());
    final ArrayList<User> users = new ArrayList<>(5);
    users.add(new User());
    users.add(new User());
    users.add(new User());

    return users;
  }

  /**
   * @param id
   * @return
   */
  @GetMapping("user/{id:\\d+}")
  public User getUserById(@PathVariable String id) {
    final User user = new User();
    user.setUsername("alessio");
    return user;
  }


}
