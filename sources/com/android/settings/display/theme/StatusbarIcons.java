package com.android.settings.display.theme;

import android.content.Context;
import android.content.om.OverlayInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.nameless.ThemeUtils;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.applications.RecentAppOpsAccess;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
/* loaded from: classes.dex */
public class StatusbarIcons extends SettingsPreferenceFragment {
    private String mCategory = "android.theme.customization.icon_pack.android";
    private List<String> mPkgs;
    private RecyclerView mRecyclerView;
    private ThemeUtils mThemeUtils;
    Map<String, String> overlayMap;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    public StatusbarIcons() {
        HashMap hashMap = new HashMap();
        this.overlayMap = hashMap;
        hashMap.put("com.android.settings", "android.theme.customization.icon_pack.settings");
        this.overlayMap.put("com.android.systemui", "android.theme.customization.icon_pack.systemui");
        this.overlayMap.put("com.google.android.apps.nexuslauncher", "android.theme.customization.icon_pack.launcher");
        this.overlayMap.put("com.google.android.apps.wallpaper", "android.theme.customization.icon_pack.themepicker");
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(R.string.theme_customization_icon_pack_title);
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
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        this.mRecyclerView.setAdapter(new Adapter(getActivity()));
        return inflate;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
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
            return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.icon_option, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
            final String str = (String) StatusbarIcons.this.mPkgs.get(i);
            ImageView imageView = customViewHolder.image1;
            imageView.setBackgroundDrawable(StatusbarIcons.this.getDrawable(imageView.getContext(), str, "ic_wifi_signal_4"));
            ImageView imageView2 = customViewHolder.image2;
            imageView2.setBackgroundDrawable(StatusbarIcons.this.getDrawable(imageView2.getContext(), str, "ic_signal_cellular_4_4_bar"));
            ImageView imageView3 = customViewHolder.image3;
            imageView3.setBackgroundDrawable(StatusbarIcons.this.getDrawable(imageView3.getContext(), str, "ic_qs_airplane"));
            ImageView imageView4 = customViewHolder.image4;
            imageView4.setBackgroundDrawable(StatusbarIcons.this.getDrawable(imageView4.getContext(), str, "ic_qs_flashlight"));
            String str2 = (String) StatusbarIcons.this.mThemeUtils.getOverlayInfos(StatusbarIcons.this.mCategory).stream().filter(new Predicate() { // from class: com.android.settings.display.theme.StatusbarIcons$Adapter$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean isEnabled;
                    isEnabled = ((OverlayInfo) obj).isEnabled();
                    return isEnabled;
                }
            }).map(new Function() { // from class: com.android.settings.display.theme.StatusbarIcons$Adapter$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String str3;
                    str3 = ((OverlayInfo) obj).packageName;
                    return str3;
                }
            }).findFirst().orElse(RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME);
            customViewHolder.name.setText(RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME.equals(str) ? "Default" : StatusbarIcons.this.getLabel(customViewHolder.name.getContext(), str));
            if (str2.equals(str)) {
                this.mAppliedPkg = str;
                if (this.mSelectedPkg == null) {
                    this.mSelectedPkg = str;
                }
            }
            customViewHolder.itemView.setActivated(str == this.mSelectedPkg);
            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.display.theme.StatusbarIcons.Adapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Adapter adapter = Adapter.this;
                    adapter.updateActivatedStatus(adapter.mSelectedPkg, false);
                    Adapter.this.updateActivatedStatus(str, true);
                    Adapter adapter2 = Adapter.this;
                    adapter2.mSelectedPkg = str;
                    StatusbarIcons.this.enableOverlays(i);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return StatusbarIcons.this.mPkgs.size();
        }

        /* loaded from: classes.dex */
        public class CustomViewHolder extends RecyclerView.ViewHolder {
            ImageView image1;
            ImageView image2;
            ImageView image3;
            ImageView image4;
            TextView name;

            public CustomViewHolder(View view) {
                super(view);
                this.name = (TextView) view.findViewById(R.id.option_label);
                this.image1 = (ImageView) view.findViewById(R.id.image1);
                this.image2 = (ImageView) view.findViewById(R.id.image2);
                this.image3 = (ImageView) view.findViewById(R.id.image3);
                this.image4 = (ImageView) view.findViewById(R.id.image4);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateActivatedStatus(String str, boolean z) {
            RecyclerView.ViewHolder findViewHolderForAdapterPosition;
            View view;
            int indexOf = StatusbarIcons.this.mPkgs.indexOf(str);
            if (indexOf < 0 || (findViewHolderForAdapterPosition = StatusbarIcons.this.mRecyclerView.findViewHolderForAdapterPosition(indexOf)) == null || (view = findViewHolderForAdapterPosition.itemView) == null) {
                return;
            }
            view.setActivated(z);
        }
    }

    public Drawable getDrawable(Context context, String str, String str2) {
        try {
            Resources system = str.equals(RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME) ? Resources.getSystem() : context.getPackageManager().getResourcesForApplication(str);
            int identifier = system.getIdentifier(str2, "drawable", str);
            if (identifier == 0) {
                return Resources.getSystem().getDrawable(Resources.getSystem().getIdentifier(str2, "drawable", RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME));
            }
            return system.getDrawable(identifier);
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

    public void enableOverlays(int i) {
        this.mThemeUtils.setOverlayEnabled(this.mCategory, this.mPkgs.get(i));
        String str = RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME.equals(this.mPkgs.get(i)) ? "" : this.mPkgs.get(i).split("\\.")[4];
        for (Map.Entry<String, String> entry : this.overlayMap.entrySet()) {
            enableOverlay(entry.getValue(), entry.getKey(), str);
        }
    }

    public void enableOverlay(String str, String str2, String str3) {
        if (str3.isEmpty()) {
            this.mThemeUtils.setOverlayEnabled(str, RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME);
            return;
        }
        for (String str4 : this.mThemeUtils.getOverlayPackagesForCategory(str, str2)) {
            if (str4.contains(str3)) {
                this.mThemeUtils.setOverlayEnabled(str, str4);
            }
        }
    }
}
