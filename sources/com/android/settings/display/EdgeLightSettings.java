package com.android.settings.display;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Switch;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.preference.SystemSettingMainSwitchPreference;
import com.android.settingslib.widget.OnMainSwitchChangeListener;
import org.nameless.custom.colorpicker.ColorPickerPreference;
import org.nameless.custom.preference.SystemSettingListPreference;
import org.nameless.custom.preference.SystemSettingSwitchPreference;
/* loaded from: classes.dex */
public class EdgeLightSettings extends SettingsPreferenceFragment implements OnMainSwitchChangeListener, Preference.OnPreferenceChangeListener {
    private static String KEY_ALWAYS_TRIGGER = "edge_light_always_trigger_on_pulse";
    private static String KEY_COLOR = "edge_light_custom_color";
    private static String KEY_COLOR_MODE = "edge_light_color_mode";
    private static String KEY_ENABLED = "edge_light_enabled";
    private static String KEY_REPEAT = "edge_light_repeat_animation";
    private SystemSettingSwitchPreference mAlwaysTrigger;
    private ColorPickerPreference mColor;
    private SystemSettingListPreference mColorMode;
    private SystemSettingMainSwitchPreference mEnabled;
    private SystemSettingSwitchPreference mRepeat;
    private ContentResolver mResolver;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.edge_light_settings);
        ContentResolver contentResolver = getContentResolver();
        this.mResolver = contentResolver;
        boolean z = false;
        boolean z2 = Settings.System.getIntForUser(contentResolver, "edge_light_enabled", 0, -2) == 1;
        boolean z3 = Settings.System.getIntForUser(this.mResolver, "edge_light_color_mode", 0, -2) == 3;
        String stringForUser = Settings.System.getStringForUser(this.mResolver, "edge_light_custom_color", -2);
        SystemSettingMainSwitchPreference systemSettingMainSwitchPreference = (SystemSettingMainSwitchPreference) findPreference(KEY_ENABLED);
        this.mEnabled = systemSettingMainSwitchPreference;
        systemSettingMainSwitchPreference.addOnSwitchChangeListener(this);
        SystemSettingSwitchPreference systemSettingSwitchPreference = (SystemSettingSwitchPreference) findPreference(KEY_ALWAYS_TRIGGER);
        this.mAlwaysTrigger = systemSettingSwitchPreference;
        systemSettingSwitchPreference.setEnabled(z2);
        SystemSettingSwitchPreference systemSettingSwitchPreference2 = (SystemSettingSwitchPreference) findPreference(KEY_REPEAT);
        this.mRepeat = systemSettingSwitchPreference2;
        systemSettingSwitchPreference2.setEnabled(z2);
        SystemSettingListPreference systemSettingListPreference = (SystemSettingListPreference) findPreference(KEY_COLOR_MODE);
        this.mColorMode = systemSettingListPreference;
        systemSettingListPreference.setEnabled(z2);
        this.mColorMode.setOnPreferenceChangeListener(this);
        if (TextUtils.isEmpty(stringForUser)) {
            stringForUser = "#ffffff";
        }
        ColorPickerPreference colorPickerPreference = (ColorPickerPreference) findPreference(KEY_COLOR);
        this.mColor = colorPickerPreference;
        colorPickerPreference.setDefaultValue(ColorPickerPreference.convertToColorInt("#ffffff"));
        ColorPickerPreference colorPickerPreference2 = this.mColor;
        if (z2 && z3) {
            z = true;
        }
        colorPickerPreference2.setEnabled(z);
        this.mColor.setSummary(stringForUser);
        this.mColor.setNewPreviewColor(ColorPickerPreference.convertToColorInt(stringForUser));
        this.mColor.setOnPreferenceChangeListener(this);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mColorMode) {
            int intValue = Integer.valueOf((String) obj).intValue();
            boolean z = false;
            boolean z2 = Settings.System.getIntForUser(this.mResolver, "edge_light_enabled", 0, -2) == 1;
            ColorPickerPreference colorPickerPreference = this.mColor;
            if (z2 && intValue == 3) {
                z = true;
            }
            colorPickerPreference.setEnabled(z);
        } else if (preference == this.mColor) {
            String convertToRGB = ColorPickerPreference.convertToRGB(Integer.valueOf(String.valueOf(obj)).intValue());
            this.mColor.setSummary(convertToRGB);
            Settings.System.putStringForUser(this.mResolver, "edge_light_custom_color", convertToRGB, -2);
        }
        return true;
    }

    @Override // com.android.settingslib.widget.OnMainSwitchChangeListener
    public void onSwitchChanged(Switch r4, boolean z) {
        boolean z2 = false;
        boolean z3 = Settings.System.getIntForUser(this.mResolver, "edge_light_color_mode", 0, -2) == 3;
        this.mAlwaysTrigger.setEnabled(z);
        this.mRepeat.setEnabled(z);
        this.mColorMode.setEnabled(z);
        ColorPickerPreference colorPickerPreference = this.mColor;
        if (z && z3) {
            z2 = true;
        }
        colorPickerPreference.setEnabled(z2);
    }
}
