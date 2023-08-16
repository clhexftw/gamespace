package com.android.settings.display.iris;

import org.nameless.display.DisplayFeatureManager;
/* loaded from: classes.dex */
public class FeaturesHolder {
    public static final boolean MEMC_FHD_SUPPORTED = DisplayFeatureManager.getInstance().hasFeature(32);
    public static final boolean MEMC_QHD_SUPPORTED = DisplayFeatureManager.getInstance().hasFeature(64);
    public static final boolean SDR2HDR_SUPPORTED = DisplayFeatureManager.getInstance().hasFeature(16);
}
