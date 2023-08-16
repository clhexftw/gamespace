package com.android.settings.display.refreshrate;

import android.os.Bundle;
import com.android.settings.R;
import com.android.settings.fragment.PerAppConfigFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.nameless.display.DisplayRefreshRateHelper;
import org.nameless.display.RefreshRateManager;
/* loaded from: classes.dex */
public class PerAppRefreshRateFragment extends PerAppConfigFragment {
    private List<String> mEntries;
    private DisplayRefreshRateHelper mHelper;
    private RefreshRateManager mRefreshRateManager;
    private List<Integer> mValues;

    @Override // com.android.settings.fragment.PerAppConfigFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHelper = DisplayRefreshRateHelper.getInstance(getActivity());
        this.mRefreshRateManager = (RefreshRateManager) getActivity().getSystemService(RefreshRateManager.class);
        ArrayList arrayList = new ArrayList();
        this.mEntries = arrayList;
        arrayList.add(getString(R.string.per_app_refresh_rate_default));
        ArrayList arrayList2 = new ArrayList();
        this.mValues = arrayList2;
        arrayList2.add(-1);
        Iterator it = this.mHelper.getSupportedRefreshRateList().iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            List<String> list = this.mEntries;
            list.add(String.valueOf(intValue) + " Hz");
            this.mValues.add(Integer.valueOf(intValue));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.per_app_refresh_rate_config;
    }

    @Override // com.android.settings.fragment.PerAppConfigFragment
    protected List<String> getEntries() {
        return this.mEntries;
    }

    @Override // com.android.settings.fragment.PerAppConfigFragment
    protected List<Integer> getValues() {
        return this.mValues;
    }

    @Override // com.android.settings.fragment.PerAppConfigFragment
    protected int getCurrentValue(String str) {
        return this.mRefreshRateManager.getRefreshRateForPackage(str);
    }

    @Override // com.android.settings.fragment.PerAppConfigFragment
    protected void onValueChanged(String str, int i) {
        if (i > 0) {
            this.mRefreshRateManager.setRefreshRateForPackage(str, i);
        } else {
            this.mRefreshRateManager.unsetRefreshRateForPackage(str);
        }
    }
}
