package com.github.tb280320889.security.demo.controller;

import com.github.tb280320889.security.demo.domain.FileInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by TangBin on 2017/10/12.
 */

@RestController
@RequestMapping("file")
@Slf4j
public class FileController {


  /**
   * @param file
   * @return
   */
  @PostMapping
  public FileInfo uploadFile(MultipartFile file) {

    log.info("file name : {} ", file.getName());
    log.info("file original name : {} ", file.getOriginalFilename());
    log.info("file size : {} ", file.getSize());

    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    final String path = this.getClass().getResource("").getPath();
    log.info("path : {} ", path);
    final File localfile = new File(path, dateFormat.format(new Date()) + ".txt");

    //     file.getInputStream()

    try {
      file.transferTo(localfile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new FileInfo(localfile.getAbsolutePath());
  }

  /**
   * @param id
   * @param httpServletRequest
   * @param httpServletResponse
   */
  @GetMapping("/{id}")
  public void download(@PathVariable String id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    try (FileInputStream fileInputStream = new FileInputStream(new File("", id + ".txt"));
         final ServletOutputStream outputStream = httpServletResponse.getOutputStream()
    ) {
      httpServletResponse.setContentType("application/x-download");
      httpServletResponse.addHeader("Content-Disposition", "attachment;filename-test.txt");
      IOUtils.copy(fileInputStream, outputStream);
      outputStream.flush();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }


}
