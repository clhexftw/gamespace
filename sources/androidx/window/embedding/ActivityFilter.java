package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import androidx.window.core.ActivityComponentInfo;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ActivityFilter.kt */
/* loaded from: classes.dex */
public final class ActivityFilter {
    private final ActivityComponentInfo activityComponentInfo;
    private final String intentAction;

    public ActivityFilter(ActivityComponentInfo activityComponentInfo, String str) {
        Intrinsics.checkNotNullParameter(activityComponentInfo, "activityComponentInfo");
        this.activityComponentInfo = activityComponentInfo;
        this.intentAction = str;
        MatcherUtils.INSTANCE.validateComponentName$window_release(activityComponentInfo.getPackageName(), activityComponentInfo.getClassName());
    }

    public final ActivityComponentInfo getActivityComponentInfo$window_release() {
        return this.activityComponentInfo;
    }

    public final String getIntentAction() {
        return this.intentAction;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActivityFilter(ComponentName componentName, String str) {
        this(new ActivityComponentInfo(componentName), str);
        Intrinsics.checkNotNullParameter(componentName, "componentName");
    }

    public final boolean matchesIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (MatcherUtils.INSTANCE.isIntentMatching$window_release(intent, this.activityComponentInfo)) {
            String str = this.intentAction;
            return str == null || Intrinsics.areEqual(str, intent.getAction());
        }
        return false;
    }

    public final boolean matchesActivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (MatcherUtils.INSTANCE.isActivityMatching$window_release(activity, this.activityComponentInfo)) {
            String str = this.intentAction;
            if (str != null) {
                Intent intent = activity.getIntent();
                if (Intrinsics.areEqual(str, intent != null ? intent.getAction() : null)) {
                }
            }
            return true;
        }
        return false;
    }

    public final ComponentName getComponentName() {
        return new ComponentName(this.activityComponentInfo.getPackageName(), this.activityComponentInfo.getClassName());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActivityFilter) {
            ActivityFilter activityFilter = (ActivityFilter) obj;
            return Intrinsics.areEqual(this.activityComponentInfo, activityFilter.activityComponentInfo) && Intrinsics.areEqual(this.intentAction, activityFilter.intentAction);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.activityComponentInfo.hashCode() * 31;
        String str = this.intentAction;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "ActivityFilter(componentName=" + this.activityComponentInfo + ", intentAction=" + this.intentAction + ')';
    }
}
