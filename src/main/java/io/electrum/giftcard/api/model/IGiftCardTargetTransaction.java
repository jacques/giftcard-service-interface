package io.electrum.giftcard.api.model;

public interface IGiftCardTargetTransaction extends IGiftCardTransaction{
    Card getTargetCard();
    void setTargetCard(Card targetCard);
    Product getProduct();
    void setProduct(Product product);
}
