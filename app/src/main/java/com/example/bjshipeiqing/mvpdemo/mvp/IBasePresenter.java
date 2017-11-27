package com.example.bjshipeiqing.mvpdemo.mvp;

/**
 * Created by bjshipeiqing on 2017/11/22.
 */

public interface IBasePresenter<V> {
  V getView();

  void attatchView(V v);

  void detatchView();
}
