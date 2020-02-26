//package com.jsjlzj.wayne.data.http.convert;
//
//
//import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
//
//import java.util.function.Function;
//
///**
// * RxJava 网络请求结果转化
// *
// * @param <E>
// */
//public class ResponseConvert<E> implements Function<MdlBaseHttpResp<E>, MdlBaseHttpResp<E>> {
//
//    @Override
//    public BaseApiResponse<E> apply(BaseApiResponse<E> response) {
//        if (!ErrorCode.SUCCESS.getCode().equals(response.getCode())) {
//            ApiException apiException = new ApiException(response.getCode(), response.getMessage());
//            ErrorCode errorCode = ErrorCode.requestTypeByCode(response.getCode());
//            if (errorCode != null) {
//                if (StringUtils.isEmpty(response.getMessage())) {
//                    apiException.setErrorMessage(errorCode.getTypeView());
//                }
//            }
//            throw apiException;
//        }
//        return response;
//    }
//
//}
