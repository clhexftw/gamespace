package com.android.settings.users;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.R$string;
import com.android.settingslib.RestrictedSwitchPreference;
/* loaded from: classes.dex */
public class RemoveGuestOnExitPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private static final int REMOVE_GUEST_ON_EXIT_DEFAULT = 1;
    private static final String TAG = "RemoveGuestOnExitPreferenceController";
    private static final String TAG_CONFIRM_GUEST_REMOVE = "confirmGuestRemove";
    private final Handler mHandler;
    private final Fragment mParentFragment;
    private final UserCapabilities mUserCaps;
    private final UserManager mUserManager;

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

    public RemoveGuestOnExitPreferenceController(Context context, String str, Fragment fragment, Handler handler) {
        super(context, str);
        this.mUserCaps = UserCapabilities.create(context);
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
        this.mParentFragment = fragment;
        this.mHandler = handler;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        this.mUserCaps.updateAddUserCapabilities(this.mContext);
        RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
        restrictedSwitchPreference.setChecked(isChecked());
        if (!isAvailable()) {
            restrictedSwitchPreference.setVisible(false);
            return;
        }
        restrictedSwitchPreference.setDisabledByAdmin(this.mUserCaps.disallowAddUser() ? this.mUserCaps.getEnforcedAdmin() : null);
        restrictedSwitchPreference.setVisible(this.mUserCaps.mUserSwitcherEnabled);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (UserManager.isGuestUserAlwaysEphemeral() || !UserManager.isGuestUserAllowEphemeralStateChange() || !this.mUserCaps.isAdmin() || this.mUserCaps.disallowAddUser() || this.mUserCaps.disallowAddUserSetByAdmin()) {
            return 4;
        }
        return this.mUserCaps.mUserSwitcherEnabled ? 0 : 2;
    }

    private boolean isChecked() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "remove_guest_on_exit", 1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean setChecked(Context context, boolean z) {
        Settings.Global.putInt(context.getContentResolver(), "remove_guest_on_exit", z ? 1 : 0);
        return true;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        UserInfo findCurrentGuestUser = this.mUserManager.findCurrentGuestUser();
        if (findCurrentGuestUser == null) {
            return setChecked(this.mContext, booleanValue);
        }
        if (!findCurrentGuestUser.isInitialized()) {
            if (this.mUserManager.setUserEphemeral(findCurrentGuestUser.id, booleanValue)) {
                return setChecked(this.mContext, booleanValue);
            }
            String str = TAG;
            Log.w(str, "Unused guest, id=" + findCurrentGuestUser.id + ". Mark ephemeral as " + booleanValue + " failed !!!");
            return false;
        }
        if (findCurrentGuestUser.isInitialized() && !findCurrentGuestUser.isEphemeral() && booleanValue) {
            ConfirmGuestRemoveFragment.show(this.mParentFragment, this.mHandler, booleanValue, findCurrentGuestUser.id, (RestrictedSwitchPreference) preference);
        }
        return false;
    }

    /* loaded from: classes.dex */
    public static final class ConfirmGuestRemoveFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        private static final String TAG = ConfirmGuestRemoveFragment.class.getSimpleName();
        private boolean mEnabling;
        private int mGuestUserId;
        private Handler mHandler;
        private RestrictedSwitchPreference mPreference;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 591;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void show(Fragment fragment, Handler handler, boolean z, int i, RestrictedSwitchPreference restrictedSwitchPreference) {
            if (fragment.isAdded()) {
                ConfirmGuestRemoveFragment confirmGuestRemoveFragment = new ConfirmGuestRemoveFragment();
                confirmGuestRemoveFragment.mHandler = handler;
                confirmGuestRemoveFragment.mEnabling = z;
                confirmGuestRemoveFragment.mGuestUserId = i;
                confirmGuestRemoveFragment.setTargetFragment(fragment, 0);
                confirmGuestRemoveFragment.mPreference = restrictedSwitchPreference;
                confirmGuestRemoveFragment.show(fragment.getFragmentManager(), RemoveGuestOnExitPreferenceController.TAG_CONFIRM_GUEST_REMOVE);
            }
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            FragmentActivity activity = getActivity();
            if (bundle != null) {
                this.mEnabling = bundle.getBoolean("enabling");
                this.mGuestUserId = bundle.getInt("guestUserId");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(R.string.remove_guest_on_exit_dialog_title);
            builder.setMessage(R.string.remove_guest_on_exit_dialog_message);
            builder.setPositiveButton(R$string.guest_exit_clear_data_button, this);
            builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
            return builder.create();
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putBoolean("enabling", this.mEnabling);
            bundle.putInt("guestUserId", this.mGuestUserId);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i != -1) {
                return;
            }
            UserManager userManager = (UserManager) getContext().getSystemService(UserManager.class);
            if (userManager == null) {
                Log.e(TAG, "Unable to get user manager service");
                return;
            }
            UserInfo userInfo = userManager.getUserInfo(this.mGuestUserId);
            if (userInfo == null || !userInfo.isGuest() || !this.mEnabling) {
                String str = TAG;
                Log.w(str, "Removing guest user ... failed, id=" + this.mGuestUserId);
            } else if (this.mPreference != null) {
                if (!userManager.markGuestForDeletion(userInfo.id)) {
                    String str2 = TAG;
                    Log.w(str2, "Couldn't mark the guest for deletion for user " + userInfo.id);
                    return;
                }
                userManager.removeUser(userInfo.id);
                if (RemoveGuestOnExitPreferenceController.setChecked(getContext(), this.mEnabling)) {
                    this.mPreference.setChecked(this.mEnabling);
                    this.mHandler.sendEmptyMessage(3);
                }
            }
        }
    }
}
