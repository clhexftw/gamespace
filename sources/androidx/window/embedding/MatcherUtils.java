package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import androidx.window.core.ActivityComponentInfo;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
/* compiled from: MatcherUtils.kt */
@SourceDebugExtension({"SMAP\nMatcherUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MatcherUtils.kt\nandroidx/window/embedding/MatcherUtils\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,136:1\n1#2:137\n*E\n"})
/* loaded from: classes.dex */
public final class MatcherUtils {
    public static final MatcherUtils INSTANCE = new MatcherUtils();
    public static final boolean sDebugMatchers = false;
    public static final String sMatchersTag = "SplitRuleResolution";

    private MatcherUtils() {
    }

    public final boolean areComponentsMatching$window_release(ActivityComponentInfo activityComponentInfo, ActivityComponentInfo ruleComponent) {
        boolean contains$default;
        Intrinsics.checkNotNullParameter(ruleComponent, "ruleComponent");
        if (activityComponentInfo == null) {
            return Intrinsics.areEqual(ruleComponent.getPackageName(), "*") && Intrinsics.areEqual(ruleComponent.getClassName(), "*");
        }
        contains$default = StringsKt__StringsKt.contains$default(activityComponentInfo.toString(), "*", false, 2, null);
        if (!contains$default) {
            return (Intrinsics.areEqual(activityComponentInfo.getPackageName(), ruleComponent.getPackageName()) || wildcardMatch(activityComponentInfo.getPackageName(), ruleComponent.getPackageName())) && (Intrinsics.areEqual(activityComponentInfo.getClassName(), ruleComponent.getClassName()) || wildcardMatch(activityComponentInfo.getClassName(), ruleComponent.getClassName()));
        }
        throw new IllegalArgumentException("Wildcard can only be part of the rule.".toString());
    }

    public final boolean isActivityMatching$window_release(Activity activity, ActivityComponentInfo ruleComponent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(ruleComponent, "ruleComponent");
        ComponentName componentName = activity.getComponentName();
        Intrinsics.checkNotNullExpressionValue(componentName, "activity.componentName");
        if (areComponentsMatching$window_release(new ActivityComponentInfo(componentName), ruleComponent)) {
            return true;
        }
        Intent intent = activity.getIntent();
        if (intent != null) {
            return INSTANCE.isIntentMatching$window_release(intent, ruleComponent);
        }
        return false;
    }

    public final boolean isIntentMatching$window_release(Intent intent, ActivityComponentInfo ruleComponent) {
        String str;
        Intrinsics.checkNotNullParameter(intent, "intent");
        Intrinsics.checkNotNullParameter(ruleComponent, "ruleComponent");
        ComponentName component = intent.getComponent();
        if (areComponentsMatching$window_release(component != null ? new ActivityComponentInfo(component) : null, ruleComponent)) {
            return true;
        }
        if (intent.getComponent() == null && (str = intent.getPackage()) != null) {
            return (Intrinsics.areEqual(str, ruleComponent.getPackageName()) || wildcardMatch(str, ruleComponent.getPackageName())) && Intrinsics.areEqual(ruleComponent.getClassName(), "*");
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean wildcardMatch(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            java.lang.String r11 = "*"
            r0 = 0
            r1 = 2
            r2 = 0
            boolean r3 = kotlin.text.StringsKt.contains$default(r13, r11, r0, r1, r2)
            if (r3 != 0) goto Lc
            return r0
        Lc:
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r13, r11)
            r4 = 1
            if (r3 == 0) goto L14
            return r4
        L14:
            r7 = 0
            r8 = 0
            r9 = 6
            r10 = 0
            java.lang.String r6 = "*"
            r5 = r13
            int r3 = kotlin.text.StringsKt.indexOf$default(r5, r6, r7, r8, r9, r10)
            java.lang.String r6 = "*"
            int r5 = kotlin.text.StringsKt.lastIndexOf$default(r5, r6, r7, r8, r9, r10)
            if (r3 != r5) goto L2f
            boolean r11 = kotlin.text.StringsKt.endsWith$default(r13, r11, r0, r1, r2)
            if (r11 == 0) goto L2f
            r11 = r4
            goto L30
        L2f:
            r11 = r0
        L30:
            if (r11 == 0) goto L46
            int r11 = r13.length()
            int r11 = r11 - r4
            java.lang.String r11 = r13.substring(r0, r11)
            java.lang.String r13 = "this as java.lang.String…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r13)
            boolean r11 = kotlin.text.StringsKt.startsWith$default(r12, r11, r0, r1, r2)
            return r11
        L46:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "Name pattern with a wildcard must only contain a single wildcard in the end"
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.MatcherUtils.wildcardMatch(java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void validateComponentName$window_release(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            java.lang.String r11 = "packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r11)
            java.lang.String r11 = "className"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r11)
            int r11 = r12.length()
            r0 = 1
            r1 = 0
            if (r11 <= 0) goto L14
            r11 = r0
            goto L15
        L14:
            r11 = r1
        L15:
            if (r11 == 0) goto L85
            int r11 = r13.length()
            if (r11 <= 0) goto L1f
            r11 = r0
            goto L20
        L1f:
            r11 = r1
        L20:
            if (r11 == 0) goto L79
            java.lang.String r11 = "*"
            r2 = 2
            r3 = 0
            boolean r4 = kotlin.text.StringsKt.contains$default(r12, r11, r1, r2, r3)
            if (r4 == 0) goto L41
            r7 = 0
            r8 = 0
            r9 = 6
            r10 = 0
            java.lang.String r6 = "*"
            r5 = r12
            int r4 = kotlin.text.StringsKt.indexOf$default(r5, r6, r7, r8, r9, r10)
            int r12 = r12.length()
            int r12 = r12 - r0
            if (r4 != r12) goto L3f
            goto L41
        L3f:
            r12 = r1
            goto L42
        L41:
            r12 = r0
        L42:
            if (r12 == 0) goto L6d
            boolean r11 = kotlin.text.StringsKt.contains$default(r13, r11, r1, r2, r3)
            if (r11 == 0) goto L5e
            r4 = 0
            r5 = 0
            r6 = 6
            r7 = 0
            java.lang.String r3 = "*"
            r2 = r13
            int r11 = kotlin.text.StringsKt.indexOf$default(r2, r3, r4, r5, r6, r7)
            int r12 = r13.length()
            int r12 = r12 - r0
            if (r11 != r12) goto L5d
            goto L5e
        L5d:
            r0 = r1
        L5e:
            if (r0 == 0) goto L61
            return
        L61:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "Wildcard in class name is only allowed at the end."
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        L6d:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "Wildcard in package name is only allowed at the end."
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        L79:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "Activity class name must not be empty"
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        L85:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "Package name must not be empty"
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.MatcherUtils.validateComponentName$window_release(java.lang.String, java.lang.String):void");
    }
}
