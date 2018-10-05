package io.electrum.giftcard.api;


import io.electrum.giftcard.api.model.ErrorDetail;
import io.electrum.giftcard.api.model.ReplaceConfirmation;
import io.electrum.giftcard.api.model.ReplaceRequest;
import io.electrum.giftcard.api.model.ReplaceResponse;
import io.electrum.giftcard.api.model.ReplaceReversal;
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
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Api(description = "the replaces API", authorizations = {
      @Authorization(value = GiftcardApi.HttpAuthorizations.HTTP_BASIC) })
public abstract class ReplacesResource {
   protected abstract IReplacesResource getResourceImplementation();

   @POST
   @Path(GiftcardApi.Paths.ReplacePaths.REPLACE_CONFIRMATION)
   @ApiOperation(value = "Confirm a replace request of an old card with a new card.", notes = "The Replace Confirmations endpoint "
         + "registers the confirmation of a prior replace request for an old gift card to be replaced by a new gift card. "
         + "Replace confirmations are advice type messages and should continue to be sent at suitable intervals until a response has "
         + "been received. Multiple confirmation advices may be sent which refer to the same replace request. The net result is that the "
         + "replace request is confirmed once.", tags = { "Confirmations",
                     "Replaces", }, nickname = Operations.CONFIRM_REPLACE)
   @ApiResponses(value = {
         @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdvice.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void confirmReplace(
         @ApiParam(value = "The randomly generated replaceId UUID as sent in the original replace request.", required = true) @PathParam(GiftcardApi.PathParams.REPLACE_ID) String replaceId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.CONFIRMATION_ID) String confirmationId,
         @ApiParam(value = "The replace confirmation information.", required = true) ReplaceConfirmation replaceConfirmation,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().confirmReplace(
            replaceId,
            confirmationId,
            replaceConfirmation,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path(GiftcardApi.Paths.ReplacePaths.REPLACE_REQUEST)
   @ApiOperation(value = "Request a replace of an old gift card with a new gift card.", notes = "The Replace endpoint "
         + "allows old gift cards to be replaced with new gift cards (transfers all funds and voids old card). "
         + "A replace is not considered complete until a replace confirmation or replace reversal has been sent and "
         + "acknowledged. A replace request should only be sent once otherwise multiple "
         + "replace requests may occur erroneously.", tags = {
                     "Replaces", }, nickname = Operations.REPLACE)
   @ApiResponses(value = {
         @ApiResponse(code = GiftcardApi.ResponseCodes.CREATED, message = GiftcardApi.ResponseMessages.CREATED, response = ReplaceResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created load resource", response = String.class) }),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void replace(
         @ApiParam(value = "The randomly generated UUID identifying this replace, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.REPLACE_ID) String replaceId,
         @ApiParam(value = "The replace information.", required = true) ReplaceRequest replaceRequest,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().replace(
            replaceId,
            replaceRequest,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path(GiftcardApi.Paths.ReplacePaths.REPLACE_REVERSAL)
   @ApiOperation(value = "Simplistically, a replace reversal undoes a replace request if the replace "
         + "was successfully processed.", notes = "The Replace Reversals endpoint allows "
               + "replace requests of old gift cards with a new gift cards to be reversed. If the sender of a replace request "
               + "is uncertain of the state of a replace request then the sender must send a "
               + "replace reversal. Reversals should continue to be sent at suitable intervals "
               + "until a response has been received. Multiple reversals may be sent which refer to "
               + "the same replace request. The net result is that the replace request is reversed once.", tags = { "Replaces",
                           "Reversals", }, nickname = Operations.REVERSE_REPLACE)
   @ApiResponses(value = {
         @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdvice.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void reverseReplace(
         @ApiParam(value = "The randomly generated replaceId UUID as sent in the original replace.", required = true) @PathParam(GiftcardApi.PathParams.REPLACE_ID) String replaceId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.REVERSAL_ID) String reversalId,
         @ApiParam(value = "The replace reversal information.", required = true) ReplaceReversal replaceReversal,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().reverseReplace(
            replaceId,
            reversalId,
            replaceReversal,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   public class Operations {
      public static final String CONFIRM_REPLACE = "confirmReplace";
      public static final String REPLACE = "replace";
      public static final String REVERSE_REPLACE = "reverseReplace";
   }

}
