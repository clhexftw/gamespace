package com.android.settings.applications.assist;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.UserHandle;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import com.android.internal.app.AssistUtils;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class AssistFlashScreenPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener, LifecycleObserver, OnResume, OnPause {
    private final AssistUtils mAssistUtils;
    private Preference mPreference;
    private PreferenceScreen mScreen;
    private final SettingObserver mSettingObserver;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "flash";
    }

    public AssistFlashScreenPreferenceController(Context context, Lifecycle lifecycle) {
        super(context);
        this.mAssistUtils = new AssistUtils(context);
        this.mSettingObserver = new SettingObserver();
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return getCurrentAssist() != null && allowDisablingAssistDisclosure();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mScreen = preferenceScreen;
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        super.displayPreference(preferenceScreen);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        this.mSettingObserver.register(this.mContext.getContentResolver(), true);
        updatePreference();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        updatePreference();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        this.mSettingObserver.register(this.mContext.getContentResolver(), false);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "assist_disclosure_enabled", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreference() {
        Preference preference = this.mPreference;
        if (preference == null || !(preference instanceof TwoStatePreference)) {
            return;
        }
        if (isAvailable()) {
            if (this.mScreen.findPreference(getPreferenceKey()) == null) {
                this.mScreen.addPreference(this.mPreference);
            }
        } else {
            this.mScreen.removePreference(this.mPreference);
        }
        ComponentName currentAssist = getCurrentAssist();
        this.mPreference.setEnabled(AssistContextPreferenceController.isChecked(this.mContext) && isPreInstalledAssistant(currentAssist));
        ((TwoStatePreference) this.mPreference).setChecked(willShowFlash(currentAssist));
    }

    boolean willShowFlash(ComponentName componentName) {
        return AssistUtils.shouldDisclose(this.mContext, componentName);
    }

    boolean isPreInstalledAssistant(ComponentName componentName) {
        return AssistUtils.isPreinstalledAssistant(this.mContext, componentName);
    }

    boolean allowDisablingAssistDisclosure() {
        return AssistUtils.allowDisablingAssistDisclosure(this.mContext);
    }

    private ComponentName getCurrentAssist() {
        return this.mAssistUtils.getAssistComponentForUser(UserHandle.myUserId());
    }

    /* loaded from: classes.dex */
    class SettingObserver extends AssistSettingObserver {
        private final Uri URI = Settings.Secure.getUriFor("assist_disclosure_enabled");
        private final Uri CONTEXT_URI = Settings.Secure.getUriFor("assist_structure_enabled");

        SettingObserver() {
        }

        @Override // com.android.settings.applications.assist.AssistSettingObserver
        protected List<Uri> getSettingUris() {
            return Arrays.asList(this.URI, this.CONTEXT_URI);
        }

        @Override // com.android.settings.applications.assist.AssistSettingObserver
        public void onSettingChange() {
            AssistFlashScreenPreferenceController.this.updatePreference();
        }
    }
}
