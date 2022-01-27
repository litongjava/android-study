package com.litongjava.criminal.intent.criminalintentjava.model;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Crime {
  private UUID id;
  private String title;
  private Date date;
  private boolean isSolved;
  private String suspect;

  public Crime(){
    this.id=UUID.randomUUID();
    this.date=new Date();
  }

  public Crime(String title, boolean isSolved) {
    this.id=UUID.randomUUID();
    this.date=new Date();
    this.title=title;
    this.isSolved=isSolved;
  }

  public Crime(UUID uuid) {
    this.id=uuid;
  }

  public String getPhotoFilename() {
    return "IMG_" + getId().toString() + ".jpg";
  }
}
