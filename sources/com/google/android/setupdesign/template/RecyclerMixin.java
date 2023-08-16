package com.google.android.setupdesign.template;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.TypedValue;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.DividerItemDecoration;
import com.google.android.setupdesign.R$attr;
import com.google.android.setupdesign.util.DrawableLayoutDirectionHelper;
import com.google.android.setupdesign.util.PartnerStyleHelper;
import com.google.android.setupdesign.view.HeaderRecyclerView;
/* loaded from: classes.dex */
public class RecyclerMixin implements Mixin {
    private Drawable defaultDivider;
    private Drawable divider;
    private DividerItemDecoration dividerDecoration;
    private int dividerInsetEnd;
    private int dividerInsetStart;
    private View header;
    private boolean isDividerDisplay;
    private final RecyclerView recyclerView;
    private final TemplateLayout templateLayout;

    public RecyclerMixin(TemplateLayout templateLayout, RecyclerView recyclerView) {
        this.isDividerDisplay = true;
        this.templateLayout = templateLayout;
        this.dividerDecoration = new DividerItemDecoration(templateLayout.getContext());
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(templateLayout.getContext()));
        if (recyclerView instanceof HeaderRecyclerView) {
            this.header = ((HeaderRecyclerView) recyclerView).getHeader();
        }
        boolean isShowItemsDivider = isShowItemsDivider(templateLayout.getContext());
        this.isDividerDisplay = isShowItemsDivider;
        if (isShowItemsDivider) {
            recyclerView.addItemDecoration(this.dividerDecoration);
        }
    }

    private boolean isShowItemsDivider(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.sudDividerShown, typedValue, true);
        boolean z = typedValue.data != 0;
        if (PartnerStyleHelper.shouldApplyPartnerResource(this.templateLayout)) {
            PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(this.recyclerView.getContext());
            PartnerConfig partnerConfig = PartnerConfig.CONFIG_ITEMS_DIVIDER_SHOWN;
            if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
                return PartnerConfigHelper.get(this.recyclerView.getContext()).getBoolean(this.recyclerView.getContext(), partnerConfig, z);
            }
        }
        return z;
    }

    public View getHeader() {
        return this.header;
    }

    public void onLayout() {
        if (this.divider == null) {
            updateDivider();
        }
    }

    private void updateDivider() {
        if (this.templateLayout.isLayoutDirectionResolved()) {
            if (this.defaultDivider == null) {
                this.defaultDivider = this.dividerDecoration.getDivider();
            }
            InsetDrawable createRelativeInsetDrawable = DrawableLayoutDirectionHelper.createRelativeInsetDrawable(this.defaultDivider, this.dividerInsetStart, 0, this.dividerInsetEnd, 0, this.templateLayout);
            this.divider = createRelativeInsetDrawable;
            this.dividerDecoration.setDivider(createRelativeInsetDrawable);
        }
    }
}
