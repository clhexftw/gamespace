package ink.kscope.settings.wifi.tether;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
/* loaded from: classes2.dex */
public class WifiTetherAutoOffPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private ListPreference mPreference;
    private final WifiManager mWifiManager;

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public WifiTetherAutoOffPreferenceController(Context context, String str) {
        super(context, str);
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ListPreference) preferenceScreen.findPreference(getPreferenceKey());
        updateDisplay();
    }

    private long getAutoOffTimeout() {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        if (softApConfiguration.isAutoShutdownEnabled()) {
            return softApConfiguration.getShutdownTimeoutMillis() / 1000;
        }
        return 0L;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        try {
            long parseLong = Long.parseLong((String) obj);
            SoftApConfiguration.Builder builder = new SoftApConfiguration.Builder(this.mWifiManager.getSoftApConfiguration());
            setShutdownTimeout(builder, parseLong);
            return this.mWifiManager.setSoftApConfiguration(builder.build());
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public void updateConfig(SoftApConfiguration.Builder builder) {
        if (builder == null) {
            return;
        }
        setShutdownTimeout(builder, getAutoOffTimeout());
    }

    private void setShutdownTimeout(SoftApConfiguration.Builder builder, long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        builder.setAutoShutdownEnabled(i > 0);
        if (i > 0) {
            builder.setShutdownTimeoutMillis(j * 1000);
        }
    }

    public void updateDisplay() {
        ListPreference listPreference = this.mPreference;
        if (listPreference != null) {
            listPreference.setValue(String.valueOf(getAutoOffTimeout()));
        }
    }
}
