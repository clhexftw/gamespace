package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
/* loaded from: classes.dex */
public final class CompoundButtonCompat {
    public static void setButtonTintList(CompoundButton compoundButton, ColorStateList colorStateList) {
        Api21Impl.setButtonTintList(compoundButton, colorStateList);
    }

    public static ColorStateList getButtonTintList(CompoundButton compoundButton) {
        return Api21Impl.getButtonTintList(compoundButton);
    }

    public static void setButtonTintMode(CompoundButton compoundButton, PorterDuff.Mode mode) {
        Api21Impl.setButtonTintMode(compoundButton, mode);
    }

    public static Drawable getButtonDrawable(CompoundButton compoundButton) {
        return Api23Impl.getButtonDrawable(compoundButton);
    }

    /* loaded from: classes.dex */
    static class Api21Impl {
        static void setButtonTintList(CompoundButton compoundButton, ColorStateList colorStateList) {
            compoundButton.setButtonTintList(colorStateList);
        }

        static ColorStateList getButtonTintList(CompoundButton compoundButton) {
            return compoundButton.getButtonTintList();
        }

        static void setButtonTintMode(CompoundButton compoundButton, PorterDuff.Mode mode) {
            compoundButton.setButtonTintMode(mode);
        }
    }

    /* loaded from: classes.dex */
    static class Api23Impl {
        static Drawable getButtonDrawable(CompoundButton compoundButton) {
            return compoundButton.getButtonDrawable();
        }
    }
}
