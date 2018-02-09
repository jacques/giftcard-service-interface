package io.electrum.giftcard.api.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.Amounts;
import io.electrum.vas.model.LedgerAmount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Amounts which make up the transaction. Absent amounts have zero value.
 **/
@ApiModel(description = "Amounts which make up the transaction. Absent amounts have zero value.")
public class GiftcardAmounts extends Amounts{

   protected LedgerAmount availableBalance = null;

   public GiftcardAmounts availableBalance(LedgerAmount availableBalance) {
      this.availableBalance = availableBalance;
      return this;
   }

   /**
    * The funds currently available on the card which may be redeemed.
    * This field should not include loads which must still be finalised.
    * 
    * @return availableBalance
    **/
   @ApiModelProperty(value = "The funds currently available on the card which may be redeemed. This field should not include loads which must still be finalised.")
   @JsonProperty("availableBalance")
   public LedgerAmount getAvailableBalance() {
      return availableBalance;
   }

   public void setAvailableBalance(LedgerAmount availableBalance) {
      this.availableBalance = availableBalance;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      GiftcardAmounts tender = (GiftcardAmounts) o;
      return Objects.equals(requestAmount, tender.requestAmount)
            && Objects.equals(approvedAmount, tender.approvedAmount) && Objects.equals(feeAmount, tender.feeAmount)
            && Objects.equals(balanceAmount, tender.balanceAmount) && Objects.equals(availableBalance, tender.availableBalance);
   }

   @Override
   public int hashCode() {
      return Objects.hash(requestAmount, approvedAmount, feeAmount, balanceAmount, availableBalance);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Amounts {\n");

      sb.append("    requestAmount: ").append(Utils.toIndentedString(requestAmount)).append("\n");
      sb.append("    approvedAmount: ").append(Utils.toIndentedString(approvedAmount)).append("\n");
      sb.append("    feeAmount: ").append(Utils.toIndentedString(feeAmount)).append("\n");
      sb.append("    balanceAmount: ").append(Utils.toIndentedString(balanceAmount)).append("\n");
      sb.append("    availableBalance: ").append(Utils.toIndentedString(availableBalance)).append("\n");
      sb.append("    additionalAmounts: ").append(Utils.toIndentedString(additionalAmounts)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
