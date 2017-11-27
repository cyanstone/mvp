package com.example.bjshipeiqing.mvpdemo.mvp;

/**
 * Created by bjshipeiqing on 2017/11/22.
 */

public class UseCaseHandler {

  private IUseCaseScheduler mUseCaseScheduler;

  public <P extends UseCase.RequestValue, Q extends UseCase.UseCaseCallback> void ayncRunTask(final UseCase useCase, final P requestVaule, Q callback) {
    useCase.setRequestValue(requestVaule);
    useCase.setCallback(new UseCaseCallbackWrapper(this,callback));
    mUseCaseScheduler.execute(new Runnable() {
      @Override
      public void run() {
        useCase.excecuteUseCase(requestVaule);
      }
    });
  }

  private UseCaseHandler() {
     mUseCaseScheduler = new UseCaseThreadPoolScheduler();
  }

  private <Q extends UseCase.ResponseValue>void onSuccess(Q response, UseCase.UseCaseCallback<Q> callback) {
    mUseCaseScheduler.onSuccess(response,callback);
  }

  private <Q extends UseCase.ResponseValue> void onError(UseCase.UseCaseCallback<Q> callback, int errorCode, String error) {
    mUseCaseScheduler.onError(callback,errorCode,error);
  }

  public static UseCaseHandler getInstance() {
    return SingletonHolder.mUseCaseHandler;
  }

  private static class SingletonHolder{
   private  static UseCaseHandler mUseCaseHandler = new UseCaseHandler();
  }

  private class UseCaseCallbackWrapper<Q extends UseCase.ResponseValue> implements UseCase.UseCaseCallback<Q>{

    private UseCaseHandler mUseCaseHandler;

    private UseCase.UseCaseCallback callback;

    public UseCaseCallbackWrapper(UseCaseHandler useCaseHandler, UseCase.UseCaseCallback<Q> callback){
      this.callback = callback;
      this.mUseCaseHandler = useCaseHandler;
    }

    @Override
    public void onSuccess(Q response) {
      mUseCaseHandler.onSuccess(response,callback);
    }

    @Override
    public void onError(int errorCode, String error) {
      mUseCaseHandler.onError(callback,errorCode,error);
    }
  }

}
