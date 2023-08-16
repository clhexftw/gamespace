package com.android.settings.activityembedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.FeatureFlagUtils;
import android.util.Log;
import androidx.window.embedding.ActivityFilter;
import androidx.window.embedding.ActivityRule;
import androidx.window.embedding.EmbeddingAspectRatio;
import androidx.window.embedding.RuleController;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitPairFilter;
import androidx.window.embedding.SplitPairRule;
import androidx.window.embedding.SplitPlaceholderRule;
import androidx.window.embedding.SplitRule;
import com.android.settings.Settings;
import com.android.settings.SubSettings;
import com.android.settings.biometrics.face.FaceEnrollIntroduction;
import com.android.settings.biometrics.face.FaceEnrollIntroductionInternal;
import com.android.settings.biometrics.fingerprint.FingerprintEnrollEnrolling;
import com.android.settings.biometrics.fingerprint.FingerprintEnrollIntroduction;
import com.android.settings.biometrics.fingerprint.FingerprintEnrollIntroductionInternal;
import com.android.settings.homepage.DeepLinkHomepageActivity;
import com.android.settings.homepage.DeepLinkHomepageActivityInternal;
import com.android.settings.homepage.SettingsHomepageActivity;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.password.ChooseLockPattern;
import com.android.settingslib.users.AvatarPickerActivity;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class ActivityEmbeddingRulesController {
    private static final ComponentName COMPONENT_NAME_WILDCARD = new ComponentName("*", "*");
    private final Context mContext;
    private final RuleController mRuleController;

    public ActivityEmbeddingRulesController(Context context) {
        this.mContext = context;
        this.mRuleController = RuleController.getInstance(context);
    }

    public void initRules() {
        if (!ActivityEmbeddingUtils.isEmbeddingActivityEnabled(this.mContext)) {
            Log.d("ActivityEmbeddingCtrl", "Not support this feature now");
            return;
        }
        this.mRuleController.clearRules();
        registerHomepagePlaceholderRule();
        registerAlwaysExpandRule();
    }

    public static void registerTwoPanePairRule(Context context, ComponentName componentName, ComponentName componentName2, String str, SplitRule.FinishBehavior finishBehavior, SplitRule.FinishBehavior finishBehavior2, boolean z) {
        if (ActivityEmbeddingUtils.isEmbeddingActivityEnabled(context)) {
            HashSet hashSet = new HashSet();
            hashSet.add(new SplitPairFilter(componentName, componentName2, str));
            RuleController.getInstance(context).addRule(new SplitPairRule.Builder(hashSet).setFinishPrimaryWithSecondary(finishBehavior).setFinishSecondaryWithPrimary(finishBehavior2).setClearTop(z).setMinWidthDp(ActivityEmbeddingUtils.getMinCurrentScreenSplitWidthDp()).setMinSmallestWidthDp(ActivityEmbeddingUtils.getMinSmallestScreenSplitWidthDp()).setMaxAspectRatioInPortrait(EmbeddingAspectRatio.ALWAYS_ALLOW).setDefaultSplitAttributes(new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.ratio(ActivityEmbeddingUtils.getSplitRatio(context))).setLayoutDirection(SplitAttributes.LayoutDirection.LOCALE).build()).build());
        }
    }

    public static void registerTwoPanePairRuleForSettingsHome(Context context, ComponentName componentName, String str, boolean z, boolean z2, boolean z3) {
        SplitRule.FinishBehavior finishBehavior;
        SplitRule.FinishBehavior finishBehavior2;
        SplitRule.FinishBehavior finishBehavior3;
        SplitRule.FinishBehavior finishBehavior4;
        SplitRule.FinishBehavior finishBehavior5;
        SplitRule.FinishBehavior finishBehavior6;
        SplitRule.FinishBehavior finishBehavior7;
        SplitRule.FinishBehavior finishBehavior8;
        if (ActivityEmbeddingUtils.isEmbeddingActivityEnabled(context)) {
            ComponentName componentName2 = new ComponentName(context, Settings.class);
            if (z) {
                finishBehavior = SplitRule.FinishBehavior.ADJACENT;
            } else {
                finishBehavior = SplitRule.FinishBehavior.NEVER;
            }
            SplitRule.FinishBehavior finishBehavior9 = finishBehavior;
            if (z2) {
                finishBehavior2 = SplitRule.FinishBehavior.ADJACENT;
            } else {
                finishBehavior2 = SplitRule.FinishBehavior.NEVER;
            }
            registerTwoPanePairRule(context, componentName2, componentName, str, finishBehavior9, finishBehavior2, z3);
            ComponentName componentName3 = new ComponentName(context, SettingsHomepageActivity.class);
            if (z) {
                finishBehavior3 = SplitRule.FinishBehavior.ADJACENT;
            } else {
                finishBehavior3 = SplitRule.FinishBehavior.NEVER;
            }
            SplitRule.FinishBehavior finishBehavior10 = finishBehavior3;
            if (z2) {
                finishBehavior4 = SplitRule.FinishBehavior.ADJACENT;
            } else {
                finishBehavior4 = SplitRule.FinishBehavior.NEVER;
            }
            registerTwoPanePairRule(context, componentName3, componentName, str, finishBehavior10, finishBehavior4, z3);
            ComponentName componentName4 = new ComponentName(context, DeepLinkHomepageActivity.class);
            if (z) {
                finishBehavior5 = SplitRule.FinishBehavior.ALWAYS;
            } else {
                finishBehavior5 = SplitRule.FinishBehavior.NEVER;
            }
            SplitRule.FinishBehavior finishBehavior11 = finishBehavior5;
            if (z2) {
                finishBehavior6 = SplitRule.FinishBehavior.ALWAYS;
            } else {
                finishBehavior6 = SplitRule.FinishBehavior.NEVER;
            }
            registerTwoPanePairRule(context, componentName4, componentName, str, finishBehavior11, finishBehavior6, z3);
            ComponentName componentName5 = new ComponentName(context, DeepLinkHomepageActivityInternal.class);
            if (z) {
                finishBehavior7 = SplitRule.FinishBehavior.ALWAYS;
            } else {
                finishBehavior7 = SplitRule.FinishBehavior.NEVER;
            }
            SplitRule.FinishBehavior finishBehavior12 = finishBehavior7;
            if (z2) {
                finishBehavior8 = SplitRule.FinishBehavior.ALWAYS;
            } else {
                finishBehavior8 = SplitRule.FinishBehavior.NEVER;
            }
            registerTwoPanePairRule(context, componentName5, componentName, str, finishBehavior12, finishBehavior8, z3);
        }
    }

    public static void registerTwoPanePairRuleForSettingsHome(Context context, ComponentName componentName, String str, boolean z) {
        if (ActivityEmbeddingUtils.isEmbeddingActivityEnabled(context)) {
            registerTwoPanePairRuleForSettingsHome(context, componentName, str, true, true, z);
        }
    }

    public static void registerSubSettingsPairRule(Context context, boolean z) {
        if (ActivityEmbeddingUtils.isEmbeddingActivityEnabled(context)) {
            registerTwoPanePairRuleForSettingsHome(context, new ComponentName(context, SubSettings.class), null, z);
            registerTwoPanePairRuleForSettingsHome(context, COMPONENT_NAME_WILDCARD, "android.intent.action.SAFETY_CENTER", z);
        }
    }

    private void registerHomepagePlaceholderRule() {
        HashSet hashSet = new HashSet();
        addActivityFilter(hashSet, SettingsHomepageActivity.class);
        addActivityFilter(hashSet, Settings.class);
        Intent intent = new Intent(this.mContext, Settings.NetworkDashboardActivity.class);
        intent.putExtra(":settings:is_second_layer_page", true);
        this.mRuleController.addRule(new SplitPlaceholderRule.Builder(hashSet, intent).setMinWidthDp(ActivityEmbeddingUtils.getMinCurrentScreenSplitWidthDp()).setMinSmallestWidthDp(ActivityEmbeddingUtils.getMinSmallestScreenSplitWidthDp()).setMaxAspectRatioInPortrait(EmbeddingAspectRatio.ALWAYS_ALLOW).setSticky(false).setFinishPrimaryWithPlaceholder(SplitRule.FinishBehavior.ADJACENT).setDefaultSplitAttributes(new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.ratio(ActivityEmbeddingUtils.getSplitRatio(this.mContext))).build()).build());
    }

    private void registerAlwaysExpandRule() {
        HashSet hashSet = new HashSet();
        if (FeatureFlagUtils.isEnabled(this.mContext, "settings_search_always_expand")) {
            addActivityFilter(hashSet, FeatureFactory.getFactory(this.mContext).getSearchFeatureProvider().buildSearchIntent(this.mContext, 1502));
        }
        addActivityFilter(hashSet, FingerprintEnrollIntroduction.class);
        addActivityFilter(hashSet, FingerprintEnrollIntroductionInternal.class);
        addActivityFilter(hashSet, FingerprintEnrollEnrolling.class);
        addActivityFilter(hashSet, FaceEnrollIntroductionInternal.class);
        addActivityFilter(hashSet, FaceEnrollIntroduction.class);
        addActivityFilter(hashSet, AvatarPickerActivity.class);
        addActivityFilter(hashSet, ChooseLockPattern.class);
        this.mRuleController.addRule(new ActivityRule.Builder(hashSet).setAlwaysExpand(true).build());
    }

    private static void addActivityFilter(Set<ActivityFilter> set, Intent intent) {
        set.add(new ActivityFilter(COMPONENT_NAME_WILDCARD, intent.getAction()));
    }

    private void addActivityFilter(Collection<ActivityFilter> collection, Class<? extends Activity> cls) {
        collection.add(new ActivityFilter(new ComponentName(this.mContext, cls), (String) null));
    }
}
