--
-- PostgreSQL database dump
--

-- Dumped from database version 15.6 (Postgres.app)
-- Dumped by pg_dump version 15.6 (Postgres.app)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: accesses; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.accesses (
    token character varying(50) NOT NULL,
    user_name character varying(50) NOT NULL,
    start timestamp without time zone DEFAULT now() NOT NULL,
    ends timestamp without time zone,
    application character varying(50) DEFAULT 'HR'::character varying
);


ALTER TABLE public.accesses OWNER TO ubhvhg6k11glmr;

--
-- Name: accounts; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.accounts (
    identifier character varying(50) NOT NULL,
    name character varying(50) NOT NULL,
    address character varying(50),
    postcode character varying(10),
    country character varying(50),
    telephone character varying(20),
    number_of_employees character varying(20),
    users_limit integer DEFAULT 5
);


ALTER TABLE public.accounts OWNER TO ubhvhg6k11glmr;

--
-- Name: annoucement_audience; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.annoucement_audience (
    annoucement_identifier character varying(50) NOT NULL,
    profile_identifier character varying(50) NOT NULL
);


ALTER TABLE public.annoucement_audience OWNER TO ubhvhg6k11glmr;

--
-- Name: annoucement_comments; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.annoucement_comments (
    identifier character varying(50) NOT NULL,
    annoucement_identifier character varying(50) NOT NULL,
    profile_identifier character varying(50) NOT NULL,
    date_created timestamp without time zone DEFAULT now(),
    comment character varying
);


ALTER TABLE public.annoucement_comments OWNER TO ubhvhg6k11glmr;

--
-- Name: annoucement_group; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.annoucement_group (
    annoucement_identifier character varying(50) NOT NULL,
    group_identifier character varying(50) NOT NULL
);


ALTER TABLE public.annoucement_group OWNER TO ubhvhg6k11glmr;

--
-- Name: annoucements; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.annoucements (
    identifier character varying(50) NOT NULL,
    body character varying,
    topic character varying(50),
    draft boolean DEFAULT false,
    profile_identifier character varying(50),
    audience character varying(50),
    email_notification boolean DEFAULT false,
    ping_to_the_top boolean DEFAULT false,
    date_created timestamp without time zone DEFAULT now()
);


ALTER TABLE public.annoucements OWNER TO ubhvhg6k11glmr;

--
-- Name: contracts; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.contracts (
    identifier character varying(50) NOT NULL,
    start_date character varying(30) NOT NULL,
    cont_service character varying(30) NOT NULL,
    contract_end_date timestamp without time zone,
    onboard_date timestamp without time zone,
    onboard_contract character varying(50),
    benefits_start character varying(30),
    review_date timestamp without time zone,
    job_type character varying(120),
    country character varying(100),
    office_role character varying(120),
    location character varying(100),
    department character varying(120),
    team character varying(120),
    cost_centre character varying(120),
    line_manager character varying(50),
    right_to_work character varying(100),
    profile_identifier character varying(50),
    length_of_service character varying(50),
    notice_period character varying(50),
    hours_per_week character varying(3),
    days_per_week character varying(3),
    fte character varying(3),
    work_pattern character varying(3),
    my_line_manager character varying(50),
    right_to_work_expires character varying(30),
    holiday_entitlement character varying(4),
    holiday_brought_forward character varying(4)
);


ALTER TABLE public.contracts OWNER TO ubhvhg6k11glmr;

--
-- Name: documents; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.documents (
    identifier character varying(50) NOT NULL,
    img character varying NOT NULL,
    profile_identifier character varying(50) NOT NULL,
    name character varying(200) NOT NULL,
    date_created date DEFAULT ('now'::text)::date NOT NULL,
    status character varying(20) DEFAULT 'ACTIVE'::character varying
);


ALTER TABLE public.documents OWNER TO ubhvhg6k11glmr;

--
-- Name: equipments; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.equipments (
    identifier character varying(50) NOT NULL,
    asset_reference character varying(300),
    serial_number character varying(100),
    item character varying(300),
    profile_identifier character varying(50)
);


ALTER TABLE public.equipments OWNER TO ubhvhg6k11glmr;

--
-- Name: event_attendee; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.event_attendee (
    event_identifier character varying(50) NOT NULL,
    profile_identifier character varying(50) NOT NULL,
    status character varying(20)
);


ALTER TABLE public.event_attendee OWNER TO ubhvhg6k11glmr;

--
-- Name: events; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.events (
    identifier character varying(50) NOT NULL,
    start timestamp without time zone NOT NULL,
    end_date timestamp without time zone NOT NULL,
    description character varying,
    location character varying,
    url character varying,
    label character varying(20),
    status character varying(20),
    profile_identifier character varying(50) NOT NULL,
    date_created date DEFAULT ('now'::text)::date NOT NULL,
    mod_date date DEFAULT ('now'::text)::date NOT NULL,
    includesaturday boolean DEFAULT false,
    includesunday boolean DEFAULT false,
    all_day boolean DEFAULT false,
    title character varying(300)
);


ALTER TABLE public.events OWNER TO ubhvhg6k11glmr;

--
-- Name: goal_performance_review; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.goal_performance_review (
    goal_identifier character varying(50) NOT NULL,
    performance_review_identifier character varying(50) NOT NULL
);


ALTER TABLE public.goal_performance_review OWNER TO ubhvhg6k11glmr;

--
-- Name: goals; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.goals (
    identifier character varying(50) NOT NULL,
    name character varying(200) NOT NULL,
    target character varying(200),
    deadline date NOT NULL,
    profile_identifier character varying(50) NOT NULL,
    progress integer DEFAULT 0,
    description character varying
);


ALTER TABLE public.goals OWNER TO ubhvhg6k11glmr;

--
-- Name: groups; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.groups (
    identifier character varying(50) NOT NULL,
    name character varying(200),
    type character varying(30),
    description character varying,
    account_identifier character varying(50)
);


ALTER TABLE public.groups OWNER TO ubhvhg6k11glmr;

--
-- Name: holidays; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.holidays (
    identifier character varying(50) NOT NULL,
    start timestamp without time zone NOT NULL,
    end_date timestamp without time zone NOT NULL,
    comments character varying,
    type character varying(20),
    status character varying(20),
    profile_identifier character varying(50) NOT NULL,
    authorized_by character varying(50),
    date_created date DEFAULT now(),
    mod_date date DEFAULT now(),
    includesaturday boolean DEFAULT false,
    includesunday boolean DEFAULT false,
    halfdaystart boolean DEFAULT false,
    halfdayend boolean DEFAULT false,
    draft boolean DEFAULT false
);


ALTER TABLE public.holidays OWNER TO ubhvhg6k11glmr;

--
-- Name: invoice_item; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.invoice_item (
    identifier character varying(50) NOT NULL,
    nominal_code character varying(20),
    line_ref character varying(100),
    vat character varying(20),
    quantity integer DEFAULT 1 NOT NULL,
    amount numeric DEFAULT 0 NOT NULL,
    invoice_identifier character varying(50),
    amount_type character varying(10)
);


ALTER TABLE public.invoice_item OWNER TO ubhvhg6k11glmr;

--
-- Name: invoice_payment; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.invoice_payment (
    invoice_identifier character varying(50) NOT NULL,
    payment_identifier character varying(50) NOT NULL
);


ALTER TABLE public.invoice_payment OWNER TO ubhvhg6k11glmr;

--
-- Name: invoices; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.invoices (
    identifier character varying(50) NOT NULL,
    date date,
    due_date date,
    ref character varying(300),
    invoice_number character varying(50),
    contact character varying(50),
    project character varying(100),
    account_identifier character varying(50),
    status character varying(10),
    outstanding numeric DEFAULT 0
);


ALTER TABLE public.invoices OWNER TO ubhvhg6k11glmr;

--
-- Name: leads; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.leads (
    identifier character varying(50) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    company character varying(100),
    email character varying(100) NOT NULL,
    phone_number character varying(20),
    website character varying(100),
    location character varying(100),
    industry character varying(50),
    lead_source character varying(50),
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL,
    profile_identifier character varying(50) NOT NULL,
    account_identifier character varying(50) NOT NULL,
    comments text
);


ALTER TABLE public.leads OWNER TO ubhvhg6k11glmr;

--
-- Name: notifications; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.notifications (
    identifier character varying(50) NOT NULL,
    profile_identifier character varying(50) NOT NULL,
    message character varying,
    "time" timestamp without time zone,
    deleted boolean DEFAULT false,
    owner_identifier character varying(50) NOT NULL,
    unread boolean DEFAULT true
);


ALTER TABLE public.notifications OWNER TO ubhvhg6k11glmr;

--
-- Name: organization_chart; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.organization_chart (
    parent_identifier character varying(50),
    child_identifier character varying(50),
    relationship_type character varying(30),
    authorize_holiday boolean DEFAULT false
);


ALTER TABLE public.organization_chart OWNER TO ubhvhg6k11glmr;

--
-- Name: payments; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.payments (
    identifier character varying(50) NOT NULL,
    amount numeric DEFAULT 0 NOT NULL,
    contact character varying(200),
    ref character varying(200),
    date date,
    type character varying(50),
    bank_account character varying(50)
);


ALTER TABLE public.payments OWNER TO ubhvhg6k11glmr;

--
-- Name: performance_reviews; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.performance_reviews (
    identifier character varying NOT NULL,
    employee_identifier character varying(50) NOT NULL,
    reviewer_identifier character varying(50) NOT NULL,
    comments character varying,
    reviewdate date
);


ALTER TABLE public.performance_reviews OWNER TO ubhvhg6k11glmr;

--
-- Name: profile_group; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.profile_group (
    profile_identifier character varying(50) NOT NULL,
    group_identifier character varying(50) NOT NULL
);


ALTER TABLE public.profile_group OWNER TO ubhvhg6k11glmr;

--
-- Name: profile_role; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.profile_role (
    profile_identifier character varying(50) NOT NULL,
    role_key character varying(30) NOT NULL
);


ALTER TABLE public.profile_role OWNER TO ubhvhg6k11glmr;

--
-- Name: profiles; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.profiles (
    identifier character varying(50) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50),
    email character varying(50) NOT NULL,
    deleted boolean DEFAULT false,
    account_identifier character varying(50),
    dob date,
    entitlement_absence integer DEFAULT 0,
    avatar character varying(500),
    title character varying(10),
    known_as character varying(50),
    address character varying(300),
    gender character varying(30),
    gender_identity character varying(100),
    preferred_pronoun character varying(20),
    marital_status character varying(30),
    employee_number integer DEFAULT 0,
    work_phone character varying(20),
    work_extn character varying(20),
    work_mobile character varying(20),
    personal_email character varying(100),
    personal_mobile character varying(20),
    home_phone character varying(20),
    online boolean DEFAULT false
);


ALTER TABLE public.profiles OWNER TO ubhvhg6k11glmr;

--
-- Name: role_positions; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.role_positions (
    identifier character varying(50) NOT NULL,
    requested_by character varying(50) NOT NULL,
    grade character varying(10),
    salary_level character varying(50),
    job_description character varying,
    contract_type character varying(50),
    date_created date DEFAULT now(),
    start_date date DEFAULT now(),
    header character varying(400) NOT NULL,
    status character varying(20) DEFAULT 'REQUESTED'::character varying,
    assigned character varying(50),
    log character varying,
    file character varying(2000),
    file_name character varying(300)
);


ALTER TABLE public.role_positions OWNER TO ubhvhg6k11glmr;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.roles (
    key character varying(30) NOT NULL,
    description character varying
);


ALTER TABLE public.roles OWNER TO ubhvhg6k11glmr;

--
-- Name: salary_levels; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.salary_levels (
    identifier character varying(50) NOT NULL,
    account_identifier character varying(50) NOT NULL,
    name character varying(50),
    min character varying(10),
    max character varying(10),
    description text
);


ALTER TABLE public.salary_levels OWNER TO ubhvhg6k11glmr;

--
-- Name: settings; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.settings (
    identifier character varying(50) NOT NULL,
    mailsmtphost character varying(300),
    mailsmtpport character varying(10),
    mailsmtpauth boolean DEFAULT true,
    mailsmtpstarttlsenable boolean DEFAULT true,
    mailusername character varying(50),
    mailpassword character varying(100),
    linkelntoken character varying(300),
    mailsmtpsslenable boolean DEFAULT true
);


ALTER TABLE public.settings OWNER TO ubhvhg6k11glmr;

--
-- Name: shareddocument_profile; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.shareddocument_profile (
    profile_identifier character varying(50) NOT NULL,
    document_identifier character varying(50) NOT NULL
);


ALTER TABLE public.shareddocument_profile OWNER TO ubhvhg6k11glmr;

--
-- Name: supporttickets; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.supporttickets (
    identifier character varying(50) NOT NULL,
    title character varying(300) NOT NULL,
    body character varying,
    profile_identifier character varying(50) NOT NULL,
    account_identifier character varying(50) NOT NULL,
    status character varying(30) DEFAULT 'CREATED'::character varying,
    answer character varying,
    date timestamp without time zone,
    type character varying(50),
    moddate timestamp without time zone
);


ALTER TABLE public.supporttickets OWNER TO ubhvhg6k11glmr;

--
-- Name: todo; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.todo (
    identifier character varying(50) NOT NULL,
    profile_identifier character varying(50) NOT NULL,
    type character varying(20),
    text character varying,
    done boolean DEFAULT false,
    read boolean DEFAULT false,
    date_created date DEFAULT now(),
    mod_date date DEFAULT now(),
    status character varying(30),
    title character varying(200)
);


ALTER TABLE public.todo OWNER TO ubhvhg6k11glmr;

--
-- Name: users; Type: TABLE; Schema: public; Owner: ubhvhg6k11glmr
--

CREATE TABLE public.users (
    user_name character varying(50) NOT NULL,
    password character varying(30)
);


ALTER TABLE public.users OWNER TO ubhvhg6k11glmr;

--
-- Name: accesses accesses_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.accesses
    ADD CONSTRAINT accesses_pkey PRIMARY KEY (token);


--
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (identifier);


--
-- Name: annoucement_audience annoucement_audience_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.annoucement_audience
    ADD CONSTRAINT annoucement_audience_pkey PRIMARY KEY (annoucement_identifier, profile_identifier);


--
-- Name: annoucement_comments annoucement_comments_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.annoucement_comments
    ADD CONSTRAINT annoucement_comments_pkey PRIMARY KEY (identifier);


--
-- Name: annoucement_group annoucement_group_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.annoucement_group
    ADD CONSTRAINT annoucement_group_pkey PRIMARY KEY (annoucement_identifier, group_identifier);


--
-- Name: annoucements annoucements_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.annoucements
    ADD CONSTRAINT annoucements_pkey PRIMARY KEY (identifier);


--
-- Name: contracts contracts_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.contracts
    ADD CONSTRAINT contracts_pkey PRIMARY KEY (identifier);


--
-- Name: documents documents_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_pkey PRIMARY KEY (identifier);


--
-- Name: equipments equipments_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.equipments
    ADD CONSTRAINT equipments_pkey PRIMARY KEY (identifier);


--
-- Name: event_attendee event_attendee_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_pkey PRIMARY KEY (event_identifier, profile_identifier);


--
-- Name: events events_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (identifier);


--
-- Name: goal_performance_review goals_performance_reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.goal_performance_review
    ADD CONSTRAINT goals_performance_reviews_pkey PRIMARY KEY (goal_identifier, performance_review_identifier);


--
-- Name: goals goals_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.goals
    ADD CONSTRAINT goals_pkey PRIMARY KEY (identifier);


--
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (identifier);


--
-- Name: holidays holidays_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.holidays
    ADD CONSTRAINT holidays_pkey PRIMARY KEY (identifier);


--
-- Name: invoice_item invoice_item_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.invoice_item
    ADD CONSTRAINT invoice_item_pkey PRIMARY KEY (identifier);


--
-- Name: invoice_payment invoice_payment_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.invoice_payment
    ADD CONSTRAINT invoice_payment_pkey PRIMARY KEY (invoice_identifier, payment_identifier);


--
-- Name: invoices invoices_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_pkey PRIMARY KEY (identifier);


--
-- Name: leads leads_email_key; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT leads_email_key UNIQUE (email);


--
-- Name: leads leads_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT leads_pkey PRIMARY KEY (identifier);


--
-- Name: notifications notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (identifier);


--
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (identifier);


--
-- Name: performance_reviews performance_reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.performance_reviews
    ADD CONSTRAINT performance_reviews_pkey PRIMARY KEY (identifier);


--
-- Name: profile_group profile_group_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.profile_group
    ADD CONSTRAINT profile_group_pkey PRIMARY KEY (profile_identifier, group_identifier);


--
-- Name: profile_role profile_role_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.profile_role
    ADD CONSTRAINT profile_role_pkey PRIMARY KEY (profile_identifier, role_key);


--
-- Name: profiles profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_pkey PRIMARY KEY (identifier);


--
-- Name: role_positions role_positions_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.role_positions
    ADD CONSTRAINT role_positions_pkey PRIMARY KEY (identifier);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (key);


--
-- Name: salary_levels salary_levels_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.salary_levels
    ADD CONSTRAINT salary_levels_pkey PRIMARY KEY (identifier);


--
-- Name: settings settings_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_pkey PRIMARY KEY (identifier);


--
-- Name: shareddocument_profile shareddocument_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.shareddocument_profile
    ADD CONSTRAINT shareddocument_profile_pkey PRIMARY KEY (profile_identifier, document_identifier);


--
-- Name: supporttickets supporttickets_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.supporttickets
    ADD CONSTRAINT supporttickets_pkey PRIMARY KEY (identifier);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_name);


--
-- Name: annoucement_audience annoucement_audience_annoucement_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.annoucement_audience
    ADD CONSTRAINT annoucement_audience_annoucement_identifier_fkey FOREIGN KEY (annoucement_identifier) REFERENCES public.annoucements(identifier);


--
-- Name: annoucement_audience annoucement_audience_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.annoucement_audience
    ADD CONSTRAINT annoucement_audience_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: annoucement_comments annoucement_comments_annoucement_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.annoucement_comments
    ADD CONSTRAINT annoucement_comments_annoucement_identifier_fkey FOREIGN KEY (annoucement_identifier) REFERENCES public.annoucements(identifier);


--
-- Name: annoucement_comments annoucement_comments_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.annoucement_comments
    ADD CONSTRAINT annoucement_comments_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: annoucement_group annoucement_group_annoucement_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.annoucement_group
    ADD CONSTRAINT annoucement_group_annoucement_identifier_fkey FOREIGN KEY (annoucement_identifier) REFERENCES public.annoucements(identifier);


--
-- Name: annoucement_group annoucement_group_group_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.annoucement_group
    ADD CONSTRAINT annoucement_group_group_identifier_fkey FOREIGN KEY (group_identifier) REFERENCES public.groups(identifier);


--
-- Name: contracts contracts_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.contracts
    ADD CONSTRAINT contracts_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: documents documents_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: event_attendee event_attendee_event_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_event_identifier_fkey FOREIGN KEY (event_identifier) REFERENCES public.events(identifier);


--
-- Name: event_attendee event_attendee_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: events events_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: goal_performance_review goals_performance_reviews_goal_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.goal_performance_review
    ADD CONSTRAINT goals_performance_reviews_goal_identifier_fkey FOREIGN KEY (goal_identifier) REFERENCES public.goals(identifier);


--
-- Name: goal_performance_review goals_performance_reviews_performance_review_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.goal_performance_review
    ADD CONSTRAINT goals_performance_reviews_performance_review_identifier_fkey FOREIGN KEY (performance_review_identifier) REFERENCES public.performance_reviews(identifier);


--
-- Name: goals goals_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.goals
    ADD CONSTRAINT goals_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: holidays holidays_authorized_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.holidays
    ADD CONSTRAINT holidays_authorized_by_fkey FOREIGN KEY (authorized_by) REFERENCES public.profiles(identifier);


--
-- Name: holidays holidays_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.holidays
    ADD CONSTRAINT holidays_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: invoice_item invoice_item_invoice_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.invoice_item
    ADD CONSTRAINT invoice_item_invoice_identifier_fkey FOREIGN KEY (invoice_identifier) REFERENCES public.invoices(identifier);


--
-- Name: invoice_payment invoice_payment_invoice_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.invoice_payment
    ADD CONSTRAINT invoice_payment_invoice_identifier_fkey FOREIGN KEY (invoice_identifier) REFERENCES public.invoices(identifier);


--
-- Name: invoice_payment invoice_payment_payment_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.invoice_payment
    ADD CONSTRAINT invoice_payment_payment_identifier_fkey FOREIGN KEY (payment_identifier) REFERENCES public.payments(identifier);


--
-- Name: invoices invoices_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: leads leads_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT leads_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: leads leads_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT leads_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: notifications notifications_owner_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_owner_identifier_fkey FOREIGN KEY (owner_identifier) REFERENCES public.profiles(identifier);


--
-- Name: notifications notifications_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: organization_chart organization_chart_child_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.organization_chart
    ADD CONSTRAINT organization_chart_child_identifier_fkey FOREIGN KEY (child_identifier) REFERENCES public.profiles(identifier);


--
-- Name: organization_chart organization_chart_parent_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.organization_chart
    ADD CONSTRAINT organization_chart_parent_identifier_fkey FOREIGN KEY (parent_identifier) REFERENCES public.profiles(identifier);


--
-- Name: performance_reviews performance_reviews_employee_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.performance_reviews
    ADD CONSTRAINT performance_reviews_employee_identifier_fkey FOREIGN KEY (employee_identifier) REFERENCES public.profiles(identifier);


--
-- Name: performance_reviews performance_reviews_reviewer_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.performance_reviews
    ADD CONSTRAINT performance_reviews_reviewer_identifier_fkey FOREIGN KEY (reviewer_identifier) REFERENCES public.profiles(identifier);


--
-- Name: profile_group profile_group_group_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.profile_group
    ADD CONSTRAINT profile_group_group_identifier_fkey FOREIGN KEY (group_identifier) REFERENCES public.groups(identifier);


--
-- Name: profile_group profile_group_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.profile_group
    ADD CONSTRAINT profile_group_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: profile_group profile_group_profile_identifier_fkey1; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.profile_group
    ADD CONSTRAINT profile_group_profile_identifier_fkey1 FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: profile_role profile_role_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.profile_role
    ADD CONSTRAINT profile_role_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: profile_role profile_role_role_key_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.profile_role
    ADD CONSTRAINT profile_role_role_key_fkey FOREIGN KEY (role_key) REFERENCES public.roles(key);


--
-- Name: profiles profiles_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: role_positions role_positions_assigned_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.role_positions
    ADD CONSTRAINT role_positions_assigned_fkey FOREIGN KEY (assigned) REFERENCES public.profiles(identifier);


--
-- Name: role_positions role_positions_requested_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.role_positions
    ADD CONSTRAINT role_positions_requested_by_fkey FOREIGN KEY (requested_by) REFERENCES public.profiles(identifier);


--
-- Name: salary_levels salary_levels_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.salary_levels
    ADD CONSTRAINT salary_levels_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: settings settings_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_identifier_fkey FOREIGN KEY (identifier) REFERENCES public.accounts(identifier);


--
-- Name: shareddocument_profile shareddocument_profile_document_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.shareddocument_profile
    ADD CONSTRAINT shareddocument_profile_document_identifier_fkey FOREIGN KEY (document_identifier) REFERENCES public.documents(identifier);


--
-- Name: shareddocument_profile shareddocument_profile_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.shareddocument_profile
    ADD CONSTRAINT shareddocument_profile_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: supporttickets supporttickets_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.supporttickets
    ADD CONSTRAINT supporttickets_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: supporttickets supporttickets_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubhvhg6k11glmr
--

ALTER TABLE ONLY public.supporttickets
    ADD CONSTRAINT supporttickets_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: pg_database_owner
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO ubhvhg6k11glmr;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

