package com.example.androidxbasestudy.model;

import java.util.List;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/2/23
 */
public class WanAndroidResponse {
  private DataDTO data;

  private Integer errorCode;

  private String errorMsg;

  public DataDTO getData() {
    return data;
  }

  public void setData(DataDTO data) {
    this.data = data;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  @Override
  public String toString() {
    return "WanAndroidResponse{" +
      "data=" + data +
      ", errorCode=" + errorCode +
      ", errorMsg='" + errorMsg + '\'' +
      '}';
  }

  public static class DataDTO {

    private Boolean admin;

    private List<?> chapterTops;

    private Integer coinCount;

    private List<Integer> collectIds;

    private String email;

    private String icon;

    private Integer id;

    private String nickname;

    private String password;

    private String publicName;

    private String token;

    private Integer type;

    private String username;

    public Boolean getAdmin() {
      return admin;
    }

    public void setAdmin(Boolean admin) {
      this.admin = admin;
    }

    public List<?> getChapterTops() {
      return chapterTops;
    }

    public void setChapterTops(List<?> chapterTops) {
      this.chapterTops = chapterTops;
    }

    public Integer getCoinCount() {
      return coinCount;
    }

    public void setCoinCount(Integer coinCount) {
      this.coinCount = coinCount;
    }

    public List<Integer> getCollectIds() {
      return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
      this.collectIds = collectIds;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getIcon() {
      return icon;
    }

    public void setIcon(String icon) {
      this.icon = icon;
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getNickname() {
      return nickname;
    }

    public void setNickname(String nickname) {
      this.nickname = nickname;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getPublicName() {
      return publicName;
    }

    public void setPublicName(String publicName) {
      this.publicName = publicName;
    }

    public String getToken() {
      return token;
    }

    public void setToken(String token) {
      this.token = token;
    }

    public Integer getType() {
      return type;
    }

    public void setType(Integer type) {
      this.type = type;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    @Override
    public String toString() {
      return "DataDTO{" +
        "admin=" + admin +
        ", chapterTops=" + chapterTops +
        ", coinCount=" + coinCount +
        ", collectIds=" + collectIds +
        ", email='" + email + '\'' +
        ", icon='" + icon + '\'' +
        ", id=" + id +
        ", nickname='" + nickname + '\'' +
        ", password='" + password + '\'' +
        ", publicName='" + publicName + '\'' +
        ", token='" + token + '\'' +
        ", type=" + type +
        ", username='" + username + '\'' +
        '}';
    }
  }
}
