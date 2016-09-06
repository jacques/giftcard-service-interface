---
title: "Protocol Basics"
menu:
  main:
    weight: 10
---

The Giftcard Service Interface is an HTTP based protocol. A detailed description of the supported operations and definitions can be found in the [specification](/specification/introduction) section.

## Terminology

Throughout the Giftcard Service Interface documentation various terms are used to describe the different entities which send or receive various requests. These terms are described in more detail in this section.

### Upstream And Downstream Entities

Upstream and downstream impart a convention for the direction for a request and its response. A request message (such as an activation request) typically originates at a merchant location and must be sent to the gift card issuer for authorization. When a message is sent from a merchant to an issuer it is said to be sent upstream. When a message is sent from an issuer to a merchant it is said to be sent downstream. Thus, 'upstream entity' is a relative term and is any entity located between the entity under discussion and the issuer (including the issuer itself). Likewise, 'downstream entity' is also a relative term and is any entity located between the entity under discussion and the merchant (including the merchant itself).

### Server vs Client

Servers typically host an application and, in the context of the RESTful Giftcard Service Interface, a server would host the gift card service application responsible for servicing requests received from downstream entities and providing a response. A server is the entity which receives requests and returns responses. A client therefore is the entity responsible for sending requests to a server and expects responses from the server.

The various request and response messages defined in the Giftcard Service Interface are always initiated from the downstream entities and sent to upstream entities for processing. This means that clients are therefore downstream of servers and that servers are upstream of clients. Consider an entity which receives a request from a downstream entity and forwards it to an upstream entity; this entity receives the request in the capacity of a server and passes it on in the capacity of a client.

## Security

All communication shall be secured by establishing an SSL encrypted transport. SSL provides a manner for client and server systems to identify themselves to each other as well as to establish an encrypted channel over which they may securely communicate. SSL provides security at a network level and identifies entities who communicate to each other. At a minimum server side SSL authentication shall be enforced although both server and client side authentication is performed (mutual authentication).

Since the Giftcard Service Interface is a RESTful service, server implementations are typically hosted on web servers. Using the HTTP Basic Authentication headers over and above SSL allows the sender of a message to be identified at an application level and any appropriate processing to take place on a per-sender basis.


## Failures

The failure outcome of a request shall be determined in the first instance by examining the HTTP status code of the response. The HTTP status types and their associated meanings convey information about the possible reasons for a failure response. Where possible, a failure response will also contain further information about the nature of the failure in an [ErrorDetail](specification/definitions/#errordetail) object.

### Status type

Three basic types of outcomes are possible for transactions, namely: _successful_, _unknown_, and _failed_. HTTP status types are mapped to one of the possible outcomes as indicated below.

HTTP Status Codes               | Status type
--------------------------------|---------------------------------------------------------------------------------------------
200, 201, 202, 404*             | successful
500, 504, timeout               | unknown
400, 404*, 501, 503, all others | failed

A timeout occurs when the client has not received a response to a request after an agreed upon interval. Unless otherwise agreed, this interval shall be 60 seconds. Any response received after the timeout should be logged but ignored.

\*Note that an HTTP status type of 404 could indicate either a success or a failure; this is dependent on the context of the response. This is covered in more detail in the section on [Transaction Flows](/transaction-flows/)

### ErrorDetail and detailMessage

In addition to the HTTP status code, failure response bodies shall contain an [ErrorDetail](/specification/definitions/#errordetail) object if possible to describe the failure in more detail. It should be noted though that responses in some scenarios which are not successful may contain an empty response body and therefore no ErrorDetail object will be present.

If present, an ErrorDetail object may also contain data in the detailMessage field. This data is intended to provide further information useful for investigations. The detailMessage field is not intended to be used for systematic handling of error responses.
