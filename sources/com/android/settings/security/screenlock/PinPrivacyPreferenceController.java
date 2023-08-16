package com.android.settings.security.screenlock;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PinPrivacyPreferenceController.kt */
/* loaded from: classes.dex */
public final class PinPrivacyPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    public static final Companion Companion = new Companion(null);
    private final LockPatternUtils lockPatternUtils;
    private final int userId;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "enhancedPinPrivacy";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PinPrivacyPreferenceController(Context context, int i, LockPatternUtils lockPatternUtils) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(lockPatternUtils, "lockPatternUtils");
        this.userId = i;
        this.lockPatternUtils = lockPatternUtils;
    }

    /* compiled from: PinPrivacyPreferenceController.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.lockPatternUtils.getCredentialTypeForUser(this.userId) == 3;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        LockPatternUtils lockPatternUtils = this.lockPatternUtils;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
        lockPatternUtils.setPinEnhancedPrivacyEnabled(((Boolean) obj).booleanValue(), this.userId);
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        ((TwoStatePreference) preference).setChecked(getCurrentPreferenceState());
    }

    private final boolean getCurrentPreferenceState() {
        return this.lockPatternUtils.isPinEnhancedPrivacyEnabled(this.userId);
    }
}
