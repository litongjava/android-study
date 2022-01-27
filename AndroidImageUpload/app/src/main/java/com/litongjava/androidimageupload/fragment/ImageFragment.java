package com.litongjava.androidimageupload.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidimageupload.R;
import com.litongjava.androidimageupload.services.ImageService;
import com.litongjava.androidimageupload.utils.ToastUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageFragment extends Fragment {

  private static final int REQUEST_CODE_PICK = 0;
  //权限请求码
  private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;

  private ImageService imageService=new ImageService();
  private Uri uri;
  @FindViewById(R.id.btnChoose)
  private Button button;
  @FindViewById(R.id.btnUpload)
  private Button btnUpload;
  @FindViewById(R.id.imageViewShow)
  private ImageView imageViewShow;

  public static ImageFragment newInstance() {
    return new ImageFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_image, container, false);
    ViewInjectUtils.inject(view,this);
    ViewInjectUtils.injectOnClick(view,this);
    return view;
  }

  @OnClick(R.id.btnChoose)
  public void btnChooseOnClick(){
    log.info("开始选择图片");
    Intent intent = new Intent(Intent.ACTION_PICK, null);
    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
    startActivityForResult(intent, REQUEST_CODE_PICK);
  }
  @OnClick(R.id.btnUpload)
  public void btnUploadOnClick(){
    imageService.upload(imageViewShow);

  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE_PICK) {
      // 从相册返回的数据
      if (data != null) {
        // 得到图片的全路径
        uri = data.getData();
        log.info("uri:{}",uri);
        getPermission();
      }
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void getPermission() {
    //权限检查
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
      try {
        int hasPermission =getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        log.info("hasWriteContactsPermission:{}",hasPermission);
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
          //弹出授权对对话框
          String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
          requestPermissions(permissions,REQUEST_CODE_ASK_PERMISSIONS);
          //没有权限，结束
          return;
        }else {
          //有权限,执行操作
          updateImage();
        }
      } catch (Exception e) {
        e.printStackTrace();
        ToastUtils.defaultToast(getContext().getApplicationContext(), "权限异常");
      }
    }else{
      //低版本执行操作
      updateImage();
    }
  }

  private void updateImage() {
    imageViewShow.setImageURI(uri);
  }

  //回调函数，无论用户是否允许都会调用执行此方法
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case REQUEST_CODE_ASK_PERMISSIONS:
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          // Permission Granted 用户允许权限 继续执行（我这里执行的是二维码扫描，检查的是照相机权限）
          updateImage();
        } else {
          // Permission Denied 拒绝
          ToastUtils.defaultToast(this.getContext(),"获取权限失败");
        }
        break;
      default:
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
  }
}
