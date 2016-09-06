---
title: "Transaction Flows"
menu:
  main:
    weight: 20
---

This section depicts some transaction flows. It is not an exhaustive list of all conceivable flows, but does attempt to illustrate typical scenarios.

When developing a client implementation of the Giftcard Service Interface, Electrum hosts the gift card service and assumes the role of the server. The implementor is then responsible for initiating the gift card service requests and processing the responses received from Electrum. When developing a server implementation, Electrum assumes the role of the client. The implementor is then responsible for servicing the gift card service requests sent by Electrum and generating appropriate responses.

## Request vs Advice Messages

There are two basic message types defined in the Giftcard Service Interface: request and advice type messages. Request messages require a response from an upstream entity before processing can continue. If no response is received then the client cannot determine whether the server successfully received the request and also cannot assume an approved response was sent by the upstream entity. Therefore the client is responsible for ensuring that the request is reversed to ensure that both parties agree on the status of the request.

Advice type messages inform the server of an action or instruction but do not require the client to wait for a response from the server. Advice type messages are sent at suitable intervals until a definite response is received from the upstream entity. Reversals and confirmations are examples of advice type messages.

In order to maintain system consistency, it is important that all advice messages are queued in persistent storage on the gift card service client implementation and repeated until an acknowledgement of receipt is received from the Giftcard Service server implementation. This process is commonly referred to as store-and-forward.

## Dual Messaging

The Giftcard Service Interface is based upon the dual message pattern. Any request which has a financial impact must be followed by either a confirmation or a reversal. The only exception to this is if the initial request was outright declined. In such instances neither a confirmation nor reversal is required. Examples of messages which have financial impact (and must therefore be confirmed or reversed) are Activations, Voids, Loads and Redeems.

The use of a dual message pattern allows a retailer to request an action on the gift card and only require tender from the customer if the requested action was successful. Furthermore, consideration must be made of scenarios wherein the tender process fails after a successful request has already been approved. Therefore, whether the requested action should be reversed or confirmed can only be determined after tender has been completed. This is the purpose of submitting advice messages (reversals and confirmations) after an approved request response has already been received.

Gift card lookup requests have no financial impact and thus neither a confirmation nor a reversal is required following any lookup request.

## Gift Card Activation

In order to activate a gift card an [ActivationRequest](/specification/definitions/#activationrequest) is submitted by the client to the server. The server processes the ActivationRequest and, in the case of a successful activation, returns an [ActivationResponse](/specification/definitions/#activationresponse). At some later time the client submits an advice to either confirm the activation or reverse the activation. This advice message is persisted in a SAF queue and submitted repeatedly at suitable intervals until a response is received.

### Confirmed Activation Flow

The sequence diagram below shows a successful [gift card activation](/specification/operations/#activate) operation which was subsequently confirmed. The activation response payload contains information regarding the gift card which was activated.

![A Successful Activation](/images/sequence-successful-activation.png "A Successful Activation")

Note that once an activation has been confirmed it cannot be reversed.

### Reversed Activation Flow

The sequence diagram below shows a [gift card activation reversal](/specification/operations/#reverseactivation) operation which was subsequently reversed (e.g. the customer could not provide tender for the activation).

![A Reversed Activation](/images/sequence-reversed-activation.png "A Reversed Activation")

Note that once an activation has been reversed it cannot be confirmed.

### Declined Activation Flow

The sequence diagram below shows a declined [gift card activation](/specification/operations/#activate) operation. The response payload contains information regarding the reason for the declined response in the [errorDetail](/specification/definitions/#errordetail) field. Note that since the activation request was declined outright no subsequent confirmation or reversal advice is necessary.

![A Declined Activation](/images/sequence-declined-activation.png "A Declined Activation")

### Timeout Activation Flow

The sequence diagram below shows a [timeout reversed gift card activation](/specification/operations/#reverseactivation) operation which timed out. The server may or may not approve the request. Furthermore the client may receive no response at all or a timeout response. The client therefore cannot determine whether the activation was approved and whether a reversal is required. The client therefore sends a gift card reversal so that all entities are certain of the result of the activation attempt.

![A Timed Out Activation](/images/sequence-timeout-activation.png "A Timed Out Activation")

## Other Financial Transactions

The flows for Loads, Redemptions and Voids follow the same pattern as above for Activations.

## Activations And Loads

Activations and Loads are separate messages within the Giftcard Service Interface. However a common scenario may be to activate a gift card and then immediately load funds onto the gift card. The sequence diagram below shows a successful gift card activation followed by a distinct load request.

![A Distinct Activation And Load](/images/sequence-activation-load.png "A Distinct Activation And Load")

While the above sequence diagram describes a valid flow, an activation is permitted to include an amount to be loaded onto the gift card if it is successfully activated. This spares the expense of the extra load step. Thus the extra load step is not necessary and a simple activation flow may occur as described above.

## Lookup Gift Card

The sequence diagram below shows a [gift card lookup](/specification/operations/#lookupgiftcard) operation. The client submits an HTTP GET request with the card number of the gift card to be retrieved in the URL. The response from the server contains information about the gift card such as the balance of funds which may be redeemed against the gift card.

![A Gift Card Lookup](/images/sequence-giftcard-lookup.png "A Gift Card Lookup")

Note that since the [gift card lookup](/specification/operations/#lookupgiftcard) operation has no financial impact there is no subsequent reversal or confirmation advice message.

## Gift Card Redemptions

The gift card redemption process is subtly different from the Activation process already described above although its flow is the same. Since the redemption request is itself the tender process which typically separates request and advice messages in other flows, the need for a [gift card redemption confirmation](/specification/operations/#confirmredemption) operation may not be clear. However, in order to conform to the pattern of other messages with financial impact, a redemption confirmation advices message is required after an approved gift card redemption request. The sequence diagram below shows a [gift card redemption request](/specification/operations/#redeem) followed by a [gift card redemption confirmation](/specification/operations/#confirmredemption).

![A Gift Card Redemption](/images/sequence-successful-redemption.png "A Gift Card Redemption")
