package com.android.settings.accessibility;

import android.content.Context;
import com.android.settingslib.display.DisplayDensityUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
/* loaded from: classes.dex */
class DisplaySizeData extends PreviewSizeData<Integer> {
    private final DisplayDensityUtils mDensity;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DisplaySizeData(Context context) {
        super(context);
        DisplayDensityUtils displayDensityUtils = new DisplayDensityUtils(getContext());
        this.mDensity = displayDensityUtils;
        int currentIndexForDefaultDisplay = displayDensityUtils.getCurrentIndexForDefaultDisplay();
        if (currentIndexForDefaultDisplay < 0) {
            int i = getContext().getResources().getDisplayMetrics().densityDpi;
            setDefaultValue(Integer.valueOf(i));
            setInitialIndex(0);
            setValues(Collections.singletonList(Integer.valueOf(i)));
            return;
        }
        setDefaultValue(Integer.valueOf(displayDensityUtils.getDefaultDensityForDefaultDisplay()));
        setInitialIndex(currentIndexForDefaultDisplay);
        setValues((List) Arrays.stream(displayDensityUtils.getDefaultDisplayDensityValues()).boxed().collect(Collectors.toList()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void commit(int i) {
        this.mDensity.setForcedDisplayDensity(i);
    }
}
