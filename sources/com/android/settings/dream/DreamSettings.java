package com.android.settings.dream;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.dream.DreamPickerController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.dream.DreamBackend;
import com.android.settingslib.widget.MainSwitchPreference;
import com.android.settingslib.widget.OnMainSwitchChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
/* loaded from: classes.dex */
public class DreamSettings extends DashboardFragment implements OnMainSwitchChangeListener {
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new SearchIndexProvider(R.xml.dream_fragment_overview);
    private final DreamPickerController.Callback mCallback = new DreamPickerController.Callback() { // from class: com.android.settings.dream.DreamSettings$$ExternalSyntheticLambda0
        @Override // com.android.settings.dream.DreamPickerController.Callback
        public final void onActiveDreamChanged() {
            DreamSettings.this.updateComplicationsToggleVisibility();
        }
    };
    private Preference mComplicationsTogglePreference;
    private DreamPickerController mDreamPickerController;
    private MainSwitchPreference mMainSwitchPreference;
    private Button mPreviewButton;
    private RecyclerView mRecyclerView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getKeyFromSetting(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "never" : "either_charging_or_docked" : "while_docked_only" : "while_charging_only";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return "DreamSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 47;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getSettingFromPrefKey(String str) {
        char c;
        switch (str.hashCode()) {
            case -1592701525:
                if (str.equals("while_docked_only")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -294641318:
                if (str.equals("either_charging_or_docked")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 104712844:
                if (str.equals("never")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1019349036:
                if (str.equals("while_charging_only")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                return c != 2 ? 3 : 2;
            }
            return 1;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getDreamSettingDescriptionResId(int i, boolean z) {
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    return R.string.screensaver_settings_summary_either_long;
                }
                return R.string.screensaver_settings_summary_never;
            } else if (z) {
                return R.string.screensaver_settings_summary_dock;
            } else {
                return R.string.screensaver_settings_summary_dock_and_charging;
            }
        }
        return R.string.screensaver_settings_summary_sleep;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.dream_fragment_overview;
    }

    @Override // com.android.settings.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_screen_saver;
    }

    @Override // com.android.settings.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        if (this.mDreamPickerController == null) {
            this.mDreamPickerController = new DreamPickerController(context);
        }
        arrayList.add(this.mDreamPickerController);
        arrayList.add(new WhenToDreamPreferenceController(context));
        return arrayList;
    }

    public static CharSequence getSummaryTextWithDreamName(Context context) {
        return getSummaryTextFromBackend(DreamBackend.getInstance(context), context);
    }

    static CharSequence getSummaryTextFromBackend(DreamBackend dreamBackend, Context context) {
        if (dreamBackend.isEnabled()) {
            return context.getString(R.string.screensaver_settings_summary_on, dreamBackend.getActiveDreamName());
        }
        return context.getString(R.string.screensaver_settings_summary_off);
    }

    void setDreamPickerController(DreamPickerController dreamPickerController) {
        this.mDreamPickerController = dreamPickerController;
    }

    private void setAllPreferencesEnabled(final boolean z) {
        getPreferenceControllers().forEach(new Consumer() { // from class: com.android.settings.dream.DreamSettings$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DreamSettings.this.lambda$setAllPreferencesEnabled$1(z, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAllPreferencesEnabled$1(final boolean z, List list) {
        list.forEach(new Consumer() { // from class: com.android.settings.dream.DreamSettings$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DreamSettings.this.lambda$setAllPreferencesEnabled$0(z, (AbstractPreferenceController) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAllPreferencesEnabled$0(boolean z, AbstractPreferenceController abstractPreferenceController) {
        Preference findPreference;
        String preferenceKey = abstractPreferenceController.getPreferenceKey();
        if (preferenceKey.equals("dream_main_settings_switch") || (findPreference = findPreference(preferenceKey)) == null) {
            return;
        }
        findPreference.setEnabled(z);
        abstractPreferenceController.updateState(findPreference);
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DreamBackend dreamBackend = DreamBackend.getInstance(getContext());
        this.mComplicationsTogglePreference = findPreference(DreamComplicationPreferenceController.PREF_KEY);
        updateComplicationsToggleVisibility();
        MainSwitchPreference mainSwitchPreference = (MainSwitchPreference) findPreference("dream_main_settings_switch");
        this.mMainSwitchPreference = mainSwitchPreference;
        if (mainSwitchPreference != null) {
            mainSwitchPreference.addOnSwitchChangeListener(this);
        }
        setAllPreferencesEnabled(dreamBackend.isEnabled());
        DreamPickerController dreamPickerController = this.mDreamPickerController;
        if (dreamPickerController != null) {
            dreamPickerController.addCallback(this.mCallback);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        DreamPickerController dreamPickerController = this.mDreamPickerController;
        if (dreamPickerController != null) {
            dreamPickerController.removeCallback(this.mCallback);
        }
        super.onDestroy();
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final DreamBackend dreamBackend = DreamBackend.getInstance(getContext());
        ViewGroup viewGroup2 = (ViewGroup) getActivity().findViewById(16908290);
        Button button = (Button) getActivity().getLayoutInflater().inflate(R.layout.dream_preview_button, viewGroup2, false);
        this.mPreviewButton = button;
        button.setVisibility(dreamBackend.isEnabled() ? 0 : 8);
        viewGroup2.addView(this.mPreviewButton);
        this.mPreviewButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.dream.DreamSettings$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DreamSettings.lambda$onCreateRecyclerView$2(DreamBackend.this, view);
            }
        });
        RecyclerView onCreateRecyclerView = super.onCreateRecyclerView(layoutInflater, viewGroup, bundle);
        this.mRecyclerView = onCreateRecyclerView;
        onCreateRecyclerView.setFocusable(false);
        updatePaddingForPreviewButton();
        return this.mRecyclerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreateRecyclerView$2(DreamBackend dreamBackend, View view) {
        dreamBackend.preview(dreamBackend.getActiveDream());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateComplicationsToggleVisibility() {
        DreamPickerController dreamPickerController = this.mDreamPickerController;
        if (dreamPickerController == null || this.mComplicationsTogglePreference == null) {
            return;
        }
        DreamBackend.DreamInfo activeDreamInfo = dreamPickerController.getActiveDreamInfo();
        this.mComplicationsTogglePreference.setVisible(activeDreamInfo != null && activeDreamInfo.supportsComplications);
    }

    private void updatePaddingForPreviewButton() {
        this.mPreviewButton.post(new Runnable() { // from class: com.android.settings.dream.DreamSettings$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DreamSettings.this.lambda$updatePaddingForPreviewButton$3();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePaddingForPreviewButton$3() {
        this.mRecyclerView.setPadding(0, 0, 0, this.mPreviewButton.getMeasuredHeight());
    }

    @Override // com.android.settingslib.widget.OnMainSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        setAllPreferencesEnabled(z);
        this.mPreviewButton.setVisibility(z ? 0 : 8);
        updatePaddingForPreviewButton();
    }

    /* loaded from: classes.dex */
    static class SearchIndexProvider extends BaseSearchIndexProvider {
        SearchIndexProvider(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.settings.search.BaseSearchIndexProvider
        public boolean isPageSearchEnabled(Context context) {
            return Utils.areDreamsAvailableToCurrentUser(context);
        }
    }
}
