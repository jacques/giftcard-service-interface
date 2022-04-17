package io.electrum.giftcard.api.model;

public interface IGiftCardExtTransaction extends IGiftCardTransaction{
    GiftcardAmounts getAmounts();
    void setAmounts(GiftcardAmounts amounts);
    Product getProduct();
    void setProduct(Product product);
}
