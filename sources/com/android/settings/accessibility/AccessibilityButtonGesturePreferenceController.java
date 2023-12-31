package com.android.settings.accessibility;

import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import com.google.common.primitives.Ints;
import java.util.Optional;
/* loaded from: classes.dex */
public class AccessibilityButtonGesturePreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private Optional<Integer> mDefaultGesture;

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AccessibilityButtonGesturePreferenceController(Context context, String str) {
        super(context, str);
        this.mDefaultGesture = Optional.empty();
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return AccessibilityUtil.isGestureNavigateEnabled(this.mContext) ? 0 : 2;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        ListPreference listPreference = (ListPreference) preference;
        Integer tryParse = Ints.tryParse((String) obj);
        if (tryParse != null) {
            Settings.Secure.putInt(this.mContext.getContentResolver(), "accessibility_button_mode", tryParse.intValue());
            updateState(listPreference);
            return true;
        }
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        ((ListPreference) preference).setValue(getCurrentAccessibilityButtonMode());
    }

    private String getCurrentAccessibilityButtonMode() {
        return String.valueOf(Settings.Secure.getInt(this.mContext.getContentResolver(), "accessibility_button_mode", getDefaultGestureValue()));
    }

    private int getDefaultGestureValue() {
        if (!this.mDefaultGesture.isPresent()) {
            this.mDefaultGesture = Optional.of(Integer.valueOf(Integer.parseInt(this.mContext.getResources().getStringArray(R.array.accessibility_button_gesture_selector_values)[0])));
        }
        return this.mDefaultGesture.get().intValue();
    }
}
