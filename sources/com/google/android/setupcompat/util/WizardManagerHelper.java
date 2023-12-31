package com.google.android.setupcompat.util;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import java.util.Arrays;
/* loaded from: classes2.dex */
public final class WizardManagerHelper {
    public static final String ACTION_NEXT = "com.android.wizard.NEXT";
    static final String EXTRA_ACTION_ID = "actionId";
    static final String EXTRA_SCRIPT_URI = "scriptUri";
    static final String EXTRA_WIZARD_BUNDLE = "wizardBundle";

    public static void copyWizardManagerExtras(Intent intent, Intent intent2) {
        intent2.putExtra(EXTRA_WIZARD_BUNDLE, intent.getBundleExtra(EXTRA_WIZARD_BUNDLE));
        for (String str : Arrays.asList("firstRun", "deferredSetup", "preDeferredSetup", "portalSetup", "isSetupFlow", "isSuwSuggestedActionFlow")) {
            intent2.putExtra(str, intent.getBooleanExtra(str, false));
        }
        for (String str2 : Arrays.asList("theme", EXTRA_SCRIPT_URI, EXTRA_ACTION_ID)) {
            intent2.putExtra(str2, intent.getStringExtra(str2));
        }
    }

    @Deprecated
    public static boolean isSetupWizardIntent(Intent intent) {
        return intent.getBooleanExtra("firstRun", false);
    }

    public static boolean isUserSetupComplete(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "user_setup_complete", 0) == 1;
    }

    public static boolean isDeviceProvisioned(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 1;
    }

    public static boolean isPortalSetupWizard(Intent intent) {
        return intent != null && intent.getBooleanExtra("portalSetup", false);
    }

    public static boolean isDeferredSetupWizard(Intent intent) {
        return intent != null && intent.getBooleanExtra("deferredSetup", false);
    }

    public static boolean isAnySetupWizard(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.getBooleanExtra("isSetupFlow", false);
    }
}
