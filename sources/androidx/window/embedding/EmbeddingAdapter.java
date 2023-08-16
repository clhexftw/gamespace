package androidx.window.embedding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Pair;
import android.view.WindowMetrics;
import androidx.window.core.ExtensionsUtil;
import androidx.window.core.PredicateAdapter;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitRule;
import androidx.window.extensions.core.util.function.Function;
import androidx.window.extensions.core.util.function.Predicate;
import androidx.window.extensions.embedding.ActivityRule;
import androidx.window.extensions.embedding.SplitAttributes;
import androidx.window.extensions.embedding.SplitPairRule;
import androidx.window.extensions.embedding.SplitPlaceholderRule;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetricsCalculator;
import androidx.window.layout.adapter.extensions.ExtensionsWindowLayoutInfoAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
/* compiled from: EmbeddingAdapter.kt */
@SourceDebugExtension({"SMAP\nEmbeddingAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EmbeddingAdapter.kt\nandroidx/window/embedding/EmbeddingAdapter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,517:1\n1549#2:518\n1620#2,3:519\n1549#2:522\n1620#2,3:523\n1747#2,3:526\n1747#2,3:529\n1747#2,3:532\n1747#2,3:535\n1747#2,3:538\n1747#2,3:541\n*S KotlinDebug\n*F\n+ 1 EmbeddingAdapter.kt\nandroidx/window/embedding/EmbeddingAdapter\n*L\n72#1:518\n72#1:519,3\n313#1:522\n313#1:523,3\n163#1:526,3\n169#1:529,3\n253#1:532,3\n256#1:535,3\n296#1:538,3\n299#1:541,3\n*E\n"})
/* loaded from: classes.dex */
public final class EmbeddingAdapter {
    private final VendorApiLevel1Impl api1Impl;
    private final VendorApiLevel2Impl api2Impl;
    private final PredicateAdapter predicateAdapter;
    private final int vendorApiLevel;

    public EmbeddingAdapter(PredicateAdapter predicateAdapter) {
        Intrinsics.checkNotNullParameter(predicateAdapter, "predicateAdapter");
        this.predicateAdapter = predicateAdapter;
        this.vendorApiLevel = ExtensionsUtil.INSTANCE.getSafeVendorApiLevel();
        this.api1Impl = new VendorApiLevel1Impl(this, predicateAdapter);
        this.api2Impl = new VendorApiLevel2Impl();
    }

    public final List<SplitInfo> translate(List<? extends androidx.window.extensions.embedding.SplitInfo> splitInfoList) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(splitInfoList, "splitInfoList");
        List<? extends androidx.window.extensions.embedding.SplitInfo> list = splitInfoList;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (androidx.window.extensions.embedding.SplitInfo splitInfo : list) {
            arrayList.add(translate(splitInfo));
        }
        return arrayList;
    }

    private final SplitInfo translate(androidx.window.extensions.embedding.SplitInfo splitInfo) {
        int i = this.vendorApiLevel;
        if (i != 1) {
            if (i == 2) {
                return this.api2Impl.translateCompat(splitInfo);
            }
            androidx.window.extensions.embedding.ActivityStack primaryActivityStack = splitInfo.getPrimaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(primaryActivityStack, "splitInfo.primaryActivityStack");
            androidx.window.extensions.embedding.ActivityStack secondaryActivityStack = splitInfo.getSecondaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(secondaryActivityStack, "splitInfo.secondaryActivityStack");
            List activities = primaryActivityStack.getActivities();
            Intrinsics.checkNotNullExpressionValue(activities, "primaryActivityStack.activities");
            ActivityStack activityStack = new ActivityStack(activities, primaryActivityStack.isEmpty());
            List activities2 = secondaryActivityStack.getActivities();
            Intrinsics.checkNotNullExpressionValue(activities2, "secondaryActivityStack.activities");
            ActivityStack activityStack2 = new ActivityStack(activities2, secondaryActivityStack.isEmpty());
            androidx.window.extensions.embedding.SplitAttributes splitAttributes = splitInfo.getSplitAttributes();
            Intrinsics.checkNotNullExpressionValue(splitAttributes, "splitInfo.splitAttributes");
            return new SplitInfo(activityStack, activityStack2, translate$window_release(splitAttributes));
        }
        return this.api1Impl.translateCompat(splitInfo);
    }

    public final SplitAttributes translate$window_release(androidx.window.extensions.embedding.SplitAttributes splitAttributes) {
        SplitAttributes.SplitType ratio;
        SplitAttributes.LayoutDirection layoutDirection;
        Intrinsics.checkNotNullParameter(splitAttributes, "splitAttributes");
        SplitAttributes.Builder builder = new SplitAttributes.Builder();
        SplitAttributes.SplitType.RatioSplitType splitType = splitAttributes.getSplitType();
        Intrinsics.checkNotNullExpressionValue(splitType, "splitAttributes.splitType");
        if (splitType instanceof SplitAttributes.SplitType.HingeSplitType) {
            ratio = SplitAttributes.SplitType.SPLIT_TYPE_HINGE;
        } else if (splitType instanceof SplitAttributes.SplitType.ExpandContainersSplitType) {
            ratio = SplitAttributes.SplitType.SPLIT_TYPE_EXPAND;
        } else if (!(splitType instanceof SplitAttributes.SplitType.RatioSplitType)) {
            throw new IllegalArgumentException("Unknown split type: " + splitType);
        } else {
            ratio = SplitAttributes.SplitType.Companion.ratio(splitType.getRatio());
        }
        SplitAttributes.Builder splitType2 = builder.setSplitType(ratio);
        int layoutDirection2 = splitAttributes.getLayoutDirection();
        if (layoutDirection2 == 0) {
            layoutDirection = SplitAttributes.LayoutDirection.LEFT_TO_RIGHT;
        } else if (layoutDirection2 == 1) {
            layoutDirection = SplitAttributes.LayoutDirection.RIGHT_TO_LEFT;
        } else if (layoutDirection2 == 3) {
            layoutDirection = SplitAttributes.LayoutDirection.LOCALE;
        } else if (layoutDirection2 == 4) {
            layoutDirection = SplitAttributes.LayoutDirection.TOP_TO_BOTTOM;
        } else if (layoutDirection2 == 5) {
            layoutDirection = SplitAttributes.LayoutDirection.BOTTOM_TO_TOP;
        } else {
            throw new IllegalArgumentException("Unknown layout direction: " + layoutDirection2);
        }
        return splitType2.setLayoutDirection(layoutDirection).setAnimationBackgroundColor(SplitAttributes.BackgroundColor.Companion.buildFromValue$window_release(splitAttributes.getAnimationBackgroundColor())).build();
    }

    public final Function<androidx.window.extensions.embedding.SplitAttributesCalculatorParams, androidx.window.extensions.embedding.SplitAttributes> translateSplitAttributesCalculator(final Function1<? super SplitAttributesCalculatorParams, SplitAttributes> calculator) {
        Intrinsics.checkNotNullParameter(calculator, "calculator");
        return new Function() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda3
            @Override // androidx.window.extensions.core.util.function.Function
            public final Object apply(Object obj) {
                androidx.window.extensions.embedding.SplitAttributes translateSplitAttributesCalculator$lambda$0;
                translateSplitAttributesCalculator$lambda$0 = EmbeddingAdapter.translateSplitAttributesCalculator$lambda$0(EmbeddingAdapter.this, calculator, (androidx.window.extensions.embedding.SplitAttributesCalculatorParams) obj);
                return translateSplitAttributesCalculator$lambda$0;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final androidx.window.extensions.embedding.SplitAttributes translateSplitAttributesCalculator$lambda$0(EmbeddingAdapter this$0, Function1 calculator, androidx.window.extensions.embedding.SplitAttributesCalculatorParams oemParams) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(calculator, "$calculator");
        Intrinsics.checkNotNullExpressionValue(oemParams, "oemParams");
        return this$0.translateSplitAttributes((SplitAttributes) calculator.invoke(this$0.translate(oemParams)));
    }

    @SuppressLint({"NewApi"})
    public final SplitAttributesCalculatorParams translate(androidx.window.extensions.embedding.SplitAttributesCalculatorParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        WindowMetrics parentWindowMetrics = params.getParentWindowMetrics();
        Intrinsics.checkNotNullExpressionValue(parentWindowMetrics, "params.parentWindowMetrics");
        Configuration parentConfiguration = params.getParentConfiguration();
        Intrinsics.checkNotNullExpressionValue(parentConfiguration, "params.parentConfiguration");
        WindowLayoutInfo parentWindowLayoutInfo = params.getParentWindowLayoutInfo();
        Intrinsics.checkNotNullExpressionValue(parentWindowLayoutInfo, "params.parentWindowLayoutInfo");
        androidx.window.extensions.embedding.SplitAttributes defaultSplitAttributes = params.getDefaultSplitAttributes();
        Intrinsics.checkNotNullExpressionValue(defaultSplitAttributes, "params.defaultSplitAttributes");
        boolean areDefaultConstraintsSatisfied = params.areDefaultConstraintsSatisfied();
        String splitRuleTag = params.getSplitRuleTag();
        androidx.window.layout.WindowMetrics translateWindowMetrics$window_release = WindowMetricsCalculator.Companion.translateWindowMetrics$window_release(parentWindowMetrics);
        return new SplitAttributesCalculatorParams(translateWindowMetrics$window_release, parentConfiguration, ExtensionsWindowLayoutInfoAdapter.INSTANCE.translate$window_release(translateWindowMetrics$window_release, parentWindowLayoutInfo), translate$window_release(defaultSplitAttributes), areDefaultConstraintsSatisfied, splitRuleTag);
    }

    private final androidx.window.extensions.embedding.SplitPairRule translateSplitPairRule(final Context context, final SplitPairRule splitPairRule, Class<?> cls) {
        if (this.vendorApiLevel < 2) {
            return this.api1Impl.translateSplitPairRuleCompat(context, splitPairRule, cls);
        }
        Predicate predicate = new Predicate() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda0
            @Override // androidx.window.extensions.core.util.function.Predicate
            public final boolean test(Object obj) {
                boolean translateSplitPairRule$lambda$3;
                translateSplitPairRule$lambda$3 = EmbeddingAdapter.translateSplitPairRule$lambda$3(SplitPairRule.this, (Pair) obj);
                return translateSplitPairRule$lambda$3;
            }
        };
        Predicate predicate2 = new Predicate() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda1
            @Override // androidx.window.extensions.core.util.function.Predicate
            public final boolean test(Object obj) {
                boolean translateSplitPairRule$lambda$5;
                translateSplitPairRule$lambda$5 = EmbeddingAdapter.translateSplitPairRule$lambda$5(SplitPairRule.this, (Pair) obj);
                return translateSplitPairRule$lambda$5;
            }
        };
        Predicate predicate3 = new Predicate() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda2
            @Override // androidx.window.extensions.core.util.function.Predicate
            public final boolean test(Object obj) {
                boolean translateSplitPairRule$lambda$6;
                translateSplitPairRule$lambda$6 = EmbeddingAdapter.translateSplitPairRule$lambda$6(SplitPairRule.this, context, (WindowMetrics) obj);
                return translateSplitPairRule$lambda$6;
            }
        };
        String tag = splitPairRule.getTag();
        SplitPairRule.Builder shouldClearTop = new SplitPairRule.Builder(predicate, predicate2, predicate3).setDefaultSplitAttributes(translateSplitAttributes(splitPairRule.getDefaultSplitAttributes())).setFinishPrimaryWithSecondary(translateFinishBehavior(splitPairRule.getFinishPrimaryWithSecondary())).setFinishSecondaryWithPrimary(translateFinishBehavior(splitPairRule.getFinishSecondaryWithPrimary())).setShouldClearTop(splitPairRule.getClearTop());
        Intrinsics.checkNotNullExpressionValue(shouldClearTop, "SplitPairRuleBuilder(\n  …ldClearTop(rule.clearTop)");
        if (tag != null) {
            shouldClearTop.setTag(tag);
        }
        androidx.window.extensions.embedding.SplitPairRule build = shouldClearTop.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translateSplitPairRule$lambda$3(SplitPairRule rule, Pair pair) {
        Intrinsics.checkNotNullParameter(rule, "$rule");
        Set<SplitPairFilter> filters = rule.getFilters();
        if ((filters instanceof Collection) && filters.isEmpty()) {
            return false;
        }
        for (SplitPairFilter splitPairFilter : filters) {
            Object obj = pair.first;
            Intrinsics.checkNotNullExpressionValue(obj, "activitiesPair.first");
            Object obj2 = pair.second;
            Intrinsics.checkNotNullExpressionValue(obj2, "activitiesPair.second");
            if (splitPairFilter.matchesActivityPair((Activity) obj, (Activity) obj2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translateSplitPairRule$lambda$5(SplitPairRule rule, Pair pair) {
        Intrinsics.checkNotNullParameter(rule, "$rule");
        Set<SplitPairFilter> filters = rule.getFilters();
        if ((filters instanceof Collection) && filters.isEmpty()) {
            return false;
        }
        for (SplitPairFilter splitPairFilter : filters) {
            Object obj = pair.first;
            Intrinsics.checkNotNullExpressionValue(obj, "activityIntentPair.first");
            Object obj2 = pair.second;
            Intrinsics.checkNotNullExpressionValue(obj2, "activityIntentPair.second");
            if (splitPairFilter.matchesActivityIntentPair((Activity) obj, (Intent) obj2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translateSplitPairRule$lambda$6(SplitPairRule rule, Context context, WindowMetrics windowMetrics) {
        Intrinsics.checkNotNullParameter(rule, "$rule");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullExpressionValue(windowMetrics, "windowMetrics");
        return rule.checkParentMetrics$window_release(context, windowMetrics);
    }

    public final androidx.window.extensions.embedding.SplitAttributes translateSplitAttributes(SplitAttributes splitAttributes) {
        Intrinsics.checkNotNullParameter(splitAttributes, "splitAttributes");
        int i = 1;
        if (!(this.vendorApiLevel >= 2)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        SplitAttributes.Builder splitType = new SplitAttributes.Builder().setSplitType(translateSplitType(splitAttributes.getSplitType()));
        SplitAttributes.LayoutDirection layoutDirection = splitAttributes.getLayoutDirection();
        if (Intrinsics.areEqual(layoutDirection, SplitAttributes.LayoutDirection.LOCALE)) {
            i = 3;
        } else if (Intrinsics.areEqual(layoutDirection, SplitAttributes.LayoutDirection.LEFT_TO_RIGHT)) {
            i = 0;
        } else if (!Intrinsics.areEqual(layoutDirection, SplitAttributes.LayoutDirection.RIGHT_TO_LEFT)) {
            if (Intrinsics.areEqual(layoutDirection, SplitAttributes.LayoutDirection.TOP_TO_BOTTOM)) {
                i = 4;
            } else if (!Intrinsics.areEqual(layoutDirection, SplitAttributes.LayoutDirection.BOTTOM_TO_TOP)) {
                throw new IllegalArgumentException("Unsupported layoutDirection:" + splitAttributes + ".layoutDirection");
            } else {
                i = 5;
            }
        }
        androidx.window.extensions.embedding.SplitAttributes build = splitType.setLayoutDirection(i).setAnimationBackgroundColor(splitAttributes.getAnimationBackgroundColor().getValue$window_release()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…lue)\n            .build()");
        return build;
    }

    private final SplitAttributes.SplitType translateSplitType(SplitAttributes.SplitType splitType) {
        if (!(this.vendorApiLevel >= 2)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (Intrinsics.areEqual(splitType, SplitAttributes.SplitType.SPLIT_TYPE_HINGE)) {
            return new SplitAttributes.SplitType.HingeSplitType(translateSplitType(SplitAttributes.SplitType.SPLIT_TYPE_EQUAL));
        }
        if (Intrinsics.areEqual(splitType, SplitAttributes.SplitType.SPLIT_TYPE_EXPAND)) {
            return new SplitAttributes.SplitType.ExpandContainersSplitType();
        }
        float value$window_release = splitType.getValue$window_release();
        double d = value$window_release;
        if (d > 0.0d && d < 1.0d) {
            return new SplitAttributes.SplitType.RatioSplitType(value$window_release);
        }
        throw new IllegalArgumentException("Unsupported SplitType: " + splitType + " with value: " + splitType.getValue$window_release());
    }

    private final androidx.window.extensions.embedding.SplitPlaceholderRule translateSplitPlaceholderRule(final Context context, final SplitPlaceholderRule splitPlaceholderRule, Class<?> cls) {
        if (this.vendorApiLevel < 2) {
            return this.api1Impl.translateSplitPlaceholderRuleCompat(context, splitPlaceholderRule, cls);
        }
        Predicate predicate = new Predicate() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda6
            @Override // androidx.window.extensions.core.util.function.Predicate
            public final boolean test(Object obj) {
                boolean translateSplitPlaceholderRule$lambda$8;
                translateSplitPlaceholderRule$lambda$8 = EmbeddingAdapter.translateSplitPlaceholderRule$lambda$8(SplitPlaceholderRule.this, (Activity) obj);
                return translateSplitPlaceholderRule$lambda$8;
            }
        };
        Predicate predicate2 = new Predicate() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda7
            @Override // androidx.window.extensions.core.util.function.Predicate
            public final boolean test(Object obj) {
                boolean translateSplitPlaceholderRule$lambda$10;
                translateSplitPlaceholderRule$lambda$10 = EmbeddingAdapter.translateSplitPlaceholderRule$lambda$10(SplitPlaceholderRule.this, (Intent) obj);
                return translateSplitPlaceholderRule$lambda$10;
            }
        };
        Predicate predicate3 = new Predicate() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda8
            @Override // androidx.window.extensions.core.util.function.Predicate
            public final boolean test(Object obj) {
                boolean translateSplitPlaceholderRule$lambda$11;
                translateSplitPlaceholderRule$lambda$11 = EmbeddingAdapter.translateSplitPlaceholderRule$lambda$11(SplitPlaceholderRule.this, context, (WindowMetrics) obj);
                return translateSplitPlaceholderRule$lambda$11;
            }
        };
        String tag = splitPlaceholderRule.getTag();
        SplitPlaceholderRule.Builder finishPrimaryWithPlaceholder = new SplitPlaceholderRule.Builder(splitPlaceholderRule.getPlaceholderIntent(), predicate, predicate2, predicate3).setSticky(splitPlaceholderRule.isSticky()).setDefaultSplitAttributes(translateSplitAttributes(splitPlaceholderRule.getDefaultSplitAttributes())).setFinishPrimaryWithPlaceholder(translateFinishBehavior(splitPlaceholderRule.getFinishPrimaryWithPlaceholder()));
        Intrinsics.checkNotNullExpressionValue(finishPrimaryWithPlaceholder, "SplitPlaceholderRuleBuil…holder)\n                )");
        if (tag != null) {
            finishPrimaryWithPlaceholder.setTag(tag);
        }
        androidx.window.extensions.embedding.SplitPlaceholderRule build = finishPrimaryWithPlaceholder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translateSplitPlaceholderRule$lambda$8(SplitPlaceholderRule rule, Activity activity) {
        Intrinsics.checkNotNullParameter(rule, "$rule");
        Set<ActivityFilter> filters = rule.getFilters();
        if ((filters instanceof Collection) && filters.isEmpty()) {
            return false;
        }
        for (ActivityFilter activityFilter : filters) {
            Intrinsics.checkNotNullExpressionValue(activity, "activity");
            if (activityFilter.matchesActivity(activity)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translateSplitPlaceholderRule$lambda$10(SplitPlaceholderRule rule, Intent intent) {
        Intrinsics.checkNotNullParameter(rule, "$rule");
        Set<ActivityFilter> filters = rule.getFilters();
        if ((filters instanceof Collection) && filters.isEmpty()) {
            return false;
        }
        for (ActivityFilter activityFilter : filters) {
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            if (activityFilter.matchesIntent(intent)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translateSplitPlaceholderRule$lambda$11(SplitPlaceholderRule rule, Context context, WindowMetrics windowMetrics) {
        Intrinsics.checkNotNullParameter(rule, "$rule");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullExpressionValue(windowMetrics, "windowMetrics");
        return rule.checkParentMetrics$window_release(context, windowMetrics);
    }

    public final int translateFinishBehavior(SplitRule.FinishBehavior behavior) {
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        if (Intrinsics.areEqual(behavior, SplitRule.FinishBehavior.NEVER)) {
            return 0;
        }
        if (Intrinsics.areEqual(behavior, SplitRule.FinishBehavior.ALWAYS)) {
            return 1;
        }
        if (Intrinsics.areEqual(behavior, SplitRule.FinishBehavior.ADJACENT)) {
            return 2;
        }
        throw new IllegalArgumentException("Unknown finish behavior:" + behavior);
    }

    private final androidx.window.extensions.embedding.ActivityRule translateActivityRule(final ActivityRule activityRule, Class<?> cls) {
        if (this.vendorApiLevel < 2) {
            return this.api1Impl.translateActivityRuleCompat(activityRule, cls);
        }
        ActivityRule.Builder shouldAlwaysExpand = new ActivityRule.Builder(new Predicate() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda4
            @Override // androidx.window.extensions.core.util.function.Predicate
            public final boolean test(Object obj) {
                boolean translateActivityRule$lambda$13;
                translateActivityRule$lambda$13 = EmbeddingAdapter.translateActivityRule$lambda$13(ActivityRule.this, (Activity) obj);
                return translateActivityRule$lambda$13;
            }
        }, new Predicate() { // from class: androidx.window.embedding.EmbeddingAdapter$$ExternalSyntheticLambda5
            @Override // androidx.window.extensions.core.util.function.Predicate
            public final boolean test(Object obj) {
                boolean translateActivityRule$lambda$15;
                translateActivityRule$lambda$15 = EmbeddingAdapter.translateActivityRule$lambda$15(ActivityRule.this, (Intent) obj);
                return translateActivityRule$lambda$15;
            }
        }).setShouldAlwaysExpand(activityRule.getAlwaysExpand());
        Intrinsics.checkNotNullExpressionValue(shouldAlwaysExpand, "ActivityRuleBuilder(acti…Expand(rule.alwaysExpand)");
        String tag = activityRule.getTag();
        if (tag != null) {
            shouldAlwaysExpand.setTag(tag);
        }
        androidx.window.extensions.embedding.ActivityRule build = shouldAlwaysExpand.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translateActivityRule$lambda$13(ActivityRule rule, Activity activity) {
        Intrinsics.checkNotNullParameter(rule, "$rule");
        Set<ActivityFilter> filters = rule.getFilters();
        if ((filters instanceof Collection) && filters.isEmpty()) {
            return false;
        }
        for (ActivityFilter activityFilter : filters) {
            Intrinsics.checkNotNullExpressionValue(activity, "activity");
            if (activityFilter.matchesActivity(activity)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translateActivityRule$lambda$15(ActivityRule rule, Intent intent) {
        Intrinsics.checkNotNullParameter(rule, "$rule");
        Set<ActivityFilter> filters = rule.getFilters();
        if ((filters instanceof Collection) && filters.isEmpty()) {
            return false;
        }
        for (ActivityFilter activityFilter : filters) {
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            if (activityFilter.matchesIntent(intent)) {
                return true;
            }
        }
        return false;
    }

    public final Set<androidx.window.extensions.embedding.EmbeddingRule> translate(Context context, Set<? extends EmbeddingRule> rules) {
        int collectionSizeOrDefault;
        Set<androidx.window.extensions.embedding.EmbeddingRule> set;
        androidx.window.extensions.embedding.SplitPairRule translateActivityRule;
        Set<androidx.window.extensions.embedding.EmbeddingRule> emptySet;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(rules, "rules");
        Class<?> predicateClassOrNull$window_release = this.predicateAdapter.predicateClassOrNull$window_release();
        if (predicateClassOrNull$window_release == null) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        }
        Set<? extends EmbeddingRule> set2 = rules;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (EmbeddingRule embeddingRule : set2) {
            if (embeddingRule instanceof SplitPairRule) {
                translateActivityRule = translateSplitPairRule(context, (SplitPairRule) embeddingRule, predicateClassOrNull$window_release);
            } else if (embeddingRule instanceof SplitPlaceholderRule) {
                translateActivityRule = translateSplitPlaceholderRule(context, (SplitPlaceholderRule) embeddingRule, predicateClassOrNull$window_release);
            } else if (!(embeddingRule instanceof ActivityRule)) {
                throw new IllegalArgumentException("Unsupported rule type");
            } else {
                translateActivityRule = translateActivityRule((ActivityRule) embeddingRule, predicateClassOrNull$window_release);
            }
            arrayList.add((androidx.window.extensions.embedding.EmbeddingRule) translateActivityRule);
        }
        set = CollectionsKt___CollectionsKt.toSet(arrayList);
        return set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EmbeddingAdapter.kt */
    /* loaded from: classes.dex */
    public final class VendorApiLevel2Impl {
        public VendorApiLevel2Impl() {
        }

        public final SplitInfo translateCompat(androidx.window.extensions.embedding.SplitInfo splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            androidx.window.extensions.embedding.ActivityStack primaryActivityStack = splitInfo.getPrimaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(primaryActivityStack, "splitInfo.primaryActivityStack");
            List activities = primaryActivityStack.getActivities();
            Intrinsics.checkNotNullExpressionValue(activities, "primaryActivityStack.activities");
            ActivityStack activityStack = new ActivityStack(activities, primaryActivityStack.isEmpty());
            androidx.window.extensions.embedding.ActivityStack secondaryActivityStack = splitInfo.getSecondaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(secondaryActivityStack, "splitInfo.secondaryActivityStack");
            List activities2 = secondaryActivityStack.getActivities();
            Intrinsics.checkNotNullExpressionValue(activities2, "secondaryActivityStack.activities");
            ActivityStack activityStack2 = new ActivityStack(activities2, secondaryActivityStack.isEmpty());
            EmbeddingAdapter embeddingAdapter = EmbeddingAdapter.this;
            androidx.window.extensions.embedding.SplitAttributes splitAttributes = splitInfo.getSplitAttributes();
            Intrinsics.checkNotNullExpressionValue(splitAttributes, "splitInfo.splitAttributes");
            return new SplitInfo(activityStack, activityStack2, embeddingAdapter.translate$window_release(splitAttributes));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EmbeddingAdapter.kt */
    /* loaded from: classes.dex */
    public final class VendorApiLevel1Impl {
        private final PredicateAdapter predicateAdapter;
        final /* synthetic */ EmbeddingAdapter this$0;

        public VendorApiLevel1Impl(EmbeddingAdapter embeddingAdapter, PredicateAdapter predicateAdapter) {
            Intrinsics.checkNotNullParameter(predicateAdapter, "predicateAdapter");
            this.this$0 = embeddingAdapter;
            this.predicateAdapter = predicateAdapter;
        }

        public final PredicateAdapter getPredicateAdapter() {
            return this.predicateAdapter;
        }

        public final SplitAttributes getSplitAttributesCompat(androidx.window.extensions.embedding.SplitInfo splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            return new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.Companion.buildSplitTypeFromValue$window_release(splitInfo.getSplitRatio())).setLayoutDirection(SplitAttributes.LayoutDirection.LOCALE).build();
        }

        public final androidx.window.extensions.embedding.ActivityRule translateActivityRuleCompat(ActivityRule rule, Class<?> predicateClass) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            Intrinsics.checkNotNullParameter(predicateClass, "predicateClass");
            androidx.window.extensions.embedding.ActivityRule build = ((ActivityRule.Builder) ActivityRule.Builder.class.getConstructor(predicateClass, predicateClass).newInstance(translateActivityPredicates(rule.getFilters()), translateIntentPredicates(rule.getFilters()))).setShouldAlwaysExpand(rule.getAlwaysExpand()).build();
            Intrinsics.checkNotNullExpressionValue(build, "ActivityRuleBuilder::cla…\n                .build()");
            return build;
        }

        public final androidx.window.extensions.embedding.SplitPlaceholderRule translateSplitPlaceholderRuleCompat(Context context, SplitPlaceholderRule rule, Class<?> predicateClass) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(rule, "rule");
            Intrinsics.checkNotNullParameter(predicateClass, "predicateClass");
            SplitPlaceholderRule.Builder finishPrimaryWithSecondary = ((SplitPlaceholderRule.Builder) SplitPlaceholderRule.Builder.class.getConstructor(Intent.class, predicateClass, predicateClass, predicateClass).newInstance(rule.getPlaceholderIntent(), translateActivityPredicates(rule.getFilters()), translateIntentPredicates(rule.getFilters()), translateParentMetricsPredicate(context, rule))).setSticky(rule.isSticky()).setFinishPrimaryWithSecondary(this.this$0.translateFinishBehavior(rule.getFinishPrimaryWithPlaceholder()));
            Intrinsics.checkNotNullExpressionValue(finishPrimaryWithSecondary, "SplitPlaceholderRuleBuil…holder)\n                )");
            androidx.window.extensions.embedding.SplitPlaceholderRule build = setDefaultSplitAttributesCompat(finishPrimaryWithSecondary, rule.getDefaultSplitAttributes()).build();
            Intrinsics.checkNotNullExpressionValue(build, "SplitPlaceholderRuleBuil…\n                .build()");
            return build;
        }

        private final SplitPlaceholderRule.Builder setDefaultSplitAttributesCompat(SplitPlaceholderRule.Builder builder, SplitAttributes splitAttributes) {
            kotlin.Pair<Float, Integer> translateSplitAttributesCompatInternal = translateSplitAttributesCompatInternal(splitAttributes);
            float floatValue = translateSplitAttributesCompatInternal.component1().floatValue();
            int intValue = translateSplitAttributesCompatInternal.component2().intValue();
            builder.setSplitRatio(floatValue);
            builder.setLayoutDirection(intValue);
            return builder;
        }

        public final androidx.window.extensions.embedding.SplitPairRule translateSplitPairRuleCompat(Context context, SplitPairRule rule, Class<?> predicateClass) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(rule, "rule");
            Intrinsics.checkNotNullParameter(predicateClass, "predicateClass");
            Object newInstance = SplitPairRule.Builder.class.getConstructor(predicateClass, predicateClass, predicateClass).newInstance(translateActivityPairPredicates(rule.getFilters()), translateActivityIntentPredicates(rule.getFilters()), translateParentMetricsPredicate(context, rule));
            Intrinsics.checkNotNullExpressionValue(newInstance, "SplitPairRuleBuilder::cl…text, rule)\n            )");
            androidx.window.extensions.embedding.SplitPairRule build = setDefaultSplitAttributesCompat((SplitPairRule.Builder) newInstance, rule.getDefaultSplitAttributes()).setShouldClearTop(rule.getClearTop()).setFinishPrimaryWithSecondary(this.this$0.translateFinishBehavior(rule.getFinishPrimaryWithSecondary())).setFinishSecondaryWithPrimary(this.this$0.translateFinishBehavior(rule.getFinishSecondaryWithPrimary())).build();
            Intrinsics.checkNotNullExpressionValue(build, "SplitPairRuleBuilder::cl…                ).build()");
            return build;
        }

        @SuppressLint({"ClassVerificationFailure", "NewApi"})
        private final Object translateActivityPairPredicates(final Set<SplitPairFilter> set) {
            return this.predicateAdapter.buildPairPredicate(Reflection.getOrCreateKotlinClass(Activity.class), Reflection.getOrCreateKotlinClass(Activity.class), new Function2<Activity, Activity, Boolean>() { // from class: androidx.window.embedding.EmbeddingAdapter$VendorApiLevel1Impl$translateActivityPairPredicates$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(Activity first, Activity second) {
                    Intrinsics.checkNotNullParameter(first, "first");
                    Intrinsics.checkNotNullParameter(second, "second");
                    Set<SplitPairFilter> set2 = set;
                    boolean z = false;
                    if (!(set2 instanceof Collection) || !set2.isEmpty()) {
                        Iterator<T> it = set2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            } else if (((SplitPairFilter) it.next()).matchesActivityPair(first, second)) {
                                z = true;
                                break;
                            }
                        }
                    }
                    return Boolean.valueOf(z);
                }
            });
        }

        @SuppressLint({"ClassVerificationFailure", "NewApi"})
        private final Object translateActivityIntentPredicates(final Set<SplitPairFilter> set) {
            return this.predicateAdapter.buildPairPredicate(Reflection.getOrCreateKotlinClass(Activity.class), Reflection.getOrCreateKotlinClass(Intent.class), new Function2<Activity, Intent, Boolean>() { // from class: androidx.window.embedding.EmbeddingAdapter$VendorApiLevel1Impl$translateActivityIntentPredicates$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(Activity first, Intent second) {
                    Intrinsics.checkNotNullParameter(first, "first");
                    Intrinsics.checkNotNullParameter(second, "second");
                    Set<SplitPairFilter> set2 = set;
                    boolean z = false;
                    if (!(set2 instanceof Collection) || !set2.isEmpty()) {
                        Iterator<T> it = set2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            } else if (((SplitPairFilter) it.next()).matchesActivityIntentPair(first, second)) {
                                z = true;
                                break;
                            }
                        }
                    }
                    return Boolean.valueOf(z);
                }
            });
        }

        private final SplitPairRule.Builder setDefaultSplitAttributesCompat(SplitPairRule.Builder builder, SplitAttributes splitAttributes) {
            kotlin.Pair<Float, Integer> translateSplitAttributesCompatInternal = translateSplitAttributesCompatInternal(splitAttributes);
            float floatValue = translateSplitAttributesCompatInternal.component1().floatValue();
            int intValue = translateSplitAttributesCompatInternal.component2().intValue();
            builder.setSplitRatio(floatValue);
            builder.setLayoutDirection(intValue);
            return builder;
        }

        private final kotlin.Pair<Float, Integer> translateSplitAttributesCompatInternal(SplitAttributes splitAttributes) {
            int i = 3;
            if (!isSplitAttributesSupported(splitAttributes)) {
                return new kotlin.Pair<>(Float.valueOf(0.0f), 3);
            }
            Float valueOf = Float.valueOf(splitAttributes.getSplitType().getValue$window_release());
            SplitAttributes.LayoutDirection layoutDirection = splitAttributes.getLayoutDirection();
            if (!Intrinsics.areEqual(layoutDirection, SplitAttributes.LayoutDirection.LOCALE)) {
                if (Intrinsics.areEqual(layoutDirection, SplitAttributes.LayoutDirection.LEFT_TO_RIGHT)) {
                    i = 0;
                } else if (!Intrinsics.areEqual(layoutDirection, SplitAttributes.LayoutDirection.RIGHT_TO_LEFT)) {
                    throw new IllegalStateException("Unsupported layout direction must be covered in @isSplitAttributesSupported!");
                } else {
                    i = 1;
                }
            }
            return new kotlin.Pair<>(valueOf, Integer.valueOf(i));
        }

        private final boolean isSplitAttributesSupported(SplitAttributes splitAttributes) {
            boolean contains;
            double value$window_release = splitAttributes.getSplitType().getValue$window_release();
            if (0.0d <= value$window_release && value$window_release <= 1.0d) {
                if (!(splitAttributes.getSplitType().getValue$window_release() == 1.0f)) {
                    contains = ArraysKt___ArraysKt.contains(new SplitAttributes.LayoutDirection[]{SplitAttributes.LayoutDirection.LEFT_TO_RIGHT, SplitAttributes.LayoutDirection.RIGHT_TO_LEFT, SplitAttributes.LayoutDirection.LOCALE}, splitAttributes.getLayoutDirection());
                    if (contains) {
                        return true;
                    }
                }
            }
            return false;
        }

        @SuppressLint({"ClassVerificationFailure", "NewApi"})
        private final Object translateActivityPredicates(final Set<ActivityFilter> set) {
            return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(Activity.class), new Function1<Activity, Boolean>() { // from class: androidx.window.embedding.EmbeddingAdapter$VendorApiLevel1Impl$translateActivityPredicates$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Activity activity) {
                    Intrinsics.checkNotNullParameter(activity, "activity");
                    Set<ActivityFilter> set2 = set;
                    boolean z = false;
                    if (!(set2 instanceof Collection) || !set2.isEmpty()) {
                        Iterator<T> it = set2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            } else if (((ActivityFilter) it.next()).matchesActivity(activity)) {
                                z = true;
                                break;
                            }
                        }
                    }
                    return Boolean.valueOf(z);
                }
            });
        }

        @SuppressLint({"ClassVerificationFailure", "NewApi"})
        private final Object translateIntentPredicates(final Set<ActivityFilter> set) {
            return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(Intent.class), new Function1<Intent, Boolean>() { // from class: androidx.window.embedding.EmbeddingAdapter$VendorApiLevel1Impl$translateIntentPredicates$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Intent intent) {
                    Intrinsics.checkNotNullParameter(intent, "intent");
                    Set<ActivityFilter> set2 = set;
                    boolean z = false;
                    if (!(set2 instanceof Collection) || !set2.isEmpty()) {
                        Iterator<T> it = set2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            } else if (((ActivityFilter) it.next()).matchesIntent(intent)) {
                                z = true;
                                break;
                            }
                        }
                    }
                    return Boolean.valueOf(z);
                }
            });
        }

        @SuppressLint({"ClassVerificationFailure", "NewApi"})
        private final Object translateParentMetricsPredicate(final Context context, final SplitRule splitRule) {
            return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(WindowMetrics.class), new Function1<WindowMetrics, Boolean>() { // from class: androidx.window.embedding.EmbeddingAdapter$VendorApiLevel1Impl$translateParentMetricsPredicate$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(WindowMetrics windowMetrics) {
                    Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
                    return Boolean.valueOf(SplitRule.this.checkParentMetrics$window_release(context, windowMetrics));
                }
            });
        }

        public final SplitInfo translateCompat(androidx.window.extensions.embedding.SplitInfo splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            List activities = splitInfo.getPrimaryActivityStack().getActivities();
            Intrinsics.checkNotNullExpressionValue(activities, "splitInfo.primaryActivityStack.activities");
            ActivityStack activityStack = new ActivityStack(activities, splitInfo.getPrimaryActivityStack().isEmpty());
            List activities2 = splitInfo.getSecondaryActivityStack().getActivities();
            Intrinsics.checkNotNullExpressionValue(activities2, "splitInfo.secondaryActivityStack.activities");
            return new SplitInfo(activityStack, new ActivityStack(activities2, splitInfo.getSecondaryActivityStack().isEmpty()), getSplitAttributesCompat(splitInfo));
        }
    }
}
