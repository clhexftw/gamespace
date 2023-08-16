package com.android.settings.notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.PrimarySwitchPreference;
import java.util.List;
/* loaded from: classes.dex */
public class NotificationAssistantPreferenceController extends TogglePreferenceController {
    private static final int AVAILABLE = 1;
    static final String KEY_NAS = "notification_assistant";
    private static final String TAG = "NASPreferenceController";
    private ComponentName mDefaultNASComponent;
    private Fragment mFragment;
    private Intent mNASSettingIntent;
    protected NotificationBackend mNotificationBackend;
    private final PackageManager mPackageManager;
    private int mUserId;
    private final UserManager mUserManager;

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 1;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public NotificationAssistantPreferenceController(Context context) {
        super(context, KEY_NAS);
        this.mUserId = UserHandle.myUserId();
        this.mUserManager = UserManager.get(context);
        this.mNotificationBackend = new NotificationBackend();
        this.mPackageManager = context.getPackageManager();
        getDefaultNASIntent();
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        ComponentName allowedNotificationAssistant = this.mNotificationBackend.getAllowedNotificationAssistant();
        return allowedNotificationAssistant != null && allowedNotificationAssistant.equals(this.mDefaultNASComponent);
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        ComponentName componentName = z ? this.mDefaultNASComponent : null;
        if (z) {
            if (this.mFragment == null) {
                throw new IllegalStateException("No fragment to start activity");
            }
            showDialog(componentName);
            return false;
        }
        setNotificationAssistantGranted(null);
        return true;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_notifications;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setNotificationAssistantGranted(ComponentName componentName) {
        if (Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "nas_settings_updated", 0, this.mUserId) == 0) {
            this.mNotificationBackend.setNASMigrationDoneAndResetDefault(this.mUserId, componentName != null);
        }
        this.mNotificationBackend.setNotificationAssistantGranted(componentName);
    }

    protected void showDialog(ComponentName componentName) {
        NotificationAssistantDialogFragment.newInstance(this.mFragment, componentName).show(this.mFragment.getFragmentManager(), TAG);
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    void setBackend(NotificationBackend notificationBackend) {
        this.mNotificationBackend = notificationBackend;
    }

    void getDefaultNASIntent() {
        ComponentName defaultNotificationAssistant = this.mNotificationBackend.getDefaultNotificationAssistant();
        this.mDefaultNASComponent = defaultNotificationAssistant;
        if (defaultNotificationAssistant != null) {
            Intent intent = new Intent("android.service.notification.action.NOTIFICATION_ASSISTANT_DETAIL_SETTINGS");
            this.mNASSettingIntent = intent;
            intent.setPackage(this.mDefaultNASComponent.getPackageName());
            this.mNASSettingIntent.addFlags(268435456);
        }
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public boolean isSliceable() {
        Fragment fragment = this.mFragment;
        return fragment != null && (fragment instanceof ConfigureNotificationSettings);
    }

    private boolean isNASSettingActivityAvailable() {
        List queryIntentActivities = this.mPackageManager.queryIntentActivities(this.mNASSettingIntent, PackageManager.ResolveInfoFlags.of(131072L));
        return (queryIntentActivities == null || queryIntentActivities.isEmpty()) ? false : true;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mDefaultNASComponent == null) {
            preference.setEnabled(false);
            ((PrimarySwitchPreference) preference).setSwitchEnabled(false);
        } else if (isNASSettingActivityAvailable()) {
            preference.setIntent(this.mNASSettingIntent);
        } else {
            preference.setIntent(null);
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.settings.notification.NotificationAssistantPreferenceController$$ExternalSyntheticLambda0
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    boolean lambda$updateState$0;
                    lambda$updateState$0 = NotificationAssistantPreferenceController.this.lambda$updateState$0(preference2);
                    return lambda$updateState$0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$updateState$0(Preference preference) {
        onPreferenceChange(preference, Boolean.valueOf(!isChecked()));
        ((PrimarySwitchPreference) preference).setChecked(isChecked());
        return true;
    }
}
