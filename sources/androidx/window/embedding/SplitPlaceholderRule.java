package androidx.window.embedding;

import android.content.Intent;
import androidx.core.util.Preconditions;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitRule;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
/* compiled from: SplitPlaceholderRule.kt */
/* loaded from: classes.dex */
public final class SplitPlaceholderRule extends SplitRule {
    private final Set<ActivityFilter> filters;
    private final SplitRule.FinishBehavior finishPrimaryWithPlaceholder;
    private final boolean isSticky;
    private final Intent placeholderIntent;

    public final Set<ActivityFilter> getFilters() {
        return this.filters;
    }

    public final Intent getPlaceholderIntent() {
        return this.placeholderIntent;
    }

    public final boolean isSticky() {
        return this.isSticky;
    }

    public final SplitRule.FinishBehavior getFinishPrimaryWithPlaceholder() {
        return this.finishPrimaryWithPlaceholder;
    }

    public /* synthetic */ SplitPlaceholderRule(String str, Set set, Intent intent, boolean z, SplitRule.FinishBehavior finishBehavior, int i, int i2, int i3, EmbeddingAspectRatio embeddingAspectRatio, EmbeddingAspectRatio embeddingAspectRatio2, SplitAttributes splitAttributes, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, set, intent, z, (i4 & 16) != 0 ? SplitRule.FinishBehavior.ALWAYS : finishBehavior, (i4 & 32) != 0 ? 600 : i, (i4 & 64) != 0 ? 600 : i2, (i4 & 128) != 0 ? 600 : i3, (i4 & 256) != 0 ? SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT : embeddingAspectRatio, (i4 & 512) != 0 ? SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT : embeddingAspectRatio2, splitAttributes);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplitPlaceholderRule(String str, Set<ActivityFilter> filters, Intent placeholderIntent, boolean z, SplitRule.FinishBehavior finishPrimaryWithPlaceholder, int i, int i2, int i3, EmbeddingAspectRatio maxAspectRatioInPortrait, EmbeddingAspectRatio maxAspectRatioInLandscape, SplitAttributes defaultSplitAttributes) {
        super(str, i, i2, i3, maxAspectRatioInPortrait, maxAspectRatioInLandscape, defaultSplitAttributes);
        Set<ActivityFilter> set;
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(placeholderIntent, "placeholderIntent");
        Intrinsics.checkNotNullParameter(finishPrimaryWithPlaceholder, "finishPrimaryWithPlaceholder");
        Intrinsics.checkNotNullParameter(maxAspectRatioInPortrait, "maxAspectRatioInPortrait");
        Intrinsics.checkNotNullParameter(maxAspectRatioInLandscape, "maxAspectRatioInLandscape");
        Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
        Preconditions.checkArgument(!Intrinsics.areEqual(finishPrimaryWithPlaceholder, SplitRule.FinishBehavior.NEVER), "NEVER is not a valid configuration for SplitPlaceholderRule. Please use FINISH_ALWAYS or FINISH_ADJACENT instead or refer to the current API.", new Object[0]);
        set = CollectionsKt___CollectionsKt.toSet(filters);
        this.filters = set;
        this.placeholderIntent = placeholderIntent;
        this.isSticky = z;
        this.finishPrimaryWithPlaceholder = finishPrimaryWithPlaceholder;
    }

    /* compiled from: SplitPlaceholderRule.kt */
    @SourceDebugExtension({"SMAP\nSplitPlaceholderRule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SplitPlaceholderRule.kt\nandroidx/window/embedding/SplitPlaceholderRule$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,342:1\n1#2:343\n*E\n"})
    /* loaded from: classes.dex */
    public static final class Builder {
        private SplitAttributes defaultSplitAttributes;
        private final Set<ActivityFilter> filters;
        private SplitRule.FinishBehavior finishPrimaryWithPlaceholder;
        private boolean isSticky;
        private EmbeddingAspectRatio maxAspectRatioInLandscape;
        private EmbeddingAspectRatio maxAspectRatioInPortrait;
        private int minHeightDp;
        private int minSmallestWidthDp;
        private int minWidthDp;
        private final Intent placeholderIntent;
        private String tag;

        public Builder(Set<ActivityFilter> filters, Intent placeholderIntent) {
            Intrinsics.checkNotNullParameter(filters, "filters");
            Intrinsics.checkNotNullParameter(placeholderIntent, "placeholderIntent");
            this.filters = filters;
            this.placeholderIntent = placeholderIntent;
            this.minWidthDp = SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT;
            this.minHeightDp = SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT;
            this.minSmallestWidthDp = SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT;
            this.maxAspectRatioInPortrait = SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT;
            this.maxAspectRatioInLandscape = SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT;
            this.finishPrimaryWithPlaceholder = SplitRule.FinishBehavior.ALWAYS;
            this.defaultSplitAttributes = new SplitAttributes.Builder().build();
        }

        public final Builder setMinWidthDp(int i) {
            this.minWidthDp = i;
            return this;
        }

        public final Builder setMinHeightDp(int i) {
            this.minHeightDp = i;
            return this;
        }

        public final Builder setMinSmallestWidthDp(int i) {
            this.minSmallestWidthDp = i;
            return this;
        }

        public final Builder setMaxAspectRatioInPortrait(EmbeddingAspectRatio aspectRatio) {
            Intrinsics.checkNotNullParameter(aspectRatio, "aspectRatio");
            this.maxAspectRatioInPortrait = aspectRatio;
            return this;
        }

        public final Builder setMaxAspectRatioInLandscape(EmbeddingAspectRatio aspectRatio) {
            Intrinsics.checkNotNullParameter(aspectRatio, "aspectRatio");
            this.maxAspectRatioInLandscape = aspectRatio;
            return this;
        }

        public final Builder setFinishPrimaryWithPlaceholder(SplitRule.FinishBehavior finishPrimaryWithPlaceholder) {
            Intrinsics.checkNotNullParameter(finishPrimaryWithPlaceholder, "finishPrimaryWithPlaceholder");
            this.finishPrimaryWithPlaceholder = finishPrimaryWithPlaceholder;
            return this;
        }

        public final Builder setSticky(boolean z) {
            this.isSticky = z;
            return this;
        }

        public final Builder setDefaultSplitAttributes(SplitAttributes defaultSplitAttributes) {
            Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
            this.defaultSplitAttributes = defaultSplitAttributes;
            return this;
        }

        public final Builder setTag(String str) {
            this.tag = str;
            return this;
        }

        public final SplitPlaceholderRule build() {
            return new SplitPlaceholderRule(this.tag, this.filters, this.placeholderIntent, this.isSticky, this.finishPrimaryWithPlaceholder, this.minWidthDp, this.minHeightDp, this.minSmallestWidthDp, this.maxAspectRatioInPortrait, this.maxAspectRatioInLandscape, this.defaultSplitAttributes);
        }
    }

    public final SplitPlaceholderRule plus$window_release(ActivityFilter filter) {
        Set set;
        Intrinsics.checkNotNullParameter(filter, "filter");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.filters);
        linkedHashSet.add(filter);
        set = CollectionsKt___CollectionsKt.toSet(linkedHashSet);
        return new Builder(set, this.placeholderIntent).setTag(getTag()).setMinWidthDp(getMinWidthDp()).setMinHeightDp(getMinHeightDp()).setMinSmallestWidthDp(getMinSmallestWidthDp()).setMaxAspectRatioInPortrait(getMaxAspectRatioInPortrait()).setMaxAspectRatioInLandscape(getMaxAspectRatioInLandscape()).setSticky(this.isSticky).setFinishPrimaryWithPlaceholder(this.finishPrimaryWithPlaceholder).setDefaultSplitAttributes(getDefaultSplitAttributes()).build();
    }

    @Override // androidx.window.embedding.SplitRule, androidx.window.embedding.EmbeddingRule
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof SplitPlaceholderRule) && super.equals(obj)) {
            SplitPlaceholderRule splitPlaceholderRule = (SplitPlaceholderRule) obj;
            return Intrinsics.areEqual(this.placeholderIntent, splitPlaceholderRule.placeholderIntent) && this.isSticky == splitPlaceholderRule.isSticky && Intrinsics.areEqual(this.finishPrimaryWithPlaceholder, splitPlaceholderRule.finishPrimaryWithPlaceholder) && Intrinsics.areEqual(this.filters, splitPlaceholderRule.filters);
        }
        return false;
    }

    @Override // androidx.window.embedding.SplitRule, androidx.window.embedding.EmbeddingRule
    public int hashCode() {
        return (((((((super.hashCode() * 31) + this.placeholderIntent.hashCode()) * 31) + Boolean.hashCode(this.isSticky)) * 31) + this.finishPrimaryWithPlaceholder.hashCode()) * 31) + this.filters.hashCode();
    }

    @Override // androidx.window.embedding.SplitRule
    public String toString() {
        return "SplitPlaceholderRule{tag=" + getTag() + ", defaultSplitAttributes=" + getDefaultSplitAttributes() + ", minWidthDp=" + getMinWidthDp() + ", minHeightDp=" + getMinHeightDp() + ", minSmallestWidthDp=" + getMinSmallestWidthDp() + ", maxAspectRatioInPortrait=" + getMaxAspectRatioInPortrait() + ", maxAspectRatioInLandscape=" + getMaxAspectRatioInLandscape() + ", placeholderIntent=" + this.placeholderIntent + ", isSticky=" + this.isSticky + ", finishPrimaryWithPlaceholder=" + this.finishPrimaryWithPlaceholder + ", filters=" + this.filters + '}';
    }
}
