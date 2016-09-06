package io.electrum.giftcard.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Data that may be printed on the customer slip for information purposes.
 */
@ApiModel(description = "Data that may be printed on the customer slip for information purposes.")
public class SlipData {

   private List<String> messageLines = new ArrayList<String>();
   private String issuerReference = null;

   public SlipData messageLines(List<String> messageLines) {
      this.messageLines = messageLines;
      return this;
   }

   public SlipData addMessageLinesItem(String messageLinesItem) {
      this.messageLines.add(messageLinesItem);
      return this;
   }

   /**
    * An array of free text lines to be printed on the customer slip.
    * 
    * @return messageLines
    **/
   @ApiModelProperty(value = "An array of free text lines to be printed on the customer slip.")
   @JsonProperty("messageLines")
   public List<String> getMessageLines() {
      return messageLines;
   }

   public void setMessageLines(List<String> messageLines) {
      this.messageLines = messageLines;
   }

   public SlipData issuerReference(String issuerReference) {
      this.issuerReference = issuerReference;
      return this;
   }

   /**
    * An identifier that uniquely references the request associated with the slip. This is printed on the customer slip
    * and uniquely identifies the transaction on the gift card issuer's system. This value is used by the customer
    * to identify a transaction with the gift card issuer. It is thus important that this number can be used to locate a
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
