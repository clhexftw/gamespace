package androidx.window.embedding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import androidx.window.R;
import androidx.window.embedding.ActivityRule;
import androidx.window.embedding.EmbeddingAspectRatio;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitPairRule;
import androidx.window.embedding.SplitPlaceholderRule;
import androidx.window.embedding.SplitRule;
import java.util.HashSet;
import java.util.Set;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
/* compiled from: RuleParser.kt */
@SourceDebugExtension({"SMAP\nRuleParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RuleParser.kt\nandroidx/window/embedding/RuleParser\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,399:1\n1855#2,2:400\n*S KotlinDebug\n*F\n+ 1 RuleParser.kt\nandroidx/window/embedding/RuleParser\n*L\n123#1:400,2\n*E\n"})
/* loaded from: classes.dex */
public final class RuleParser {
    public static final RuleParser INSTANCE = new RuleParser();

    private RuleParser() {
    }

    public final Set<EmbeddingRule> parseRules$window_release(Context context, int i) {
        SplitPlaceholderRule plus$window_release;
        ActivityRule plus$window_release2;
        SplitPairRule parseSplitPairRule;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            XmlResourceParser xml = context.getResources().getXml(i);
            Intrinsics.checkNotNullExpressionValue(xml, "resources.getXml(staticRuleResourceId)");
            HashSet<EmbeddingRule> hashSet = new HashSet<>();
            int depth = xml.getDepth();
            int next = xml.next();
            ActivityRule activityRule = null;
            SplitPairRule splitPairRule = null;
            SplitPlaceholderRule splitPlaceholderRule = null;
            while (next != 1 && (next != 3 || xml.getDepth() > depth)) {
                if (xml.getEventType() != 2 || Intrinsics.areEqual("split-config", xml.getName())) {
                    next = xml.next();
                } else {
                    String name = xml.getName();
                    if (name != null) {
                        switch (name.hashCode()) {
                            case 511422343:
                                if (name.equals("ActivityFilter")) {
                                    if (activityRule != null || splitPlaceholderRule != null) {
                                        ActivityFilter parseActivityFilter = parseActivityFilter(context, xml);
                                        if (activityRule == null) {
                                            if (splitPlaceholderRule != null) {
                                                hashSet.remove(splitPlaceholderRule);
                                                plus$window_release = splitPlaceholderRule.plus$window_release(parseActivityFilter);
                                                addRuleWithDuplicatedTagCheck(hashSet, plus$window_release);
                                                splitPlaceholderRule = plus$window_release;
                                                break;
                                            }
                                        } else {
                                            hashSet.remove(activityRule);
                                            plus$window_release2 = activityRule.plus$window_release(parseActivityFilter);
                                            addRuleWithDuplicatedTagCheck(hashSet, plus$window_release2);
                                            activityRule = plus$window_release2;
                                            break;
                                        }
                                    } else {
                                        throw new IllegalArgumentException("Found orphaned ActivityFilter");
                                    }
                                }
                                break;
                            case 520447504:
                                if (name.equals("SplitPairRule")) {
                                    parseSplitPairRule = parseSplitPairRule(context, xml);
                                    addRuleWithDuplicatedTagCheck(hashSet, parseSplitPairRule);
                                    activityRule = null;
                                    splitPlaceholderRule = null;
                                    splitPairRule = parseSplitPairRule;
                                    break;
                                }
                                break;
                            case 1579230604:
                                if (name.equals("SplitPairFilter")) {
                                    if (splitPairRule != null) {
                                        SplitPairFilter parseSplitPairFilter = parseSplitPairFilter(context, xml);
                                        hashSet.remove(splitPairRule);
                                        parseSplitPairRule = splitPairRule.plus$window_release(parseSplitPairFilter);
                                        addRuleWithDuplicatedTagCheck(hashSet, parseSplitPairRule);
                                        splitPairRule = parseSplitPairRule;
                                        break;
                                    } else {
                                        throw new IllegalArgumentException("Found orphaned SplitPairFilter outside of SplitPairRule");
                                    }
                                }
                                break;
                            case 1793077963:
                                if (name.equals("ActivityRule")) {
                                    plus$window_release2 = parseActivityRule(context, xml);
                                    addRuleWithDuplicatedTagCheck(hashSet, plus$window_release2);
                                    splitPairRule = null;
                                    splitPlaceholderRule = null;
                                    activityRule = plus$window_release2;
                                    break;
                                }
                                break;
                            case 2050988213:
                                if (name.equals("SplitPlaceholderRule")) {
                                    plus$window_release = parseSplitPlaceholderRule(context, xml);
                                    addRuleWithDuplicatedTagCheck(hashSet, plus$window_release);
                                    activityRule = null;
                                    splitPairRule = null;
                                    splitPlaceholderRule = plus$window_release;
                                    break;
                                }
                                break;
                        }
                    }
                    next = xml.next();
                }
            }
            return hashSet;
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    private final void addRuleWithDuplicatedTagCheck(HashSet<EmbeddingRule> hashSet, EmbeddingRule embeddingRule) {
        String tag = embeddingRule.getTag();
        for (EmbeddingRule embeddingRule2 : hashSet) {
            if (tag != null && Intrinsics.areEqual(tag, embeddingRule2.getTag())) {
                throw new IllegalArgumentException("Duplicated tag: " + tag + " for " + embeddingRule + ". The tag must be unique in XML rule definition.");
            }
        }
        hashSet.add(embeddingRule);
    }

    private final SplitPairRule parseSplitPairRule(Context context, XmlResourceParser xmlResourceParser) {
        Set emptySet;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(xmlResourceParser, R.styleable.SplitPairRule, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.SplitPairRule_tag);
        float f = obtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitRatio, 0.5f);
        int integer = obtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinWidthDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        int integer2 = obtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinHeightDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        int integer3 = obtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinSmallestWidthDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        float f2 = obtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitMaxAspectRatioInPortrait, SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT.getValue$window_release());
        float f3 = obtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitMaxAspectRatioInLandscape, SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT.getValue$window_release());
        int i = obtainStyledAttributes.getInt(R.styleable.SplitPairRule_splitLayoutDirection, SplitAttributes.LayoutDirection.LOCALE.getValue$window_release());
        int i2 = obtainStyledAttributes.getInt(R.styleable.SplitPairRule_finishPrimaryWithSecondary, SplitRule.FinishBehavior.NEVER.getValue$window_release());
        int i3 = obtainStyledAttributes.getInt(R.styleable.SplitPairRule_finishSecondaryWithPrimary, SplitRule.FinishBehavior.ALWAYS.getValue$window_release());
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.SplitPairRule_clearTop, false);
        int color = obtainStyledAttributes.getColor(R.styleable.SplitPairRule_animationBackgroundColor, SplitAttributes.BackgroundColor.DEFAULT.getValue$window_release());
        obtainStyledAttributes.recycle();
        SplitAttributes build = new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.Companion.buildSplitTypeFromValue$window_release(f)).setLayoutDirection(SplitAttributes.LayoutDirection.Companion.getLayoutDirectionFromValue$window_release(i)).setAnimationBackgroundColor(SplitAttributes.BackgroundColor.Companion.buildFromValue$window_release(color)).build();
        emptySet = SetsKt__SetsKt.emptySet();
        SplitPairRule.Builder minSmallestWidthDp = new SplitPairRule.Builder(emptySet).setTag(string).setMinWidthDp(integer).setMinHeightDp(integer2).setMinSmallestWidthDp(integer3);
        EmbeddingAspectRatio.Companion companion = EmbeddingAspectRatio.Companion;
        SplitPairRule.Builder maxAspectRatioInLandscape = minSmallestWidthDp.setMaxAspectRatioInPortrait(companion.buildAspectRatioFromValue$window_release(f2)).setMaxAspectRatioInLandscape(companion.buildAspectRatioFromValue$window_release(f3));
        SplitRule.FinishBehavior.Companion companion2 = SplitRule.FinishBehavior.Companion;
        return maxAspectRatioInLandscape.setFinishPrimaryWithSecondary(companion2.getFinishBehaviorFromValue$window_release(i2)).setFinishSecondaryWithPrimary(companion2.getFinishBehaviorFromValue$window_release(i3)).setClearTop(z).setDefaultSplitAttributes(build).build();
    }

    private final SplitPlaceholderRule parseSplitPlaceholderRule(Context context, XmlResourceParser xmlResourceParser) {
        Set emptySet;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(xmlResourceParser, R.styleable.SplitPlaceholderRule, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.SplitPlaceholderRule_tag);
        String string2 = obtainStyledAttributes.getString(R.styleable.SplitPlaceholderRule_placeholderActivityName);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.SplitPlaceholderRule_stickyPlaceholder, false);
        int i = obtainStyledAttributes.getInt(R.styleable.SplitPlaceholderRule_finishPrimaryWithPlaceholder, SplitRule.FinishBehavior.ALWAYS.getValue$window_release());
        if (i == SplitRule.FinishBehavior.NEVER.getValue$window_release()) {
            throw new IllegalArgumentException("Never is not a valid configuration for Placeholder activities. Please use FINISH_ALWAYS or FINISH_ADJACENT instead or refer to the current API");
        }
        float f = obtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitRatio, 0.5f);
        int integer = obtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinWidthDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        int integer2 = obtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinHeightDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        int integer3 = obtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinSmallestWidthDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        float f2 = obtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitMaxAspectRatioInPortrait, SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT.getValue$window_release());
        float f3 = obtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitMaxAspectRatioInLandscape, SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT.getValue$window_release());
        int i2 = obtainStyledAttributes.getInt(R.styleable.SplitPlaceholderRule_splitLayoutDirection, SplitAttributes.LayoutDirection.LOCALE.getValue$window_release());
        int color = obtainStyledAttributes.getColor(R.styleable.SplitPlaceholderRule_animationBackgroundColor, SplitAttributes.BackgroundColor.DEFAULT.getValue$window_release());
        obtainStyledAttributes.recycle();
        SplitAttributes build = new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.Companion.buildSplitTypeFromValue$window_release(f)).setLayoutDirection(SplitAttributes.LayoutDirection.Companion.getLayoutDirectionFromValue$window_release(i2)).setAnimationBackgroundColor(SplitAttributes.BackgroundColor.Companion.buildFromValue$window_release(color)).build();
        String packageName = context.getApplicationContext().getPackageName();
        RuleParser ruleParser = INSTANCE;
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        ComponentName buildClassName = ruleParser.buildClassName(packageName, string2);
        emptySet = SetsKt__SetsKt.emptySet();
        Intent component = new Intent().setComponent(buildClassName);
        Intrinsics.checkNotNullExpressionValue(component, "Intent().setComponent(pl…eholderActivityClassName)");
        SplitPlaceholderRule.Builder minSmallestWidthDp = new SplitPlaceholderRule.Builder(emptySet, component).setTag(string).setMinWidthDp(integer).setMinHeightDp(integer2).setMinSmallestWidthDp(integer3);
        EmbeddingAspectRatio.Companion companion = EmbeddingAspectRatio.Companion;
        return minSmallestWidthDp.setMaxAspectRatioInPortrait(companion.buildAspectRatioFromValue$window_release(f2)).setMaxAspectRatioInLandscape(companion.buildAspectRatioFromValue$window_release(f3)).setSticky(z).setFinishPrimaryWithPlaceholder(SplitRule.FinishBehavior.Companion.getFinishBehaviorFromValue$window_release(i)).setDefaultSplitAttributes(build).build();
    }

    private final SplitPairFilter parseSplitPairFilter(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(xmlResourceParser, R.styleable.SplitPairFilter, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.SplitPairFilter_primaryActivityName);
        String string2 = obtainStyledAttributes.getString(R.styleable.SplitPairFilter_secondaryActivityName);
        String string3 = obtainStyledAttributes.getString(R.styleable.SplitPairFilter_secondaryActivityAction);
        String packageName = context.getApplicationContext().getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        return new SplitPairFilter(buildClassName(packageName, string), buildClassName(packageName, string2), string3);
    }

    private final ActivityRule parseActivityRule(Context context, XmlResourceParser xmlResourceParser) {
        Set emptySet;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(xmlResourceParser, R.styleable.ActivityRule, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.ActivityRule_tag);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.ActivityRule_alwaysExpand, false);
        obtainStyledAttributes.recycle();
        emptySet = SetsKt__SetsKt.emptySet();
        ActivityRule.Builder alwaysExpand = new ActivityRule.Builder(emptySet).setAlwaysExpand(z);
        if (string != null) {
            alwaysExpand.setTag(string);
        }
        return alwaysExpand.build();
    }

    private final ActivityFilter parseActivityFilter(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(xmlResourceParser, R.styleable.ActivityFilter, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.ActivityFilter_activityName);
        String string2 = obtainStyledAttributes.getString(R.styleable.ActivityFilter_activityAction);
        String packageName = context.getApplicationContext().getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        return new ActivityFilter(buildClassName(packageName, string), string2);
    }

    private final ComponentName buildClassName(String str, CharSequence charSequence) {
        int indexOf$default;
        int indexOf$default2;
        if (charSequence == null || charSequence.length() == 0) {
            throw new IllegalArgumentException("Activity name must not be null");
        }
        String obj = charSequence.toString();
        if (obj.charAt(0) == '.') {
            return new ComponentName(str, str + obj);
        }
        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) obj, '/', 0, false, 6, (Object) null);
        if (indexOf$default > 0) {
            str = obj.substring(0, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            obj = obj.substring(indexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(obj, "this as java.lang.String).substring(startIndex)");
        }
        if (!Intrinsics.areEqual(obj, "*")) {
            indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) obj, '.', 0, false, 6, (Object) null);
            if (indexOf$default2 < 0) {
                return new ComponentName(str, str + '.' + obj);
            }
        }
        return new ComponentName(str, obj);
    }
}
