package com.github.tb280320889.security.demo.controller;

import lombok.extern.slf4j.Slf4j;

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

import java.util.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by TangBin on 2017/10/11.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
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
    final String result = mockMvc.perform(get("/user")
        .param("username", "sherlock")
        .param("age", "27")
        .param("ageTo", "100")
        .param("xxx", "yyy")
        .param("page", "2")
        .param("size", "12")
        .param("sort", "age,desc")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(3))
        .andReturn().getResponse().getContentAsString();

    log.warn("json : {} ", result);

  }

  @Test
  public void whenGetUserByIdSuccess() throws Exception {
    final String result = mockMvc.perform(get("/user/1")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("alessio"))
        .andReturn().getResponse().getContentAsString();

    log.warn("json : {} ", result);
  }

  @Test
  public void whenGetUserByIdFail() throws Exception {
    mockMvc.perform(get("/user/a")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void whenCreateSuccess() throws Exception {


    final Long timeStamp = Calendar.getInstance().getTime().getTime();

    log.warn("ts : {} ", timeStamp);
    final String content = "{\"username\":\"alessio\",\"password\":null,\"id\":1,\"birthday\":  " + timeStamp + '}';
    final String result = mockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(content))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1"))
        .andReturn().getResponse().getContentAsString();

    log.warn("result : {} ", result);
  }
}
