package com.android.settingslib.fuelgauge;

import android.content.Context;
import android.content.Intent;
import com.android.settingslib.R$integer;
/* loaded from: classes2.dex */
public class BatteryStatus {
    public final int chargerStatusCustom;
    public final int health;
    public final int level;
    public final int maxChargingCurrent;
    public final int maxChargingVoltage;
    public final int maxChargingWattage;
    public final int plugged;
    public final boolean present;
    public final int status;
    public final float temperature;

    public static boolean isCharged(int i, int i2) {
        return i == 5 || i2 >= 100;
    }

    public BatteryStatus(Intent intent) {
        this.status = intent.getIntExtra("status", 1);
        this.plugged = intent.getIntExtra("plugged", 0);
        this.level = intent.getIntExtra("level", 0);
        this.health = intent.getIntExtra("health", 1);
        this.present = intent.getBooleanExtra("present", true);
        this.temperature = intent.getIntExtra("temperature", -1);
        this.chargerStatusCustom = intent.getIntExtra("charger_status_custom", -1);
        int intExtra = intent.getIntExtra("max_charging_current", -1);
        int intExtra2 = intent.getIntExtra("max_charging_voltage", -1);
        intExtra2 = intExtra2 <= 0 ? 5000000 : intExtra2;
        if (intExtra > 0) {
            this.maxChargingCurrent = intExtra;
            this.maxChargingVoltage = intExtra2;
            this.maxChargingWattage = (intExtra / 1000) * (intExtra2 / 1000);
            return;
        }
        this.maxChargingCurrent = -1;
        this.maxChargingVoltage = -1;
        this.maxChargingWattage = -1;
    }

    public boolean isPluggedInWired() {
        int i = this.plugged;
        return i == 1 || i == 2;
    }

    public boolean isPluggedInDock() {
        return this.plugged == 8;
    }

    public boolean isCharged() {
        return isCharged(this.status, this.level);
    }

    public final int getChargingSpeed(Context context) {
        int integer = context.getResources().getInteger(R$integer.config_chargingSlowlyThreshold);
        int integer2 = context.getResources().getInteger(R$integer.config_chargingFastThreshold);
        int i = this.chargerStatusCustom;
        if (i != -1) {
            return i;
        }
        int i2 = this.maxChargingWattage;
        if (i2 <= 0) {
            return -1;
        }
        if (i2 < integer) {
            return 0;
        }
        return i2 > integer2 ? 2 : 1;
    }

    public String toString() {
        return "BatteryStatus{status=" + this.status + ",level=" + this.level + ",plugged=" + this.plugged + ",health=" + this.health + ",maxChargingWattage=" + this.maxChargingWattage + "}";
    }
}
