package com.litongjava.androidbasestudy.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.androidbasestudy.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Slf4j
@FindViewByIdLayout(R.layout.activity_notification)
public class NotificationActivity extends AppCompatActivity {

  private Logger log=LoggerFactory.getLogger(this.getClass());



  @FindViewById(R.id.btnSendNotifaction)
  private Button btnSendNotifaction;
  @FindViewById(R.id.btnCancelNotifaction)
  private Button btnCancelNotifaction;

  private NotificationManager notificationManager;
  private Notification notification;
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
    notificationManager = (NotificationManager) super.getSystemService(Context.NOTIFICATION_SERVICE);

    int sdkInt = Build.VERSION.SDK_INT;
    log.info("sdkInt:{}",sdkInt);
    //判断是否大于8.0版本
    if (sdkInt >= Build.VERSION_CODES.O) {
      //创建channdle
      NotificationChannel notificationChannel = new NotificationChannel("ping", "测试通知", NotificationManager.IMPORTANCE_HIGH);
      notificationManager.createNotificationChannel(notificationChannel);
    }
    Resources resources = super.getResources();
    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.ping_avatar);

    Intent intent = new Intent(this, ImageShowActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
    int red = Color.parseColor("#ff0000");
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "ping");
    notification= builder.setContentTitle("官方通知").setContentText("周游世界")
      .setSmallIcon(R.drawable.ic_android_black_24dp)
      .setLargeIcon(bitmap)
      .setColor(red)
      .setContentIntent(pendingIntent)
      .setAutoCancel(true)
      .build();

  }

  @OnClick(R.id.btnSendNotifaction)
  public void btnSendNotifactionOnClick() {
    notificationManager.notify(1,notification);
  }

  @OnClick(R.id.btnCancelNotifaction)
  public void btnCancelNotifactionOnClick() {
    notificationManager.cancel(1 );
  }
}
