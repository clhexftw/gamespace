package org.nameless.gamespace.gamebar;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DraggableTouchListener.kt */
/* loaded from: classes.dex */
public final class DraggableTouchListener implements View.OnTouchListener {
    private final Function0<Point> initialPosition;
    private int initialX;
    private int initialY;
    private final int longClickInterval;
    private boolean longClickPerformed;
    private boolean moving;
    private final Function0<Unit> onDragComplete;
    private int pointerStartX;
    private int pointerStartY;
    private final Function2<Integer, Integer, Unit> positionListener;
    private Timer timer;
    private final int touchSlop;
    private final View view;

    /* JADX WARN: Multi-variable type inference failed */
    public DraggableTouchListener(Context context, View view, Function0<? extends Point> initialPosition, Function2<? super Integer, ? super Integer, Unit> positionListener, Function0<Unit> onDragComplete) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(initialPosition, "initialPosition");
        Intrinsics.checkNotNullParameter(positionListener, "positionListener");
        Intrinsics.checkNotNullParameter(onDragComplete, "onDragComplete");
        this.view = view;
        this.initialPosition = initialPosition;
        this.positionListener = positionListener;
        this.onDragComplete = onDragComplete;
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.longClickInterval = ViewConfiguration.getLongPressTimeout();
        view.setOnTouchListener(this);
    }

    private final void scheduleLongClickTimer() {
        if (this.timer == null) {
            Timer timer = new Timer();
            this.timer = timer;
            timer.schedule(new TimerTask() { // from class: org.nameless.gamespace.gamebar.DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    boolean z;
                    boolean z2;
                    View view;
                    z = DraggableTouchListener.this.moving;
                    if (!z) {
                        z2 = DraggableTouchListener.this.longClickPerformed;
                        if (!z2) {
                            view = DraggableTouchListener.this.view;
                            final DraggableTouchListener draggableTouchListener = DraggableTouchListener.this;
                            view.post(new Runnable() { // from class: org.nameless.gamespace.gamebar.DraggableTouchListener$scheduleLongClickTimer$1$1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    View view2;
                                    view2 = DraggableTouchListener.this.view;
                                    view2.performLongClick();
                                }
                            });
                            DraggableTouchListener.this.longClickPerformed = true;
                        }
                    }
                    DraggableTouchListener.this.cancelLongClickTimer();
                }
            }, this.longClickInterval);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelLongClickTimer() {
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
        this.timer = null;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        int action = motionEvent.getAction();
        if (action == 0) {
            this.pointerStartX = (int) motionEvent.getRawX();
            this.pointerStartY = (int) motionEvent.getRawY();
            Point invoke = this.initialPosition.invoke();
            this.initialX = invoke.x;
            this.initialY = invoke.y;
            this.moving = false;
            this.longClickPerformed = false;
            scheduleLongClickTimer();
        } else if (action != 1) {
            if (action == 2 && !this.longClickPerformed) {
                float rawX = motionEvent.getRawX() - this.pointerStartX;
                float rawY = motionEvent.getRawY() - this.pointerStartY;
                if (this.moving || ((float) Math.hypot(rawX, rawY)) > this.touchSlop) {
                    cancelLongClickTimer();
                    this.positionListener.invoke(Integer.valueOf(this.initialX + ((int) rawX)), Integer.valueOf(this.initialY + ((int) rawY)));
                    this.moving = true;
                }
            }
        } else {
            cancelLongClickTimer();
            if (!this.moving && !this.longClickPerformed) {
                view.performClick();
            }
            if (this.moving && !this.longClickPerformed) {
                this.onDragComplete.invoke();
            }
        }
        return true;
    }
}
