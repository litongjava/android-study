package com.litongjava.criminal.intent.criminalintentjava.fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.criminal.intent.criminalintentjava.R;
import com.litongjava.criminal.intent.criminalintentjava.utils.PictureUtils;

import java.io.File;

public class ImageDialogFragment extends DialogFragment {

  public static final String ARG_IMAGE = "image";
  private File mPhotoFile;

  @FindViewById(R.id.dialog_image)
  private ImageView imageView;

  public static ImageDialogFragment newInstance(File mPhotoFile) {
    Bundle args = new Bundle();
    args.putSerializable(ARG_IMAGE, mPhotoFile);
    ImageDialogFragment dialog = new ImageDialogFragment();
    dialog.setArguments(args);
    return dialog;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_image, null);
    ViewInjectUtils.injectView(v, this);

    mPhotoFile = (File) getArguments().getSerializable(ARG_IMAGE);

    updatePhotoView();
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setView(v);
    final AlertDialog dialog = builder.create();
    v.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.cancel();
      }
    });
    return dialog;
  }

  private void updatePhotoView() {
    if (mPhotoFile == null || !mPhotoFile.exists()) {
      imageView.setImageDrawable(null);
    } else {
      Bitmap bitmap= BitmapFactory.decodeFile(mPhotoFile.getAbsolutePath());
      imageView.setImageBitmap(bitmap);
    }
  }
}
