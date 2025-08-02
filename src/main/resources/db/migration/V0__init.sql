CREATE TABLE IF NOT EXISTS address (
   uuid UUID PRIMARY KEY,
   address_line1 VARCHAR(255) NOT NULL,
   address_line2 VARCHAR(255),
   zip_code VARCHAR(20) NOT NULL UNIQUE,
   city VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS customer (
   uuid UUID PRIMARY KEY,
   first_name VARCHAR(50) NOT NULL,
   last_name VARCHAR(50) NOT NULL,
   email VARCHAR(100) NOT NULL UNIQUE CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
   backup_email VARCHAR(100) NOT NULL UNIQUE CHECK (backup_email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
   mobile_phone_number VARCHAR(20) NOT NULL UNIQUE,
   is_active BOOLEAN NOT NULL,
   ssn VARCHAR(20) NOT NULL UNIQUE,
   created_time TIMESTAMP NOT NULL,
   updated_time TIMESTAMP,
   address_uuid UUID,
   CONSTRAINT email_equals_backup_email CHECK (email = backup_email),
   CONSTRAINT fk_customer_address FOREIGN KEY (address_uuid) REFERENCES address(uuid)
);

