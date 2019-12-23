package com.jsjlzj.wayne.utils.textInputFilter;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Pattern;

public class EditTextInputFilterD implements InputFilter {
    Pattern p;

    public EditTextInputFilterD() {
        p = Pattern.compile("^\\d+(\\.\\d{0,2})?$");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String s = dest.toString() + source;
        if (!p.matcher(s).matches()) source = "";
        if (!s.contains(".")) if (s.length() > 9 || dest.toString().indexOf("0") == 0) source = "";
        return source;
    }
}
