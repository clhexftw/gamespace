package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import androidx.window.core.ActivityComponentInfo;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SplitPairFilter.kt */
/* loaded from: classes.dex */
public final class SplitPairFilter {
    private final ComponentName primaryActivityName;
    private final String secondaryActivityIntentAction;
    private final ComponentName secondaryActivityName;

    public SplitPairFilter(ComponentName primaryActivityName, ComponentName secondaryActivityName, String str) {
        Intrinsics.checkNotNullParameter(primaryActivityName, "primaryActivityName");
        Intrinsics.checkNotNullParameter(secondaryActivityName, "secondaryActivityName");
        this.primaryActivityName = primaryActivityName;
        this.secondaryActivityName = secondaryActivityName;
        this.secondaryActivityIntentAction = str;
        MatcherUtils matcherUtils = MatcherUtils.INSTANCE;
        String packageName = primaryActivityName.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "primaryActivityName.packageName");
        String className = primaryActivityName.getClassName();
        Intrinsics.checkNotNullExpressionValue(className, "primaryActivityName.className");
        matcherUtils.validateComponentName$window_release(packageName, className);
        String packageName2 = secondaryActivityName.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName2, "secondaryActivityName.packageName");
        String className2 = secondaryActivityName.getClassName();
        Intrinsics.checkNotNullExpressionValue(className2, "secondaryActivityName.className");
        matcherUtils.validateComponentName$window_release(packageName2, className2);
    }

    public final ComponentName getPrimaryActivityName() {
        return this.primaryActivityName;
    }

    public final ComponentName getSecondaryActivityName() {
        return this.secondaryActivityName;
    }

    public final String getSecondaryActivityIntentAction() {
        return this.secondaryActivityIntentAction;
    }

    private final ActivityComponentInfo getPrimaryActivityInfo() {
        return new ActivityComponentInfo(this.primaryActivityName);
    }

    private final ActivityComponentInfo getSecondaryActivityInfo() {
        return new ActivityComponentInfo(this.secondaryActivityName);
    }

    public final boolean matchesActivityPair(Activity primaryActivity, Activity secondaryActivity) {
        Intrinsics.checkNotNullParameter(primaryActivity, "primaryActivity");
        Intrinsics.checkNotNullParameter(secondaryActivity, "secondaryActivity");
        MatcherUtils matcherUtils = MatcherUtils.INSTANCE;
        if (matcherUtils.isActivityMatching$window_release(primaryActivity, getPrimaryActivityInfo()) && matcherUtils.isActivityMatching$window_release(secondaryActivity, getSecondaryActivityInfo())) {
            String str = this.secondaryActivityIntentAction;
            if (str != null) {
                Intent intent = secondaryActivity.getIntent();
                if (!Intrinsics.areEqual(str, intent != null ? intent.getAction() : null)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final boolean matchesActivityIntentPair(Activity primaryActivity, Intent secondaryActivityIntent) {
        Intrinsics.checkNotNullParameter(primaryActivity, "primaryActivity");
        Intrinsics.checkNotNullParameter(secondaryActivityIntent, "secondaryActivityIntent");
        MatcherUtils matcherUtils = MatcherUtils.INSTANCE;
        if (matcherUtils.isActivityMatching$window_release(primaryActivity, getPrimaryActivityInfo()) && matcherUtils.isIntentMatching$window_release(secondaryActivityIntent, getSecondaryActivityInfo())) {
            String str = this.secondaryActivityIntentAction;
            return str == null || Intrinsics.areEqual(str, secondaryActivityIntent.getAction());
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SplitPairFilter) {
            SplitPairFilter splitPairFilter = (SplitPairFilter) obj;
            return Intrinsics.areEqual(this.primaryActivityName, splitPairFilter.primaryActivityName) && Intrinsics.areEqual(this.secondaryActivityName, splitPairFilter.secondaryActivityName) && Intrinsics.areEqual(this.secondaryActivityIntentAction, splitPairFilter.secondaryActivityIntentAction);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.primaryActivityName.hashCode() * 31) + this.secondaryActivityName.hashCode()) * 31;
        String str = this.secondaryActivityIntentAction;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "SplitPairFilter{primaryActivityName=" + this.primaryActivityName + ", secondaryActivityName=" + this.secondaryActivityName + ", secondaryActivityAction=" + this.secondaryActivityIntentAction + '}';
    }
}
