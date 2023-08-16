package com.android.settings.sound;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.internal.util.nameless.CustomUtils;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
/* loaded from: classes.dex */
public class DolbyAtmosPreferenceController extends BasePreferenceController {
    private final String mClassName;
    private final Context mContext;
    private final String mPackageName;

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

    public DolbyAtmosPreferenceController(Context context, String str) {
        super(context, str);
        this.mContext = context;
        String string = context.getResources().getString(R.string.config_dolbyAtmosPackage);
        if (!TextUtils.isEmpty(string)) {
            String[] split = string.split("/");
            if (split.length == 2) {
                this.mPackageName = split[0];
                this.mClassName = split[1];
                return;
            }
        }
        this.mPackageName = "";
        this.mClassName = "";
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (TextUtils.isEmpty(this.mPackageName) || !CustomUtils.isPackageInstalled(this.mContext, this.mPackageName, false)) ? 3 : 0;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(this.mPackageName, this.mClassName));
                this.mContext.startActivity(intent);
                return true;
            } catch (Exception unused) {
                return true;
            }
        }
        return false;
    }
}
