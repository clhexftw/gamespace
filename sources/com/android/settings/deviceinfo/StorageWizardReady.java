package com.android.settings.deviceinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.android.settings.R;
/* loaded from: classes.dex */
public class StorageWizardReady extends StorageWizardBase {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.deviceinfo.StorageWizardBase, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mDisk == null) {
            finish();
            return;
        }
        setContentView(R.layout.storage_wizard_generic);
        setHeaderText(R.string.storage_wizard_ready_title, getDiskShortDescription());
        if (findFirstVolume(1) != null) {
            if (getIntent().getBooleanExtra("migrate_skip", false)) {
                setBodyText(R.string.storage_wizard_ready_v2_internal_body, getDiskDescription());
            } else {
                setBodyText(R.string.storage_wizard_ready_v2_internal_moved_body, getDiskDescription(), getDiskShortDescription());
            }
        } else {
            setBodyText(R.string.storage_wizard_ready_v2_external_body, getDiskDescription());
        }
        ((ImageView) findViewById(R.id.storage_wizard_body_image)).setImageResource(R.drawable.ic_storage_wizard_ready);
        setIcon(R.drawable.ic_test_tick);
        setNextButtonText(R.string.done, new CharSequence[0]);
        setBackButtonVisibility(4);
    }

    @Override // com.android.settings.deviceinfo.StorageWizardBase
    public void onNavigateNext(View view) {
        finishAffinity();
    }
}
