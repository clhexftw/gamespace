package androidx.window.embedding;

import android.content.res.Configuration;
import androidx.window.core.ExperimentalWindowApi;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SplitAttributesCalculatorParams.kt */
@ExperimentalWindowApi
/* loaded from: classes.dex */
public final class SplitAttributesCalculatorParams {
    private final boolean areDefaultConstraintsSatisfied;
    private final SplitAttributes defaultSplitAttributes;
    private final Configuration parentConfiguration;
    private final WindowLayoutInfo parentWindowLayoutInfo;
    private final WindowMetrics parentWindowMetrics;
    private final String splitRuleTag;

    public SplitAttributesCalculatorParams(WindowMetrics parentWindowMetrics, Configuration parentConfiguration, WindowLayoutInfo parentWindowLayoutInfo, SplitAttributes defaultSplitAttributes, boolean z, String str) {
        Intrinsics.checkNotNullParameter(parentWindowMetrics, "parentWindowMetrics");
        Intrinsics.checkNotNullParameter(parentConfiguration, "parentConfiguration");
        Intrinsics.checkNotNullParameter(parentWindowLayoutInfo, "parentWindowLayoutInfo");
        Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
        this.parentWindowMetrics = parentWindowMetrics;
        this.parentConfiguration = parentConfiguration;
        this.parentWindowLayoutInfo = parentWindowLayoutInfo;
        this.defaultSplitAttributes = defaultSplitAttributes;
        this.areDefaultConstraintsSatisfied = z;
        this.splitRuleTag = str;
    }

    public final WindowMetrics getParentWindowMetrics() {
        return this.parentWindowMetrics;
    }

    public final Configuration getParentConfiguration() {
        return this.parentConfiguration;
    }

    public final WindowLayoutInfo getParentWindowLayoutInfo() {
        return this.parentWindowLayoutInfo;
    }

    public final SplitAttributes getDefaultSplitAttributes() {
        return this.defaultSplitAttributes;
    }

    public final boolean areDefaultConstraintsSatisfied() {
        return this.areDefaultConstraintsSatisfied;
    }

    public final String getSplitRuleTag() {
        return this.splitRuleTag;
    }

    public String toString() {
        return SplitAttributesCalculatorParams.class.getSimpleName() + ":{windowMetrics=" + this.parentWindowMetrics + ", configuration=" + this.parentConfiguration + ", windowLayoutInfo=" + this.parentWindowLayoutInfo + ", defaultSplitAttributes=" + this.defaultSplitAttributes + ", areDefaultConstraintsSatisfied=" + this.areDefaultConstraintsSatisfied + ", tag=" + this.splitRuleTag + '}';
    }
}
