package com.android.settings.security.screenlock;

import android.content.Context;
import com.android.internal.widget.LockPatternUtils;
/* loaded from: classes.dex */
public class PatternErrorVisiblePreferenceController extends AbstractPatternSwitchPreferenceController {
    public PatternErrorVisiblePreferenceController(Context context, int i, LockPatternUtils lockPatternUtils) {
        super(context, "visible_error_pattern", i, lockPatternUtils);
    }

    @Override // com.android.settings.security.screenlock.AbstractPatternSwitchPreferenceController
    protected boolean isEnabled(LockPatternUtils lockPatternUtils, int i) {
        return lockPatternUtils.isShowErrorPath(i);
    }

    @Override // com.android.settings.security.screenlock.AbstractPatternSwitchPreferenceController
    protected void setEnabled(LockPatternUtils lockPatternUtils, int i, boolean z) {
        lockPatternUtils.setShowErrorPath(z, i);
    }
}
