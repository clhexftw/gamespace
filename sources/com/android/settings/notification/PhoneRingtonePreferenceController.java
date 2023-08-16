package com.android.settings.notification;

import android.content.Context;
import android.telephony.TelephonyManager;
import androidx.preference.PreferenceScreen;
import com.android.settings.DefaultRingtonePreference;
import com.android.settings.R;
import com.android.settings.Utils;
/* loaded from: classes.dex */
public class PhoneRingtonePreferenceController extends RingtonePreferenceControllerBase {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "phone_ringtone";
    }

    @Override // com.android.settings.notification.RingtonePreferenceControllerBase
    public int getRingtoneType() {
        return 1;
    }

    public PhoneRingtonePreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (((TelephonyManager) this.mContext.getSystemService("phone")).isMultiSimEnabled()) {
            DefaultRingtonePreference defaultRingtonePreference = (DefaultRingtonePreference) preferenceScreen.findPreference("phone_ringtone");
            defaultRingtonePreference.setTitle(this.mContext.getString(R.string.ringtone_title) + " - " + String.format(this.mContext.getString(R.string.sim_card_number_title), 1));
            defaultRingtonePreference.setEnabled(hasCard());
        }
    }

    @Override // com.android.settings.notification.RingtonePreferenceControllerBase, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return Utils.isVoiceCapable(this.mContext);
    }

    private boolean hasCard() {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).hasIccCard(0);
    }
}
