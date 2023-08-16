package com.android.settings.bluetooth;

import android.os.Bundle;
import com.android.settings.R;
import com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity;
/* loaded from: classes.dex */
public final class DevicePickerActivity extends CollapsingToolbarBaseActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addSystemFlags(524288);
        setContentView(R.layout.bluetooth_device_picker);
    }
}
