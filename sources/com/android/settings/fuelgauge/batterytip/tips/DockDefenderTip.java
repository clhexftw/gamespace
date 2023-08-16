package com.android.settings.fuelgauge.batterytip.tips;

import android.content.Context;
import android.content.Intent;
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
public class DockDefenderTip extends BatteryTip {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.android.settings.fuelgauge.batterytip.tips.DockDefenderTip.1
        @Override // android.os.Parcelable.Creator
        public BatteryTip createFromParcel(Parcel parcel) {
            return new DockDefenderTip(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BatteryTip[] newArray(int i) {
            return new DockDefenderTip[i];
        }
    };
    private int mMode;

    public DockDefenderTip(int i, int i2) {
        super(9, i, false);
        this.mMode = i2;
    }

    private DockDefenderTip(Parcel parcel) {
        super(parcel);
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public CharSequence getTitle(Context context) {
        int i = this.mMode;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return context.getString(R.string.battery_tip_dock_defender_temporarily_bypassed_title);
            }
            return context.getString(R.string.battery_tip_dock_defender_active_title);
        }
        return context.getString(R.string.battery_tip_dock_defender_future_bypass_title);
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public CharSequence getSummary(Context context) {
        int i = this.mMode;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return context.getString(R.string.battery_tip_dock_defender_temporarily_bypassed_summary);
            }
            return context.getString(R.string.battery_tip_dock_defender_active_summary);
        }
        return context.getString(R.string.battery_tip_dock_defender_future_bypass_summary);
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public int getIconId() {
        return this.mMode == 1 ? R.drawable.ic_battery_status_protected_24dp : R.drawable.ic_battery_dock_defender_untriggered_24dp;
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public void updateState(BatteryTip batteryTip) {
        this.mState = batteryTip.mState;
        if (batteryTip instanceof DockDefenderTip) {
            this.mMode = ((DockDefenderTip) batteryTip).mMode;
        }
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public void log(Context context, MetricsFeatureProvider metricsFeatureProvider) {
        metricsFeatureProvider.action(context, 1804, this.mState);
    }

    @Override // com.android.settings.fuelgauge.batterytip.tips.BatteryTip
    public void updatePreference(final Preference preference) {
        super.updatePreference(preference);
        final Context context = preference.getContext();
        CardPreference castToCardPreferenceSafely = castToCardPreferenceSafely(preference);
        if (castToCardPreferenceSafely == null) {
            Log.e("DockDefenderTip", "cast Preference to CardPreference failed");
            return;
        }
        castToCardPreferenceSafely.setSelectable(false);
        int i = this.mMode;
        if (i == 0 || i == 1) {
            castToCardPreferenceSafely.setPrimaryButtonText(context.getString(R.string.battery_tip_charge_to_full_button));
            castToCardPreferenceSafely.setPrimaryButtonClickListener(new View.OnClickListener() { // from class: com.android.settings.fuelgauge.batterytip.tips.DockDefenderTip$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DockDefenderTip.this.lambda$updatePreference$0(context, preference, view);
                }
            });
            castToCardPreferenceSafely.setPrimaryButtonVisible(true);
        } else if (i == 2) {
            castToCardPreferenceSafely.setPrimaryButtonVisible(false);
        } else {
            castToCardPreferenceSafely.setVisible(false);
            return;
        }
        castToCardPreferenceSafely.setSecondaryButtonText(context.getString(R.string.learn_more));
        castToCardPreferenceSafely.setSecondaryButtonClickListener(new View.OnClickListener() { // from class: com.android.settings.fuelgauge.batterytip.tips.DockDefenderTip$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DockDefenderTip.lambda$updatePreference$1(context, view);
            }
        });
        castToCardPreferenceSafely.setSecondaryButtonVisible(true);
        castToCardPreferenceSafely.setSecondaryButtonContentDescription(context.getString(R.string.battery_tip_limited_temporarily_sec_button_content_description));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePreference$0(Context context, Preference preference, View view) {
        resumeCharging(context);
        this.mMode = 2;
        context.sendBroadcast(new Intent().setAction("battery.dock.defender.bypass").setPackage(context.getPackageName()).addFlags(1342177280));
        updatePreference(preference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePreference$1(Context context, View view) {
        view.startActivityForResult(HelpUtils.getHelpIntent(context, context.getString(R.string.help_url_dock_defender), ""), 0);
    }

    private CardPreference castToCardPreferenceSafely(Preference preference) {
        if (preference instanceof CardPreference) {
            return (CardPreference) preference;
        }
        return null;
    }

    private void resumeCharging(Context context) {
        Intent resumeChargeIntent = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context).getResumeChargeIntent(true);
        if (resumeChargeIntent != null) {
            context.sendBroadcast(resumeChargeIntent);
        }
        Log.i("DockDefenderTip", "send resume charging broadcast intent=" + resumeChargeIntent);
    }
}
