package com.android.settingslib.widget;

import android.graphics.drawable.Drawable;
/* loaded from: classes2.dex */
public abstract class CandidateInfo {
    public final boolean enabled;

    public abstract String getKey();

    public abstract Drawable loadIcon();

    public abstract CharSequence loadLabel();

    public CandidateInfo(boolean z) {
        this.enabled = z;
    }
}
