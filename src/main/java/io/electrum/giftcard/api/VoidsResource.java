package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.ErrorDetail;
import io.electrum.giftcard.api.model.VoidConfirmation;
import io.electrum.giftcard.api.model.VoidRequest;
import io.electrum.giftcard.api.model.VoidResponse;
import io.electrum.giftcard.api.model.VoidReversal;
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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/voids")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(description = "the voids API")
public abstract class VoidsResource {
   protected abstract IVoidsResource getResourceImplementation();

   @POST
   @Path("/{voidId}/confirmations/{confirmationId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Confirm a void of a gift card.", notes = "The Void Confirmations endpoint "
         + "registers the confirmation of a prior void on a gift card. Void confirmations are "
         + "advice type messages and should continue to be sent at suitable intervals until a response "
         + "has been received. Multiple confirmation advices may be sent which refer to the same "
         + "void. The net result is that the void is confirmed once.", authorizations = { @Authorization(value = "httpBasic") }, tags = {
         "Confirmations", "Voids", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = BasicAdviceResponse.class),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void confirmVoid(
         @ApiParam(value = "The randomly generated voidId UUID as sent in the original void.", required = true) @PathParam("voidId") String voidId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("confirmationId") String confirmationId,
         @ApiParam(value = "The void confirmation information.", required = true) VoidConfirmation voidConfirmation,
         @Context SecurityContext securityContext,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      asyncResponse.resume(getResourceImplementation().confirmVoid(
            voidId,
            confirmationId,
            voidConfirmation,
            securityContext,
            httpHeaders,
            uriInfo,
            httpServletRequest));
   }

   @POST
   @Path("/{voidId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Request a gift card be voided.", notes = "The Voids endpoint "
         + "allows an activated giftcard to be voided. A void is not considered "
         + "complete until a void confirmation or void reversal has been sent and "
         + "acknowledged. While a gift card can only be voided once, a void request "
         + "should only be sent once and then either confirmed or reversed.", response = VoidResponse.class, authorizations = { @Authorization(value = "httpBasic") }, tags = { "Voids", })
   @ApiResponses(value = {
         @ApiResponse(code = 201, message = "Created", response = VoidResponse.class, responseHeaders = { @ResponseHeader(name = "Location", description = "The location of the created load resource", response = String.class) }),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void voidGiftcard(
         @ApiParam(value = "The randomly generated UUID identifying this void, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("voidId") String voidId,
         @ApiParam(value = "The void information.", required = true) VoidRequest voidRequest,
         @Context SecurityContext securityContext,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      asyncResponse.resume(getResourceImplementation().voidGiftcard(
            voidId,
            voidRequest,
            securityContext,
            httpHeaders,
            uriInfo,
            httpServletRequest));
   }

   @POST
   @Path("/{voidId}/reversals/{reversalId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Simplistically, a void reversal undoes a void if the void " + "was successfully processed.", notes = "The Void Reversals endpoint "
         + "allows voids of a gift card to be reversed. If the sender of a "
         + "void request is uncertain of the state of a void request then the "
         + "sender must send a void reversal. Reversals should continue to be "
         + "sent at suitable intervals until a response has been received. "
         + "Multiple reversals may be sent which refer to the same void. The "
         + "net result is that the void is reversed once.", authorizations = { @Authorization(value = "httpBasic") }, tags = {
         "Voids", "Reversals", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = BasicAdviceResponse.class),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void reverseVoid(
         @ApiParam(value = "The randomly generated voidId UUID as sent in the original void.", required = true) @PathParam("voidId") String voidId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("reversalId") String reversalId,
         @ApiParam(value = "The void reversal information.", required = true) VoidReversal voidReversal,
         @Context SecurityContext securityContext,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      asyncResponse.resume(getResourceImplementation().reverseVoid(
            voidId,
            reversalId,
            voidReversal,
            securityContext,
            httpHeaders,
            uriInfo,
            httpServletRequest));
   }
}
