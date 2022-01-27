package com.litongjava.androidjavaannotationnofindviewbyid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FindViewById {
  /**
   * 保存view控件的id
   *
   * @return view控件id
   */
  int value();
}