package com.android.settings.fuelgauge.batterytip.tips;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.widget.CardPreference;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
/* loaded from: classes.dex */
public class BatteryDefenderTip extends BatteryTip {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.android.settings.fuelgauge.batterytip.tips.BatteryDefenderTip.1
        @Override // android.os.Parcelable.Creator
        public BatteryTip createFromParcel(Parcel parcel) {
            return new BatteryDefenderTip(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BatteryTip[] newArray(int i) {
            return new BatteryDefenderTip[i];
        }
    };

    public BatteryDefenderTip(int i) {
        super(8, i, false);
    }

    private BatteryDefenderTip(Parcel parcel) {
        super(parcel);
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public CharSequence getTitle(Context context) {
        return context.getString(R.string.battery_tip_limited_temporarily_title);
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public CharSequence getSummary(Context context) {
        return context.getString(R.string.battery_tip_limited_temporarily_summary);
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public int getIconId() {
        return R.drawable.ic_battery_status_good_24dp;
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public void updateState(BatteryTip batteryTip) {
        this.mState = batteryTip.mState;
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public void log(Context context, MetricsFeatureProvider metricsFeatureProvider) {
        metricsFeatureProvider.action(context, 1772, this.mState);
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public void updatePreference(final Preference preference) {
        super.updatePreference(preference);
        final Context context = preference.getContext();
        CardPreference castToCardPreferenceSafely = castToCardPreferenceSafely(preference);
        if (castToCardPreferenceSafely == null) {
            Log.e("BatteryDefenderTip", "cast Preference to CardPreference failed");
            return;
        }
        castToCardPreferenceSafely.setSelectable(false);
        castToCardPreferenceSafely.setPrimaryButtonText(context.getString(R.string.battery_tip_charge_to_full_button));
        castToCardPreferenceSafely.setPrimaryButtonClickListener(new View.OnClickListener() { // from class: com.android.settings.fuelgauge.batterytip.tips.BatteryDefenderTip$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BatteryDefenderTip.this.lambda$updatePreference$0(context, preference, view);
            }
        });
        castToCardPreferenceSafely.setPrimaryButtonVisible(isPluggedIn(context));
        castToCardPreferenceSafely.setSecondaryButtonText(context.getString(R.string.learn_more));
        castToCardPreferenceSafely.setSecondaryButtonClickListener(new View.OnClickListener() { // from class: com.android.settings.fuelgauge.batterytip.tips.BatteryDefenderTip$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BatteryDefenderTip.lambda$updatePreference$1(context, view);
            }
        });
        castToCardPreferenceSafely.setSecondaryButtonVisible(true);
        castToCardPreferenceSafely.setSecondaryButtonContentDescription(context.getString(R.string.battery_tip_limited_temporarily_sec_button_content_description));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePreference$0(Context context, Preference preference, View view) {
        resumeCharging(context);
        preference.setVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePreference$1(Context context, View view) {
        view.startActivityForResult(HelpUtils.getHelpIntent(context, context.getString(R.string.help_url_battery_defender), ""), 0);
    }

    private CardPreference castToCardPreferenceSafely(Preference preference) {
        if (preference instanceof CardPreference) {
            return (CardPreference) preference;
        }
        return null;
    }

    private void resumeCharging(Context context) {
        Intent resumeChargeIntent = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context).getResumeChargeIntent(false);
        if (resumeChargeIntent != null) {
            context.sendBroadcast(resumeChargeIntent);
        }
        Log.i("BatteryDefenderTip", "send resume charging broadcast intent=" + resumeChargeIntent);
    }

    private boolean isPluggedIn(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return (registerReceiver == null || registerReceiver.getIntExtra("plugged", 0) == 0) ? false : true;
    }
}
