package com.litongjava.criminal.intent.criminalintentjava.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.criminal.intent.criminalintentjava.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
  private static final String ARG_DATE = "date";
  public static final String EXTRA_DATE = "com.litongjava.criminal.intent.date";


  @FindViewById(R.id.dialog_date_date_picker)
  private DatePicker mDatePicker;

  public static DatePickerFragment newInstance(Date date) {
    Bundle args = new Bundle();
    args.putSerializable(ARG_DATE, date);
    DatePickerFragment fragment = new DatePickerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
    ViewInjectUtils.injectView(v, this);

    Date date = (Date) getArguments().getSerializable(ARG_DATE);

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    mDatePicker.init(year, month, day, null);

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setView(v).setTitle(R.string.date_picker_title);
    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        int year = mDatePicker.getYear();
        int month = mDatePicker.getMonth();
        int day = mDatePicker.getDayOfMonth();
        Date date = new GregorianCalendar(year, month, day).getTime();
        sendResult(Activity.RESULT_OK, date);
      }
    });
    return builder.create();
  }

  private void sendResult(int resultCode, Date date) {
    if (getTargetFragment() == null) {
      return;
    }
    Intent intent = new Intent();
    intent.putExtra(EXTRA_DATE, date);
    getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
  }
}