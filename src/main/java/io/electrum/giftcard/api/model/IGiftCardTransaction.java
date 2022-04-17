package io.electrum.giftcard.api.model;

import io.electrum.vas.model.VasMessage;

public interface IGiftCardTransaction extends VasMessage{
    Card getCard();
    void setCard(Card card);
    PosInfo getPosInfo();
    void setPosInfo(PosInfo posInfo);
}
