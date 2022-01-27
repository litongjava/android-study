package com.litongjava.criminal.intent.criminalintentjava.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.criminal.intent.criminalintentjava.CrimeActivity;
import com.litongjava.criminal.intent.criminalintentjava.R;
import com.litongjava.criminal.intent.criminalintentjava.activity.CrimePagerActivity;
import com.litongjava.criminal.intent.criminalintentjava.model.Crime;
import com.litongjava.criminal.intent.criminalintentjava.model.CrimeLab;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CrimeListFragment extends Fragment {
  private static final int REQUEST_CRIME = 1;

  private RecyclerView mCrimeRecyclerView;
  private CrimeAdapter mAdapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = createCrimeRecyclerView(inflater, container);
    //View view = inflater.inflate(R.layout.fragment_crime, container, false);
    return view;
  }

  @NonNull
  public View createCrimeRecyclerView(LayoutInflater inflater, @Nullable ViewGroup container) {
    View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
    log.info("fragment_crime:{}", view);

    mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
    log.info("mCrimeRecyclerView:{}", mCrimeRecyclerView);
    mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    updateUI();
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    updateUI();
  }

  private void updateUI() {
    CrimeLab crimeLab = CrimeLab.get(getActivity());
    List<Crime> crimes = crimeLab.getCrimes();
    if (mAdapter == null) {
      mAdapter = new CrimeAdapter(crimes);
      mCrimeRecyclerView.setAdapter(mAdapter);
    } else {
      mAdapter.notifyDataSetChanged();
    }
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragment_crime_list, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_itme_new_crime:
        Crime crime = new Crime();
        CrimeLab.get(getActivity()).addCrime(crime);
        //Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
        //startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CRIME) {
      log.info("data:{}",data);
    }
  }

  private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Crime mCrime;
    @FindViewById(R.id.list_item_crime_title_text_view)
    private TextView mTittleTextView;
    @FindViewById(R.id.list_item_crime_date_text_view)
    private TextView mDateTextView;
    @FindViewById(R.id.list_item_crime_solved_check_box)
    private CheckBox mSolvedCheckBox;


    public CrimeHolder(View itemView) {
      super(itemView);
      ViewInjectUtils.injectView(itemView, this);
      itemView.setOnClickListener(this);
      //itemView.findViewById(R.id.list_item_crime_)
    }

    public void bindCrime(Crime crime) {
      mCrime = crime;
      log.info("crime:{}", crime);
      mTittleTextView.setText(crime.getTitle());
      mDateTextView.setText(crime.getDate().toString());
      mSolvedCheckBox.setChecked(mCrime.isSolved());
    }

    @Override
    public void onClick(View v) {
      Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
      startActivityForResult(intent, REQUEST_CRIME);
    }
  }

  private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

    private List<Crime> mCrimes;

    public CrimeAdapter(List<Crime> crimes) {
      mCrimes = crimes;
    }

    @Override
    public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
      //View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
      View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
      return new CrimeHolder(view);
    }

    @Override
    public void onBindViewHolder(CrimeHolder holder, int position) {
      Crime crime = mCrimes.get(position);
      holder.bindCrime(crime);
    }

    @Override
    public int getItemCount() {
      int size = mCrimes.size();
      return size;
    }
  }

}
