package com.aepronunciation.ipa;


import android.content.Context;

import java.util.Locale;

class AppLocale {
    static Locale getLocale(Context context) {
        // this allows things to only be formatted for translated languages
        // All others will use US format.
        String currentTranslationLocale = context.getString(R.string.locale);
        switch (currentTranslationLocale) {
            case "zh-rCN":
                return Locale.SIMPLIFIED_CHINESE;
            case "zh-rTW":
                return Locale.TRADITIONAL_CHINESE;
            case "zh":
                return Locale.TRADITIONAL_CHINESE;
            default:
                return Locale.US;
        }
    }
}
