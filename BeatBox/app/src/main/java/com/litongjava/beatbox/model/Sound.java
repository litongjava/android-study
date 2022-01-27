package com.litongjava.beatbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sound {
  private String assetPath,name;
  private Integer soundId;

  public Sound(String assetPath) {
    String[] componts = assetPath.split("/");
    String filename=componts[componts.length-1];
    name=filename.replace(".wav","");
    this.assetPath=assetPath;
  }
}
