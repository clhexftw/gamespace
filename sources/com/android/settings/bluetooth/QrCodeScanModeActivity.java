package com.android.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.android.settingslib.R$id;
/* loaded from: classes.dex */
public class QrCodeScanModeActivity extends QrCodeScanModeBaseActivity {
    private boolean mIsGroupOp;
    private BluetoothDevice mSink;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.bluetooth.QrCodeScanModeBaseActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.android.settings.bluetooth.QrCodeScanModeBaseActivity
    protected void handleIntent(Intent intent) {
        String action = intent != null ? intent.getAction() : null;
        if (action == null) {
            finish();
        } else if (action.equals("android.settings.BLUETOOTH_LE_AUDIO_QR_CODE_SCANNER")) {
            showQrCodeScannerFragment(intent);
        } else {
            finish();
        }
    }

    protected void showQrCodeScannerFragment(Intent intent) {
        if (intent == null) {
            return;
        }
        this.mSink = (BluetoothDevice) intent.getParcelableExtra("bluetooth_device_sink");
        this.mIsGroupOp = intent.getBooleanExtra("bluetooth_sink_is_group", false);
        QrCodeScanModeFragment qrCodeScanModeFragment = (QrCodeScanModeFragment) this.mFragmentManager.findFragmentByTag("qr_code_scanner_fragment");
        if (qrCodeScanModeFragment == null) {
            QrCodeScanModeFragment qrCodeScanModeFragment2 = new QrCodeScanModeFragment(this.mIsGroupOp, this.mSink);
            FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
            beginTransaction.replace(R$id.fragment_container, qrCodeScanModeFragment2, "qr_code_scanner_fragment");
            beginTransaction.commit();
        } else if (qrCodeScanModeFragment.isVisible()) {
        } else {
            this.mFragmentManager.popBackStackImmediate();
        }
    }
}
