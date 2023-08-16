package org.nameless.gamespace.gamebar;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
import org.nameless.gamespace.data.AppSettings;
/* compiled from: CallListener.kt */
/* loaded from: classes.dex */
public final class CallListener {
    public static final Companion Companion = new Companion(null);
    private final AppSettings appSettings;
    private final AudioManager audioManager;
    private int callStatus;
    private final int callsMode;
    private final Context context;
    private final Listener phoneStateListener;
    private final TelecomManager telecomManager;
    private final TelephonyManager telephonyManager;

    public CallListener(Context context, AppSettings appSettings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appSettings, "appSettings");
        this.context = context;
        this.appSettings = appSettings;
        this.audioManager = (AudioManager) context.getSystemService(AudioManager.class);
        this.telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        this.telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        this.callsMode = appSettings.getCallsMode();
        this.phoneStateListener = new Listener();
        this.callStatus = 2;
    }

    public final void init() {
        this.telephonyManager.listen(this.phoneStateListener, 32);
    }

    public final void destory() {
        this.telephonyManager.listen(this.phoneStateListener, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isHeadsetPluggedIn() {
        AudioDeviceInfo[] devices = this.audioManager.getDevices(2);
        Intrinsics.checkNotNullExpressionValue(devices, "audioManager.getDevices(…ager.GET_DEVICES_OUTPUTS)");
        for (AudioDeviceInfo audioDeviceInfo : devices) {
            if (audioDeviceInfo.getType() == 4 || audioDeviceInfo.getType() == 3 || audioDeviceInfo.getType() == 22) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.ANSWER_PHONE_CALLS") != 0) {
            Log.e("CallListener", "App does not have required permission ANSWER_PHONE_CALLS");
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CallListener.kt */
    /* loaded from: classes.dex */
    public final class Listener extends PhoneStateListener {
        private int previousAudioMode;
        private int previousState;

        public Listener() {
            this.previousAudioMode = CallListener.this.audioManager.getMode();
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String incomingNumber) {
            Intrinsics.checkNotNullParameter(incomingNumber, "incomingNumber");
            if (CallListener.this.callsMode == 0) {
                return;
            }
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        if (CallListener.this.callsMode == 2) {
                            return;
                        }
                        if (this.previousState == 1) {
                            if (CallListener.this.isHeadsetPluggedIn()) {
                                CallListener.this.audioManager.setSpeakerphoneOn(false);
                                AudioSystem.setForceUse(0, 0);
                            } else {
                                CallListener.this.audioManager.setSpeakerphoneOn(true);
                                AudioSystem.setForceUse(0, 1);
                            }
                            CallListener.this.audioManager.setMode(3);
                        }
                    }
                } else if (!CallListener.this.checkPermission()) {
                    return;
                } else {
                    if (CallListener.this.callsMode == 1) {
                        CallListener.this.telecomManager.acceptRingingCall();
                        Toast.makeText(CallListener.this.context, CallListener.this.context.getString(R.string.in_game_calls_received_number, incomingNumber), 0).show();
                    } else {
                        CallListener.this.telecomManager.endCall();
                        Toast.makeText(CallListener.this.context, CallListener.this.context.getString(R.string.in_game_calls_rejected_number, incomingNumber), 0).show();
                    }
                }
            } else if (CallListener.this.callsMode == 2) {
                return;
            } else {
                if (this.previousState == 2) {
                    CallListener.this.audioManager.setMode(this.previousAudioMode);
                    AudioSystem.setForceUse(0, 0);
                    CallListener.this.audioManager.setSpeakerphoneOn(false);
                }
            }
            this.previousState = i;
        }
    }

    /* compiled from: CallListener.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
