package com.litongjava.android_view_inject_test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.android_view_inject_test.R;


public class PhotoGalleryFragment extends Fragment {
  @FindViewById(R.id.fragment_photo_gallery_recycler_view)
  private RecyclerView mPhotoRecyclerView;

  public static Fragment newInstance() {
    return new PhotoGalleryFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
    //mPhotoRecyclerView = (RecyclerView)view.findViewById(R.id.fragment_container);
    ViewInjectUtils.injectView(view, this);
    mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    return view;
  }
}