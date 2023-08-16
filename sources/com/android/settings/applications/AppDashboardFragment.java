package com.android.settings.applications;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.internal.util.nameless.CustomUtils;
import com.android.internal.util.nameless.LauncherUtils;
import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class AppDashboardFragment extends DashboardFragment implements Preference.OnPreferenceChangeListener {
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.settings.applications.AppDashboardFragment.1
        @Override // com.android.settings.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.apps;
            return Arrays.asList(searchIndexableResource);
        }

        @Override // com.android.settings.search.BaseSearchIndexProvider
        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return AppDashboardFragment.buildPreferenceControllers(context);
        }
    };
    private AppsPreferenceController mAppsPreferenceController;
    private ListPreference mLauncherSwitcher;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return "AppDashboardFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 65;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AppsPreferenceController(context));
        return arrayList;
    }

    @Override // com.android.settings.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_apps_and_notifications;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.apps;
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((SpecialAppAccessPreferenceController) use(SpecialAppAccessPreferenceController.class)).setSession(getSettingsLifecycle());
        AppsPreferenceController appsPreferenceController = (AppsPreferenceController) use(AppsPreferenceController.class);
        this.mAppsPreferenceController = appsPreferenceController;
        appsPreferenceController.setFragment(this);
        getSettingsLifecycle().addObserver(this.mAppsPreferenceController);
        getSettingsLifecycle().addObserver((HibernatedAppsPreferenceController) use(HibernatedAppsPreferenceController.class));
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        ArrayList launcherList = LauncherUtils.getLauncherList(getContext());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = launcherList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (CustomUtils.isPackageInstalled(getContext(), str2)) {
                arrayList.add(str2);
                arrayList2.add(CustomUtils.getAppName(getContext(), str2));
            }
        }
        this.mLauncherSwitcher = (ListPreference) findPreference("launcher_switcher_preference");
        if (arrayList.size() == 0) {
            getPreferenceScreen().removePreference(getPreferenceScreen().findPreference("launcher_switcher_category"));
            return;
        }
        int size = arrayList.size();
        CharSequence[] charSequenceArr = new CharSequence[size];
        CharSequence[] charSequenceArr2 = new CharSequence[arrayList2.size()];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            charSequenceArr[i2] = (CharSequence) arrayList2.get(i2);
            charSequenceArr2[i2] = (CharSequence) arrayList.get(i2);
        }
        this.mLauncherSwitcher.setEntries(charSequenceArr);
        this.mLauncherSwitcher.setEntryValues(charSequenceArr2);
        String selectedLauncher = LauncherUtils.getSelectedLauncher();
        if (selectedLauncher.isEmpty()) {
            selectedLauncher = LauncherUtils.getResComponentName(getContext()).split("/")[0];
        }
        while (true) {
            if (i >= size) {
                str = selectedLauncher;
                break;
            } else if (charSequenceArr2[i].equals(selectedLauncher)) {
                str = charSequenceArr[i];
                break;
            } else {
                i++;
            }
        }
        this.mLauncherSwitcher.setValue(selectedLauncher);
        this.mLauncherSwitcher.setSummary(str);
        this.mLauncherSwitcher.setOnPreferenceChangeListener(this);
    }

    @Override // com.android.settings.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.preference.ListPreference] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r5v3 */
    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mLauncherSwitcher) {
            ?? r5 = (String) obj;
            LauncherUtils.setSelectedLauncher((String) r5);
            CharSequence[] entries = this.mLauncherSwitcher.getEntries();
            CharSequence[] entryValues = this.mLauncherSwitcher.getEntryValues();
            int i = 0;
            while (true) {
                if (i >= entries.length) {
                    break;
                } else if (entryValues[i].equals(r5)) {
                    r5 = entries[i];
                    break;
                } else {
                    i++;
                }
            }
            this.mLauncherSwitcher.setSummary(r5);
            return true;
        }
        return true;
    }
}
