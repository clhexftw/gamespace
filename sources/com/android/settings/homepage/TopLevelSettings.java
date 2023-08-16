package com.android.settings.homepage;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;
import androidx.window.embedding.ActivityEmbeddingController;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.activityembedding.ActivityEmbeddingRulesController;
import com.android.settings.activityembedding.ActivityEmbeddingUtils;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.support.SupportPreferenceController;
import com.android.settings.widget.HomepagePreference;
import com.android.settings.widget.HomepagePreferenceLayoutHelper;
import com.android.settingslib.core.instrumentation.Instrumentable;
import com.android.settingslib.drawer.Tile;
/* loaded from: classes.dex */
public class TopLevelSettings extends DashboardFragment implements SplitLayoutListener, PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider(R.xml.top_level_settings) { // from class: com.android.settings.homepage.TopLevelSettings.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.settings.search.BaseSearchIndexProvider
        public boolean isPageSearchEnabled(Context context) {
            return false;
        }
    };
    private ActivityEmbeddingController mActivityEmbeddingController;
    private TopLevelHighlightMixin mHighlightMixin;
    private boolean mIsEmbeddingActivityEnabled;
    private int mPaddingHorizontal;
    private boolean mScrollNeeded = true;
    private boolean mFirstStarted = true;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface PreferenceJob {
        void doForEach(Preference preference);

        default void init() {
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public Fragment getCallbackFragment() {
        return this;
    }

    @Override // com.android.settings.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return "TopLevelSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 35;
    }

    public TopLevelSettings() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("need_search_icon_in_action_bar", false);
        setArguments(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.top_level_settings;
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        HighlightableMenu.fromXml(context, getPreferenceScreenResId());
        ((SupportPreferenceController) use(SupportPreferenceController.class)).setActivity(getActivity());
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (isDuplicateClick(preference)) {
            return true;
        }
        ActivityEmbeddingRulesController.registerSubSettingsPairRule(getContext(), true);
        setHighlightPreferenceKey(preference.getKey());
        return super.onPreferenceTreeClick(preference);
    }

    @Override // androidx.preference.PreferenceFragmentCompat.OnPreferenceStartFragmentCallback
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference) {
        new SubSettingLauncher(getActivity()).setDestination(preference.getFragment()).setArguments(preference.getExtras()).setSourceMetricsCategory(preferenceFragmentCompat instanceof Instrumentable ? ((Instrumentable) preferenceFragmentCompat).getMetricsCategory() : 0).setTitleRes(-1).setIsSecondLayerPage(true).launch();
        return true;
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean isEmbeddingActivityEnabled = ActivityEmbeddingUtils.isEmbeddingActivityEnabled(getContext());
        this.mIsEmbeddingActivityEnabled = isEmbeddingActivityEnabled;
        if (isEmbeddingActivityEnabled) {
            boolean isActivityEmbedded = isActivityEmbedded();
            if (bundle != null) {
                TopLevelHighlightMixin topLevelHighlightMixin = (TopLevelHighlightMixin) bundle.getParcelable("highlight_mixin");
                this.mHighlightMixin = topLevelHighlightMixin;
                this.mScrollNeeded = !topLevelHighlightMixin.isActivityEmbedded() && isActivityEmbedded;
                this.mHighlightMixin.setActivityEmbedded(isActivityEmbedded);
            }
            if (this.mHighlightMixin == null) {
                this.mHighlightMixin = new TopLevelHighlightMixin(isActivityEmbedded);
            }
        }
    }

    public boolean isActivityEmbedded() {
        if (this.mActivityEmbeddingController == null) {
            this.mActivityEmbeddingController = ActivityEmbeddingController.getInstance(getActivity());
        }
        return this.mActivityEmbeddingController.isActivityEmbedded(getActivity());
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        if (this.mFirstStarted) {
            this.mFirstStarted = false;
            FeatureFactory.getFactory(getContext()).getSearchFeatureProvider().sendPreIndexIntent(getContext());
        } else if (this.mIsEmbeddingActivityEnabled && isOnlyOneActivityInTask() && !isActivityEmbedded()) {
            Log.i("TopLevelSettings", "Set default menu key");
            setHighlightMenuKey(getString(SettingsHomepageActivity.DEFAULT_HIGHLIGHT_MENU_KEY), false);
        }
        super.onStart();
    }

    private boolean isOnlyOneActivityInTask() {
        return ((ActivityManager) getSystemService(ActivityManager.class)).getRunningTasks(1).get(0).numActivities == 1;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        TopLevelHighlightMixin topLevelHighlightMixin = this.mHighlightMixin;
        if (topLevelHighlightMixin != null) {
            bundle.putParcelable("highlight_mixin", topLevelHighlightMixin);
        }
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        final int homepageIconColor = Utils.getHomepageIconColor(getContext());
        iteratePreferences(new PreferenceJob() { // from class: com.android.settings.homepage.TopLevelSettings$$ExternalSyntheticLambda0
            @Override // com.android.settings.homepage.TopLevelSettings.PreferenceJob
            public final void doForEach(Preference preference) {
                TopLevelSettings.lambda$onCreatePreferences$0(homepageIconColor, preference);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreatePreferences$0(int i, Preference preference) {
        Drawable icon = preference.getIcon();
        if (icon != null) {
            icon.setTint(i);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        highlightPreferenceIfNeeded();
    }

    @Override // com.android.settings.homepage.SplitLayoutListener
    public void onSplitLayoutChanged(final boolean z) {
        iteratePreferences(new PreferenceJob() { // from class: com.android.settings.homepage.TopLevelSettings$$ExternalSyntheticLambda1
            @Override // com.android.settings.homepage.TopLevelSettings.PreferenceJob
            public final void doForEach(Preference preference) {
                TopLevelSettings.lambda$onSplitLayoutChanged$1(z, preference);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onSplitLayoutChanged$1(boolean z, Preference preference) {
        if (preference instanceof HomepagePreferenceLayoutHelper.HomepagePreferenceLayout) {
            ((HomepagePreferenceLayoutHelper.HomepagePreferenceLayout) preference).getHelper().setIconVisible(z);
        }
    }

    @Override // com.android.settings.SettingsPreferenceFragment
    public void highlightPreferenceIfNeeded() {
        TopLevelHighlightMixin topLevelHighlightMixin = this.mHighlightMixin;
        if (topLevelHighlightMixin != null) {
            topLevelHighlightMixin.highlightPreferenceIfNeeded();
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView onCreateRecyclerView = super.onCreateRecyclerView(layoutInflater, viewGroup, bundle);
        int i = this.mPaddingHorizontal;
        onCreateRecyclerView.setPadding(i, 0, i, 0);
        return onCreateRecyclerView;
    }

    public void setPaddingHorizontal(int i) {
        this.mPaddingHorizontal = i;
        RecyclerView listView = getListView();
        if (listView != null) {
            listView.setPadding(i, 0, i, 0);
        }
    }

    public void updatePreferencePadding(final boolean z) {
        iteratePreferences(new PreferenceJob() { // from class: com.android.settings.homepage.TopLevelSettings.1
            private int mIconPaddingStart;
            private int mTextPaddingStart;

            @Override // com.android.settings.homepage.TopLevelSettings.PreferenceJob
            public void init() {
                int i;
                int i2;
                Resources resources = TopLevelSettings.this.getResources();
                if (z) {
                    i = R.dimen.homepage_preference_icon_padding_start_two_pane;
                } else {
                    i = R.dimen.homepage_preference_icon_padding_start;
                }
                this.mIconPaddingStart = resources.getDimensionPixelSize(i);
                Resources resources2 = TopLevelSettings.this.getResources();
                if (z) {
                    i2 = R.dimen.homepage_preference_text_padding_start_two_pane;
                } else {
                    i2 = R.dimen.homepage_preference_text_padding_start;
                }
                this.mTextPaddingStart = resources2.getDimensionPixelSize(i2);
            }

            @Override // com.android.settings.homepage.TopLevelSettings.PreferenceJob
            public void doForEach(Preference preference) {
                if (preference instanceof HomepagePreferenceLayoutHelper.HomepagePreferenceLayout) {
                    HomepagePreferenceLayoutHelper.HomepagePreferenceLayout homepagePreferenceLayout = (HomepagePreferenceLayoutHelper.HomepagePreferenceLayout) preference;
                    homepagePreferenceLayout.getHelper().setIconPaddingStart(this.mIconPaddingStart);
                    homepagePreferenceLayout.getHelper().setTextPaddingStart(this.mTextPaddingStart);
                }
            }
        });
    }

    public TopLevelHighlightMixin getHighlightMixin() {
        return this.mHighlightMixin;
    }

    public void setHighlightPreferenceKey(String str) {
        if (this.mHighlightMixin == null || TextUtils.equals(str, "top_level_support")) {
            return;
        }
        this.mHighlightMixin.setHighlightPreferenceKey(str);
    }

    public boolean isDuplicateClick(Preference preference) {
        return this.mHighlightMixin != null && TextUtils.equals(preference.getKey(), this.mHighlightMixin.getHighlightPreferenceKey()) && isActivityEmbedded();
    }

    public void setMenuHighlightShowed(boolean z) {
        TopLevelHighlightMixin topLevelHighlightMixin = this.mHighlightMixin;
        if (topLevelHighlightMixin != null) {
            topLevelHighlightMixin.setMenuHighlightShowed(z);
        }
    }

    public void setHighlightMenuKey(String str, boolean z) {
        TopLevelHighlightMixin topLevelHighlightMixin = this.mHighlightMixin;
        if (topLevelHighlightMixin != null) {
            topLevelHighlightMixin.setHighlightMenuKey(str, z);
        }
    }

    @Override // com.android.settings.dashboard.DashboardFragment
    protected boolean shouldForceRoundedIcon() {
        return getContext().getResources().getBoolean(R.bool.config_force_rounded_icon_TopLevelSettings);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        if (!this.mIsEmbeddingActivityEnabled || !(getActivity() instanceof SettingsHomepageActivity)) {
            return super.onCreateAdapter(preferenceScreen);
        }
        return this.mHighlightMixin.onCreateAdapter(this, preferenceScreen, this.mScrollNeeded);
    }

    @Override // com.android.settings.dashboard.DashboardFragment
    protected Preference createPreference(Tile tile) {
        return new HomepagePreference(getPrefContext());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reloadHighlightMenuKey() {
        TopLevelHighlightMixin topLevelHighlightMixin = this.mHighlightMixin;
        if (topLevelHighlightMixin != null) {
            topLevelHighlightMixin.reloadHighlightMenuKey(getArguments());
        }
    }

    private void iteratePreferences(PreferenceJob preferenceJob) {
        PreferenceScreen preferenceScreen;
        if (preferenceJob == null || getPreferenceManager() == null || (preferenceScreen = getPreferenceScreen()) == null) {
            return;
        }
        preferenceJob.init();
        int preferenceCount = preferenceScreen.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceScreen.getPreference(i);
            if (preference == null) {
                return;
            }
            preferenceJob.doForEach(preference);
        }
    }
}
