package com.android.settings.panel;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import androidx.slice.widget.EventInfo;
import androidx.slice.widget.SliceView;
import com.android.settings.R;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.panel.PanelSlicesAdapter;
import com.google.android.setupdesign.DividerItemDecoration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class PanelSlicesAdapter extends RecyclerView.Adapter<SliceRowViewHolder> {
    static final int MAX_NUM_OF_SLICES = 9;
    private final int mMetricsCategory;
    private final PanelFragment mPanelFragment;
    private final List<LiveData<Slice>> mSliceLiveData;

    public PanelSlicesAdapter(PanelFragment panelFragment, Map<Uri, LiveData<Slice>> map, int i) {
        this.mPanelFragment = panelFragment;
        this.mSliceLiveData = new ArrayList(map.values());
        this.mMetricsCategory = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SliceRowViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate;
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            inflate = from.inflate(R.layout.panel_slice_slider_row, viewGroup, false);
        } else {
            inflate = from.inflate(R.layout.panel_slice_row, viewGroup, false);
        }
        return new SliceRowViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SliceRowViewHolder sliceRowViewHolder, int i) {
        sliceRowViewHolder.onBind(this.mSliceLiveData.get(i).getValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return Math.min(this.mSliceLiveData.size(), 9);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mPanelFragment.getPanelViewType();
    }

    List<LiveData<Slice>> getData() {
        return this.mSliceLiveData.subList(0, getItemCount());
    }

    /* loaded from: classes.dex */
    public class SliceRowViewHolder extends RecyclerView.ViewHolder implements DividerItemDecoration.DividedViewHolder {
        final LinearLayout mSliceSliderLayout;
        final SliceView sliceView;

        @Override // com.google.android.setupdesign.DividerItemDecoration.DividedViewHolder
        public boolean isDividerAllowedAbove() {
            return false;
        }

        @Override // com.google.android.setupdesign.DividerItemDecoration.DividedViewHolder
        public boolean isDividerAllowedBelow() {
            return false;
        }

        public SliceRowViewHolder(View view) {
            super(view);
            SliceView sliceView = (SliceView) view.findViewById(R.id.slice_view);
            this.sliceView = sliceView;
            sliceView.setMode(2);
            sliceView.setShowTitleItems(true);
            sliceView.setImportantForAccessibility(2);
            this.mSliceSliderLayout = (LinearLayout) view.findViewById(R.id.slice_slider_layout);
        }

        public void onBind(final Slice slice) {
            if (slice == null || !isValidSlice(slice)) {
                this.sliceView.setVisibility(8);
                return;
            }
            this.sliceView.setSlice(slice);
            this.sliceView.setVisibility(0);
            this.sliceView.setShowActionDividers(true);
            this.sliceView.setOnSliceActionListener(new SliceView.OnSliceActionListener() { // from class: com.android.settings.panel.PanelSlicesAdapter$SliceRowViewHolder$$ExternalSyntheticLambda0
                @Override // androidx.slice.widget.SliceView.OnSliceActionListener
                public final void onSliceAction(EventInfo eventInfo, SliceItem sliceItem) {
                    PanelSlicesAdapter.SliceRowViewHolder.this.lambda$onBind$0(slice, eventInfo, sliceItem);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBind$0(Slice slice, EventInfo eventInfo, SliceItem sliceItem) {
            FeatureFactory.getFactory(this.sliceView.getContext()).getMetricsFeatureProvider().action(0, 1658, PanelSlicesAdapter.this.mMetricsCategory, slice.getUri().getLastPathSegment(), eventInfo.actionType);
        }

        private boolean isValidSlice(Slice slice) {
            if (slice.getHints().contains("error")) {
                return false;
            }
            for (SliceItem sliceItem : slice.getItems()) {
                if (sliceItem.getFormat().equals("slice")) {
                    return true;
                }
            }
            return false;
        }
    }
}
