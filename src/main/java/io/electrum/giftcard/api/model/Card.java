package io.electrum.giftcard.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about a gift card; its number, expiry date and optionally the PIN either unencrypted or encrypted.
 */
@ApiModel(description = "Information about a gift card; its number, expiry date and optionally the PIN either unencrypted or encrypted.")
public class Card {

   private String pan = null;
   private String expiryDate = null;
   private String clearPin = null;
   private String encryptedPin = null;

   public Card pan(String pan) {
      this.pan = pan;
      return this;
   }

   /**
    * Primary account number that uniquely identifies this card.
    * 
    * @return pan
    **/
   @ApiModelProperty(required = true, value = "Primary account number that uniquely identifies this card.")
   @JsonProperty("pan")
   @NotNull
   @Pattern(regexp = "[0-9]{1,19}")
   public String getPan() {
      return pan;
   }

   public void setPan(String pan) {
      this.pan = pan;
   }

   public Card expiryDate(String expiryDate) {
      this.expiryDate = expiryDate;
      return this;
   }

   /**
    * The card expiry date, in YYMM format.
    * 
    * @return expiryDate
    **/
   @ApiModelProperty(required = true, value = "The card expiry date, in YYMM format.")
   @JsonProperty("expiryDate")
   @Pattern(regexp = "[0-9]{4}")
   public String getExpiryDate() {
      return expiryDate;
   }

   public void setExpiryDate(String expiryDate) {
      this.expiryDate = expiryDate;
   }

   public Card clearPin(String clearPin) {
      this.clearPin = clearPin;
      return this;
   }

   /**
    * The pin number associated with the card unencrypted.
    * 
    * @return clearPin
    **/
   @ApiModelProperty(value = "The pin number associated with the card unencrypted.")
   @JsonProperty("clearPin")
   public String getClearPin() {
      return clearPin;
   }

   public void setClearPin(String clearPin) {
      this.clearPin = clearPin;
   }

   public Card encryptedPin(String encryptedPin) {
      this.encryptedPin = encryptedPin;
      return this;
   }

   /**
    * The encrypted pin number associated with the card in HEX format.
    * 
    * @return encryptedPin
    **/
   @ApiModelProperty(value = "The encrypted pin number associated with the card in HEX format.")
   @JsonProperty("encryptedPin")
   @Pattern(regexp = "[0-9ABCDEF]+")
   public String getEncryptedPin() {
      return encryptedPin;
   }

   public void setEncryptedPin(String encryptedPin) {
      this.encryptedPin = encryptedPin;
   }

   @Override
   public boolean equals(java.lang.Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Card card = (Card) o;
      return Objects.equals(this.pan, card.pan) && Objects.equals(this.expiryDate, card.expiryDate)
            && Objects.equals(this.clearPin, card.clearPin) && Objects.equals(this.encryptedPin, card.encryptedPin);
   }

   @Override
   public int hashCode() {
      return Objects.hash(pan, expiryDate, clearPin, encryptedPin);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Card {\n");

      sb.append("    pan: ").append(Utils.toIndentedString(pan)).append("\n");
      sb.append("    expiryDate: ").append(Utils.toIndentedString(expiryDate)).append("\n");
      sb.append("    clearPin: ").append(Utils.toIndentedString(clearPin)).append("\n");
      sb.append("    encryptedPin: ").append(Utils.toIndentedString(encryptedPin)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
