package androidx.window.embedding;

import java.util.Set;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
/* compiled from: ActivityRule.kt */
/* loaded from: classes.dex */
public final class ActivityRule extends EmbeddingRule {
    private final boolean alwaysExpand;
    private final Set<ActivityFilter> filters;

    public /* synthetic */ ActivityRule(String str, Set set, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, set, (i & 4) != 0 ? false : z);
    }

    public final Set<ActivityFilter> getFilters() {
        return this.filters;
    }

    public final boolean getAlwaysExpand() {
        return this.alwaysExpand;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityRule(String str, Set<ActivityFilter> filters, boolean z) {
        super(str);
        Intrinsics.checkNotNullParameter(filters, "filters");
        this.filters = filters;
        this.alwaysExpand = z;
    }

    /* compiled from: ActivityRule.kt */
    @SourceDebugExtension({"SMAP\nActivityRule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ActivityRule.kt\nandroidx/window/embedding/ActivityRule$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,109:1\n1#2:110\n*E\n"})
    /* loaded from: classes.dex */
    public static final class Builder {
        private boolean alwaysExpand;
        private final Set<ActivityFilter> filters;
        private String tag;

        public Builder(Set<ActivityFilter> filters) {
            Intrinsics.checkNotNullParameter(filters, "filters");
            this.filters = filters;
        }

        public final Builder setAlwaysExpand(boolean z) {
            this.alwaysExpand = z;
            return this;
        }

        public final Builder setTag(String str) {
            this.tag = str;
            return this;
        }

        public final ActivityRule build() {
            return new ActivityRule(this.tag, this.filters, this.alwaysExpand);
        }
    }

    public final ActivityRule plus$window_release(ActivityFilter filter) {
        Set plus;
        Intrinsics.checkNotNullParameter(filter, "filter");
        String tag = getTag();
        plus = SetsKt___SetsKt.plus(this.filters, filter);
        return new ActivityRule(tag, plus, this.alwaysExpand);
    }

    @Override // androidx.window.embedding.EmbeddingRule
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ActivityRule) && super.equals(obj)) {
            ActivityRule activityRule = (ActivityRule) obj;
            return Intrinsics.areEqual(this.filters, activityRule.filters) && this.alwaysExpand == activityRule.alwaysExpand;
        }
        return false;
    }

    @Override // androidx.window.embedding.EmbeddingRule
    public int hashCode() {
        return (((super.hashCode() * 31) + this.filters.hashCode()) * 31) + Boolean.hashCode(this.alwaysExpand);
    }

    public String toString() {
        return "ActivityRule:{tag={" + getTag() + "},filters={" + this.filters + "}, alwaysExpand={" + this.alwaysExpand + "}}";
    }
}
