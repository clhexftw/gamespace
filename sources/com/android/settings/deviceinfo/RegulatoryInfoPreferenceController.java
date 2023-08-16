package com.android.settings.deviceinfo;

import android.content.Context;
import android.content.Intent;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
/* loaded from: classes.dex */
public class RegulatoryInfoPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private static final Intent INTENT_PROBE = new Intent("android.settings.SHOW_REGULATORY_INFO");

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "regulatory_info";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return false;
    }

    public RegulatoryInfoPreferenceController(Context context) {
        super(context);
    }
}
