package com.android.settings.accessibility;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settingslib.core.AbstractPreferenceController;
/* loaded from: classes.dex */
public class CustomVibrationPreferenceConfig {
    private static final VibrationAttributes ACCESSIBILITY_VIBRATION_ATTRIBUTES = new VibrationAttributes.Builder(VibrationAttributes.createForUsage(66)).setFlags(2).build();
    public static final VibrationEffect PREVIEW_VIBRATION_EFFECT = VibrationEffect.createPredefined(0);
    protected final ContentResolver mContentResolver;
    private final String mMainSettingKey;
    private final String mSettingKey;
    protected final Vibrator mVibrator;

    public CustomVibrationPreferenceConfig(Context context, String str, String str2) {
        this.mContentResolver = context.getContentResolver();
        this.mVibrator = (Vibrator) context.getSystemService(Vibrator.class);
        this.mMainSettingKey = str;
        this.mSettingKey = str2;
    }

    public String getMainSettingKey() {
        return this.mMainSettingKey;
    }

    public String getSettingKey() {
        return this.mSettingKey;
    }

    public boolean isPreferenceEnabled() {
        return Settings.System.getInt(this.mContentResolver, this.mMainSettingKey, 1) == 1;
    }

    public int readValue(int i) {
        return Settings.System.getInt(this.mContentResolver, this.mSettingKey, i);
    }

    public boolean setValue(int i) {
        return Settings.System.putInt(this.mContentResolver, this.mSettingKey, i);
    }

    public void playVibrationPreview() {
        this.mVibrator.vibrate(PREVIEW_VIBRATION_EFFECT, ACCESSIBILITY_VIBRATION_ATTRIBUTES);
    }

    /* loaded from: classes.dex */
    public static final class SettingObserver extends ContentObserver {
        private final Uri mMainSettingUri;
        private Preference mPreference;
        private AbstractPreferenceController mPreferenceController;
        private final Uri mUri;

        public SettingObserver(CustomVibrationPreferenceConfig customVibrationPreferenceConfig) {
            super(new Handler(true));
            this.mMainSettingUri = Settings.System.getUriFor(customVibrationPreferenceConfig.getMainSettingKey());
            this.mUri = Settings.System.getUriFor(customVibrationPreferenceConfig.getSettingKey());
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            if (this.mUri.equals(uri) || this.mMainSettingUri.equals(uri)) {
                notifyChange();
            }
        }

        private void notifyChange() {
            Preference preference;
            AbstractPreferenceController abstractPreferenceController = this.mPreferenceController;
            if (abstractPreferenceController == null || (preference = this.mPreference) == null) {
                return;
            }
            abstractPreferenceController.updateState(preference);
        }

        public void register(Context context) {
            context.getContentResolver().registerContentObserver(this.mUri, false, this);
            context.getContentResolver().registerContentObserver(this.mMainSettingUri, false, this);
        }

        public void unregister(Context context) {
            context.getContentResolver().unregisterContentObserver(this);
        }

        public void onDisplayPreference(AbstractPreferenceController abstractPreferenceController, Preference preference) {
            this.mPreferenceController = abstractPreferenceController;
            this.mPreference = preference;
        }
    }
}
