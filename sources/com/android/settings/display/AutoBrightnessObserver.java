package com.android.settings.display;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
/* loaded from: classes.dex */
public class AutoBrightnessObserver {
    private Runnable mCallback;
    private final ContentObserver mContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.settings.display.AutoBrightnessObserver.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            AutoBrightnessObserver.this.mCallback.run();
        }
    };
    private final Context mContext;

    public AutoBrightnessObserver(Context context) {
        this.mContext = context;
    }

    public void subscribe(Runnable runnable) {
        this.mCallback = runnable;
        this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("screen_brightness_mode"), false, this.mContentObserver);
    }

    public void unsubscribe() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
    }
}
