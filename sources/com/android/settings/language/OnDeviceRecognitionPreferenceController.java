package com.android.settings.language;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.language.VoiceInputHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
/* loaded from: classes.dex */
public class OnDeviceRecognitionPreferenceController extends BasePreferenceController {
    private static final String TAG = "OnDeviceRecognitionPreferenceController";
    private Optional<Intent> mIntent;

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

    public OnDeviceRecognitionPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (this.mIntent == null) {
            this.mIntent = Optional.ofNullable(onDeviceRecognitionIntent());
        }
        return this.mIntent.isPresent() ? 0 : 2;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        Optional<Intent> optional = this.mIntent;
        if (optional == null || !optional.isPresent()) {
            return;
        }
        preference.setIntent(this.mIntent.get());
    }

    private Intent onDeviceRecognitionIntent() {
        String string = this.mContext.getString(17039940);
        if (string == null) {
            Log.v(TAG, "No on-device recognizer, intent not created.");
            return null;
        }
        ComponentName unflattenFromString = ComponentName.unflattenFromString(string);
        if (unflattenFromString == null) {
            Log.v(TAG, "Invalid on-device recognizer string format, intent not created.");
            return null;
        }
        ArrayList<VoiceInputHelper.RecognizerInfo> validRecognitionServices = VoiceInputHelper.validRecognitionServices(this.mContext);
        if (validRecognitionServices.isEmpty()) {
            Log.v(TAG, "No speech recognition serviceswith proper `recognition-service` meta-data found.");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<VoiceInputHelper.RecognizerInfo> it = validRecognitionServices.iterator();
        while (it.hasNext()) {
            VoiceInputHelper.RecognizerInfo next = it.next();
            if (!unflattenFromString.getPackageName().equals(next.mService.packageName)) {
                Log.v(TAG, String.format("Recognition service not in the same package as the default on-device recognizer: %s.", next.mComponentName.flattenToString()));
            } else if (next.mSettings == null) {
                Log.v(TAG, String.format("Recognition service with no settings activity: %s.", next.mComponentName.flattenToString()));
            } else {
                arrayList.add(next);
                Log.v(TAG, String.format("Recognition service in the same package as the default on-device recognizer with settings activity: %s.", next.mSettings.flattenToString()));
            }
        }
        if (arrayList.isEmpty()) {
            Log.v(TAG, "No speech recognition services with proper `recognition-service` meta-data found in the same package as the default on-device recognizer.");
            return null;
        }
        if (arrayList.size() > 1) {
            Log.w(TAG, "More than one recognition services with proper `recognition-service` meta-data found in the same package as the default on-device recognizer.");
        }
        return new Intent("android.intent.action.MAIN").setComponent(((VoiceInputHelper.RecognizerInfo) arrayList.get(0)).mSettings);
    }
}
