package com.wyd.royalprince.mymvp.common.model;

import com.wyd.royalprince.mymvp.common.net.ErrKind;
import com.wyd.royalprince.mymvp.common.net.SSRESTClient;

/**
 * Created by wyd on 2017/6/5.
 */

public class BaseModel {
  public SSRESTClient mRestClient;
  public SSRESTClient.SSRetrofitService mService;

  public BaseModel() {
    mRestClient = SSRESTClient.getInstance();
    mService = mRestClient.getRfServiceInstance();
  }

  public interface SSCallback<T> {

    public void success(final T resp);// HTTP SUCCESS, logic may be failure

    /**
     * @param errKind @see {@link ErrKind}
     * @param httpStatus the http status code, only meanuful when errKind is
     */
    public void failure(ErrKind errKind, int httpStatus);
  }
}
