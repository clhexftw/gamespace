package com.android.settings.survey;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.overlay.SurveyFeatureProvider;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnResume;
/* loaded from: classes.dex */
public class SurveyMixin implements LifecycleObserver, OnResume {
    private Fragment mFragment;
    private String mName;

    public SurveyMixin(Fragment fragment, String str) {
        this.mName = str;
        this.mFragment = fragment;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        SurveyFeatureProvider surveyFeatureProvider;
        FragmentActivity activity = this.mFragment.getActivity();
        if (activity == null || (surveyFeatureProvider = FeatureFactory.getFactory(activity).getSurveyFeatureProvider(activity)) == null) {
            return;
        }
        surveyFeatureProvider.sendActivityIfAvailable(this.mName);
    }
}
