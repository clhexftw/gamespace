package com.android.settings.security.screenlock;

import android.content.Context;
import com.android.internal.widget.LockPatternUtils;
/* loaded from: classes.dex */
public class PatternVisiblePreferenceController extends AbstractPatternSwitchPreferenceController {
    public PatternVisiblePreferenceController(Context context, int i, LockPatternUtils lockPatternUtils) {
        super(context, "visiblepattern", i, lockPatternUtils);
    }

    @Override // com.android.settings.security.screenlock.AbstractPatternSwitchPreferenceController
    protected boolean isEnabled(LockPatternUtils lockPatternUtils, int i) {
        return lockPatternUtils.isVisiblePatternEnabled(i);
    }

    @Override // com.android.settings.security.screenlock.AbstractPatternSwitchPreferenceController
    protected void setEnabled(LockPatternUtils lockPatternUtils, int i, boolean z) {
        lockPatternUtils.setVisiblePatternEnabled(z, i);
    }
}
