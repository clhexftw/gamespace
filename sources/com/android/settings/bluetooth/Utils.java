package com.android.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.R;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
/* loaded from: classes.dex */
public final class Utils {
    private static final BluetoothUtils.ErrorListener mErrorListener = new BluetoothUtils.ErrorListener() { // from class: com.android.settings.bluetooth.Utils.1
        @Override // com.android.settingslib.bluetooth.BluetoothUtils.ErrorListener
        public void onShowError(Context context, String str, int i) {
            Utils.showError(context, str, i);
        }
    };
    private static final LocalBluetoothManager.BluetoothManagerCallback mOnInitCallback = new LocalBluetoothManager.BluetoothManagerCallback() { // from class: com.android.settings.bluetooth.Utils.2
        @Override // com.android.settingslib.bluetooth.LocalBluetoothManager.BluetoothManagerCallback
        public void onBluetoothManagerInitialized(Context context, LocalBluetoothManager localBluetoothManager) {
            BluetoothUtils.setErrorListener(Utils.mErrorListener);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlertDialog showDisconnectDialog(Context context, AlertDialog alertDialog, DialogInterface.OnClickListener onClickListener, CharSequence charSequence, CharSequence charSequence2) {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(context).setPositiveButton(17039370, onClickListener).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
        } else {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            alertDialog.setButton(-1, context.getText(17039370), onClickListener);
        }
        alertDialog.setTitle(charSequence);
        alertDialog.setMessage(charSequence2);
        alertDialog.show();
        return alertDialog;
    }

    static void showConnectingError(Context context, String str, LocalBluetoothManager localBluetoothManager) {
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().visible(context, 0, 869, 0);
        showError(context, str, R.string.bluetooth_connecting_error_message, localBluetoothManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void showError(Context context, String str, int i) {
        showError(context, str, i, getLocalBtManager(context));
    }

    private static void showError(Context context, String str, int i, LocalBluetoothManager localBluetoothManager) {
        String string = context.getString(i, str);
        Context foregroundActivity = localBluetoothManager.getForegroundActivity();
        if (localBluetoothManager.isForegroundActivity()) {
            try {
                new AlertDialog.Builder(foregroundActivity).setTitle(R.string.bluetooth_error_title).setMessage(string).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).show();
                return;
            } catch (Exception e) {
                Log.e("BluetoothUtils", "Cannot show error dialog.", e);
                return;
            }
        }
        Toast.makeText(context, string, 0).show();
    }

    public static LocalBluetoothManager getLocalBtManager(Context context) {
        return LocalBluetoothManager.getInstance(context, mOnInitCallback);
    }

    public static String createRemoteName(Context context, BluetoothDevice bluetoothDevice) {
        String alias = bluetoothDevice != null ? bluetoothDevice.getAlias() : null;
        return alias == null ? context.getString(R.string.unknown) : alias;
    }

    public static boolean isBluetoothScanningEnabled(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "ble_scan_always_enabled", 0) == 1;
    }

    public static boolean isAdvancedDetailsHeader(BluetoothDevice bluetoothDevice) {
        if (!DeviceConfig.getBoolean("settings_ui", "bt_advanced_header_enabled", true)) {
            Log.d("BluetoothUtils", "isAdvancedDetailsHeader: advancedEnabled is false");
            return false;
        } else if (BluetoothUtils.getBooleanMetaData(bluetoothDevice, 6)) {
            Log.d("BluetoothUtils", "isAdvancedDetailsHeader: untetheredHeadset is true");
            return true;
        } else {
            String stringMetaData = BluetoothUtils.getStringMetaData(bluetoothDevice, 17);
            if (TextUtils.equals(stringMetaData, "Untethered Headset") || TextUtils.equals(stringMetaData, "Watch") || TextUtils.equals(stringMetaData, "Default")) {
                Log.d("BluetoothUtils", "isAdvancedDetailsHeader: deviceType is " + stringMetaData);
                return true;
            }
            return false;
        }
    }

    public static String findBluetoothPackageName(Context context) throws PackageManager.NameNotFoundException {
        String[] packagesForUid;
        PackageManager packageManager = context.createContextAsUser(UserHandle.SYSTEM, 0).getPackageManager();
        String str = null;
        for (String str2 : packageManager.getPackagesForUid(1002)) {
            try {
                ActivityInfo[] activityInfoArr = packageManager.getPackageInfo(str2, 4203009).activities;
                if (activityInfoArr != null) {
                    for (ActivityInfo activityInfo : activityInfoArr) {
                        if ("com.android.bluetooth.opp.BluetoothOppLauncherActivity".equals(activityInfo.name)) {
                            if (str != null) {
                                throw new PackageManager.NameNotFoundException("multiple main bluetooth packages found");
                            }
                            str = str2;
                        }
                    }
                    continue;
                }
            } catch (PackageManager.NameNotFoundException e) {
                throw e;
            }
        }
        if (str != null) {
            return str;
        }
        throw new PackageManager.NameNotFoundException("Could not find main bluetooth package");
    }
}
