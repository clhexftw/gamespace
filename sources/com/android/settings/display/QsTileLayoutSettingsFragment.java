package com.android.settings.display;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.preference.Preference;
import com.android.internal.util.nameless.TileUtils;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.widget.LayoutPreference;
import org.nameless.custom.preference.CustomSeekBarPreference;
import org.nameless.custom.preference.SystemSettingSwitchPreference;
/* loaded from: classes.dex */
public class QsTileLayoutSettingsFragment extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private int[] currentValue = new int[2];
    private Button mApplyChange;
    private Context mContext;
    private SystemSettingSwitchPreference mHide;
    private CustomSeekBarPreference mQqsRows;
    private CustomSeekBarPreference mQsColumns;
    private CustomSeekBarPreference mQsRows;
    private SystemSettingSwitchPreference mVertical;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.qs_tile_layout);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        CustomSeekBarPreference customSeekBarPreference = (CustomSeekBarPreference) findPreference("qs_layout_columns");
        this.mQsColumns = customSeekBarPreference;
        customSeekBarPreference.setOnPreferenceChangeListener(this);
        CustomSeekBarPreference customSeekBarPreference2 = (CustomSeekBarPreference) findPreference("qs_layout_rows");
        this.mQsRows = customSeekBarPreference2;
        customSeekBarPreference2.setOnPreferenceChangeListener(this);
        CustomSeekBarPreference customSeekBarPreference3 = (CustomSeekBarPreference) findPreference("qqs_layout_rows");
        this.mQqsRows = customSeekBarPreference3;
        customSeekBarPreference3.setOnPreferenceChangeListener(this);
        this.mContext = getContext();
        Button button = (Button) ((LayoutPreference) findPreference("apply_change_button")).findViewById(R.id.apply_change);
        this.mApplyChange = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.display.QsTileLayoutSettingsFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (QsTileLayoutSettingsFragment.this.mApplyChange.isEnabled()) {
                    int[] iArr = {(QsTileLayoutSettingsFragment.this.mQsRows.getValue() * 10) + QsTileLayoutSettingsFragment.this.mQsColumns.getValue(), (QsTileLayoutSettingsFragment.this.mQqsRows.getValue() * 10) + QsTileLayoutSettingsFragment.this.mQsColumns.getValue()};
                    Settings.System.putIntForUser(QsTileLayoutSettingsFragment.this.getContentResolver(), "qs_layout", iArr[0], 0);
                    Settings.System.putIntForUser(QsTileLayoutSettingsFragment.this.getContentResolver(), "qqs_layout", iArr[1], 0);
                    if (!TileUtils.updateLayout(QsTileLayoutSettingsFragment.this.mContext)) {
                        Settings.System.putIntForUser(QsTileLayoutSettingsFragment.this.getContentResolver(), "qs_layout", QsTileLayoutSettingsFragment.this.currentValue[0], 0);
                        Settings.System.putIntForUser(QsTileLayoutSettingsFragment.this.getContentResolver(), "qqs_layout", QsTileLayoutSettingsFragment.this.currentValue[1], 0);
                        Toast.makeText(QsTileLayoutSettingsFragment.this.mContext, R.string.qs_apply_change_failed, 1).show();
                        return;
                    }
                    QsTileLayoutSettingsFragment.this.currentValue[0] = iArr[0];
                    QsTileLayoutSettingsFragment.this.currentValue[1] = iArr[1];
                    QsTileLayoutSettingsFragment.this.mApplyChange.setEnabled(false);
                }
            }
        });
        initPreference();
        boolean qSTileLabelHide = TileUtils.getQSTileLabelHide(this.mContext);
        SystemSettingSwitchPreference systemSettingSwitchPreference = (SystemSettingSwitchPreference) findPreference("qs_tile_label_hide");
        this.mHide = systemSettingSwitchPreference;
        systemSettingSwitchPreference.setOnPreferenceChangeListener(this);
        SystemSettingSwitchPreference systemSettingSwitchPreference2 = (SystemSettingSwitchPreference) findPreference("qs_tile_vertical_layout");
        this.mVertical = systemSettingSwitchPreference2;
        systemSettingSwitchPreference2.setEnabled(!qSTileLabelHide);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mHide) {
            this.mVertical.setEnabled(!((Boolean) obj).booleanValue());
        } else {
            boolean z = false;
            if (preference == this.mQsColumns) {
                int parseInt = Integer.parseInt(obj.toString());
                Button button = this.mApplyChange;
                if (this.currentValue[0] != (this.mQsRows.getValue() * 10) + parseInt || this.currentValue[1] != (this.mQqsRows.getValue() * 10) + parseInt) {
                    z = true;
                }
                button.setEnabled(z);
            } else if (preference == this.mQsRows) {
                int parseInt2 = Integer.parseInt(obj.toString());
                int i = parseInt2 - 1;
                this.mQqsRows.setMax(i);
                if (this.mQqsRows.getValue() > i) {
                    this.mQqsRows.setValue(i);
                }
                Button button2 = this.mApplyChange;
                if (this.currentValue[0] != (parseInt2 * 10) + this.mQsColumns.getValue() || this.currentValue[1] != (this.mQqsRows.getValue() * 10) + this.mQsColumns.getValue()) {
                    z = true;
                }
                button2.setEnabled(z);
            } else if (preference == this.mQqsRows) {
                int parseInt3 = Integer.parseInt(obj.toString());
                Button button3 = this.mApplyChange;
                if (this.currentValue[0] != (this.mQsRows.getValue() * 10) + this.mQsColumns.getValue() || this.currentValue[1] != (parseInt3 * 10) + this.mQsColumns.getValue()) {
                    z = true;
                }
                button3.setEnabled(z);
            }
        }
        return true;
    }

    private void initPreference() {
        int intForUser = Settings.System.getIntForUser(getContentResolver(), "qs_layout", 42, 0);
        int intForUser2 = Settings.System.getIntForUser(getContentResolver(), "qqs_layout", 22, 0);
        this.mQsColumns.setValue(intForUser % 10);
        this.mQsRows.setValue(intForUser / 10);
        this.mQqsRows.setValue(intForUser2 / 10);
        this.mQqsRows.setMax(this.mQsRows.getValue() - 1);
        int[] iArr = this.currentValue;
        iArr[0] = intForUser;
        iArr[1] = intForUser2;
    }
}
