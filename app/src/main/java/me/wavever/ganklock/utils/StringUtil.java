/*
 *
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 * Copyright (C) 2015 GuDong <maoruibin9035@gmail.com>
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.wavever.ganklock.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import me.wavever.ganklock.MyApplication;

public class StringUtil {

    public static AssetManager assetManager = MyApplication.getContext().getAssets();


    public static SpannableString format(Context context, String text, int style) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new TextAppearanceSpan(context, style), 0, text.length(), 0);
        return spannableString;
    }

    public static Typeface getTypeFace(String fontName) {
        return Typeface.createFromAsset(MyApplication.getContext().getAssets(),
            "fonts/" + fontName);
    }


    /**
     * 将 "yyyy-MM-dd" 转化为 中文
     */
    public static String convertDateNum(String dateNum) {
        String month = "";
        String day = "";
        switch (Integer.parseInt(dateNum.substring(5, 7))) {
            case 1:
                month = "一月";
                break;
            case 2:
                month = "二月";
                break;
            case 3:
                month = "三月";
                break;
            case 4:
                month = "四月";
                break;
            case 5:
                month = "五月";
                break;
            case 6:
                month = "六月";
                break;
            case 7:
                month = "七月";
                break;
            case 8:
                month = "八月";
                break;
            case 9:
                month = "九月";
                break;
            case 10:
                month = "十月";
                break;
            case 11:
                month = "十一月";
                break;
            default:
                month = "十二月";
                break;

        }
        day= convertNum(Integer.parseInt(dateNum.substring(8, 10)));
        return month + "·" + day+"日";
    }


    private static String convertNum(int num) {
        String s1 = "";
        String s2 = "";
        if (num >= 30) {
            s1 = "三十";
        } else if (num >= 20) {
            s1 = "廿";
        } else if (num >= 10) {
            s1 = "十";
        }

        switch (num % 10) {
            case 1:
                s2 = "一";
                break;
            case 2:
                s2 = "二";
                break;
            case 3:
                s2="三";
                break;
            case 4:
                s2="四";
                break;
            case 5:
                s2="五";
                break;
            case 6:
                s2="六";
                break;
            case 7:
                s2="七";
                break;
            case 8:
                s2="八";
                break;
            case 9:
                s2="九";
                break;
        }

        return s1 + s2;
    }

    public static String getChannelValue(){
        Context context = MyApplication.getContext();
        String value = "";
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                PackageManager.GET_META_DATA);
            value = info.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

}
