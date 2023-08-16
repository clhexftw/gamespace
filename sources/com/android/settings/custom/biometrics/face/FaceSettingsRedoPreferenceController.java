package com.android.settings.custom.biometrics.face;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.face.Face;
import android.hardware.face.FaceManager;
import android.util.Log;
import android.widget.Toast;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.SettingsActivity;
import com.android.settings.biometrics.face.FaceSettings;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.custom.biometrics.FaceUtils;
import java.util.List;
/* loaded from: classes.dex */
public class FaceSettingsRedoPreferenceController extends BasePreferenceController implements Preference.OnPreferenceClickListener {
    static final String KEY = "security_settings_face_redo_face_scan";
    private static final String TAG = "FaceSettings/Redo";
    private SettingsActivity mActivity;
    private final Context mContext;
    private final FaceManager mFaceManager;
    private final FaceManager.RemovalCallback mRemovalCallback;
    private byte[] mToken;
    private int mUserId;

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY;
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

    public FaceSettingsRedoPreferenceController(Context context, String str) {
        super(context, str);
        this.mRemovalCallback = new FaceManager.RemovalCallback() { // from class: com.android.settings.custom.biometrics.face.FaceSettingsRedoPreferenceController.1
            public void onRemovalError(Face face, int i, CharSequence charSequence) {
                Log.e(FaceSettingsRedoPreferenceController.TAG, "Unable to remove face: " + face.getBiometricId() + " error: " + i + " " + ((Object) charSequence));
                Toast.makeText(FaceSettingsRedoPreferenceController.this.mContext, charSequence, 0).show();
            }

            public void onRemovalSucceeded(Face face, int i) {
                if (i == 0) {
                    Log.v(FaceSettingsRedoPreferenceController.TAG, "onRemovalSucceeded ");
                    Intent intent = new Intent("com.android.settings.intent.action.FACE_ENROLL");
                    intent.putExtra("for_face", true);
                    intent.putExtra("for_redo", true);
                    intent.putExtra("hw_auth_token", FaceSettingsRedoPreferenceController.this.mToken);
                    intent.addFlags(268435456);
                    FaceSettingsRedoPreferenceController.this.mContext.startActivity(intent);
                    return;
                }
                Log.v(FaceSettingsRedoPreferenceController.TAG, "Remaining: " + i);
            }
        };
        this.mContext = context;
        this.mFaceManager = (FaceManager) context.getSystemService(FaceManager.class);
    }

    public FaceSettingsRedoPreferenceController(Context context) {
        this(context, KEY);
    }

    public void setUserId(int i) {
        this.mUserId = i;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (!FaceSettings.isFaceHardwareDetected(this.mContext) || !this.mFaceManager.hasEnrolledTemplates(this.mUserId)) {
            preference.setEnabled(false);
            return;
        }
        preference.setEnabled(true);
        preference.setOnPreferenceClickListener(this);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return FaceUtils.isFaceUnlockSupported() ? 0 : 3;
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        showFaceRedoWarningDialog();
        return true;
    }

    public void setActivity(SettingsActivity settingsActivity) {
        this.mActivity = settingsActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteFace() {
        List enrolledFaces = this.mFaceManager.getEnrolledFaces(this.mUserId);
        if (enrolledFaces.isEmpty()) {
            Log.e(TAG, "No faces");
            return;
        }
        if (enrolledFaces.size() > 1) {
            Log.e(TAG, "Multiple enrollments: " + enrolledFaces.size());
        }
        this.mFaceManager.remove((Face) enrolledFaces.get(0), this.mUserId, this.mRemovalCallback);
    }

    void showFaceRedoWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle(R.string.security_settings_face_unlock_redo_face_scan_title).setMessage(R.string.face_redo_warning_msg).setPositiveButton(R.string.face_redo_confirm_btn, new DialogInterface.OnClickListener() { // from class: com.android.settings.custom.biometrics.face.FaceSettingsRedoPreferenceController.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                FaceSettingsRedoPreferenceController.this.deleteFace();
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.settings.custom.biometrics.face.FaceSettingsRedoPreferenceController.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.settings.custom.biometrics.face.FaceSettingsRedoPreferenceController.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        builder.create().show();
    }

    public void setToken(byte[] bArr) {
        this.mToken = bArr;
    }
}
