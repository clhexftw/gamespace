package com.android.settings.display.theme;

import android.content.Context;
import android.content.om.OverlayInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.nameless.ThemeUtils;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.applications.RecentAppOpsAccess;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.function.Predicate;
/* loaded from: classes.dex */
public class LockClockFonts extends SettingsPreferenceFragment {
    private List<String> mPkgs;
    private RecyclerView mRecyclerView;
    private ThemeUtils mThemeUtils;
    private String mCategory = "android.theme.customization.lockscreen_clock_font";
    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private Handler mHandler = new Handler();
    private final AtomicBoolean mApplyingOverlays = new AtomicBoolean(false);

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(R.string.theme_customization_lock_clock_title);
        this.mHandler = new Handler();
        ThemeUtils themeUtils = new ThemeUtils(getActivity());
        this.mThemeUtils = themeUtils;
        List<String> overlayPackagesForCategory = themeUtils.getOverlayPackagesForCategory(this.mCategory, RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME);
        this.mPkgs = overlayPackagesForCategory;
        Collections.sort(overlayPackagesForCategory);
    }

    @Override // com.android.settings.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.item_view, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.mRecyclerView.setAdapter(new Adapter(getActivity()));
        return inflate;
    }

    /* loaded from: classes.dex */
    public class Adapter extends RecyclerView.Adapter<CustomViewHolder> {
        Context context;
        String mAppliedPkg;
        String mSelectedPkg;

        public Adapter(Context context) {
            this.context = context;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lock_clock_fonts_option, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
            final String str = (String) LockClockFonts.this.mPkgs.get(i);
            LockClockFonts.this.getLabel(customViewHolder.itemView.getContext(), str);
            customViewHolder.title.setTextSize(24.0f);
            TextView textView = customViewHolder.title;
            textView.setTypeface(LockClockFonts.this.getTypeface(textView.getContext(), str));
            customViewHolder.name.setVisibility(8);
            if (((String) LockClockFonts.this.mThemeUtils.getOverlayInfos(LockClockFonts.this.mCategory).stream().filter(new Predicate() { // from class: com.android.settings.display.theme.LockClockFonts$Adapter$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean isEnabled;
                    isEnabled = ((OverlayInfo) obj).isEnabled();
                    return isEnabled;
                }
            }).map(new Function() { // from class: com.android.settings.display.theme.LockClockFonts$Adapter$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String str2;
                    str2 = ((OverlayInfo) obj).packageName;
                    return str2;
                }
            }).findFirst().orElse(RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME)).equals(str)) {
                this.mAppliedPkg = str;
                if (this.mSelectedPkg == null) {
                    this.mSelectedPkg = str;
                }
            }
            customViewHolder.itemView.setActivated(str == this.mSelectedPkg);
            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.display.theme.LockClockFonts.Adapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (LockClockFonts.this.mApplyingOverlays.get()) {
                        return;
                    }
                    Adapter adapter = Adapter.this;
                    adapter.updateActivatedStatus(adapter.mSelectedPkg, false);
                    Adapter.this.updateActivatedStatus(str, true);
                    Adapter adapter2 = Adapter.this;
                    adapter2.mSelectedPkg = str;
                    LockClockFonts.this.enableOverlays(i);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return LockClockFonts.this.mPkgs.size();
        }

        /* loaded from: classes.dex */
        public class CustomViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView title;

            public CustomViewHolder(View view) {
                super(view);
                this.title = (TextView) view.findViewById(R.id.option_title);
                this.name = (TextView) view.findViewById(R.id.option_label);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateActivatedStatus(String str, boolean z) {
            RecyclerView.ViewHolder findViewHolderForAdapterPosition;
            View view;
            int indexOf = LockClockFonts.this.mPkgs.indexOf(str);
            if (indexOf < 0 || (findViewHolderForAdapterPosition = LockClockFonts.this.mRecyclerView.findViewHolderForAdapterPosition(indexOf)) == null || (view = findViewHolderForAdapterPosition.itemView) == null) {
                return;
            }
            view.setActivated(z);
        }
    }

    public Typeface getTypeface(Context context, String str) {
        try {
            Resources system = str.equals(RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME) ? Resources.getSystem() : context.getPackageManager().getResourcesForApplication(str);
            return Typeface.create(system.getString(system.getIdentifier("config_clockFontFamily", "string", str)), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getLabel(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(str, 0).loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return str;
        }
    }

    public void enableOverlays(final int i) {
        this.mApplyingOverlays.set(true);
        this.mExecutor.execute(new Runnable() { // from class: com.android.settings.display.theme.LockClockFonts$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LockClockFonts.this.lambda$enableOverlays$1(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$enableOverlays$1(int i) {
        this.mThemeUtils.setOverlayEnabled(this.mCategory, this.mPkgs.get(i));
        this.mHandler.post(new Runnable() { // from class: com.android.settings.display.theme.LockClockFonts$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                LockClockFonts.this.lambda$enableOverlays$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$enableOverlays$0() {
        this.mApplyingOverlays.set(false);
    }
}
