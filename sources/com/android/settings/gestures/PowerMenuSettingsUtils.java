package com.android.settings.gestures;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
/* loaded from: classes.dex */
final class PowerMenuSettingsUtils {
    private static final Uri POWER_BUTTON_LONG_PRESS_URI = Settings.Global.getUriFor("power_button_long_press");
    private final Context mContext;
    private final SettingsObserver mSettingsObserver = new SettingsObserver(new Handler(Looper.getMainLooper()));

    /* loaded from: classes.dex */
    public interface SettingsStateCallback {
        void onChange(Uri uri);
    }

    public static boolean isLongPressPowerForAssistantEnabled(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "power_button_long_press", context.getResources().getInteger(17694869)) == 5;
    }

    public static boolean isLongPressPowerSettingAvailable(Context context) {
        if (context.getResources().getBoolean(17891721)) {
            int integer = context.getResources().getInteger(17694869);
            return integer == 1 || integer == 5;
        }
        return false;
    }

    public static boolean setLongPressPowerForAssistant(Context context) {
        if (Settings.Global.putInt(context.getContentResolver(), "power_button_long_press", 5)) {
            Settings.Global.putInt(context.getContentResolver(), "key_chord_power_volume_up", 2);
            return true;
        }
        return false;
    }

    public static boolean setLongPressPowerForPowerMenu(Context context) {
        if (Settings.Global.putInt(context.getContentResolver(), "power_button_long_press", 1)) {
            Settings.Global.putInt(context.getContentResolver(), "key_chord_power_volume_up", context.getResources().getInteger(17694853));
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PowerMenuSettingsUtils(Context context) {
        this.mContext = context;
    }

    public void registerObserver(SettingsStateCallback settingsStateCallback) {
        this.mSettingsObserver.setCallback(settingsStateCallback);
        this.mContext.getContentResolver().registerContentObserver(POWER_BUTTON_LONG_PRESS_URI, true, this.mSettingsObserver);
    }

    public void unregisterObserver() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mSettingsObserver);
    }

    /* loaded from: classes.dex */
    private static final class SettingsObserver extends ContentObserver {
        private SettingsStateCallback mCallback;

        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallback(SettingsStateCallback settingsStateCallback) {
            this.mCallback = settingsStateCallback;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            SettingsStateCallback settingsStateCallback = this.mCallback;
            if (settingsStateCallback != null) {
                settingsStateCallback.onChange(uri);
            }
        }
    }
}
