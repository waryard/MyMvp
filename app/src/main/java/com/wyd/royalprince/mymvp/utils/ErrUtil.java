package com.wyd.royalprince.mymvp.utils;

import android.text.TextUtils;
import android.util.Log;

import com.wyd.royalprince.mymvp.R;
import com.wyd.royalprince.mymvp.android.MyApp;
import com.wyd.royalprince.mymvp.common.model.BaseModel;
import com.wyd.royalprince.mymvp.common.net.ErrKind;
import com.wyd.royalprince.mymvp.entity.BaseResp;
import com.wyd.royalprince.mymvp.entity.BaseRespItem;
import java.util.ArrayList;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ErrUtil {

    private static final String LOG_TAG = ErrUtil.class.getSimpleName();

    public static void handleRetrofitError(RetrofitError re, @SuppressWarnings("rawtypes") final BaseModel.SSCallback cb) {
        try {
            if (re != null) {
                RetrofitError.Kind kind = re.getKind();
                if (Constants.DEBUG) {
                    Log.d(LOG_TAG, "handleRetrofitError, Retrofit error kind: " + kind);
                }
                ErrKind errKind = ErrKind.NONE;
                int httpStatus = 0;
                if (kind == RetrofitError.Kind.NETWORK) {
                    errKind = ErrKind.NETWORK;
                } else if (kind == RetrofitError.Kind.CONVERSION) {
                    errKind = ErrKind.CONVERSION;
                    if (Constants.DEBUG) {
                        Log.e(LOG_TAG, "conversion error", re.getCause());
                    }
                } else if (kind == RetrofitError.Kind.HTTP) {
                    errKind = ErrKind.HTTP;
                    Response res = re.getResponse();
                    if (res != null) {
                        httpStatus = res.getStatus();
                        if (httpStatus / 100 == 5 || httpStatus == 400 || httpStatus == 404) {// TODO
                            errKind = ErrKind.SERVER;
                        }
                    }
                } else if (kind == RetrofitError.Kind.UNEXPECTED) {
                    errKind = ErrKind.UNEXPECTED;
               }

                ToastUtil.showToast(MyApp.getInstance(), MyApp.getInstance().getString(getErrMsgResIdByErrKind(errKind, R.string.unexpected_err)));
                if (cb != null) {
                    cb.failure(errKind, httpStatus);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getErrMsgResIdByErrKind(ErrKind errKind, int defaultMsg) {
        int errRes = defaultMsg;
        if (errKind == ErrKind.NETWORK) {
            errRes = R.string.network_err;
        } else if (errKind == ErrKind.SERVER) {
            errRes = R.string.server_err;
        } else {
            errRes = R.string.unexpected_err;
        }
        return errRes;
    }

    public static int getErrMsgResIdByErrKind(ErrKind errKind) {
        return getErrMsgResIdByErrKind(errKind, R.string.network_err);
    }

    /**
     * 具有列表的 错误封装
     */
//    public static String getWaringMsgStrByErrorCode(BaseResp baseResp) {
//        String errCode = null;
//        if (baseResp != null) {
//            errCode = baseResp.getErrCode();
//            if (errCode != null) {
//                if (!TextUtils.isEmpty(errCode)) {  //W 开头的错误 需要提醒
//                    if (errCode.startsWith("w") || errCode.startsWith("W")) {
//                        if (errCode.endsWith("00004")) { //请求无效参数类型，需要去errorList中获取
//                            ArrayList<BaseRespItem> errorList = baseResp.getErrorList();
//                            BaseRespItem baseRespItem = errorList.get(0);
//                            if (baseRespItem != null) {
//                                return baseRespItem.getMessage();
//                            } else {
//                                return MyApp.getInstance().getString(R.string.server_err);
//                            }
//                        }/*else if(errCode.equals(Constants.STATE_LOCKED)) {  //因为第二次锁定后,弹Dialog 无需弹toast，所以这里过滤下
//                            return null;
//                        }*/ else { //直接errMsg
//                            return baseResp.getErrMsg();
//                        }
//                    } else if (errCode.startsWith("e") || errCode.startsWith("E")) {   //E 开头的错误,可以统一提醒
//                        if ("10007".equals(errCode)) {  //给用户提示
//                            return MyApp.getInstance().getString(R.string.again_msg);
//                        }/*else if(errCode.equals(Constants.ID_CARD_FRONT_SIDE_UPDATE_FAIL) || errCode.equals(Constants.ID_CARD_BACK_SIDE_UPDATE_FAIL)
//                                || errCode.equals(Constants.LIVENESS_FILE_UPDATE_FAIL) || errCode.equals(Constants.LIVENESS_PHOTO_UPDATE_FAIL)) {
//                            return null;
//                        }*/ else if (errCode.equals("E10012") || errCode.equals("e10012")) {  //一键申请 提示 不让跳过
//                            return baseResp.getErrMsg();
//                        }/*else if(errCode.equals("E12011")) {
//                            return XwxApp.getInstance().getString(R.string.voice_over_error_msg);
//                        }*/ else {
//                            return MyApp.getInstance().getString(R.string.os_again_msg);//不给用户看到 baseResp.getErrMsg();//XwxApp.getInstance().getString(R.string.chengxuyuan_kan_world);
//                        }
//                    } else if (errCode.startsWith("TZ") || errCode.startsWith("tz") || errCode.startsWith("Tz") || errCode.startsWith("tZ")) {   //TZ 医保返回，不能绑卡
//                        return baseResp.getErrMsg();
//                    } else {
//                        return MyApp.getInstance().getString(R.string.server_err);//XwxApp.getInstance().getString(R.string.chengxuyuan_go_vacation);
//                    }
//                }
//            }
//        }
//
//        return errCode;
//    }
}
