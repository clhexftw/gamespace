package com.android.settings.notification;

import android.content.Context;
import android.telephony.TelephonyManager;
import androidx.preference.PreferenceScreen;
import com.android.settings.DefaultRingtonePreference;
import com.android.settings.R;
import com.android.settings.Utils;
/* loaded from: classes.dex */
public class PhoneRingtone2PreferenceController extends RingtonePreferenceControllerBase {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "ringtone2";
    }

    @Override // com.android.settings.notification.RingtonePreferenceControllerBase
    public int getRingtoneType() {
        return 1;
    }

    public PhoneRingtone2PreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        DefaultRingtonePreference defaultRingtonePreference = (DefaultRingtonePreference) preferenceScreen.findPreference("ringtone2");
        defaultRingtonePreference.setSlotId(1);
        defaultRingtonePreference.setTitle(this.mContext.getString(R.string.ringtone_title) + " - " + String.format(this.mContext.getString(R.string.sim_card_number_title), 2));
        defaultRingtonePreference.setEnabled(hasCard());
    }

    @Override // com.android.settings.notification.RingtonePreferenceControllerBase, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return Utils.isVoiceCapable(this.mContext) && ((TelephonyManager) this.mContext.getSystemService("phone")).isMultiSimEnabled();
    }

    private boolean hasCard() {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).hasIccCard(1);
    }
}
