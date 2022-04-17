package io.electrum.giftcard.api.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.PosConditionCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * POS related data.
 */
@ApiModel(description = "POS related data.")
public class PosInfo {
   private PosEntryMode entryMode = null;
   private PosConditionCode posConditionCode = null;

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

   public PosInfo posConditionCode(PosConditionCode posConditionCode) {
      this.posConditionCode = posConditionCode;
      return this;
   }

   /**
    * Describes the circumstances of the transaciton at the POS.
    *
    * @return entryMode
    **/
   @ApiModelProperty(value = "Describes the circumstances of the transaciton at the POS.")
   @JsonProperty("posConditionCode")
   public PosConditionCode getPosConditionCode() {
      return posConditionCode;
   }

   public void setPosConditionCode(PosConditionCode posConditionCode) {
      this.posConditionCode = posConditionCode;
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
      return Objects.equals(this.entryMode, posInfo.entryMode)
            && Objects.equals(this.posConditionCode, posInfo.posConditionCode);
   }

   @Override
   public int hashCode() {
      return Objects.hash(entryMode, posConditionCode);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class PosInfo {\n");

      sb.append("    entryMode: ").append(Utils.toIndentedString(entryMode)).append("\n");
      sb.append("    posConditionCode: ").append(Utils.toIndentedString(posConditionCode)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
