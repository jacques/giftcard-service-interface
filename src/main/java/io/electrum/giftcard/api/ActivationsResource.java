package io.electrum.giftcard.api;

import java.util.UUID;

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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import io.electrum.giftcard.api.model.ActivationConfirmation;
import io.electrum.giftcard.api.model.ActivationRequest;
import io.electrum.giftcard.api.model.ActivationResponse;
import io.electrum.giftcard.api.model.ActivationReversal;
import io.electrum.vas.model.ErrorDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ResponseHeader;

@Path("/activations")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(description = "the activations API")
public abstract class ActivationsResource {

   protected abstract IActivationsResource getResourceImplementation();

   @POST
   @Path("/{activationId}/confirmations/{confirmationId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Confirm a gift card activation.", notes = "The Activation Confirmations endpoint "
         + "registers the confirmation of a prior activation of a giftcard. Activation confirmations are "
         + "advice type messages and should continue to be sent at suitable intervals until a response has "
         + "been received. Multiple confirmation advices may be sent which refer to the same activation. "
         + "The net result is that the activation is confirmed once.", authorizations = {
               @Authorization(value = "httpBasic") }, tags = { "Activations", "Confirmations", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted"),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void confirmActivation(
         @ApiParam(value = "The randomly generated activationId UUID as sent in the original activation.", required = true) @PathParam("activationId") UUID activationId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("confirmationId") UUID confirmationId,
         @ApiParam(value = "The activation confirmation information.", required = true) ActivationConfirmation activationConfirmation,
         @Context SecurityContext securityContext,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      asyncResponse.resume(
            getResourceImplementation().confirmActivation(
                  activationId,
                  confirmationId,
                  activationConfirmation,
                  securityContext,
                  httpHeaders,
                  uriInfo,
                  httpServletRequest));
   }

   @POST
   @Path("/{activationId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Request a gift card activation.", notes = "The Activations endpoint allows a "
         + "gift card to be activated. Optionally, an amount can be included to indicate initial funds "
         + "which should be credited to the card. An activation is not considered complete until an "
         + "activation confirmation or activation reversal has been sent and acknowledged. An "
         + "activation request should only be sent once.", authorizations = {
         @Authorization(value = "httpBasic") }, tags = { "Activations", })
   @ApiResponses(value = {
         @ApiResponse(code = 201, message = "Created", response = ActivationResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created activation resource", response = String.class) }),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void activate(
         @ApiParam(value = "The randomly generated UUID identifying this activation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("activationId") UUID activationId,
         @ApiParam(value = "The activation information.", required = true) ActivationRequest activationRequest,
         @Context SecurityContext securityContext,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      asyncResponse.resume(
            getResourceImplementation().activate(
                  activationId,
                  activationRequest,
                  securityContext,
                  httpHeaders,
                  uriInfo,
                  httpServletRequest));
   }

   @POST
   @Path("/{activationId}/reversals/{reversalId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Simplistically, an activation reversal undoes an activation if the activation "
         + "was successfully processed.", notes = "The Activation Reversals endpoint allows an "
               + "activation of a giftcard to be reversed. If the sender of an activation request is "
               + "uncertain of the state of an activation request then the sender must send an "
               + "activation reversal. Activation reversals are advice type messages and should continue to "
               + "be sent at suitable intervals until a response has been received. Multiple reversals "
               + "advices may be sent which refer to the same activation. The net result is that the "
               + "activation is reversed once.", authorizations = {
         @Authorization(value = "httpBasic") }, tags = { "Activations", "Reversals", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted"),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void reverseActivation(
         @ApiParam(value = "The randomly generated activationId UUID as sent in the original activation.", required = true) @PathParam("activationId") UUID activationId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("reversalId") UUID reversalId,
         @ApiParam(value = "The activation reversal information.", required = true) ActivationReversal activationReversal,
         @Context SecurityContext securityContext,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      asyncResponse.resume(
            getResourceImplementation().reverseActivation(
                  activationId,
                  reversalId,
                  activationReversal,
                  securityContext,
                  httpHeaders,
                  uriInfo,
                  httpServletRequest));
   }
}
