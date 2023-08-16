package com.android.settings.accessibility;

import android.content.Context;
import android.content.IntentFilter;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.accessibility.CustomVibrationPreferenceConfig;
import com.android.settings.core.SliderPreferenceController;
import com.android.settings.widget.LabeledSeekBarPreference;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import org.nameless.vibrator.VibratorExtManager;
import vendor.nameless.hardware.vibratorExt.V1_0.LevelRange;
/* loaded from: classes.dex */
public abstract class VibrationStrengthSeekBarPreferenceController extends SliderPreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final VibrationAttributes ACCESSIBILITY_VIBRATION_ATTRIBUTES = new VibrationAttributes.Builder(VibrationAttributes.createForUsage(66)).setFlags(2).build();
    private static final int AMPLITUDE_BASE = 30;
    private static final int AMPLITUDE_MAX = 255;
    private static final long VIBRATION_DURATION = 30;
    private final VibratorExtManager.StrengthLevelChangedCallback mCallback;
    private final LevelRange mLevelRange;
    private final CustomVibrationPreferenceConfig mPreferenceConfig;
    private final CustomVibrationPreferenceConfig.SettingObserver mSettingsContentObserver;
    protected final Vibrator mVibrator;
    private final VibratorExtManager mVibratorExtManager;

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public int getMin() {
        return 1;
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    public abstract int getVibrationType();

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public VibrationStrengthSeekBarPreferenceController(Context context, String str) {
        super(context, str);
        this.mCallback = new VibratorExtManager.StrengthLevelChangedCallback() { // from class: com.android.settings.accessibility.VibrationStrengthSeekBarPreferenceController.1
            public void onStrengthLevelChanged() {
                VibrationStrengthSeekBarPreferenceController.this.mVibrator.vibrate(VibrationEffect.createOneShot(VibrationStrengthSeekBarPreferenceController.VIBRATION_DURATION, ((int) ((225.0f / (VibrationStrengthSeekBarPreferenceController.this.getMax() - VibrationStrengthSeekBarPreferenceController.this.getMin())) * (VibrationStrengthSeekBarPreferenceController.this.getSliderPosition() - VibrationStrengthSeekBarPreferenceController.this.getMin()))) + 30), VibrationStrengthSeekBarPreferenceController.ACCESSIBILITY_VIBRATION_ATTRIBUTES);
            }
        };
        VibratorExtManager vibratorExtManager = VibratorExtManager.getInstance();
        this.mVibratorExtManager = vibratorExtManager;
        this.mLevelRange = vibratorExtManager.getStrengthLevelRange(getVibrationType());
        CustomVibrationPreferenceConfig customVibrationPreferenceConfig = new CustomVibrationPreferenceConfig(context, "vibrate_on", vibratorExtManager.vibrationTypeToSettings(getVibrationType()));
        this.mPreferenceConfig = customVibrationPreferenceConfig;
        this.mSettingsContentObserver = new CustomVibrationPreferenceConfig.SettingObserver(customVibrationPreferenceConfig);
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mSettingsContentObserver.register(this.mContext);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mSettingsContentObserver.unregister(this.mContext);
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        LabeledSeekBarPreference labeledSeekBarPreference = (LabeledSeekBarPreference) preferenceScreen.findPreference(getPreferenceKey());
        labeledSeekBarPreference.setContinuousUpdates(true);
        labeledSeekBarPreference.setMax(getMax());
        labeledSeekBarPreference.setMin(getMin());
        this.mSettingsContentObserver.onDisplayPreference(this, labeledSeekBarPreference);
        labeledSeekBarPreference.setEnabled(this.mPreferenceConfig.isPreferenceEnabled());
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mVibratorExtManager.getValidVibrationTypes().contains(Integer.valueOf(getVibrationType())) ? 0 : 3;
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(this.mPreferenceConfig.isPreferenceEnabled());
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public int getSliderPosition() {
        LevelRange levelRange = this.mLevelRange;
        if (levelRange != null) {
            return this.mPreferenceConfig.readValue(levelRange.defaultLevel);
        }
        return 0;
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public boolean setSliderPosition(int i) {
        this.mPreferenceConfig.setValue(i);
        this.mVibratorExtManager.setStrengthLevel(getVibrationType(), i, this.mCallback);
        return true;
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public int getMax() {
        LevelRange levelRange = this.mLevelRange;
        if (levelRange != null) {
            return levelRange.maxLevel;
        }
        return 0;
    }
}
