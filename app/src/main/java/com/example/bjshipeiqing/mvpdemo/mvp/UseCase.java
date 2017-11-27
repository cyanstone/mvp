package com.example.bjshipeiqing.mvpdemo.mvp;

/**
 * Created by bjshipeiqing on 2017/11/22.
 */

public abstract class UseCase<P extends UseCase.RequestValue,Q extends UseCase.ResponseValue> {

  private P mRequestValue;

  private UseCaseCallback<Q> mCallback;

  protected abstract void excecuteUseCase(P requetValue);

  public void run(P requestValue, UseCaseCallback caseCallback) {
    setRequestValue(requestValue);
    setCallback(caseCallback);
    excecuteUseCase(mRequestValue);
  }

  public P getRequestValue() {
    return mRequestValue;
  }

  public void setRequestValue(P mRequestValue) {
    this.mRequestValue = mRequestValue;
  }

  public UseCaseCallback<Q> getCallback() {
    return mCallback;
  }

  public void setCallback(UseCaseCallback<Q> mCallback) {
    this.mCallback = mCallback;
  }

  public interface RequestValue{

  }

  public interface ResponseValue{

  }

  public interface UseCaseCallback<Q>{

    void onSuccess(Q response);

    void onError(int errorCode, String error);
  }
}
