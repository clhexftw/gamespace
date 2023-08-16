package org.nameless.custom.colorpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import org.nameless.custom.R$dimen;
import org.nameless.custom.R$drawable;
import org.nameless.custom.R$layout;
import org.nameless.custom.colorpicker.ColorPickerDialog;
/* loaded from: classes2.dex */
public class ColorPickerPreference extends Preference implements Preference.OnPreferenceClickListener, ColorPickerDialog.OnColorChangedListener {
    private boolean mAlphaSliderEnabled;
    private final Context mContext;
    private int mCurrentValue;
    private int mDefaultValue;
    private float mDensity;
    ColorPickerDialog mDialog;
    private boolean mDividerAbove;
    private boolean mDividerBelow;
    private EditText mEditText;
    private boolean mShowPreview;
    private boolean mShowReset;
    private final Vibrator mVibrator;
    PreferenceViewHolder mView;
    LinearLayout mWidgetFrameView;

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        return false;
    }

    public ColorPickerPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ColorPickerPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPickerPreference(Context context) {
        this(context, null);
    }

    public ColorPickerPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDefaultValue = -16777216;
        this.mCurrentValue = -16777216;
        this.mDensity = 0.0f;
        this.mAlphaSliderEnabled = false;
        setLayoutResource(R$layout.preference_material_settings);
        init(context, attributeSet);
        this.mContext = context;
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, -16777216));
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(boolean z, Object obj) {
        if (obj == null) {
            obj = -16777216;
        }
        int persistedInt = getPersistedInt(((Integer) obj).intValue());
        this.mCurrentValue = persistedInt;
        onColorChanged(persistedInt);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mDensity = getContext().getResources().getDisplayMetrics().density;
        setOnPreferenceClickListener(this);
        if (attributeSet != null) {
            this.mAlphaSliderEnabled = attributeSet.getAttributeBooleanValue(null, "alphaSlider", false);
            this.mDefaultValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "defaultValue", -16777216);
            this.mShowReset = attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/com.android.settings", "showReset", true);
            this.mShowPreview = attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/com.android.settings", "showPreview", true);
            this.mDividerAbove = attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/com.android.settings", "dividerAbove", false);
            this.mDividerBelow = attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/com.android.settings", "dividerBelow", false);
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        this.mView = preferenceViewHolder;
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.setDividerAllowedAbove(this.mDividerAbove);
        preferenceViewHolder.setDividerAllowedBelow(this.mDividerBelow);
        preferenceViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: org.nameless.custom.colorpicker.ColorPickerPreference.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ColorPickerPreference.this.showDialog(null);
            }
        });
        LinearLayout linearLayout = (LinearLayout) preferenceViewHolder.findViewById(16908312);
        this.mWidgetFrameView = linearLayout;
        linearLayout.setOrientation(0);
        this.mWidgetFrameView.setVisibility(0);
        this.mWidgetFrameView.setMinimumWidth(0);
        LinearLayout linearLayout2 = this.mWidgetFrameView;
        linearLayout2.setPadding(linearLayout2.getPaddingLeft(), this.mWidgetFrameView.getPaddingTop(), (int) (this.mDensity * 8.0f), this.mWidgetFrameView.getPaddingBottom());
        setDefaultButton();
        setPreviewColor();
    }

    private void setDefaultButton() {
        LinearLayout linearLayout;
        if (!this.mShowReset || this.mView == null || (linearLayout = this.mWidgetFrameView) == null) {
            return;
        }
        if (linearLayout.getChildCount() > 0) {
            View findViewWithTag = this.mWidgetFrameView.findViewWithTag("default");
            View findViewWithTag2 = this.mWidgetFrameView.findViewWithTag("spacer");
            if (findViewWithTag != null) {
                this.mWidgetFrameView.removeView(findViewWithTag);
            }
            if (findViewWithTag2 != null) {
                this.mWidgetFrameView.removeView(findViewWithTag2);
            }
        }
        if (isEnabled()) {
            ImageView imageView = new ImageView(getContext());
            this.mWidgetFrameView.addView(imageView);
            imageView.setImageDrawable(getContext().getDrawable(R$drawable.ic_settings_backup_restore));
            imageView.setTag("default");
            imageView.setOnClickListener(new View.OnClickListener() { // from class: org.nameless.custom.colorpicker.ColorPickerPreference.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ColorPickerPreference colorPickerPreference = ColorPickerPreference.this;
                    colorPickerPreference.onColorChanged(colorPickerPreference.mDefaultValue);
                    ColorPickerPreference.this.doHapticFeedback();
                }
            });
            View view = new View(getContext());
            view.setTag("spacer");
            view.setLayoutParams(new LinearLayout.LayoutParams((int) (this.mDensity * 16.0f), -1));
            this.mWidgetFrameView.addView(view);
        }
    }

    private void setPreviewColor() {
        LinearLayout linearLayout;
        View findViewWithTag;
        if (!this.mShowPreview || this.mView == null || (linearLayout = this.mWidgetFrameView) == null) {
            return;
        }
        if (linearLayout.getChildCount() > 0 && (findViewWithTag = this.mWidgetFrameView.findViewWithTag("preview")) != null) {
            this.mWidgetFrameView.removeView(findViewWithTag);
        }
        if (isEnabled()) {
            ImageView imageView = new ImageView(getContext());
            this.mWidgetFrameView.addView(imageView);
            int dimension = (int) getContext().getResources().getDimension(R$dimen.oval_notification_size);
            int i = this.mCurrentValue;
            if ((i & 15790320) == 15790320) {
                i -= 1052688;
            }
            imageView.setImageDrawable(createOvalShape(dimension, i - 16777216));
            imageView.setTag("preview");
        }
    }

    @Override // androidx.preference.Preference
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setPreviewColor();
        setDefaultButton();
    }

    @Override // org.nameless.custom.colorpicker.ColorPickerDialog.OnColorChangedListener
    public void onColorChanged(int i) {
        this.mCurrentValue = i;
        setPreviewColor();
        persistInt(i);
        try {
            getOnPreferenceChangeListener().onPreferenceChange(this, Integer.valueOf(i));
        } catch (NullPointerException unused) {
        }
        try {
            this.mEditText.setText(Integer.toString(i, 16));
        } catch (NullPointerException unused2) {
        }
    }

    protected void showDialog(Bundle bundle) {
        ColorPickerDialog colorPickerDialog = new ColorPickerDialog(getContext(), this.mCurrentValue);
        this.mDialog = colorPickerDialog;
        colorPickerDialog.setOnColorChangedListener(this);
        if (this.mAlphaSliderEnabled) {
            this.mDialog.setAlphaSliderVisible(true);
        }
        if (bundle != null) {
            this.mDialog.onRestoreInstanceState(bundle);
        }
        this.mDialog.show();
        this.mDialog.getWindow().setSoftInputMode(2);
    }

    public void setNewPreviewColor(int i) {
        onColorChanged(i);
    }

    public void setDefaultValue(int i) {
        this.mDefaultValue = i;
    }

    public static String convertToRGB(int i) {
        String hexString = Integer.toHexString(Color.red(i));
        String hexString2 = Integer.toHexString(Color.green(i));
        String hexString3 = Integer.toHexString(Color.blue(i));
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        if (hexString2.length() == 1) {
            hexString2 = "0" + hexString2;
        }
        if (hexString3.length() == 1) {
            hexString3 = "0" + hexString3;
        }
        return "#" + hexString + hexString2 + hexString3;
    }

    public static int convertToColorInt(String str) throws NumberFormatException {
        int i;
        int i2;
        int i3;
        if (str.startsWith("#")) {
            str = str.replace("#", "");
        }
        int i4 = -1;
        if (str.length() == 8) {
            i4 = Integer.parseInt(str.substring(0, 2), 16);
            i2 = Integer.parseInt(str.substring(2, 4), 16);
            i3 = Integer.parseInt(str.substring(4, 6), 16);
            i = Integer.parseInt(str.substring(6, 8), 16);
        } else if (str.length() == 6) {
            i4 = 255;
            i2 = Integer.parseInt(str.substring(0, 2), 16);
            i3 = Integer.parseInt(str.substring(2, 4), 16);
            i = Integer.parseInt(str.substring(4, 6), 16);
        } else {
            i = -1;
            i2 = -1;
            i3 = -1;
        }
        return Color.argb(i4, i2, i3, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        ColorPickerDialog colorPickerDialog = this.mDialog;
        if (colorPickerDialog == null || !colorPickerDialog.isShowing()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.dialogBundle = this.mDialog.onSaveInstanceState();
        return savedState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        showDialog(savedState.dialogBundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: org.nameless.custom.colorpicker.ColorPickerPreference.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        Bundle dialogBundle;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.dialogBundle = parcel.readBundle();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.dialogBundle);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    private static ShapeDrawable createOvalShape(int i, int i2) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setIntrinsicHeight(i);
        shapeDrawable.setIntrinsicWidth(i);
        shapeDrawable.getPaint().setColor(i2);
        return shapeDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHapticFeedback() {
        if (Settings.System.getInt(this.mContext.getContentResolver(), "haptic_feedback_enabled", 1) != 0) {
            this.mVibrator.vibrate(VibrationEffect.get(0));
        }
    }
}
