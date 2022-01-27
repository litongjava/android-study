package com.litongjava.criminal.intent.criminalintentjava.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.criminal.intent.criminalintentjava.R;
import com.litongjava.criminal.intent.criminalintentjava.fragment.CrimeFragment;
import com.litongjava.criminal.intent.criminalintentjava.model.Crime;
import com.litongjava.criminal.intent.criminalintentjava.model.CrimeLab;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


public class CrimePagerActivity extends FragmentActivity {

  @FindViewById(R.id.activity_crime_pager_view_pager)
  private ViewPager mViewPager;
  private List<Crime> mCrimes;

  public static final String EXTRA_CRIME_ID="com.litongjava.criminal.crime_id";
  public static Intent newIntent(Context packageContext, UUID crimeId) {
    Intent intent = new Intent(packageContext, CrimePagerActivity.class);
    intent.putExtra(EXTRA_CRIME_ID, crimeId);
    return intent;
  }
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_crime_pager);
    ViewInjectUtils.injectView(this,this);

    mCrimes = CrimeLab.get(this).getCrimes();

    FragmentManager fragmentManager = getSupportFragmentManager();
    mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
      @Override
      public Fragment getItem(int position) {
        Crime crime = mCrimes.get(position);
        return CrimeFragment.newInstance(crime.getId());
      }
      @Override
      public int getCount() {
        return mCrimes.size();
      }
    });
    UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
    for (int i = 0; i < mCrimes.size(); i++) {
      if (mCrimes.get(i).getId().equals(crimeId)) {
        mViewPager.setCurrentItem(i);
        break;
      }
    }
  }
}
