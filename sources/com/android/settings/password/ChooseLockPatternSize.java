package com.android.settings.password;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.R;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.SetupWizardUtils;
import com.android.settings.utils.SettingsDividerItemDecoration;
import com.google.android.setupdesign.GlifPreferenceLayout;
/* loaded from: classes.dex */
public class ChooseLockPatternSize extends SettingsActivity {
    @Override // com.android.settings.core.SettingsBaseActivity
    protected boolean isToolbarEnabled() {
        return false;
    }

    @Override // com.android.settings.SettingsActivity, android.app.Activity
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", ChooseLockPatternSizeFragment.class.getName());
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.SettingsActivity, android.app.Activity, android.view.ContextThemeWrapper
    public void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, SetupWizardUtils.getTheme(this, getIntent()), z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.SettingsActivity
    public boolean isValidFragment(String str) {
        return ChooseLockPatternSizeFragment.class.getName().equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.SettingsActivity, com.android.settings.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        findViewById(R.id.content_parent).setFitsSystemWindows(false);
    }

    /* loaded from: classes.dex */
    public static class ChooseLockPatternSizeFragment extends SettingsPreferenceFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 1999;
        }

        @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            if (!(getActivity() instanceof ChooseLockPatternSize)) {
                throw new SecurityException("Fragment contained in wrong activity");
            }
            addPreferencesFromResource(R.xml.security_settings_pattern_size);
        }

        @Override // com.android.settings.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
        public boolean onPreferenceTreeClick(Preference preference) {
            byte b;
            String key = preference.getKey();
            if ("lock_pattern_size_4".equals(key)) {
                b = 4;
            } else if ("lock_pattern_size_5".equals(key)) {
                b = 5;
            } else {
                b = "lock_pattern_size_6".equals(key) ? (byte) 6 : (byte) 3;
            }
            Bundle extras = getActivity().getIntent().getExtras();
            Intent intent = new Intent();
            intent.setClassName(getActivity(), extras.getString("className"));
            intent.putExtras(extras);
            intent.putExtra("pattern_size", b);
            intent.addFlags(33554432);
            startActivity(intent);
            finish();
            return true;
        }

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            GlifPreferenceLayout glifPreferenceLayout = (GlifPreferenceLayout) view;
            glifPreferenceLayout.setDividerItemDecoration(new SettingsDividerItemDecoration(getContext()));
            glifPreferenceLayout.setIcon(getContext().getDrawable(R.drawable.ic_lock));
            if (getActivity() != null) {
                getActivity().setTitle(R.string.lock_settings_picker_pattern_size_message);
            }
            glifPreferenceLayout.setHeaderText(R.string.lock_settings_picker_pattern_size_message);
            setDivider(null);
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return ((GlifPreferenceLayout) viewGroup).onCreateRecyclerView(layoutInflater, viewGroup, bundle);
        }
    }
}
