package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.ErrorDetail;
import io.electrum.giftcard.api.model.LoadConfirmation;
import io.electrum.giftcard.api.model.LoadRequest;
import io.electrum.giftcard.api.model.LoadResponse;
import io.electrum.giftcard.api.model.LoadReversal;
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
@Api(description = "the loads API", authorizations = {
        @Authorization(value = GiftcardApi.HttpAuthorizations.HTTP_BASIC) })
public abstract class LoadsResource {
   protected abstract ILoadsResource getResourceImplementation();

   @POST
   @Path(GiftcardApi.Paths.LoadPaths.LOAD_CONFIRMATION)
   @ApiOperation(value = "Confirm a load of funds on a gift card.", notes = "The Load Confirmations endpoint "
         + "registers the confirmation of a prior load on a gift card. Load confirmations are advice type "
         + "messages and should continue to be sent at suitable intervals until a response has been received. "
         + "Multiple confirmation advices may be sent which refer to the same load. The net result is that "
         + "the load is confirmed once.", tags = { "Confirmations", "Loads", }, nickname = Operations.CONFIRM_LOAD)
   @ApiResponses(value = { @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdvice.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void confirmLoad(
         @ApiParam(value = "The randomly generated loadId UUID as sent in the original load.", required = true) @PathParam(GiftcardApi.PathParams.LOAD_ID) String loadId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.CONFIRMATION_ID) String confirmationId,
         @ApiParam(value = "The load confirmation information.", required = true) LoadConfirmation loadConfirmation,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().confirmLoad(
            loadId,
            confirmationId,
            loadConfirmation,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path(GiftcardApi.Paths.LoadPaths.LOAD_REQUEST)
   @ApiOperation(value = "Request funds to be loaded on a gift card.", notes = "The Loads endpoint "
         + "allows loading of funds on a gift card to be authorized. A load is not considered "
         + "complete until a load confirmation or load reversal has been sent and acknowledged. A "
         + "load request should only be sent once otherwise multiple loads may occur erroneously.", tags = { "Loads", }, nickname = Operations.LOAD)
   @ApiResponses(value = {
         @ApiResponse(code = GiftcardApi.ResponseCodes.CREATED, message = GiftcardApi.ResponseMessages.CREATED, response = LoadResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created load resource", response = String.class) }),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void load(
         @ApiParam(value = "The randomly generated UUID identifying this load, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.LOAD_ID) String loadId,
         @ApiParam(value = "The load information.", required = true) LoadRequest loadRequest,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().load(
            loadId,
            loadRequest,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path(GiftcardApi.Paths.LoadPaths.LOAD_REVERSAL)
   @ApiOperation(value = "Simplistically, a load reversal undoes a load if the load "
         + "was successfully processed.", notes = "The Load Reversals endpoint allows "
               + "loads on a gift card to be reversed. If the sender of a load request "
               + "is uncertain of the state of a load request then the sender must "
               + "send a load reversal. Reversals should continue to be sent at "
               + "suitable intervals until a response has been received. Multiple "
               + "reversals may be sent which refer to the same load. The net result "
               + "is that the load is reversed once. Note that a load reversal does not "
               + "equate to a redemption.", tags = { "Loads", "Reversals", }, nickname = Operations.REVERSE_LOAD)
   @ApiResponses(value = { @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdvice.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void reverseLoad(
         @ApiParam(value = "The randomly generated loadId UUID as sent in the original load.", required = true) @PathParam(GiftcardApi.PathParams.LOAD_ID) String loadId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.REVERSAL_ID) String reversalId,
         @ApiParam(value = "The redemption reversal information.", required = true) LoadReversal loadReversal,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().reverseLoad(
            loadId,
            reversalId,
            loadReversal,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   public class Operations {
      public static final String CONFIRM_LOAD = "confirmLoad";
      public static final String LOAD = "load";
      public static final String REVERSE_LOAD = "reverseLoad";
   }
}
