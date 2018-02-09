package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.*;
import io.electrum.vas.model.BasicAdviceResponse;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.*;

@Path("/replaces")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(description = "the replaces API")
public abstract class ReplacesResource {
   protected abstract IReplacesResource getResourceImplementation();

   @POST
   @Path("/{replaceId}/confirmations/{confirmationId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Confirm a replace request of an old card with a new card.", notes = "The Replace Confirmations endpoint "
         + "registers the confirmation of a prior replace request for an old gift card to be replaced by a new gift card. "
         + "Replace confirmations are advice type messages and should continue to be sent at suitable intervals until a response has "
         + "been received. Multiple confirmation advices may be sent which refer to the same replace request. The net result is that the "
         + "replace request is confirmed once.", authorizations = {
         @Authorization(value = "httpBasic") }, tags = { "Confirmations", "Replaces", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = BasicAdviceResponse.class),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void confirmReplace(
         @ApiParam(value = "The randomly generated replaceId UUID as sent in the original replace request.", required = true) @PathParam("replaceId") String replaceId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("confirmationId") String confirmationId,
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
   @Path("/{replaceId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Request a replace of an old gift card with a new gift card.", notes = "The Replace endpoint "
         + "allows old gift cards to be replaced with new gift cards (transfers all funds and voids old card). "
         + "A replace is not considered complete until a replace confirmation or replace reversal has been sent and "
         + "acknowledged. A replace request should only be sent once otherwise multiple "
         + "replace requests may occur erroneously.", authorizations = {
         @Authorization(value = "httpBasic") }, tags = { "Replaces", })
   @ApiResponses(value = {
         @ApiResponse(code = 201, message = "Created", response = ReplaceResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created load resource", response = String.class) }),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void replace(
         @ApiParam(value = "The randomly generated UUID identifying this replace, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("replaceId") String replaceId,
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
   @Path("/{replaceId}/reversals/{reversalId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Simplistically, a replace reversal undoes a replace request if the replace "
         + "was successfully processed.", notes = "The Replace Reversals endpoint allows "
         + "replace requests of old gift cards with a new gift cards to be reversed. If the sender of a replace request "
         + "is uncertain of the state of a replace request then the sender must send a "
         + "replace reversal. Reversals should continue to be sent at suitable intervals "
         + "until a response has been received. Multiple reversals may be sent which refer to "
         + "the same replace request. The net result is that the replace request is reversed once.", authorizations = {
         @Authorization(value = "httpBasic") }, tags = { "Replaces", "Reversals", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = BasicAdviceResponse.class),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void reverseReplace(
         @ApiParam(value = "The randomly generated replaceId UUID as sent in the original replace.", required = true) @PathParam("replaceId") String replaceId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("reversalId") String reversalId,
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

}
