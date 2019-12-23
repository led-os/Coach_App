package com.jsjlzj.wayne.ui.mvp.address;


import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.address.MalAddressProvince;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;


public interface AddressView extends BaseView {


    default void showResult(MdlBaseHttpResp<MalAddressProvince> resp){}
}
