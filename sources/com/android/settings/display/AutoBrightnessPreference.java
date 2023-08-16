package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import com.android.settingslib.PrimarySwitchPreference;
/* loaded from: classes.dex */
public class AutoBrightnessPreference extends PrimarySwitchPreference {
    private final AutoBrightnessObserver mAutoBrightnessObserver;
    private final Runnable mCallback;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        setChecked(Settings.System.getInt(getContext().getContentResolver(), "screen_brightness_mode", 1) == 1);
    }

    public AutoBrightnessPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCallback = new Runnable() { // from class: com.android.settings.display.AutoBrightnessPreference$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AutoBrightnessPreference.this.lambda$new$0();
            }
        };
        this.mAutoBrightnessObserver = new AutoBrightnessObserver(context);
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mAutoBrightnessObserver.subscribe(this.mCallback);
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        super.onDetached();
        this.mAutoBrightnessObserver.unsubscribe();
    }
}
