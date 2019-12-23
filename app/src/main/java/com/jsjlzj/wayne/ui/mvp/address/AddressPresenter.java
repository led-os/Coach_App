package com.jsjlzj.wayne.ui.mvp.address;

import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;

import java.util.Map;


public class AddressPresenter extends BasePresenter<AddressView> {
    private static final int REQ_LOGIN_SMES= 3;
    private static final int REQ_LOGIN_PWD= 4;
    private static final int REQ_RESET_PWD= 5;

    private static final int REQ_ALL_AREA = 1;
    private static final int REQ_GET_CHECK_CODE = 2;



    private AddressModel model;

    public AddressPresenter(AddressView view) {
        this.view = view;
        this.model = new AddressModel();
    }

    public void getAllArea(Map param) {
        if (model != null) {
            view.showLoading();
            model.getAllArea(REQ_ALL_AREA, param, this);
        }
    }


    

    @Override
    protected BaseModel getMode() {
        return model;
    }

    @Override
    protected void responseSuccess(int code, MdlBaseHttpResp resp) {
        switch (code) {
            case REQ_ALL_AREA:
                view.showResult(resp);
                break;

        }
    }
}
