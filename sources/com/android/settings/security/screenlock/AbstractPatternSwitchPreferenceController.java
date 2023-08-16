package com.android.settings.security.screenlock;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
/* loaded from: classes.dex */
public abstract class AbstractPatternSwitchPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private final String mKey;
    private final LockPatternUtils mLockPatternUtils;
    private final int mUserId;

    protected abstract boolean isEnabled(LockPatternUtils lockPatternUtils, int i);

    protected abstract void setEnabled(LockPatternUtils lockPatternUtils, int i, boolean z);

    public AbstractPatternSwitchPreferenceController(Context context, String str, int i, LockPatternUtils lockPatternUtils) {
        super(context);
        this.mKey = str;
        this.mUserId = i;
        this.mLockPatternUtils = lockPatternUtils;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return isPatternLock();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.mKey;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ((TwoStatePreference) preference).setChecked(isEnabled(this.mLockPatternUtils, this.mUserId));
    }

    private boolean isPatternLock() {
        return this.mLockPatternUtils.isSecure(this.mUserId) && this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mUserId) == 65536;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        setEnabled(this.mLockPatternUtils, this.mUserId, ((Boolean) obj).booleanValue());
        return true;
    }
}
