package org.nameless.custom.preference;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.R$attr;
import org.nameless.vibrator.CustomVibrationAttributes;
/* loaded from: classes.dex */
public class SwitchPreference extends androidx.preference.SwitchPreference {
    private static final VibrationEffect EFFECT_CLICK = VibrationEffect.createPredefined(0);
    private final Vibrator mVibrator;

    public SwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.switchPreferenceStyle, 16843629));
    }

    public SwitchPreference(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    public void performClick(View view) {
        super.performClick(view);
        this.mVibrator.vibrate(EFFECT_CLICK, CustomVibrationAttributes.VIBRATION_ATTRIBUTES_SWITCH);
    }
}
