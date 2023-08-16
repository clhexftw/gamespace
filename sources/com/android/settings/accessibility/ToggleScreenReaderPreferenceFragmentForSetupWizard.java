package com.android.settings.accessibility;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.R;
import com.android.settingslib.widget.TopIntroPreference;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupdesign.GlifPreferenceLayout;
/* loaded from: classes.dex */
public class ToggleScreenReaderPreferenceFragmentForSetupWizard extends ToggleAccessibilityServicePreferenceFragment {
    private boolean mToggleSwitchWasInitiallyChecked;

    @Override // com.android.settings.accessibility.ToggleAccessibilityServicePreferenceFragment, com.android.settings.accessibility.ToggleFeaturePreferenceFragment, com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 371;
    }

    @Override // com.android.settings.accessibility.ToggleFeaturePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        GlifPreferenceLayout glifPreferenceLayout = (GlifPreferenceLayout) view;
        AccessibilitySetupWizardUtils.updateGlifPreferenceLayout(getContext(), glifPreferenceLayout, getArguments().getString("title"), getContext().getString(R.string.talkback_summary), getContext().getDrawable(R.drawable.ic_accessibility_visibility));
        AccessibilitySetupWizardUtils.setPrimaryButton(getContext(), (FooterBarMixin) glifPreferenceLayout.getMixin(FooterBarMixin.class), R.string.done, new Runnable() { // from class: com.android.settings.accessibility.ToggleScreenReaderPreferenceFragmentForSetupWizard$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ToggleScreenReaderPreferenceFragmentForSetupWizard.this.lambda$onViewCreated$0();
            }
        });
        this.mToggleSwitchWasInitiallyChecked = this.mToggleServiceSwitchPreference.isChecked();
        TopIntroPreference topIntroPreference = this.mTopIntroPreference;
        if (topIntroPreference != null) {
            topIntroPreference.setVisible(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0() {
        setResult(0);
        finish();
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return ((GlifPreferenceLayout) viewGroup).onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.android.settings.accessibility.ToggleAccessibilityServicePreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        if (this.mToggleServiceSwitchPreference.isChecked() != this.mToggleSwitchWasInitiallyChecked) {
            this.mMetricsFeatureProvider.action(getContext(), 371, this.mToggleServiceSwitchPreference.isChecked());
        }
        super.onStop();
    }
}
