package org.nameless.gamespace.widget.tiles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import dagger.hilt.EntryPoints;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
import org.nameless.gamespace.data.UserGame;
import org.nameless.gamespace.utils.GameModeUtils;
import org.nameless.gamespace.utils.di.ServiceViewEntryPoint;
/* compiled from: GameModeTile.kt */
/* loaded from: classes.dex */
public final class GameModeTile extends BaseTile {
    private int activeMode;
    private final Lazy gameModeUtils$delegate;
    private final List<Integer> modes;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GameModeTile(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ GameModeTile(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GameModeTile(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Lazy lazy;
        List<Integer> listOf;
        Intrinsics.checkNotNullParameter(context, "context");
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<GameModeUtils>() { // from class: org.nameless.gamespace.widget.tiles.GameModeTile$gameModeUtils$2
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
        this.gameModeUtils$delegate = lazy;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{1, 2, 3});
        this.modes = listOf;
        this.activeMode = 1;
    }

    private final GameModeUtils getGameModeUtils() {
        return (GameModeUtils) this.gameModeUtils$delegate.getValue();
    }

    private final void setActiveMode(int i) {
        this.activeMode = i;
        TextView summary = getSummary();
        if (summary != null) {
            GameModeUtils.Companion companion = GameModeUtils.Companion;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            summary.setText(companion.describeGameMode(context, i));
        }
        setSelected(i != 1);
        getGameModeUtils().setActiveGameMode(getSystemSettings(), i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextView title = getTitle();
        if (title != null) {
            title.setText(getContext().getString(R.string.game_mode_title));
        }
        UserGame activeGame = getGameModeUtils().getActiveGame();
        setActiveMode(activeGame != null ? activeGame.getMode() : 1);
        ImageView icon = getIcon();
        if (icon != null) {
            icon.setImageResource(R.drawable.ic_speed);
        }
    }

    @Override // org.nameless.gamespace.widget.tiles.BaseTile, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int indexOf = this.modes.indexOf(Integer.valueOf(this.activeMode));
        List<Integer> list = this.modes;
        setActiveMode(list.get(indexOf == list.size() + (-1) ? 0 : indexOf + 1).intValue());
    }
}
