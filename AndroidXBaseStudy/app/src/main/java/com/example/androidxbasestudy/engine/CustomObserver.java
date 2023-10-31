package com.example.androidxbasestudy.engine;


import com.example.androidxbasestudy.model.ResponseResult;
import com.example.androidxbasestudy.model.SuccessBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/24
 */
public abstract class CustomObserver implements Observer<ResponseResult> {

  public abstract void success(SuccessBean successBean);

  public abstract void error(String message);

  @Override
  public void onSubscribe(Disposable d) {

  }

  @Override
  public void onNext(ResponseResult responseResult) {
    SuccessBean data = responseResult.getData();
    if(data !=null){
      this.success(data);
    }else{
      this.error("请求失败,请检查日志");
    }
  }

  @Override
  public void onError(Throwable e) {
    error("出现异常:"+e.getMessage());
  }

  @Override
  public void onComplete() {

  }
}
