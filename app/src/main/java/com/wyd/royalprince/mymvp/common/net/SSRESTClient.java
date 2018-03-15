package com.wyd.royalprince.mymvp.common.net;


import com.squareup.okhttp.OkHttpClient;
import com.wyd.royalprince.mymvp.entity.BaseReq;
import com.wyd.royalprince.mymvp.entity.BaseResp;
import com.wyd.royalprince.mymvp.entity.NewsDataListResp;
import com.wyd.royalprince.mymvp.utils.Constants;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

public class SSRESTClient {

  private static final String LOG_TAG = "SSRESTClient";
  private static SSRESTClient mInstance = new SSRESTClient();
  private RestAdapter mRestAdapter;
  private SSRetrofitService mService;

  private SSRESTClient() {

    OkHttpClient client = new OkHttpClient();
    client.setConnectTimeout(30, TimeUnit.SECONDS);
    client.setReadTimeout(30, TimeUnit.SECONDS);

    SSLContext sc = null;
    try {
      sc = SSLContext.getInstance("SSL");
      sc.init(null, new TrustManager[]{new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
          return null;
        }
      }}, new SecureRandom());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (KeyManagementException e) {
      e.printStackTrace();
    }

    client.setSslSocketFactory(sc.getSocketFactory());
    client.setHostnameVerifier(new HostnameVerifier() {
      @Override
      public boolean verify(String hostname, SSLSession session) {
//                if (hostname.equals(Constants.LE_SB_HOST)) {
//                    return true;
//                }
        return false;
      }
    });

    mRestAdapter = new RestAdapter.Builder().setLogLevel(Constants.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE).setRequestInterceptor(new RequestInterceptor() {

      @Override
      public void intercept(RequestFacade req) {
//                req.addHeader(Constants.imei_str, ToolUtils.getDeviceID(MyApp.getInstance()));
//                req.addHeader(Constants.device, "ANDROID");
//                req.addHeader(Constants.accept, "zh-CN;q=0.5"); //Accept-Language:zh-CN;q=0.5
//                req.addHeader(Constants.authorization, LastingSharedPref.getInstance().getBuildToken());
      }

    }).setEndpoint(Constants.HOST).setClient(new OkClient(client)).build();

    mService = mRestAdapter.create(SSRetrofitService.class);
  }

  public static SSRESTClient getInstance() {
    return mInstance;
  }

  public SSRetrofitService getRfServiceInstance() {
    return mService;
  }

  public interface SSRetrofitService {


    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/{url}")
    void getNewsDataList(@Path("url") String url, Callback<NewsDataListResp> cb);

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("/{url}")
    void getPostData(@Path("url") String url,BaseReq baseReq,Callback<BaseResp> cb);


  }
}
