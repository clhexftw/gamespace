package com.android.settings.core;

import android.R;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.text.LineBreakConfig;
import android.os.Bundle;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.SetupWizardUtils;
import com.android.settings.SubSettings;
import com.android.settings.core.CategoryMixin;
import com.android.settingslib.core.lifecycle.HideNonSystemOverlayMixin;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.resources.TextAppearanceConfig;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.google.android.setupdesign.util.ThemeHelper;
/* loaded from: classes.dex */
public class SettingsBaseActivity extends FragmentActivity implements CategoryMixin.CategoryHandler {
    protected AppBarLayout mAppBarLayout;
    protected CategoryMixin mCategoryMixin;
    protected CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;

    protected boolean isToolbarEnabled() {
        return true;
    }

    @Override // com.android.settings.core.CategoryMixin.CategoryHandler
    public CategoryMixin getCategoryMixin() {
        return this.mCategoryMixin;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isFinishing()) {
            return;
        }
        if (isLockTaskModePinned() && !isSettingsRunOnTop()) {
            Log.w("SettingsBaseActivity", "Devices lock task mode pinned.");
            finish();
        }
        System.currentTimeMillis();
        getLifecycle().addObserver(new HideNonSystemOverlayMixin(this));
        TextAppearanceConfig.setShouldLoadFontSynchronously(true);
        this.mCategoryMixin = new CategoryMixin(this);
        getLifecycle().addObserver(this.mCategoryMixin);
        if (!getTheme().obtainStyledAttributes(R.styleable.Theme).getBoolean(38, false)) {
            requestWindowFeature(1);
        }
        boolean isAnySetupWizard = WizardManagerHelper.isAnySetupWizard(getIntent());
        if (isAnySetupWizard && (this instanceof SubSettings)) {
            setTheme(SetupWizardUtils.getTheme(this, getIntent()));
            setTheme(com.android.settings.R.style.SettingsPreferenceTheme_SetupWizard);
            ThemeHelper.trySetDynamicColor(this);
        }
        if (isToolbarEnabled() && !isAnySetupWizard) {
            super.setContentView(com.android.settings.R.layout.collapsing_toolbar_base_layout);
            this.mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(com.android.settings.R.id.collapsing_toolbar);
            this.mAppBarLayout = (AppBarLayout) findViewById(com.android.settings.R.id.app_bar);
            CollapsingToolbarLayout collapsingToolbarLayout = this.mCollapsingToolbarLayout;
            if (collapsingToolbarLayout != null) {
                collapsingToolbarLayout.setLineSpacingMultiplier(1.1f);
                this.mCollapsingToolbarLayout.setHyphenationFrequency(3);
                this.mCollapsingToolbarLayout.setStaticLayoutBuilderConfigurer(new CollapsingToolbarLayout.StaticLayoutBuilderConfigurer() { // from class: com.android.settings.core.SettingsBaseActivity$$ExternalSyntheticLambda0
                    @Override // com.google.android.material.internal.StaticLayoutBuilderConfigurer
                    public final void configure(StaticLayout.Builder builder) {
                        SettingsBaseActivity.lambda$onCreate$0(builder);
                    }
                });
            }
            disableCollapsingToolbarLayoutScrollingBehavior();
        } else {
            super.setContentView(com.android.settings.R.layout.settings_base_layout);
        }
        Toolbar toolbar = (Toolbar) findViewById(com.android.settings.R.id.action_bar);
        if (!isToolbarEnabled() || isAnySetupWizard) {
            toolbar.setVisibility(8);
        } else {
            setActionBar(toolbar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreate$0(StaticLayout.Builder builder) {
        builder.setLineBreakConfig(new LineBreakConfig.Builder().setLineBreakWordStyle(1).build());
    }

    @Override // android.app.Activity
    public void setActionBar(Toolbar toolbar) {
        super.setActionBar(toolbar);
        this.mToolbar = toolbar;
    }

    @Override // android.app.Activity
    public boolean onNavigateUp() {
        if (super.onNavigateUp()) {
            return true;
        }
        finishAfterTransition();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        int transitionType = getTransitionType(intent);
        super.startActivityForResult(intent, i, bundle);
        if (transitionType == 1) {
            overridePendingTransition(com.android.settings.R.anim.sud_slide_next_in, com.android.settings.R.anim.sud_slide_next_out);
        } else if (transitionType == 2) {
            overridePendingTransition(17432576, com.android.settings.R.anim.sud_stay);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        if (getTransitionType(getIntent()) == 2) {
            overridePendingTransition(com.android.settings.R.anim.sud_stay, 17432577);
        }
        super.onPause();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i) {
        ViewGroup viewGroup = (ViewGroup) findViewById(com.android.settings.R.id.content_frame);
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        LayoutInflater.from(this).inflate(i, viewGroup);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        ((ViewGroup) findViewById(com.android.settings.R.id.content_frame)).addView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ((ViewGroup) findViewById(com.android.settings.R.id.content_frame)).addView(view, layoutParams);
    }

    @Override // android.app.Activity
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        CollapsingToolbarLayout collapsingToolbarLayout = this.mCollapsingToolbarLayout;
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(charSequence);
        }
    }

    @Override // android.app.Activity
    public void setTitle(int i) {
        super.setTitle(getText(i));
        CollapsingToolbarLayout collapsingToolbarLayout = this.mCollapsingToolbarLayout;
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(getText(i));
        }
    }

    private boolean isLockTaskModePinned() {
        return ((ActivityManager) getApplicationContext().getSystemService(ActivityManager.class)).getLockTaskModeState() == 2;
    }

    private boolean isSettingsRunOnTop() {
        return TextUtils.equals(getPackageName(), ((ActivityManager) getApplicationContext().getSystemService(ActivityManager.class)).getRunningTasks(1).get(0).baseActivity.getPackageName());
    }

    public boolean setTileEnabled(ComponentName componentName, boolean z) {
        PackageManager packageManager = getPackageManager();
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if ((componentEnabledSetting == 1) != z || componentEnabledSetting == 0) {
            if (z) {
                this.mCategoryMixin.removeFromDenylist(componentName);
            } else {
                this.mCategoryMixin.addToDenylist(componentName);
            }
            packageManager.setComponentEnabledSetting(componentName, z ? 1 : 2, 1);
            return true;
        }
        return false;
    }

    private void disableCollapsingToolbarLayoutScrollingBehavior() {
        AppBarLayout appBarLayout = this.mAppBarLayout;
        if (appBarLayout == null) {
            return;
        }
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() { // from class: com.android.settings.core.SettingsBaseActivity.1
            @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior.BaseDragCallback
            public boolean canDrag(AppBarLayout appBarLayout2) {
                return false;
            }
        });
        ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).setBehavior(behavior);
    }

    private int getTransitionType(Intent intent) {
        if (intent == null) {
            return -1;
        }
        return intent.getIntExtra("page_transition_type", -1);
    }
}
