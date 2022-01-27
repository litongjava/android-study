package com.litongjava.nerdlauncher.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.litongjava.nerdlauncher.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NerdLauncherFragment extends Fragment {
  private RecyclerView mRecyclerView;

  public static NerdLauncherFragment newInstance() {
    return new NerdLauncherFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_nerd_launcher, container, false);
    mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_nerd_launcher_recycler_view);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    setupAdaper();
    return view;
  }

  private void setupAdaper() {
    //装配intent作为查参数
    Intent startupIntent = new Intent(Intent.ACTION_MAIN);
    startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);

    //查询Activity
    final PackageManager pm = getActivity().getPackageManager();
    List<ResolveInfo> activities = pm.queryIntentActivities(startupIntent, 0);

    log.info("Found {} activities", activities.size());
    //对activity进行排序
    Collections.sort(activities, new Comparator<ResolveInfo>() {

      @Override
      public int compare(ResolveInfo o1, ResolveInfo o2) {
        String o1Name = o1.loadLabel(pm).toString();
        String o2Name = o2.loadLabel(pm).toString();
        int compareResult = String.CASE_INSENSITIVE_ORDER.compare(o1Name, o2Name);
        //log.info("o1Name:{} o2Name:{} compare:{}",o1Name,o2Name,compareResult);
        return compareResult;
      }
    });
    mRecyclerView.setAdapter(new ActivityAdaper(activities));
  }

  private class ActivityHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ResolveInfo mResolveInfo;
    private TextView mNameTextView;

    public ActivityHolder(@NonNull View itemView) {
      super(itemView);
      mNameTextView = (TextView) itemView;
      mNameTextView.setOnClickListener(this);
    }

    public void bingActivity(ResolveInfo resolveInfo) {
      mResolveInfo = resolveInfo;
      PackageManager pm = getActivity().getPackageManager();
      String appName = mResolveInfo.loadLabel(pm).toString();
      //log.info("appName:{}",appName);
      mNameTextView.setText(appName);
    }

    @Override
    public void onClick(View v) {
      ActivityInfo activityInfo = mResolveInfo.activityInfo;
      Intent i = new Intent(Intent.ACTION_MAIN);
      String packageName=activityInfo.applicationInfo.packageName;
      String name=activityInfo.name;
      log.info("setClassName packageName:{},name:{}",packageName,name);
      i.setClassName(packageName,name);
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(i);
    }
  }

  private class ActivityAdaper extends RecyclerView.Adapter<ActivityHolder> {
    private final List<ResolveInfo> mResolveInfoList;

    private ActivityAdaper(List<ResolveInfo> mActivities) {
      this.mResolveInfoList = mActivities;
    }

    @NonNull
    @Override
    public ActivityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
      LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
      View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
      return new ActivityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityHolder activityHolder, int position) {
      ResolveInfo resolveInfo = mResolveInfoList.get(position);
      activityHolder.bingActivity(resolveInfo);
    }

    @Override
    public int getItemCount() {
      return mResolveInfoList.size();
    }
  }
}
