package com.android.settings.gestures;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.gestures.PowerMenuSettingsUtils;
import com.android.settingslib.widget.SelectorWithWidgetPreference;
/* loaded from: classes.dex */
public class LongPressPowerForAssistantPreferenceController extends BasePreferenceController implements PowerMenuSettingsUtils.SettingsStateCallback, SelectorWithWidgetPreference.OnClickListener, LifecycleObserver {
    private SelectorWithWidgetPreference mPreference;
    private final PowerMenuSettingsUtils mUtils;

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

    public LongPressPowerForAssistantPreferenceController(Context context, String str) {
        super(context, str);
        this.mUtils = new PowerMenuSettingsUtils(context);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return PowerMenuSettingsUtils.isLongPressPowerSettingAvailable(this.mContext) ? 0 : 3;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        SelectorWithWidgetPreference selectorWithWidgetPreference = (SelectorWithWidgetPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = selectorWithWidgetPreference;
        if (selectorWithWidgetPreference != null) {
            selectorWithWidgetPreference.setOnClickListener(this);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (preference instanceof SelectorWithWidgetPreference) {
            ((SelectorWithWidgetPreference) preference).setChecked(PowerMenuSettingsUtils.isLongPressPowerForAssistantEnabled(this.mContext));
        }
    }

    @Override // com.android.settingslib.widget.SelectorWithWidgetPreference.OnClickListener
    public void onRadioButtonClicked(SelectorWithWidgetPreference selectorWithWidgetPreference) {
        PowerMenuSettingsUtils.setLongPressPowerForAssistant(this.mContext);
        SelectorWithWidgetPreference selectorWithWidgetPreference2 = this.mPreference;
        if (selectorWithWidgetPreference2 != null) {
            updateState(selectorWithWidgetPreference2);
        }
    }

    @Override // com.android.settings.gestures.PowerMenuSettingsUtils.SettingsStateCallback
    public void onChange(Uri uri) {
        SelectorWithWidgetPreference selectorWithWidgetPreference = this.mPreference;
        if (selectorWithWidgetPreference != null) {
            updateState(selectorWithWidgetPreference);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        this.mUtils.registerObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        this.mUtils.unregisterObserver();
    }
}
