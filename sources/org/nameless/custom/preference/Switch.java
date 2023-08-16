package org.nameless.custom.preference;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import org.nameless.vibrator.CustomVibrationAttributes;
/* loaded from: classes2.dex */
public class Switch extends android.widget.Switch {
    private static final VibrationEffect EFFECT_CLICK = VibrationEffect.createPredefined(0);
    private final Vibrator mVibrator;

    public Switch(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mVibrator = (Vibrator) context.getSystemService(Vibrator.class);
    }

    public Switch(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public Switch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843839);
    }

    public Switch(Context context) {
        this(context, null);
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        super.toggle();
        this.mVibrator.vibrate(EFFECT_CLICK, CustomVibrationAttributes.VIBRATION_ATTRIBUTES_SWITCH);
    }
}
