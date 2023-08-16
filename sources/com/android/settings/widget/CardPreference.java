package com.android.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.R;
import java.util.Optional;
import java.util.function.Consumer;
/* loaded from: classes.dex */
public class CardPreference extends Preference {
    private Optional<View> mButtonsGroup;
    private View.OnClickListener mPrimaryBtnClickListener;
    private Optional<Button> mPrimaryButton;
    private String mPrimaryButtonText;
    private boolean mPrimaryButtonVisible;
    private View.OnClickListener mSecondaryBtnClickListener;
    private Optional<Button> mSecondaryButton;
    private String mSecondaryButtonText;
    private boolean mSecondaryButtonVisible;

    public CardPreference(Context context) {
        this(context, null);
    }

    public CardPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.cardPreferenceStyle);
        this.mPrimaryBtnClickListener = null;
        this.mSecondaryBtnClickListener = null;
        this.mPrimaryButtonText = null;
        this.mSecondaryButtonText = null;
        this.mPrimaryButton = Optional.empty();
        this.mSecondaryButton = Optional.empty();
        this.mButtonsGroup = Optional.empty();
        this.mPrimaryButtonVisible = false;
        this.mSecondaryButtonVisible = false;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        initButtonsAndLayout(preferenceViewHolder);
    }

    private void initButtonsAndLayout(PreferenceViewHolder preferenceViewHolder) {
        this.mPrimaryButton = Optional.ofNullable((Button) preferenceViewHolder.findViewById(16908313));
        this.mSecondaryButton = Optional.ofNullable((Button) preferenceViewHolder.findViewById(16908314));
        this.mButtonsGroup = Optional.ofNullable(preferenceViewHolder.findViewById(R.id.card_preference_buttons));
        setPrimaryButtonText(this.mPrimaryButtonText);
        setPrimaryButtonClickListener(this.mPrimaryBtnClickListener);
        setPrimaryButtonVisible(this.mPrimaryButtonVisible);
        setSecondaryButtonText(this.mSecondaryButtonText);
        setSecondaryButtonClickListener(this.mSecondaryBtnClickListener);
        setSecondaryButtonVisible(this.mSecondaryButtonVisible);
    }

    public void setPrimaryButtonClickListener(final View.OnClickListener onClickListener) {
        this.mPrimaryButton.ifPresent(new Consumer() { // from class: com.android.settings.widget.CardPreference$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Button) obj).setOnClickListener(onClickListener);
            }
        });
        this.mPrimaryBtnClickListener = onClickListener;
    }

    public void setSecondaryButtonClickListener(final View.OnClickListener onClickListener) {
        this.mSecondaryButton.ifPresent(new Consumer() { // from class: com.android.settings.widget.CardPreference$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Button) obj).setOnClickListener(onClickListener);
            }
        });
        this.mSecondaryBtnClickListener = onClickListener;
    }

    public void setPrimaryButtonText(final String str) {
        this.mPrimaryButton.ifPresent(new Consumer() { // from class: com.android.settings.widget.CardPreference$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Button) obj).setText(str);
            }
        });
        this.mPrimaryButtonText = str;
    }

    public void setSecondaryButtonText(final String str) {
        this.mSecondaryButton.ifPresent(new Consumer() { // from class: com.android.settings.widget.CardPreference$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Button) obj).setText(str);
            }
        });
        this.mSecondaryButtonText = str;
    }

    public void setPrimaryButtonVisible(final boolean z) {
        this.mPrimaryButton.ifPresent(new Consumer() { // from class: com.android.settings.widget.CardPreference$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CardPreference.lambda$setPrimaryButtonVisible$4(z, (Button) obj);
            }
        });
        this.mPrimaryButtonVisible = z;
        updateButtonGroupsVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setPrimaryButtonVisible$4(boolean z, Button button) {
        button.setVisibility(z ? 0 : 8);
    }

    public void setSecondaryButtonVisible(final boolean z) {
        this.mSecondaryButton.ifPresent(new Consumer() { // from class: com.android.settings.widget.CardPreference$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CardPreference.lambda$setSecondaryButtonVisible$5(z, (Button) obj);
            }
        });
        this.mSecondaryButtonVisible = z;
        updateButtonGroupsVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setSecondaryButtonVisible$5(boolean z, Button button) {
        button.setVisibility(z ? 0 : 8);
    }

    public void setSecondaryButtonContentDescription(final String str) {
        this.mSecondaryButton.ifPresent(new Consumer() { // from class: com.android.settings.widget.CardPreference$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Button) obj).setContentDescription(str);
            }
        });
    }

    private void updateButtonGroupsVisibility() {
        final int i = (this.mPrimaryButtonVisible || this.mSecondaryButtonVisible) ? 0 : 8;
        this.mButtonsGroup.ifPresent(new Consumer() { // from class: com.android.settings.widget.CardPreference$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((View) obj).setVisibility(i);
            }
        });
    }
}
