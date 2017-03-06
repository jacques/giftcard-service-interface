package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.ErrorDetail;
import io.electrum.giftcard.api.model.RedemptionConfirmation;
import io.electrum.giftcard.api.model.RedemptionRequest;
import io.electrum.giftcard.api.model.RedemptionResponse;
import io.electrum.giftcard.api.model.RedemptionReversal;
import io.electrum.vas.model.BasicAdviceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ResponseHeader;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/redemptions")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(description = "the redemptions API")
public abstract class RedemptionsResource {
   protected abstract IRedemptionsResource getResourceImplementation();

   @POST
   @Path("/{redemptionId}/confirmations/{confirmationId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Confirm a redemption against a gift card.", notes = "The Redemption Confirmations endpoint "
         + "registers the confirmation of a prior redemption of a gift card. Redemption confirmations are advice type "
         + "messages and should continue to be sent at suitable intervals until a response has been received. Multiple "
         + "confirmation advices may be sent which refer to the same redemption. The net result is that the "
         + "redemption is confirmed once.", authorizations = {
               @Authorization(value = "httpBasic") }, tags = { "Confirmations", "Redemptions", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = BasicAdviceResponse.class),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void confirmRedemption(
         @ApiParam(value = "The randomly generated redemptionId UUID as sent in the original redemption.", required = true) @PathParam("redemptionId") String redemptionId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("confirmationId") String confirmationId,
         @ApiParam(value = "The redemption confirmation information.", required = true) RedemptionConfirmation redemptionConfirmation,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().confirmRedemption(
            redemptionId,
            confirmationId,
            redemptionConfirmation,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path("/{redemptionId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Request a redemption of a gift card.", notes = "The Redemptions endpoint "
         + "allows gift cards to be redeemed as a form of tender. A redemption is not considered "
         + "complete until a redemption confirmation or redemption reversal has been sent and "
         + "acknowledged. A redemption request should only be sent once otherwise multiple "
         + "redemptions may occur erroneously.", authorizations = {
               @Authorization(value = "httpBasic") }, tags = { "Redemptions", })
   @ApiResponses(value = {
         @ApiResponse(code = 201, message = "Created", response = RedemptionResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created load resource", response = String.class) }),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void redeem(
         @ApiParam(value = "The randomly generated UUID identifying this redemption, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("redemptionId") String redemptionId,
         @ApiParam(value = "The redemption information.", required = true) RedemptionRequest redemptionRequest,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().redeem(
            redemptionId,
            redemptionRequest,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path("/{redemptionId}/reversals/{reversalId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Simplistically, a redemption reversal undoes a redemption if the redemption "
         + "was successfully processed.", notes = "The Redemption Reversals endpoint allows "
               + "redemptions on a gift card to be reversed. If the sender of a redemption request "
               + "is uncertain of the state of a redemption request then the sender must send a "
               + "redemption reversal. Reversals should continue to be sent at suitable intervals "
               + "until a response has been received. Multiple reversals may be sent which refer to "
               + "the same redemption. The net result is that the redemption is reversed once. Note "
               + "that a reversal does not equate to a load.", authorizations = {
                     @Authorization(value = "httpBasic") }, tags = { "Redemptions", "Reversals", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = BasicAdviceResponse.class),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void reverseRedemption(
         @ApiParam(value = "The randomly generated redemptionId UUID as sent in the original redemption.", required = true) @PathParam("redemptionId") String redemptionId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("reversalId") String reversalId,
         @ApiParam(value = "The redemption reversal information.", required = true) RedemptionReversal redemtpionReversal,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().reverseRedemption(
            redemptionId,
            reversalId,
            redemtpionReversal,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }
}
