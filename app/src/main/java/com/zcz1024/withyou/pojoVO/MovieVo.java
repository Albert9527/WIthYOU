package com.zcz1024.withyou.pojoVO;

public class MovieVo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MovieVo{" +
                "name='" + name + '\'' +
                '}';
    }
}
