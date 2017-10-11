package com.github.tb280320889.security.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by TangBin on 2017/10/11.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void whenListUserSuccess() throws Exception {
    mockMvc.perform(get("/user")
        .param("username", "sherlock")
        .param("age", "27")
        .param("ageTo", "100")
        .param("xxx", "yyy")
        .param("page", "2")
        .param("size", "12")
        .param("sort", "age,desc")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(3));

  }

  @Test
  public void whenGetUserByIdSuccess() throws Exception {
    mockMvc.perform(get("/user/1")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("alessio"));
  }

  @Test
  public void whenGetUserByIdFail() throws Exception {
    mockMvc.perform(get("/user/a")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is4xxClientError());
  }
}
