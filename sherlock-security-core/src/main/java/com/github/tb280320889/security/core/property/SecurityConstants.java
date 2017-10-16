package com.github.tb280320889.security.core.property;

/**
 * Created by TangBin on 2017/10/15.
 */

public interface SecurityConstants {


  /**
   * default validate code url prefix
   */
  String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/validate_code";
  /**
   * 当请求需要身份认证时，默认跳转的url
   */
  String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
  /**
   * 默认的用户名密码登录请求处理url
   */
  String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
  /**
   * 默认的手机验证码登录请求处理url
   */
  String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
  /**
   * default login page url
   */
  String DEFAULT_LOGIN_PAGE_URL = "/login/basic-login.html";
  /**
   * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
   */
  String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
  /**
   * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
   */
  String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
  /**
   * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
   */
  String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
  /**
   * session失效默认的跳转地址
   */
  String DEFAULT_SESSION_INVALID_URL = "/session/invalid";


}
