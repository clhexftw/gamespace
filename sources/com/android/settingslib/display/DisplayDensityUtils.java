package com.android.settingslib.display;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.util.MathUtils;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.WindowManagerGlobal;
import com.android.settingslib.R$fraction;
import com.android.settingslib.R$string;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
/* loaded from: classes2.dex */
public class DisplayDensityUtils {
    private int mCurrentIndex;
    private int mDefaultDensityForDefaultDisplay;
    private String[] mDefaultDisplayDensityEntries;
    private int[] mDefaultDisplayDensityValues;
    private final DisplayManager mDisplayManager;
    private final Predicate<DisplayInfo> mPredicate;
    private final Map<String, int[]> mValuesPerDisplay;
    public static final int SUMMARY_DEFAULT = R$string.screen_zoom_summary_default;
    private static final int SUMMARY_CUSTOM = R$string.screen_zoom_summary_custom;
    private static final int[] SUMMARIES_SMALLER = {R$string.screen_zoom_summary_small, R$string.screen_zoom_summary_smaller, R$string.screen_zoom_summary_smallest};
    private static final int[] SUMMARIES_LARGER = {R$string.screen_zoom_summary_large, R$string.screen_zoom_summary_very_large, R$string.screen_zoom_summary_extremely_large};
    private static final Predicate<DisplayInfo> INTERNAL_ONLY = new Predicate() { // from class: com.android.settingslib.display.DisplayDensityUtils$$ExternalSyntheticLambda0
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            boolean lambda$static$0;
            lambda$static$0 = DisplayDensityUtils.lambda$static$0((DisplayInfo) obj);
            return lambda$static$0;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$0(DisplayInfo displayInfo) {
        return displayInfo.type == 1;
    }

    public DisplayDensityUtils(Context context) {
        this(context, INTERNAL_ONLY);
    }

    public DisplayDensityUtils(Context context, Predicate predicate) {
        Display[] displayArr;
        int i;
        int i2;
        int i3;
        int i4;
        this.mValuesPerDisplay = new HashMap();
        this.mCurrentIndex = -1;
        this.mPredicate = predicate;
        DisplayManager displayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        this.mDisplayManager = displayManager;
        Display[] displays = displayManager.getDisplays("android.hardware.display.category.ALL_INCLUDING_DISABLED");
        int length = displays.length;
        int i5 = 0;
        while (i5 < length) {
            Display display = displays[i5];
            DisplayInfo displayInfo = new DisplayInfo();
            if (!display.getDisplayInfo(displayInfo)) {
                Log.w("DisplayDensityUtils", "Cannot fetch display info for display " + display.getDisplayId());
            } else if (!this.mPredicate.test(displayInfo)) {
                if (display.getDisplayId() == 0) {
                    throw new IllegalArgumentException("Predicate must not filter out the default display.");
                }
            } else {
                int defaultDensityForDisplay = getDefaultDensityForDisplay(display.getDisplayId());
                if (defaultDensityForDisplay <= 0) {
                    Log.w("DisplayDensityUtils", "Cannot fetch default density for display " + display.getDisplayId());
                } else {
                    Resources resources = context.getResources();
                    int i6 = displayInfo.logicalDensityDpi;
                    float f = defaultDensityForDisplay;
                    float min = Math.min(context.getResources().getFraction(R$fraction.display_density_max_scale, 1, 1), ((Math.min(displayInfo.logicalWidth, displayInfo.logicalHeight) * 160) / 320) / f);
                    float fraction = context.getResources().getFraction(R$fraction.display_density_min_scale, 1, 1);
                    float fraction2 = context.getResources().getFraction(R$fraction.display_density_min_scale_interval, 1, 1);
                    float f2 = min - 1.0f;
                    displayArr = displays;
                    int constrain = (int) MathUtils.constrain(f2 / fraction2, 0.0f, SUMMARIES_LARGER.length);
                    float f3 = 1.0f - fraction;
                    int constrain2 = (int) MathUtils.constrain(f3 / fraction2, 0.0f, SUMMARIES_SMALLER.length);
                    int i7 = constrain2 + 1 + constrain;
                    String[] strArr = new String[i7];
                    int[] iArr = new int[i7];
                    i = length;
                    if (constrain2 > 0) {
                        float f4 = f3 / constrain2;
                        int i8 = constrain2 - 1;
                        i3 = 0;
                        i4 = -1;
                        while (i8 >= 0) {
                            int i9 = i5;
                            int i10 = ((int) ((1.0f - ((i8 + 1) * f4)) * f)) & (-2);
                            if (i6 == i10) {
                                i4 = i3;
                            }
                            strArr[i3] = resources.getString(SUMMARIES_SMALLER[i8]);
                            iArr[i3] = i10;
                            i3++;
                            i8--;
                            i5 = i9;
                            f4 = f4;
                        }
                        i2 = i5;
                    } else {
                        i2 = i5;
                        i3 = 0;
                        i4 = -1;
                    }
                    i4 = i6 == defaultDensityForDisplay ? i3 : i4;
                    iArr[i3] = defaultDensityForDisplay;
                    strArr[i3] = resources.getString(SUMMARY_DEFAULT);
                    int i11 = i3 + 1;
                    if (constrain > 0) {
                        float f5 = f2 / constrain;
                        int i12 = 0;
                        while (i12 < constrain) {
                            int i13 = i12 + 1;
                            int i14 = ((int) (((i13 * f5) + 1.0f) * f)) & (-2);
                            if (i6 == i14) {
                                i4 = i11;
                            }
                            iArr[i11] = i14;
                            strArr[i11] = resources.getString(SUMMARIES_LARGER[i12]);
                            i11++;
                            i12 = i13;
                        }
                    }
                    if (i4 >= 0) {
                        i11 = i4;
                    } else {
                        int i15 = i7 + 1;
                        iArr = Arrays.copyOf(iArr, i15);
                        iArr[i11] = i6;
                        strArr = (String[]) Arrays.copyOf(strArr, i15);
                        strArr[i11] = resources.getString(SUMMARY_CUSTOM, Integer.valueOf(i6));
                    }
                    if (display.getDisplayId() == 0) {
                        this.mDefaultDensityForDefaultDisplay = defaultDensityForDisplay;
                        this.mCurrentIndex = i11;
                        this.mDefaultDisplayDensityEntries = strArr;
                        this.mDefaultDisplayDensityValues = iArr;
                    }
                    this.mValuesPerDisplay.put(displayInfo.uniqueId, iArr);
                    i5 = i2 + 1;
                    displays = displayArr;
                    length = i;
                }
            }
            displayArr = displays;
            i = length;
            i2 = i5;
            i5 = i2 + 1;
            displays = displayArr;
            length = i;
        }
    }

    public String[] getDefaultDisplayDensityEntries() {
        return this.mDefaultDisplayDensityEntries;
    }

    public int[] getDefaultDisplayDensityValues() {
        return this.mDefaultDisplayDensityValues;
    }

    public int getCurrentIndexForDefaultDisplay() {
        return this.mCurrentIndex;
    }

    public int getDefaultDensityForDefaultDisplay() {
        return this.mDefaultDensityForDefaultDisplay;
    }

    private static int getDefaultDensityForDisplay(int i) {
        try {
            return WindowManagerGlobal.getWindowManagerService().getInitialDisplayDensity(i);
        } catch (RemoteException unused) {
            return -1;
        }
    }

    public void setForcedDisplayDensity(final int i) {
        final int myUserId = UserHandle.myUserId();
        AsyncTask.execute(new Runnable() { // from class: com.android.settingslib.display.DisplayDensityUtils$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DisplayDensityUtils.this.lambda$setForcedDisplayDensity$2(i, myUserId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setForcedDisplayDensity$2(int i, int i2) {
        Display[] displays;
        try {
            for (Display display : this.mDisplayManager.getDisplays("android.hardware.display.category.ALL_INCLUDING_DISABLED")) {
                int displayId = display.getDisplayId();
                DisplayInfo displayInfo = new DisplayInfo();
                if (!display.getDisplayInfo(displayInfo)) {
                    Log.w("DisplayDensityUtils", "Unable to save forced display density setting for display " + displayId);
                } else if (this.mPredicate.test(displayInfo)) {
                    if (this.mValuesPerDisplay.containsKey(displayInfo.uniqueId)) {
                        WindowManagerGlobal.getWindowManagerService().setForcedDisplayDensityForUser(displayId, this.mValuesPerDisplay.get(displayInfo.uniqueId)[i], i2);
                    } else {
                        Log.w("DisplayDensityUtils", "Unable to save forced display density setting for display " + displayInfo.uniqueId);
                    }
                }
            }
        } catch (RemoteException unused) {
            Log.w("DisplayDensityUtils", "Unable to save forced display density setting");
        }
    }
}
