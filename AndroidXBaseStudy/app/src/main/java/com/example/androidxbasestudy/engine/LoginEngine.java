package com.example.androidxbasestudy.engine;

import com.example.androidxbasestudy.model.ResponseResult;
import com.example.androidxbasestudy.model.SuccessBean;

import io.reactivex.Observable;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/24
 */
public class LoginEngine {
  public static Observable<ResponseResult> login(String username, String password) {
    ResponseResult responseResult = new ResponseResult();
    //模拟登录成功和登录失败
    if ("PingELee".equals(username) && "00000000".equals(password)) {
      //登录成功
      SuccessBean successBean = new SuccessBean(1, username);

      responseResult.setCode(200);
      responseResult.setMessage("登录成功");

      responseResult.setData(successBean);
    }
    //登录失败
    else {
      responseResult.setCode(404);
      responseResult.setMessage("登录失败");
      responseResult.setData(null);
    }
    return Observable.just(responseResult);
  }
}
