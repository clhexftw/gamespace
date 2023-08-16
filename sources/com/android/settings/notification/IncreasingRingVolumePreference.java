package com.android.settings.notification;

import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.R;
/* loaded from: classes.dex */
public class IncreasingRingVolumePreference extends Preference implements Handler.Callback, SeekBar.OnSeekBarChangeListener {
    private Callback mCallback;
    private Handler mHandler;
    private final Handler mMainHandler;
    private SeekBar mRampUpTimeSeekBar;
    private TextView mRampUpTimeValue;
    private Ringtone mRingtone;
    private SeekBar mStartVolumeSeekBar;

    /* loaded from: classes.dex */
    public interface Callback {
        void onSampleStarting(IncreasingRingVolumePreference increasingRingVolumePreference);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public IncreasingRingVolumePreference(Context context) {
        this(context, null);
    }

    public IncreasingRingVolumePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R.attr.customPreferenceStyle, 16842894));
    }

    public IncreasingRingVolumePreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public IncreasingRingVolumePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mMainHandler = new Handler(this);
        setLayoutResource(R.layout.preference_increasing_ring);
        initHandler();
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void onActivityResume() {
        initHandler();
    }

    public void onActivityStop() {
        if (this.mHandler != null) {
            postStopSample();
            this.mHandler.getLooper().quitSafely();
            this.mHandler = null;
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            onStartSample(message.arg1 / 1000.0f);
        } else if (i == 2) {
            onStopSample();
        } else if (i == 3) {
            onInitSample();
        } else if (i == 4) {
            onSetVolume(message.arg1 / 1000.0f);
        }
        return true;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        initHandler();
        SeekBar seekBar = (SeekBar) preferenceViewHolder.findViewById(R.id.start_volume);
        if (seekBar == this.mStartVolumeSeekBar) {
            return;
        }
        this.mStartVolumeSeekBar = seekBar;
        this.mRampUpTimeSeekBar = (SeekBar) preferenceViewHolder.findViewById(R.id.ramp_up_time);
        this.mRampUpTimeValue = (TextView) preferenceViewHolder.findViewById(R.id.ramp_up_time_value);
        ContentResolver contentResolver = getContext().getContentResolver();
        float floatForUser = Settings.System.getFloatForUser(contentResolver, "increasing_ring_start_vol", 0.1f, -2);
        int intForUser = Settings.System.getIntForUser(contentResolver, "increasing_ring_ramp_up_time", 10, -2);
        this.mStartVolumeSeekBar.setProgress(Math.round(floatForUser * 1000.0f));
        this.mStartVolumeSeekBar.setOnSeekBarChangeListener(this);
        this.mRampUpTimeSeekBar.setOnSeekBarChangeListener(this);
        this.mRampUpTimeSeekBar.setProgress((intForUser / 5) - 1);
        this.mRampUpTimeValue.setText(Formatter.formatShortElapsedTime(getContext(), intForUser * 1000));
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar == this.mStartVolumeSeekBar) {
            postStartSample(seekBar.getProgress());
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        ContentResolver contentResolver = getContext().getContentResolver();
        if (z && seekBar == this.mStartVolumeSeekBar) {
            Settings.System.putFloatForUser(contentResolver, "increasing_ring_start_vol", i / 1000.0f, -2);
        } else if (seekBar == this.mRampUpTimeSeekBar) {
            int i2 = (i + 1) * 5;
            this.mRampUpTimeValue.setText(Formatter.formatShortElapsedTime(getContext(), i2 * 1000));
            if (z) {
                Settings.System.putIntForUser(contentResolver, "increasing_ring_ramp_up_time", i2, -2);
            }
        }
    }

    private void initHandler() {
        if (this.mHandler != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("IncreasingRingMinVolumePreference.CallbackHandler");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), this);
        this.mHandler = handler;
        handler.sendEmptyMessage(3);
    }

    private void onInitSample() {
        Ringtone ringtone = RingtoneManager.getRingtone(getContext(), Settings.System.DEFAULT_RINGTONE_URI);
        this.mRingtone = ringtone;
        if (ringtone != null) {
            ringtone.setStreamType(2);
            this.mRingtone.setAudioAttributes(new AudioAttributes.Builder(this.mRingtone.getAudioAttributes()).setFlags(192).build());
        }
    }

    private void postStartSample(int i) {
        boolean isSamplePlaying = isSamplePlaying();
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(4);
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1, i, 0), isSamplePlaying ? 1000L : 0L);
        if (isSamplePlaying) {
            Handler handler2 = this.mHandler;
            handler2.sendMessage(handler2.obtainMessage(4, i, 0));
        }
    }

    private void onStartSample(float f) {
        if (this.mRingtone == null) {
            return;
        }
        if (!isSamplePlaying()) {
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.onSampleStarting(this);
            }
            try {
                this.mRingtone.play();
            } catch (Throwable th) {
                Log.w("IncreasingRingMinVolumePreference", "Error playing ringtone", th);
            }
        }
        this.mRingtone.setVolume(f);
    }

    private void onSetVolume(float f) {
        Ringtone ringtone = this.mRingtone;
        if (ringtone != null) {
            ringtone.setVolume(f);
        }
    }

    private boolean isSamplePlaying() {
        Ringtone ringtone = this.mRingtone;
        return ringtone != null && ringtone.isPlaying();
    }

    public void stopSample() {
        if (this.mHandler != null) {
            postStopSample();
        }
    }

    private void postStopSample() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessage(2);
    }

    private void onStopSample() {
        Ringtone ringtone = this.mRingtone;
        if (ringtone != null) {
            ringtone.stop();
        }
    }
}
