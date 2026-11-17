# RaC Ghana — Multi-vendor car rental marketplace

Mobile-first car rental marketplace for Ghana.

## Deployment
- Live hostname configured: https://racghana.edougan.com
- Cloudflare Worker: `racghana-marketplace`
- Cloudflare D1: `racghana-production`
- Cloudflare R2: `racghana-assets`
- Development branch: `rebuild/mobile-marketplace`

## Implemented in the current deployment
- Customer and rental-company registration
- Secure password hashing (PBKDF2) and HttpOnly session cookies
- Rental-company accounts start pending approval
- Public vehicle catalogue only exposes approved listings from approved companies
- Approved rental companies can submit vehicles for administrator review
- Customer booking requests with date-overlap checks
- Admin endpoints for company and vehicle approvals
- Admin overview metrics
- Mobile-responsive public page and dashboard entry points
- D1 schema for users, companies, vehicles, images, bookings, payments, commissions, sessions, audit logs, documents, favourites, reviews and notifications

## Production launch blockers / remaining work
This is an initial live deployment, not yet a complete production launch. Do not accept real payments or depend on it for operational rentals until these are completed and tested:
- Create the first administrator using the one-time bootstrap endpoint and token stored as a Worker secret.
- Implement booking status transitions and ownership checks for rental-company/customer actions.
- Add vehicle and company-document uploads and review workflow using R2.
- Complete Paystack server-side initialization, signed webhook verification, refunds and reconciliation; production Paystack credentials are not configured.
- Configure transactional email/SMS/WhatsApp notifications.
- Add rate limiting, login throttling, password reset, email verification, CSRF protections and monitoring/alerting.
- Add booking concurrency protection at database level, payment idempotency and end-to-end automated tests.
- Confirm legal pages, privacy policy, rental terms, cancellation/refund rules, company KYC requirements and Ghana tax/accounting treatment.

## Local development
Install Wrangler, then run `npx wrangler dev`. Deploy only after reviewing environment bindings and secrets.

Required bindings are in `wrangler.toml`. Set secrets with Wrangler; never commit credentials.
