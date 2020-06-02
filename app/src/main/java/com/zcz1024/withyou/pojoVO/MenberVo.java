package com.zcz1024.withyou.pojoVO;

public class MenberVo {

    private String nickaname;

    private String avatar;

    private String intro;

    private String role;

    public String getNickaname() {
        return nickaname;
    }

    public void setNickaname(String nickaname) {
        this.nickaname = nickaname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MenberVo{" +
                "nickaname='" + nickaname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", intro='" + intro + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
