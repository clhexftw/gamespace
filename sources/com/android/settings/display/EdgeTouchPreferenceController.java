package com.android.settings.display;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import org.nameless.custom.preference.SwitchPreference;
import org.nameless.display.DisplayFeatureManager;
/* loaded from: classes.dex */
public class EdgeTouchPreferenceController extends TogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final String KEY = "unlimit_edge_touch_mode";
    private final DisplayFeatureManager mManager;
    private SwitchPreference mPreference;
    private SettingObserver mSettingObserver;

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return 0;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public EdgeTouchPreferenceController(Context context, String str) {
        super(context, str);
        this.mManager = DisplayFeatureManager.getInstance();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mSettingObserver = new SettingObserver(this.mPreference);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mManager.hasFeature(2) ? 0 : 3;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.System.getIntForUser(this.mContext.getContentResolver(), KEY, 0, 0) == 1;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(final boolean z) {
        if (!z) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), KEY, 0, 0);
            return true;
        }
        new AlertDialog.Builder(this.mContext).setTitle(this.mContext.getText(R.string.confirm_before_enable_title)).setMessage(this.mContext.getText(R.string.unlimit_edge_touch_warning)).setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() { // from class: com.android.settings.display.EdgeTouchPreferenceController.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Settings.System.putIntForUser(((AbstractPreferenceController) EdgeTouchPreferenceController.this).mContext.getContentResolver(), EdgeTouchPreferenceController.KEY, z ? 1 : 0, 0);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        return isChecked();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(this.mContext.getContentResolver());
            this.mSettingObserver.onChange(false, null);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.unregister(this.mContext.getContentResolver());
        }
    }

    /* loaded from: classes.dex */
    private class SettingObserver extends ContentObserver {
        private final Preference mPreference;
        private final Uri mUri;

        SettingObserver(Preference preference) {
            super(new Handler());
            this.mUri = Settings.System.getUriFor(EdgeTouchPreferenceController.KEY);
            this.mPreference = preference;
        }

        public void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.mUri, false, this);
        }

        public void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (uri == null || this.mUri.equals(uri)) {
                EdgeTouchPreferenceController.this.updateState(this.mPreference);
            }
        }
    }
}
