package com.litongjava.beatbox.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.utils.ViewUtils;
import com.litongjava.beatbox.R;
import com.litongjava.beatbox.model.BeatBox;
import com.litongjava.beatbox.model.Sound;

import java.util.List;

public class BeatBoxFragment extends Fragment {

  private BeatBox mBeatBox;

  public static BeatBoxFragment newInstance() {
    return new BeatBoxFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBeatBox = new BeatBox(getActivity());
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_beat_box_recycler_view);
    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
    return view;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mBeatBox.release();
  }

  private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @FindViewById(R.id.list_item_sound_button)
    private Button mButton;
    private Sound mSound;
    public SoundHolder(LayoutInflater inflater, ViewGroup container) {
      super(inflater.inflate(R.layout.list_item_sound, container, false));
      ViewUtils.injectView(itemView, this);
      mButton.setOnClickListener(this);
    }

    public void bindSound(Sound sound){
      mSound=sound;
      mButton.setText(mSound.getName());
    }

    @Override
    public void onClick(View v) {
      mBeatBox.play(mSound);
    }
  }

  public class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
    private List<Sound> mSounds;
    public SoundAdapter(List<Sound> sounds){
      mSounds=sounds;
    }
    @Override
    public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(getActivity());
      return new SoundHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(SoundHolder holder, int position) {
      Sound sound = mSounds.get(position);
      holder.bindSound(sound);
    }

    @Override
    public int getItemCount() {
      return mSounds.size();
    }
  }
}
