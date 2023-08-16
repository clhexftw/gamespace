package org.nameless.gamespace.settings;

import android.os.Bundle;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.nameless.gamespace.R;
/* compiled from: PerAppSettingsActivity.kt */
/* loaded from: classes.dex */
public final class PerAppSettingsActivity extends Hilt_PerAppSettingsActivity {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new PerAppSettingsFragment()).commit();
        }
    }

    /* compiled from: PerAppSettingsActivity.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
