package com.yunnex.canteen.common.ui;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * double过滤器    首位不为零
 * 可配置最大double数值
 * 保留小数点后5位
 */

public class WeightDoubleInputFilter implements InputFilter {

    double amount;

    public WeightDoubleInputFilter(double amount) {
        this.amount = amount;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (TextUtils.isEmpty(source)) {
            return source;
        }
        StringBuffer buffer = new StringBuffer(dest).insert(dstart,source);
        String s = buffer.toString();
        if (TextUtils.isEmpty(s)) {
            return source;
        }

        if (!isMatch("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,5})?$", s)) {
            return "";
        }

        double number = Double.valueOf(buffer.toString());
        if (number > amount) {
            return "";
        }
        return source;
    }


    private static boolean isMatch(String regex, String orginal) {
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }
}
