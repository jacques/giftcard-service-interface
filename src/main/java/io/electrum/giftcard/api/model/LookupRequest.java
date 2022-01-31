package io.electrum.giftcard.api.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.Customer;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about a gift card to be retrieved.
 */ 
@ApiModel(description = "Information about a gift card to be retrieved.")
public class LookupRequest extends Transaction implements IGiftCardTransaction{

   private Card card = null;
   private PosInfo posInfo = null;
   private Customer cardHolder = null;

   public LookupRequest card(Card card) {
      this.card = card;
      return this;
   }

   /**
    * Information about the gift card being retrieved.
    * 
    * @return card
    **/
   @ApiModelProperty(value = "Information about the gift card being retrieved. Either this field or the cardHolder field should be set.")
   @JsonProperty("card")
   public Card getCard() {
      return card;
   }

   public void setCard(Card card) {
      this.card = card;
   }

   public LookupRequest posInfo(PosInfo posInfo) {
      this.posInfo = posInfo;
      return this;
   }

   /**
    * Information about the POS which captured the card details.
    * 
    * @return posInfo
    **/
   @ApiModelProperty(value = "Information about the POS which captured the card details.")
   @JsonProperty("posInfo")
   public PosInfo getPosInfo() {
      return posInfo;
   }

   public void setPosInfo(PosInfo posInfo) {
      this.posInfo = posInfo;
   }

   /**
    * Information about the card holder
    *
    * @return cardHolder
    **/
   @ApiModelProperty(value = "Information about the card holder. Either this field or the card field should be set.")
   @JsonProperty("cardHolder")
   public LookupRequest cardHolder(Customer cardHolder) {
      this.cardHolder = cardHolder;
      return this;
   }

   public void setCardHolder(Customer cardHolder) {
      this.cardHolder = cardHolder;
   }

   public Customer getCardHolder() {
      return cardHolder;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class LookupRequest {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    originator: ").append(Utils.toIndentedString(originator)).append("\n");
      sb.append("    client: ").append(Utils.toIndentedString(client)).append("\n");
      sb.append("    settlementEntity: ").append(Utils.toIndentedString(settlementEntity)).append("\n");
      sb.append("    receiver: ").append(Utils.toIndentedString(receiver)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    card: ").append(Utils.toIndentedString(card)).append("\n");
      sb.append("    posInfo: ").append(Utils.toIndentedString(posInfo)).append("\n");
      sb.append("    cardHolder: ").append(Utils.toIndentedString(cardHolder)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
