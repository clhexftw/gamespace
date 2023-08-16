package com.android.settings.users;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.Trace;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManagerGlobal;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.window.embedding.SplitRule;
import com.android.internal.util.UserIcons;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.R;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.users.MultiUserSwitchBarController;
import com.android.settings.users.UserSettings;
import com.android.settings.widget.MainSwitchBarController;
import com.android.settings.widget.SettingsMainSwitchBar;
import com.android.settingslib.R$string;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.Utils;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.android.settingslib.users.EditUserInfoController;
import com.android.settingslib.users.UserCreatingDialog;
import com.android.settingslib.utils.ThreadUtils;
import com.google.android.setupcompat.util.WizardManagerHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/* loaded from: classes.dex */
public class UserSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceClickListener, MultiUserSwitchBarController.OnMultiUserSwitchChangedListener, DialogInterface.OnDismissListener {
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER;
    private static final IntentFilter USER_REMOVED_INTENT_FILTER;
    private static SparseArray<Bitmap> sDarkDefaultUserBitmapCache;
    RestrictedPreference mAddGuest;
    RestrictedPreference mAddSupervisedUser;
    RestrictedPreference mAddUser;
    private AddUserWhenLockedPreferenceController mAddUserWhenLockedPreferenceController;
    private boolean mAddingUser;
    private String mAddingUserName;
    private String mConfigSupervisedUserCreationPackage;
    private Drawable mDefaultIconDrawable;
    PreferenceGroup mGuestCategory;
    Preference mGuestExitPreference;
    Preference mGuestResetPreference;
    private boolean mGuestUserAutoCreated;
    PreferenceGroup mGuestUserCategory;
    UserPreference mMePreference;
    private MultiUserTopIntroPreferenceController mMultiUserTopIntroPreferenceController;
    private Drawable mPendingUserIcon;
    private CharSequence mPendingUserName;
    private RemoveGuestOnExitPreferenceController mRemoveGuestOnExitPreferenceController;
    private MultiUserSwitchBarController mSwitchBarController;
    private TimeoutToDockUserPreferenceController mTimeoutToDockUserPreferenceController;
    private UserCapabilities mUserCaps;
    private UserCreatingDialog mUserCreatingDialog;
    PreferenceGroup mUserListCategory;
    private UserManager mUserManager;
    SparseArray<Bitmap> mUserIcons = new SparseArray<>();
    private int mRemovingUserId = -1;
    private boolean mShouldUpdateUserList = true;
    private final Object mUserLock = new Object();
    private EditUserInfoController mEditUserInfoController = new EditUserInfoController("com.android.settings.files");
    private final AtomicBoolean mGuestCreationScheduled = new AtomicBoolean();
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private Handler mHandler = new Handler() { // from class: com.android.settings.users.UserSettings.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                UserSettings.this.updateUserList();
            } else if (i == 2) {
                UserSettings.this.onUserCreated(message.arg1);
            } else if (i != 3) {
            } else {
                UserSettings.this.updateUserList();
                if (UserSettings.this.mGuestUserAutoCreated) {
                    UserSettings.this.scheduleGuestCreation();
                }
            }
        }
    };
    private BroadcastReceiver mUserChangeReceiver = new BroadcastReceiver() { // from class: com.android.settings.users.UserSettings.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra;
            if (intent.getAction().equals("android.intent.action.USER_REMOVED")) {
                UserSettings.this.mRemovingUserId = -1;
            } else if (intent.getAction().equals("android.intent.action.USER_INFO_CHANGED") && (intExtra = intent.getIntExtra("android.intent.extra.user_handle", -1)) != -1) {
                UserSettings.this.mUserIcons.remove(intExtra);
            }
            UserSettings.this.mHandler.sendEmptyMessage(1);
        }
    };

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settings.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        if (i != 1) {
            if (i != 2) {
                switch (i) {
                    case 5:
                        return 594;
                    case 6:
                        return 598;
                    case 7:
                        return 599;
                    case 8:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        return SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT;
                    case 9:
                    case 10:
                    case 11:
                        return 601;
                    default:
                        return 0;
                }
            }
            return 595;
        }
        return 591;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 96;
    }

    static {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.USER_REMOVED");
        USER_REMOVED_INTENT_FILTER = intentFilter;
        intentFilter.addAction("android.intent.action.USER_INFO_CHANGED");
        sDarkDefaultUserBitmapCache = new SparseArray<>();
        SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider(R.xml.user_settings) { // from class: com.android.settings.users.UserSettings.15
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.android.settings.search.BaseSearchIndexProvider
            public boolean isPageSearchEnabled(Context context) {
                return UserCapabilities.create(context).mEnabled;
            }

            @Override // com.android.settings.search.BaseSearchIndexProvider
            public List<String> getNonIndexableKeysFromXml(Context context, int i, boolean z) {
                List<String> nonIndexableKeysFromXml = super.getNonIndexableKeysFromXml(context, i, z);
                new AddUserWhenLockedPreferenceController(context, "user_settings_add_users_when_locked").updateNonIndexableKeys(nonIndexableKeysFromXml);
                new AutoSyncDataPreferenceController(context, null).updateNonIndexableKeys(nonIndexableKeysFromXml);
                new AutoSyncPersonalDataPreferenceController(context, null).updateNonIndexableKeys(nonIndexableKeysFromXml);
                new AutoSyncWorkDataPreferenceController(context, null).updateNonIndexableKeys(nonIndexableKeysFromXml);
                return nonIndexableKeysFromXml;
            }
        };
    }

    @Override // com.android.settings.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        SettingsMainSwitchBar switchBar = settingsActivity.getSwitchBar();
        switchBar.setTitle(getContext().getString(R.string.multiple_users_main_switch_title));
        if (this.mUserCaps.mIsAdmin) {
            switchBar.show();
        } else {
            switchBar.hide();
        }
        this.mSwitchBarController = new MultiUserSwitchBarController(settingsActivity, new MainSwitchBarController(switchBar), this);
        getSettingsLifecycle().addObserver(this.mSwitchBarController);
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.user_settings);
        FragmentActivity activity = getActivity();
        if (!WizardManagerHelper.isDeviceProvisioned(activity)) {
            activity.finish();
            return;
        }
        this.mGuestUserAutoCreated = getPrefContext().getResources().getBoolean(17891681);
        this.mAddUserWhenLockedPreferenceController = new AddUserWhenLockedPreferenceController(activity, "user_settings_add_users_when_locked");
        this.mRemoveGuestOnExitPreferenceController = new RemoveGuestOnExitPreferenceController(activity, "remove_guest_on_exit", this, this.mHandler);
        this.mMultiUserTopIntroPreferenceController = new MultiUserTopIntroPreferenceController(activity, "multiuser_top_intro");
        this.mTimeoutToDockUserPreferenceController = new TimeoutToDockUserPreferenceController(activity, "timeout_to_dock_user_preference");
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mAddUserWhenLockedPreferenceController.displayPreference(preferenceScreen);
        this.mRemoveGuestOnExitPreferenceController.displayPreference(preferenceScreen);
        this.mMultiUserTopIntroPreferenceController.displayPreference(preferenceScreen);
        this.mTimeoutToDockUserPreferenceController.displayPreference(preferenceScreen);
        preferenceScreen.findPreference(this.mAddUserWhenLockedPreferenceController.getPreferenceKey()).setOnPreferenceChangeListener(this.mAddUserWhenLockedPreferenceController);
        preferenceScreen.findPreference(this.mRemoveGuestOnExitPreferenceController.getPreferenceKey()).setOnPreferenceChangeListener(this.mRemoveGuestOnExitPreferenceController);
        if (bundle != null) {
            if (bundle.containsKey("removing_user")) {
                this.mRemovingUserId = bundle.getInt("removing_user");
            }
            this.mEditUserInfoController.onRestoreInstanceState(bundle);
        }
        this.mUserCaps = UserCapabilities.create(activity);
        this.mUserManager = (UserManager) activity.getSystemService("user");
        if (this.mUserCaps.mEnabled) {
            int myUserId = UserHandle.myUserId();
            this.mUserListCategory = (PreferenceGroup) findPreference("user_list");
            UserPreference userPreference = new UserPreference(getPrefContext(), null, myUserId);
            this.mMePreference = userPreference;
            userPreference.setKey("user_me");
            this.mMePreference.setOnPreferenceClickListener(this);
            if (this.mUserCaps.mIsAdmin) {
                this.mMePreference.setSummary(R.string.user_admin);
            }
            this.mGuestCategory = (PreferenceGroup) findPreference("guest_category");
            Preference findPreference = findPreference("guest_reset");
            this.mGuestResetPreference = findPreference;
            findPreference.setOnPreferenceClickListener(this);
            Preference findPreference2 = findPreference("guest_exit");
            this.mGuestExitPreference = findPreference2;
            findPreference2.setOnPreferenceClickListener(this);
            this.mGuestUserCategory = (PreferenceGroup) findPreference("guest_user_category");
            RestrictedPreference restrictedPreference = (RestrictedPreference) findPreference("guest_add");
            this.mAddGuest = restrictedPreference;
            restrictedPreference.setOnPreferenceClickListener(this);
            RestrictedPreference restrictedPreference2 = (RestrictedPreference) findPreference("user_add");
            this.mAddUser = restrictedPreference2;
            if (!this.mUserCaps.mCanAddRestrictedProfile) {
                restrictedPreference2.setTitle(R$string.user_add_user);
            }
            this.mAddUser.setOnPreferenceClickListener(this);
            setConfigSupervisedUserCreationPackage();
            RestrictedPreference restrictedPreference3 = (RestrictedPreference) findPreference("supervised_user_add");
            this.mAddSupervisedUser = restrictedPreference3;
            restrictedPreference3.setOnPreferenceClickListener(this);
            activity.registerReceiverAsUser(this.mUserChangeReceiver, UserHandle.ALL, USER_REMOVED_INTENT_FILTER, null, this.mHandler, 2);
            updateUI();
            this.mShouldUpdateUserList = false;
        }
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mUserCaps.mEnabled) {
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            AddUserWhenLockedPreferenceController addUserWhenLockedPreferenceController = this.mAddUserWhenLockedPreferenceController;
            addUserWhenLockedPreferenceController.updateState(preferenceScreen.findPreference(addUserWhenLockedPreferenceController.getPreferenceKey()));
            TimeoutToDockUserPreferenceController timeoutToDockUserPreferenceController = this.mTimeoutToDockUserPreferenceController;
            timeoutToDockUserPreferenceController.updateState(preferenceScreen.findPreference(timeoutToDockUserPreferenceController.getPreferenceKey()));
            RemoveGuestOnExitPreferenceController removeGuestOnExitPreferenceController = this.mRemoveGuestOnExitPreferenceController;
            removeGuestOnExitPreferenceController.updateState(preferenceScreen.findPreference(removeGuestOnExitPreferenceController.getPreferenceKey()));
            if (this.mShouldUpdateUserList) {
                updateUI();
            }
        }
    }

    @Override // com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        this.mShouldUpdateUserList = true;
        super.onPause();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        UserCapabilities userCapabilities = this.mUserCaps;
        if (userCapabilities == null || !userCapabilities.mEnabled) {
            return;
        }
        getActivity().unregisterReceiver(this.mUserChangeReceiver);
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        this.mEditUserInfoController.onSaveInstanceState(bundle);
        bundle.putInt("removing_user", this.mRemovingUserId);
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void startActivityForResult(Intent intent, int i) {
        this.mEditUserInfoController.startingActivityForResult();
        super.startActivityForResult(intent, i);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (!this.mUserCaps.mIsAdmin && canSwitchUserNow() && (!isCurrentUserGuest() || !this.mGuestUserAutoCreated)) {
            MenuItem add = menu.add(0, 1, 0, getResources().getString(R.string.user_remove_user_menu, this.mUserManager.getUserName()));
            add.setShowAsAction(0);
            RestrictedLockUtilsInternal.setMenuItemAsDisabledByAdmin(getContext(), add, RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getContext(), "no_remove_user", UserHandle.myUserId()));
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 1) {
            onRemoveUserClicked(UserHandle.myUserId());
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.settings.users.MultiUserSwitchBarController.OnMultiUserSwitchChangedListener
    public void onMultiUserSwitchChanged(boolean z) {
        updateUI();
    }

    private void updateUI() {
        this.mUserCaps.updateAddUserCapabilities(getActivity());
        loadProfile();
        updateUserList();
    }

    private void loadProfile() {
        int i;
        if (isCurrentUserGuest()) {
            this.mMePreference.setIcon(getEncircledDefaultIcon());
            UserPreference userPreference = this.mMePreference;
            if (this.mGuestUserAutoCreated) {
                i = R$string.guest_reset_guest;
            } else {
                i = R$string.guest_exit_guest;
            }
            userPreference.setTitle(i);
            this.mMePreference.setSelectable(true);
            this.mMePreference.setEnabled(canSwitchUserNow());
            return;
        }
        new AsyncTask<Void, Void, String>() { // from class: com.android.settings.users.UserSettings.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String str) {
                UserSettings.this.finishLoadProfile(str);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(Void... voidArr) {
                UserInfo userInfo = UserSettings.this.mUserManager.getUserInfo(UserHandle.myUserId());
                String str = userInfo.iconPath;
                if (str == null || str.equals("")) {
                    UserSettings.copyMeProfilePhoto(UserSettings.this.getActivity(), userInfo);
                }
                return userInfo.name;
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishLoadProfile(String str) {
        if (getActivity() == null) {
            return;
        }
        this.mMePreference.setTitle(getString(R.string.user_you, str));
        int myUserId = UserHandle.myUserId();
        Bitmap userIcon = this.mUserManager.getUserIcon(myUserId);
        if (userIcon != null) {
            this.mMePreference.setIcon(encircleUserIcon(userIcon));
            this.mUserIcons.put(myUserId, userIcon);
        }
    }

    private boolean hasLockscreenSecurity() {
        return new LockPatternUtils(getActivity()).isSecure(UserHandle.myUserId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchChooseLockscreen() {
        Intent intent = new Intent("android.app.action.SET_NEW_PASSWORD");
        intent.putExtra("hide_insecure_options", true);
        startActivityForResult(intent, 10);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10) {
            if (i2 == 0 || !hasLockscreenSecurity()) {
                return;
            }
            addUserNow(2);
        } else if (this.mGuestUserAutoCreated && i == 11 && i2 == 100) {
            scheduleGuestCreation();
        } else {
            this.mEditUserInfoController.onActivityResult(i, i2, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAddUserClicked(int i) {
        synchronized (this.mUserLock) {
            if (this.mRemovingUserId == -1 && !this.mAddingUser) {
                if (i == 1) {
                    showDialog(2);
                } else if (i == 2) {
                    if (hasLockscreenSecurity()) {
                        showDialog(11);
                    } else {
                        showDialog(7);
                    }
                }
            }
        }
    }

    private void onAddSupervisedUserClicked() {
        startActivity(new Intent().setAction("android.os.action.CREATE_SUPERVISED_USER").setPackage(this.mConfigSupervisedUserCreationPackage).addFlags(268435456));
    }

    private void onAddGuestClicked() {
        final UserCreatingDialog userCreatingDialog = new UserCreatingDialog(getActivity(), true);
        userCreatingDialog.show();
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.settings.users.UserSettings$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                UserSettings.this.lambda$onAddGuestClicked$1(userCreatingDialog);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAddGuestClicked$1(final UserCreatingDialog userCreatingDialog) {
        this.mMetricsFeatureProvider.action(getActivity(), 1764, new Pair[0]);
        Trace.beginSection("UserSettings.addGuest");
        final UserInfo createGuest = this.mUserManager.createGuest(getContext());
        Trace.endSection();
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settings.users.UserSettings$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                UserSettings.this.lambda$onAddGuestClicked$0(userCreatingDialog, createGuest);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAddGuestClicked$0(UserCreatingDialog userCreatingDialog, UserInfo userInfo) {
        userCreatingDialog.dismiss();
        if (userInfo == null) {
            Toast.makeText(getContext(), R$string.add_guest_failed, 0).show();
        } else {
            openUserDetails(userInfo, true);
        }
    }

    private void onRemoveUserClicked(int i) {
        synchronized (this.mUserLock) {
            if (this.mRemovingUserId == -1 && !this.mAddingUser) {
                this.mRemovingUserId = i;
                showDialog(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserCreated(int i) {
        hideUserCreatingDialog();
        if (getContext() == null) {
            return;
        }
        this.mAddingUser = false;
        openUserDetails(this.mUserManager.getUserInfo(i), true);
    }

    private void hideUserCreatingDialog() {
        UserCreatingDialog userCreatingDialog = this.mUserCreatingDialog;
        if (userCreatingDialog == null || !userCreatingDialog.isShowing()) {
            return;
        }
        this.mUserCreatingDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserCreationFailed() {
        Toast.makeText(getContext(), R$string.add_user_failed, 0).show();
        hideUserCreatingDialog();
    }

    private void openUserDetails(UserInfo userInfo, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt("user_id", userInfo.id);
        bundle.putBoolean("new_user", z);
        SubSettingLauncher sourceMetricsCategory = new SubSettingLauncher(getContext()).setDestination(UserDetailsSettings.class.getName()).setArguments(bundle).setTitleText(userInfo.name).setSourceMetricsCategory(getMetricsCategory());
        if (this.mGuestUserAutoCreated && userInfo.isGuest()) {
            sourceMetricsCategory.setResultListener(this, 11);
        }
        sourceMetricsCategory.launch();
    }

    @Override // com.android.settings.SettingsPreferenceFragment
    public void onDialogShowing() {
        super.onDialogShowing();
        setOnDismissListener(this);
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settings.DialogCreatable
    public Dialog onCreateDialog(int i) {
        int i2;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        if (i != 1) {
            if (i != 2) {
                switch (i) {
                    case 5:
                        return new AlertDialog.Builder(activity).setMessage(R.string.user_cannot_manage_message).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).create();
                    case 6:
                        ArrayList arrayList = new ArrayList();
                        HashMap hashMap = new HashMap();
                        hashMap.put("title", getString(R$string.user_add_user_item_title));
                        hashMap.put("summary", getString(R$string.user_add_user_item_summary));
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("title", getString(R$string.user_add_profile_item_title));
                        hashMap2.put("summary", getString(R$string.user_add_profile_item_summary));
                        arrayList.add(hashMap);
                        arrayList.add(hashMap2);
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        SimpleAdapter simpleAdapter = new SimpleAdapter(builder.getContext(), arrayList, R.layout.two_line_list_item, new String[]{"title", "summary"}, new int[]{R.id.title, R.id.summary});
                        builder.setTitle(R$string.user_add_user_type_title);
                        builder.setAdapter(simpleAdapter, new DialogInterface.OnClickListener() { // from class: com.android.settings.users.UserSettings.6
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i3) {
                                UserSettings.this.onAddUserClicked(i3 == 0 ? 1 : 2);
                            }
                        });
                        return builder.create();
                    case 7:
                        return new AlertDialog.Builder(activity).setMessage(R$string.user_need_lock_message).setPositiveButton(R$string.user_set_lock_button, new DialogInterface.OnClickListener() { // from class: com.android.settings.users.UserSettings.7
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i3) {
                                UserSettings.this.launchChooseLockscreen();
                            }
                        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
                    case 8:
                        return new AlertDialog.Builder(activity).setTitle(R$string.guest_remove_guest_dialog_title).setMessage(R.string.user_exit_guest_confirm_message).setPositiveButton(R.string.user_exit_guest_dialog_remove, new DialogInterface.OnClickListener() { // from class: com.android.settings.users.UserSettings.8
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i3) {
                                UserSettings.this.clearAndExitGuest();
                            }
                        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
                    case 9:
                        return buildEditCurrentUserDialog();
                    case 10:
                        synchronized (this.mUserLock) {
                            this.mPendingUserName = getString(R$string.user_new_user_name);
                            this.mPendingUserIcon = null;
                        }
                        return buildAddUserDialog(1);
                    case 11:
                        synchronized (this.mUserLock) {
                            this.mPendingUserName = getString(R$string.user_new_profile_name);
                            this.mPendingUserIcon = null;
                        }
                        return buildAddUserDialog(2);
                    case 12:
                        return UserDialogs.createResetGuestDialog(getActivity(), new DialogInterface.OnClickListener() { // from class: com.android.settings.users.UserSettings$$ExternalSyntheticLambda1
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i3) {
                                UserSettings.this.lambda$onCreateDialog$2(dialogInterface, i3);
                            }
                        });
                    case 13:
                        return new AlertDialog.Builder(activity).setTitle(R$string.guest_reset_and_restart_dialog_title).setMessage(R$string.guest_reset_and_restart_dialog_message).setPositiveButton(R$string.guest_reset_guest_confirm_button, new DialogInterface.OnClickListener() { // from class: com.android.settings.users.UserSettings.12
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i3) {
                                UserSettings.this.resetAndRestartGuest();
                            }
                        }).setNeutralButton(17039360, null).create();
                    case 14:
                        return new AlertDialog.Builder(activity).setTitle(R$string.guest_exit_dialog_title).setMessage(R$string.guest_exit_dialog_message).setPositiveButton(R$string.guest_exit_dialog_button, new DialogInterface.OnClickListener() { // from class: com.android.settings.users.UserSettings.9
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i3) {
                                UserSettings.this.clearAndExitGuest();
                            }
                        }).setNeutralButton(17039360, null).create();
                    case 15:
                        return new AlertDialog.Builder(activity).setTitle(R$string.guest_exit_dialog_title_non_ephemeral).setMessage(R$string.guest_exit_dialog_message_non_ephemeral).setPositiveButton(R$string.guest_exit_save_data_button, new DialogInterface.OnClickListener() { // from class: com.android.settings.users.UserSettings.11
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i3) {
                                UserSettings.this.exitGuest();
                            }
                        }).setNegativeButton(R$string.guest_exit_clear_data_button, new DialogInterface.OnClickListener() { // from class: com.android.settings.users.UserSettings.10
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i3) {
                                UserSettings.this.clearAndExitGuest();
                            }
                        }).setNeutralButton(17039360, null).create();
                    default:
                        return null;
                }
            }
            final SharedPreferences preferences = getActivity().getPreferences(0);
            final boolean z = preferences.getBoolean("key_add_user_long_message_displayed", false);
            if (z) {
                i2 = R$string.user_add_user_message_short;
            } else {
                i2 = R$string.user_add_user_message_long;
            }
            return new AlertDialog.Builder(activity).setTitle(R$string.user_add_user_title).setMessage(i2).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.android.settings.users.UserSettings.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i3) {
                    UserSettings.this.showDialog(10);
                    if (z) {
                        return;
                    }
                    preferences.edit().putBoolean("key_add_user_long_message_displayed", true).apply();
                }
            }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
        }
        return UserDialogs.createRemoveDialog(getActivity(), this.mRemovingUserId, new DialogInterface.OnClickListener() { // from class: com.android.settings.users.UserSettings.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                UserSettings.this.removeUserNow();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateDialog$2(DialogInterface dialogInterface, int i) {
        clearAndExitGuest();
    }

    private Dialog buildEditCurrentUserDialog() {
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        final UserInfo userInfo = this.mUserManager.getUserInfo(Process.myUserHandle().getIdentifier());
        final Drawable userIcon = Utils.getUserIcon(activity, this.mUserManager, userInfo);
        return this.mEditUserInfoController.createDialog(activity, new UserSettings$$ExternalSyntheticLambda4(this), userIcon, userInfo.name, getString(R$string.profile_info_settings_title), new BiConsumer() { // from class: com.android.settings.users.UserSettings$$ExternalSyntheticLambda8
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                UserSettings.this.lambda$buildEditCurrentUserDialog$4(userIcon, userInfo, activity, (String) obj, (Drawable) obj2);
            }
        }, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildEditCurrentUserDialog$4(Drawable drawable, final UserInfo userInfo, final Activity activity, String str, final Drawable drawable2) {
        if (drawable2 != drawable) {
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.settings.users.UserSettings$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    UserSettings.this.lambda$buildEditCurrentUserDialog$3(userInfo, activity, drawable2);
                }
            });
            this.mMePreference.setIcon(drawable2);
        }
        if (TextUtils.isEmpty(str) || str.equals(userInfo.name)) {
            return;
        }
        this.mMePreference.setTitle(str);
        this.mUserManager.setUserName(userInfo.id, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildEditCurrentUserDialog$3(UserInfo userInfo, Activity activity, Drawable drawable) {
        this.mUserManager.setUserIcon(userInfo.id, UserIcons.convertToBitmapAtUserIconSize(activity.getResources(), drawable));
    }

    private Dialog buildAddUserDialog(final int i) {
        int i2;
        Dialog createDialog;
        synchronized (this.mUserLock) {
            EditUserInfoController editUserInfoController = this.mEditUserInfoController;
            FragmentActivity activity = getActivity();
            UserSettings$$ExternalSyntheticLambda4 userSettings$$ExternalSyntheticLambda4 = new UserSettings$$ExternalSyntheticLambda4(this);
            String charSequence = this.mPendingUserName.toString();
            if (i == 1) {
                i2 = R$string.user_info_settings_title;
            } else {
                i2 = R$string.profile_info_settings_title;
            }
            createDialog = editUserInfoController.createDialog(activity, userSettings$$ExternalSyntheticLambda4, null, charSequence, getString(i2), new BiConsumer() { // from class: com.android.settings.users.UserSettings$$ExternalSyntheticLambda5
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UserSettings.this.lambda$buildAddUserDialog$5(i, (String) obj, (Drawable) obj2);
                }
            }, new Runnable() { // from class: com.android.settings.users.UserSettings$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    UserSettings.this.lambda$buildAddUserDialog$6();
                }
            });
        }
        return createDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildAddUserDialog$5(int i, String str, Drawable drawable) {
        this.mPendingUserIcon = drawable;
        this.mPendingUserName = str;
        addUserNow(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildAddUserDialog$6() {
        synchronized (this.mUserLock) {
            this.mPendingUserIcon = null;
            this.mPendingUserName = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeUserNow() {
        if (this.mRemovingUserId == UserHandle.myUserId()) {
            removeThisUser();
        } else {
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.settings.users.UserSettings.13
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (UserSettings.this.mUserLock) {
                        UserSettings.this.mUserManager.removeUser(UserSettings.this.mRemovingUserId);
                        UserSettings.this.mHandler.sendEmptyMessage(1);
                    }
                }
            });
        }
    }

    private void removeThisUser() {
        if (!canSwitchUserNow()) {
            Log.w("UserSettings", "Cannot remove current user when switching is disabled");
            return;
        }
        try {
            ((UserManager) getContext().getSystemService(UserManager.class)).removeUserWhenPossible(UserHandle.of(UserHandle.myUserId()), false);
            ActivityManager.getService().switchUser(0);
        } catch (RemoteException unused) {
            Log.e("UserSettings", "Unable to remove self user");
        }
    }

    private void switchToUserId(int i) {
        if (!canSwitchUserNow()) {
            Log.w("UserSettings", "Cannot switch current user when switching is disabled");
            return;
        }
        try {
            ActivityManager.getService().switchUser(i);
        } catch (RemoteException unused) {
            Log.e("UserSettings", "Unable to switch user");
        }
    }

    private void addUserNow(int i) {
        String charSequence;
        Trace.beginAsyncSection("UserSettings.addUserNow", 0);
        synchronized (this.mUserLock) {
            this.mAddingUser = true;
            if (i == 1) {
                CharSequence charSequence2 = this.mPendingUserName;
                charSequence = charSequence2 != null ? charSequence2.toString() : getString(R.string.user_new_user_name);
            } else {
                CharSequence charSequence3 = this.mPendingUserName;
                charSequence = charSequence3 != null ? charSequence3.toString() : getString(R.string.user_new_profile_name);
            }
            this.mAddingUserName = charSequence;
        }
        UserCreatingDialog userCreatingDialog = new UserCreatingDialog(getActivity());
        this.mUserCreatingDialog = userCreatingDialog;
        userCreatingDialog.show();
        ThreadUtils.postOnBackgroundThread(new AddUserNowImpl(i, this.mAddingUserName));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class AddUserNowImpl implements Runnable {
        String mImplAddUserName;
        int mUserType;

        AddUserNowImpl(int i, String str) {
            this.mUserType = i;
            this.mImplAddUserName = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            runAddUser();
            Trace.endAsyncSection("UserSettings.addUserNow", 0);
        }

        private void runAddUser() {
            String str;
            UserInfo createRestrictedProfile;
            synchronized (UserSettings.this.mUserLock) {
                str = this.mImplAddUserName;
            }
            if (this.mUserType == 1) {
                createRestrictedProfile = UserSettings.this.mUserManager.createUser(str, 0);
            } else {
                createRestrictedProfile = UserSettings.this.mUserManager.createRestrictedProfile(str);
            }
            synchronized (UserSettings.this.mUserLock) {
                if (createRestrictedProfile == null) {
                    UserSettings.this.mAddingUser = false;
                    UserSettings.this.mPendingUserIcon = null;
                    UserSettings.this.mPendingUserName = null;
                    ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settings.users.UserSettings$AddUserNowImpl$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            UserSettings.AddUserNowImpl.this.lambda$runAddUser$0();
                        }
                    });
                    return;
                }
                Drawable drawable = UserSettings.this.mPendingUserIcon;
                if (drawable == null) {
                    drawable = UserIcons.getDefaultUserIcon(UserSettings.this.getResources(), createRestrictedProfile.id, false);
                }
                UserSettings.this.mUserManager.setUserIcon(createRestrictedProfile.id, UserIcons.convertToBitmapAtUserIconSize(UserSettings.this.getResources(), drawable));
                if (this.mUserType == 1) {
                    UserSettings.this.mHandler.sendEmptyMessage(1);
                }
                UserSettings.this.mHandler.sendMessage(UserSettings.this.mHandler.obtainMessage(2, createRestrictedProfile.id, createRestrictedProfile.serialNumber));
                UserSettings.this.mPendingUserIcon = null;
                UserSettings.this.mPendingUserName = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$runAddUser$0() {
            UserSettings.this.onUserCreationFailed();
        }
    }

    void clearAndExitGuest() {
        if (isCurrentUserGuest()) {
            this.mMetricsFeatureProvider.action(getActivity(), 1763, new Pair[0]);
            int myUserId = UserHandle.myUserId();
            if (!this.mUserManager.markGuestForDeletion(myUserId)) {
                Log.w("UserSettings", "Couldn't mark the guest for deletion for user " + myUserId);
                return;
            }
            removeThisUser();
            if (this.mGuestUserAutoCreated) {
                scheduleGuestCreation();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitGuest() {
        if (isCurrentUserGuest()) {
            this.mMetricsFeatureProvider.action(getActivity(), 1763, new Pair[0]);
            switchToUserId(0);
        }
    }

    private int createGuest() {
        try {
            UserInfo createGuest = this.mUserManager.createGuest(getPrefContext());
            if (createGuest == null) {
                Log.e("UserSettings", "Couldn't create guest, most likely because there already exists one");
                return -10000;
            }
            return createGuest.id;
        } catch (UserManager.UserOperationException e) {
            Log.e("UserSettings", "Couldn't create guest user", e);
            return -10000;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAndRestartGuest() {
        if (isCurrentUserGuest()) {
            int myUserId = UserHandle.myUserId();
            if (!this.mUserManager.markGuestForDeletion(myUserId)) {
                Log.w("UserSettings", "Couldn't mark the guest for deletion for user " + myUserId);
                return;
            }
            try {
                int createGuest = createGuest();
                if (createGuest == -10000) {
                    Log.e("UserSettings", "Could not create new guest, switching back to system user");
                    switchToUserId(0);
                    this.mUserManager.removeUser(myUserId);
                    WindowManagerGlobal.getWindowManagerService().lockNow((Bundle) null);
                    return;
                }
                switchToUserId(createGuest);
                this.mUserManager.removeUser(myUserId);
            } catch (RemoteException unused) {
                Log.e("UserSettings", "Couldn't remove guest because ActivityManager or WindowManager is dead");
            }
        }
    }

    void scheduleGuestCreation() {
        if (this.mGuestCreationScheduled.compareAndSet(false, true)) {
            this.mHandler.sendEmptyMessage(1);
            this.mExecutor.execute(new Runnable() { // from class: com.android.settings.users.UserSettings$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    UserSettings.this.lambda$scheduleGuestCreation$7();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleGuestCreation$7() {
        UserInfo createGuest = this.mUserManager.createGuest(getContext());
        this.mGuestCreationScheduled.set(false);
        if (createGuest == null) {
            Log.e("UserSettings", "Unable to automatically recreate guest user");
        }
        this.mHandler.sendEmptyMessage(1);
    }

    void updateUserList() {
        Preference preference;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        List<UserInfo> list = (List) this.mUserManager.getAliveUsers().stream().filter(new Predicate() { // from class: com.android.settings.users.UserSettings$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((UserInfo) obj).supportsSwitchToByUser();
            }
        }).collect(Collectors.toList());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (!this.mUserCaps.mIsGuest) {
            arrayList2.add(this.mMePreference);
        }
        boolean z = this.mUserCaps.mIsAdmin || (canSwitchUserNow() && !this.mUserCaps.mDisallowSwitchUser);
        for (UserInfo userInfo : list) {
            if (!userInfo.isGuest()) {
                if (userInfo.id == UserHandle.myUserId()) {
                    preference = this.mMePreference;
                } else {
                    UserPreference userPreference = new UserPreference(getPrefContext(), null, userInfo.id);
                    userPreference.setTitle(userInfo.name);
                    arrayList2.add(userPreference);
                    userPreference.setOnPreferenceClickListener(this);
                    userPreference.setEnabled(z);
                    userPreference.setSelectable(true);
                    userPreference.setKey("id=" + userInfo.id);
                    if (userInfo.isAdmin()) {
                        userPreference.setSummary(R.string.user_admin);
                    }
                    preference = userPreference;
                }
                if (preference != null) {
                    if (userInfo.id != UserHandle.myUserId() && !userInfo.isGuest() && !userInfo.isInitialized()) {
                        if (userInfo.isRestricted()) {
                            preference.setSummary(R.string.user_summary_restricted_not_set_up);
                        } else {
                            preference.setSummary(R.string.user_summary_not_set_up);
                            preference.setEnabled(!this.mUserCaps.mDisallowSwitchUser && canSwitchUserNow());
                        }
                    } else if (userInfo.isRestricted()) {
                        preference.setSummary(R.string.user_summary_restricted_profile);
                    }
                    if (userInfo.iconPath != null) {
                        if (this.mUserIcons.get(userInfo.id) == null) {
                            arrayList.add(Integer.valueOf(userInfo.id));
                            preference.setIcon(getEncircledDefaultIcon());
                        } else {
                            setPhotoId(preference, userInfo);
                        }
                    } else {
                        preference.setIcon(getEncircledDefaultIcon());
                    }
                }
            }
        }
        if (this.mAddingUser) {
            UserPreference userPreference2 = new UserPreference(getPrefContext(), null, -10);
            userPreference2.setEnabled(false);
            userPreference2.setTitle(this.mAddingUserName);
            userPreference2.setIcon(getEncircledDefaultIcon());
            arrayList2.add(userPreference2);
        }
        Collections.sort(arrayList2, UserPreference.SERIAL_NUMBER_COMPARATOR);
        getActivity().invalidateOptionsMenu();
        if (arrayList.size() > 0) {
            loadIconsAsync(arrayList);
        }
        if (this.mUserCaps.mCanAddRestrictedProfile) {
            this.mUserListCategory.setTitle(R.string.user_list_title);
        } else if (isCurrentUserGuest()) {
            this.mUserListCategory.setTitle(R.string.other_user_category_title);
        } else {
            this.mUserListCategory.setTitle(R.string.user_category_title);
        }
        this.mUserListCategory.removeAll();
        this.mAddUserWhenLockedPreferenceController.updateState(getPreferenceScreen().findPreference(this.mAddUserWhenLockedPreferenceController.getPreferenceKey()));
        this.mMultiUserTopIntroPreferenceController.updateState(getPreferenceScreen().findPreference(this.mMultiUserTopIntroPreferenceController.getPreferenceKey()));
        this.mUserListCategory.setVisible(this.mUserCaps.mUserSwitcherEnabled);
        updateGuestPreferences();
        updateGuestCategory(activity, list);
        updateAddUser(activity);
        updateAddSupervisedUser(activity);
        if (this.mUserCaps.mUserSwitcherEnabled) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                UserPreference userPreference3 = (UserPreference) it.next();
                userPreference3.setOrder(Integer.MAX_VALUE);
                this.mUserListCategory.addPreference(userPreference3);
            }
        }
    }

    void setConfigSupervisedUserCreationPackage() {
        this.mConfigSupervisedUserCreationPackage = getPrefContext().getString(17040049);
    }

    private boolean isCurrentUserGuest() {
        return this.mUserCaps.mIsGuest;
    }

    private boolean canSwitchUserNow() {
        return this.mUserManager.getUserSwitchability() == 0;
    }

    private void updateGuestPreferences() {
        String string;
        this.mGuestCategory.setVisible(false);
        this.mGuestResetPreference.setVisible(false);
        this.mGuestExitPreference.setVisible(false);
        if (isCurrentUserGuest()) {
            this.mGuestCategory.setVisible(true);
            this.mGuestExitPreference.setVisible(true);
            this.mGuestResetPreference.setVisible(true);
            boolean z = Settings.Secure.getIntForUser(getContext().getContentResolver(), "systemui.guest_has_logged_in", 0, UserHandle.myUserId()) <= 1;
            if (this.mUserCaps.mIsEphemeral) {
                string = getContext().getString(R.string.guest_notification_ephemeral);
            } else if (z) {
                string = getContext().getString(R.string.guest_notification_non_ephemeral);
            } else {
                string = getContext().getString(R.string.guest_notification_non_ephemeral_non_first_login);
            }
            this.mGuestExitPreference.setSummary(string);
        }
    }

    private void updateGuestCategory(Context context, List<UserInfo> list) {
        boolean z = this.mUserCaps.mIsAdmin || (canSwitchUserNow() && !this.mUserCaps.mDisallowSwitchUser);
        this.mGuestUserCategory.removeAll();
        this.mGuestUserCategory.setVisible(false);
        boolean z2 = false;
        for (UserInfo userInfo : list) {
            if (userInfo.isGuest() && userInfo.isEnabled()) {
                UserPreference userPreference = new UserPreference(getPrefContext(), null, userInfo.id);
                userPreference.setTitle(userInfo.name);
                userPreference.setOnPreferenceClickListener(this);
                userPreference.setEnabled(z);
                userPreference.setSelectable(true);
                Drawable drawable = getContext().getDrawable(R.drawable.ic_account_circle_outline);
                drawable.setTint(Utils.getColorAttrDefaultColor(getContext(), 16843817));
                userPreference.setIcon(encircleUserIcon(UserIcons.convertToBitmapAtUserIconSize(getContext().getResources(), drawable)));
                userPreference.setKey("user_guest");
                userPreference.setOrder(Integer.MAX_VALUE);
                if (this.mUserCaps.mDisallowSwitchUser) {
                    userPreference.setDisabledByAdmin(RestrictedLockUtilsInternal.getDeviceOwner(context));
                } else {
                    userPreference.setDisabledByAdmin(null);
                }
                if (this.mUserCaps.mUserSwitcherEnabled) {
                    this.mGuestUserCategory.addPreference(userPreference);
                    this.mGuestUserCategory.setVisible(true);
                }
                z2 = true;
            }
        }
        if (updateAddGuestPreference(context, z2)) {
            this.mGuestUserCategory.setVisible(true);
        }
        this.mRemoveGuestOnExitPreferenceController.updateState(getPreferenceScreen().findPreference(this.mRemoveGuestOnExitPreferenceController.getPreferenceKey()));
        if (this.mRemoveGuestOnExitPreferenceController.isAvailable()) {
            this.mGuestUserCategory.setVisible(true);
        }
        if (this.mUserCaps.mIsGuest) {
            this.mGuestUserCategory.setVisible(false);
        }
    }

    private boolean updateAddGuestPreference(Context context, boolean z) {
        if (!z && this.mUserCaps.mCanAddGuest && this.mUserManager.canAddMoreUsers("android.os.usertype.full.GUEST") && WizardManagerHelper.isDeviceProvisioned(context) && this.mUserCaps.mUserSwitcherEnabled) {
            this.mAddGuest.setIcon(centerAndTint(context.getDrawable(R.drawable.ic_account_circle)));
            this.mAddGuest.setVisible(true);
            this.mAddGuest.setSelectable(true);
            if (this.mGuestUserAutoCreated && this.mGuestCreationScheduled.get()) {
                this.mAddGuest.setTitle(17040448);
                this.mAddGuest.setSummary(R.string.guest_resetting);
                this.mAddGuest.setEnabled(false);
                return true;
            }
            this.mAddGuest.setTitle(R$string.guest_new_guest);
            this.mAddGuest.setEnabled(canSwitchUserNow());
            return true;
        }
        this.mAddGuest.setVisible(false);
        return false;
    }

    private void updateAddUser(Context context) {
        updateAddUserCommon(context, this.mAddUser, this.mUserCaps.mCanAddRestrictedProfile);
        this.mAddUser.setIcon(centerAndTint(context.getDrawable(R.drawable.ic_account_circle_filled)));
    }

    private void updateAddSupervisedUser(Context context) {
        if (!TextUtils.isEmpty(this.mConfigSupervisedUserCreationPackage)) {
            updateAddUserCommon(context, this.mAddSupervisedUser, false);
            this.mAddSupervisedUser.setIcon(centerAndTint(context.getDrawable(R.drawable.ic_add_supervised_user)));
            return;
        }
        this.mAddSupervisedUser.setVisible(false);
    }

    private void updateAddUserCommon(Context context, RestrictedPreference restrictedPreference, boolean z) {
        UserCapabilities userCapabilities = this.mUserCaps;
        boolean z2 = false;
        if ((userCapabilities.mCanAddUser || userCapabilities.mDisallowAddUserSetByAdmin) && WizardManagerHelper.isDeviceProvisioned(context) && this.mUserCaps.mUserSwitcherEnabled) {
            restrictedPreference.setVisible(true);
            restrictedPreference.setSelectable(true);
            boolean z3 = this.mUserManager.canAddMoreUsers("android.os.usertype.full.SECONDARY") || (z && this.mUserManager.canAddMoreUsers("android.os.usertype.full.RESTRICTED"));
            if (z3 && !this.mAddingUser && canSwitchUserNow()) {
                z2 = true;
            }
            restrictedPreference.setEnabled(z2);
            if (!z3) {
                restrictedPreference.setSummary(getString(R.string.user_add_max_count));
            } else {
                restrictedPreference.setSummary((CharSequence) null);
            }
            if (restrictedPreference.isEnabled()) {
                UserCapabilities userCapabilities2 = this.mUserCaps;
                restrictedPreference.setDisabledByAdmin(userCapabilities2.mDisallowAddUser ? userCapabilities2.mEnforcedAdmin : null);
                return;
            }
            return;
        }
        restrictedPreference.setVisible(false);
    }

    private Drawable centerAndTint(Drawable drawable) {
        drawable.setTintBlendMode(BlendMode.SRC_IN);
        drawable.setTint(Utils.getColorAttrDefaultColor(getContext(), 16842806));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{getContext().getDrawable(R.drawable.user_avatar_bg).mutate(), drawable});
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.multiple_users_avatar_size);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.multiple_users_user_icon_size);
        layerDrawable.setLayerSize(1, dimensionPixelSize, dimensionPixelSize);
        layerDrawable.setLayerSize(0, dimensionPixelSize2, dimensionPixelSize2);
        layerDrawable.setLayerGravity(1, 17);
        return layerDrawable;
    }

    int getRealUsersCount() {
        return (int) this.mUserManager.getUsers().stream().filter(new Predicate() { // from class: com.android.settings.users.UserSettings$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lambda$getRealUsersCount$8;
                lambda$getRealUsersCount$8 = UserSettings.lambda$getRealUsersCount$8((UserInfo) obj);
                return lambda$getRealUsersCount$8;
            }
        }).count();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getRealUsersCount$8(UserInfo userInfo) {
        return (userInfo.isGuest() || userInfo.isProfile()) ? false : true;
    }

    private void loadIconsAsync(List<Integer> list) {
        new AsyncTask<List<Integer>, Void, Void>() { // from class: com.android.settings.users.UserSettings.14
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r1) {
                UserSettings.this.updateUserList();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(List<Integer>... listArr) {
                for (Integer num : listArr[0]) {
                    int intValue = num.intValue();
                    Bitmap userIcon = UserSettings.this.mUserManager.getUserIcon(intValue);
                    if (userIcon == null) {
                        userIcon = UserSettings.getDefaultUserIconAsBitmap(UserSettings.this.getContext().getResources(), intValue);
                    }
                    UserSettings.this.mUserIcons.append(intValue, userIcon);
                }
                return null;
            }
        }.execute(list);
    }

    private Drawable getEncircledDefaultIcon() {
        if (this.mDefaultIconDrawable == null) {
            this.mDefaultIconDrawable = encircleUserIcon(getDefaultUserIconAsBitmap(getContext().getResources(), -10000));
        }
        return this.mDefaultIconDrawable;
    }

    private void setPhotoId(Preference preference, UserInfo userInfo) {
        Bitmap bitmap = this.mUserIcons.get(userInfo.id);
        if (bitmap != null) {
            preference.setIcon(encircleUserIcon(bitmap));
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        if (isCurrentUserGuest()) {
            Preference preference2 = this.mGuestResetPreference;
            if (preference2 != null && preference == preference2) {
                showDialog(13);
                return true;
            }
            Preference preference3 = this.mGuestExitPreference;
            if (preference3 != null && preference == preference3) {
                if (this.mUserCaps.mIsEphemeral) {
                    showDialog(14);
                } else {
                    showDialog(15);
                }
                return true;
            }
        }
        if (preference == this.mMePreference) {
            if (!isCurrentUserGuest()) {
                showDialog(9);
                return true;
            }
        } else if (preference instanceof UserPreference) {
            openUserDetails(this.mUserManager.getUserInfo(((UserPreference) preference).getUserId()), false);
            return true;
        } else if (preference == this.mAddUser) {
            if (this.mUserCaps.mCanAddRestrictedProfile) {
                showDialog(6);
            } else {
                onAddUserClicked(1);
            }
            return true;
        } else if (preference == this.mAddSupervisedUser) {
            this.mMetricsFeatureProvider.action(getActivity(), 1786, new Pair[0]);
            Trace.beginSection("UserSettings.addSupervisedUser");
            onAddSupervisedUserClicked();
            Trace.endSection();
            return true;
        } else {
            RestrictedPreference restrictedPreference = this.mAddGuest;
            if (preference == restrictedPreference) {
                restrictedPreference.setEnabled(false);
                onAddGuestClicked();
                return true;
            }
        }
        return false;
    }

    private Drawable encircleUserIcon(Bitmap bitmap) {
        return new CircleFramedDrawable(bitmap, getActivity().getResources().getDimensionPixelSize(R.dimen.multiple_users_user_icon_size));
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        synchronized (this.mUserLock) {
            this.mRemovingUserId = -1;
            updateUserList();
        }
    }

    @Override // com.android.settings.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_users;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap getDefaultUserIconAsBitmap(Resources resources, int i) {
        Bitmap bitmap = sDarkDefaultUserBitmapCache.get(i);
        if (bitmap == null) {
            Bitmap convertToBitmapAtUserIconSize = UserIcons.convertToBitmapAtUserIconSize(resources, UserIcons.getDefaultUserIcon(resources, i, false));
            sDarkDefaultUserBitmapCache.put(i, convertToBitmapAtUserIconSize);
            return convertToBitmapAtUserIconSize;
        }
        return bitmap;
    }

    static boolean assignDefaultPhoto(Context context, int i) {
        if (context == null) {
            return false;
        }
        ((UserManager) context.getSystemService("user")).setUserIcon(i, getDefaultUserIconAsBitmap(context.getResources(), i));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void copyMeProfilePhoto(Context context, UserInfo userInfo) {
        Uri uri = ContactsContract.Profile.CONTENT_URI;
        int myUserId = userInfo != null ? userInfo.id : UserHandle.myUserId();
        InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), uri, true);
        if (openContactPhotoInputStream == null) {
            assignDefaultPhoto(context, myUserId);
            return;
        }
        ((UserManager) context.getSystemService("user")).setUserIcon(myUserId, UserIcons.convertToBitmapAtUserIconSize(context.getResources(), CircleFramedDrawable.getInstance(context, BitmapFactory.decodeStream(openContactPhotoInputStream))));
        try {
            openContactPhotoInputStream.close();
        } catch (IOException unused) {
        }
    }
}
