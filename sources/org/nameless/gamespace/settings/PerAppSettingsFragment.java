package org.nameless.gamespace.settings;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.nameless.gamespace.R;
import org.nameless.gamespace.data.GameConfig;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.data.UserGame;
import org.nameless.gamespace.utils.GameModeUtils;
/* compiled from: PerAppSettingsFragment.kt */
/* loaded from: classes.dex */
public final class PerAppSettingsFragment extends Hilt_PerAppSettingsFragment implements Preference.OnPreferenceChangeListener {
    public static final Companion Companion = new Companion(null);
    private final Lazy currentGame$delegate = LazyKt.lazy(new Function0<ApplicationInfo>() { // from class: org.nameless.gamespace.settings.PerAppSettingsFragment$currentGame$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ApplicationInfo invoke() {
            Intent intent;
            String stringExtra;
            PackageManager packageManager;
            FragmentActivity activity = PerAppSettingsFragment.this.getActivity();
            if (activity == null || (intent = activity.getIntent()) == null || (stringExtra = intent.getStringExtra("package_name")) == null) {
                return null;
            }
            PerAppSettingsFragment perAppSettingsFragment = PerAppSettingsFragment.this;
            PackageManager.ApplicationInfoFlags of = PackageManager.ApplicationInfoFlags.of(0L);
            Context context = perAppSettingsFragment.getContext();
            if (context == null || (packageManager = context.getPackageManager()) == null) {
                return null;
            }
            return packageManager.getApplicationInfo(stringExtra, of);
        }
    });
    public GameModeUtils gameModeUtils;
    public SystemSettings settings;

    public final SystemSettings getSettings() {
        SystemSettings systemSettings = this.settings;
        if (systemSettings != null) {
            return systemSettings;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settings");
        return null;
    }

    public final GameModeUtils getGameModeUtils() {
        GameModeUtils gameModeUtils = this.gameModeUtils;
        if (gameModeUtils != null) {
            return gameModeUtils;
        }
        Intrinsics.throwUninitializedPropertyAccessException("gameModeUtils");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ApplicationInfo getCurrentGame() {
        return (ApplicationInfo) this.currentGame$delegate.getValue();
    }

    private final UserGame getCurrentConfig() {
        Object obj;
        Iterator<T> it = getSettings().getUserGames().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            String packageName = ((UserGame) next).getPackageName();
            ApplicationInfo currentGame = getCurrentGame();
            if (Intrinsics.areEqual(packageName, currentGame != null ? currentGame.packageName : null)) {
                obj = next;
                break;
            }
        }
        return (UserGame) obj;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        Context context = getContext();
        activity.setTitle(context != null ? context.getString(R.string.per_app_title) : null);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        setPreferencesFromResource(R.xml.per_app_preferences, str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x008a, code lost:
        if (r2 != false) goto L26;
     */
    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(android.view.View r6, android.os.Bundle r7) {
        /*
            r5 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            super.onViewCreated(r6, r7)
            java.lang.String r6 = "headers"
            androidx.preference.Preference r6 = r5.findPreference(r6)
            r7 = 0
            if (r6 == 0) goto L4c
            r0 = 2131558545(0x7f0d0091, float:1.8742409E38)
            r6.setLayoutResource(r0)
            android.content.pm.ApplicationInfo r0 = r5.getCurrentGame()
            if (r0 == 0) goto L2a
            android.content.Context r1 = r6.getContext()
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            android.graphics.drawable.Drawable r0 = r0.loadIcon(r1)
            goto L2b
        L2a:
            r0 = r7
        L2b:
            r6.setIcon(r0)
            android.content.Context r0 = r6.getContext()
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            if (r0 == 0) goto L48
            java.lang.String r1 = "packageManager"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            android.content.pm.ApplicationInfo r1 = r5.getCurrentGame()
            if (r1 == 0) goto L48
            java.lang.CharSequence r0 = r1.loadLabel(r0)
            goto L49
        L48:
            r0 = r7
        L49:
            r6.setTitle(r0)
        L4c:
            java.lang.String r6 = "per_app_preferred_mode"
            androidx.preference.Preference r6 = r5.findPreference(r6)
            androidx.preference.ListPreference r6 = (androidx.preference.ListPreference) r6
            if (r6 == 0) goto L6a
            org.nameless.gamespace.data.UserGame r0 = r5.getCurrentConfig()
            if (r0 == 0) goto L67
            int r0 = r0.getMode()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r6.setValue(r0)
        L67:
            r6.setOnPreferenceChangeListener(r5)
        L6a:
            java.lang.String r6 = "per_app_use_angle"
            androidx.preference.Preference r6 = r5.findPreference(r6)
            org.nameless.custom.preference.SwitchPreference r6 = (org.nameless.custom.preference.SwitchPreference) r6
            r0 = 1
            r1 = 0
            if (r6 == 0) goto Lcb
            android.content.Context r2 = r6.getContext()
            android.content.res.Resources r2 = r2.getResources()
            if (r2 == 0) goto L8c
            r3 = 2131034114(0x7f050002, float:1.7678736E38)
            boolean r2 = r2.getBoolean(r3)
            r6.setVisible(r2)
            if (r2 == 0) goto Lcb
        L8c:
            org.nameless.gamespace.utils.GameModeUtils r2 = r5.getGameModeUtils()
            android.content.pm.ActivityInfo r2 = r2.findAnglePackage()
            if (r2 == 0) goto L9e
            boolean r2 = r2.isEnabled()
            if (r2 != r0) goto L9e
            r2 = r0
            goto L9f
        L9e:
            r2 = r1
        L9f:
            if (r2 != 0) goto Lb3
            r6.setEnabled(r1)
            android.content.Context r2 = r6.getContext()
            r3 = 2131886325(0x7f1200f5, float:1.9407226E38)
            java.lang.String r2 = r2.getString(r3)
            r6.setSummary(r2)
            goto Lcb
        Lb3:
            org.nameless.gamespace.utils.GameModeUtils r2 = r5.getGameModeUtils()
            android.content.pm.ApplicationInfo r3 = r5.getCurrentGame()
            if (r3 == 0) goto Lc0
            java.lang.String r3 = r3.packageName
            goto Lc1
        Lc0:
            r3 = r7
        Lc1:
            boolean r2 = r2.isAngleUsed(r3)
            r6.setChecked(r2)
            r6.setOnPreferenceChangeListener(r5)
        Lcb:
            java.lang.String r6 = "per_app_unregister"
            androidx.preference.Preference r6 = r5.findPreference(r6)
            if (r6 == 0) goto Lff
            android.content.Context r2 = r6.getContext()
            r3 = 2131886682(0x7f12025a, float:1.940795E38)
            java.lang.Object[] r0 = new java.lang.Object[r0]
            android.content.pm.ApplicationInfo r4 = r5.getCurrentGame()
            if (r4 == 0) goto Lee
            android.content.Context r7 = r6.getContext()
            android.content.pm.PackageManager r7 = r7.getPackageManager()
            java.lang.CharSequence r7 = r4.loadLabel(r7)
        Lee:
            r0[r1] = r7
            java.lang.String r7 = r2.getString(r3, r0)
            r6.setSummary(r7)
            org.nameless.gamespace.settings.PerAppSettingsFragment$onViewCreated$4$1 r7 = new org.nameless.gamespace.settings.PerAppSettingsFragment$onViewCreated$4$1
            r7.<init>()
            r6.setOnPreferenceClickListener(r7)
        Lff:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.nameless.gamespace.settings.PerAppSettingsFragment.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        ApplicationInfo currentGame = getCurrentGame();
        if (currentGame == null) {
            return false;
        }
        String key = preference.getKey();
        if (Intrinsics.areEqual(key, "per_app_preferred_mode")) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
            Integer intOrNull = StringsKt.toIntOrNull((String) obj);
            int intValue = intOrNull != null ? intOrNull.intValue() : 1;
            GameModeUtils gameModeUtils = getGameModeUtils();
            String str = currentGame.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "gameInfo.packageName");
            gameModeUtils.setGameModeFor(str, getSettings(), intValue);
            return true;
        } else if (Intrinsics.areEqual(key, "per_app_use_angle")) {
            GameConfig.ModeBuilder modeBuilder = GameConfig.ModeBuilder.INSTANCE;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
            modeBuilder.setUseAngle(((Boolean) obj).booleanValue());
            List<GameConfig> build = modeBuilder.build();
            GameModeUtils gameModeUtils2 = getGameModeUtils();
            String str2 = currentGame.packageName;
            Intrinsics.checkNotNullExpressionValue(str2, "gameInfo.packageName");
            gameModeUtils2.setIntervention(str2, build);
            return true;
        } else {
            return false;
        }
    }

    /* compiled from: PerAppSettingsFragment.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
