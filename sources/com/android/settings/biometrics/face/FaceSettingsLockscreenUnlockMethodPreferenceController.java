package com.android.settings.biometrics.face;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.face.FaceManager;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.settings.core.BasePreferenceController;
/* loaded from: classes.dex */
public class FaceSettingsLockscreenUnlockMethodPreferenceController extends BasePreferenceController {
    protected FaceManager mFaceManager;
    private UserManager mUserManager;

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

    public FaceSettingsLockscreenUnlockMethodPreferenceController(Context context, String str) {
        super(context, str);
        if (context.getPackageManager().hasSystemFeature("android.hardware.biometrics.face")) {
            this.mFaceManager = (FaceManager) context.getSystemService(FaceManager.class);
        }
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (this.mUserManager.isManagedProfile(UserHandle.myUserId())) {
            return 3;
        }
        boolean z = this.mContext.getResources().getBoolean(17891667);
        FaceManager faceManager = this.mFaceManager;
        if (faceManager == null || !faceManager.isHardwareDetected() || z) {
            return 3;
        }
        return this.mFaceManager.hasEnrolledTemplates(UserHandle.myUserId()) ? 0 : 5;
    }
}
