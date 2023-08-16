package org.nameless.gamespace.settings;

import android.os.Bundle;
import org.nameless.gamespace.R;
/* compiled from: SettingsActivity.kt */
/* loaded from: classes.dex */
public final class SettingsActivity extends Hilt_SettingsActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new SettingsFragment()).commit();
        }
    }
}
