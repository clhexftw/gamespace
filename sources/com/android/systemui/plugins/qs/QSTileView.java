package com.android.systemui.plugins.qs;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
/* loaded from: classes2.dex */
public abstract class QSTileView extends LinearLayout {
    public abstract int getDetailY();

    public abstract QSIconView getIcon();

    public abstract View getIconWithBackground();

    public View getLabel() {
        return null;
    }

    public View getLabelContainer() {
        return null;
    }

    public View getSecondaryIcon() {
        return null;
    }

    public View getSecondaryLabel() {
        return null;
    }

    public abstract void setPosition(int i);

    public QSTileView(Context context) {
        super(context);
    }
}
