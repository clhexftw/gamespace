package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.text.BidiFormatter;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.deviceinfo.VersionUtils;
/* loaded from: classes.dex */
public class SimpleBuildNumberPreferenceController extends BasePreferenceController {
    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 1;
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

    public SimpleBuildNumberPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(BidiFormatter.getInstance().unicodeWrap(Build.DISPLAY));
        String namelessVersion = VersionUtils.getNamelessVersion();
        if (!namelessVersion.equals("")) {
            sb.append("\n");
            sb.append(namelessVersion);
        }
        return sb.toString();
    }
}
