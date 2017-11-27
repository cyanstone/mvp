package com.example.bjshipeiqing.mvpdemo.mvp;

/**
 * Created by bjshipeiqing on 2017/11/22.
 */

public interface IUseCaseScheduler {

  void execute(Runnable runnable);

  <Q extends UseCase.ResponseValue> void onSuccess(Q response,UseCase.UseCaseCallback<Q> caseCallback);

  <Q extends UseCase.ResponseValue> void onError(UseCase.UseCaseCallback<Q> caseCallback, int errorCode, String error);

}
