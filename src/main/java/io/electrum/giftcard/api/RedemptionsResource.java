package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.ErrorDetail;
import io.electrum.giftcard.api.model.RedemptionConfirmation;
import io.electrum.giftcard.api.model.RedemptionRequest;
import io.electrum.giftcard.api.model.RedemptionResponse;
import io.electrum.giftcard.api.model.RedemptionReversal;
import io.electrum.vas.model.BasicAdvice;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ResponseHeader;

@Path("")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(description = "the redemptions API", authorizations = {
        @Authorization(value = GiftcardApi.HttpAuthorizations.HTTP_BASIC) })
public abstract class RedemptionsResource {
   protected abstract IRedemptionsResource getResourceImplementation();

   @POST
   @Path(GiftcardApi.Paths.RedemptionPaths.REDEMPTION_CONFIRMATION)
   @ApiOperation(value = "Confirm a redemption against a gift card.", notes = "The Redemption Confirmations endpoint "
         + "registers the confirmation of a prior redemption of a gift card. Redemption confirmations are advice type "
         + "messages and should continue to be sent at suitable intervals until a response has been received. Multiple "
         + "confirmation advices may be sent which refer to the same redemption. The net result is that the "
         + "redemption is confirmed once.", tags = { "Confirmations", "Redemptions", }, nickname = Operations.CONFIRM_REDEMPTION)
   @ApiResponses(value = { @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdvice.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void confirmRedemption(
         @ApiParam(value = "The randomly generated redemptionId UUID as sent in the original redemption.", required = true) @PathParam(GiftcardApi.PathParams.REDEMPTION_ID) String redemptionId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.CONFIRMATION_ID) String confirmationId,
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
   @Path(GiftcardApi.Paths.RedemptionPaths.REDEMPTION_REQUEST)
   @ApiOperation(value = "Request a redemption of a gift card.", notes = "The Redemptions endpoint "
         + "allows gift cards to be redeemed as a form of tender. A redemption is not considered "
         + "complete until a redemption confirmation or redemption reversal has been sent and "
         + "acknowledged. A redemption request should only be sent once otherwise multiple "
         + "redemptions may occur erroneously.", tags = { "Redemptions", }, nickname = Operations.REDEMPTION)
   @ApiResponses(value = {
         @ApiResponse(code = GiftcardApi.ResponseCodes.CREATED, message = GiftcardApi.ResponseMessages.CREATED, response = RedemptionResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created load resource", response = String.class) }),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void redeem(
         @ApiParam(value = "The randomly generated UUID identifying this redemption, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.REDEMPTION_ID) String redemptionId,
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
   @Path(GiftcardApi.Paths.RedemptionPaths.REDEMPTION_REVERSAL)
   @ApiOperation(value = "Simplistically, a redemption reversal undoes a redemption if the redemption "
         + "was successfully processed.", notes = "The Redemption Reversals endpoint allows "
               + "redemptions on a gift card to be reversed. If the sender of a redemption request "
               + "is uncertain of the state of a redemption request then the sender must send a "
               + "redemption reversal. Reversals should continue to be sent at suitable intervals "
               + "until a response has been received. Multiple reversals may be sent which refer to "
               + "the same redemption. The net result is that the redemption is reversed once. Note "
               + "that a reversal does not equate to a load.", tags = { "Redemptions", "Reversals", }, nickname = Operations.REVERSE_REDEMPTION)
   @ApiResponses(value = { @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdvice.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void reverseRedemption(
         @ApiParam(value = "The randomly generated redemptionId UUID as sent in the original redemption.", required = true) @PathParam(GiftcardApi.PathParams.REDEMPTION_ID) String redemptionId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.REVERSAL_ID) String reversalId,
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

   public class Operations {
      public static final String CONFIRM_REDEMPTION = "confirmRedemption";
      public static final String REDEMPTION = "redemption";
      public static final String REVERSE_REDEMPTION = "reverseRedemption";
   }
}
