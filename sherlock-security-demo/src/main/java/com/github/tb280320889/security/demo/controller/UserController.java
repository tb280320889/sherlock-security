package com.github.tb280320889.security.demo.controller;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.tb280320889.security.demo.domain.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangBin on 2017/10/11.
 */

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

  /**
   * @param userQueryCondition
   * @return
   */
  @GetMapping
  @JsonView(User.UserSimpleView.class)
  public List<User> listUser(UserQueryCondition userQueryCondition, @PageableDefault(page = 1, size = 15, sort = "username,asc") Pageable pageable) {

    log.warn("userQueryCondition  : {} ", userQueryCondition);
    log.warn("pageSize : {} ", pageable.getPageSize());
    log.warn("pageNumber : {} ", pageable.getPageNumber());
    log.warn("sort : {} ", pageable.getSort());
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
  @GetMapping("/{id:\\d+}")
  @JsonView(User.UserDetailView.class)
  public User getUserById(@PathVariable String id) {
    final User user = new User();
    user.setUsername("alessio");
    return user;
  }


}
