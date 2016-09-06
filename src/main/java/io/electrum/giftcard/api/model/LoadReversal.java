package io.electrum.giftcard.api.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.BasicReversal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about the gift card load being reversed.
 */
@ApiModel(description = "Information about the gift card load being reversed.")
public class LoadReversal extends BasicReversal {
   private LoadRequest loadRequest = null;

   public LoadReversal loadRequest(LoadRequest loadRequest) {
      this.loadRequest = loadRequest;
      return this;
   }

   /**
    * Get loadRequest
    * 
    * @return loadRequest
    **/
   @ApiModelProperty(required = true, value = "The original Load Request being reversed.")
   @JsonProperty("loadRequest")
   @NotNull
   public LoadRequest getLoadRequest() {
      return loadRequest;
   }

   public void setLoadRequest(LoadRequest loadRequest) {
      this.loadRequest = loadRequest;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class LoadReversal {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    requestId: ").append(Utils.toIndentedString(requestId)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    reversalReason: ").append(Utils.toIndentedString(reversalReason)).append("\n");
      sb.append("    loadRequest: ").append(Utils.toIndentedString(loadRequest)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
