package com.android.settings.dashboard.suggestions;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import com.android.settings.R;
import com.android.settings.Settings;
import com.android.settings.biometrics.fingerprint.FingerprintEnrollSuggestionActivity;
import com.android.settings.biometrics.fingerprint.FingerprintSuggestionActivity;
import com.android.settings.display.NightDisplayPreferenceController;
import com.android.settings.notification.zen.ZenOnboardingActivity;
import com.android.settings.notification.zen.ZenSuggestionActivity;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.password.ScreenLockSuggestionActivity;
import com.android.settings.wallpaper.StyleSuggestionActivity;
import com.android.settings.wallpaper.WallpaperSuggestionActivity;
import com.android.settings.wifi.calling.WifiCallingSuggestionActivity;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
/* loaded from: classes.dex */
public class SuggestionFeatureProviderImpl implements SuggestionFeatureProvider {
    private final Context mAppContext;
    private final MetricsFeatureProvider mMetricsFeatureProvider;

    @Override // com.android.settings.dashboard.suggestions.SuggestionFeatureProvider
    public Class<? extends Fragment> getContextualSuggestionFragment() {
        return null;
    }

    @Override // com.android.settings.dashboard.suggestions.SuggestionFeatureProvider
    public ComponentName getSuggestionServiceComponent() {
        return new ComponentName(this.mAppContext.getString(R.string.config_settingsintelligence_package_name), this.mAppContext.getString(R.string.config_settingsintelligence_suggestions_class));
    }

    @Override // com.android.settings.dashboard.suggestions.SuggestionFeatureProvider
    public boolean isSuggestionComplete(Context context, ComponentName componentName) {
        String className = componentName.getClassName();
        if (className.equals(WallpaperSuggestionActivity.class.getName())) {
            return WallpaperSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(StyleSuggestionActivity.class.getName())) {
            return StyleSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(FingerprintSuggestionActivity.class.getName())) {
            return FingerprintSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(FingerprintEnrollSuggestionActivity.class.getName())) {
            return FingerprintEnrollSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(ScreenLockSuggestionActivity.class.getName())) {
            return ScreenLockSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(WifiCallingSuggestionActivity.class.getName())) {
            return WifiCallingSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(Settings.NightDisplaySuggestionActivity.class.getName())) {
            return NightDisplayPreferenceController.isSuggestionComplete(context);
        }
        if (className.equals(ZenSuggestionActivity.class.getName())) {
            return ZenOnboardingActivity.isSuggestionComplete(context);
        }
        return false;
    }

    @Override // com.android.settings.dashboard.suggestions.SuggestionFeatureProvider
    public SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences("suggestions", 0);
    }

    public SuggestionFeatureProviderImpl(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mAppContext = applicationContext;
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(applicationContext).getMetricsFeatureProvider();
    }
}
