package io.electrum.giftcard.api.model;

import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class SlipData extends io.electrum.vas.model.SlipData {

   private String issuerReference = null;

   public SlipData issuerReference(String issuerReference) {
      this.issuerReference = issuerReference;
      return this;
   }

   /**
    * An identifier that uniquely references the request associated with the slip. This is printed on the customer slip
    * and uniquely identifies the transaction on the gift card issuer's system. This value is used by the customer to
    * identify a transaction with the gift card issuer. It is thus important that this number can be used to locate a
    * past transaction at some time after the transaction has been completed.
    *
    * @return issuerReference
    **/
   @ApiModelProperty(value = "An identifier that uniquely references the request associated with the slip. This is printed on the customer slip and uniquely identifies the transaction on the gift card issuer's system. This value is used by the customer to identify a transaction with the gift card issuer. It is thus important that this number can be used to locate a past transaction at some time after the transaction has been completed.")
   public String getIssuerReference() {
      return issuerReference;
   }

   public void setIssuerReference(String issuerReference) {
      this.issuerReference = issuerReference;
   }

   @Override
   public boolean equals(java.lang.Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      SlipData slipData = (SlipData) o;
      return Objects.equals(this.messageLines, slipData.messageLines)
            && Objects.equals(this.issuerReference, slipData.issuerReference);
   }

   @Override
   public int hashCode() {
      return Objects.hash(messageLines, issuerReference);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class SlipData {\n");

      sb.append("    messageLines: ").append(Utils.toIndentedString(messageLines)).append("\n");
      sb.append("    issuerReference: ").append(Utils.toIndentedString(issuerReference)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
