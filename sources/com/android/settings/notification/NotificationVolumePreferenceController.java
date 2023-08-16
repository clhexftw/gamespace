package com.android.settings.notification;

import android.app.ActivityThread;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.provider.DeviceConfig;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.PreferenceScreen;
import com.android.internal.util.FunctionalUtils;
import com.android.settings.R;
import com.android.settingslib.core.AbstractPreferenceController;
/* loaded from: classes.dex */
public class NotificationVolumePreferenceController extends RingerModeAffectedVolumePreferenceController {
    private static final String KEY_NOTIFICATION_VOLUME = "notification_volume";
    private static final String TAG = "NotificationVolumePreferenceController";
    private final H mHandler;
    private final RingReceiver mReceiver;

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController
    public int getAudioStream() {
        return 5;
    }

    @Override // com.android.settings.notification.RingerModeAffectedVolumePreferenceController, com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_NOTIFICATION_VOLUME;
    }

    @Override // com.android.settings.notification.RingerModeAffectedVolumePreferenceController, com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.notification.RingerModeAffectedVolumePreferenceController
    protected boolean hintsMatch(int i) {
        return ((i & 1) != 0) || ((i & 2) != 0);
    }

    public NotificationVolumePreferenceController(Context context) {
        this(context, KEY_NOTIFICATION_VOLUME);
    }

    public NotificationVolumePreferenceController(Context context, String str) {
        super(context, str, TAG);
        this.mReceiver = new RingReceiver();
        this.mHandler = new H();
        this.mNormalIconId = R.drawable.ic_notifications;
        this.mVibrateIconId = R.drawable.ic_volume_ringer_vibrate;
        this.mSilentIconId = R.drawable.ic_notifications_off_24dp;
        if (updateRingerMode()) {
            updateEnabledState();
        }
    }

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (this.mPreference == null) {
            setupVolPreference(preferenceScreen);
        }
        updateEffectsSuppressor();
        selectPreferenceIconState();
        updateEnabledState();
    }

    public void onDeviceConfigChange(DeviceConfig.Properties properties) {
        boolean isSeparateNotificationConfigEnabled;
        if (!properties.getKeyset().contains("volume_separate_notification") || (isSeparateNotificationConfigEnabled = isSeparateNotificationConfigEnabled()) == this.mSeparateNotification) {
            return;
        }
        this.mSeparateNotification = isSeparateNotificationConfigEnabled;
        if (this.mPreference != null) {
            int availabilityStatus = getAvailabilityStatus();
            this.mPreference.setVisible(availabilityStatus == 0 || availabilityStatus == 5);
            if (availabilityStatus == 5) {
                this.mPreference.setEnabled(false);
            }
        }
    }

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        super.onResume();
        this.mReceiver.register(true);
        Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: com.android.settings.notification.NotificationVolumePreferenceController$$ExternalSyntheticLambda1
            public final void runOrThrow() {
                NotificationVolumePreferenceController.this.lambda$onResume$0();
            }
        });
    }

    public /* synthetic */ void lambda$onResume$0() throws Exception {
        DeviceConfig.addOnPropertiesChangedListener("systemui", ActivityThread.currentApplication().getMainExecutor(), new NotificationVolumePreferenceController$$ExternalSyntheticLambda2(this));
    }

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        super.onPause();
        this.mReceiver.register(false);
        Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: com.android.settings.notification.NotificationVolumePreferenceController$$ExternalSyntheticLambda0
            public final void runOrThrow() {
                NotificationVolumePreferenceController.this.lambda$onPause$1();
            }
        });
    }

    public /* synthetic */ void lambda$onPause$1() throws Exception {
        DeviceConfig.removeOnPropertiesChangedListener(new NotificationVolumePreferenceController$$ExternalSyntheticLambda2(this));
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        boolean isSeparateNotificationConfigEnabled = isSeparateNotificationConfigEnabled();
        if (this.mContext.getResources().getBoolean(R.bool.config_show_notification_volume) && !((VolumeSeekBarPreferenceController) this).mHelper.isSingleVolume() && isSeparateNotificationConfigEnabled) {
            return this.mRingerMode == 2 ? 0 : 5;
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.notification.RingerModeAffectedVolumePreferenceController
    public void selectPreferenceIconState() {
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            Vibrator vibrator = this.mVibrator;
            if (vibrator != null && this.mRingerMode == 1) {
                int i = this.mVibrateIconId;
                this.mMuteIcon = i;
                volumeSeekBarPreference.showIcon(i);
                return;
            }
            int i2 = this.mRingerMode;
            if (i2 == 0 || (vibrator == null && i2 == 1)) {
                int i3 = this.mSilentIconId;
                this.mMuteIcon = i3;
                volumeSeekBarPreference.showIcon(i3);
            } else if (((VolumeSeekBarPreferenceController) this).mHelper.getStreamVolume(5) == 0) {
                int i4 = this.mSilentIconId;
                this.mMuteIcon = i4;
                this.mPreference.showIcon(i4);
            } else {
                this.mPreference.showIcon(this.mNormalIconId);
            }
        }
    }

    public void updateEnabledState() {
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            volumeSeekBarPreference.setEnabled(this.mRingerMode == 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class H extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private H() {
            super(Looper.getMainLooper());
            NotificationVolumePreferenceController.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                NotificationVolumePreferenceController.this.updateEffectsSuppressor();
            } else if (i == 2) {
                if (NotificationVolumePreferenceController.this.updateRingerMode()) {
                    NotificationVolumePreferenceController.this.updateEnabledState();
                }
            } else if (i != 3) {
            } else {
                NotificationVolumePreferenceController.this.selectPreferenceIconState();
                NotificationVolumePreferenceController.this.updateEnabledState();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RingReceiver extends BroadcastReceiver {
        private boolean mRegistered;

        private RingReceiver() {
            NotificationVolumePreferenceController.this = r1;
        }

        public void register(boolean z) {
            if (this.mRegistered == z) {
                return;
            }
            if (!z) {
                ((AbstractPreferenceController) NotificationVolumePreferenceController.this).mContext.unregisterReceiver(this);
            } else {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED");
                intentFilter.addAction("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION");
                intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
                ((AbstractPreferenceController) NotificationVolumePreferenceController.this).mContext.registerReceiver(this, intentFilter);
            }
            this.mRegistered = z;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED".equals(action)) {
                NotificationVolumePreferenceController.this.mHandler.sendEmptyMessage(1);
            } else if ("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION".equals(action)) {
                NotificationVolumePreferenceController.this.mHandler.sendEmptyMessage(2);
            } else if ("android.media.VOLUME_CHANGED_ACTION".equals(action) && intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 5) {
                NotificationVolumePreferenceController.this.mHandler.obtainMessage(3, intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1), 0).sendToTarget();
            }
        }
    }
}
