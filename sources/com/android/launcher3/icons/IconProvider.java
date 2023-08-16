package com.android.launcher3.icons;

import android.content.res.Resources;
import androidx.core.os.BuildCompat;
import com.android.launcher3.util.override.MainThreadInitializedObject;
/* loaded from: classes.dex */
public class IconProvider {
    static final int CONFIG_ICON_MASK_RES_ID = Resources.getSystem().getIdentifier("config_icon_mask", "string", "android");
    public static final boolean ATLEAST_T = BuildCompat.isAtLeastT();
    public static MainThreadInitializedObject<IconProvider> INSTANCE = MainThreadInitializedObject.forOverride(IconProvider.class, R$string.icon_provider_class);
}
