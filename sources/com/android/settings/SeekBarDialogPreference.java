package com.android.settings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.settingslib.CustomDialogPreferenceCompat;
/* loaded from: classes.dex */
public class SeekBarDialogPreference extends CustomDialogPreferenceCompat {
    private final Drawable mMyIcon;
    private TextView mTextView;

    public SeekBarDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(R.layout.preference_dialog_seekbar_material);
        createActionButtons();
        this.mMyIcon = getDialogIcon();
        setDialogIcon(null);
    }

    public SeekBarDialogPreference(Context context) {
        this(context, null);
    }

    public void createActionButtons() {
        setPositiveButtonText(17039370);
        setNegativeButtonText(17039360);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    public void onBindDialogView(View view) {
        super.onBindDialogView(view);
        ImageView imageView = (ImageView) view.findViewById(16908294);
        Drawable drawable = this.mMyIcon;
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        } else {
            imageView.setVisibility(8);
        }
        this.mTextView = (TextView) view.findViewById(R.id.text);
    }

    public void setText(String str) {
        if (this.mTextView != null) {
            if (TextUtils.isEmpty(str)) {
                this.mTextView.setVisibility(8);
            } else {
                this.mTextView.setVisibility(0);
            }
            this.mTextView.setText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static SeekBar getSeekBar(View view) {
        return (SeekBar) view.findViewById(R.id.seekbar);
    }
}
