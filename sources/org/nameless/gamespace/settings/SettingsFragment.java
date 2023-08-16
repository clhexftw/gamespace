package org.nameless.gamespace.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult;
import androidx.preference.Preference;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
import org.nameless.gamespace.preferences.AppListPreferences;
import org.nameless.gamespace.preferences.appselector.AppSelectorActivity;
/* compiled from: SettingsFragment.kt */
/* loaded from: classes.dex */
public final class SettingsFragment extends Hilt_SettingsFragment {
    private AppListPreferences apps;
    private final ActivityResultLauncher<Intent> perAppResult;
    private final ActivityResultLauncher<Intent> selectorResult;

    public SettingsFragment() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts$StartActivityForResult(), new ActivityResultCallback() { // from class: org.nameless.gamespace.settings.SettingsFragment$selectorResult$1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(ActivityResult activityResult) {
                AppListPreferences appListPreferences;
                appListPreferences = SettingsFragment.this.apps;
                if (appListPreferences != null) {
                    appListPreferences.useSelectorResult(activityResult);
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…ectorResult(it)\n        }");
        this.selectorResult = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts$StartActivityForResult(), new ActivityResultCallback() { // from class: org.nameless.gamespace.settings.SettingsFragment$perAppResult$1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(ActivityResult activityResult) {
                AppListPreferences appListPreferences;
                appListPreferences = SettingsFragment.this.apps;
                if (appListPreferences != null) {
                    appListPreferences.usePerAppResult(activityResult);
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…erAppResult(it)\n        }");
        this.perAppResult = registerForActivityResult2;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        setPreferencesFromResource(R.xml.root_preferences, str);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        AppListPreferences appListPreferences = (AppListPreferences) findPreference("gamespace_game_list");
        this.apps = appListPreferences;
        if (appListPreferences != null) {
            appListPreferences.onRegisteredAppClick(new Function1<String, Unit>() { // from class: org.nameless.gamespace.settings.SettingsFragment$onViewCreated$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    ActivityResultLauncher activityResultLauncher;
                    Intrinsics.checkNotNullParameter(it, "it");
                    activityResultLauncher = SettingsFragment.this.perAppResult;
                    Intent intent = new Intent(SettingsFragment.this.getContext(), PerAppSettingsActivity.class);
                    intent.putExtra("package_name", it);
                    activityResultLauncher.launch(intent);
                }
            });
        }
        Preference findPreference = findPreference("add_game");
        if (findPreference != null) {
            findPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: org.nameless.gamespace.settings.SettingsFragment$onViewCreated$2
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    ActivityResultLauncher activityResultLauncher;
                    activityResultLauncher = SettingsFragment.this.selectorResult;
                    activityResultLauncher.launch(new Intent(SettingsFragment.this.getContext(), AppSelectorActivity.class));
                    return true;
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AppListPreferences appListPreferences = this.apps;
        if (appListPreferences != null) {
            appListPreferences.updateAppList();
        }
    }
}
