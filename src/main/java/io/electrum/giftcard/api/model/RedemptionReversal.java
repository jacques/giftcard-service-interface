package io.electrum.giftcard.api.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.BasicReversal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about the gift card redemption being reversed.
 */
@ApiModel(description = "Information about the gift card redemption being reversed.")
public class RedemptionReversal extends BasicReversal {
   private RedemptionRequest redemptionRequest = null;

   public RedemptionReversal redemptionRequest(RedemptionRequest redemptionRequest) {
      this.redemptionRequest = redemptionRequest;
      return this;
   }

   /**
    * Get redemptionRequest
    * 
    * @return redemptionRequest
    **/
   @ApiModelProperty(required = true, value = "The original Redemption Request being reversed.")
   @JsonProperty("redemptionRequest")
   @NotNull
   public RedemptionRequest getRedemptionRequest() {
      return redemptionRequest;
   }

   public void setRedemptionRequest(RedemptionRequest redemptionRequest) {
      this.redemptionRequest = redemptionRequest;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class RedemptionReversal {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    requestId: ").append(Utils.toIndentedString(requestId)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    reversalReason: ").append(Utils.toIndentedString(reversalReason)).append("\n");
      sb.append("    redemptionRequest: ").append(Utils.toIndentedString(redemptionRequest)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
