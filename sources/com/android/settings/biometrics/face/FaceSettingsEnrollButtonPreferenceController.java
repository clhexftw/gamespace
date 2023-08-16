package com.android.settings.biometrics.face;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.preference.Preference;
import com.android.settings.SettingsActivity;
import com.android.settings.core.BasePreferenceController;
/* loaded from: classes.dex */
public class FaceSettingsEnrollButtonPreferenceController extends BasePreferenceController implements Preference.OnPreferenceClickListener {
    static final String KEY = "security_settings_face_enroll_faces_container";
    private static final String TAG = "FaceSettings/Remove";
    private SettingsActivity mActivity;
    private final Context mContext;
    private boolean mIsClicked;
    private Listener mListener;
    private byte[] mToken;
    private int mUserId;

    /* loaded from: classes.dex */
    public interface Listener {
        void onStartEnrolling(Intent intent);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

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

    public FaceSettingsEnrollButtonPreferenceController(Context context) {
        this(context, KEY);
    }

    public FaceSettingsEnrollButtonPreferenceController(Context context, String str) {
        super(context, str);
        this.mContext = context;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setOnPreferenceClickListener(this);
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        this.mIsClicked = true;
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", FaceEnrollIntroduction.class.getName());
        intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
        intent.putExtra("hw_auth_token", this.mToken);
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onStartEnrolling(intent);
        } else {
            this.mContext.startActivity(intent);
        }
        return true;
    }

    public void setUserId(int i) {
        this.mUserId = i;
    }

    public void setToken(byte[] bArr) {
        this.mToken = bArr;
    }

    public boolean isClicked() {
        boolean z = this.mIsClicked;
        this.mIsClicked = false;
        return z;
    }

    public void setActivity(SettingsActivity settingsActivity) {
        this.mActivity = settingsActivity;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }
}
