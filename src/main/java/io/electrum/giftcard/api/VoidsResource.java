package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.ErrorDetail;
import io.electrum.giftcard.api.model.VoidConfirmation;
import io.electrum.giftcard.api.model.VoidRequest;
import io.electrum.giftcard.api.model.VoidResponse;
import io.electrum.giftcard.api.model.VoidReversal;
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
@Api(description = "the voids API",  authorizations = {
        @Authorization(value = GiftcardApi.HttpAuthorizations.HTTP_BASIC) })
public abstract class VoidsResource {
   protected abstract IVoidsResource getResourceImplementation();

   @POST
   @Path(GiftcardApi.Paths.VoidPaths.VOID_CONFIRMATION)
   @ApiOperation(value = "Confirm a void of a gift card.", notes = "The Void Confirmations endpoint "
         + "registers the confirmation of a prior void on a gift card. Void confirmations are "
         + "advice type messages and should continue to be sent at suitable intervals until a response "
         + "has been received. Multiple confirmation advices may be sent which refer to the same "
         + "void. The net result is that the void is confirmed once.", tags = { "Confirmations", "Voids", }, nickname = Operations.CONFIRM_VOID)
   @ApiResponses(value = { @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdvice.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void confirmVoid(
         @ApiParam(value = "The randomly generated voidId UUID as sent in the original void.", required = true) @PathParam(GiftcardApi.PathParams.VOID_ID) String voidId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.CONFIRMATION_ID) String confirmationId,
         @ApiParam(value = "The void confirmation information.", required = true) VoidConfirmation voidConfirmation,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().confirmVoid(
            voidId,
            confirmationId,
            voidConfirmation,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path(GiftcardApi.Paths.VoidPaths.VOID_REQUEST)
   @ApiOperation(value = "Request a gift card be voided.", notes = "The Voids endpoint "
         + "allows an activated giftcard to be voided. A void is not considered "
         + "complete until a void confirmation or void reversal has been sent and "
         + "acknowledged. While a gift card can only be voided once, a void request "
         + "should only be sent once and then either confirmed or reversed.", response = VoidResponse.class, tags = { "Voids", },nickname = Operations.VOID)
   @ApiResponses(value = {
         @ApiResponse(code = GiftcardApi.ResponseCodes.CREATED, message = GiftcardApi.ResponseMessages.CREATED, response = VoidResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created load resource", response = String.class) }),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void voidGiftcard(
         @ApiParam(value = "The randomly generated UUID identifying this void, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.VOID_ID) String voidId,
         @ApiParam(value = "The void information.", required = true) VoidRequest voidRequest,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().voidGiftcard(
            voidId,
            voidRequest,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path(GiftcardApi.Paths.VoidPaths.VOID_REVERSAL)
   @ApiOperation(value = "Simplistically, a void reversal undoes a void if the void "
         + "was successfully processed.", notes = "The Void Reversals endpoint "
               + "allows voids of a gift card to be reversed. If the sender of a "
               + "void request is uncertain of the state of a void request then the "
               + "sender must send a void reversal. Reversals should continue to be "
               + "sent at suitable intervals until a response has been received. "
               + "Multiple reversals may be sent which refer to the same void. The "
               + "net result is that the void is reversed once.", tags = { "Voids", "Reversals", }, nickname = Operations.REVERSE_VOID)
   @ApiResponses(value = { @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdvice.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void reverseVoid(
         @ApiParam(value = "The randomly generated voidId UUID as sent in the original void.", required = true) @PathParam(GiftcardApi.PathParams.VOID_ID) String voidId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.REVERSAL_ID) String reversalId,
         @ApiParam(value = "The void reversal information.", required = true) VoidReversal voidReversal,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().reverseVoid(
            voidId,
            reversalId,
            voidReversal,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   public class Operations {
        public static final String CONFIRM_VOID = "confirmVoid";
        public static final String VOID = "void";
        public static final String REVERSE_VOID = "reverseVoid";
    }
}
