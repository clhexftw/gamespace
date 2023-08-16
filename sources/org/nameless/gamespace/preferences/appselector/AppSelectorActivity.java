package org.nameless.gamespace.preferences.appselector;

import android.os.Bundle;
import org.nameless.gamespace.R;
/* compiled from: AppSelectorActivity.kt */
/* loaded from: classes.dex */
public final class AppSelectorActivity extends Hilt_AppSelectorActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new AppSelectorFragment()).commit();
        }
    }
}
