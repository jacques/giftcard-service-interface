package io.electrum.giftcard.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.electrum.vas.Utils;
import io.electrum.vas.model.LedgerAmount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Point Amounts which make up the transaction. Absent amounts have zero value.
 */
@ApiModel(description = "Point Amounts which make up the transaction. Absent amounts have zero value.")
public class PointAmounts {
   private PointsAmount requestAmount = null;
   private PointsAmount approvedAmount = null;
   private PointsAmount balanceAmount = null;
   private PointsAmount availableAmount = null;

   public PointAmounts requestAmount(PointsAmount requestAmount) {
      this.requestAmount = requestAmount;
      return this;
   }

   /**
    * The points amount requested by the customer to be authorised or approved. This is the total amount the customer wishes to pay for a service or virtual product.
    *
    * @return requestAmount
    **/
   @ApiModelProperty(value = "The points amount requested by the customer to be authorised or approved. This is the total amount the customer wishes to pay for a service or virtual product.")
   @JsonProperty("requestAmount")
   @Valid
   public PointsAmount getRequestAmount() {
      return requestAmount;
   }

   public void setRequestAmount(PointsAmount requestAmount) {
      this.requestAmount = requestAmount;
   }

   public PointAmounts approvedAmount(PointsAmount approvedAmount) {
      this.approvedAmount = approvedAmount;
      return this;
   }

   /**
    * The points amount which was approved by the upstream entity.
    *
    * @return approvedAmount
    **/
   @ApiModelProperty(value = "The points amount which was approved by the upstream entity.")
   @JsonProperty("approvedAmount")
   @Valid
   public PointsAmount getApprovedAmount() {
      return approvedAmount;
   }

   public void setApprovedAmount(PointsAmount approvedAmount) {
      this.approvedAmount = approvedAmount;
   }

   public PointAmounts balanceAmount(PointsAmount balanceAmount) {
      this.balanceAmount = balanceAmount;
      return this;
   }

   /**
    * The remaining points balance on the customer’s account.
    *
    * @return balanceAmount
    **/
   @ApiModelProperty(value = "The remaining points balance on the customer’s account.")
   @JsonProperty("balanceAmount")
   @Valid
   public PointsAmount getBalanceAmount() {
      return balanceAmount;
   }

   public void setBalanceAmount(PointsAmount balanceAmount) {
      this.balanceAmount = balanceAmount;
   }

   public PointAmounts availableAmount(PointsAmount availableAmount) {
      this.availableAmount = availableAmount;
      return this;
   }

   /**
    * The points currently available on the card which may be redeemed. This field should not include loads which must still be finalised.
    *
    * @return availableAmount
    **/
   @ApiModelProperty(value = "The points currently available on the card which may be redeemed. This field should not include loads which must still be finalised.")
   @JsonProperty("availableAmount")
   @Valid
   public PointsAmount getAvailableAmount() {
      return availableAmount;
   }

   public void setAvailableAmount(PointsAmount availableAmount) {
      this.availableAmount = availableAmount;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof PointAmounts)) return false;
      PointAmounts that = (PointAmounts) o;
      return Objects.equals(requestAmount, that.requestAmount) &&
            Objects.equals(approvedAmount, that.approvedAmount) &&
            Objects.equals(balanceAmount, that.balanceAmount) &&
            Objects.equals(availableAmount, that.availableAmount);
   }

   @Override
   public int hashCode() {
      return Objects.hash(requestAmount, approvedAmount, balanceAmount, availableAmount);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class PointAmounts {\n");

      sb.append("    requestAmount: ").append(Utils.toIndentedString(requestAmount)).append("\n");
      sb.append("    approvedAmount: ").append(Utils.toIndentedString(approvedAmount)).append("\n");
      sb.append("    balanceAmount: ").append(Utils.toIndentedString(balanceAmount)).append("\n");
      sb.append("    availableAmount: ").append(Utils.toIndentedString(availableAmount)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
