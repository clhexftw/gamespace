package com.android.settings.display.refreshrate;

import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import java.util.ArrayList;
import org.nameless.display.DisplayRefreshRateHelper;
/* loaded from: classes.dex */
public class PeakRefreshRatePreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private final DisplayRefreshRateHelper mHelper;
    private ListPreference mListPreference;
    private final ArrayList<Integer> mSupportedList;

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public PeakRefreshRatePreferenceController(Context context, String str) {
        super(context, str);
        DisplayRefreshRateHelper displayRefreshRateHelper = DisplayRefreshRateHelper.getInstance(context);
        this.mHelper = displayRefreshRateHelper;
        this.mSupportedList = displayRefreshRateHelper.getSupportedRefreshRateList();
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mSupportedList.size() > 1 ? 0 : 3;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mListPreference = (ListPreference) preferenceScreen.findPreference(getPreferenceKey());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < this.mSupportedList.size(); i++) {
            String valueOf = String.valueOf(this.mSupportedList.get(i));
            arrayList.add(valueOf + " Hz");
            arrayList2.add(valueOf);
        }
        this.mListPreference.setEntries((CharSequence[]) arrayList.toArray(new String[arrayList.size()]));
        this.mListPreference.setEntryValues((CharSequence[]) arrayList2.toArray(new String[arrayList2.size()]));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        String valueOf = String.valueOf(this.mHelper.getPeakRefreshRate());
        int findIndexOfValue = this.mListPreference.findIndexOfValue(valueOf);
        if (findIndexOfValue != -1) {
            this.mListPreference.setValueIndex(findIndexOfValue);
            ListPreference listPreference = this.mListPreference;
            listPreference.setSummary(listPreference.getEntries()[findIndexOfValue]);
        } else {
            ListPreference listPreference2 = this.mListPreference;
            listPreference2.setSummary(valueOf + " Hz");
        }
        int parseInt = Integer.parseInt(valueOf);
        if (this.mHelper.getMinimumRefreshRate() > parseInt) {
            this.mHelper.setMinimumRefreshRate(parseInt);
        }
        this.mListPreference.setEnabled(!(Settings.System.getIntForUser(this.mContext.getContentResolver(), "extreme_refresh_rate", 0, -2) != 0));
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mHelper.setPeakRefreshRate(Integer.parseInt(String.valueOf(obj)));
        updateState(preference);
        return true;
    }
}
