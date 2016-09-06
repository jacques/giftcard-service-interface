---
title: "Getting Started"
menu:
  main:
    weight: 0
---

The Giftcard Service Interface is not yet released. This specification is still undergoing maintenance and should not be used for development until this message is removed. 

The Giftcard Service Interface is an apache licensed RESTful HTTP based messaging protocol, intended to enable interoperability between gift card issuers and retailers.

Using the Giftcard Service Interface enables retailers to access an established and growing group of gift card issuers who support the interface. At the same time, the Giftcard Service Interface is made available to allow gift card issuers a quick, well defined route towards integrating into retailers.

## Supported languages

### Java

The Electrum implementation of the Giftcard Service Interface is written in Java. It is highly recommended that if you are planning a Java implementation of the Giftcard Service Interface you include the [Giftcard Service Interface](https://github.com/electrumpayments/giftcard-service-interface) dependency in your project to save you from having to re-implement the interface. You can find instructions in the project [readme](https://github.com/electrumpayments/giftcard-service-interface).

### Other languages

The Giftcard Service Interface is described as a [swagger (OpenApi) document](/specification/swagger). It is highly recommended that widely available swagger tooling is used to generate a project in your preferred language as a starting point to a new integration project.
