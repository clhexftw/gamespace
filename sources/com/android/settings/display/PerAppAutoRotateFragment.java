package com.android.settings.display;

import android.os.Bundle;
import com.android.settings.R;
import com.android.settings.fragment.PerAppConfigFragment;
import java.util.ArrayList;
import java.util.List;
import org.nameless.wm.RotateManager;
/* loaded from: classes.dex */
public class PerAppAutoRotateFragment extends PerAppConfigFragment {
    private List<String> mEntries;
    private RotateManager mRotateManager;
    private List<Integer> mValues;

    @Override // com.android.settings.fragment.PerAppConfigFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRotateManager = (RotateManager) getActivity().getSystemService(RotateManager.class);
        ArrayList arrayList = new ArrayList();
        this.mEntries = arrayList;
        arrayList.add(getString(R.string.per_app_auto_rotate_default));
        this.mEntries.add(getString(R.string.per_app_auto_rotate_off));
        this.mEntries.add(getString(R.string.per_app_auto_rotate_on));
        ArrayList arrayList2 = new ArrayList();
        this.mValues = arrayList2;
        arrayList2.add(0);
        this.mValues.add(1);
        this.mValues.add(2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.per_app_auto_rotate_config;
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
        return this.mRotateManager.getRotateConfigForPackage(str);
    }

    @Override // com.android.settings.fragment.PerAppConfigFragment
    protected void onValueChanged(String str, int i) {
        this.mRotateManager.setRotateConfigForPackage(str, i);
    }
}
