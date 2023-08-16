package com.android.settings.biometrics.fingerprint;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Handler;
import java.time.Clock;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
/* loaded from: classes.dex */
public class MessageDisplayController extends FingerprintManager.EnrollmentCallback {
    private final Clock mClock;
    private final int mCollectTime;
    private final Runnable mDisplayMessageRunnable;
    FingerprintManager.EnrollmentCallback mEnrollmentCallback;
    private final Handler mHandler;
    private final int mHelpMinimumDisplayTime;
    private ProgressMessage mLastProgressMessageDisplayed;
    private boolean mMustDisplayProgress;
    private final boolean mPrioritizeAcquireMessages;
    private final int mProgressMinimumDisplayTime;
    private final boolean mProgressPriorityOverHelp;
    private boolean mWaitingForMessage = false;
    private final Deque<HelpMessage> mHelpMessageList = new ArrayDeque();
    private final Deque<ProgressMessage> mProgressMessageList = new ArrayDeque();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class Message {
        long mTimeStamp;

        abstract void display();

        private Message() {
            this.mTimeStamp = 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class HelpMessage extends Message {
        private final int mHelpMsgId;
        private final CharSequence mHelpString;

        HelpMessage(int i, CharSequence charSequence) {
            super();
            this.mHelpMsgId = i;
            this.mHelpString = charSequence;
            this.mTimeStamp = MessageDisplayController.this.mClock.millis();
        }

        @Override // com.android.settings.biometrics.fingerprint.MessageDisplayController.Message
        void display() {
            MessageDisplayController.this.mEnrollmentCallback.onEnrollmentHelp(this.mHelpMsgId, this.mHelpString);
            MessageDisplayController.this.mHandler.postDelayed(MessageDisplayController.this.mDisplayMessageRunnable, MessageDisplayController.this.mHelpMinimumDisplayTime);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ProgressMessage extends Message {
        private final int mRemaining;

        ProgressMessage(int i) {
            super();
            this.mRemaining = i;
            this.mTimeStamp = MessageDisplayController.this.mClock.millis();
        }

        @Override // com.android.settings.biometrics.fingerprint.MessageDisplayController.Message
        void display() {
            MessageDisplayController.this.mEnrollmentCallback.onEnrollmentProgress(this.mRemaining);
            MessageDisplayController.this.mLastProgressMessageDisplayed = this;
            MessageDisplayController.this.mHandler.postDelayed(MessageDisplayController.this.mDisplayMessageRunnable, MessageDisplayController.this.mProgressMinimumDisplayTime);
        }
    }

    public MessageDisplayController(Handler handler, FingerprintManager.EnrollmentCallback enrollmentCallback, Clock clock, int i, int i2, boolean z, boolean z2, int i3) {
        this.mClock = clock;
        this.mHandler = handler;
        this.mEnrollmentCallback = enrollmentCallback;
        this.mHelpMinimumDisplayTime = i;
        this.mProgressMinimumDisplayTime = i2;
        this.mProgressPriorityOverHelp = z;
        this.mPrioritizeAcquireMessages = z2;
        this.mCollectTime = i3;
        Runnable runnable = new Runnable() { // from class: com.android.settings.biometrics.fingerprint.MessageDisplayController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MessageDisplayController.this.lambda$new$0();
            }
        };
        this.mDisplayMessageRunnable = runnable;
        handler.postDelayed(runnable, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        Message messageToDisplay = getMessageToDisplay(this.mClock.millis());
        if (messageToDisplay != null) {
            messageToDisplay.display();
        } else {
            this.mWaitingForMessage = true;
        }
    }

    public void onEnrollmentHelp(int i, CharSequence charSequence) {
        this.mHelpMessageList.add(new HelpMessage(i, charSequence));
        if (this.mWaitingForMessage) {
            this.mWaitingForMessage = false;
            this.mHandler.postDelayed(this.mDisplayMessageRunnable, this.mCollectTime);
        }
    }

    public void onEnrollmentProgress(int i) {
        this.mProgressMessageList.add(new ProgressMessage(i));
        if (this.mWaitingForMessage) {
            this.mWaitingForMessage = false;
            this.mHandler.postDelayed(this.mDisplayMessageRunnable, this.mCollectTime);
        }
    }

    public void onEnrollmentError(int i, CharSequence charSequence) {
        this.mEnrollmentCallback.onEnrollmentError(i, charSequence);
    }

    private Message getMessageToDisplay(long j) {
        ProgressMessage progressMessageToDisplay = getProgressMessageToDisplay(j);
        if (this.mMustDisplayProgress) {
            this.mMustDisplayProgress = false;
            if (progressMessageToDisplay != null) {
                return progressMessageToDisplay;
            }
            ProgressMessage progressMessage = this.mLastProgressMessageDisplayed;
            if (progressMessage != null) {
                return progressMessage;
            }
        }
        HelpMessage helpMessageToDisplay = getHelpMessageToDisplay(j);
        if (helpMessageToDisplay == null && progressMessageToDisplay == null) {
            return null;
        }
        if ((!this.mProgressPriorityOverHelp || progressMessageToDisplay == null) && helpMessageToDisplay != null) {
            if (progressMessageToDisplay != null) {
                this.mMustDisplayProgress = true;
                this.mLastProgressMessageDisplayed = progressMessageToDisplay;
            }
            return helpMessageToDisplay;
        }
        return progressMessageToDisplay;
    }

    private ProgressMessage getProgressMessageToDisplay(long j) {
        ProgressMessage progressMessage = null;
        while (true) {
            Deque<ProgressMessage> deque = this.mProgressMessageList;
            if (deque == null || deque.isEmpty() || this.mProgressMessageList.peekFirst().mTimeStamp > j) {
                break;
            }
            ProgressMessage pollFirst = this.mProgressMessageList.pollFirst();
            ProgressMessage progressMessage2 = this.mLastProgressMessageDisplayed;
            if (progressMessage2 == null || progressMessage2.mRemaining != pollFirst.mRemaining) {
                progressMessage = pollFirst;
            }
        }
        return progressMessage;
    }

    private HelpMessage getHelpMessageToDisplay(long j) {
        HashMap<CharSequence, Integer> hashMap = new HashMap<>();
        HelpMessage helpMessage = null;
        while (true) {
            Deque<HelpMessage> deque = this.mHelpMessageList;
            if (deque == null || deque.isEmpty() || this.mHelpMessageList.peekFirst().mTimeStamp > j) {
                break;
            }
            helpMessage = this.mHelpMessageList.pollFirst();
            CharSequence charSequence = helpMessage.mHelpString;
            hashMap.put(charSequence, Integer.valueOf(hashMap.getOrDefault(charSequence, 0).intValue() + 1));
        }
        return this.mPrioritizeAcquireMessages ? prioritizeHelpMessageByCount(hashMap) : helpMessage;
    }

    private HelpMessage prioritizeHelpMessageByCount(HashMap<CharSequence, Integer> hashMap) {
        CharSequence charSequence = null;
        int i = 0;
        for (CharSequence charSequence2 : hashMap.keySet()) {
            if (i < hashMap.get(charSequence2).intValue()) {
                i = hashMap.get(charSequence2).intValue();
                charSequence = charSequence2;
            }
        }
        if (charSequence != null) {
            return new HelpMessage(0, charSequence);
        }
        return null;
    }
}
