package com.litongjava.criminal.intent.criminalintentjava;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.litongjava.criminal.intent.criminalintentjava.activity.SingleFragmentAcitvity;
import com.litongjava.criminal.intent.criminalintentjava.fragment.CrimeFragment;

import java.io.Serializable;
import java.util.UUID;

public class CrimeActivity extends SingleFragmentAcitvity {
  public static final String EXTRA_CRIME_ID="com.litongjava.criminal.crime_id";
  public static Intent newIntent(Context packageContext, UUID crimeId) {
    Intent intent = new Intent(packageContext, CrimeActivity.class);
    intent.putExtra(EXTRA_CRIME_ID, crimeId);
    return intent;
  }
  @Override
  protected Fragment createFragment() {
    UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
    return CrimeFragment.newInstance(crimeId);
  }
}