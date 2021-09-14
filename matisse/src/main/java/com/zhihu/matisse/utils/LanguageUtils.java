package com.zhihu.matisse.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.webkit.WebView;

import java.util.Locale;

/**
 * @Description:
 * @author: mick
 * @CreateAt: 2019-12-12 13:27
 * @UpdateUser: mick
 * @UpdateDate: 2019-12-12 13:27
 * @UpdateRemark:
 */
public class LanguageUtils {
    public static void setDefaultLanguage(Context context, Locale locale) {
        try {
            new WebView(context).destroy();
        } catch (Exception e) {
        }
        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        Locale.setDefault(locale);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        context.getResources().updateConfiguration(config, metrics);
//        SpUtils.saveString(context,"language",language);
    }


    public static Context attachBaseContext(Context context, Locale locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, locale);
        } else {
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, Locale locale) {
        try {
            new WebView(context).destroy();
        } catch (Exception e) {
        }
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }
}
