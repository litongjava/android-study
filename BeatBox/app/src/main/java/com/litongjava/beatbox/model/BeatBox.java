package com.litongjava.beatbox.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeatBox {
  public static final String SOUND_FOLDER="sample_sounds";
  public static final int MAX_SOUNDS=5;

  private AssetManager mAsset;
  private List<Sound> mSounds=new ArrayList<>();
  private SoundPool mSoundPool;

  public BeatBox(Context context){
    mAsset=context.getAssets();
    mSoundPool=new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
    loadSounds();
  }

  public void loadSounds(){
    String[] soundNames;
    try {
       soundNames= mAsset.list(SOUND_FOLDER);
      log.info("Found {} sounds",soundNames.length);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    for (String soundName : soundNames) {
      String assetPath=SOUND_FOLDER+"/"+soundName;
      Sound sound = new Sound(assetPath);

      try {
        load(sound);
      } catch (IOException e) {
        log.error("Could not load sound {} error:{}",soundNames,e);
      }
      mSounds.add(sound);
    }
  }
  public List<Sound> getSounds(){
    return mSounds;
  }
  public void load(Sound sound) throws IOException {
    AssetFileDescriptor asf = mAsset.openFd(sound.getAssetPath());
    int soundId = mSoundPool.load(asf, 1);
    sound.setSoundId(soundId);
  }

  public void play(Sound sound){
    Integer soundId = sound.getSoundId();
    if(soundId==null){
      return;
    }
    mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
  }

  public void release(){
    mSoundPool.release();
  }

}
