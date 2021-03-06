package com.jsjlzj.wayne.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.jsjlzj.wayne.R;


 /**
  *
  * @ClassName:      SearchBarView
  * @Description:    搜索条目
  * @Author:         曾海强
  * @CreateDate:
  */
public class SearchBarView extends FrameLayout {
    private EditText mSearchEt = null;
    private ImageView mSearchBtn = null;
    private ImageView mClearBtn = null;
    private EditTextCallback editTextCallback;//回调接口
    private static final int SEARCH_MAX_LENGTH = 60;

    public SearchBarView(Context context) {
        this(context, null);
    }

    public SearchBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initListener();
    }

    private void initView(Context context) {
        View mView = LayoutInflater.from(context).inflate(R.layout.search_bar_layout, this);
        mSearchEt = mView.findViewById(R.id.search_et);
        mSearchBtn = mView.findViewById(R.id.search_btn);
        mClearBtn = mView.findViewById(R.id.search_clear_word_btn);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {
        mSearchEt.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && mSearchEt.getText().toString().trim().equals("")) {
                //显示语音图标 ，隐藏删除图标
                mClearBtn.setVisibility(GONE);
                mSearchBtn.setVisibility(VISIBLE);
                mSearchBtn.setClickable(true);
                mSearchEt.setCursorVisible(true);
            } else if (hasFocus && !mSearchEt.getText().toString().trim().equals("")) {
                //显示删除图标，隐藏语音图标
                mClearBtn.setVisibility(VISIBLE);
                mSearchBtn.setVisibility(VISIBLE);
                mSearchEt.setCursorVisible(true);
                mClearBtn.setClickable(true);
            }
            if (!hasFocus) {
                //没有焦点 显示语音图标 隐藏删除图标
                mClearBtn.setVisibility(GONE);
                mSearchBtn.setVisibility(VISIBLE);
                mSearchEt.setCursorVisible(false);
                mSearchBtn.setClickable(true);
            }
        });
        mClearBtn.setOnClickListener(v -> {
            mSearchEt.setText("");
            mClearBtn.setVisibility(View.GONE);
        });

        mSearchEt.setOnTouchListener((v, event) -> {
            mSearchEt.setCursorVisible(true);
            return false;
        });

        mSearchEt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //在这里做请求操作
                if (editTextCallback != null)
                    editTextCallback.onClickSoftWare(getInputText());
                return true;
            }
            return false;
        });
//            mSearchBtn.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String[] permission = new String[]{android.Manifest.permission.RECORD_AUDIO};
//                    (AppLike.self().getCurrentActivity()).checkPermission(permission, getResources().getString(R.string.please_open_read_video_permission), new BaseActivity.OnPermissionCheckSuccess() {
//                        @Override
//                        public void checkSuccess() {
////                            (AppLike.self().getCurrentActivity()).showSpeechVoiceFragment();
//                        }
//                        @Override
//                        public void checkFailed() {
////                            (AppLike.self().getCurrentActivity()).cancelSpeechVoiceFragment();
//                        }
//                    });
//                }
//            });

        mSearchEt.setFilters(new InputFilter[]{searchFilter});
        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mSearchBtn.setVisibility(View.VISIBLE);
                    mClearBtn.setVisibility(View.VISIBLE);
                    mClearBtn.setClickable(true);
                } else {
                    mSearchBtn.setVisibility(View.VISIBLE);
                    mClearBtn.setVisibility(View.GONE);
                    mSearchEt.setClickable(true);
                }
                if (editTextCallback != null)
                    editTextCallback.onEditTextChange(getInputText());
            }
        });
    }

    //    public void hideDeleteShowVoice() {
//                mSearchEt.clearFocus();
//                mSearchEt.setCursorVisible(false);
//                mClearBtn.setVisibility(GONE);
//                mSearchBtn.setVisibility(VISIBLE);
//                mSearchBtn.setClickable(true);
//                if (AppLike.self().getCurrentActivity() instanceof BaseActivity) {
//                    View root = ((BaseActivity) AppLike.self().getCurrentActivity()).getRoot();
//                    if (root != null) {
//                        root.setEnabled(true);
//                        root.setFocusable(true);
//                        root.setFocusableInTouchMode(true);
//                        root.requestFocus();
//                        root.performClick();
//                    }
//                }
//    }

    /**
     * 获取编辑框信息
     * @return
     */
    public String getInputText() {
        return mSearchEt.getText().toString().trim();
    }

    /**
     * 设置提示文字信息
     * @param resId
     */
    public void setSearchEtHint(int resId) {
        mSearchEt.setHint(resId);
    }

    public void setOnEditTextChangeListener(EditTextCallback callback) {
        this.editTextCallback = callback;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == View.VISIBLE) {
            mSearchEt.setEnabled(true);
            mSearchEt.setFocusable(true);
            mSearchEt.setFocusableInTouchMode(true);
            mSearchEt.requestFocus();
        }
    }

    /**
     * editText改变监听
     */
    public interface EditTextCallback {
        //编辑框改变监听
        void onEditTextChange(String str);

        //语音识别完成监听  语音完成后会触发编辑框改变监听
        void onEditFinish(String str);

        //点击软键盘搜索
        void onClickSoftWare(String str);
    }

    public EditText getSearchEditText() {
        return mSearchEt;
    }

    public ImageView getmSearchBtn() {
        return mSearchBtn;
    }

    public EditTextCallback getEditTextCallback() {
        return editTextCallback;
    }

    private InputFilter searchFilter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            int dindex = 0;
            int count = 0;
            while (count <= SEARCH_MAX_LENGTH && dindex < dest.length()) {
                char c = dest.charAt(dindex++);
                if (c < 128) {
                    count = count + 1;
                } else {
                    count = count + 2;
                }
            }
            if (count > SEARCH_MAX_LENGTH) {
                return dest.subSequence(0, dindex - 1);
            }
            int sindex = 0;
            while (count <= SEARCH_MAX_LENGTH && sindex < source.length()) {
                char c = source.charAt(sindex++);
                if (c < 128) {
                    count = count + 1;
                } else {
                    count = count + 2;
                }
            }
            if (count > SEARCH_MAX_LENGTH) {
                sindex--;
            }
            return source.subSequence(0, sindex);
        }
    };
}
