package com.example.androidxbasestudy.glide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.BaseRequestOptions;
import com.example.androidxbasestudy.R;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/29
 */
@GlideExtension
public class MyAppGlideExtension {
  /**
   * 私有构造类
   */
  private MyAppGlideExtension() {
  }

  @GlideOption
  public static BaseRequestOptions<?> defaultImg(BaseRequestOptions<?> options) {
    return options.placeholder(R.drawable.hold).
      error(R.drawable.error).
      fallback(R.drawable.empty);
  }
}
