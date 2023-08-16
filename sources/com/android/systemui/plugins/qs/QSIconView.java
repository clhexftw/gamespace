package com.android.systemui.plugins.qs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes2.dex */
public abstract class QSIconView extends ViewGroup {
    public abstract View getIconView();

    public QSIconView(Context context) {
        super(context);
    }
}
