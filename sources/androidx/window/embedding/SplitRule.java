package androidx.window.embedding;

import android.content.Context;
import android.graphics.Rect;
import android.view.WindowMetrics;
import androidx.core.util.Preconditions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SplitRule.kt */
/* loaded from: classes.dex */
public class SplitRule extends EmbeddingRule {
    public static final int SPLIT_MIN_DIMENSION_ALWAYS_ALLOW = 0;
    public static final int SPLIT_MIN_DIMENSION_DP_DEFAULT = 600;
    private final SplitAttributes defaultSplitAttributes;
    private final EmbeddingAspectRatio maxAspectRatioInLandscape;
    private final EmbeddingAspectRatio maxAspectRatioInPortrait;
    private final int minHeightDp;
    private final int minSmallestWidthDp;
    private final int minWidthDp;
    public static final Companion Companion = new Companion(null);
    public static final EmbeddingAspectRatio SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT = EmbeddingAspectRatio.Companion.ratio(1.4f);
    public static final EmbeddingAspectRatio SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT = EmbeddingAspectRatio.ALWAYS_ALLOW;

    private final int convertDpToPx(float f, int i) {
        return (int) ((i * f) + 0.5f);
    }

    public final int getMinWidthDp() {
        return this.minWidthDp;
    }

    public final int getMinHeightDp() {
        return this.minHeightDp;
    }

    public final int getMinSmallestWidthDp() {
        return this.minSmallestWidthDp;
    }

    public /* synthetic */ SplitRule(String str, int i, int i2, int i3, EmbeddingAspectRatio embeddingAspectRatio, EmbeddingAspectRatio embeddingAspectRatio2, SplitAttributes splitAttributes, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, (i4 & 2) != 0 ? 600 : i, (i4 & 4) != 0 ? 600 : i2, (i4 & 8) != 0 ? 600 : i3, (i4 & 16) != 0 ? SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT : embeddingAspectRatio, (i4 & 32) != 0 ? SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT : embeddingAspectRatio2, splitAttributes);
    }

    public final EmbeddingAspectRatio getMaxAspectRatioInPortrait() {
        return this.maxAspectRatioInPortrait;
    }

    public final EmbeddingAspectRatio getMaxAspectRatioInLandscape() {
        return this.maxAspectRatioInLandscape;
    }

    public final SplitAttributes getDefaultSplitAttributes() {
        return this.defaultSplitAttributes;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplitRule(String str, int i, int i2, int i3, EmbeddingAspectRatio maxAspectRatioInPortrait, EmbeddingAspectRatio maxAspectRatioInLandscape, SplitAttributes defaultSplitAttributes) {
        super(str);
        Intrinsics.checkNotNullParameter(maxAspectRatioInPortrait, "maxAspectRatioInPortrait");
        Intrinsics.checkNotNullParameter(maxAspectRatioInLandscape, "maxAspectRatioInLandscape");
        Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
        this.minWidthDp = i;
        this.minHeightDp = i2;
        this.minSmallestWidthDp = i3;
        this.maxAspectRatioInPortrait = maxAspectRatioInPortrait;
        this.maxAspectRatioInLandscape = maxAspectRatioInLandscape;
        this.defaultSplitAttributes = defaultSplitAttributes;
        Preconditions.checkArgumentNonnegative(i, "minWidthDp must be non-negative");
        Preconditions.checkArgumentNonnegative(i2, "minHeightDp must be non-negative");
        Preconditions.checkArgumentNonnegative(i3, "minSmallestWidthDp must be non-negative");
    }

    /* compiled from: SplitRule.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* compiled from: SplitRule.kt */
    /* loaded from: classes.dex */
    public static final class FinishBehavior {
        private final String description;
        private final int value;
        public static final Companion Companion = new Companion(null);
        public static final FinishBehavior NEVER = new FinishBehavior("NEVER", 0);
        public static final FinishBehavior ALWAYS = new FinishBehavior("ALWAYS", 1);
        public static final FinishBehavior ADJACENT = new FinishBehavior("ADJACENT", 2);

        private FinishBehavior(String str, int i) {
            this.description = str;
            this.value = i;
        }

        public final int getValue$window_release() {
            return this.value;
        }

        public String toString() {
            return this.description;
        }

        /* compiled from: SplitRule.kt */
        /* loaded from: classes.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final FinishBehavior getFinishBehaviorFromValue$window_release(int i) {
                FinishBehavior finishBehavior = FinishBehavior.NEVER;
                if (i != finishBehavior.getValue$window_release()) {
                    finishBehavior = FinishBehavior.ALWAYS;
                    if (i != finishBehavior.getValue$window_release()) {
                        finishBehavior = FinishBehavior.ADJACENT;
                        if (i != finishBehavior.getValue$window_release()) {
                            throw new IllegalArgumentException("Unknown finish behavior:" + i);
                        }
                    }
                }
                return finishBehavior;
            }
        }
    }

    public final boolean checkParentMetrics$window_release(Context context, WindowMetrics parentMetrics) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parentMetrics, "parentMetrics");
        return checkParentBounds$window_release(context.getResources().getDisplayMetrics().density, Api30Impl.INSTANCE.getBounds(parentMetrics));
    }

    public final boolean checkParentBounds$window_release(float f, Rect bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        int width = bounds.width();
        int height = bounds.height();
        if (width == 0 || height == 0) {
            return false;
        }
        return (this.minWidthDp == 0 || width >= convertDpToPx(f, this.minWidthDp)) && (this.minHeightDp == 0 || height >= convertDpToPx(f, this.minHeightDp)) && (this.minSmallestWidthDp == 0 || Math.min(width, height) >= convertDpToPx(f, this.minSmallestWidthDp)) && (height < width ? Intrinsics.areEqual(this.maxAspectRatioInLandscape, EmbeddingAspectRatio.ALWAYS_ALLOW) || (((((float) width) * 1.0f) / ((float) height)) > this.maxAspectRatioInLandscape.getValue$window_release() ? 1 : (((((float) width) * 1.0f) / ((float) height)) == this.maxAspectRatioInLandscape.getValue$window_release() ? 0 : -1)) <= 0 : Intrinsics.areEqual(this.maxAspectRatioInPortrait, EmbeddingAspectRatio.ALWAYS_ALLOW) || (((((float) height) * 1.0f) / ((float) width)) > this.maxAspectRatioInPortrait.getValue$window_release() ? 1 : (((((float) height) * 1.0f) / ((float) width)) == this.maxAspectRatioInPortrait.getValue$window_release() ? 0 : -1)) <= 0);
    }

    /* compiled from: SplitRule.kt */
    /* loaded from: classes.dex */
    public static final class Api30Impl {
        public static final Api30Impl INSTANCE = new Api30Impl();

        private Api30Impl() {
        }

        public final Rect getBounds(WindowMetrics windowMetrics) {
            Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
            Rect bounds = windowMetrics.getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "windowMetrics.bounds");
            return bounds;
        }
    }

    @Override // androidx.window.embedding.EmbeddingRule
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof SplitRule) && super.equals(obj)) {
            SplitRule splitRule = (SplitRule) obj;
            return this.minWidthDp == splitRule.minWidthDp && this.minHeightDp == splitRule.minHeightDp && this.minSmallestWidthDp == splitRule.minSmallestWidthDp && Intrinsics.areEqual(this.maxAspectRatioInPortrait, splitRule.maxAspectRatioInPortrait) && Intrinsics.areEqual(this.maxAspectRatioInLandscape, splitRule.maxAspectRatioInLandscape) && Intrinsics.areEqual(this.defaultSplitAttributes, splitRule.defaultSplitAttributes);
        }
        return false;
    }

    @Override // androidx.window.embedding.EmbeddingRule
    public int hashCode() {
        return (((((((((((super.hashCode() * 31) + this.minWidthDp) * 31) + this.minHeightDp) * 31) + this.minSmallestWidthDp) * 31) + this.maxAspectRatioInPortrait.hashCode()) * 31) + this.maxAspectRatioInLandscape.hashCode()) * 31) + this.defaultSplitAttributes.hashCode();
    }

    public String toString() {
        return SplitRule.class.getSimpleName() + "{ tag=" + getTag() + ", defaultSplitAttributes=" + this.defaultSplitAttributes + ", minWidthDp=" + this.minWidthDp + ", minHeightDp=" + this.minHeightDp + ", minSmallestWidthDp=" + this.minSmallestWidthDp + ", maxAspectRatioInPortrait=" + this.maxAspectRatioInPortrait + ", maxAspectRatioInLandscape=" + this.maxAspectRatioInLandscape + '}';
    }
}
