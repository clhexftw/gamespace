package com.android.settings.display;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.quickaccesswallet.QuickAccessWalletClient;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.overlay.FeatureFactory;
/* loaded from: classes.dex */
public class WalletPrivacyPreferenceController extends TogglePreferenceController {
    private static final String SETTING_KEY = "lockscreen_show_wallet";
    private final QuickAccessWalletClient mClient;

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public WalletPrivacyPreferenceController(Context context, String str) {
        super(context, str);
        this.mClient = initWalletClient();
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), SETTING_KEY, 0) != 0;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return Settings.Secure.putInt(this.mContext.getContentResolver(), SETTING_KEY, z ? 1 : 0);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mContext.getText(isSecure() ? R.string.lockscreen_privacy_wallet_summary : R.string.lockscreen_privacy_not_secure);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (!CustomizableLockScreenUtils.isFeatureEnabled(this.mContext) && isEnabled()) {
            return !isSecure() ? 5 : 0;
        }
        return 3;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(getAvailabilityStatus() == 0);
        refreshSummary(preference);
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_display;
    }

    private boolean isEnabled() {
        return this.mClient.isWalletServiceAvailable();
    }

    private boolean isSecure() {
        return FeatureFactory.getFactory(this.mContext).getSecurityFeatureProvider().getLockPatternUtils(this.mContext).isSecure(UserHandle.myUserId());
    }

    QuickAccessWalletClient initWalletClient() {
        return QuickAccessWalletClient.create(this.mContext);
    }
}
