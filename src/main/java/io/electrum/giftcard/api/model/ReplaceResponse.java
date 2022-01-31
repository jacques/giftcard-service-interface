package io.electrum.giftcard.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.electrum.vas.Utils;
import io.electrum.vas.model.Customer;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Information about the replace request made to switch an old card with a new card.
 */
@ApiModel(description = "Information about the replace request made to switch an old card with a new card.")
public class ReplaceResponse extends Transaction implements IGiftCardTargetTransaction {

   private GiftcardAmounts amounts = null;
   private Card oldCard = null;
   private Card newCard = null;
   private PosInfo posInfo = null;
   private Product product = null;
   private Customer cardHolder = null;

   public ReplaceResponse amounts(GiftcardAmounts amounts) {
      this.amounts = amounts;
      return this;
   }

   /**
    * Indicates the balance amount which now exists in the new card (balance from old card transferred to new card).
    *
    * @return amounts
    **/
   @ApiModelProperty(required = true, value = "Indicates the balance amount which now exists in the new card (balance from old card transferred to new card).")
   @JsonProperty("amounts")
   @NotNull
   public GiftcardAmounts getAmounts() {
      return amounts;
   }

   public void setAmounts(GiftcardAmounts amounts) {
      this.amounts = amounts;
   }

   /**
    * Information about the gift card being replaced.
    *
    * @return oldCard
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card being replaced.")
   @JsonProperty("oldCard")
   @NotNull
   public Card getOldCard() {
      return oldCard;
   }

   public void setOldCard(Card oldCard) {
      this.oldCard = oldCard;
   }

   /**
    * Information about the new gift card that will be used instead of the old replaced card.
    *
    * @return newCard
    **/
   @ApiModelProperty(required = true, value = "Information about the new gift card that will be used instead of the old replaced card.")
   @JsonProperty("newCard")
   @NotNull
   public Card getNewCard() {
      return newCard;
   }

   public void setNewCard(Card newCard) {
      this.newCard = newCard;
   }

   public ReplaceResponse posInfo(PosInfo posInfo) {
      this.posInfo = posInfo;
      return this;
   }

   @Override
   public Card getCard() {
      return getOldCard();
   }

   @Override
   public void setCard(Card card) {
      setOldCard(card);
   }

   /**
    * Information about the POS.
    *
    * @return posInfo
    **/
   @ApiModelProperty(value = "Information about the POS.")
   @JsonProperty("posInfo")
   public PosInfo getPosInfo() {
      return posInfo;
   }

   public void setPosInfo(PosInfo posInfo) {
      this.posInfo = posInfo;
   }

   public ReplaceResponse product(Product product) {
      this.product = product;
      return this;
   }

   /**
    * Information about the gift card product being used for the giftcard replace operation.
    *
    * @return product
    **/
   @ApiModelProperty(value = "Information about the gift card product being used for the giftcard replace operation.")
   @JsonProperty("product")
   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public ReplaceResponse cardHolder(Customer cardHolder) {
      this.cardHolder = cardHolder;
      return this;
   }

   /**
    * Information about the card holder.
    * 
    * @return cardHolder
    **/
   @ApiModelProperty(value = "Information about the card holder.")
   @JsonProperty("cardHolder")
   public Customer getCardHolder() {
      return cardHolder;
   }

   public void setCardHolder(Customer cardHolder) {
      this.cardHolder = cardHolder;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class ReplaceResponse {\n");
      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    originator: ").append(Utils.toIndentedString(originator)).append("\n");
      sb.append("    client: ").append(Utils.toIndentedString(client)).append("\n");
      sb.append("    settlementEntity: ").append(Utils.toIndentedString(settlementEntity)).append("\n");
      sb.append("    receiver: ").append(Utils.toIndentedString(receiver)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    oldCard: ").append(Utils.toIndentedString(oldCard)).append("\n");
      sb.append("    newCard: ").append(Utils.toIndentedString(newCard)).append("\n");
      sb.append("    posInfo: ").append(Utils.toIndentedString(posInfo)).append("\n");
      sb.append("    product: ").append(Utils.toIndentedString(product)).append("\n");
      sb.append("    slipData: ").append(Utils.toIndentedString(slipData)).append("\n");
      sb.append("    tranType: ").append(Utils.toIndentedString(tranType)).append("\n");
      sb.append("    srcAccType: ").append(Utils.toIndentedString(srcAccType)).append("\n");
      sb.append("    destAccType: ").append(Utils.toIndentedString(destAccType)).append("\n");
      sb.append("    customer: ").append(Utils.toIndentedString(cardHolder)).append("\n");
      sb.append("}");
      return sb.toString();
   }

   @Override
   public Card getTargetCard() {
      return getNewCard();
   }

   @Override
   public void setTargetCard(Card targetCard) {
      setNewCard(newCard);
   }
}
