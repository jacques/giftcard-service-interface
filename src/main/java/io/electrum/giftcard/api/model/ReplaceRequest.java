package io.electrum.giftcard.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.electrum.vas.Utils;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Information about the replace request to switch an old card with a new card (Transfer funds out of and void old card).
 */
@ApiModel(description = "Information about the replace request to switch an old card with a new card (Transfer funds out of and void old card).")
public class ReplaceRequest extends Transaction implements IGiftCardTargetTransaction {

   private Card oldCard = null;
   private Card newCard = null;
   private PosInfo posInfo = null;
   private Product product = null;

   public ReplaceRequest oldCard(Card oldCard) {
      this.oldCard = oldCard;
      return this;
   }

   /**
    * Information about the gift card that is being replaced.
    *
    * @return oldCard
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card that is being replaced.")
   @JsonProperty("oldCard")
   @NotNull
   public Card getOldCard() {
      return oldCard;
   }

   public void setOldCard(Card oldCard) {
      this.oldCard = oldCard;
   }

   public ReplaceRequest newCard(Card newCard) {
      this.newCard = newCard;
      return this;
   }

   /**
    * Information about the new gift card that will replace the old card.
    *
    * @return newCard
    **/
   @ApiModelProperty(required = true, value = "Information about the new gift card that will replace the old card.")
   @JsonProperty("newCard")
   @NotNull
   public Card getNewCard() {
      return newCard;
   }

   public void setNewCard(Card newCard) {
      this.newCard = newCard;
   }

   public ReplaceRequest posInfo(PosInfo posInfo) {
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

   public ReplaceRequest product(Product product) {
      this.product = product;
      return this;
   }

   /**
    * Information about the gift card product being used for the gift card replace.
    *
    * @return product
    **/
   @ApiModelProperty(value = "Information about the gift card product being used for the gift card replace.")
   @JsonProperty("product")
   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class ReplaceRequest {\n");
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
      sb.append("    tranType: ").append(Utils.toIndentedString(tranType)).append("\n");
      sb.append("    srcAccType: ").append(Utils.toIndentedString(srcAccType)).append("\n");
      sb.append("    destAccType: ").append(Utils.toIndentedString(destAccType)).append("\n");
      sb.append("}");
      return sb.toString();
   }

   @Override
   public Card getTargetCard() {
      return getNewCard();
   }

   @Override
   public void setTargetCard(Card targetCard) {
      setNewCard(targetCard);
   }
}
