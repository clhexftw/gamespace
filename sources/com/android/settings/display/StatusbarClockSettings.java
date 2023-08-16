package com.android.settings.display;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.widget.EditText;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/* loaded from: classes.dex */
public class StatusbarClockSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.settings.display.StatusbarClockSettings.3
        @Override // com.android.settings.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.statusbar_clock;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        @Override // com.android.settings.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<String> getNonIndexableKeys(Context context) {
            return super.getNonIndexableKeys(context);
        }
    };
    private ListPreference mClockDateDisplay;
    private ListPreference mClockDateFormat;
    private ListPreference mClockDatePosition;
    private ListPreference mClockDateStyle;
    private ListPreference mStatusBarAmPm;
    private ListPreference mStatusBarClock;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.statusbar_clock);
        ContentResolver contentResolver = getActivity().getContentResolver();
        this.mStatusBarClock = (ListPreference) findPreference("statusbar_clock_style");
        this.mStatusBarAmPm = (ListPreference) findPreference("statusbar_am_pm");
        this.mClockDateDisplay = (ListPreference) findPreference("clock_date_display");
        this.mClockDateStyle = (ListPreference) findPreference("clock_date_style");
        this.mStatusBarClock.setValue(String.valueOf(Settings.System.getInt(contentResolver, "statusbar_clock_style", 0)));
        ListPreference listPreference = this.mStatusBarClock;
        listPreference.setSummary(listPreference.getEntry());
        this.mStatusBarClock.setOnPreferenceChangeListener(this);
        if (DateFormat.is24HourFormat(getActivity())) {
            this.mStatusBarAmPm.setEnabled(false);
            this.mStatusBarAmPm.setSummary(R.string.status_bar_am_pm_info);
        } else {
            this.mStatusBarAmPm.setValue(String.valueOf(Settings.System.getInt(contentResolver, "statusbar_clock_am_pm_style", 2)));
            ListPreference listPreference2 = this.mStatusBarAmPm;
            listPreference2.setSummary(listPreference2.getEntry());
            this.mStatusBarAmPm.setOnPreferenceChangeListener(this);
        }
        this.mClockDateDisplay.setValue(String.valueOf(Settings.System.getInt(contentResolver, "statusbar_clock_date_display", 0)));
        ListPreference listPreference3 = this.mClockDateDisplay;
        listPreference3.setSummary(listPreference3.getEntry());
        this.mClockDateDisplay.setOnPreferenceChangeListener(this);
        this.mClockDateStyle.setValue(String.valueOf(Settings.System.getInt(contentResolver, "statusbar_clock_date_style", 0)));
        ListPreference listPreference4 = this.mClockDateStyle;
        listPreference4.setSummary(listPreference4.getEntry());
        this.mClockDateStyle.setOnPreferenceChangeListener(this);
        ListPreference listPreference5 = (ListPreference) findPreference("clock_date_format");
        this.mClockDateFormat = listPreference5;
        listPreference5.setOnPreferenceChangeListener(this);
        String string = Settings.System.getString(getActivity().getContentResolver(), "statusbar_clock_date_format");
        string = (string == null || string.isEmpty()) ? "EEE" : "EEE";
        if (this.mClockDateFormat.findIndexOfValue(string) == -1) {
            this.mClockDateFormat.setValueIndex(18);
        } else {
            this.mClockDateFormat.setValue(string);
        }
        parseClockDateFormats();
        ListPreference listPreference6 = (ListPreference) findPreference("statusbar_clock_date_position");
        this.mClockDatePosition = listPreference6;
        listPreference6.setValue(Integer.toString(Settings.System.getInt(getActivity().getContentResolver(), "statusbar_clock_date_position", 0)));
        ListPreference listPreference7 = this.mClockDatePosition;
        listPreference7.setSummary(listPreference7.getEntry());
        this.mClockDatePosition.setOnPreferenceChangeListener(this);
        this.mClockDatePosition.setValue(String.valueOf(Settings.System.getInt(contentResolver, "statusbar_clock_date_position", 0)));
        ListPreference listPreference8 = this.mClockDatePosition;
        listPreference8.setSummary(listPreference8.getEntry());
        this.mClockDatePosition.setOnPreferenceChangeListener(this);
        setDateOptions();
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mStatusBarClock) {
            String str = (String) obj;
            int parseInt = Integer.parseInt(str);
            int findIndexOfValue = this.mStatusBarClock.findIndexOfValue(str);
            Settings.System.putInt(getActivity().getContentResolver(), "statusbar_clock_style", parseInt);
            ListPreference listPreference = this.mStatusBarClock;
            listPreference.setSummary(listPreference.getEntries()[findIndexOfValue]);
            return true;
        } else if (preference == this.mStatusBarAmPm) {
            String str2 = (String) obj;
            int intValue = Integer.valueOf(str2).intValue();
            int findIndexOfValue2 = this.mStatusBarAmPm.findIndexOfValue(str2);
            Settings.System.putInt(getActivity().getContentResolver(), "statusbar_clock_am_pm_style", intValue);
            ListPreference listPreference2 = this.mStatusBarAmPm;
            listPreference2.setSummary(listPreference2.getEntries()[findIndexOfValue2]);
            return true;
        } else if (preference == this.mClockDateDisplay) {
            String str3 = (String) obj;
            int intValue2 = Integer.valueOf(str3).intValue();
            int findIndexOfValue3 = this.mClockDateDisplay.findIndexOfValue(str3);
            Settings.System.putInt(getActivity().getContentResolver(), "statusbar_clock_date_display", intValue2);
            ListPreference listPreference3 = this.mClockDateDisplay;
            listPreference3.setSummary(listPreference3.getEntries()[findIndexOfValue3]);
            setDateOptions();
            return true;
        } else if (preference == this.mClockDateStyle) {
            String str4 = (String) obj;
            int intValue3 = Integer.valueOf(str4).intValue();
            int findIndexOfValue4 = this.mClockDateStyle.findIndexOfValue(str4);
            Settings.System.putInt(getActivity().getContentResolver(), "statusbar_clock_date_style", intValue3);
            ListPreference listPreference4 = this.mClockDateStyle;
            listPreference4.setSummary(listPreference4.getEntries()[findIndexOfValue4]);
            parseClockDateFormats();
            return true;
        } else {
            ListPreference listPreference5 = this.mClockDateFormat;
            if (preference == listPreference5) {
                String str5 = (String) obj;
                if (listPreference5.findIndexOfValue(str5) == 18) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.clock_date_string_edittext_title);
                    builder.setMessage(R.string.clock_date_string_edittext_summary);
                    final EditText editText = new EditText(getActivity());
                    String string = Settings.System.getString(getActivity().getContentResolver(), "statusbar_clock_date_format");
                    if (string != null) {
                        editText.setText(string);
                    }
                    builder.setView(editText);
                    builder.setPositiveButton(R.string.menu_save, new DialogInterface.OnClickListener() { // from class: com.android.settings.display.StatusbarClockSettings.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String obj2 = editText.getText().toString();
                            if (obj2.equals("")) {
                                return;
                            }
                            Settings.System.putString(StatusbarClockSettings.this.getActivity().getContentResolver(), "statusbar_clock_date_format", obj2);
                        }
                    });
                    builder.setNegativeButton(R.string.menu_cancel, new DialogInterface.OnClickListener() { // from class: com.android.settings.display.StatusbarClockSettings.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.create().show();
                } else if (str5 != null) {
                    Settings.System.putString(getActivity().getContentResolver(), "statusbar_clock_date_format", str5);
                }
                return true;
            } else if (preference == this.mClockDatePosition) {
                String str6 = (String) obj;
                int parseInt2 = Integer.parseInt(str6);
                int findIndexOfValue5 = this.mClockDatePosition.findIndexOfValue(str6);
                Settings.System.putInt(getActivity().getContentResolver(), "statusbar_clock_date_position", parseInt2);
                ListPreference listPreference6 = this.mClockDatePosition;
                listPreference6.setSummary(listPreference6.getEntries()[findIndexOfValue5]);
                parseClockDateFormats();
                return true;
            } else {
                return false;
            }
        }
    }

    private void parseClockDateFormats() {
        String charSequence;
        String[] stringArray = getResources().getStringArray(R.array.clock_date_format_entries_values);
        String[] strArr = new String[stringArray.length];
        Date date = new Date();
        int length = stringArray.length - 1;
        int i = Settings.System.getInt(getActivity().getContentResolver(), "statusbar_clock_date_style", 0);
        for (int i2 = 0; i2 < stringArray.length; i2++) {
            if (i2 == length) {
                strArr[i2] = stringArray[i2];
            } else {
                CharSequence format = DateFormat.format(stringArray[i2], date);
                if (i == 1) {
                    charSequence = format.toString().toLowerCase();
                } else if (i == 2) {
                    charSequence = format.toString().toUpperCase();
                } else {
                    charSequence = format.toString();
                }
                strArr[i2] = charSequence;
            }
        }
        this.mClockDateFormat.setEntries(strArr);
    }

    private void setDateOptions() {
        if (Settings.System.getInt(getActivity().getContentResolver(), "statusbar_clock_date_display", 0) == 0) {
            this.mClockDateStyle.setEnabled(false);
            this.mClockDateFormat.setEnabled(false);
            this.mClockDatePosition.setEnabled(false);
            return;
        }
        this.mClockDateStyle.setEnabled(true);
        this.mClockDateFormat.setEnabled(true);
        this.mClockDatePosition.setEnabled(true);
    }
}
