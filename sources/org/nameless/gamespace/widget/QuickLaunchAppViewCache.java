package org.nameless.gamespace.widget;

import android.graphics.drawable.Drawable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
/* compiled from: QuickLaunchAppViewCache.kt */
/* loaded from: classes.dex */
public final class QuickLaunchAppViewCache {
    public static final QuickLaunchAppViewCache INSTANCE = new QuickLaunchAppViewCache();
    private static final Set<String> appSet = new LinkedHashSet();
    private static final Map<String, Drawable> iconMap = new LinkedHashMap();

    private QuickLaunchAppViewCache() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x007f, code lost:
        r10 = kotlin.text.StringsKt__StringsKt.split$default(r4, new java.lang.String[]{";"}, false, 0, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002c, code lost:
        r10 = kotlin.text.StringsKt__StringsKt.split$default(r4, new java.lang.String[]{";"}, false, 0, 6, null);
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateAppList(android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.nameless.gamespace.widget.QuickLaunchAppViewCache.updateAppList(android.content.Context):void");
    }

    public final int getAppCount() {
        return appSet.size();
    }

    public final Set<String> getAppSet() {
        return appSet;
    }

    public final Map<String, Drawable> getIconMap() {
        return iconMap;
    }
}
