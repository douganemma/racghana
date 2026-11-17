PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS users (
 id TEXT PRIMARY KEY, email TEXT NOT NULL UNIQUE, password_hash TEXT NOT NULL,
 full_name TEXT NOT NULL, phone TEXT, role TEXT NOT NULL CHECK(role IN ('customer','rental_company','admin')),
 status TEXT NOT NULL DEFAULT 'active' CHECK(status IN ('active','pending','rejected','suspended')),
 created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, updated_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS companies (
 id TEXT PRIMARY KEY, user_id TEXT NOT NULL UNIQUE REFERENCES users(id),
 legal_name TEXT NOT NULL, contact_name TEXT NOT NULL, phone TEXT NOT NULL,
 address TEXT, registration_number TEXT, verification_status TEXT NOT NULL DEFAULT 'pending'
 CHECK(verification_status IN ('pending','approved','rejected','suspended')),
 reviewed_by TEXT REFERENCES users(id), reviewed_at TEXT, rejection_reason TEXT, created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS vehicles (
 id TEXT PRIMARY KEY, company_id TEXT NOT NULL REFERENCES companies(id),
 make TEXT NOT NULL, model TEXT NOT NULL, year INTEGER, category TEXT, transmission TEXT,
 seats INTEGER, daily_rate INTEGER NOT NULL CHECK(daily_rate > 0), currency TEXT NOT NULL DEFAULT 'GHS',
 location TEXT NOT NULL, description TEXT, status TEXT NOT NULL DEFAULT 'pending'
 CHECK(status IN ('pending','approved','rejected','unavailable','archived')),
 created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS vehicle_images (
 id TEXT PRIMARY KEY, vehicle_id TEXT NOT NULL REFERENCES vehicles(id) ON DELETE CASCADE,
 object_key TEXT NOT NULL, sort_order INTEGER NOT NULL DEFAULT 0
);
CREATE TABLE IF NOT EXISTS bookings (
 id TEXT PRIMARY KEY, customer_id TEXT NOT NULL REFERENCES users(id),
 vehicle_id TEXT NOT NULL REFERENCES vehicles(id), company_id TEXT NOT NULL REFERENCES companies(id),
 pickup_at TEXT NOT NULL, return_at TEXT NOT NULL, pickup_location TEXT NOT NULL,
 subtotal INTEGER NOT NULL, fees INTEGER NOT NULL DEFAULT 0, total INTEGER NOT NULL,
 currency TEXT NOT NULL DEFAULT 'GHS',
 status TEXT NOT NULL DEFAULT 'requested' CHECK(status IN ('requested','confirmed','rejected','cancelled','completed')),
 created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
 CHECK(return_at > pickup_at)
);
CREATE TABLE IF NOT EXISTS payments (
 id TEXT PRIMARY KEY, booking_id TEXT NOT NULL REFERENCES bookings(id),
 provider TEXT NOT NULL, provider_reference TEXT UNIQUE, amount INTEGER NOT NULL,
 currency TEXT NOT NULL DEFAULT 'GHS', status TEXT NOT NULL DEFAULT 'pending'
 CHECK(status IN ('pending','successful','failed','refunded')),
 verified_at TEXT, created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS commissions (
 id TEXT PRIMARY KEY, booking_id TEXT NOT NULL UNIQUE REFERENCES bookings(id),
 rate_basis_points INTEGER NOT NULL, amount INTEGER NOT NULL, status TEXT NOT NULL DEFAULT 'pending'
);
CREATE TABLE IF NOT EXISTS sessions (
 id_hash TEXT PRIMARY KEY, user_id TEXT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
 expires_at TEXT NOT NULL, created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS audit_logs (
 id TEXT PRIMARY KEY, actor_id TEXT REFERENCES users(id), action TEXT NOT NULL,
 entity_type TEXT NOT NULL, entity_id TEXT, metadata TEXT, created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_vehicles_company ON vehicles(company_id,status);
CREATE INDEX IF NOT EXISTS idx_bookings_customer ON bookings(customer_id,created_at);
CREATE INDEX IF NOT EXISTS idx_bookings_company ON bookings(company_id,status);
CREATE INDEX IF NOT EXISTS idx_bookings_vehicle_dates ON bookings(vehicle_id,pickup_at,return_at,status);
CREATE INDEX IF NOT EXISTS idx_payments_booking ON payments(booking_id,status);
