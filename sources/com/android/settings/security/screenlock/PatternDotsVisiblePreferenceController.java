package com.android.settings.security.screenlock;

import android.content.Context;
import com.android.internal.widget.LockPatternUtils;
/* loaded from: classes.dex */
public class PatternDotsVisiblePreferenceController extends AbstractPatternSwitchPreferenceController {
    public PatternDotsVisiblePreferenceController(Context context, int i, LockPatternUtils lockPatternUtils) {
        super(context, "visibledots", i, lockPatternUtils);
    }

    @Override // com.android.settings.security.screenlock.AbstractPatternSwitchPreferenceController
    protected boolean isEnabled(LockPatternUtils lockPatternUtils, int i) {
        return lockPatternUtils.isVisibleDotsEnabled(i);
    }

    @Override // com.android.settings.security.screenlock.AbstractPatternSwitchPreferenceController
    protected void setEnabled(LockPatternUtils lockPatternUtils, int i, boolean z) {
        lockPatternUtils.setVisibleDotsEnabled(z, i);
    }
}
