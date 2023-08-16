package com.android.settings.homepage;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.FeatureFlagUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.window.embedding.ActivityEmbeddingController;
import androidx.window.embedding.SplitRule;
import com.android.settings.R;
import com.android.settings.Settings;
import com.android.settings.SettingsApplication;
import com.android.settings.accounts.AvatarViewMixin;
import com.android.settings.activityembedding.ActivityEmbeddingRulesController;
import com.android.settings.activityembedding.ActivityEmbeddingUtils;
import com.android.settings.core.CategoryMixin;
import com.android.settings.homepage.SettingsHomepageActivity;
import com.android.settings.homepage.contextualcards.ContextualCardsFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.safetycenter.SafetyCenterManagerWrapper;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.HideNonSystemOverlayMixin;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.function.Consumer;
/* loaded from: classes.dex */
public class SettingsHomepageActivity extends FragmentActivity implements CategoryMixin.CategoryHandler {
    static final int DEFAULT_HIGHLIGHT_MENU_KEY = R.string.menu_key_network;
    static final String EXTRA_INITIAL_REFERRER = "initial_referrer";
    private ActivityEmbeddingController mActivityEmbeddingController;
    private CategoryMixin mCategoryMixin;
    private View mHomepageView;
    private boolean mIsEmbeddingActivityEnabled;
    private boolean mIsRegularLayout = true;
    private boolean mIsTwoPane;
    private Set<HomepageLoadedListener> mLoadedListeners;
    private TopLevelSettings mMainFragment;
    private View mSuggestionView;
    private View mTwoPaneSuggestionView;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface FragmentCreator<T extends Fragment> {
        T create();

        default void init(Fragment fragment) {
        }
    }

    /* loaded from: classes.dex */
    public interface HomepageLoadedListener {
        void onHomepageLoaded();
    }

    public boolean addHomepageLoadedListener(HomepageLoadedListener homepageLoadedListener) {
        if (this.mHomepageView == null) {
            return false;
        }
        if (this.mLoadedListeners.contains(homepageLoadedListener)) {
            return true;
        }
        this.mLoadedListeners.add(homepageLoadedListener);
        return true;
    }

    public void showHomepageWithSuggestion(boolean z) {
        if (this.mHomepageView == null) {
            return;
        }
        Log.i("SettingsHomepageActivity", "showHomepageWithSuggestion: " + z);
        View view = this.mHomepageView;
        this.mSuggestionView.setVisibility(z ? 0 : 8);
        this.mTwoPaneSuggestionView.setVisibility(z ? 0 : 8);
        this.mHomepageView = null;
        this.mLoadedListeners.forEach(new Consumer() { // from class: com.android.settings.homepage.SettingsHomepageActivity$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((SettingsHomepageActivity.HomepageLoadedListener) obj).onHomepageLoaded();
            }
        });
        this.mLoadedListeners.clear();
        view.setVisibility(0);
    }

    public TopLevelSettings getMainFragment() {
        return this.mMainFragment;
    }

    @Override // com.android.settings.core.CategoryMixin.CategoryHandler
    public CategoryMixin getCategoryMixin() {
        return this.mCategoryMixin;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean isEmbeddingActivityEnabled = ActivityEmbeddingUtils.isEmbeddingActivityEnabled(this);
        this.mIsEmbeddingActivityEnabled = isEmbeddingActivityEnabled;
        if (isEmbeddingActivityEnabled) {
            UserManager userManager = (UserManager) getSystemService(UserManager.class);
            if (userManager.getUserInfo(getUserId()).isManagedProfile()) {
                Intent putExtra = new Intent(getIntent()).addFlags(33554432).putExtra("user_handle", getUser()).putExtra(EXTRA_INITIAL_REFERRER, getCurrentReferrer());
                if (TextUtils.equals(putExtra.getAction(), "android.settings.SETTINGS_EMBED_DEEP_LINK_ACTIVITY") && (this instanceof DeepLinkHomepageActivity)) {
                    putExtra.setClass(this, DeepLinkHomepageActivityInternal.class);
                }
                putExtra.removeFlags(268435456);
                startActivityAsUser(putExtra, userManager.getPrimaryUser().getUserHandle());
                finish();
                return;
            }
        }
        setupEdgeToEdge();
        setContentView(R.layout.settings_homepage_container);
        ActivityEmbeddingController activityEmbeddingController = ActivityEmbeddingController.getInstance(this);
        this.mActivityEmbeddingController = activityEmbeddingController;
        this.mIsTwoPane = activityEmbeddingController.isActivityEmbedded(this);
        updateAppBarMinHeight();
        initHomepageContainer();
        updateHomepageAppBar();
        updateHomepageBackground();
        this.mLoadedListeners = new ArraySet();
        initSearchBarView();
        getLifecycle().addObserver(new HideNonSystemOverlayMixin(this));
        this.mCategoryMixin = new CategoryMixin(this);
        getLifecycle().addObserver(this.mCategoryMixin);
        final String highlightMenuKey = getHighlightMenuKey();
        if (!((ActivityManager) getSystemService(ActivityManager.class)).isLowRamDevice()) {
            initAvatarView();
            showSuggestionFragment(this.mIsEmbeddingActivityEnabled && !TextUtils.equals(getString(DEFAULT_HIGHLIGHT_MENU_KEY), highlightMenuKey));
            if (FeatureFlagUtils.isEnabled(this, "settings_contextual_home")) {
                showFragment(new FragmentCreator() { // from class: com.android.settings.homepage.SettingsHomepageActivity$$ExternalSyntheticLambda0
                    @Override // com.android.settings.homepage.SettingsHomepageActivity.FragmentCreator
                    public final Fragment create() {
                        ContextualCardsFragment lambda$onCreate$1;
                        lambda$onCreate$1 = SettingsHomepageActivity.lambda$onCreate$1();
                        return lambda$onCreate$1;
                    }
                }, R.id.contextual_cards_content);
                ((FrameLayout) findViewById(R.id.main_content)).getLayoutTransition().enableTransitionType(4);
            }
        }
        this.mMainFragment = (TopLevelSettings) showFragment(new FragmentCreator() { // from class: com.android.settings.homepage.SettingsHomepageActivity$$ExternalSyntheticLambda1
            @Override // com.android.settings.homepage.SettingsHomepageActivity.FragmentCreator
            public final Fragment create() {
                TopLevelSettings lambda$onCreate$2;
                lambda$onCreate$2 = SettingsHomepageActivity.lambda$onCreate$2(highlightMenuKey);
                return lambda$onCreate$2;
            }
        }, R.id.main_content);
        launchDeepLinkIntentToRight();
        updateHomepagePaddings();
        updateSplitLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ContextualCardsFragment lambda$onCreate$1() {
        return new ContextualCardsFragment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ TopLevelSettings lambda$onCreate$2(String str) {
        TopLevelSettings topLevelSettings = new TopLevelSettings();
        topLevelSettings.getArguments().putString(":settings:fragment_args_key", str);
        return topLevelSettings;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        ((SettingsApplication) getApplication()).setHomeActivity(this);
        super.onStart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        reloadHighlightMenuKey();
        if (isFinishing()) {
            return;
        }
        if (ActivityEmbeddingUtils.isEmbeddingActivityEnabled(this) && (intent.getFlags() & 67108864) != 0) {
            initSplitPairRules();
        }
        launchDeepLinkIntentToRight();
    }

    void initSplitPairRules() {
        new ActivityEmbeddingRulesController(getApplicationContext()).initRules();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean isActivityEmbedded = this.mActivityEmbeddingController.isActivityEmbedded(this);
        if (this.mIsTwoPane != isActivityEmbedded) {
            this.mIsTwoPane = isActivityEmbedded;
            updateHomepageAppBar();
            updateHomepageBackground();
            updateHomepagePaddings();
        }
        updateSplitLayout();
    }

    private void updateSplitLayout() {
        int i;
        if (this.mIsEmbeddingActivityEnabled) {
            if (this.mIsTwoPane) {
                if (this.mIsRegularLayout == ActivityEmbeddingUtils.isRegularHomepageLayout(this)) {
                    return;
                }
            } else if (this.mIsRegularLayout) {
                return;
            }
            this.mIsRegularLayout = !this.mIsRegularLayout;
            View findViewById = findViewById(R.id.search_bar_title);
            if (findViewById != null) {
                Resources resources = getResources();
                if (this.mIsRegularLayout) {
                    i = R.dimen.search_bar_title_padding_start_regular_two_pane;
                } else {
                    i = R.dimen.search_bar_title_padding_start;
                }
                findViewById.setPaddingRelative(resources.getDimensionPixelSize(i), 0, 0, 0);
            }
            getSupportFragmentManager().getFragments().forEach(new Consumer() { // from class: com.android.settings.homepage.SettingsHomepageActivity$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SettingsHomepageActivity.this.lambda$updateSplitLayout$3((Fragment) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSplitLayout$3(Fragment fragment) {
        if (fragment instanceof SplitLayoutListener) {
            ((SplitLayoutListener) fragment).onSplitLayoutChanged(this.mIsRegularLayout);
        }
    }

    private void setupEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(16908290), new OnApplyWindowInsetsListener() { // from class: com.android.settings.homepage.SettingsHomepageActivity$$ExternalSyntheticLambda2
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat lambda$setupEdgeToEdge$4;
                lambda$setupEdgeToEdge$4 = SettingsHomepageActivity.lambda$setupEdgeToEdge$4(view, windowInsetsCompat);
                return lambda$setupEdgeToEdge$4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ WindowInsetsCompat lambda$setupEdgeToEdge$4(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    private void initSearchBarView() {
        FeatureFactory.getFactory(this).getSearchFeatureProvider().initSearchToolbar(this, (Toolbar) findViewById(R.id.search_action_bar), 1502);
        if (this.mIsEmbeddingActivityEnabled) {
            FeatureFactory.getFactory(this).getSearchFeatureProvider().initSearchToolbar(this, (Toolbar) findViewById(R.id.search_action_bar_two_pane), 1502);
        }
    }

    private void initAvatarView() {
        ImageView imageView = (ImageView) findViewById(R.id.account_avatar);
        ImageView imageView2 = (ImageView) findViewById(R.id.account_avatar_two_pane_version);
        if (AvatarViewMixin.isAvatarSupported(this)) {
            imageView.setVisibility(0);
            getLifecycle().addObserver(new AvatarViewMixin(this, imageView));
            if (this.mIsEmbeddingActivityEnabled) {
                imageView2.setVisibility(0);
                getLifecycle().addObserver(new AvatarViewMixin(this, imageView2));
            }
        }
    }

    private void updateHomepageBackground() {
        int colorAttrDefaultColor;
        if (this.mIsEmbeddingActivityEnabled) {
            Window window = getWindow();
            if (this.mIsTwoPane) {
                colorAttrDefaultColor = getColor(R.color.settings_two_pane_background_color);
            } else {
                colorAttrDefaultColor = Utils.getColorAttrDefaultColor(this, 16842801);
            }
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(colorAttrDefaultColor);
            findViewById(16908290).setBackgroundColor(colorAttrDefaultColor);
        }
    }

    private void showSuggestionFragment(boolean z) {
        Class<? extends Fragment> contextualSuggestionFragment = FeatureFactory.getFactory(this).getSuggestionFeatureProvider(this).getContextualSuggestionFragment();
        if (contextualSuggestionFragment == null) {
            return;
        }
        int i = R.id.suggestion_content;
        this.mSuggestionView = findViewById(i);
        int i2 = R.id.two_pane_suggestion_content;
        this.mTwoPaneSuggestionView = findViewById(i2);
        View findViewById = findViewById(R.id.settings_homepage_container);
        this.mHomepageView = findViewById;
        findViewById.setVisibility(z ? 4 : 8);
        this.mHomepageView.postDelayed(new Runnable() { // from class: com.android.settings.homepage.SettingsHomepageActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                SettingsHomepageActivity.this.lambda$showSuggestionFragment$5();
            }
        }, 300L);
        showFragment(new SuggestionFragCreator(contextualSuggestionFragment, false), i);
        if (this.mIsEmbeddingActivityEnabled) {
            showFragment(new SuggestionFragCreator(contextualSuggestionFragment, true), i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSuggestionFragment$5() {
        showHomepageWithSuggestion(false);
    }

    private <T extends Fragment> T showFragment(FragmentCreator<T> fragmentCreator, int i) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        T t = (T) supportFragmentManager.findFragmentById(i);
        if (t == null) {
            t = fragmentCreator.create();
            fragmentCreator.init(t);
            beginTransaction.add(i, t);
        } else {
            fragmentCreator.init(t);
            beginTransaction.show(t);
        }
        beginTransaction.commit();
        return t;
    }

    private void launchDeepLinkIntentToRight() {
        Intent intent;
        int i;
        if (this.mIsEmbeddingActivityEnabled && (intent = getIntent()) != null && TextUtils.equals(intent.getAction(), "android.settings.SETTINGS_EMBED_DEEP_LINK_ACTIVITY")) {
            if (!(this instanceof DeepLinkHomepageActivity) && !(this instanceof DeepLinkHomepageActivityInternal)) {
                Log.e("SettingsHomepageActivity", "Not a deep link component");
                finish();
                return;
            }
            String stringExtra = intent.getStringExtra("android.provider.extra.SETTINGS_EMBEDDED_DEEP_LINK_INTENT_URI");
            if (TextUtils.isEmpty(stringExtra)) {
                Log.e("SettingsHomepageActivity", "No EXTRA_SETTINGS_EMBEDDED_DEEP_LINK_INTENT_URI to deep link");
                finish();
                return;
            }
            try {
                Intent parseUri = Intent.parseUri(stringExtra, 1);
                ComponentName resolveActivity = parseUri.resolveActivity(getPackageManager());
                if (resolveActivity == null) {
                    Log.e("SettingsHomepageActivity", "No valid target for the deep link intent: " + parseUri);
                    finish();
                    return;
                }
                try {
                    ActivityInfo activityInfo = getPackageManager().getActivityInfo(resolveActivity, 0);
                    UserHandle userHandle = (UserHandle) intent.getParcelableExtra("user_handle", UserHandle.class);
                    String initialReferrer = getInitialReferrer();
                    if (initialReferrer != null) {
                        try {
                            i = getPackageManager().getApplicationInfoAsUser(initialReferrer, PackageManager.ApplicationInfoFlags.of(0L), userHandle != null ? userHandle.getIdentifier() : getUserId()).uid;
                        } catch (PackageManager.NameNotFoundException e) {
                            Log.e("SettingsHomepageActivity", "Not able to get callerUid: " + e);
                            finish();
                            return;
                        }
                    } else {
                        i = -1;
                    }
                    if (!hasPrivilegedAccess(initialReferrer, i, activityInfo.packageName)) {
                        if (!activityInfo.exported) {
                            Log.e("SettingsHomepageActivity", "Target Activity is not exported");
                            finish();
                            return;
                        } else if (!isCallingAppPermitted(activityInfo.permission, i)) {
                            Log.e("SettingsHomepageActivity", "Calling app must have the permission of deep link Activity");
                            finish();
                            return;
                        }
                    }
                    parseUri.setComponent(resolveActivity);
                    intent.setAction(null);
                    parseUri.removeFlags(268959744);
                    parseUri.addFlags(33554432);
                    parseUri.replaceExtras(intent);
                    parseUri.putExtra("is_from_settings_homepage", true);
                    parseUri.putExtra("is_from_slice", false);
                    parseUri.setData((Uri) intent.getParcelableExtra("settings_large_screen_deep_link_intent_data"));
                    int flags = parseUri.getFlags() & 3;
                    if (parseUri.getData() != null && flags != 0 && checkUriPermission(parseUri.getData(), -1, i, flags) == -1) {
                        Log.e("SettingsHomepageActivity", "Calling app must have the permission to access Uri and grant permission");
                        finish();
                        return;
                    }
                    ComponentName componentName = new ComponentName(getApplicationContext(), getClass());
                    String action = parseUri.getAction();
                    SplitRule.FinishBehavior finishBehavior = SplitRule.FinishBehavior.ALWAYS;
                    ActivityEmbeddingRulesController.registerTwoPanePairRule(this, componentName, resolveActivity, action, finishBehavior, finishBehavior, true);
                    ActivityEmbeddingRulesController.registerTwoPanePairRule(this, new ComponentName(getApplicationContext(), Settings.class), resolveActivity, parseUri.getAction(), finishBehavior, finishBehavior, true);
                    if (userHandle != null) {
                        startActivityAsUser(parseUri, userHandle);
                    } else {
                        startActivity(parseUri);
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.e("SettingsHomepageActivity", "Failed to get target ActivityInfo: " + e2);
                    finish();
                }
            } catch (URISyntaxException e3) {
                Log.e("SettingsHomepageActivity", "Failed to parse deep link intent: " + e3);
                finish();
            }
        }
    }

    private boolean hasPrivilegedAccess(String str, int i, String str2) {
        int appId;
        if (TextUtils.equals(str, getPackageName())) {
            return true;
        }
        try {
            return UserHandle.isSameApp(i, getPackageManager().getApplicationInfo(str2, PackageManager.ApplicationInfoFlags.of(0L)).uid) || (appId = UserHandle.getAppId(i)) == 0 || appId == 1000;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("SettingsHomepageActivity", "Not able to get targetUid: " + e);
            return false;
        }
    }

    String getInitialReferrer() {
        String currentReferrer = getCurrentReferrer();
        if (TextUtils.equals(currentReferrer, getPackageName())) {
            String stringExtra = getIntent().getStringExtra(EXTRA_INITIAL_REFERRER);
            return TextUtils.isEmpty(stringExtra) ? currentReferrer : stringExtra;
        }
        return currentReferrer;
    }

    String getCurrentReferrer() {
        Intent intent = getIntent();
        intent.removeExtra("android.intent.extra.REFERRER");
        intent.removeExtra("android.intent.extra.REFERRER_NAME");
        Uri referrer = getReferrer();
        if (referrer != null) {
            return referrer.getHost();
        }
        return null;
    }

    boolean isCallingAppPermitted(String str, int i) {
        return TextUtils.isEmpty(str) || checkPermission(str, -1, i) == 0;
    }

    private String getHighlightMenuKey() {
        Intent intent = getIntent();
        if (intent != null && TextUtils.equals(intent.getAction(), "android.settings.SETTINGS_EMBED_DEEP_LINK_ACTIVITY")) {
            String stringExtra = intent.getStringExtra("android.provider.extra.SETTINGS_EMBEDDED_DEEP_LINK_HIGHLIGHT_MENU_KEY");
            if (!TextUtils.isEmpty(stringExtra)) {
                return maybeRemapMenuKey(stringExtra);
            }
        }
        return getString(DEFAULT_HIGHLIGHT_MENU_KEY);
    }

    private String maybeRemapMenuKey(String str) {
        boolean z = getString(R.string.menu_key_privacy).equals(str) || getString(R.string.menu_key_security).equals(str);
        int i = R.string.menu_key_safety_center;
        boolean equals = getString(i).equals(str);
        if (z && SafetyCenterManagerWrapper.get().isEnabled(this)) {
            return getString(i);
        }
        return (!equals || SafetyCenterManagerWrapper.get().isEnabled(this)) ? str : getString(R.string.menu_key_security);
    }

    void reloadHighlightMenuKey() {
        this.mMainFragment.getArguments().putString(":settings:fragment_args_key", getHighlightMenuKey());
        this.mMainFragment.reloadHighlightMenuKey();
    }

    private void initHomepageContainer() {
        View findViewById = findViewById(R.id.homepage_container);
        findViewById.setFocusableInTouchMode(true);
        findViewById.requestFocus();
    }

    private void updateHomepageAppBar() {
        if (this.mIsEmbeddingActivityEnabled) {
            updateAppBarMinHeight();
            if (this.mIsTwoPane) {
                findViewById(R.id.homepage_app_bar_regular_phone_view).setVisibility(8);
                findViewById(R.id.homepage_app_bar_two_pane_view).setVisibility(0);
                findViewById(R.id.suggestion_container_two_pane).setVisibility(0);
                return;
            }
            findViewById(R.id.homepage_app_bar_regular_phone_view).setVisibility(0);
            findViewById(R.id.homepage_app_bar_two_pane_view).setVisibility(8);
            findViewById(R.id.suggestion_container_two_pane).setVisibility(8);
        }
    }

    private void updateHomepagePaddings() {
        if (this.mIsEmbeddingActivityEnabled) {
            if (this.mIsTwoPane) {
                this.mMainFragment.setPaddingHorizontal(getResources().getDimensionPixelSize(R.dimen.homepage_padding_horizontal_two_pane));
            } else {
                this.mMainFragment.setPaddingHorizontal(0);
            }
            this.mMainFragment.updatePreferencePadding(this.mIsTwoPane);
        }
    }

    private void updateAppBarMinHeight() {
        int i;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.search_bar_height);
        Resources resources = getResources();
        if (this.mIsEmbeddingActivityEnabled && this.mIsTwoPane) {
            i = R.dimen.homepage_app_bar_padding_two_pane;
        } else {
            i = R.dimen.search_bar_margin;
        }
        findViewById(R.id.app_bar_container).setMinimumHeight(dimensionPixelSize + (resources.getDimensionPixelSize(i) * 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SuggestionFragCreator implements FragmentCreator {
        private final Class<? extends Fragment> mClass;
        private final boolean mIsTwoPaneLayout;

        SuggestionFragCreator(Class<? extends Fragment> cls, boolean z) {
            this.mClass = cls;
            this.mIsTwoPaneLayout = z;
        }

        @Override // com.android.settings.homepage.SettingsHomepageActivity.FragmentCreator
        public Fragment create() {
            try {
                return this.mClass.getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception e) {
                Log.w("SettingsHomepageActivity", "Cannot show fragment", e);
                return null;
            }
        }

        @Override // com.android.settings.homepage.SettingsHomepageActivity.FragmentCreator
        public void init(Fragment fragment) {
            if (fragment instanceof SplitLayoutListener) {
                ((SplitLayoutListener) fragment).setSplitLayoutSupported(this.mIsTwoPaneLayout);
            }
        }
    }
}
