package com.example.bjshipeiqing.mvpdemo.mvp;

import java.lang.ref.WeakReference;

/**
 * Created by bjshipeiqing on 2017/11/22.
 */

public abstract class BasePresenter<V> implements IBasePresenter<V>{
  private WeakReference<V > mViewRef;

  private UseCaseHandler mUseCaseHandler;

  public BasePresenter() {
    mUseCaseHandler = UseCaseHandler.getInstance();
  }

  @Override
  public V getView() {
    if(mViewRef != null && mViewRef.get() != null){
      return mViewRef.get();
    }
    return null;
  }

  @Override
  public void attatchView(V view) {
     if(view != null) {
       mViewRef = new WeakReference<V>(view);
     }
  }

  @Override
  public void detatchView() {
    if(mViewRef!= null) {
      mViewRef.clear();
      mViewRef = null;
    }
  }

  public UseCaseHandler getUseCaseHandler() {
    return mUseCaseHandler;
  }
}
