package com.android.settings.display;

import android.os.Bundle;
import android.provider.Settings;
import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.widget.SettingsMainSwitchPreference;
/* loaded from: classes.dex */
public class AutoBrightnessSettings extends DashboardFragment {
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider(R.xml.auto_brightness_detail);
    private AutoBrightnessObserver mAutoBrightnessObserver;
    private final Runnable mCallback = new Runnable() { // from class: com.android.settings.display.AutoBrightnessSettings$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            AutoBrightnessSettings.this.lambda$new$0();
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return "AutoBrightnessSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1381;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        int i = Settings.System.getInt(getContext().getContentResolver(), "screen_brightness_mode", 1);
        SettingsMainSwitchPreference settingsMainSwitchPreference = (SettingsMainSwitchPreference) findPreference("auto_brightness");
        if (settingsMainSwitchPreference == null) {
            return;
        }
        settingsMainSwitchPreference.setChecked(i == 1);
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAutoBrightnessObserver = new AutoBrightnessObserver(getContext());
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mAutoBrightnessObserver.subscribe(this.mCallback);
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mAutoBrightnessObserver.unsubscribe();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.auto_brightness_detail;
    }

    @Override // com.android.settings.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_auto_brightness;
    }
}
