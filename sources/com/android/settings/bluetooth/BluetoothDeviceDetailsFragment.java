package com.android.settings.bluetooth;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.provider.DeviceConfig;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.android.settings.R;
import com.android.settings.dashboard.RestrictedDashboardFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.slices.BlockingSlicePrefController;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class BluetoothDeviceDetailsFragment extends RestrictedDashboardFragment {
    static int EDIT_DEVICE_NAME_ITEM_ID = 1;
    static TestDataFactory sTestDataFactory;
    CachedBluetoothDevice mCachedDevice;
    String mDeviceAddress;
    LocalBluetoothManager mManager;
    private final ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface TestDataFactory {
        CachedBluetoothDevice getDevice(String str);

        LocalBluetoothManager getManager(Context context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return "BTDeviceDetailsFrg";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1009;
    }

    public BluetoothDeviceDetailsFragment() {
        super("no_config_bluetooth");
        this.mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.settings.bluetooth.BluetoothDeviceDetailsFragment.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                View view = BluetoothDeviceDetailsFragment.this.getView();
                if (view != null && view.getWidth() > 0) {
                    BluetoothDeviceDetailsFragment.this.updateExtraControlUri(view.getWidth() - BluetoothDeviceDetailsFragment.this.getPaddingSize());
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(BluetoothDeviceDetailsFragment.this.mOnGlobalLayoutListener);
                }
            }
        };
    }

    LocalBluetoothManager getLocalBluetoothManager(Context context) {
        TestDataFactory testDataFactory = sTestDataFactory;
        if (testDataFactory != null) {
            return testDataFactory.getManager(context);
        }
        return Utils.getLocalBtManager(context);
    }

    CachedBluetoothDevice getCachedDevice(String str) {
        TestDataFactory testDataFactory = sTestDataFactory;
        if (testDataFactory != null) {
            return testDataFactory.getDevice(str);
        }
        return this.mManager.getCachedDeviceManager().findDevice(this.mManager.getBluetoothAdapter().getRemoteDevice(str));
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        this.mDeviceAddress = getArguments().getString("device_address");
        this.mManager = getLocalBluetoothManager(context);
        this.mCachedDevice = getCachedDevice(this.mDeviceAddress);
        super.onAttach(context);
        if (this.mCachedDevice == null) {
            Log.w("BTDeviceDetailsFrg", "onAttach() CachedDevice is null!");
            finish();
            return;
        }
        ((AdvancedBluetoothDetailsHeaderController) use(AdvancedBluetoothDetailsHeaderController.class)).init(this.mCachedDevice);
        ((LeAudioBluetoothDetailsHeaderController) use(LeAudioBluetoothDetailsHeaderController.class)).init(this.mCachedDevice, this.mManager);
        BluetoothFeatureProvider bluetoothFeatureProvider = FeatureFactory.getFactory(context).getBluetoothFeatureProvider();
        ((BlockingSlicePrefController) use(BlockingSlicePrefController.class)).setSliceUri(DeviceConfig.getBoolean("settings_ui", "bt_slice_settings_enabled", true) ? bluetoothFeatureProvider.getBluetoothDeviceSettingsUri(this.mCachedDevice.getDevice()) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateExtraControlUri(int r5) {
        /*
            r4 = this;
            android.content.Context r0 = r4.getContext()
            com.android.settings.overlay.FeatureFactory r0 = com.android.settings.overlay.FeatureFactory.getFactory(r0)
            com.android.settings.bluetooth.BluetoothFeatureProvider r0 = r0.getBluetoothFeatureProvider()
            java.lang.String r1 = "settings_ui"
            java.lang.String r2 = "bt_slice_settings_enabled"
            r3 = 1
            boolean r1 = android.provider.DeviceConfig.getBoolean(r1, r2, r3)
            com.android.settingslib.bluetooth.CachedBluetoothDevice r2 = r4.mCachedDevice
            android.bluetooth.BluetoothDevice r2 = r2.getDevice()
            java.lang.String r0 = r0.getBluetoothDeviceControlUri(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            r3 = 0
            if (r2 != 0) goto L43
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.NullPointerException -> L3b
            r2.<init>()     // Catch: java.lang.NullPointerException -> L3b
            r2.append(r0)     // Catch: java.lang.NullPointerException -> L3b
            r2.append(r5)     // Catch: java.lang.NullPointerException -> L3b
            java.lang.String r5 = r2.toString()     // Catch: java.lang.NullPointerException -> L3b
            android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch: java.lang.NullPointerException -> L3b
            goto L44
        L3b:
            java.lang.String r5 = "BTDeviceDetailsFrg"
            java.lang.String r0 = "unable to parse uri"
            android.util.Log.d(r5, r0)
        L43:
            r5 = r3
        L44:
            java.lang.Class<com.android.settings.slices.SlicePreferenceController> r0 = com.android.settings.slices.SlicePreferenceController.class
            com.android.settingslib.core.AbstractPreferenceController r0 = r4.use(r0)
            com.android.settings.slices.SlicePreferenceController r0 = (com.android.settings.slices.SlicePreferenceController) r0
            if (r1 == 0) goto L4f
            r3 = r5
        L4f:
            r0.setSliceUri(r3)
            r0.onStart()
            androidx.preference.PreferenceScreen r4 = r4.getPreferenceScreen()
            r0.displayPreference(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.bluetooth.BluetoothDeviceDetailsFragment.updateExtraControlUri(int):void");
    }

    @Override // com.android.settings.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (onCreateView != null) {
            onCreateView.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        }
        return onCreateView;
    }

    @Override // com.android.settings.dashboard.RestrictedDashboardFragment, com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        finishFragmentIfNecessary();
    }

    void finishFragmentIfNecessary() {
        if (this.mCachedDevice.getBondState() == 10) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.bluetooth_device_details_fragment;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem add = menu.add(0, EDIT_DEVICE_NAME_ITEM_ID, 0, R.string.bluetooth_rename_button);
        add.setIcon(17302781);
        add.setShowAsAction(2);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == EDIT_DEVICE_NAME_ITEM_ID) {
            RemoteDeviceNameDialogFragment.newInstance(this.mCachedDevice).show(getFragmentManager(), "RemoteDeviceName");
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.settings.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        if (this.mCachedDevice != null) {
            Lifecycle settingsLifecycle = getSettingsLifecycle();
            arrayList.add(new BluetoothDetailsHeaderController(context, this, this.mCachedDevice, settingsLifecycle, this.mManager));
            arrayList.add(new BluetoothDetailsButtonsController(context, this, this.mCachedDevice, settingsLifecycle));
            arrayList.add(new BluetoothDetailsCompanionAppsController(context, this, this.mCachedDevice, settingsLifecycle));
            arrayList.add(new BluetoothDetailsSpatialAudioController(context, this, this.mCachedDevice, settingsLifecycle));
            arrayList.add(new BluetoothDetailsProfilesController(context, this, this.mManager, this.mCachedDevice, settingsLifecycle));
            arrayList.add(new BluetoothDetailsMacAddressController(context, this, this.mCachedDevice, settingsLifecycle));
            arrayList.add(new BluetoothDetailsRelatedToolsController(context, this, this.mCachedDevice, settingsLifecycle));
            arrayList.add(new BluetoothDetailsPairOtherController(context, this, this.mCachedDevice, settingsLifecycle));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPaddingSize() {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{16843709, 16843710});
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, 0) + obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }
}
