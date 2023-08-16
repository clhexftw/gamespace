package androidx.window.embedding;

import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitRule;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
/* compiled from: SplitPairRule.kt */
/* loaded from: classes.dex */
public final class SplitPairRule extends SplitRule {
    private final boolean clearTop;
    private final Set<SplitPairFilter> filters;
    private final SplitRule.FinishBehavior finishPrimaryWithSecondary;
    private final SplitRule.FinishBehavior finishSecondaryWithPrimary;

    public final Set<SplitPairFilter> getFilters() {
        return this.filters;
    }

    public final SplitRule.FinishBehavior getFinishPrimaryWithSecondary() {
        return this.finishPrimaryWithSecondary;
    }

    public final SplitRule.FinishBehavior getFinishSecondaryWithPrimary() {
        return this.finishSecondaryWithPrimary;
    }

    public final boolean getClearTop() {
        return this.clearTop;
    }

    public /* synthetic */ SplitPairRule(String str, Set set, SplitRule.FinishBehavior finishBehavior, SplitRule.FinishBehavior finishBehavior2, boolean z, int i, int i2, int i3, EmbeddingAspectRatio embeddingAspectRatio, EmbeddingAspectRatio embeddingAspectRatio2, SplitAttributes splitAttributes, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, set, (i4 & 4) != 0 ? SplitRule.FinishBehavior.NEVER : finishBehavior, (i4 & 8) != 0 ? SplitRule.FinishBehavior.ALWAYS : finishBehavior2, (i4 & 16) != 0 ? false : z, (i4 & 32) != 0 ? 600 : i, (i4 & 64) != 0 ? 600 : i2, (i4 & 128) != 0 ? 600 : i3, (i4 & 256) != 0 ? SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT : embeddingAspectRatio, (i4 & 512) != 0 ? SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT : embeddingAspectRatio2, splitAttributes);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplitPairRule(String str, Set<SplitPairFilter> filters, SplitRule.FinishBehavior finishPrimaryWithSecondary, SplitRule.FinishBehavior finishSecondaryWithPrimary, boolean z, int i, int i2, int i3, EmbeddingAspectRatio maxAspectRatioInPortrait, EmbeddingAspectRatio maxAspectRatioInLandscape, SplitAttributes defaultSplitAttributes) {
        super(str, i, i2, i3, maxAspectRatioInPortrait, maxAspectRatioInLandscape, defaultSplitAttributes);
        Set<SplitPairFilter> set;
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(finishPrimaryWithSecondary, "finishPrimaryWithSecondary");
        Intrinsics.checkNotNullParameter(finishSecondaryWithPrimary, "finishSecondaryWithPrimary");
        Intrinsics.checkNotNullParameter(maxAspectRatioInPortrait, "maxAspectRatioInPortrait");
        Intrinsics.checkNotNullParameter(maxAspectRatioInLandscape, "maxAspectRatioInLandscape");
        Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
        set = CollectionsKt___CollectionsKt.toSet(filters);
        this.filters = set;
        this.clearTop = z;
        this.finishPrimaryWithSecondary = finishPrimaryWithSecondary;
        this.finishSecondaryWithPrimary = finishSecondaryWithPrimary;
    }

    /* compiled from: SplitPairRule.kt */
    @SourceDebugExtension({"SMAP\nSplitPairRule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SplitPairRule.kt\nandroidx/window/embedding/SplitPairRule$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,350:1\n1#2:351\n*E\n"})
    /* loaded from: classes.dex */
    public static final class Builder {
        private boolean clearTop;
        private SplitAttributes defaultSplitAttributes;
        private final Set<SplitPairFilter> filters;
        private SplitRule.FinishBehavior finishPrimaryWithSecondary;
        private SplitRule.FinishBehavior finishSecondaryWithPrimary;
        private EmbeddingAspectRatio maxAspectRatioInLandscape;
        private EmbeddingAspectRatio maxAspectRatioInPortrait;
        private int minHeightDp;
        private int minSmallestWidthDp;
        private int minWidthDp;
        private String tag;

        public Builder(Set<SplitPairFilter> filters) {
            Intrinsics.checkNotNullParameter(filters, "filters");
            this.filters = filters;
            this.minWidthDp = SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT;
            this.minHeightDp = SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT;
            this.minSmallestWidthDp = SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT;
            this.maxAspectRatioInPortrait = SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT;
            this.maxAspectRatioInLandscape = SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT;
            this.finishPrimaryWithSecondary = SplitRule.FinishBehavior.NEVER;
            this.finishSecondaryWithPrimary = SplitRule.FinishBehavior.ALWAYS;
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

        public final Builder setFinishPrimaryWithSecondary(SplitRule.FinishBehavior finishPrimaryWithSecondary) {
            Intrinsics.checkNotNullParameter(finishPrimaryWithSecondary, "finishPrimaryWithSecondary");
            this.finishPrimaryWithSecondary = finishPrimaryWithSecondary;
            return this;
        }

        public final Builder setFinishSecondaryWithPrimary(SplitRule.FinishBehavior finishSecondaryWithPrimary) {
            Intrinsics.checkNotNullParameter(finishSecondaryWithPrimary, "finishSecondaryWithPrimary");
            this.finishSecondaryWithPrimary = finishSecondaryWithPrimary;
            return this;
        }

        public final Builder setClearTop(boolean z) {
            this.clearTop = z;
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

        public final SplitPairRule build() {
            return new SplitPairRule(this.tag, this.filters, this.finishPrimaryWithSecondary, this.finishSecondaryWithPrimary, this.clearTop, this.minWidthDp, this.minHeightDp, this.minSmallestWidthDp, this.maxAspectRatioInPortrait, this.maxAspectRatioInLandscape, this.defaultSplitAttributes);
        }
    }

    public final SplitPairRule plus$window_release(SplitPairFilter filter) {
        Set set;
        Intrinsics.checkNotNullParameter(filter, "filter");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.filters);
        linkedHashSet.add(filter);
        set = CollectionsKt___CollectionsKt.toSet(linkedHashSet);
        return new Builder(set).setTag(getTag()).setMinWidthDp(getMinWidthDp()).setMinHeightDp(getMinHeightDp()).setMinSmallestWidthDp(getMinSmallestWidthDp()).setMaxAspectRatioInPortrait(getMaxAspectRatioInPortrait()).setMaxAspectRatioInLandscape(getMaxAspectRatioInLandscape()).setFinishPrimaryWithSecondary(this.finishPrimaryWithSecondary).setFinishSecondaryWithPrimary(this.finishSecondaryWithPrimary).setClearTop(this.clearTop).setDefaultSplitAttributes(getDefaultSplitAttributes()).build();
    }

    @Override // androidx.window.embedding.SplitRule, androidx.window.embedding.EmbeddingRule
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof SplitPairRule) && super.equals(obj)) {
            SplitPairRule splitPairRule = (SplitPairRule) obj;
            return Intrinsics.areEqual(this.filters, splitPairRule.filters) && Intrinsics.areEqual(this.finishPrimaryWithSecondary, splitPairRule.finishPrimaryWithSecondary) && Intrinsics.areEqual(this.finishSecondaryWithPrimary, splitPairRule.finishSecondaryWithPrimary) && this.clearTop == splitPairRule.clearTop;
        }
        return false;
    }

    @Override // androidx.window.embedding.SplitRule, androidx.window.embedding.EmbeddingRule
    public int hashCode() {
        return (((((((super.hashCode() * 31) + this.filters.hashCode()) * 31) + this.finishPrimaryWithSecondary.hashCode()) * 31) + this.finishSecondaryWithPrimary.hashCode()) * 31) + Boolean.hashCode(this.clearTop);
    }

    @Override // androidx.window.embedding.SplitRule
    public String toString() {
        return SplitPairRule.class.getSimpleName() + "{tag=" + getTag() + ", defaultSplitAttributes=" + getDefaultSplitAttributes() + ", minWidthDp=" + getMinWidthDp() + ", minHeightDp=" + getMinHeightDp() + ", minSmallestWidthDp=" + getMinSmallestWidthDp() + ", maxAspectRatioInPortrait=" + getMaxAspectRatioInPortrait() + ", maxAspectRatioInLandscape=" + getMaxAspectRatioInLandscape() + ", clearTop=" + this.clearTop + ", finishPrimaryWithSecondary=" + this.finishPrimaryWithSecondary + ", finishSecondaryWithPrimary=" + this.finishSecondaryWithPrimary + ", filters=" + this.filters + '}';
    }
}
