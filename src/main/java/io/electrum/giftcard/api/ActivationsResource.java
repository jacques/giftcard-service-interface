package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.ActivationConfirmation;
import io.electrum.giftcard.api.model.ActivationRequest;
import io.electrum.giftcard.api.model.ActivationResponse;
import io.electrum.giftcard.api.model.ActivationReversal;
import io.electrum.giftcard.api.model.ErrorDetail;
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


@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(description = "the activations API", authorizations = {
        @Authorization(value = GiftcardApi.HttpAuthorizations.HTTP_BASIC) })
@Path("/")
public abstract class ActivationsResource {

   protected abstract IActivationsResource getResourceImplementation();

   @POST
   @Path(GiftcardApi.Paths.ActivationPaths.ACTIVATION_CONFIRMATION)
   @ApiOperation(value = "Confirm a gift card activation.", notes = "The Activation Confirmations endpoint "
         + "registers the confirmation of a prior activation of a giftcard. Activation confirmations are "
         + "advice type messages and should continue to be sent at suitable intervals until a response has "
         + "been received. Multiple confirmation advices may be sent which refer to the same activation. "
         + "The net result is that the activation is confirmed once.", tags = { "Activations", "Confirmations", }, nickname = Operations.CONFIRM_ACTIVATION)
   @ApiResponses(value = { @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdvice.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST , response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void confirmActivation(
         @ApiParam(value = "The randomly generated activationId UUID as sent in the original activation.", required = true) @PathParam(GiftcardApi.PathParams.ACTIVATION_ID) String activationId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.CONFIRMATION_ID) String confirmationId,
         @ApiParam(value = "The activation confirmation information.", required = true) ActivationConfirmation activationConfirmation,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().confirmActivation(
            activationId,
            confirmationId,
            activationConfirmation,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   
   @POST
   @Path(GiftcardApi.Paths.ActivationPaths.ACTIVATION_REQUEST)
   @ApiOperation(value = "Request a gift card activation.", notes = "The Activations endpoint allows a "
         + "gift card to be activated. Optionally, an amount can be included to indicate initial funds "
         + "which should be credited to the card. An activation is not considered complete until an "
         + "activation confirmation or activation reversal has been sent and acknowledged. An "
         + "activation request should only be sent once.", tags = { "Activations", }, nickname = Operations.ACTIVATE)
   @ApiResponses(value = {
         @ApiResponse(code = GiftcardApi.ResponseCodes.CREATED, message = GiftcardApi.ResponseMessages.CREATED, response = ActivationResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created activation resource", response = String.class) }),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void activate(
         @ApiParam(value = "The randomly generated UUID identifying this activation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.ACTIVATION_ID) String activationId,
         @ApiParam(value = "The activation information.", required = true) ActivationRequest activationRequest,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().activate(
            activationId,
            activationRequest,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path(GiftcardApi.Paths.ActivationPaths.ACTIVATION_REVERSAL)
   @ApiOperation(value = "Simplistically, an activation reversal undoes an activation if the activation "
         + "was successfully processed.", notes = "The Activation Reversals endpoint allows an "
               + "activation of a giftcard to be reversed. If the sender of an activation request is "
               + "uncertain of the state of an activation request then the sender must send an "
               + "activation reversal. Activation reversals are advice type messages and should continue to "
               + "be sent at suitable intervals until a response has been received. Multiple reversals "
               + "advices may be sent which refer to the same activation. The net result is that the "
               + "activation is reversed once.", tags = { "Activations", "Reversals", }, nickname = Operations.REVERSE_ACIVATION)
   @ApiResponses(value = { @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdvice.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void reverseActivation(
         @ApiParam(value = "The randomly generated activationId UUID as sent in the original activation.", required = true) @PathParam(GiftcardApi.PathParams.ACTIVATION_ID) String activationId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam(GiftcardApi.PathParams.REVERSAL_ID) String reversalId,
         @ApiParam(value = "The activation reversal information.", required = true) ActivationReversal activationReversal,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().reverseActivation(
            activationId,
            reversalId,
            activationReversal,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   public class Operations {
      public static final String CONFIRM_ACTIVATION = "confirmActivation";
      public static final String ACTIVATE = "activate";
      public static final String REVERSE_ACIVATION = "reverseActivation";
   }
}
