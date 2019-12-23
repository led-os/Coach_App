package com.jsjlzj.wayne.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.constant.PublicConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.ui.MyApp;


public class SPUtil {
    private static final String SP_ACCOUNT = "ACCOUNT";
    private static final String SP_PHONE = "PHONE";
    private static final String SP_EMAIL = "EMAIL";
    private static final String SP_PWD = "PWD";
    private static final String SP_API_TOKEN = "API_TOKEN";
    private static final String SP_USER = "USER";
    private static final String SP_URL = "URL";
    private static final String SP_OBSERVER_FLAG = "OBSERVERFLAG";
    private static final String SP_OBSERVER_TID = "OBSERVERTID";
    private static final String SP_VERSION = "VERSION";
    private static final String SP_LOGO_NAME = "LOGONAME";
    private static final String SP_IMEI = "IMEI";
    private static final String SP_YXTOKRN = "YXTOKEN";
    private static final String SP_YXACCOUNT = "YXACCOUNT";

    private static final String SP_RECRUIT_CONTENT = "SP_RECRUIT_CONTENT";
    private static final String SP_FIRST = "SP_FIRST";
    private static final String SP_CHECKBOX = "SP_CHECKBOX";

    private static final SharedPreferences sp;
    static {
        sp = MyApp.ME.getSharedPreferences(PublicConstant.APP_NAME, Context.MODE_PRIVATE);
    }
    public static boolean getFist(){
        return sp.getBoolean(SP_FIRST, true);
    }
    public static void saveFist(){
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SP_FIRST, false);
        editor.apply();
    }
    public static boolean getCheckbox(){
        return sp.getBoolean(SP_CHECKBOX, false);
    }
    public static void saveCheckbox(){
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SP_CHECKBOX, true);
        editor.apply();
    }
    public static String getIMEIFromSP(){
        return sp.getString(SP_IMEI, "");
    }
    public static void saveIMEI2SP(String IMEI){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_IMEI, IMEI);
        editor.apply();
    }

    public static String getPhoneFromSP(){
        return sp.getString(SP_PHONE, "");
    }
    public static void savePhone2SP(String account){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_PHONE, account);
        editor.apply();
    }
    public static String getEmailFromSP(){
        return sp.getString(SP_EMAIL, "");
    }

    public static void saveYXTokenSP(String account){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_YXTOKRN, account);
        editor.apply();
    }
    public static String getYXTokenSP(){
        return sp.getString(SP_YXTOKRN, "");
    }


    public static void saveYXAccountSP(String account){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_YXACCOUNT, account);
        editor.apply();
    }
    public static String getYXAccountSP(){
        return sp.getString(SP_YXACCOUNT, "");
    }


    public static void saveEmail2SP(String account){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_EMAIL, account);
        editor.apply();
    }
    public static String getPwdFromSP(){
        return sp.getString(SP_PWD, "");
    }
    public static void savePwd2SP(String account){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_PWD, account);
        editor.apply();
    }
    public static String getAccountFromSP(){
        return sp.getString(SP_ACCOUNT, "");
    }
    public static void saveAccount2SP(String account){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_ACCOUNT, account);
        editor.apply();
    }

    public static String getTokenFromSP(){
        return sp.getString(SP_API_TOKEN, "");
    }

    public static void saveToken2SP(String token){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_API_TOKEN, token);
        editor.commit();
    }
    public static MdlUser.MdlUserBean getUserFromSP(){
        String json = sp.getString(SP_USER, "");
        return JsonUtil.fromJson(json, MdlUser.MdlUserBean.class);
    }

    public static void saveUser2SP(MdlUser.MdlUserBean mdlUser){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_USER, JsonUtil.createJson(mdlUser));
        editor.apply();
    }
    public static void saveURL(String url){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_URL, url);
        editor.apply();
    }


    public static String getURLFromSP(){
        return sp.getString(SP_URL, HttpConstant.BASE_URL);
    }

    public static void saveLogoName(String logoName){
        if(logoName==null)logoName="";
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_LOGO_NAME, logoName);
        editor.apply();
    }


    public static String getLogoNameFromSP(){
        String s =sp.getString(SP_LOGO_NAME, "");
        return s;
    }


    public static void saveObserverFlag(boolean isFirst){
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(SP_OBSERVER_FLAG,isFirst);
        editor.apply();
    }
    public static boolean getObserverFlag(){
        return sp.getBoolean(SP_OBSERVER_FLAG,true);
    }

    public static void saveVersionCode(String version){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(SP_VERSION,version);
        editor.apply();
    }
    public static String getVersionCode(){
        return sp.getString(SP_VERSION,"1.1.2");
    }

    /**
     * 招聘草稿箱
     */
    public static void saveRecruitContent(String content){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(SP_RECRUIT_CONTENT,content);
        editor.apply();
    }
    public static String getRecruitContent(){
        return sp.getString(SP_RECRUIT_CONTENT,"");
    }


    public static void clearSP(){
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}
