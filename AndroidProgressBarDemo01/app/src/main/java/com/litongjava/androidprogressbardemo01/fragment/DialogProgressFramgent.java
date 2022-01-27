package com.litongjava.androidprogressbardemo01.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.litongjava.androidprogressbardemo01.R;

public class DialogProgressFramgent extends DialogFragment {
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    return super.onCreateDialog(savedInstanceState);

  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //去掉Dialog的标题部分
    getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
    //dialog背景色变为透明
    getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    View v = inflater.inflate(R.layout.dialog_progress, null);
    return v;
  }
}

