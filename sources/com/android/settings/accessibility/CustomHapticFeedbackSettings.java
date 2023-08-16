package com.android.settings.accessibility;

import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
/* loaded from: classes.dex */
public class CustomHapticFeedbackSettings extends DashboardFragment {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return "CustomHapticFeedbackSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1292;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.custom_haptic_feedback;
    }
}
