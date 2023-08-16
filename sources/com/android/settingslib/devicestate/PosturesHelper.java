package com.android.settingslib.devicestate;

import android.content.Context;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PosturesHelper.kt */
/* loaded from: classes2.dex */
public final class PosturesHelper {
    private final int[] foldedDeviceStates;
    private final int[] halfFoldedDeviceStates;
    private final int[] unfoldedDeviceStates;

    public PosturesHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.foldedDeviceStates = context.getResources().getIntArray(17236073);
        this.halfFoldedDeviceStates = context.getResources().getIntArray(17236080);
        this.unfoldedDeviceStates = context.getResources().getIntArray(17236115);
    }

    public final int deviceStateToPosture(int i) {
        boolean contains;
        boolean contains2;
        boolean contains3;
        int[] foldedDeviceStates = this.foldedDeviceStates;
        Intrinsics.checkNotNullExpressionValue(foldedDeviceStates, "foldedDeviceStates");
        contains = ArraysKt___ArraysKt.contains(foldedDeviceStates, i);
        if (contains) {
            return 0;
        }
        int[] halfFoldedDeviceStates = this.halfFoldedDeviceStates;
        Intrinsics.checkNotNullExpressionValue(halfFoldedDeviceStates, "halfFoldedDeviceStates");
        contains2 = ArraysKt___ArraysKt.contains(halfFoldedDeviceStates, i);
        if (contains2) {
            return 1;
        }
        int[] unfoldedDeviceStates = this.unfoldedDeviceStates;
        Intrinsics.checkNotNullExpressionValue(unfoldedDeviceStates, "unfoldedDeviceStates");
        contains3 = ArraysKt___ArraysKt.contains(unfoldedDeviceStates, i);
        return contains3 ? 2 : -1;
    }

    public final Integer postureToDeviceState(int i) {
        Integer firstOrNull;
        Integer firstOrNull2;
        Integer firstOrNull3;
        if (i == 0) {
            int[] foldedDeviceStates = this.foldedDeviceStates;
            Intrinsics.checkNotNullExpressionValue(foldedDeviceStates, "foldedDeviceStates");
            firstOrNull = ArraysKt___ArraysKt.firstOrNull(foldedDeviceStates);
            return firstOrNull;
        } else if (i == 1) {
            int[] halfFoldedDeviceStates = this.halfFoldedDeviceStates;
            Intrinsics.checkNotNullExpressionValue(halfFoldedDeviceStates, "halfFoldedDeviceStates");
            firstOrNull2 = ArraysKt___ArraysKt.firstOrNull(halfFoldedDeviceStates);
            return firstOrNull2;
        } else if (i != 2) {
            return null;
        } else {
            int[] unfoldedDeviceStates = this.unfoldedDeviceStates;
            Intrinsics.checkNotNullExpressionValue(unfoldedDeviceStates, "unfoldedDeviceStates");
            firstOrNull3 = ArraysKt___ArraysKt.firstOrNull(unfoldedDeviceStates);
            return firstOrNull3;
        }
    }
}
