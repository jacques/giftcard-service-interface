package io.electrum.giftcard.api.model;

import io.electrum.vas.model.BasicReversal;
import io.swagger.annotations.ApiModel;
import io.electrum.vas.Utils;

/**
 * Information about the gift card replace operation being reversed.
 */
@ApiModel(description = "Information about the gift card replace operation being reversed.")
public class ReplaceReversal extends BasicReversal {

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class ReplaceReversal {\n");
      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    requestId: ").append(Utils.toIndentedString(requestId)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    reversalReason: ").append(Utils.toIndentedString(reversalReason)).append("\n");
      sb.append("}");
      return sb.toString();
   }

}
