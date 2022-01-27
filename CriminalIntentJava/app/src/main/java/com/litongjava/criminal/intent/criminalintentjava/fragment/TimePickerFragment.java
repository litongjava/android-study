package com.litongjava.criminal.intent.criminalintentjava.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import java.util.Date;

public class TimePickerFragment extends DialogFragment {


  public static TimePickerFragment newInstance(Date date){
    Bundle args = new Bundle();
    args.putSerializable(DatePickerFragment.EXTRA_DATE,date);
    TimePickerFragment fragment = new TimePickerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {

    return super.onCreateDialog(savedInstanceState);
  }
}
