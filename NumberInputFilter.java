package com.yunnex.canteen.common.ui;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

/**
 * Created by pengchengfu on 2017/12/29.
 * 整数过滤器    首位不为零
 * 可配置最大整数值
 */

public class NumberInputFilter implements InputFilter {
    
    long amount;

    public NumberInputFilter(long amount) {
        this.amount = amount;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (TextUtils.isEmpty(source)) {
            return source;
        }
        char c = source.charAt(0);
        if (c < '0' || c > '9') {
            return "";
        }

        StringBuffer buffer = new StringBuffer(dest).insert(dstart,source);
        String s = buffer.toString();

        if (TextUtils.isEmpty(s)) {
            return source;
        }

        char charAt = s.charAt(0);
        if (charAt == '0') {
            return "";
        }

        int number = Integer.valueOf(s);
        if (number > amount) {
            return "";
        }
        return source;
    }
}
