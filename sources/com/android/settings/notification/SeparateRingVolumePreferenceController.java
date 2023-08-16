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
import android.provider.DeviceConfig;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.PreferenceScreen;
import com.android.internal.util.FunctionalUtils;
import com.android.settings.R;
import com.android.settingslib.core.AbstractPreferenceController;
/* loaded from: classes.dex */
public class SeparateRingVolumePreferenceController extends RingerModeAffectedVolumePreferenceController {
    private static final String KEY_SEPARATE_RING_VOLUME = "separate_ring_volume";
    private static final String TAG = "SeparateRingVolumePreferenceController";
    private final H mHandler;
    private final RingReceiver mReceiver;

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController
    public int getAudioStream() {
        return 2;
    }

    @Override // com.android.settings.notification.RingerModeAffectedVolumePreferenceController, com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_SEPARATE_RING_VOLUME;
    }

    @Override // com.android.settings.notification.RingerModeAffectedVolumePreferenceController, com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.notification.RingerModeAffectedVolumePreferenceController
    protected boolean hintsMatch(int i) {
        return ((i & 4) == 0 && (i & 1) == 0) ? false : true;
    }

    public SeparateRingVolumePreferenceController(Context context) {
        this(context, KEY_SEPARATE_RING_VOLUME);
    }

    public SeparateRingVolumePreferenceController(Context context, String str) {
        super(context, str, TAG);
        this.mReceiver = new RingReceiver();
        this.mHandler = new H();
        this.mNormalIconId = R.drawable.ic_ring_volume;
        this.mVibrateIconId = R.drawable.ic_volume_ringer_vibrate;
        this.mSilentIconId = R.drawable.ic_ring_volume_off;
        updateRingerMode();
    }

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (this.mPreference == null) {
            setupVolPreference(preferenceScreen);
        }
        updateEffectsSuppressor();
        selectPreferenceIconState();
    }

    public void onDeviceConfigChange(DeviceConfig.Properties properties) {
        boolean isSeparateNotificationConfigEnabled;
        if (!properties.getKeyset().contains("volume_separate_notification") || (isSeparateNotificationConfigEnabled = isSeparateNotificationConfigEnabled()) == this.mSeparateNotification) {
            return;
        }
        this.mSeparateNotification = isSeparateNotificationConfigEnabled;
        if (this.mPreference != null) {
            this.mPreference.setVisible(getAvailabilityStatus() == 0);
        }
    }

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        super.onResume();
        this.mReceiver.register(true);
        Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: com.android.settings.notification.SeparateRingVolumePreferenceController$$ExternalSyntheticLambda1
            public final void runOrThrow() {
                SeparateRingVolumePreferenceController.this.lambda$onResume$0();
            }
        });
    }

    public /* synthetic */ void lambda$onResume$0() throws Exception {
        DeviceConfig.addOnPropertiesChangedListener("systemui", ActivityThread.currentApplication().getMainExecutor(), new SeparateRingVolumePreferenceController$$ExternalSyntheticLambda0(this));
    }

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        super.onPause();
        this.mReceiver.register(false);
        Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: com.android.settings.notification.SeparateRingVolumePreferenceController$$ExternalSyntheticLambda2
            public final void runOrThrow() {
                SeparateRingVolumePreferenceController.this.lambda$onPause$1();
            }
        });
    }

    public /* synthetic */ void lambda$onPause$1() throws Exception {
        DeviceConfig.removeOnPropertiesChangedListener(new SeparateRingVolumePreferenceController$$ExternalSyntheticLambda0(this));
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (!isSeparateNotificationConfigEnabled() || ((VolumeSeekBarPreferenceController) this).mHelper.isSingleVolume()) ? 3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class H extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private H() {
            super(Looper.getMainLooper());
            SeparateRingVolumePreferenceController.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                SeparateRingVolumePreferenceController.this.updateEffectsSuppressor();
            } else if (i != 2) {
            } else {
                SeparateRingVolumePreferenceController.this.updateRingerMode();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RingReceiver extends BroadcastReceiver {
        private boolean mRegistered;

        private RingReceiver() {
            SeparateRingVolumePreferenceController.this = r1;
        }

        public void register(boolean z) {
            if (this.mRegistered == z) {
                return;
            }
            if (!z) {
                ((AbstractPreferenceController) SeparateRingVolumePreferenceController.this).mContext.unregisterReceiver(this);
            } else {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED");
                intentFilter.addAction("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION");
                ((AbstractPreferenceController) SeparateRingVolumePreferenceController.this).mContext.registerReceiver(this, intentFilter);
            }
            this.mRegistered = z;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED".equals(action)) {
                SeparateRingVolumePreferenceController.this.mHandler.sendEmptyMessage(1);
            } else if ("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION".equals(action)) {
                SeparateRingVolumePreferenceController.this.mHandler.sendEmptyMessage(2);
            }
        }
    }
}
