package io.electrum.giftcard.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Describes how the PAN and PIN were captured by the POS.
 */
@ApiModel(description = "Describes how the PAN and PIN were captured by the POS.")
public class PosEntryMode {

   /**
    * Recognised PAN entry modes.
    */
   public enum PanEntryMode {
      UNKNOWN("UNKNOWN"),
      MANUAL("MANUAL"),
      MAGSTRIPE_NO_CVV("MAGSTRIPE_NO_CVV"),
      BARCODE("BARCODE"),
      OCR("OCR"),
      ICC_CVV("ICC_CVV"),
      CONTACTLESS_ICC("CONTACTLESS_ICC"),
      MAGSTRIPE_CVV("MAGSTRIPE_CVV"),
      CONTACTLESS_MAGSTRIPE("CONTACTLESS_MAGSTRIPE"),
      ICC_NO_CVV("ICC_NO_CVV"),
      ORIG_MODE("ORIG_MODE"),
      FALLBACK("FALLBACK");

      private String value;

      PanEntryMode(String value) {
         this.value = value;
      }

      @Override
      @JsonValue
      public String toString() {
         return String.valueOf(value);
      }
   }

   /**
    * Recognised PIN entry capabilities.
    */
   public enum PinEntryCapability {
      UNKNOWN("UNKNOWN"),
      CAN_ACCEPT("CAN_ACCEPT"),
      CANNOT_ACCEPT("CANNOT_ACCEPT");

      private String value;

      PinEntryCapability(String value) {
         this.value = value;
      }

      @Override
      @JsonValue
      public String toString() {
         return String.valueOf(value);
      }
   }

   private PanEntryMode panEntryMode = null;
   private PinEntryCapability pinEntryCapability = null;

   public PosEntryMode panEntryMode(PanEntryMode panEntryMode) {
      this.panEntryMode = panEntryMode;
      return this;
   }

   /**
    * Describes the method by which the PAN was captured.
    * 
    * @return panEntryMode
    **/
   @ApiModelProperty(required = true, value = "Describes the method by which the PAN was captured.")
   @JsonProperty("panEntryMode")
   @NotNull
   public PanEntryMode getPanEntryMode() {
      return panEntryMode;
   }

   public void setPanEntryMode(PanEntryMode panEntryMode) {
      this.panEntryMode = panEntryMode;
   }

   public PosEntryMode pinEntryCapability(PinEntryCapability pinEntryCapability) {
      this.pinEntryCapability = pinEntryCapability;
      return this;
   }

   /**
    * Describes whether the PIN can be entered.
    * 
    * @return pinEntryMode
    **/
   @ApiModelProperty(required = true, value = "Describes whether the PIN can be entered.")
   @JsonProperty("pinEntryCapability")
   @NotNull
   public PinEntryCapability getPinEntryCapability() {
      return pinEntryCapability;
   }

   public void setPinEntryCapability(PinEntryCapability pinEntryCapability) {
      this.pinEntryCapability = pinEntryCapability;
   }

   @Override
   public boolean equals(java.lang.Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      PosEntryMode posEntryMode = (PosEntryMode) o;
      return Objects.equals(this.panEntryMode, posEntryMode.panEntryMode)
            && Objects.equals(this.pinEntryCapability, posEntryMode.pinEntryCapability);
   }

   @Override
   public int hashCode() {
      return Objects.hash(panEntryMode, pinEntryCapability);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class PosEntryMode {\n");

      sb.append("    panEntryMode: ").append(Utils.toIndentedString(panEntryMode)).append("\n");
      sb.append("    pinEntryCapability: ").append(Utils.toIndentedString(pinEntryCapability)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
