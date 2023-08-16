package com.android.settings.fragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.widget.AppListPreference;
import com.android.settings.widget.EmptyTextSettings;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public abstract class PerAppConfigFragment extends EmptyTextSettings {
    private Context mContext;
    private PackageManager mPackageManager;

    protected abstract int getCurrentValue(String str);

    protected abstract List<String> getEntries();

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    protected abstract List<Integer> getValues();

    protected abstract void onValueChanged(String str, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class AppComparator implements Comparator<Pair<String, String>> {
        private final Collator mCollator = Collator.getInstance();

        AppComparator() {
        }

        @Override // java.util.Comparator
        public final int compare(Pair<String, String> pair, Pair<String, String> pair2) {
            String str = (String) pair.first;
            if (TextUtils.isEmpty(str)) {
                str = (String) pair.second;
            }
            String str2 = (String) pair2.first;
            if (TextUtils.isEmpty(str2)) {
                str2 = (String) pair2.second;
            }
            return this.mCollator.compare(str, str2);
        }
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        this.mPackageManager = activity.getPackageManager();
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        CharSequence charSequence;
        super.onResume();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        ArrayList<Pair<String, String>> collectApps = collectApps();
        Context prefContext = getPrefContext();
        Iterator<Pair<String, String>> it = collectApps.iterator();
        while (it.hasNext()) {
            final Pair<String, String> next = it.next();
            final AppListPreference appListPreference = new AppListPreference(prefContext);
            appListPreference.setIcon(getIcon((String) next.second));
            appListPreference.setTitle((CharSequence) next.first);
            appListPreference.setDialogTitle((CharSequence) next.first);
            int size = getEntries().size();
            CharSequence[] charSequenceArr = new CharSequence[size];
            CharSequence[] charSequenceArr2 = new CharSequence[size];
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                charSequenceArr[i2] = getEntries().get(i2);
                charSequenceArr2[i2] = String.valueOf(getValues().get(i2));
            }
            appListPreference.setEntries(charSequenceArr);
            appListPreference.setEntryValues(charSequenceArr2);
            String valueOf = String.valueOf(getCurrentValue((String) next.second));
            while (true) {
                if (i >= size) {
                    charSequence = "-1";
                    break;
                } else if (charSequenceArr2[i].equals(valueOf)) {
                    charSequence = charSequenceArr[i];
                    break;
                } else {
                    i++;
                }
            }
            appListPreference.setValue(valueOf);
            appListPreference.setSummary(charSequence);
            appListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.settings.fragment.PerAppConfigFragment.1
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public boolean onPreferenceChange(Preference preference, Object obj) {
                    String valueOf2 = String.valueOf(obj);
                    PerAppConfigFragment.this.onValueChanged((String) next.second, Integer.parseInt(valueOf2));
                    CharSequence[] entries = appListPreference.getEntries();
                    CharSequence[] entryValues = appListPreference.getEntryValues();
                    int i3 = 0;
                    CharSequence charSequence2 = entries[0];
                    while (true) {
                        if (i3 >= entries.length) {
                            break;
                        } else if (entryValues[i3].equals(valueOf2)) {
                            charSequence2 = entries[i3];
                            break;
                        } else {
                            i3++;
                        }
                    }
                    appListPreference.setSummary(charSequence2);
                    return true;
                }
            });
            preferenceScreen.addPreference(appListPreference);
        }
    }

    @Override // com.android.settings.widget.EmptyTextSettings, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setEmptyText(R.string.per_app_config_empty_text);
    }

    private ArrayList<Pair<String, String>> collectApps() {
        String[] stringArray;
        ArrayList<Pair<String, String>> arrayList = new ArrayList<>();
        for (PackageInfo packageInfo : this.mPackageManager.getInstalledPackages(0)) {
            if ((packageInfo.applicationInfo.flags & 1) == 0) {
                arrayList.add(new Pair<>(packageInfo.applicationInfo.loadLabel(this.mPackageManager).toString(), packageInfo.packageName));
            }
        }
        for (String str : this.mContext.getResources().getStringArray(R.array.config_perAppConfAllowedSystemApps)) {
            try {
                PackageInfo packageInfo2 = this.mPackageManager.getPackageInfo(str, 0);
                packageInfo2.applicationInfo.loadLabel(this.mPackageManager).toString();
                arrayList.add(new Pair<>(packageInfo2.applicationInfo.loadLabel(this.mPackageManager).toString(), str));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        Collections.sort(arrayList, new AppComparator());
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.graphics.drawable.Drawable getIcon(java.lang.String r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L9
            android.content.pm.PackageManager r0 = r1.mPackageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L9
            android.graphics.drawable.Drawable r2 = r0.getApplicationIcon(r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L9
            goto La
        L9:
            r2 = 0
        La:
            if (r2 == 0) goto Ld
            goto L13
        Ld:
            android.content.pm.PackageManager r1 = r1.mPackageManager
            android.graphics.drawable.Drawable r2 = r1.getDefaultActivityIcon()
        L13:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.fragment.PerAppConfigFragment.getIcon(java.lang.String):android.graphics.drawable.Drawable");
    }
}
