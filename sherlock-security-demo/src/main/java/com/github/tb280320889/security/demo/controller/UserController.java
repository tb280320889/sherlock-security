package com.github.tb280320889.security.demo.controller;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.tb280320889.security.demo.domain.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
  @GetMapping("/{id:\\d+}")
  @JsonView(User.UserDetailView.class)
  public User getUserById(@PathVariable String id) {
    final User user = new User();
    user.setUsername("alessio");
    return user;
  }

  /**
   * @param user
   * @param bindingResult
   * @return
   */
  @PostMapping
  public User createUser(@Valid @RequestBody User user, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      bindingResult.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
    }

    Optional<Integer> id = Optional.ofNullable(user.getId());
    log.info(id.orElse(1).toString());
    log.info(user.getUsername());
    log.info(user.getPassword());

    user.setId(1);
    return user;
  }

  /**
   * @param user
   * @param bindingResult
   * @return
   */
  @PutMapping("/{id}")
  public User updateUser(@Valid @RequestBody User user, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      bindingResult.getAllErrors().forEach(error -> {
        final FieldError fieldError = (FieldError) error;
        log.error(fieldError.getField() + ' ' + fieldError.getDefaultMessage());
      });
    }

    Optional<Integer> id = Optional.ofNullable(user.getId());
    log.info(id.orElse(1).toString());
    log.info(user.getUsername());
    log.info(user.getPassword());

    user.setId(1);
    return user;
  }

  @DeleteMapping("/{id:\\d+}")
  public void deleteUser(@PathVariable String id) {
    log.info(id);
  }

}
