package com.android.settings.accessibility;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.VibrationAttributes;
import android.os.Vibrator;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import java.util.Set;
import org.nameless.vibrator.VibrationPatternManager;
/* loaded from: classes.dex */
public abstract class VibrationPatternPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private final Context mContext;
    private final Handler mHandler;
    private ListPreference mPreference;
    private final Runnable mVibrateRunnable;
    private final Vibrator mVibrator;

    public abstract VibrationAttributes getAttribute();

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    public abstract String getSettings();

    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    public abstract int getType();

    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public VibrationPatternPreferenceController(Context context, String str) {
        super(context, str);
        this.mVibrateRunnable = new Runnable() { // from class: com.android.settings.accessibility.VibrationPatternPreferenceController.1
            @Override // java.lang.Runnable
            public void run() {
                VibrationPatternPreferenceController.this.mVibrator.vibrate(VibrationPatternManager.getPreviewVibrationFromNumber(VibrationPatternPreferenceController.this.getPatternNumber(), VibrationPatternPreferenceController.this.getType()), VibrationPatternPreferenceController.this.getAttribute());
            }
        };
        this.mContext = context;
        this.mHandler = new Handler();
        this.mVibrator = (Vibrator) context.getSystemService(Vibrator.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPatternNumber() {
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), getSettings(), 0, -2);
        if (intForUser >= VibrationPatternManager.getPatternsSize(getType())) {
            return 0;
        }
        return intForUser;
    }

    private void setPatternNumber(int i) {
        Settings.System.putIntForUser(this.mContext.getContentResolver(), getSettings(), i, -2);
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ListPreference) preferenceScreen.findPreference(getPreferenceKey());
        Set<Integer> patternsNameIdSet = VibrationPatternManager.getPatternsNameIdSet(getType());
        CharSequence[] charSequenceArr = new CharSequence[patternsNameIdSet.size()];
        int i = 0;
        for (Integer num : patternsNameIdSet) {
            charSequenceArr[i] = this.mContext.getResources().getString(num.intValue());
            i++;
        }
        CharSequence[] charSequenceArr2 = new CharSequence[patternsNameIdSet.size()];
        for (int i2 = 0; i2 < patternsNameIdSet.size(); i2++) {
            charSequenceArr2[i2] = String.valueOf(i2);
        }
        this.mPreference.setEntries(charSequenceArr);
        this.mPreference.setEntryValues(charSequenceArr2);
        this.mPreference.setValue(String.valueOf(getPatternNumber()));
        ListPreference listPreference = this.mPreference;
        listPreference.setSummary(listPreference.getEntry());
        this.mPreference.setOnPreferenceChangeListener(this);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public final boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mPreference) {
            int parseInt = Integer.parseInt(obj.toString());
            setPatternNumber(parseInt);
            this.mHandler.post(this.mVibrateRunnable);
            this.mPreference.setSummary(this.mPreference.getEntries()[parseInt]);
            return true;
        }
        return true;
    }
}
