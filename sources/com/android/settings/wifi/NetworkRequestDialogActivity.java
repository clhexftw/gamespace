package com.android.settings.wifi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Message;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.R;
import com.android.settings.wifi.NetworkRequestErrorDialogFragment;
import java.util.List;
/* loaded from: classes.dex */
public class NetworkRequestDialogActivity extends FragmentActivity implements WifiManager.NetworkRequestMatchCallback {
    private static String TAG = "NetworkRequestDialogActivity";
    NetworkRequestDialogBaseFragment mDialogFragment;
    private final Handler mHandler = new Handler() { // from class: com.android.settings.wifi.NetworkRequestDialogActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            removeMessages(0);
            NetworkRequestDialogActivity.this.stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE.TIME_OUT);
        }
    };
    boolean mIsSpecifiedSsid;
    private WifiConfiguration mMatchedConfig;
    ProgressDialog mProgressDialog;
    boolean mShowingErrorDialog;
    private WifiManager.NetworkRequestUserSelectionCallback mUserSelectionCallback;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.mIsSpecifiedSsid = intent.getBooleanExtra("com.android.settings.wifi.extra.REQUEST_IS_FOR_SINGLE_NETWORK", false);
        }
        if (this.mIsSpecifiedSsid) {
            showProgressDialog(getString(R.string.network_connection_searching_message));
            return;
        }
        NetworkRequestDialogFragment newInstance = NetworkRequestDialogFragment.newInstance();
        this.mDialogFragment = newInstance;
        newInstance.show(getSupportFragmentManager(), TAG);
    }

    private void showProgressDialog(String str) {
        dismissDialogs();
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.mProgressDialog = progressDialog;
        progressDialog.setIndeterminate(true);
        this.mProgressDialog.setCancelable(false);
        this.mProgressDialog.setMessage(str);
        this.mProgressDialog.show();
    }

    private void showSingleSsidRequestDialog(String str, boolean z) {
        dismissDialogs();
        this.mDialogFragment = new NetworkRequestSingleSsidDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("DIALOG_REQUEST_SSID", str);
        bundle.putBoolean("DIALOG_IS_TRYAGAIN", z);
        this.mDialogFragment.setArguments(bundle);
        this.mDialogFragment.show(getSupportFragmentManager(), TAG);
    }

    void dismissDialogs() {
        NetworkRequestDialogBaseFragment networkRequestDialogBaseFragment = this.mDialogFragment;
        if (networkRequestDialogBaseFragment != null) {
            networkRequestDialogBaseFragment.dismiss();
            this.mDialogFragment = null;
        }
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        WifiManager wifiManager = (WifiManager) getSystemService(WifiManager.class);
        if (wifiManager != null) {
            wifiManager.registerNetworkRequestMatchCallback(new HandlerExecutor(this.mHandler), this);
        }
        this.mHandler.sendEmptyMessageDelayed(0, 30000L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.mHandler.removeMessages(0);
        WifiManager wifiManager = (WifiManager) getSystemService(WifiManager.class);
        if (wifiManager != null) {
            wifiManager.unregisterNetworkRequestMatchCallback(this);
        }
        super.onPause();
    }

    protected void stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE error_dialog_type) {
        dismissDialogs();
        NetworkRequestErrorDialogFragment newInstance = NetworkRequestErrorDialogFragment.newInstance();
        newInstance.setRejectCallback(this.mUserSelectionCallback);
        Bundle bundle = new Bundle();
        bundle.putSerializable("DIALOG_ERROR_TYPE", error_dialog_type);
        newInstance.setArguments(bundle);
        newInstance.show(getSupportFragmentManager(), TAG);
        this.mShowingErrorDialog = true;
    }

    public void onUserSelectionCallbackRegistration(WifiManager.NetworkRequestUserSelectionCallback networkRequestUserSelectionCallback) {
        if (this.mIsSpecifiedSsid) {
            this.mUserSelectionCallback = networkRequestUserSelectionCallback;
            return;
        }
        NetworkRequestDialogBaseFragment networkRequestDialogBaseFragment = this.mDialogFragment;
        if (networkRequestDialogBaseFragment != null) {
            networkRequestDialogBaseFragment.onUserSelectionCallbackRegistration(networkRequestUserSelectionCallback);
        }
    }

    public void onAbort() {
        stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE.ABORT);
    }

    public void onMatch(List<ScanResult> list) {
        if (this.mShowingErrorDialog) {
            return;
        }
        this.mHandler.removeMessages(0);
        if (this.mIsSpecifiedSsid) {
            if (this.mMatchedConfig == null) {
                WifiConfiguration wifiConfig = WifiUtils.getWifiConfig(null, list.get(0));
                this.mMatchedConfig = wifiConfig;
                showSingleSsidRequestDialog(android.net.wifi.WifiInfo.sanitizeSsid(wifiConfig.SSID), false);
                return;
            }
            return;
        }
        NetworkRequestDialogBaseFragment networkRequestDialogBaseFragment = this.mDialogFragment;
        if (networkRequestDialogBaseFragment != null) {
            networkRequestDialogBaseFragment.onMatch(list);
        }
    }

    public void onUserSelectionConnectSuccess(WifiConfiguration wifiConfiguration) {
        if (isFinishing()) {
            return;
        }
        Toast.makeText(this, R.string.network_connection_connect_successful, 0).show();
        setResult(-1);
        finish();
    }

    public void onUserSelectionConnectFailure(WifiConfiguration wifiConfiguration) {
        if (isFinishing()) {
            return;
        }
        Toast.makeText(this, R.string.network_connection_connect_failure, 0).show();
        setResult(-1);
        finish();
    }

    public void onClickConnectButton() {
        WifiManager.NetworkRequestUserSelectionCallback networkRequestUserSelectionCallback = this.mUserSelectionCallback;
        if (networkRequestUserSelectionCallback != null) {
            networkRequestUserSelectionCallback.select(this.mMatchedConfig);
            showProgressDialog(getString(R.string.network_connection_connecting_message));
        }
    }

    public void onClickRescanButton() {
        this.mHandler.sendEmptyMessageDelayed(0, 30000L);
        this.mShowingErrorDialog = false;
        if (this.mIsSpecifiedSsid) {
            this.mMatchedConfig = null;
            showProgressDialog(getString(R.string.network_connection_searching_message));
            return;
        }
        NetworkRequestDialogFragment newInstance = NetworkRequestDialogFragment.newInstance();
        this.mDialogFragment = newInstance;
        newInstance.show(getSupportFragmentManager(), TAG);
    }

    public void onCancel() {
        dismissDialogs();
        WifiManager.NetworkRequestUserSelectionCallback networkRequestUserSelectionCallback = this.mUserSelectionCallback;
        if (networkRequestUserSelectionCallback != null) {
            networkRequestUserSelectionCallback.reject();
        }
        finish();
    }
}
