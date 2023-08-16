package org.nameless.gamespace;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import androidx.fragment.app.Fragment;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_Lifecycle_Factory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.nameless.gamespace.data.AppSettings;
import org.nameless.gamespace.data.GameSession;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.gamebar.CallListener;
import org.nameless.gamespace.gamebar.CallListener_Factory;
import org.nameless.gamespace.gamebar.DanmakuService;
import org.nameless.gamespace.gamebar.DanmakuService_Factory;
import org.nameless.gamespace.gamebar.GameBarService;
import org.nameless.gamespace.gamebar.GameBarService_MembersInjector;
import org.nameless.gamespace.gamebar.SessionService;
import org.nameless.gamespace.gamebar.SessionService_MembersInjector;
import org.nameless.gamespace.preferences.appselector.AppSelectorActivity;
import org.nameless.gamespace.preferences.appselector.AppSelectorFragment;
import org.nameless.gamespace.preferences.appselector.AppSelectorFragment_MembersInjector;
import org.nameless.gamespace.settings.PerAppSettingsActivity;
import org.nameless.gamespace.settings.PerAppSettingsFragment;
import org.nameless.gamespace.settings.PerAppSettingsFragment_MembersInjector;
import org.nameless.gamespace.settings.SettingsActivity;
import org.nameless.gamespace.settings.SettingsFragment;
import org.nameless.gamespace.utils.GameModeUtils;
import org.nameless.gamespace.utils.ScreenUtils;
import org.nameless.gamespace.utils.di.MainModule_ProvideAppSettingsFactory;
import org.nameless.gamespace.utils.di.MainModule_ProvideBaseGsonFactory;
import org.nameless.gamespace.utils.di.MainModule_ProvideGameModeUtilsFactory;
import org.nameless.gamespace.utils.di.MainModule_ProvideGameSessionFactory;
import org.nameless.gamespace.utils.di.MainModule_ProvideScreenUtilsFactory;
import org.nameless.gamespace.utils.di.MainModule_ProvideSystemSettingsFactory;
/* loaded from: classes.dex */
public final class DaggerGameSpace_HiltComponents_SingletonC extends GameSpace_HiltComponents$SingletonC {
    private final ApplicationContextModule applicationContextModule;
    private Provider<AppSettings> provideAppSettingsProvider;
    private Provider<Context> provideContextProvider;
    private Provider<GameModeUtils> provideGameModeUtilsProvider;
    private Provider<GameSession> provideGameSessionProvider;
    private Provider<ScreenUtils> provideScreenUtilsProvider;
    private Provider<SystemSettings> provideSystemSettingsProvider;

    @Override // org.nameless.gamespace.GameSpace_GeneratedInjector
    public void injectGameSpace(GameSpace gameSpace) {
    }

    private DaggerGameSpace_HiltComponents_SingletonC(ApplicationContextModule applicationContextModule) {
        this.applicationContextModule = applicationContextModule;
        initialize(applicationContextModule);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(ApplicationContextModule applicationContextModule) {
        ApplicationContextModule_ProvideContextFactory create = ApplicationContextModule_ProvideContextFactory.create(applicationContextModule);
        this.provideContextProvider = create;
        this.provideAppSettingsProvider = DoubleCheck.provider(MainModule_ProvideAppSettingsFactory.create(create));
        Provider<GameModeUtils> provider = DoubleCheck.provider(MainModule_ProvideGameModeUtilsFactory.create(this.provideContextProvider));
        this.provideGameModeUtilsProvider = provider;
        this.provideSystemSettingsProvider = DoubleCheck.provider(MainModule_ProvideSystemSettingsFactory.create(this.provideContextProvider, provider));
        this.provideScreenUtilsProvider = DoubleCheck.provider(MainModule_ProvideScreenUtilsFactory.create(this.provideContextProvider));
        this.provideGameSessionProvider = DoubleCheck.provider(MainModule_ProvideGameSessionFactory.create(this.provideContextProvider, this.provideAppSettingsProvider, this.provideSystemSettingsProvider, MainModule_ProvideBaseGsonFactory.create()));
    }

    @Override // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.ActivityRetainedComponentBuilderEntryPoint
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
        return new ActivityRetainedCBuilder();
    }

    @Override // dagger.hilt.android.internal.managers.ServiceComponentManager.ServiceComponentBuilderEntryPoint
    public ServiceComponentBuilder serviceComponentBuilder() {
        return new ServiceCBuilder();
    }

    @Override // org.nameless.gamespace.utils.di.ServiceViewEntryPoint
    public AppSettings appSettings() {
        return this.provideAppSettingsProvider.get();
    }

    @Override // org.nameless.gamespace.utils.di.ServiceViewEntryPoint
    public SystemSettings systemSettings() {
        return this.provideSystemSettingsProvider.get();
    }

    @Override // org.nameless.gamespace.utils.di.ServiceViewEntryPoint
    public ScreenUtils screenUtils() {
        return this.provideScreenUtilsProvider.get();
    }

    @Override // org.nameless.gamespace.utils.di.ServiceViewEntryPoint
    public GameModeUtils gameModeUtils() {
        return this.provideGameModeUtilsProvider.get();
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private ApplicationContextModule applicationContextModule;

        private Builder() {
        }

        public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
            this.applicationContextModule = (ApplicationContextModule) Preconditions.checkNotNull(applicationContextModule);
            return this;
        }

        public GameSpace_HiltComponents$SingletonC build() {
            Preconditions.checkBuilderRequirement(this.applicationContextModule, ApplicationContextModule.class);
            return new DaggerGameSpace_HiltComponents_SingletonC(this.applicationContextModule);
        }
    }

    /* loaded from: classes.dex */
    private final class ActivityRetainedCBuilder implements ActivityRetainedComponentBuilder {
        private ActivityRetainedCBuilder() {
        }

        @Override // dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder
        public GameSpace_HiltComponents$ActivityRetainedC build() {
            return new ActivityRetainedCImpl();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ActivityRetainedCImpl extends GameSpace_HiltComponents$ActivityRetainedC {
        private Provider lifecycleProvider;

        private ActivityRetainedCImpl() {
            initialize();
        }

        private void initialize() {
            this.lifecycleProvider = DoubleCheck.provider(ActivityRetainedComponentManager_Lifecycle_Factory.create());
        }

        @Override // dagger.hilt.android.internal.managers.ActivityComponentManager.ActivityComponentBuilderEntryPoint
        public ActivityComponentBuilder activityComponentBuilder() {
            return new ActivityCBuilder();
        }

        @Override // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.ActivityRetainedLifecycleEntryPoint
        public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
            return (ActivityRetainedLifecycle) this.lifecycleProvider.get();
        }

        /* loaded from: classes.dex */
        private final class ActivityCBuilder implements ActivityComponentBuilder {
            private Activity activity;

            private ActivityCBuilder() {
            }

            @Override // dagger.hilt.android.internal.builders.ActivityComponentBuilder
            public ActivityCBuilder activity(Activity activity) {
                this.activity = (Activity) Preconditions.checkNotNull(activity);
                return this;
            }

            @Override // dagger.hilt.android.internal.builders.ActivityComponentBuilder
            public GameSpace_HiltComponents$ActivityC build() {
                Preconditions.checkBuilderRequirement(this.activity, Activity.class);
                return new ActivityCImpl(this.activity);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public final class ActivityCImpl extends GameSpace_HiltComponents$ActivityC {
            @Override // org.nameless.gamespace.preferences.appselector.AppSelectorActivity_GeneratedInjector
            public void injectAppSelectorActivity(AppSelectorActivity appSelectorActivity) {
            }

            @Override // org.nameless.gamespace.settings.PerAppSettingsActivity_GeneratedInjector
            public void injectPerAppSettingsActivity(PerAppSettingsActivity perAppSettingsActivity) {
            }

            @Override // org.nameless.gamespace.settings.SettingsActivity_GeneratedInjector
            public void injectSettingsActivity(SettingsActivity settingsActivity) {
            }

            private ActivityCImpl(Activity activity) {
            }

            @Override // dagger.hilt.android.internal.managers.FragmentComponentManager.FragmentComponentBuilderEntryPoint
            public FragmentComponentBuilder fragmentComponentBuilder() {
                return new FragmentCBuilder();
            }

            /* loaded from: classes.dex */
            private final class FragmentCBuilder implements FragmentComponentBuilder {
                private Fragment fragment;

                private FragmentCBuilder() {
                }

                @Override // dagger.hilt.android.internal.builders.FragmentComponentBuilder
                public FragmentCBuilder fragment(Fragment fragment) {
                    this.fragment = (Fragment) Preconditions.checkNotNull(fragment);
                    return this;
                }

                @Override // dagger.hilt.android.internal.builders.FragmentComponentBuilder
                public GameSpace_HiltComponents$FragmentC build() {
                    Preconditions.checkBuilderRequirement(this.fragment, Fragment.class);
                    return new FragmentCI(this.fragment);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* loaded from: classes.dex */
            public final class FragmentCI extends GameSpace_HiltComponents$FragmentC {
                @Override // org.nameless.gamespace.settings.SettingsFragment_GeneratedInjector
                public void injectSettingsFragment(SettingsFragment settingsFragment) {
                }

                private FragmentCI(Fragment fragment) {
                }

                @Override // org.nameless.gamespace.preferences.appselector.AppSelectorFragment_GeneratedInjector
                public void injectAppSelectorFragment(AppSelectorFragment appSelectorFragment) {
                    injectAppSelectorFragment2(appSelectorFragment);
                }

                @Override // org.nameless.gamespace.settings.PerAppSettingsFragment_GeneratedInjector
                public void injectPerAppSettingsFragment(PerAppSettingsFragment perAppSettingsFragment) {
                    injectPerAppSettingsFragment2(perAppSettingsFragment);
                }

                private AppSelectorFragment injectAppSelectorFragment2(AppSelectorFragment appSelectorFragment) {
                    AppSelectorFragment_MembersInjector.injectSettings(appSelectorFragment, (SystemSettings) DaggerGameSpace_HiltComponents_SingletonC.this.provideSystemSettingsProvider.get());
                    return appSelectorFragment;
                }

                private PerAppSettingsFragment injectPerAppSettingsFragment2(PerAppSettingsFragment perAppSettingsFragment) {
                    PerAppSettingsFragment_MembersInjector.injectSettings(perAppSettingsFragment, (SystemSettings) DaggerGameSpace_HiltComponents_SingletonC.this.provideSystemSettingsProvider.get());
                    PerAppSettingsFragment_MembersInjector.injectGameModeUtils(perAppSettingsFragment, (GameModeUtils) DaggerGameSpace_HiltComponents_SingletonC.this.provideGameModeUtilsProvider.get());
                    return perAppSettingsFragment;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    private final class ServiceCBuilder implements ServiceComponentBuilder {
        private Service service;

        private ServiceCBuilder() {
        }

        @Override // dagger.hilt.android.internal.builders.ServiceComponentBuilder
        public ServiceCBuilder service(Service service) {
            this.service = (Service) Preconditions.checkNotNull(service);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ServiceComponentBuilder
        public GameSpace_HiltComponents$ServiceC build() {
            Preconditions.checkBuilderRequirement(this.service, Service.class);
            return new ServiceCImpl(this.service);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ServiceCImpl extends GameSpace_HiltComponents$ServiceC {
        private Provider<CallListener> callListenerProvider;
        private Provider<DanmakuService> danmakuServiceProvider;

        private ServiceCImpl(Service service) {
            initialize(service);
        }

        private void initialize(Service service) {
            this.danmakuServiceProvider = DoubleCheck.provider(DanmakuService_Factory.create(DaggerGameSpace_HiltComponents_SingletonC.this.provideContextProvider, DaggerGameSpace_HiltComponents_SingletonC.this.provideAppSettingsProvider));
            this.callListenerProvider = DoubleCheck.provider(CallListener_Factory.create(DaggerGameSpace_HiltComponents_SingletonC.this.provideContextProvider, DaggerGameSpace_HiltComponents_SingletonC.this.provideAppSettingsProvider));
        }

        @Override // org.nameless.gamespace.gamebar.GameBarService_GeneratedInjector
        public void injectGameBarService(GameBarService gameBarService) {
            injectGameBarService2(gameBarService);
        }

        @Override // org.nameless.gamespace.gamebar.SessionService_GeneratedInjector
        public void injectSessionService(SessionService sessionService) {
            injectSessionService2(sessionService);
        }

        private GameBarService injectGameBarService2(GameBarService gameBarService) {
            GameBarService_MembersInjector.injectAppSettings(gameBarService, (AppSettings) DaggerGameSpace_HiltComponents_SingletonC.this.provideAppSettingsProvider.get());
            GameBarService_MembersInjector.injectScreenUtils(gameBarService, (ScreenUtils) DaggerGameSpace_HiltComponents_SingletonC.this.provideScreenUtilsProvider.get());
            GameBarService_MembersInjector.injectDanmakuService(gameBarService, this.danmakuServiceProvider.get());
            return gameBarService;
        }

        private SessionService injectSessionService2(SessionService sessionService) {
            SessionService_MembersInjector.injectAppSettings(sessionService, (AppSettings) DaggerGameSpace_HiltComponents_SingletonC.this.provideAppSettingsProvider.get());
            SessionService_MembersInjector.injectSettings(sessionService, (SystemSettings) DaggerGameSpace_HiltComponents_SingletonC.this.provideSystemSettingsProvider.get());
            SessionService_MembersInjector.injectSession(sessionService, (GameSession) DaggerGameSpace_HiltComponents_SingletonC.this.provideGameSessionProvider.get());
            SessionService_MembersInjector.injectScreenUtils(sessionService, (ScreenUtils) DaggerGameSpace_HiltComponents_SingletonC.this.provideScreenUtilsProvider.get());
            SessionService_MembersInjector.injectGameModeUtils(sessionService, (GameModeUtils) DaggerGameSpace_HiltComponents_SingletonC.this.provideGameModeUtilsProvider.get());
            SessionService_MembersInjector.injectCallListener(sessionService, this.callListenerProvider.get());
            return sessionService;
        }
    }
}
