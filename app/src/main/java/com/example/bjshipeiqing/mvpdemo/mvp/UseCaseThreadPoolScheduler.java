package com.example.bjshipeiqing.mvpdemo.mvp;

import android.os.Handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjshipeiqing on 2017/11/22.
 */

public class UseCaseThreadPoolScheduler implements IUseCaseScheduler {
  private static final int CORE_POOL_SIZE = 10;

  private static final int MAX_POOL_SIZE = 40;

  private static final int KEEP_ALIVE_TIME_OUT = 60;

  private Handler mHandler;

  private ThreadPoolExecutor mThreadPool;

  public UseCaseThreadPoolScheduler() {
    mHandler = new Handler();
    mThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,MAX_POOL_SIZE,KEEP_ALIVE_TIME_OUT, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(CORE_POOL_SIZE),new ThreadPoolExecutor.DiscardPolicy());
  }

  @Override
  public void execute(Runnable runnable) {
    mThreadPool.execute(runnable);
  }

  @Override
  public <Q extends UseCase.ResponseValue> void onSuccess(final Q response, final UseCase.UseCaseCallback<Q> caseCallback) {
    mHandler.post(new Runnable() {
      @Override
      public void run() {
        caseCallback.onSuccess(response);
      }
    });
  }

  @Override
  public <Q extends UseCase.ResponseValue> void onError(final UseCase.UseCaseCallback<Q> caseCallback, final int errorCode, final String error) {
    mHandler.post(new Runnable() {
      @Override
      public void run() {
        caseCallback.onError(errorCode, error);
      }
    });
  }
}
