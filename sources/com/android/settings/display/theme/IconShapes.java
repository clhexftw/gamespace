package com.android.settings.display.theme;

import android.content.Context;
import android.content.om.OverlayInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.nameless.ThemeUtils;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.Utils;
import com.android.settingslib.applications.RecentAppOpsAccess;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
/* loaded from: classes.dex */
public class IconShapes extends SettingsPreferenceFragment {
    private String mCategory = "android.theme.customization.adaptive_icon_shape";
    private List<String> mPkgs;
    private RecyclerView mRecyclerView;
    private ThemeUtils mThemeUtils;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(R.string.theme_customization_icon_shape_title);
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

        public Adapter(Context context) {
            this.context = context;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_option, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
            String str = (String) IconShapes.this.mPkgs.get(i);
            customViewHolder.image.setBackgroundDrawable(IconShapes.this.mThemeUtils.createShapeDrawable(str));
            String str2 = (String) IconShapes.this.mThemeUtils.getOverlayInfos(IconShapes.this.mCategory).stream().filter(new Predicate() { // from class: com.android.settings.display.theme.IconShapes$Adapter$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean isEnabled;
                    isEnabled = ((OverlayInfo) obj).isEnabled();
                    return isEnabled;
                }
            }).map(new Function() { // from class: com.android.settings.display.theme.IconShapes$Adapter$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String str3;
                    str3 = ((OverlayInfo) obj).packageName;
                    return str3;
                }
            }).findFirst().orElse(RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME);
            customViewHolder.name.setText(RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME.equals(str) ? "Default" : IconShapes.this.getLabel(customViewHolder.name.getContext(), str));
            customViewHolder.image.setBackgroundTintList(ColorStateList.valueOf(ColorUtils.setAlphaComponent(Utils.getColorAttrDefaultColor(IconShapes.this.getContext(), 16843829), (str.equals(str2) || (RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME.equals(str2) && RecentAppOpsAccess.ANDROID_SYSTEM_PACKAGE_NAME.equals(str))) ? 170 : 75)));
            customViewHolder.itemView.findViewById(R.id.option_tile).setBackgroundDrawable(null);
            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.display.theme.IconShapes.Adapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    IconShapes.this.enableOverlays(i);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return IconShapes.this.mPkgs.size();
        }

        /* loaded from: classes.dex */
        public class CustomViewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView name;

            public CustomViewHolder(View view) {
                super(view);
                this.name = (TextView) view.findViewById(R.id.option_label);
                this.image = (ImageView) view.findViewById(R.id.option_thumbnail);
            }
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
    }
}
