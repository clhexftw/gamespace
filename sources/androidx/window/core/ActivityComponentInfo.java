package androidx.window.core;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: ActivityComponentInfo.kt */
/* loaded from: classes.dex */
public final class ActivityComponentInfo {
    private final String className;
    private final String packageName;

    public ActivityComponentInfo(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        this.packageName = packageName;
        this.className = className;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getClassName() {
        return this.className;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ActivityComponentInfo(android.content.ComponentName r3) {
        /*
            r2 = this;
            java.lang.String r0 = "componentName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = r3.getPackageName()
            java.lang.String r1 = "componentName.packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r3 = r3.getClassName()
            java.lang.String r1 = "componentName.className"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            r2.<init>(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.core.ActivityComponentInfo.<init>(android.content.ComponentName):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(ActivityComponentInfo.class, obj != null ? obj.getClass() : null)) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.window.core.ActivityComponentInfo");
            ActivityComponentInfo activityComponentInfo = (ActivityComponentInfo) obj;
            return Intrinsics.areEqual(this.packageName, activityComponentInfo.packageName) && Intrinsics.areEqual(this.className, activityComponentInfo.className);
        }
        return false;
    }

    public int hashCode() {
        return (this.packageName.hashCode() * 31) + this.className.hashCode();
    }

    public String toString() {
        return "ClassInfo { packageName: " + this.packageName + ", className: " + this.className + " }";
    }
}
