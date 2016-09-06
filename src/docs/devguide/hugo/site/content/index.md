---
title: Giftcard Service Interface
type: index
---

[Electrum Payments](http://electrum.io) connects businesses together to transact with each other easily and robustly. This Giftcard Service Interface documents the messaging interface used by Electrum to connect acquirers, processors, and gift card issuers together for performing gift card transactions.

Gift card issuers allow customers to purchase gift cards which can then be used as a form of tender when purchasing products from a merchant. Gift cards are sold by merchants as a product. When a gift card is purchased it is first activated on the issuer's system. Activated gift cards can then be loaded thereby placing funds into an account associated with the card. When used as a form of tender the gift card is redeemed and funds are deducted from the account associated with the card. Once a card has been activated it may be voided at some later time thereby preventing the card from being used as a form of tender or loaded again.

Merchants and gift card issuers subsequently perform settlement and reconciliation operations to ensure that the necessary funds are transferred between parties interacting in the gift card system. This settlement and reconciliation process is outside the scope of the Giftcard Service Interface.
