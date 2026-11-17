# RaC Ghana marketplace rebuild

Mobile-first multi-vendor car rental marketplace for Ghana.

## Current branch
`rebuild/mobile-marketplace`

## Prototype scope
- Public car discovery and booking entry points
- Customer, rental company and administrator dashboard shells
- Responsive desktop and phone layouts
- Rental company approval workflow states represented in UI

## Important implementation status
This branch is a front-end prototype. Authentication, database persistence, document verification, vehicle availability locking, payment processing, notifications and role-enforced APIs are not yet implemented. Do not use it to accept real bookings or payments.

## Planned production architecture
- Cloudflare Workers + Static Assets for the app/API
- D1 for relational marketplace records
- R2 for vehicle and verification-document storage
- Cloudflare Turnstile for abuse prevention
- Payment gateway integration through a verified server-side provider adapter (Paystack plus supported Ghana mobile money/card rails)
- Server-side role and ownership checks for customer, rental-company and admin access

Production deployment to racghana.com is intentionally not changed by this prototype branch.
