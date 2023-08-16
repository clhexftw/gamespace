package org.nameless.gamespace.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.activity.result.ActivityResult;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import dagger.hilt.EntryPoints;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
import org.nameless.gamespace.data.GameConfig;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.data.UserGame;
import org.nameless.gamespace.utils.GameModeUtils;
import org.nameless.gamespace.utils.di.ServiceViewEntryPoint;
/* compiled from: AppListPreferences.kt */
/* loaded from: classes.dex */
public final class AppListPreferences extends PreferenceCategory implements Preference.OnPreferenceClickListener {
    public static final Companion Companion = new Companion(null);
    private final List<UserGame> apps;
    private final Lazy gameModeUtils$delegate;
    private final Lazy makeAddPref$delegate;
    private Function1<? super String, Unit> registeredAppClickAction;
    private final Lazy systemSettings$delegate;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AppListPreferences(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ AppListPreferences(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppListPreferences(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Lazy lazy;
        Lazy lazy2;
        Lazy lazy3;
        Intrinsics.checkNotNullParameter(context, "context");
        this.apps = new ArrayList();
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<SystemSettings>() { // from class: org.nameless.gamespace.preferences.AppListPreferences$systemSettings$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final SystemSettings invoke() {
                Object obj = EntryPoints.get(context.getApplicationContext(), ServiceViewEntryPoint.class);
                Intrinsics.checkNotNullExpressionValue(obj, "get(applicationContext, T::class.java)");
                return ((ServiceViewEntryPoint) obj).systemSettings();
            }
        });
        this.systemSettings$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<GameModeUtils>() { // from class: org.nameless.gamespace.preferences.AppListPreferences$gameModeUtils$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final GameModeUtils invoke() {
                Object obj = EntryPoints.get(context.getApplicationContext(), ServiceViewEntryPoint.class);
                Intrinsics.checkNotNullExpressionValue(obj, "get(applicationContext, T::class.java)");
                return ((ServiceViewEntryPoint) obj).gameModeUtils();
            }
        });
        this.gameModeUtils$delegate = lazy2;
        setOrderingAsAdded(false);
        lazy3 = LazyKt__LazyJVMKt.lazy(new Function0<Preference>() { // from class: org.nameless.gamespace.preferences.AppListPreferences$makeAddPref$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Preference invoke() {
                Preference preference = new Preference(context);
                Context context2 = context;
                AppListPreferences appListPreferences = this;
                preference.setTitle(context2.getString(R.string.game_list_add_title));
                preference.setKey("add_game");
                preference.setIcon(R.drawable.ic_add);
                preference.setPersistent(false);
                preference.setOnPreferenceClickListener(appListPreferences);
                return preference;
            }
        });
        this.makeAddPref$delegate = lazy3;
    }

    private final SystemSettings getSystemSettings() {
        return (SystemSettings) this.systemSettings$delegate.getValue();
    }

    private final GameModeUtils getGameModeUtils() {
        return (GameModeUtils) this.gameModeUtils$delegate.getValue();
    }

    private final Preference getMakeAddPref() {
        return (Preference) this.makeAddPref$delegate.getValue();
    }

    private final ApplicationInfo getAppInfo(String str) {
        try {
            return getContext().getPackageManager().getApplicationInfo(str, PackageManager.ApplicationInfoFlags.of(128L));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public final void updateAppList() {
        int collectionSizeOrDefault;
        List<Preference> sortedWith;
        this.apps.clear();
        List<UserGame> userGames = getSystemSettings().getUserGames();
        if (!(userGames == null || userGames.isEmpty())) {
            this.apps.addAll(getSystemSettings().getUserGames());
        }
        removeAll();
        addPreference(getMakeAddPref());
        ArrayList<UserGame> arrayList = new ArrayList();
        for (Object obj : this.apps) {
            if (getAppInfo(((UserGame) obj).getPackageName()) != null) {
                arrayList.add(obj);
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (UserGame userGame : arrayList) {
            ApplicationInfo appInfo = getAppInfo(userGame.getPackageName());
            Preference preference = new Preference(getContext());
            preference.setKey(userGame.getPackageName());
            Drawable drawable = null;
            preference.setTitle(appInfo != null ? appInfo.loadLabel(preference.getContext().getPackageManager()) : null);
            GameModeUtils.Companion companion = GameModeUtils.Companion;
            Context context = preference.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            preference.setSummary(companion.describeGameMode(context, userGame.getMode()));
            if (appInfo != null) {
                drawable = appInfo.loadIcon(preference.getContext().getPackageManager());
            }
            preference.setIcon(drawable);
            preference.setLayoutResource(R.layout.library_item);
            preference.setPersistent(false);
            preference.setOnPreferenceClickListener(this);
            arrayList2.add(preference);
        }
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: org.nameless.gamespace.preferences.AppListPreferences$updateAppList$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int compareValues;
                String obj2 = ((Preference) t).getTitle().toString();
                Locale locale = Locale.ROOT;
                String lowerCase = obj2.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                String lowerCase2 = ((Preference) t2).getTitle().toString().toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(lowerCase, lowerCase2);
                return compareValues;
            }
        });
        for (Preference preference2 : sortedWith) {
            addPreference(preference2);
        }
    }

    private final void registerApp(String str) {
        boolean z;
        List<UserGame> list = this.apps;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (UserGame userGame : list) {
                if (Intrinsics.areEqual(userGame.getPackageName(), str)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (!z) {
            this.apps.add(new UserGame(str, 0, 2, null));
        }
        getSystemSettings().setUserGames(this.apps);
        getGameModeUtils().setIntervention(str, GameConfig.ModeBuilder.INSTANCE.build());
        updateAppList();
    }

    private final void unregisterApp(final String str) {
        this.apps.removeIf(new Predicate() { // from class: org.nameless.gamespace.preferences.AppListPreferences$unregisterApp$1
            @Override // java.util.function.Predicate
            public final boolean test(UserGame it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Intrinsics.areEqual(it.getPackageName(), str);
            }
        });
        getSystemSettings().setUserGames(this.apps);
        getGameModeUtils().setIntervention(str, null);
        updateAppList();
    }

    @Override // androidx.preference.PreferenceGroup, androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        updateAppList();
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        Function1<? super String, Unit> function1;
        Intrinsics.checkNotNullParameter(preference, "preference");
        if (Intrinsics.areEqual(preference, getMakeAddPref()) || (function1 = this.registeredAppClickAction) == null) {
            return true;
        }
        if (function1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("registeredAppClickAction");
            function1 = null;
        }
        String key = preference.getKey();
        Intrinsics.checkNotNullExpressionValue(key, "preference.key");
        function1.invoke(key);
        return true;
    }

    public final void onRegisteredAppClick(Function1<? super String, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.registeredAppClickAction = action;
    }

    public final void usePerAppResult(ActivityResult activityResult) {
        Intent data;
        String stringExtra;
        if (activityResult != null) {
            if (!(activityResult.getResultCode() == -1)) {
                activityResult = null;
            }
            if (activityResult == null || (data = activityResult.getData()) == null || (stringExtra = data.getStringExtra("per_app_unregister")) == null) {
                return;
            }
            unregisterApp(stringExtra);
        }
    }

    public final void useSelectorResult(ActivityResult activityResult) {
        Intent data;
        String stringExtra;
        if (activityResult != null) {
            if (!(activityResult.getResultCode() == -1)) {
                activityResult = null;
            }
            if (activityResult == null || (data = activityResult.getData()) == null || (stringExtra = data.getStringExtra("selected_app")) == null) {
                return;
            }
            registerApp(stringExtra);
        }
    }

    /* compiled from: AppListPreferences.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
