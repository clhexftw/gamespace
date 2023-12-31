package com.android.settings.intelligence;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;
/* loaded from: classes.dex */
public final class ContextualCardProto$ContextualCardList extends GeneratedMessageLite<ContextualCardProto$ContextualCardList, Builder> implements MessageLiteOrBuilder {
    public static final int CARD_FIELD_NUMBER = 1;
    private static final ContextualCardProto$ContextualCardList DEFAULT_INSTANCE;
    private static volatile Parser<ContextualCardProto$ContextualCardList> PARSER;
    private Internal.ProtobufList<ContextualCardProto$ContextualCard> card_ = GeneratedMessageLite.emptyProtobufList();

    private ContextualCardProto$ContextualCardList() {
    }

    public List<ContextualCardProto$ContextualCard> getCardList() {
        return this.card_;
    }

    private void ensureCardIsMutable() {
        if (this.card_.isModifiable()) {
            return;
        }
        this.card_ = GeneratedMessageLite.mutableCopy(this.card_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCard(ContextualCardProto$ContextualCard contextualCardProto$ContextualCard) {
        contextualCardProto$ContextualCard.getClass();
        ensureCardIsMutable();
        this.card_.add(contextualCardProto$ContextualCard);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    /* loaded from: classes.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<ContextualCardProto$ContextualCardList, Builder> implements MessageLiteOrBuilder {
        private Builder() {
            super(ContextualCardProto$ContextualCardList.DEFAULT_INSTANCE);
        }

        public Builder addCard(ContextualCardProto$ContextualCard contextualCardProto$ContextualCard) {
            copyOnWrite();
            ((ContextualCardProto$ContextualCardList) this.instance).addCard(contextualCardProto$ContextualCard);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (ContextualCardProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ContextualCardProto$ContextualCardList();
            case 2:
                return new Builder();
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"card_", ContextualCardProto$ContextualCard.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ContextualCardProto$ContextualCardList> parser = PARSER;
                if (parser == null) {
                    synchronized (ContextualCardProto$ContextualCardList.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        ContextualCardProto$ContextualCardList contextualCardProto$ContextualCardList = new ContextualCardProto$ContextualCardList();
        DEFAULT_INSTANCE = contextualCardProto$ContextualCardList;
        GeneratedMessageLite.registerDefaultInstance(ContextualCardProto$ContextualCardList.class, contextualCardProto$ContextualCardList);
    }
}
