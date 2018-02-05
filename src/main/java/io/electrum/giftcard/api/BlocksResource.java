package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.*;
import io.electrum.vas.model.BasicAdviceResponse;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.*;

@Path("/blocks")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(description = "the blocks API")
public abstract class BlocksResource {

   protected abstract IBlocksResource getResourceImplementation();

   @POST
   @Path("/{blockId}/confirmations/{confirmationId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Confirm a block against a gift card.", notes = "The Block Confirmations endpoint "
         + "registers the confirmation of a prior block on a gift card. Block confirmations are advice type "
         + "messages and should continue to be sent at suitable intervals until a response has been received. Multiple "
         + "confirmation advices may be sent which refer to the same block request. The net result is that the "
         + "block is confirmed once.", authorizations = {
         @Authorization(value = "httpBasic") }, tags = { "Confirmations", "Blocks", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = BasicAdviceResponse.class),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void confirmBlock(
         @ApiParam(value = "The randomly generated blockId UUID as sent in the original block.", required = true) @PathParam("blockId") String blockId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("confirmationId") String confirmationId,
         @ApiParam(value = "The block confirmation information.", required = true) BlockConfirmation blockConfirmation,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().confirmBlock(
            blockId,
            confirmationId,
            blockConfirmation,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path("/{blockId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Request to block a gift card.", notes = "The Blocks endpoint "
         + "allows gift cards to be blocked. A block request is not considered "
         + "complete until a block confirmation or block reversal has been sent and "
         + "acknowledged.", authorizations = {
         @Authorization(value = "httpBasic") }, tags = { "Blocks", })
   @ApiResponses(value = {
         @ApiResponse(code = 201, message = "Created", response = BlockResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created load resource", response = String.class) }),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void block(
         @ApiParam(value = "The randomly generated UUID identifying this block request, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("blockId") String blockId,
         @ApiParam(value = "The block request information.", required = true) BlockRequest blockRequest,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().block(
            blockId,
            blockRequest,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }


   @POST
   @Path("/{blockId}/reversals/{reversalId}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Simplistically, a block reversal undoes a block on a card if the block "
         + "request was successfully processed.", notes = "The Block Reversals endpoint allows "
         + "a block on a gift card to be reversed. If the sender of a block request "
         + "is uncertain of the state of a block request then the sender must send a "
         + "block reversal. Reversals should continue to be sent at suitable intervals "
         + "until a response has been received. Multiple reversals may be sent which refer to "
         + "the same block request. The net result is that the block is reversed once.", authorizations = {
         @Authorization(value = "httpBasic") }, tags = { "Blocks", "Reversals", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = BasicAdviceResponse.class),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void reverseBlock(
         @ApiParam(value = "The randomly generated blockId UUID as sent in the original block request.", required = true) @PathParam("blockId") String blockId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("reversalId") String reversalId,
         @ApiParam(value = "The block reversal information.", required = true) BlockReversal blockReversal,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().reverseBlock(
            blockId,
            reversalId,
            blockReversal,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

}
