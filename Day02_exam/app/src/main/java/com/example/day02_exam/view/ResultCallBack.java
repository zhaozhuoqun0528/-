package com.example.day02_exam.view;

public interface ResultCallBack<T> {
    void getSuccess(T t);
    void getFail(String msg);
}
