package com.github.tb280320889.security.demo.wiremock;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

/**
 * Created by TangBin on 2017/10/12.
 */

public class MockServer {

  public static void main(String[] args) throws IOException {
    final int port = 8062;
    configureFor(port);
    removeAllMappings();

    mockGet("/order/1", "order1");

  }


  /**
   * @param testUrl
   * @param fileName
   * @throws IOException
   */
  private static void mockGet(String testUrl, String fileName) throws IOException {
    final ClassPathResource resource = new ClassPathResource("wiremock/mockresponse/" + fileName + ".json");
    final String body = FileUtils.readFileToString(resource.getFile(), Charset.forName("UTF-8"));

    stubFor(
        get(urlPathEqualTo(testUrl))
            .willReturn(aResponse().withBody(body).withStatus(200))
    );
  }

}
