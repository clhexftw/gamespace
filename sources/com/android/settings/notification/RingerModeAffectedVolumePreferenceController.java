package com.android.settings.notification;

import android.app.INotificationManager;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.Vibrator;
import android.provider.DeviceConfig;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.FunctionalUtils;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class RingerModeAffectedVolumePreferenceController extends VolumeSeekBarPreferenceController {
    private static final boolean CONFIG_SEPARATE_NOTIFICATION_DEFAULT_VAL = false;
    protected int mMuteIcon;
    protected INotificationManager mNoMan;
    protected int mNormalIconId;
    protected int mRingerMode;
    protected boolean mSeparateNotification;
    protected int mSilentIconId;
    protected ComponentName mSuppressor;
    private final String mTag;
    protected int mVibrateIconId;
    protected Vibrator mVibrator;

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    protected abstract boolean hintsMatch(int i);

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public boolean isPublicSlice() {
        return true;
    }

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public boolean isSliceable() {
        return true;
    }

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController, com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public boolean useDynamicSliceSummary() {
        return true;
    }

    public RingerModeAffectedVolumePreferenceController(Context context, String str, String str2) {
        super(context, str);
        this.mRingerMode = 2;
        this.mTag = str2;
        Vibrator vibrator = (Vibrator) this.mContext.getSystemService(Vibrator.class);
        this.mVibrator = vibrator;
        if (vibrator == null || vibrator.hasVibrator()) {
            return;
        }
        this.mVibrator = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateEffectsSuppressor() {
        ComponentName effectsSuppressor = NotificationManager.from(this.mContext).getEffectsSuppressor();
        if (Objects.equals(effectsSuppressor, this.mSuppressor)) {
            return;
        }
        if (this.mNoMan == null) {
            this.mNoMan = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        }
        try {
            if (hintsMatch(this.mNoMan.getHintsFromListenerNoToken())) {
                this.mSuppressor = effectsSuppressor;
                if (this.mPreference != null) {
                    this.mPreference.setSuppressionText(SuppressorHelper.getSuppressionText(this.mContext, effectsSuppressor));
                }
            }
        } catch (RemoteException e) {
            String str = this.mTag;
            Log.w(str, "updateEffectsSuppressor: " + e.getMessage());
        }
    }

    @VisibleForTesting
    void setPreference(VolumeSeekBarPreference volumeSeekBarPreference) {
        this.mPreference = volumeSeekBarPreference;
    }

    @VisibleForTesting
    void setVibrator(Vibrator vibrator) {
        this.mVibrator = vibrator;
    }

    @Override // com.android.settings.notification.VolumeSeekBarPreferenceController
    public int getMuteIcon() {
        return this.mMuteIcon;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isSeparateNotificationConfigEnabled() {
        return ((Boolean) Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingSupplier() { // from class: com.android.settings.notification.RingerModeAffectedVolumePreferenceController$$ExternalSyntheticLambda0
            public final Object getOrThrow() {
                Boolean lambda$isSeparateNotificationConfigEnabled$0;
                lambda$isSeparateNotificationConfigEnabled$0 = RingerModeAffectedVolumePreferenceController.lambda$isSeparateNotificationConfigEnabled$0();
                return lambda$isSeparateNotificationConfigEnabled$0;
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$isSeparateNotificationConfigEnabled$0() throws Exception {
        return Boolean.valueOf(DeviceConfig.getBoolean("systemui", "volume_separate_notification", false));
    }

    protected boolean readSeparateNotificationVolumeConfig() {
        boolean isSeparateNotificationConfigEnabled = isSeparateNotificationConfigEnabled();
        boolean z = isSeparateNotificationConfigEnabled != this.mSeparateNotification;
        if (z) {
            this.mSeparateNotification = isSeparateNotificationConfigEnabled;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean updateRingerMode() {
        int ringerModeInternal = ((VolumeSeekBarPreferenceController) this).mHelper.getRingerModeInternal();
        if (this.mRingerMode == ringerModeInternal) {
            return false;
        }
        this.mRingerMode = ringerModeInternal;
        selectPreferenceIconState();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void selectPreferenceIconState() {
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            int i = this.mRingerMode;
            if (i == 2) {
                volumeSeekBarPreference.showIcon(this.mNormalIconId);
                return;
            }
            if (i == 1 && this.mVibrator != null) {
                this.mMuteIcon = this.mVibrateIconId;
            } else {
                this.mMuteIcon = this.mSilentIconId;
            }
            volumeSeekBarPreference.showIcon(getMuteIcon());
        }
    }
}
