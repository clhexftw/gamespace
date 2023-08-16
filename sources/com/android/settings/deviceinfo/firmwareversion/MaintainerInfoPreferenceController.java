package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
/* loaded from: classes.dex */
public class MaintainerInfoPreferenceController extends BasePreferenceController {
    private Uri INTENT_URI_DATA;
    private final PackageManager mPackageManager;
    private final String maintainerLink;
    private final String maintainerName;

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

    public MaintainerInfoPreferenceController(Context context, String str) {
        super(context, str);
        this.maintainerName = context.getResources().getString(R.string.config_maintainer_name);
        String string = context.getResources().getString(R.string.config_maintainer_link);
        this.maintainerLink = string;
        this.mPackageManager = this.mContext.getPackageManager();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        this.INTENT_URI_DATA = Uri.parse(string);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (TextUtils.isEmpty(this.maintainerName) || TextUtils.isEmpty(this.maintainerLink)) ? 3 : 0;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.maintainerName;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(this.INTENT_URI_DATA);
            if (this.mPackageManager.queryIntentActivities(intent, 0).isEmpty()) {
                return true;
            }
            this.mContext.startActivity(intent);
            return true;
        }
        return false;
    }
}
