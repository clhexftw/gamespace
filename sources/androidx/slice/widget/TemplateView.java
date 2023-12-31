package androidx.slice.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.SliceItem;
import androidx.slice.core.SliceAction;
import androidx.slice.widget.SliceView;
import androidx.slice.widget.SliceViewPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public class TemplateView extends SliceChildView implements SliceViewPolicy.PolicyChangeListener {
    private SliceAdapter mAdapter;
    private List<SliceContent> mDisplayedItems;
    private int mDisplayedItemsHeight;
    private final View mForeground;
    private int mHiddenItemCount;
    private ListContent mListContent;
    private int[] mLoc;
    private SliceView mParent;
    private final RecyclerView mRecyclerView;

    public TemplateView(Context context) {
        super(context);
        this.mDisplayedItems = new ArrayList();
        this.mDisplayedItemsHeight = 0;
        this.mLoc = new int[2];
        RecyclerView recyclerView = new RecyclerView(getContext());
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(new SliceAdapter(context));
        SliceAdapter sliceAdapter = new SliceAdapter(context);
        this.mAdapter = sliceAdapter;
        recyclerView.setAdapter(sliceAdapter);
        addView(recyclerView);
        View view = new View(getContext());
        this.mForeground = view;
        view.setBackground(SliceViewUtil.getDrawable(getContext(), 16843534));
        addView(view);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        view.setLayoutParams(layoutParams);
    }

    public void setAdapter(SliceAdapter sliceAdapter) {
        this.mAdapter = sliceAdapter;
        this.mRecyclerView.setAdapter(sliceAdapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        SliceView sliceView = (SliceView) getParent();
        this.mParent = sliceView;
        this.mAdapter.setParents(sliceView, this);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        if (!this.mViewPolicy.isScrollable() && this.mDisplayedItems.size() > 0 && this.mDisplayedItemsHeight != size) {
            updateDisplayedItems(size);
        }
        super.onMeasure(i, i2);
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setInsets(int i, int i2, int i3, int i4) {
        super.setInsets(i, i2, i3, i4);
        this.mAdapter.setInsets(i, i2, i3, i4);
    }

    public void onForegroundActivated(MotionEvent motionEvent) {
        SliceView sliceView = this.mParent;
        if (sliceView != null && !sliceView.isSliceViewClickable()) {
            this.mForeground.setPressed(false);
            return;
        }
        this.mForeground.getLocationOnScreen(this.mLoc);
        this.mForeground.getBackground().setHotspot((int) (motionEvent.getRawX() - this.mLoc[0]), (int) (motionEvent.getRawY() - this.mLoc[1]));
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mForeground.setPressed(true);
        } else if (actionMasked == 3 || actionMasked == 1 || actionMasked == 2) {
            this.mForeground.setPressed(false);
        }
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setPolicy(SliceViewPolicy sliceViewPolicy) {
        super.setPolicy(sliceViewPolicy);
        this.mAdapter.setPolicy(sliceViewPolicy);
        sliceViewPolicy.setListener(this);
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setActionLoading(SliceItem sliceItem) {
        this.mAdapter.onSliceActionLoading(sliceItem, 0);
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setLoadingActions(Set<SliceItem> set) {
        this.mAdapter.setLoadingActions(set);
    }

    @Override // androidx.slice.widget.SliceChildView
    public Set<SliceItem> getLoadingActions() {
        return this.mAdapter.getLoadingActions();
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setTint(int i) {
        super.setTint(i);
        updateDisplayedItems(getMeasuredHeight());
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setSliceActionListener(SliceView.OnSliceActionListener onSliceActionListener) {
        this.mObserver = onSliceActionListener;
        SliceAdapter sliceAdapter = this.mAdapter;
        if (sliceAdapter != null) {
            sliceAdapter.setSliceObserver(onSliceActionListener);
        }
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setSliceActions(List<SliceAction> list) {
        this.mAdapter.setSliceActions(list);
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setSliceContent(ListContent listContent) {
        this.mListContent = listContent;
        updateDisplayedItems(listContent.getHeight(this.mSliceStyle, this.mViewPolicy));
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setStyle(SliceStyle sliceStyle, RowStyle rowStyle) {
        super.setStyle(sliceStyle, rowStyle);
        this.mAdapter.setStyle(sliceStyle);
        applyRowStyle(rowStyle);
    }

    private void applyRowStyle(RowStyle rowStyle) {
        if (rowStyle.getDisableRecyclerViewItemAnimator()) {
            this.mRecyclerView.setItemAnimator(null);
        }
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setShowLastUpdated(boolean z) {
        super.setShowLastUpdated(z);
        this.mAdapter.setShowLastUpdated(z);
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setLastUpdated(long j) {
        super.setLastUpdated(j);
        this.mAdapter.setLastUpdated(j);
    }

    @Override // androidx.slice.widget.SliceChildView
    public void setAllowTwoLines(boolean z) {
        this.mAdapter.setAllowTwoLines(z);
    }

    private void updateDisplayedItems(int i) {
        ListContent listContent = this.mListContent;
        if (listContent == null || !listContent.isValid()) {
            resetView();
            return;
        }
        DisplayedListItems rowItems = this.mListContent.getRowItems(i, this.mSliceStyle, this.mViewPolicy);
        this.mDisplayedItems = rowItems.getDisplayedItems();
        this.mHiddenItemCount = rowItems.getHiddenItemCount();
        this.mDisplayedItemsHeight = ListContent.getListHeight(this.mDisplayedItems, this.mSliceStyle, this.mViewPolicy);
        this.mAdapter.setSliceItems(this.mDisplayedItems, this.mTintColor, this.mViewPolicy.getMode());
        updateOverscroll();
    }

    private void updateOverscroll() {
        int i = 1;
        this.mRecyclerView.setOverScrollMode((this.mViewPolicy.isScrollable() && (this.mDisplayedItemsHeight > getMeasuredHeight())) ? 2 : 2);
    }

    @Override // androidx.slice.widget.SliceChildView
    public void resetView() {
        this.mDisplayedItemsHeight = 0;
        this.mDisplayedItems.clear();
        this.mAdapter.setSliceItems(null, -1, getMode());
        this.mListContent = null;
    }

    @Override // androidx.slice.widget.SliceViewPolicy.PolicyChangeListener
    public void onScrollingChanged(boolean z) {
        this.mRecyclerView.setNestedScrollingEnabled(z);
        ListContent listContent = this.mListContent;
        if (listContent != null) {
            updateDisplayedItems(listContent.getHeight(this.mSliceStyle, this.mViewPolicy));
        }
    }

    @Override // androidx.slice.widget.SliceViewPolicy.PolicyChangeListener
    public void onMaxHeightChanged(int i) {
        ListContent listContent = this.mListContent;
        if (listContent != null) {
            updateDisplayedItems(listContent.getHeight(this.mSliceStyle, this.mViewPolicy));
        }
    }

    @Override // androidx.slice.widget.SliceViewPolicy.PolicyChangeListener
    public void onMaxSmallChanged(int i) {
        SliceAdapter sliceAdapter = this.mAdapter;
        if (sliceAdapter != null) {
            sliceAdapter.notifyHeaderChanged();
        }
    }

    @Override // androidx.slice.widget.SliceViewPolicy.PolicyChangeListener
    public void onModeChanged(int i) {
        ListContent listContent = this.mListContent;
        if (listContent != null) {
            updateDisplayedItems(listContent.getHeight(this.mSliceStyle, this.mViewPolicy));
        }
    }

    @Override // androidx.slice.widget.SliceChildView
    public int getHiddenItemCount() {
        return this.mHiddenItemCount;
    }
}
