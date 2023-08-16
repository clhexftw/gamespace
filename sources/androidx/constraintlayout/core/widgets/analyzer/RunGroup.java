package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class RunGroup {
    public static int index;
    int mDirection;
    WidgetRun mFirstRun;
    int mGroupIndex;
    WidgetRun mLastRun;
    public int position = 0;
    public boolean dual = false;
    ArrayList<WidgetRun> mRuns = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunGroup(WidgetRun widgetRun, int i) {
        this.mFirstRun = null;
        this.mLastRun = null;
        int i2 = index;
        this.mGroupIndex = i2;
        index = i2 + 1;
        this.mFirstRun = widgetRun;
        this.mLastRun = widgetRun;
        this.mDirection = i;
    }

    public void add(WidgetRun widgetRun) {
        this.mRuns.add(widgetRun);
        this.mLastRun = widgetRun;
    }

    private long traverseStart(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.mRun;
        if (widgetRun instanceof HelperReferences) {
            return j;
        }
        int size = dependencyNode.mDependencies.size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            Dependency dependency = dependencyNode.mDependencies.get(i);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.mRun != widgetRun) {
                    j2 = Math.max(j2, traverseStart(dependencyNode2, dependencyNode2.mMargin + j));
                }
            }
        }
        if (dependencyNode == widgetRun.start) {
            long wrapDimension = j + widgetRun.getWrapDimension();
            return Math.max(Math.max(j2, traverseStart(widgetRun.end, wrapDimension)), wrapDimension - widgetRun.end.mMargin);
        }
        return j2;
    }

    private long traverseEnd(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.mRun;
        if (widgetRun instanceof HelperReferences) {
            return j;
        }
        int size = dependencyNode.mDependencies.size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            Dependency dependency = dependencyNode.mDependencies.get(i);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.mRun != widgetRun) {
                    j2 = Math.min(j2, traverseEnd(dependencyNode2, dependencyNode2.mMargin + j));
                }
            }
        }
        if (dependencyNode == widgetRun.end) {
            long wrapDimension = j - widgetRun.getWrapDimension();
            return Math.min(Math.min(j2, traverseEnd(widgetRun.start, wrapDimension)), wrapDimension - widgetRun.start.mMargin);
        }
        return j2;
    }

    public long computeWrapSize(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        WidgetRun widgetRun = this.mFirstRun;
        if (widgetRun instanceof ChainRun) {
            if (((ChainRun) widgetRun).orientation != i) {
                return 0L;
            }
        } else if (i == 0) {
            if (!(widgetRun instanceof HorizontalWidgetRun)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof VerticalWidgetRun)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i == 0 ? constraintWidgetContainer.mHorizontalRun : constraintWidgetContainer.mVerticalRun).start;
        DependencyNode dependencyNode2 = (i == 0 ? constraintWidgetContainer.mHorizontalRun : constraintWidgetContainer.mVerticalRun).end;
        boolean contains = widgetRun.start.mTargets.contains(dependencyNode);
        boolean contains2 = this.mFirstRun.end.mTargets.contains(dependencyNode2);
        long wrapDimension = this.mFirstRun.getWrapDimension();
        if (!contains || !contains2) {
            if (contains) {
                DependencyNode dependencyNode3 = this.mFirstRun.start;
                return Math.max(traverseStart(dependencyNode3, dependencyNode3.mMargin), this.mFirstRun.start.mMargin + wrapDimension);
            } else if (contains2) {
                DependencyNode dependencyNode4 = this.mFirstRun.end;
                return Math.max(-traverseEnd(dependencyNode4, dependencyNode4.mMargin), (-this.mFirstRun.end.mMargin) + wrapDimension);
            } else {
                WidgetRun widgetRun2 = this.mFirstRun;
                return (widgetRun2.start.mMargin + widgetRun2.getWrapDimension()) - this.mFirstRun.end.mMargin;
            }
        }
        long traverseStart = traverseStart(this.mFirstRun.start, 0L);
        long traverseEnd = traverseEnd(this.mFirstRun.end, 0L);
        long j = traverseStart - wrapDimension;
        WidgetRun widgetRun3 = this.mFirstRun;
        int i2 = widgetRun3.end.mMargin;
        if (j >= (-i2)) {
            j += i2;
        }
        int i3 = widgetRun3.start.mMargin;
        long j2 = ((-traverseEnd) - wrapDimension) - i3;
        if (j2 >= i3) {
            j2 -= i3;
        }
        float biasPercent = widgetRun3.mWidget.getBiasPercent(i);
        float f = (float) (biasPercent > 0.0f ? (((float) j2) / biasPercent) + (((float) j) / (1.0f - biasPercent)) : 0L);
        long j3 = (f * biasPercent) + 0.5f + wrapDimension + (f * (1.0f - biasPercent)) + 0.5f;
        WidgetRun widgetRun4 = this.mFirstRun;
        return (widgetRun4.start.mMargin + j3) - widgetRun4.end.mMargin;
    }
}
