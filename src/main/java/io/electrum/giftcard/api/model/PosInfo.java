package io.electrum.giftcard.api.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * POS related data.
 */
@ApiModel(description = "POS related data.")
public class PosInfo {
   private PosEntryMode entryMode = null;

   public PosInfo entryMode(PosEntryMode entryMode) {
      this.entryMode = entryMode;
      return this;
   }

   /**
    * Describes the manner in which the POS captured card and PIN data.
    * 
    * @return entryMode
    **/
   @ApiModelProperty(value = "Describes the manner in which the POS captured card and PIN data.")
   @JsonProperty("entryMode")
   public PosEntryMode getEntryMode() {
      return entryMode;
   }

   public void setEntryMode(PosEntryMode entryMode) {
      this.entryMode = entryMode;
   }

   @Override
   public boolean equals(java.lang.Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      PosInfo posInfo = (PosInfo) o;
      return Objects.equals(this.entryMode, posInfo.entryMode);
   }

   @Override
   public int hashCode() {
      return Objects.hash(entryMode);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class PosInfo {\n");

      sb.append("    entryMode: ").append(Utils.toIndentedString(entryMode)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
