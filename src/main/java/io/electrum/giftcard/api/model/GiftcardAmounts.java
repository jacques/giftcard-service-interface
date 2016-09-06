package io.electrum.giftcard.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.Amounts;
import io.electrum.vas.model.LedgerAmount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Amounts used within gift card services.
 */
@ApiModel(description = "Amounts used within gift card services")
public class GiftcardAmounts extends Amounts {

   private LedgerAmount loadedAmount = null;
   private LedgerAmount redeemedAmount = null;
   private LedgerAmount balanceAmount = null;

   public GiftcardAmounts loadedAmount(LedgerAmount loadedAmount) {
      this.loadedAmount = loadedAmount;
      return this;
   }

   /**
    * An amount which was loaded onto the gift card as part of an activation or load request.
    * 
    * @return loadedAmount
    **/
   @ApiModelProperty(value = "An amount which was loaded onto the gift card as part of an activation or load request.")
   @JsonProperty("loadedAmount")
   public LedgerAmount getLoadedAmount() {
      return loadedAmount;
   }

   public void setLoadedAmount(LedgerAmount loadedAmount) {
      this.loadedAmount = loadedAmount;
   }

   public GiftcardAmounts redeemedAmount(LedgerAmount redeemedAmount) {
      this.redeemedAmount = redeemedAmount;
      return this;
   }

   /**
    * An amount which was redeemed against the gift card as part of a redemption request.
    * 
    * @return redeemAmount
    **/
   @ApiModelProperty(value = "An amount which was redeemed against the gift card as part of a redemption request.")
   @JsonProperty("redeemedAmount")
   public LedgerAmount getRedeemedAmount() {
      return redeemedAmount;
   }

   public void setRedeemedAmount(LedgerAmount redeemedAmount) {
      this.redeemedAmount = redeemedAmount;
   }

   public GiftcardAmounts balanceAmount(LedgerAmount balanceAmount) {
      this.balanceAmount = balanceAmount;
      return this;
   }

   /**
    * The balance of funds available which may be redeemed against the card.
    * 
    * @return balanceAmount
    **/
   @ApiModelProperty(value = "The balance of funds available which may be redeemed against the card.")
   @JsonProperty("balanceAmount")
   public LedgerAmount getBalanceAmount() {
      return balanceAmount;
   }

   public void setBalanceAmount(LedgerAmount balanceAmount) {
      this.balanceAmount = balanceAmount;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class GiftcardAmounts {\n");

      sb.append("    requestAmount: ").append(Utils.toIndentedString(requestAmount)).append("\n");
      sb.append("    loadAmount: ").append(Utils.toIndentedString(loadedAmount)).append("\n");
      sb.append("    redeemedAmount: ").append(Utils.toIndentedString(redeemedAmount)).append("\n");
      sb.append("    balanceAmount: ").append(Utils.toIndentedString(balanceAmount)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
