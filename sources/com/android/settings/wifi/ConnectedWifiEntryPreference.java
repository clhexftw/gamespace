package com.android.settings.wifi;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.R$id;
import com.android.settingslib.R$layout;
import com.android.wifitrackerlib.WifiEntry;
/* loaded from: classes.dex */
public class ConnectedWifiEntryPreference extends LongPressWifiEntryPreference {
    private OnGearClickListener mOnGearClickListener;

    /* loaded from: classes.dex */
    public interface OnGearClickListener {
        void onGearClick(ConnectedWifiEntryPreference connectedWifiEntryPreference);
    }

    public ConnectedWifiEntryPreference(Context context, WifiEntry wifiEntry, Fragment fragment) {
        super(context, wifiEntry, fragment);
        setWidgetLayoutResource(R$layout.preference_widget_gear_optional_background);
    }

    public void setOnGearClickListener(OnGearClickListener onGearClickListener) {
        this.mOnGearClickListener = onGearClickListener;
        notifyChanged();
    }

    @Override // com.android.settings.wifi.LongPressWifiEntryPreference, com.android.settings.wifi.WifiEntryPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(R$id.settings_button);
        findViewById.setOnClickListener(this);
        boolean canSignIn = getWifiEntry().canSignIn();
        preferenceViewHolder.findViewById(R$id.settings_button_no_background).setVisibility(canSignIn ? 4 : 0);
        findViewById.setVisibility(canSignIn ? 0 : 4);
        preferenceViewHolder.findViewById(R$id.two_target_divider).setVisibility(canSignIn ? 0 : 4);
    }

    @Override // com.android.settings.wifi.WifiEntryPreference, android.view.View.OnClickListener
    public void onClick(View view) {
        OnGearClickListener onGearClickListener;
        if (view.getId() != R$id.settings_button || (onGearClickListener = this.mOnGearClickListener) == null) {
            return;
        }
        onGearClickListener.onGearClick(this);
    }
}
