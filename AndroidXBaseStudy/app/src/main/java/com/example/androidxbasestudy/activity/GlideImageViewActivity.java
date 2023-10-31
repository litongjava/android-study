package com.example.androidxbasestudy.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.example.androidxbasestudy.R;
import com.example.androidxbasestudy.glide.GlideApp;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.ping.permission.PermissionCode;
import com.ping.permission.PermissionUtils;

import java.util.logging.Logger;

@FindViewByIdLayout(R.layout.activity_glide_image_view)
public class GlideImageViewActivity extends AppCompatActivity {

  @FindViewById(R.id.imageView01)
  private ImageView imageView01;

  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
    //initView();
  }

  @OnClick(R.id.btnLoadImageFromNetwork)
  public void btnLoadImageFromNetworkOnClick(View viw) {
    //从网络上加载图片
    String imageUrl = "https://scpic.chinaz.net/files/pic/pic9/202201/apic38055.jpg";
    //绑定对象的生命周期,支持activity,fragment,这里绑定activity,退出activity之后,图片自动销毁
    RequestManager requestManager = Glide.with(this);
    RequestBuilder<Drawable> requestBuilder = requestManager.load(imageUrl);
    requestBuilder.into(imageView01);
  }

  @OnClick(R.id.btnRequestOperation)
  public void btnRequestOperationOnClick(View view) {
    RequestOptions requestOptions = new RequestOptions();
    //设置palceHolder图片
    requestOptions.placeholder(R.drawable.hold)
      .error(R.drawable.error)
      .fallback(R.drawable.empty);
    //指定加载图片大小
    RequestOptions override = requestOptions.override(100, 100);
    String imageUrl = "https://scpic.chinaz.net/apic38055.jpg";
    Glide.with(this).load(imageUrl).apply(requestOptions).into(imageView01);
  }

  @OnClick(R.id.btnTransition)
  public void btnTransitionOnClick(View view) {
    String imageUrl = "https://scpic.chinaz.net/files/pic/pic9/202201/apic38055.jpg";
    //交叉淡入效果（避免显示占位图）
    DrawableCrossFadeFactory factory = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
    //指定交叉变化效果
    DrawableTransitionOptions transition = DrawableTransitionOptions.withCrossFade(factory);
    Glide.with(this).load(imageUrl).placeholder(R.drawable.hold).transition(transition).into(imageView01);
  }

  @OnClick(R.id.btnTransform)
  public void btnTransformOnClick(View view){
    String imageUrl = "https://scpic.chinaz.net/files/pic/pic9/202201/apic38055.jpg";
    Glide.with(this).load(imageUrl).transform(new RoundedCorners(30)).into(imageView01);
  }

  @OnClick(R.id.btnGlideApp)
  public void btnGlideAppOnClick(View view){
    String imageUrl = "https://scpic.chinaz.net/files/pic/pic9/202201/apic38055.jpg";
    GlideApp.with(this).load(imageUrl).placeholder(R.drawable.hold).into(imageView01);
  }

  @OnClick(R.id.btnGlideExtension)
  public void btnGlideExtensionOnClick(View view){
    String imageUrl = "https://scpic.chinaz.net/files/pic/pic9/202201/apic38055.jpg";
    GlideApp.with(this).load(imageUrl).
      placeholder(R.drawable.hold).error(R.drawable.error).fallback(R.drawable.empty).into(imageView01);
    //GlideApp.with(this).load(imageUrl).defaultImg().into(imageView01);
    GlideApp.with(this).load(imageUrl).into(imageView01);
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void initView() {
    boolean isGranted = this.checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    if (isGranted) {

    } else {
      PermissionUtils.requestPermission(this, Manifest.permission.INTERNET, PermissionCode.INFTERNET);
    }
  }
}