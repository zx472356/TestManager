package com.zz.htmanager.entity;

import java.util.List;

public class FrameworkDirectory {
    String key;
    String value;
    List<FrameworkDirectory> list;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<FrameworkDirectory> getList() {
        return list;
    }

    public void setList(List<FrameworkDirectory> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FrameworkDirectory{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", list=" + list +
                '}';
    }
}
