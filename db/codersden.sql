--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.25
-- Dumped by pg_dump version 9.5.25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: accesses; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.accesses (
    token character varying(50) NOT NULL,
    user_name character varying(50) NOT NULL,
    start timestamp without time zone DEFAULT now() NOT NULL,
    ends timestamp without time zone,
    application character varying(50) DEFAULT 'HR'::character varying
);


ALTER TABLE public.accesses OWNER TO mvelasco;

--
-- Name: accounts; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.accounts OWNER TO mvelasco;

--
-- Name: annoucement_audience; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.annoucement_audience (
    annoucement_identifier character varying(50) NOT NULL,
    profile_identifier character varying(50) NOT NULL
);


ALTER TABLE public.annoucement_audience OWNER TO mvelasco;

--
-- Name: annoucement_comments; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.annoucement_comments (
    identifier character varying(50) NOT NULL,
    annoucement_identifier character varying(50) NOT NULL,
    profile_identifier character varying(50) NOT NULL,
    date_created timestamp without time zone DEFAULT now(),
    comment character varying
);


ALTER TABLE public.annoucement_comments OWNER TO mvelasco;

--
-- Name: annoucement_group; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.annoucement_group (
    annoucement_identifier character varying(50) NOT NULL,
    group_identifier character varying(50) NOT NULL
);


ALTER TABLE public.annoucement_group OWNER TO mvelasco;

--
-- Name: annoucements; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.annoucements OWNER TO mvelasco;

--
-- Name: contracts; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.contracts OWNER TO mvelasco;

--
-- Name: documents; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.documents (
    identifier character varying(50) NOT NULL,
    img character varying NOT NULL,
    profile_identifier character varying(50) NOT NULL,
    name character varying(200) NOT NULL,
    date_created date DEFAULT ('now'::text)::date NOT NULL,
    status character varying(20) DEFAULT 'ACTIVE'::character varying
);


ALTER TABLE public.documents OWNER TO mvelasco;

--
-- Name: event_attendee; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.event_attendee (
    event_identifier character varying(50) NOT NULL,
    profile_identifier character varying(50) NOT NULL,
    status character varying(20)
);


ALTER TABLE public.event_attendee OWNER TO mvelasco;

--
-- Name: events; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.events OWNER TO mvelasco;

--
-- Name: goal_performance_review; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.goal_performance_review (
    goal_identifier character varying(50) NOT NULL,
    performance_review_identifier character varying(50) NOT NULL
);


ALTER TABLE public.goal_performance_review OWNER TO mvelasco;

--
-- Name: goals; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.goals OWNER TO mvelasco;

--
-- Name: groups; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.groups (
    identifier character varying(50) NOT NULL,
    name character varying(200),
    type character varying(30),
    description character varying,
    account_identifier character varying(50)
);


ALTER TABLE public.groups OWNER TO mvelasco;

--
-- Name: holidays; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.holidays OWNER TO mvelasco;

--
-- Name: invoice_item; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.invoice_item OWNER TO mvelasco;

--
-- Name: invoice_payment; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.invoice_payment (
    invoice_identifier character varying(50) NOT NULL,
    payment_identifier character varying(50) NOT NULL
);


ALTER TABLE public.invoice_payment OWNER TO mvelasco;

--
-- Name: invoices; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.invoices OWNER TO mvelasco;

--
-- Name: leads; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.leads OWNER TO mvelasco;

--
-- Name: notifications; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.notifications OWNER TO mvelasco;

--
-- Name: organization_chart; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.organization_chart (
    parent_identifier character varying(50),
    child_identifier character varying(50),
    relationship_type character varying(30),
    authorize_holiday boolean DEFAULT false
);


ALTER TABLE public.organization_chart OWNER TO mvelasco;

--
-- Name: payments; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.payments OWNER TO mvelasco;

--
-- Name: performance_reviews; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.performance_reviews (
    identifier character varying NOT NULL,
    employee_identifier character varying(50) NOT NULL,
    reviewer_identifier character varying(50) NOT NULL,
    comments character varying,
    reviewdate date
);


ALTER TABLE public.performance_reviews OWNER TO mvelasco;

--
-- Name: profile_group; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.profile_group (
    profile_identifier character varying(50) NOT NULL,
    group_identifier character varying(50) NOT NULL
);


ALTER TABLE public.profile_group OWNER TO mvelasco;

--
-- Name: profile_role; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.profile_role (
    profile_identifier character varying(50) NOT NULL,
    role_key character varying(30) NOT NULL
);


ALTER TABLE public.profile_role OWNER TO mvelasco;

--
-- Name: profiles; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.profiles OWNER TO mvelasco;

--
-- Name: role_positions; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.role_positions OWNER TO mvelasco;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.roles (
    key character varying(30) NOT NULL,
    description character varying
);


ALTER TABLE public.roles OWNER TO mvelasco;

--
-- Name: settings; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.settings (
    identifier character varying(50) NOT NULL,
    mailsmtphost character varying(300),
    mailsmtpport character varying(10),
    mailsmtpauth boolean DEFAULT true,
    mailsmtpstarttlsenable boolean DEFAULT true,
    mailusername character varying(50),
    mailpassword character varying(100),
    linkelntoken character varying(300)
);


ALTER TABLE public.settings OWNER TO mvelasco;

--
-- Name: shareddocument_profile; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.shareddocument_profile (
    profile_identifier character varying(50) NOT NULL,
    document_identifier character varying(50) NOT NULL
);


ALTER TABLE public.shareddocument_profile OWNER TO mvelasco;

--
-- Name: supporttickets; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.supporttickets OWNER TO mvelasco;

--
-- Name: todo; Type: TABLE; Schema: public; Owner: mvelasco
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


ALTER TABLE public.todo OWNER TO mvelasco;

--
-- Name: users; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.users (
    user_name character varying(50) NOT NULL,
    password character varying(30)
);


ALTER TABLE public.users OWNER TO mvelasco;

--
-- Data for Name: accesses; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.accesses (token, user_name, start, ends, application) FROM stdin;
060fd7d8-c7df-43c3-8471-255573ba94d6	florence@ndthemachine.com	2024-01-21 16:40:57.277	2024-01-21 16:41:39.252	HR
169e1202-4a00-46ad-b21f-7ea3442a0d9d	florence@ndthemachine.com	2024-01-21 16:41:49.798	2024-01-21 16:43:35.471	HR
07aaa77f-61e0-410b-b074-e770ed561b69	florence@ndthemachine.com	2024-01-21 16:53:15.325	2024-01-21 16:54:14.22	HR
4cb471b0-8d28-45b6-b832-1642bcc82203	mariano.juri@yahoo.co.uk	2024-01-21 16:54:27.076	2024-01-22 11:45:31.69	HR
0724c779-b516-40a2-8866-b1ab06ef1739	florence@ndthemachine.com	2024-01-22 11:45:38.97	2024-01-22 11:46:57.473	HR
be73cee5-e330-4c17-83aa-9923f166b157	mariano.juri@yahoo.co.uk	2024-01-22 11:47:27.779	2024-01-22 16:03:21.891	HR
19a7f829-2727-4080-bd8e-72b92e4c1955	florence@ndthemachine.com	2024-01-22 16:03:29.896	2024-01-22 16:06:01.136	HR
eefe0697-d9a7-4f16-9b74-b49b2a8558c3	florence@ndthemachine.com	2024-01-22 16:06:08.286	2024-01-22 16:06:24.32	HR
43ae17e7-5b69-4b5d-9838-6f04c37a3cbf	mariano.juri@yahoo.co.uk	2024-01-22 16:06:40.572	2024-01-22 16:13:43.521	HR
3d7fa8b1-a7d0-49c6-8b5c-5ffc8825e79e	florence@ndthemachine.com	2024-01-22 16:13:52.746	2024-01-22 16:15:19.665	HR
bf897f1d-d5c0-4d8c-9a89-bf45b1bb5770	florence@ndthemachine.com	2024-01-22 16:15:24.673	2024-01-29 11:44:50.936	HR
e3aae828-cd55-47c4-b396-1816d92c2bad	florence@ndthemachine.com	2024-01-29 11:44:55.887	2024-01-29 11:45:27.748	HR
a942eea7-7732-4b0e-bef4-7cfaf7ae890d	mariano.juri@yahoo.co.uk	2024-01-29 11:45:37.621	2024-01-29 11:46:16.26	HR
7693b787-b26d-4c50-93cf-a61b0fc2bcc5	mariano.juri@yahoo.co.uk	2024-01-29 11:48:27.789	2024-01-29 11:48:47.839	HR
91f6bd07-0da7-4f83-9685-4df540fa61fd	florence@ndthemachine.com	2024-01-29 11:48:53.413	2024-01-29 15:05:58.432	HR
e35e60ea-8717-44ab-abb5-65af72e9e474	mariano.juri@yahoo.co.uk	2024-01-29 15:06:09.44	2024-01-29 15:11:06.241	HR
af17e81d-1395-496a-a3a6-868c2933f74d	florence@ndthemachine.com	2024-01-29 15:11:14.477	2024-01-29 15:14:07.94	HR
e909a5a5-1240-4409-8183-36f43b1ef902	mariano.juri@yahoo.co.uk	2024-01-29 15:14:19.117	2024-01-29 15:15:56.66	HR
f4c43acb-9190-4da2-974f-5bd55960917e	florence@ndthemachine.com	2024-01-29 15:16:05.297	2024-01-29 15:24:34.051	HR
6c4fdbfe-51c7-4d6a-9d18-0cefe2e9359c	mariano.juri@yahoo.co.uk	2024-01-29 15:25:14.158	2024-01-29 15:37:46.949	HR
42d3e27d-4f61-4cdc-a286-cf905bdf8f7d	florence@ndthemachine.com	2024-01-29 15:38:01.036	2024-01-29 15:39:42.862	HR
3473487b-31b0-4f98-ae1e-728e175d8571	florence@ndthemachine.com	2024-01-29 15:44:43.014	2024-01-30 12:36:21.969	HR
4abb3f6b-ea7f-4d85-a22e-0de48660df5a	mariano.juri@yahoo.co.uk	2024-01-30 12:36:30.227	2024-01-30 12:36:55.153	HR
a1f93d81-095e-4712-9ecf-828e95cf9961	florence@ndthemachine.com	2024-01-30 12:37:01.867	2024-01-30 12:37:43.315	HR
2efc7b0d-76be-4910-862f-8331dad9a9e4	mariano.juri@yahoo.co.uk	2024-01-30 12:37:51.804	2024-01-30 15:31:33.37	HR
99062c24-c8b3-4350-adc4-bf27fe314731	mariano.juri@yahoo.co.uk	2024-01-30 15:31:37.974	2024-01-30 22:03:23.601	HR
06c7f52b-03fb-48cb-8000-7959a1e6a15b	mariano.juri@yahoo.co.uk	2024-01-30 22:14:44.92	2024-01-30 22:30:34.699	\N
1fbccbe4-7ef6-45c9-9bb9-11fc29bbc0ba	mariano.juri@yahoo.co.uk	2024-01-30 22:38:45.938	2024-01-30 22:39:14.893	\N
68a29184-b6e7-4c4e-a6b7-d5fdf0179ee3	florence@ndthemachine.com	2024-01-31 14:24:42.757	2024-01-31 14:25:17.176	\N
b030e32d-66a2-44b5-ba63-b6971ba5791a	mariano.juri@yahoo.co.uk	2024-01-30 22:41:05.113	2024-02-02 17:10:41.387	\N
d5de80b8-4a08-4185-8973-8829d38cca89	mariano.juri@yahoo.co.uk	2024-02-02 17:10:46.213	2024-02-02 17:10:51.484	\N
2fa3ec8b-2f50-4085-8ca4-f455a5aa8cec	mariano.juri@yahoo.co.uk	2024-02-02 17:10:55.08	2024-02-21 12:25:41.855	\N
7f3019d1-2bae-47ff-9b34-61a5cd9566f8	mariano.juri@yahoo.co.uk	2024-02-21 12:25:53.366	2024-02-21 14:41:39.726	\N
3ad1c4db-2cf4-4b7f-841a-8e4969f3780c	mariano.juri@yahoo.co.uk	2024-02-21 14:41:45.215	2024-02-21 16:33:22.521	\N
fc408a72-02bc-4156-beae-d3320f9904ee	florence@ndthemachine.com	2024-02-21 16:33:29.028	2024-02-21 16:36:03.463	\N
5cf328a9-bbbc-40cc-a2da-eaff463e6208	mariano.juri@yahoo.co.uk	2024-02-21 16:36:13.816	\N	\N
11c1d453-af7d-4807-aa1e-7b14a41e1838	mariano.juri@yahoo.co.uk	2024-02-27 21:04:18.509	2024-02-27 21:04:36.434	\N
3d7b4506-401f-4bb4-b3f8-f83f96fdee9b	mariano.juri@yahoo.co.uk	2024-02-27 21:04:42.135	2024-02-27 21:05:51.505	\N
d1b218ce-5882-4e0f-bdcf-e5d44c5de38f	mariano.juri@yahoo.co.uk	2024-02-28 15:20:36.088	\N	\N
2c5840de-8d0d-4173-9f05-ca86b0fcccee	mariano.juri@yahoo.co.uk	2024-02-27 21:05:56.307	2024-03-01 14:02:08.246	\N
1adfead2-bd90-48c4-831f-ce027e8f8aab	mariano.juri@yahoo.co.uk	2024-03-01 14:02:14.575	\N	\N
03f7f56f-dd1b-4870-a061-09d3f8cf32f4	mariano.juri@yahoo.co.uk	2024-03-02 14:38:32.699	2024-03-02 15:57:43.54	\N
9425a518-f175-4857-b6f4-fd076e3e9917	mariano.juri@yahoo.co.uk	2024-03-02 15:57:47.69	2024-03-02 15:57:51.941	\N
64c17d77-3a23-4327-a126-27368ab6cd95	mariano.juri@yahoo.co.uk	2024-03-02 15:57:55.162	2024-03-04 11:41:55.223	\N
0b8f4ae1-5b88-4b38-8cf5-2a5757e994cc	florence@ndthemachine.com	2024-03-04 11:42:01.252	2024-03-04 11:42:29.329	\N
8c4c1614-2419-4496-a86e-8556c3e04607	mariano.juri@yahoo.co.uk	2024-03-08 11:21:36.821	2024-03-08 11:52:00.841	\N
eddcde57-aef4-4252-83c6-7b05226407b2	mariano.juri@yahoo.co.uk	2024-03-04 11:42:38.575	2024-03-08 13:25:51.487	\N
7cb1d7bd-21f4-4600-9768-d2658c5b6c6b	florence@ndthemachine.com	2024-03-08 13:25:56.393	2024-03-10 15:14:53.026	\N
8ef55dc2-7c52-461b-931a-f38d65bb5e4c	mariano.juri@yahoo.co.uk	2024-03-10 15:15:03.331	2024-03-10 16:22:24.155	\N
fd3ab48b-16cb-4f1b-93c6-a694263cb882	florence@ndthemachine.com	2024-03-08 11:52:09.038	2024-03-11 10:52:44.315	\N
21ca0f54-3799-492d-bfb9-d659e14a0da8	mariano.juri@yahoo.co.uk	2024-03-11 10:52:50.697	2024-03-11 10:52:55.643	\N
f335abfe-53ea-49b3-9768-be17a850767b	florence@ndthemachine.com	2024-03-11 10:53:01.698	2024-03-11 10:59:08.122	\N
c0556c2d-b344-41e4-ae0f-d36d7aecab07	mariano.juri@yahoo.co.uk	2024-03-11 10:59:19.353	2024-03-11 11:20:07.906	\N
82fea68e-5385-48ef-941f-b3c4a7bd93f7	mariano.juri@yahoo.co.uk	2024-03-11 11:20:12.914	\N	\N
228138fe-7702-4720-b54e-6c0ba8c5a73d	mariano.juri@yahoo.co.uk	2024-03-10 16:22:31.166	2024-03-13 09:32:50.269	\N
87e2ea79-ec9b-499a-8d79-153558334381	mariano.juri@yahoo.co.uk	2024-03-13 09:32:56.397	2024-03-13 09:48:09.984	\N
ba1d083a-31bf-4870-a488-5be656e934cf	mariano.juri@yahoo.co.uk	2024-03-13 09:48:15.484	2024-03-13 09:49:13.837	\N
54a6d375-baba-422b-a1b8-6de43d0094c9	florence@ndthemachine.com	2024-03-13 09:48:55.127	2024-03-13 09:49:25.435	\N
0df85e39-8678-4224-bc35-b4c0831c5e5d	mariano.juri@yahoo.co.uk	2024-03-13 09:49:42.54	2024-03-13 09:49:59.014	\N
1290a9bc-c65d-42b4-9171-0fdf29c6b285	mariano.juri@yahoo.co.uk	2024-03-13 09:50:28.506	2024-03-13 09:51:04.427	\N
20e98407-6088-4e8d-b09f-3dbb22923343	florence@ndthemachine.com	2024-03-13 09:51:11.481	2024-03-13 09:54:09.378	\N
bec7727f-dbeb-43a4-b610-b05f37c12629	mariano.juri@yahoo.co.uk	2024-03-13 09:54:13.807	\N	\N
512455bd-e5e4-45d5-a671-ec33d0ff1e32	mariano.juri@yahoo.co.uk	2024-03-13 15:32:24.435	2024-03-20 11:01:08.17	\N
8fad17d1-fe45-45d1-8ab4-9eb0507abecd	alejandro.fantino2024@neura.com.ar	2024-03-20 11:10:21.476	2024-03-20 11:22:37.212	\N
a377ec2f-123f-4e4c-9dcb-1f0e3528b1da	mariano.juri@yahoo.co.uk	2024-03-20 11:22:49.539	2024-03-20 11:38:08.329	\N
7874636d-146e-4456-9a50-29d40da7962a	mariano.juri@yahoo.co.uk	2024-03-20 11:51:15.37	2024-03-20 11:51:49.452	\N
79494718-dab0-400f-b884-77fc69b23949	mariano.juri@yahoo.co.uk	2024-03-20 11:52:48.418	2024-03-20 11:55:52.287	\N
789a5811-553b-4a4b-980f-0b3e97db31ca	florence@ndthemachine.com	2024-03-20 11:55:57.456	2024-03-20 11:56:33.697	\N
eba3c0b9-8d72-4b4a-b558-8e4c84ebebaa	florence@ndthemachine.com	2024-03-20 11:58:39.648	2024-03-20 11:58:54.81	\N
2acb2fe5-4a0d-4b6a-a824-6c6ff363c219	florence@ndthemachine.com	2024-03-20 12:10:37.169	2024-03-20 12:10:48.896	\N
7c08203f-f222-4fcb-a5f8-ecb51545573a	florence@ndthemachine.com	2024-03-20 12:14:13.349	2024-03-20 12:17:15.576	\N
e683cdfa-d376-4c71-afd4-c4c3651daea9	florence@ndthemachine.com	2024-03-20 12:25:16.68	2024-03-20 13:43:52.68	\N
c5259a2a-123a-41d9-9fd7-7e9b70fcce1f	alejandro.fantino2024@neura.com.ar	2024-03-20 13:44:10.324	2024-03-20 13:49:41.158	\N
9c920cad-2e58-409c-92e4-9f652da72bb3	florence@ndthemachine.com	2024-03-20 13:49:45.47	2024-03-20 13:51:50.837	\N
b4478aac-ed14-4678-9803-ea62e73b5213	florence@ndthemachine.com	2024-03-20 16:05:29.021	2024-03-20 16:05:35.61	\N
c9afcc68-d69f-4b8d-bace-bcd55db00c9f	florence@ndthemachine.com	2024-03-20 16:08:14.215	2024-03-21 10:29:24.695	\N
4041c8cb-1fd9-472a-bcd6-18990d48dcdb	florence@ndthemachine.com	2024-03-21 10:29:28.892	2024-03-21 11:51:32.76	\N
18cad92b-a104-4ead-bf52-6736a2ea9a8e	florence@ndthemachine.com	2024-03-21 11:51:37.273	2024-03-25 10:40:26.542	\N
10da62fd-1719-4e82-acc5-541e38791e46	mariano.juri@yahoo.co.uk	2024-03-25 10:40:40.757	2024-03-25 10:41:37.874	\N
bbbbcaa1-c5d6-4db6-a7b7-c23d427e711a	florence@ndthemachine.com	2024-03-25 10:41:45.13	2024-03-25 11:06:36.762	\N
1706ad58-ce35-40e5-af01-d73f89388cda	alejandro.fantino2024@neura.com.ar	2024-03-25 11:06:56.41	2024-03-30 15:47:44.282	\N
d9593eb3-5e60-4b48-82fb-6430c5017936	mariano.juri@yahoo.co.uk	2024-03-30 16:09:36.503	2024-03-30 16:11:04.659	\N
b52124b2-de12-42d5-a5a0-55262aeef339	mariano.juri@yahoo.co.uk	2024-03-30 16:11:34.813	\N	\N
74f47933-8ebb-4b40-9429-68de63273140	mariano.juri@yahoo.co.uk	2024-03-30 16:14:48.048	\N	\N
05e5767e-20e2-4b7b-bdfa-44c60be334a1	mariano.juri@yahoo.co.uk	2024-03-30 16:11:07.414	2024-04-07 14:18:22.326	\N
452e406f-aead-4e2e-adb7-8b7d93b22d83	florence@ndthemachine.com	2024-04-07 14:18:37.075	2024-04-07 14:46:45.737	\N
e57d359f-8611-4358-b912-7c462a10d480	mariano.juri@yahoo.co.uk	2024-04-07 14:46:53.684	\N	\N
821d9fca-92b3-480a-8f77-43e8ac9353e6	mariano.juri@yahoo.co.uk	2024-04-07 18:33:33.775	2024-04-07 18:33:40.964	\N
c6e4f1af-c8d4-4184-9c5e-5ea4c76194b5	mariano.juri@yahoo.co.uk	2024-04-07 18:33:45.052	2024-04-07 18:37:44.561	\N
0b44eac7-40cf-4913-9d3e-647cbbb5cd60	florence@ndthemachine.com	2024-04-07 18:38:22.523	2024-04-07 18:39:22.808	\N
fc6b706e-552f-4db4-933f-0b954ee051cc	mariano.juri@yahoo.co.uk	2024-04-07 18:37:48.943	2024-04-07 18:39:40.02	\N
57d4931d-68da-48f1-ad82-4032b34f589e	mariano.juri@yahoo.co.uk	2024-04-07 18:39:27.052	2024-04-07 18:40:16.724	\N
5e3acf86-fcd6-4d94-8127-6fda3db5dd88	alejandro.fantino2024@neura.com.ar	2024-04-07 18:40:34.393	\N	\N
80db3bcf-7683-4b0c-b693-7a456578a093	mariano.juri@yahoo.co.uk	2024-04-07 18:40:50.78	2024-04-08 09:02:57.773	\N
6577e6e8-3b7f-43ab-a8f2-9f5ae9ff8079	mariano.juri@yahoo.co.uk	2024-04-08 09:03:05.396	2024-04-08 17:34:04.959	\N
4b44fc5a-0f4f-4978-856b-6e81e0094cd0	mariano.juri@yahoo.co.uk	2024-04-08 17:43:01.684	2024-04-08 17:51:46.626	\N
1a172930-fcb6-412f-8999-2682c1d4fd9e	alejandro.fantino2024@neura.com.ar	2024-04-08 17:52:08.437	\N	\N
1148a771-83fa-47f1-a638-736f605b831a	mariano.juri@yahoo.co.uk	2024-04-23 06:33:17.21	2024-04-24 07:15:35.644	\N
84c2f327-b353-4c70-869e-dab9efcb4d12	florence@ndthemachine.com	2024-04-24 07:15:44.975	2024-04-24 07:18:17.9	\N
7524f261-fa5a-483b-a3cb-7c2ac88ee3a3	mariano.juri@yahoo.co.uk	2024-04-24 07:18:25.858	2024-04-27 14:54:23.946	\N
11d0075d-1634-491d-aeac-b5c89277d983	florence@ndthemachine.com	2024-04-27 14:54:32.007	2024-04-29 06:26:20.163	\N
5a210619-13cb-4f31-8f5c-32a2e149e19b	mariano.juri@yahoo.co.uk	2024-04-29 06:26:30.733	\N	\N
\.


--
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.accounts (identifier, name, address, postcode, country, telephone, number_of_employees, users_limit) FROM stdin;
a06dc590-8690-431a-9de0-167905011b47	Sky News	\N	\N	\N	\N	51-100	5
b81c2aba-b0ee-4642-9f90-7cc435ba2bcf	jorge.pontoriero@gmail.com	\N	\N	\N	\N	\N	5
8fddb239-8f82-4154-9098-ecf16345a853	John Doe LTD	\N	\N	\N	\N	51-100	0
69609916-442b-413d-a439-c3e505b9d000	mariano.juri@yahoo.co.uk	\N	\N	\N	\N	\N	0
2f7dd967-4367-4794-94de-9306612a7854	Libertad Avanza	\N	\N	\N	\N		0
b2b55608-535e-4274-aff3-332c9bcf5cb5	Libertad Avanza	\N	\N	\N	\N		0
5164fbf6-b966-4a47-bced-6769713c65ab	Libertad Avanza	\N	\N	\N	\N		0
cda2ddd2-d9ee-4df0-bd3f-e4c679bb8c55	Libertad Avanza	\N	\N	\N	\N		0
0dfae1ed-16cb-46b9-aaf5-b5df563cc90a	Libertad Avanza	\N	\N	\N	\N		0
fea594ef-19f3-490b-a9dc-172d663f874b	Libertad Avanza	\N	\N	\N	\N		0
04a01fa8-f9d2-4a3a-a7c1-e73de7f1ebca	Neura	\N	\N	\N	\N		0
f950ca75-e7c6-44a5-8fcf-28313e727609	Neura	\N	\N	\N	\N		0
5501c9b0-f0f9-4acc-8486-4ab2613adc57	Neura	\N	\N	\N	\N		0
393c8661-3e86-4f60-b14b-2e20ef16dc4a	Neura	\N	\N	\N	\N		0
3f9b1ca9-a373-431d-9ef9-0889cfdf87fc	11635296	\N	\N	\N	\N	Unlimited	0
\.


--
-- Data for Name: annoucement_audience; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.annoucement_audience (annoucement_identifier, profile_identifier) FROM stdin;
\.


--
-- Data for Name: annoucement_comments; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.annoucement_comments (identifier, annoucement_identifier, profile_identifier, date_created, comment) FROM stdin;
\.


--
-- Data for Name: annoucement_group; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.annoucement_group (annoucement_identifier, group_identifier) FROM stdin;
\.


--
-- Data for Name: annoucements; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.annoucements (identifier, body, topic, draft, profile_identifier, audience, email_notification, ping_to_the_top, date_created) FROM stdin;
\.


--
-- Data for Name: contracts; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.contracts (identifier, start_date, cont_service, contract_end_date, onboard_date, onboard_contract, benefits_start, review_date, job_type, country, office_role, location, department, team, cost_centre, line_manager, right_to_work, profile_identifier, length_of_service, notice_period, hours_per_week, days_per_week, fte, work_pattern, my_line_manager, right_to_work_expires, holiday_entitlement, holiday_brought_forward) FROM stdin;
9c5968c3-7f88-49e4-8ece-11c4e6204eb6	1990-01-01 00:00:00	1990-01-01 00:00:00	\N	\N	\N	1990-01-01 00:00:00	\N			\N			\N	\N	\N		\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
df7b8005-ff16-4948-9647-a9dd8723e76a	1990-01-01 00:00:00	1990-01-01 00:00:00	\N	\N	\N	1990-01-01 00:00:00	\N			\N			\N	\N	\N		\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
bdff0272-a623-4c9f-b1d8-f453aee03e05	1990-01-01 00:00:00	1990-01-01 00:00:00	\N	\N	\N	1990-01-01 00:00:00	\N		Endland	\N			\N	\N	\N		\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
17ba9a47-2fde-40fb-aa84-b8ce4b1469fb	2022-03-02 00:00:00	2013-01-01 00:00:00	\N	\N	\N	2013-01-01 00:00:00	\N	Singer	United Kingdom	\N	South London	Music	\N	\N	\N	d	\N	50	3 months	0	0	0	d	d	2013-01-01 00:00:00	0	0
cb55e7a0-d4dc-43a4-8eb4-d66c77bb3c90	2023-08-29 07:10:12	2023-08-30 07:10:12	\N	\N	\N	2023-08-28 00:00:00	\N	Software Architect	Argentina	\N	Buenos Aires	IT	\N	\N	\N	12	727910f3-4c49-4b13-b781-efc654044e29	1	12	0	0	0	12		\N	30	5
972d58cc-009b-4a2e-9a0f-2ab3d4ffc750	01/01/2013	01/01/2013	\N	\N	\N	01/01/2013	\N	Business Analyst	United Kingdom	\N	South London	Music	\N	\N	\N	01/01/2015	7bf56177-8a14-4f07-8d23-246949d65955	50	3 months	0	0	0	d	lll	01/01/2015	25	2
\.


--
-- Data for Name: documents; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.documents (identifier, img, profile_identifier, name, date_created, status) FROM stdin;
592a417f-d9a8-44a8-95df-2fac6b23fa0c	/Users/mvelasco/Documents/uploads/5b03b8e4-e19c-4baf-aa22-3f17f648fbe3/Screenshot 2023-10-26 at 15.46.23.png	727910f3-4c49-4b13-b781-efc654044e29	Screenshot 2023-10-26 at 15.46.23.png	2023-10-30	ACTIVE
42ad4a0c-dcb6-488c-8d0f-04966aa9af08	/Users/mvelasco/Documents/uploads/d362394a-7cb8-4550-884c-a222199e2c88/Screenshot 2023-10-09 at 15.39.17.png	727910f3-4c49-4b13-b781-efc654044e29	Screenshot 2023-10-09 at 15.39.17.png	2023-10-25	ACTIVE
2c2f4fbf-8175-4cac-adc9-69a1e940d197	/Users/mvelasco/Documents/uploads/242e93b1-3aec-42a5-9098-10dda41eb1bb/Screenshot 2023-10-14 at 17.55.22.png	727910f3-4c49-4b13-b781-efc654044e29	Screenshot 2023-10-14 at 17.55.22.png	2023-10-30	ACTIVE
77578fe6-20c0-4dff-a867-36d5988cd3a6	static/files/81fffa8c-99fb-4dab-bcf9-a00ea5acf83d/CUSTOM PERIOD Taxonomies for DotCMS - MAIN.xlsx	727910f3-4c49-4b13-b781-efc654044e29	CUSTOM PERIOD Taxonomies for DotCMS - MAIN.xlsx	2023-10-31	ARCHIVED
99987918-8932-420a-84f1-b5114a7e7068	static/avatars/2d7c3b40-009e-4475-a978-4c636d14fb9a/IMG_8977.jpg	727910f3-4c49-4b13-b781-efc654044e29	IMG_8977.jpg	2023-10-31	ARCHIVED
96309c55-7f71-4d79-827b-d4ba0afc44ee	/Users/mvelasco/Documents/uploads/63933e63-4ca0-4dce-a2c9-84924cef6004/Screenshot 2023-10-27 at 13.01.50.png	727910f3-4c49-4b13-b781-efc654044e29	Screenshot 2023-10-27 at 13.01.50.png	2023-10-30	ARCHIVED
e2b7fa6a-44b1-4f7a-87ac-9520355190a7	/files/7df970a5-7cc4-4b58-b54a-593d54ba0ea9/IMG_8974.jpg	727910f3-4c49-4b13-b781-efc654044e29	IMG_8974.jpg	2023-10-31	ARCHIVED
bdc53017-6316-478c-883f-98ab79b127ca	/Users/mvelasco/Documents/uploads/e063521c-921c-4f76-bde2-1974f4df57a9/Screenshot 2023-10-20 at 15.54.35.png	727910f3-4c49-4b13-b781-efc654044e29	Screenshot 2023-10-20 at 15.54.35.png	2023-10-30	ARCHIVED
4d674bb3-b5f5-4977-8238-a47119c681a6	/files/e0cbd64d-0329-47d4-b592-d3e0c911b6f2/PolicySchedule.pdf	727910f3-4c49-4b13-b781-efc654044e29	PolicySchedule.pdf	2024-01-15	ARCHIVED
987a17e7-4d3a-4f46-ae59-64e6611d24a7	/Users/mvelasco/Documents/uploads/d7953487-b1af-4aa8-864b-4447a0b29b4d/Screenshot 2023-10-27 at 13.01.50.png	727910f3-4c49-4b13-b781-efc654044e29	Screenshot 2023-10-27 at 13.01.50.png	2023-10-30	ARCHIVED
\.


--
-- Data for Name: event_attendee; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.event_attendee (event_identifier, profile_identifier, status) FROM stdin;
fbb6abce-d9ef-4f9f-886b-d52f4d737c5a	7bf56177-8a14-4f07-8d23-246949d65955	\N
9a151de4-a812-41e0-9e26-92e9ce6e24c8	727910f3-4c49-4b13-b781-efc654044e29	\N
9a151de4-a812-41e0-9e26-92e9ce6e24c8	7bf56177-8a14-4f07-8d23-246949d65955	\N
\.


--
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.events (identifier, start, end_date, description, location, url, label, status, profile_identifier, date_created, mod_date, includesaturday, includesunday, all_day, title) FROM stdin;
fbb6abce-d9ef-4f9f-886b-d52f4d737c5a	2024-04-14 00:00:00	2024-04-18 00:00:00	teste	\N	\N	Important	\N	727910f3-4c49-4b13-b781-efc654044e29	2024-04-30	2024-04-30	f	f	t	test
9a151de4-a812-41e0-9e26-92e9ce6e24c8	2024-05-13 00:00:00	2024-05-17 00:00:00	test	\N	\N	Personal	\N	727910f3-4c49-4b13-b781-efc654044e29	2024-05-01	2024-05-01	f	f	t	Java Practicing Semminary
\.


--
-- Data for Name: goal_performance_review; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.goal_performance_review (goal_identifier, performance_review_identifier) FROM stdin;
\.


--
-- Data for Name: goals; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.goals (identifier, name, target, deadline, profile_identifier, progress, description) FROM stdin;
1719275f-62ab-483b-bd73-429c91e9a757	TEST 123	70	2023-09-23	727910f3-4c49-4b13-b781-efc654044e29	45	<p style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">Black Friday is almost here, and we want to help you outsell your competition.</p>\n<p style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">We want 2023 to be the year when you&nbsp;<strong>break your revenue record</strong>&hellip;</p>\n<p style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">So, we put together 4 resources that will help you make this a reality:</p>\n<ul style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; margin: 0px 0px 20px 40px; padding: 0px;">\n<li style="margin-left: 15px;"><a class="m_-3194041646948202551intercom-content-link" style="line-height: 19.5px; margin: 0px; padding: 0px; color: #1251ba !important;" href="https://optinmonster.intercom-clicks.com/via/e?ob=sxZzrHwLIwsfd%2FJShL%2Bnm2L7XOXHIjGthZED5I9kjuQurYgU6wtaV91SQnV0ecNMXXL9IGFjeAhkd9rwdmGdciry1ShjrRUAZa8MaN7eIG7Y6aoytSGd7C5nzQH2XxXkHRrhsVHwx%2F6DVSAwXA3xZoT%2BUayqrFdcOB7x8pEJpZIeEQgAOuLVIPc0vuQNoWF8cc4oXZgxyjA9C9EFiQS7Pw%3D%3D&amp;h=4fc7ea4b07e343d4180aec46d962dab30783f1a6-yot32p6u_87845615541892&amp;l=7b2d8ed2b22ee7334751a558ed5f0867b2319cb0-82716564" target="_blank" rel="noopener" data-saferedirecturl="https://www.google.com/url?q=https://optinmonster.intercom-clicks.com/via/e?ob%3DsxZzrHwLIwsfd%252FJShL%252Bnm2L7XOXHIjGthZED5I9kjuQurYgU6wtaV91SQnV0ecNMXXL9IGFjeAhkd9rwdmGdciry1ShjrRUAZa8MaN7eIG7Y6aoytSGd7C5nzQH2XxXkHRrhsVHwx%252F6DVSAwXA3xZoT%252BUayqrFdcOB7x8pEJpZIeEQgAOuLVIPc0vuQNoWF8cc4oXZgxyjA9C9EFiQS7Pw%253D%253D%26h%3D4fc7ea4b07e343d4180aec46d962dab30783f1a6-yot32p6u_87845615541892%26l%3D7b2d8ed2b22ee7334751a558ed5f0867b2319cb0-82716564&amp;source=gmail&amp;ust=1695672879445000&amp;usg=AOvVaw1YPX8r-oSl3So7-tobJWFy">Black Friday &amp; Cyber Monday Marketing 2023: Week-by-Week Guide</a></li>\n</ul>\n<ul style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; margin: 0px 0px 20px 40px; padding: 0px;">\n<li style="margin-left: 15px;"><a class="m_-3194041646948202551intercom-content-link" style="line-height: 19.5px; margin: 0px; padding: 0px; color: #1251ba !important;" href="https://optinmonster.intercom-clicks.com/via/e?ob=57DuWS02Fb73bfHWcyIIvMTVIUYdIrWdwIrGmYFUtjpmNCOahAcbTiUGs9%2B%2BkwuP6TBkMAcrOVF9VlIFvk1gN0vyTZZM7H1WW4kxEef2rfaTz1pXQB4kYZ%2F9XHjVeeadam9H4xIU7TiEjDZzZjdoSrXtUSRMpGsS7JD0g6mtjO9xEcPGv4h09BO8kDoqFYN6TOrCdnH6PkbgJoHE6TZ0qw%3D%3D&amp;h=a7fbd7ea0001131fb1ec598102f23707c95c739b-yot32p6u_87845615541892&amp;l=2a81e3fbbe1f25231e9aa0f3cddc25602d07a6ab-82716568" target="_blank" rel="noopener" data-saferedirecturl="https://www.google.com/url?q=https://optinmonster.intercom-clicks.com/via/e?ob%3D57DuWS02Fb73bfHWcyIIvMTVIUYdIrWdwIrGmYFUtjpmNCOahAcbTiUGs9%252B%252BkwuP6TBkMAcrOVF9VlIFvk1gN0vyTZZM7H1WW4kxEef2rfaTz1pXQB4kYZ%252F9XHjVeeadam9H4xIU7TiEjDZzZjdoSrXtUSRMpGsS7JD0g6mtjO9xEcPGv4h09BO8kDoqFYN6TOrCdnH6PkbgJoHE6TZ0qw%253D%253D%26h%3Da7fbd7ea0001131fb1ec598102f23707c95c739b-yot32p6u_87845615541892%26l%3D2a81e3fbbe1f25231e9aa0f3cddc25602d07a6ab-82716568&amp;source=gmail&amp;ust=1695672879445000&amp;usg=AOvVaw3SuviWGz8HwCdbFwFwc17_">Best 9 Black Friday Marketing Tools</a></li>\n</ul>\n<ul style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; margin: 0px 0px 20px 40px; padding: 0px;">\n<li style="margin-left: 15px;"><a class="m_-3194041646948202551intercom-content-link" style="line-height: 19.5px; margin: 0px; padding: 0px; color: #1251ba !important;" href="https://optinmonster.intercom-clicks.com/via/e?ob=D3k2v5IxG8QElPrflPhBeeYc9DfJSvzUxFTGYEiX19U8HUP6RK%2FyYNhU3%2BrfXaD2pEkjmySfPmBKTTO9aYKdrVXPFeeE3%2FNvnY8ejbSU%2BPJMe8meXvD5y2SpA8TN8QypwYFGUecAAx42yRKvbgWVnhfnRGS9ZcAfdArJ0TE8z%2B1aT9KACMXYz%2Bn%2FFzsPozPg&amp;h=4ea7f2c8559dcabc5a09f15cca715e34c69aaeff-yot32p6u_87845615541892&amp;l=8115b647b0502e370a7a2bb55c8c42be677c71be-82716570" target="_blank" rel="noopener" data-saferedirecturl="https://www.google.com/url?q=https://optinmonster.intercom-clicks.com/via/e?ob%3DD3k2v5IxG8QElPrflPhBeeYc9DfJSvzUxFTGYEiX19U8HUP6RK%252FyYNhU3%252BrfXaD2pEkjmySfPmBKTTO9aYKdrVXPFeeE3%252FNvnY8ejbSU%252BPJMe8meXvD5y2SpA8TN8QypwYFGUecAAx42yRKvbgWVnhfnRGS9ZcAfdArJ0TE8z%252B1aT9KACMXYz%252Bn%252FFzsPozPg%26h%3D4ea7f2c8559dcabc5a09f15cca715e34c69aaeff-yot32p6u_87845615541892%26l%3D8115b647b0502e370a7a2bb55c8c42be677c71be-82716570&amp;source=gmail&amp;ust=1695672879445000&amp;usg=AOvVaw2cV4vIGQyyDWN7zPm72JiL">Black Friday Marketing Ideas That Actually Work</a></li>\n</ul>\n<ul style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; margin: 0px 0px 20px 40px; padding: 0px;">\n<li style="margin-left: 15px;"><a class="m_-3194041646948202551intercom-content-link" style="line-height: 19.5px; margin: 0px; padding: 0px; color: #1251ba !important;" href="https://optinmonster.intercom-clicks.com/via/e?ob=In5rzaAaGQX%2B1BQqwYvopIVm631I6oUH2el7LwQ62FvYh5WeYenDspNTk96%2BcyOlCJKmRJTDrBk2gpyOFPCxLTwjf11HDpqmroFQ8nlbLh4vgG67ODAKHEWxXLB29uyvtU28KAreRfS464vIu5xoRWLKDCDfdMHLBE41guXr%2B0pac4Hb7hxxLlQ%2FjZiYoT5h&amp;h=239537e687c6d482127587dcd74d0943965a78ca-yot32p6u_87845615541892&amp;l=6e11e7632d0195dc5327eb3b39ff8ca03629075c-82716579" target="_blank" rel="noopener" data-saferedirecturl="https://www.google.com/url?q=https://optinmonster.intercom-clicks.com/via/e?ob%3DIn5rzaAaGQX%252B1BQqwYvopIVm631I6oUH2el7LwQ62FvYh5WeYenDspNTk96%252BcyOlCJKmRJTDrBk2gpyOFPCxLTwjf11HDpqmroFQ8nlbLh4vgG67ODAKHEWxXLB29uyvtU28KAreRfS464vIu5xoRWLKDCDfdMHLBE41guXr%252B0pac4Hb7hxxLlQ%252FjZiYoT5h%26h%3D239537e687c6d482127587dcd74d0943965a78ca-yot32p6u_87845615541892%26l%3D6e11e7632d0195dc5327eb3b39ff8ca03629075c-82716579&amp;source=gmail&amp;ust=1695672879445000&amp;usg=AOvVaw2S0dymtLUx_2DNIquy4buq">Black Friday Emails: Best Examples, Top Subject Lines, and Key Takeaways</a></li>\n</ul>\n<p style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">Implement whatever you can, and&nbsp;<em>have a record-breaking Black Friday sale</em>!</p>\n<p style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">If you have any questions, just reply to this email.</p>\n<p style="font-size: 15px; color: #525252; background-color: #fefefe; font-family: sans-serif; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">Talk again soon,</p>\n<p style="font-size: 15px; color: #525252; background-color: #fefefe; font-family: sans-serif; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">Angie Meeker<br />General Manager,<br />OptinMonster</p>
0be1c20f-e1c7-42d5-9218-6086ae7cd984	Goal 123	50	2023-08-27	727910f3-4c49-4b13-b781-efc654044e29	15	<p>testing #33</p>
b84985b8-d2b0-4e7c-b2c3-cb62a4dc1264	TEST GOAL	50	2023-11-08	727910f3-4c49-4b13-b781-efc654044e29	35	<p>This is a<strong> description! </strong>Can you see it?</p>
ed3d36d4-f0fe-4978-9970-05af3e4f9f34	Goal #45	50	2023-10-31	727910f3-4c49-4b13-b781-efc654044e29	20	<p>Sarada Sarasa</p>
ffe8ed90-d19b-4067-ae87-14c53df98b3c	Goal 1	50	2024-01-27	7bf56177-8a14-4f07-8d23-246949d65955	20	<p>test</p>
ae98ff9b-fa57-414d-adec-8952c4d1eaa3	Goal 2	20	2024-01-31	7bf56177-8a14-4f07-8d23-246949d65955	10	<p>Test 2</p>
4323f5bd-70d6-433e-b3b8-2911390fa435	MD JURI VELASCO	50	2024-03-29	7bf56177-8a14-4f07-8d23-246949d65955	75	<p>asdfasdfa</p>
a72fa753-dcb6-4307-952c-6af15a0b42ab	MD JURI VELASCO	50	2023-10-31	727910f3-4c49-4b13-b781-efc654044e29	75	\N
\.


--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.groups (identifier, name, type, description, account_identifier) FROM stdin;
8c467387-e154-479e-9105-24073b8252b6	IT	Department	<p>This is a test</p>	69609916-442b-413d-a439-c3e505b9d000
3804359f-9e56-4722-a6d6-c787306a8aa1	DotCMS	Project	<p>test</p>	69609916-442b-413d-a439-c3e505b9d000
\.


--
-- Data for Name: holidays; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.holidays (identifier, start, end_date, comments, type, status, profile_identifier, authorized_by, date_created, mod_date, includesaturday, includesunday, halfdaystart, halfdayend, draft) FROM stdin;
4e6ec45f-8727-4ff9-ae06-618c56f68349	2024-03-15 00:00:00	2024-03-18 00:00:00	<p>Days off from 15th to 18th of March. Mallorca holidays</p>	Annual Leave	APPROVED	727910f3-4c49-4b13-b781-efc654044e29	727910f3-4c49-4b13-b781-efc654044e29	2024-03-11	\N	t	t	f	f	f
f01efa6e-5718-4a53-bab3-f1e705bdabec	2024-05-20 00:00:00	2024-05-24 00:00:00	test	ANNUAL_LEAVE	APPROVED	727910f3-4c49-4b13-b781-efc654044e29	\N	\N	\N	t	f	f	f	f
\.


--
-- Data for Name: invoice_item; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.invoice_item (identifier, nominal_code, line_ref, vat, quantity, amount, invoice_identifier, amount_type) FROM stdin;
\.


--
-- Data for Name: invoice_payment; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.invoice_payment (invoice_identifier, payment_identifier) FROM stdin;
\.


--
-- Data for Name: invoices; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.invoices (identifier, date, due_date, ref, invoice_number, contact, project, account_identifier, status, outstanding) FROM stdin;
\.


--
-- Data for Name: leads; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.leads (identifier, firstname, lastname, company, email, phone_number, website, location, industry, lead_source, created_at, updated_at, profile_identifier, account_identifier, comments) FROM stdin;
9a96375a-b34d-4462-b36b-8554b60e5307	Javier	Milei	11635296	javier@libertadavanza.com.ar	+447761318029	\N	\N	\N	Email marketing	2024-02-16 10:33:11.350797	2024-02-16 10:33:11.350797	727910f3-4c49-4b13-b781-efc654044e29	69609916-442b-413d-a439-c3e505b9d000	<p>test</p>
\.


--
-- Data for Name: notifications; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.notifications (identifier, profile_identifier, message, "time", deleted, owner_identifier, unread) FROM stdin;
b17251f2-5c15-47cf-b96f-8d1302da8436	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2023-11-24 10:43:04.175	f	727910f3-4c49-4b13-b781-efc654044e29	f
b0bc8e13-65b5-498c-bc80-fadf6140262f	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2023-12-12 13:29:03.131	f	7bf56177-8a14-4f07-8d23-246949d65955	f
606c778b-4090-49f5-8e4c-0b4665e8a26a	727910f3-4c49-4b13-b781-efc654044e29	 has created an event!	2023-12-12 13:48:06.271	f	7bf56177-8a14-4f07-8d23-246949d65955	f
11faa6b3-c5bd-4aaf-bac7-013052bfb0e8	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2023-12-12 13:48:06.284	f	7bf56177-8a14-4f07-8d23-246949d65955	f
270d11e5-3269-48eb-aa43-9cfc75d78a57	727910f3-4c49-4b13-b781-efc654044e29	 has created an event!	2024-01-15 15:52:22.644	f	727910f3-4c49-4b13-b781-efc654044e29	f
c45b32b4-8cef-4dc8-b15f-e4bda6879ec4	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-01-15 15:52:22.649	f	727910f3-4c49-4b13-b781-efc654044e29	f
acb01d7d-6865-405f-aae5-37786c9c9796	7bf56177-8a14-4f07-8d23-246949d65955	 has created a new annoucement!	2024-01-29 15:37:42.108	f	727910f3-4c49-4b13-b781-efc654044e29	f
00133a9a-c5a9-4359-9de1-5b130f4c6a5e	7bf56177-8a14-4f07-8d23-246949d65955	 has created a new annoucement!	2024-02-28 13:59:14.915	f	727910f3-4c49-4b13-b781-efc654044e29	f
a9d1593a-fc4d-4752-8b8d-8f3f0b365ed8	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-03-01 13:34:41.193	f	727910f3-4c49-4b13-b781-efc654044e29	f
49029ad4-4a64-481a-8453-303e9bccd049	727910f3-4c49-4b13-b781-efc654044e29	 has created a new annoucement!	2024-04-25 06:59:24.436	f	727910f3-4c49-4b13-b781-efc654044e29	f
72d733ed-3f2e-4446-8f61-4589812e951e	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-04-29 06:32:22.916	f	727910f3-4c49-4b13-b781-efc654044e29	f
3485b116-2f9a-4408-8126-caecf6831ec4	727910f3-4c49-4b13-b781-efc654044e29	 has created an event!	2024-04-29 06:49:17.481	f	727910f3-4c49-4b13-b781-efc654044e29	f
e95121d8-4e96-497a-b615-dd94b73f0da4	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-04-29 06:49:17.485	f	727910f3-4c49-4b13-b781-efc654044e29	f
a9ee0655-c1b2-4ef1-884d-de3e7993a58f	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-04-29 06:50:00.085	f	727910f3-4c49-4b13-b781-efc654044e29	f
f8b6190d-7b40-4ba1-9c07-c32a632ffdf2	727910f3-4c49-4b13-b781-efc654044e29	 has created an event!	2024-04-29 06:50:48.73	f	727910f3-4c49-4b13-b781-efc654044e29	f
ece32165-cd25-4b44-b7fd-a709f387a5e8	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-04-29 06:53:45.333	f	727910f3-4c49-4b13-b781-efc654044e29	f
dc5c560e-1303-45dc-9fa7-91f312817c38	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-04-29 09:25:22.776	f	727910f3-4c49-4b13-b781-efc654044e29	f
2c3faea0-430e-4760-bdb5-e465dfb72445	727910f3-4c49-4b13-b781-efc654044e29	 has created an event!	2024-04-29 09:35:37.999	f	727910f3-4c49-4b13-b781-efc654044e29	f
87b531c0-390e-48f7-90a0-81ae3b3dfebf	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-04-29 09:35:38.002	f	727910f3-4c49-4b13-b781-efc654044e29	f
2873e0c1-ef82-4713-b5e6-caa6a7e1a604	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-04-29 09:43:20.463	f	727910f3-4c49-4b13-b781-efc654044e29	f
e2a2f953-9735-4526-968b-625a92c3b9b3	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-04-30 13:38:21.799	f	727910f3-4c49-4b13-b781-efc654044e29	f
95b2a498-0379-4e35-b0a4-9f63b12fd129	727910f3-4c49-4b13-b781-efc654044e29	 has created an event!	2024-05-01 15:27:42.573	f	727910f3-4c49-4b13-b781-efc654044e29	f
8b6c2e6a-8bcb-4298-a7e5-5cb6f78f2f7d	7bf56177-8a14-4f07-8d23-246949d65955	 has created an event!	2024-05-01 15:27:42.608	f	727910f3-4c49-4b13-b781-efc654044e29	f
\.


--
-- Data for Name: organization_chart; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.organization_chart (parent_identifier, child_identifier, relationship_type, authorize_holiday) FROM stdin;
ffbbd930-53d3-4000-95b4-bf79840a0491	b75ae32d-d4c0-4805-b3ef-2e17c45e7ec1		t
b75ae32d-d4c0-4805-b3ef-2e17c45e7ec1	fd2977e9-10d5-4173-a4b6-21bb09b17b38		t
\.


--
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.payments (identifier, amount, contact, ref, date, type, bank_account) FROM stdin;
197ca8a8-9805-11ed-a8fc-0242ac120002	4289.75	Mariano Daniel Juri Velasco	Pago por transaccion de terceros	2023-01-19	\N	\N
3ce4a6e3-5971-4d35-bf2d-79d4a0fb225e	5000	Mariano Velasco	Ref line	2023-01-27	\N	\N
2ffcf5ce-e2ff-41a9-b8b4-1895f53bacd3	40	Mariano Velasco	Ref line 123	2023-01-27	\N	\N
058d6151-0bef-429f-bc0d-755d1e1040b5	30	Mariano Velasco	Ref Line 1234	2023-01-31	\N	\N
aa2c2bfa-a22f-46a5-98e7-8df28e6cd000	20	Mariano Velasco	Ref Line 123	2023-01-28	\N	\N
b23b4b03-6a3c-4838-8a62-b3a65b736cd8	20	Mariano Velasco	Ref line	2023-01-26	\N	\N
52cd8e03-4639-4375-aa3d-1f4017fc0e09	700	Mariano Velasco	Ref Line 123	2023-01-28	\N	\N
49a6c3a0-a4d5-42cc-8db8-e79b6a5b3ef0	700	Mariano Velasco	Ref Line 123	2023-01-28	\N	\N
ca2ba7da-e3db-4706-bfcd-16a9d40d9a00	700	Mariano Velasco	Ref Line 1237890	2023-01-06	\N	\N
14e75ded-ec68-4297-985c-623e775ac3db	22	Mariano Velasco	Ref line	2023-01-07	\N	\N
422cd582-4135-4bc4-b051-779f2ca98678	100	Mariano Velasco	Test Ref	2023-01-28	\N	\N
65da5d92-826f-41c9-b938-c80943c36c3b	2000	Mariano Velasco	Ref Line 1234	2023-01-28	\N	\N
6038697c-c765-486c-9f97-efe66970285b	140	Mariano Velasco	Ref line	2023-01-03	\N	\N
a973f92d-645e-4018-9b04-06f8b6ab59f7	20	Mariano Velasco	Test Ref	2023-01-27	\N	\N
ee6917ac-6459-4e49-adf7-b2619883e2ff	1000	Mariano Velasco	Ref Line 123	2023-01-28	\N	\N
144d37f1-0c68-43e8-817b-801dabb3d1e8	50	Mariano Velasco	Ref Line 123	2023-01-31	\N	\N
e8903c9c-1e48-4b41-8c12-98b730a6c6b9	20	Mariano Velasco	Ref line	2023-01-30	\N	\N
f50c37c5-b855-41e3-9035-73326aaa1d86	10	Mariano Velasco	Ref Line 1237890	2023-01-25	\N	\N
6f5ccee7-5b3f-4118-b9d3-1644d7335dec	5	Mariano Velasco	Ref Line 1234	2023-01-25	\N	\N
6999b4c7-3aa8-4032-ba7b-acf6f906fbae	5	Mariano Velasco	Ref Line 1234	2023-01-19	\N	\N
f4063abd-aaf7-47e0-84f2-7f833fbe1d2b	1	Mariano Velasco	Test Ref	2023-01-12	\N	\N
6c10e8ed-7237-4d4b-b2e1-10e818cd109c	1	1	1	2023-01-28	\N	\N
237a4a30-87f7-4627-87fb-b92b40028dec	2	Mariano Velasco	Ref Line 123	2023-01-27	\N	\N
cbebca4d-e981-48eb-a618-44e28502472b	2	Mariano Velasco	Ref Line 123	2023-01-27	\N	\N
7905fd31-f544-40a5-afb6-9721993af546	2	Mariano Velasco	Ref Line 123	2023-01-20	\N	\N
c2b5c47b-02d2-4a63-a960-18997f0a7d8b	20	Mariano Velasco	20	2023-01-21	\N	\N
5c2fd02d-a86b-4789-b7b7-ce4e9d162ea2	1	1	Ref line	2023-01-03	\N	\N
b5ed4c6a-f461-40fd-91bb-06ca920f49eb	1	Mariano Velasco	Ref Line 123	2023-01-26	\N	\N
33e8d025-6540-46e2-9bfd-1bad74ce9fa3	1	Mariano Velasco	Ref line	2023-01-27	\N	\N
d16024c6-2633-4707-bbe7-d62785946fe1	1	1	Test Ref	2023-01-27	\N	\N
3566a045-2a9c-48e6-ad89-483c2fce84d0	2	1	Test Ref	2023-01-03	\N	\N
ab8cf378-53b1-472d-a45b-6557795d5882	15	1	Ref Line 1234	2023-01-25	\N	\N
ceceb187-5247-4b39-8207-8d16be59358e	100	Mariano Velasco	Ref Line £100	2023-01-31	\N	\N
74be42d4-edbb-4182-9b57-caecc52809e5	20	Mariano Velasco	Ref Line 123	2023-02-23	\N	\N
87572527-d2d7-475c-8f52-d5240bbd8706	20	Mariano Velasco	Ref Line 123	2023-02-23	\N	\N
12b14669-88ca-4ada-906a-722e7a94a602	20	Mariano Velasco	Ref Line 123	2023-02-23	\N	\N
30608302-2eb4-4529-ba95-d6764aa5e67c	30	Mariano Velasco	Test Ref	2023-02-25	\N	\N
68df12e7-be9b-4ecb-b708-fad880fbd2e0	70	1	Ref Line 123	2023-02-17	\N	\N
62b6e81a-2012-4e17-b4c3-f46c339d3437	55	Mariano Velasco	Mariano - Test -- £55	2023-02-24	\N	\N
cf44888f-0e64-40d2-a448-ff846c0202c0	2	Mariano Velasco	Ref Line 123	2023-02-18	\N	\N
\.


--
-- Data for Name: performance_reviews; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.performance_reviews (identifier, employee_identifier, reviewer_identifier, comments, reviewdate) FROM stdin;
\.


--
-- Data for Name: profile_group; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.profile_group (profile_identifier, group_identifier) FROM stdin;
727910f3-4c49-4b13-b781-efc654044e29	3804359f-9e56-4722-a6d6-c787306a8aa1
7bf56177-8a14-4f07-8d23-246949d65955	8c467387-e154-479e-9105-24073b8252b6
\.


--
-- Data for Name: profile_role; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.profile_role (profile_identifier, role_key) FROM stdin;
edb0a07a-544e-4f5a-8140-06a77b6f8d1a	HR-ADMIN
41dc513b-d1f3-444e-bd79-32413fe34572	HR-ADMIN
d08d7425-d5bf-465a-b98e-12ae2589e171	HR-ADMIN
af8c81df-5bb4-4184-acf4-149700a75711	HR-ADMIN
94ce30b3-ecce-44aa-bb48-f6da32921514	HR-ADMIN
27866d58-2de6-45dd-83a9-4fd5fdad5412	HR-ADMIN
e1034681-4874-48b0-98f1-c9e7d41d09e2	HR-ADMIN
057c7b5b-bebd-4ef5-bf56-f3cc43457869	HR-ADMIN
68a00048-2a7b-41db-a29f-4e18154c6236	HR-ADMIN
fcaa2341-e98e-4021-a983-db0960b8acd0	HR-ADMIN
e4442e63-7a74-47ad-9ab6-047ed7e6e2c6	HR-ADMIN
b81b6de3-6efe-4d2e-8c08-d1c4769e120f	HR-ADMIN
727910f3-4c49-4b13-b781-efc654044e29	HR-USER
727910f3-4c49-4b13-b781-efc654044e29	HR-ADMIN
d9edc698-c858-4fbf-8ffc-6f7f1e5811d4	HR-ADMIN
014a37d3-453c-4138-8787-83a5f17a6fa6	HR-ADMIN
9696dec0-235a-4985-a780-0a43905be392	HR-ADMIN
\.


--
-- Data for Name: profiles; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.profiles (identifier, firstname, lastname, email, deleted, account_identifier, dob, entitlement_absence, avatar, title, known_as, address, gender, gender_identity, preferred_pronoun, marital_status, employee_number, work_phone, work_extn, work_mobile, personal_email, personal_mobile, home_phone, online) FROM stdin;
804b3839-9e29-45f7-a60e-e8aabe87d06f	Alisa	Davis	alisa.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Dr		430 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Single	182				alisa.davis@gmail.com			f
c8de4b3f-daa6-4fc1-9a0e-aa6bdf8b1658	Alisa	Garcia	alisa.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	24	\N	Mr		450 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Male			In a relationship	83				alisa.garcia@gmail.com			f
d6f3051f-ebb1-4029-9b14-1f4754a1d45a	Alisa	Gonzalez	alisa.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Dr		477 456 Elm St 123 Main St 321 Maple Ave 789 Oak St 654 Pine St	Male			Widowed	869				alisa.gonzalez@gmail.com			f
e78a3279-0b15-40a2-95e0-fa10ee81b222	Alisa	Johnson	alisa.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Miss		920 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Male			Married	658				alisa.johnson@gmail.com			f
f6ce5190-ab6f-4d2c-b2da-9f71cf7e99ea	Alisa	Jones	alisa.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	4	\N	Dr		719 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			In a relationship	149				alisa.jones@gmail.com			f
fd2977e9-10d5-4173-a4b6-21bb09b17b38	Marisa	Brown	alisa.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-03-28	0	\N	Miss		737 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female	\N		\N	335	\N	\N	\N	alisa.brown@gmail.com	111111	\N	f
ffbbd930-53d3-4000-95b4-bf79840a0491	Jordan	Peterson	jordan@gmail.com	f	a06dc590-8690-431a-9de0-167905011b47	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
edb0a07a-544e-4f5a-8140-06a77b6f8d1a	Jorge	Pontoriero	jorgep@gmail.com	f	b81c2aba-b0ee-4642-9f90-7cc435ba2bcf	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
907fbf29-c720-42f2-9b2a-cc30d4971f3e	Alisa	Miller	alisa.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-02	0	\N	Miss		711 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Male	\N		\N	187	\N	\N	\N	alisa.miller@gmail.com	123132131231	\N	f
b75ae32d-d4c0-4805-b3ef-2e17c45e7ec1	Charles	Johnson	charles.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-05	17	\N	Miss		16 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Divorced	804				charles.johnson@gmail.com			f
41dc513b-d1f3-444e-bd79-32413fe34572	John	Doe	johndoe@gmail.com	f	8fddb239-8f82-4154-9098-ecf16345a853	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
d08d7425-d5bf-465a-b98e-12ae2589e171	John	Doe	johndoe@gmail.com	f	8fddb239-8f82-4154-9098-ecf16345a853	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
af8c81df-5bb4-4184-acf4-149700a75711	John	Doe	johndoe@gmail.com	f	8fddb239-8f82-4154-9098-ecf16345a853	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
9acdbc78-03ef-4dc8-87f0-8237517b57fc	Alisa	Martinez	alisa.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mrs		122 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Divorced	571				alisa.martinez@gmail.com			f
f6590305-dae7-445e-9704-4f2a98d711a8	Alisa	Rodriguez	alisa.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	8	\N	Mr		485 123 Main St 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St	Male			Widowed	406				alisa.rodriguez@gmail.com			f
3fa489f5-e1fd-48b4-b133-0a9af55f531c	Alisa	Smith	alisa.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	28	\N	Mr		154 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Male			Single	872				alisa.smith@gmail.com			f
60dd53d7-49be-4a51-873a-9edd30e1babb	Charles	Brown	charles.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	29	\N	Mr		788 321 Maple Ave 123 Main St 456 Elm St 789 Oak St 654 Pine St	Male			Married	909				charles.brown@gmail.com			f
de8e455b-8401-49ec-916a-52cd0c72d023	Charles	Davis	charles.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	23	\N	Mrs		240 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Male			Single	951				charles.davis@gmail.com			f
c31d63c8-cb10-4cc3-a50a-f183b9d39be4	Charles	Garcia	charles.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	23	\N	Mr		574 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Single	59				charles.garcia@gmail.com			f
0b463d13-182e-4739-9f1f-ff23386ba2f8	Charles	Gonzalez	charles.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Mrs		480 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Divorced	633				charles.gonzalez@gmail.com			f
1ecd0b43-239f-46c8-b174-ea1b00d58536	Charles	Jones	charles.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	26	\N	Miss		528 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Single	768				charles.jones@gmail.com			f
de7ee51a-70d7-46c3-9d34-96b5806b9c5d	Charles	Martinez	charles.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	9	\N	Miss		838 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			In a relationship	411				charles.martinez@gmail.com			f
48499941-b60c-4efc-ac90-ba3abd377a09	Charles	Miller	charles.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	1	\N	Mrs		702 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Female			In a relationship	182				charles.miller@gmail.com			f
d814a647-c650-423e-af8a-f33080aec6a6	Charles	Rodriguez	charles.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	13	\N	Dr		746 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Male			Married	761				charles.rodriguez@gmail.com			f
fd41c0dc-f9c0-4b4c-9741-08387eb91045	Charles	Smith	charles.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Miss		212 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Female			Married	740				charles.smith@gmail.com			f
7ed11d84-40d6-4188-9368-29c68600b8b6	David	Brown	david.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	14	\N	Miss		71 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Female			Married	908				david.brown@gmail.com			f
472446eb-fa49-488d-80a9-6bfecf32202b	David	Davis	david.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mrs		81 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Male			Widowed	783				david.davis@gmail.com			f
540f58b2-a202-419f-b8f1-456365aed565	David	Garcia	david.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	21	\N	Dr		921 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Widowed	667				david.garcia@gmail.com			f
877e88ee-460b-47de-ad7b-17b8c2389300	David	Gonzalez	david.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	3	\N	Mrs		859 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			In a relationship	70				david.gonzalez@gmail.com			f
0ce7effd-0bd1-4aca-9b36-df627cc42ab3	David	Johnson	david.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	8	\N	Dr		88 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Married	158				david.johnson@gmail.com			f
014a37d3-453c-4138-8787-83a5f17a6fa6	Alejandro	 Fantino	alejandro.fantino2024@neura.com.ar	f	393c8661-3e86-4f60-b14b-2e20ef16dc4a	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	t
17d84458-a97c-4dd2-8399-f815ca848930	David	Jones	david.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	16	\N	Mr		466 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Married	546				david.jones@gmail.com			f
d9aafddd-bcbb-4ea2-88ee-f92e085d743d	David	Martinez	david.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Mrs		881 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Married	802				david.martinez@gmail.com			f
93149061-396c-48b6-a88b-5837b45dc4e9	David	Miller	david.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mrs		352 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Male			In a relationship	210				david.miller@gmail.com			f
2194a720-cfc2-42b4-94bb-3d1f697ddb87	David	Rodriguez	david.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	26	\N	Mrs		832 789 Oak St 456 Elm St 123 Main St 321 Maple Ave 654 Pine St	Male			Single	919				david.rodriguez@gmail.com			f
1f6bb8a7-aa6e-4c14-a8fb-64fce11a6f9e	David	Smith	david.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	12	\N	Mr		473 789 Oak St 654 Pine St 321 Maple Ave 456 Elm St 123 Main St	Female			Single	939				david.smith@gmail.com			f
fbd6b472-efc7-4e49-996b-aa73238f370c	Diana	Brown	diana.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	15	\N	Mrs		366 456 Elm St 789 Oak St 123 Main St 654 Pine St 321 Maple Ave	Female			In a relationship	247				diana.brown@gmail.com			f
a53c6bc4-5f9a-45a4-b3d0-8b0617f8736a	Diana	Davis	diana.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	3	\N	Mr		726 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Male			Divorced	77				diana.davis@gmail.com			f
460f7f4d-5bfc-4387-96b9-1d7f18309829	Diana	Garcia	diana.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	12	\N	Mrs		512 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Divorced	344				diana.garcia@gmail.com			f
3fcb5f54-f395-48d7-9a00-6fe987474fbe	Diana	Gonzalez	diana.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	19	\N	Mrs		399 123 Main St 654 Pine St 456 Elm St 789 Oak St 321 Maple Ave	Male			Married	872				diana.gonzalez@gmail.com			f
4839e156-342e-4470-9214-1b32bc05a50b	Diana	Johnson	diana.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	14	\N	Dr		625 321 Maple Ave 654 Pine St 123 Main St 456 Elm St 789 Oak St	Male			Married	991				diana.johnson@gmail.com			f
e25d603a-ea43-4492-9a6d-519dbbbfcdb9	Diana	Jones	diana.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	3	\N	Mr		355 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Married	80				diana.jones@gmail.com			f
7ff15af1-7f00-4b62-a878-e15b4bfd73f2	Diana	Martinez	diana.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	24	\N	Mr		787 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Divorced	300				diana.martinez@gmail.com			f
3a27ebb0-2b72-4f8c-9a48-a0be99a07c93	Diana	Miller	diana.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	0	\N	Miss		22 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			In a relationship	421				diana.miller@gmail.com			f
c78d016c-9987-4ad3-9906-74b4c6794029	Diana	Rodriguez	diana.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	7	\N	Mrs		786 789 Oak St 654 Pine St 321 Maple Ave 456 Elm St 123 Main St	Female			Single	411				diana.rodriguez@gmail.com			f
3a1dbfe6-4aa8-4715-adec-6c4efe7341fd	Diana	Smith	diana.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	12	\N	Mrs		491 789 Oak St 456 Elm St 654 Pine St 123 Main St 321 Maple Ave	Female			Widowed	845				diana.smith@gmail.com			f
b1382743-b915-4d0f-87be-57a1c9ca2cfc	Ellen	Brown	ellen.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Dr		159 789 Oak St 123 Main St 654 Pine St 321 Maple Ave 456 Elm St	Female			Single	946				ellen.brown@gmail.com			f
5d5599ef-5388-4bcf-9044-690678b16b73	Ellen	Davis	ellen.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	15	\N	Mr		506 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			In a relationship	527				ellen.davis@gmail.com			f
3576bd7a-cd25-4457-9672-3ce571f08035	Ellen	Garcia	ellen.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	24	\N	Dr		978 654 Pine St 123 Main St 321 Maple Ave 789 Oak St 456 Elm St	Male			Married	765				ellen.garcia@gmail.com			f
968766b5-5efe-41f8-9167-0818789cf158	Ellen	Gonzalez	ellen.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	10	\N	Mr		548 123 Main St 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St	Female			Divorced	39				ellen.gonzalez@gmail.com			f
9ce5bd88-5e51-4420-a2b3-6c615776ebf1	Ellen	Johnson	ellen.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	6	\N	Dr		10 789 Oak St 123 Main St 654 Pine St 321 Maple Ave 456 Elm St	Male			In a relationship	167				ellen.johnson@gmail.com			f
ba994b5d-8bd2-433d-a76d-f8687f1a641c	Ellen	Jones	ellen.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	26	\N	Mr		855 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Married	361				ellen.jones@gmail.com			f
82dbac84-574f-4e14-a38b-111ef7e0e5f9	Ellen	Martinez	ellen.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	7	\N	Dr		198 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Male			In a relationship	176				ellen.martinez@gmail.com			f
b6cb2f43-966c-4935-80c2-37fe35f943d0	Ellen	Miller	ellen.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Dr		894 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Single	442				ellen.miller@gmail.com			f
c50cd87e-c5bd-4e8b-8d7b-5d3a1f801b81	Ellen	Rodriguez	ellen.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	20	\N	Mrs		444 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			Married	453				ellen.rodriguez@gmail.com			f
321fbf57-82e5-491f-9159-ef74acd95941	Ellen	Smith	ellen.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Miss		962 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Widowed	817				ellen.smith@gmail.com			f
557367d9-70a7-475f-8ec6-54f3a72d04c9	Iryna	Brown	iryna.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mr		470 456 Elm St 654 Pine St 321 Maple Ave 789 Oak St 123 Main St	Male			Single	667				iryna.brown@gmail.com			f
bb69508b-de40-445d-8204-b9b73ccd157a	Iryna	Davis	iryna.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Dr		501 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Widowed	395				iryna.davis@gmail.com			f
3da6f403-50c1-4349-93a6-d74bb94d0970	Iryna	Garcia	iryna.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Dr		590 456 Elm St 654 Pine St 321 Maple Ave 789 Oak St 123 Main St	Female			Married	34				iryna.garcia@gmail.com			f
9696dec0-235a-4985-a780-0a43905be392	Mariano	Velasco	pepe@parada93.com	f	3f9b1ca9-a373-431d-9ef9-0889cfdf87fc	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
82c6e43a-b14c-4c41-83be-d8ffe68ade57	Iryna	Gonzalez	iryna.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	20	\N	Mrs		597 789 Oak St 123 Main St 456 Elm St 654 Pine St 321 Maple Ave	Female			Single	559				iryna.gonzalez@gmail.com			f
c3a7fccc-2aaa-48a1-8f0a-35caaf84dc32	Iryna	Johnson	iryna.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Mrs		432 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Divorced	901				iryna.johnson@gmail.com			f
a48b5d30-d3be-4473-8079-4d302fb9909d	Iryna	Jones	iryna.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	18	\N	Miss		683 456 Elm St 654 Pine St 321 Maple Ave 789 Oak St 123 Main St	Female			Divorced	184				iryna.jones@gmail.com			f
280d98c4-3889-4e2f-8a47-c4962c5053e2	Iryna	Martinez	iryna.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	6	\N	Miss		927 321 Maple Ave 654 Pine St 789 Oak St 456 Elm St 123 Main St	Male			Divorced	517				iryna.martinez@gmail.com			f
96927dd3-3004-4f1d-aa6e-2222b5be146c	Iryna	Miller	iryna.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	22	\N	Miss		236 123 Main St 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St	Male			Single	832				iryna.miller@gmail.com			f
3423c243-7841-41b6-bd15-497e0b1b98b4	Iryna	Rodriguez	iryna.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	4	\N	Mrs		220 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Female			Widowed	650				iryna.rodriguez@gmail.com			f
3cd4a123-d441-4885-8ecb-f1128313644b	Iryna	Smith	iryna.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	9	\N	Miss		220 654 Pine St 789 Oak St 456 Elm St 123 Main St 321 Maple Ave	Female			Single	902				iryna.smith@gmail.com			f
37db7de6-e93c-4aa2-a139-ad21d1346049	James	Brown	james.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	16	\N	Mr		991 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Female			Widowed	918				james.brown@gmail.com			f
28a0b592-4b6f-4720-b8d0-0c463ee0be54	James	Davis	james.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mrs		554 789 Oak St 456 Elm St 123 Main St 654 Pine St 321 Maple Ave	Female			In a relationship	789				james.davis@gmail.com			f
763ce1ce-fde2-4b6f-b0e5-f0bea495f8fa	James	Garcia	james.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	0	\N	Miss		532 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Male			Single	751				james.garcia@gmail.com			f
39308f01-017e-4d04-95ca-95a875e0de4e	James	Gonzalez	james.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	23	\N	Mrs		108 654 Pine St 321 Maple Ave 789 Oak St 123 Main St 456 Elm St	Male			In a relationship	326				james.gonzalez@gmail.com			f
59e18f10-7031-4f2e-8825-fe8b66832252	James	Johnson	james.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mr		533 456 Elm St 321 Maple Ave 654 Pine St 123 Main St 789 Oak St	Female			Married	524				james.johnson@gmail.com			f
a8d9c349-12c4-4fc3-be94-1486a013dc3f	James	Jones	james.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	29	\N	Mrs		190 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Male			In a relationship	743				james.jones@gmail.com			f
5c698ce2-8124-4843-b7ac-4250870bf039	James	Martinez	james.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	26	\N	Miss		144 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			Single	675				james.martinez@gmail.com			f
4b5a7d24-0ca4-4d1c-8c7b-9f9c3fffa03a	James	Miller	james.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Mrs		251 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			In a relationship	357				james.miller@gmail.com			f
3f52dc6a-fcdb-4d44-86f4-da00c3c0d88e	James	Rodriguez	james.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	23	\N	Miss		120 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			In a relationship	652				james.rodriguez@gmail.com			f
371b7409-bf41-4dd3-90f6-4270cc99305f	James	Smith	james.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	6	\N	Mr		815 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Male			Widowed	4				james.smith@gmail.com			f
4a71e349-65ec-40b6-8ac9-29f2a12c224e	John	Brown	john.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	14	\N	Miss		188 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Married	331				john.brown@gmail.com			f
8bba2c46-8e97-4679-ba24-57923bc749ca	John	Davis	john.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	13	\N	Mr		342 123 Main St 654 Pine St 456 Elm St 789 Oak St 321 Maple Ave	Female			Divorced	592				john.davis@gmail.com			f
80d4517a-d93e-413a-95ab-3e8657f9c9ba	John	Garcia	john.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	7	\N	Dr		628 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Widowed	747				john.garcia@gmail.com			f
f500ed07-c7e6-488c-8ef9-abfbe24f6025	John	Gonzalez	john.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	16	\N	Mrs		367 123 Main St 321 Maple Ave 654 Pine St 789 Oak St 456 Elm St	Female			Divorced	182				john.gonzalez@gmail.com			f
c9f5c618-4e3c-41ef-80d2-07b170b83cfc	John	Johnson	john.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Mr		292 321 Maple Ave 654 Pine St 123 Main St 789 Oak St 456 Elm St	Female			In a relationship	479				john.johnson@gmail.com			f
74809a86-1a76-48d7-9f16-25ac86079df6	John	Jones	john.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	6	\N	Dr		328 123 Main St 456 Elm St 654 Pine St 321 Maple Ave 789 Oak St	Female			In a relationship	669				john.jones@gmail.com			f
7c3762af-6592-4ff4-8438-84daddf0d090	John	Martinez	john.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	7	\N	Mrs		831 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Married	459				john.martinez@gmail.com			f
78a2671f-5cb1-4285-ae99-7b7047b2e595	John	Miller	john.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Mr		450 123 Main St 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St	Male			Widowed	817				john.miller@gmail.com			f
d57bca63-490a-470b-a02a-a32efb809ffe	John	Rodriguez	john.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Dr		180 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Single	470				john.rodriguez@gmail.com			f
38cab2ee-f788-4f5a-90dd-abe27d5106fc	John	Smith	john.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	3	\N	Dr		141 123 Main St 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St	Male			Widowed	468				john.smith@gmail.com			f
833d1844-aeda-4d06-8ccb-45bf5bd006b9	Joseph	Brown	joseph.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	17	\N	Mr		265 123 Main St 789 Oak St 321 Maple Ave 654 Pine St 456 Elm St	Female			Divorced	96				joseph.brown@gmail.com			f
d9edc698-c858-4fbf-8ffc-6f7f1e5811d4	Alejandro	 Fantino	alejandro.fantino6666@neura.com.ar	f	5501c9b0-f0f9-4acc-8486-4ab2613adc57	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
416953a1-b447-4b7c-805d-fe5758327e8f	Joseph	Davis	joseph.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	18	\N	Miss		885 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Widowed	335				joseph.davis@gmail.com			f
cb2c4830-3609-404c-82a8-2681934b1f3d	Joseph	Garcia	joseph.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	11	\N	Mr		36 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Single	215				joseph.garcia@gmail.com			f
6cc67b9f-a8fc-4537-b0a8-2adaca870b4e	Joseph	Gonzalez	joseph.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	10	\N	Miss		15 654 Pine St 123 Main St 321 Maple Ave 789 Oak St 456 Elm St	Male			Widowed	155				joseph.gonzalez@gmail.com			f
484ac8f0-0e4b-47b4-874c-acae6490185a	Joseph	Johnson	joseph.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	11	\N	Miss		801 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			Widowed	66				joseph.johnson@gmail.com			f
98ca2b5e-3735-439d-b8f4-57a2ccec6e29	Joseph	Jones	joseph.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	4	\N	Mr		132 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Widowed	17				joseph.jones@gmail.com			f
4f654698-7ad9-4dcf-8ecd-d4adc91a8c60	Joseph	Martinez	joseph.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	14	\N	Mrs		92 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			Divorced	127				joseph.martinez@gmail.com			f
4a04f12c-8c18-4046-a98c-da763ee734da	Joseph	Miller	joseph.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	19	\N	Mrs		344 321 Maple Ave 654 Pine St 123 Main St 456 Elm St 789 Oak St	Female			Single	358				joseph.miller@gmail.com			f
cede8108-cd5b-4d76-a859-a8cba9e86288	Joseph	Rodriguez	joseph.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	19	\N	Miss		264 321 Maple Ave 654 Pine St 789 Oak St 456 Elm St 123 Main St	Male			Single	64				joseph.rodriguez@gmail.com			f
454eb5d5-63cc-4c46-84f0-97ac8d699d64	Joseph	Smith	joseph.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Mr		830 789 Oak St 654 Pine St 321 Maple Ave 123 Main St 456 Elm St	Male			Married	962				joseph.smith@gmail.com			f
0d45bc2b-f4b9-4177-870a-5042d3c357d2	Kate	Brown	kate.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	0	\N	Dr		73 321 Maple Ave 654 Pine St 123 Main St 456 Elm St 789 Oak St	Male			Married	163				kate.brown@gmail.com			f
841b1fd3-7f03-41e0-8795-1ce6ba69d768	Kate	Davis	kate.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Mrs		753 321 Maple Ave 123 Main St 456 Elm St 789 Oak St 654 Pine St	Male			Widowed	97				kate.davis@gmail.com			f
7fd7fe7c-d723-4f0a-966b-d93f582f6d59	Kate	Garcia	kate.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	19	\N	Mrs		957 789 Oak St 654 Pine St 123 Main St 456 Elm St 321 Maple Ave	Male			In a relationship	221				kate.garcia@gmail.com			f
7b14ad12-d0b0-4283-a706-e3716acc650f	Kate	Gonzalez	kate.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	3	\N	Mrs		420 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Divorced	250				kate.gonzalez@gmail.com			f
f94d7f3a-2c12-422c-93cc-2767929543b6	Kate	Johnson	kate.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	21	\N	Mr		909 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St 123 Main St	Female			Single	982				kate.johnson@gmail.com			f
f5853dca-2b68-4337-956c-8b5a80d8bca6	Kate	Jones	kate.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	15	\N	Mr		561 456 Elm St 654 Pine St 321 Maple Ave 789 Oak St 123 Main St	Male			Divorced	314				kate.jones@gmail.com			f
36c34ed6-097b-4e82-8694-1da551f64910	Kate	Martinez	kate.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mrs		358 789 Oak St 321 Maple Ave 654 Pine St 123 Main St 456 Elm St	Female			In a relationship	315				kate.martinez@gmail.com			f
8cc8a41a-1308-4231-829e-a502704fd790	Kate	Miller	kate.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Miss		448 123 Main St 654 Pine St 456 Elm St 789 Oak St 321 Maple Ave	Female			In a relationship	867				kate.miller@gmail.com			f
f109ae06-a73b-4c29-a1e9-1d62f66c4bd9	Kate	Rodriguez	kate.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	7	\N	Mr		30 456 Elm St 123 Main St 654 Pine St 789 Oak St 321 Maple Ave	Female			Married	938				kate.rodriguez@gmail.com			f
4fb50b52-7c02-42f4-b648-6d6426a32db5	Kate	Smith	kate.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	15	\N	Dr		884 654 Pine St 321 Maple Ave 789 Oak St 123 Main St 456 Elm St	Male			Single	445				kate.smith@gmail.com			f
c20656d9-f1db-41ef-8225-8b57335dc60d	Nahuel	Barrios	nahuelbarrios@casla.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2000-01-01	0	\N	Mr	Perrito	\N	\N	\N	\N	\N	5	\N	\N	\N	nahuelbarrios@casla.com	07761318029	\N	f
8b3f6664-14c1-4f8c-81cc-9a005ddc0d04	Jorge	Ibañez	jorge@casla.com	f	8fddb239-8f82-4154-9098-ecf16345a853	1959-01-01	0	\N	Mr	El Pedo	\N	\N	\N	\N	\N	5	\N	\N	\N	jorge@casla.com	07761318029	\N	f
4cabff7a-51e8-48c0-9948-eb590caca53d	Nahuela	Barries	nahuelbarrios@casla.com	f	8fddb239-8f82-4154-9098-ecf16345a853	1959-01-01	0	\N	Miss	Perrito	\N	\N	\N	\N	\N	5	\N	\N	\N	nahuelbarrios@casla.com	07761318029	\N	f
0d4b0f80-be05-41c5-aa03-97e0de184ecf	Theresa	May	may@theresa.com	f	8fddb239-8f82-4154-9098-ecf16345a853	1959-01-01	0	\N	Miss	Theresita	\N	\N	\N	\N	\N	90	\N	\N	\N	may@theresa.com	134rt35432345	\N	f
94ce30b3-ecce-44aa-bb48-f6da32921514	Javier	 Milei	javier@libertadavanza.com.ar	f	2f7dd967-4367-4794-94de-9306612a7854	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
27866d58-2de6-45dd-83a9-4fd5fdad5412	Javier 3	Milei 3	javier3@libertadavanza.com.ar	f	b2b55608-535e-4274-aff3-332c9bcf5cb5	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
e1034681-4874-48b0-98f1-c9e7d41d09e2	Javier	 Milei	javier555@libertadavanza.com.ar	f	5164fbf6-b966-4a47-bced-6769713c65ab	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
057c7b5b-bebd-4ef5-bf56-f3cc43457869	Javier	 Milei	javier6543@libertadavanza.com.ar	f	cda2ddd2-d9ee-4df0-bd3f-e4c679bb8c55	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
68a00048-2a7b-41db-a29f-4e18154c6236	Javier	 Milei	javier999999@libertadavanza.com.ar	f	0dfae1ed-16cb-46b9-aaf5-b5df563cc90a	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
fcaa2341-e98e-4021-a983-db0960b8acd0	Javier	 Milei	jaasdfasdfasdfasdvier@libertadavanza.com.ar	f	fea594ef-19f3-490b-a9dc-172d663f874b	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
e4442e63-7a74-47ad-9ab6-047ed7e6e2c6	Alejandro	 Fantino	alejandro.fantino6666@neura.com.ar	f	04a01fa8-f9d2-4a3a-a7c1-e73de7f1ebca	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
b81b6de3-6efe-4d2e-8c08-d1c4769e120f	Alejandro	 Fantino	alejandro.fantino7777@neura.com.ar	f	f950ca75-e7c6-44a5-8fcf-28313e727609	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	f
7bf56177-8a14-4f07-8d23-246949d65955	Michela	Macalister	florence@ndthemachine.com	f	69609916-442b-413d-a439-c3e505b9d000	1983-01-01	0	/avatars/7bf56177-8a14-4f07-8d23-246949d65955/7bf56177-8a14-4f07-8d23-246949d65955.jpg	Miss	Flo	SULBY COTTAGE\n6 ASHLEY ROAD	female	\N	she/her	\N	22	\N	\N	\N	florence@ndthemachine.com	07966614392	\N	f
727910f3-4c49-4b13-b781-efc654044e29	Mariano	Velasco	mariano.juri@yahoo.co.uk	f	69609916-442b-413d-a439-c3e505b9d000	\N	0	/avatars/727910f3-4c49-4b13-b781-efc654044e29/727910f3-4c49-4b13-b781-efc654044e29.jpg	Mr	\N	19B, Longbridge Road\nLongbridge Road	\N	\N	\N	\N	4	\N	\N	\N	mariano.juri@yahoo.co.uk	07761318029	\N	t
\.


--
-- Data for Name: role_positions; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.role_positions (identifier, requested_by, grade, salary_level, job_description, contract_type, date_created, start_date, header, status, assigned, log, file, file_name) FROM stdin;
18a05e2d-01b4-42a3-b567-02b42e8f5373	7bf56177-8a14-4f07-8d23-246949d65955	\N	level2	\N	type2	\N	2024-03-29	teton teton que grande sos		727910f3-4c49-4b13-b781-efc654044e29	 by florence@ndthemachine.com	\N	\N
74737c6e-b07d-4ad7-9d1c-b92eb5f0956e	7bf56177-8a14-4f07-8d23-246949d65955	\N	level3	\N	type3	\N	2024-03-25	This is a header		7bf56177-8a14-4f07-8d23-246949d65955	 by florence@ndthemachine.com	\N	\N
271adaab-aa1e-46d0-98f5-d7f3fc62c53a	7bf56177-8a14-4f07-8d23-246949d65955	\N	level2	\N	type3	\N	2024-03-19	#4		7bf56177-8a14-4f07-8d23-246949d65955	 by florence@ndthemachine.com	\N	\N
4636d929-c41e-4487-b463-d257b6478830	7bf56177-8a14-4f07-8d23-246949d65955	\N	level2	\N	type3	\N	2024-03-01	this is a test of Header	ARCHIVED	7bf56177-8a14-4f07-8d23-246949d65955	 by florence@ndthemachine.com	\N	\N
c63e330a-4273-4f85-a20e-15ee29b17795	7bf56177-8a14-4f07-8d23-246949d65955	\N	level2	<p>test</p>	Fixed-Term Contract	\N	2024-03-30	Principal Software Engineer	REQUESTED	727910f3-4c49-4b13-b781-efc654044e29	 by florence@ndthemachine.com	/files/87a0516a-3983-412b-b5ff-91e707a66f7a/SN1712 TT34 Web.pdf	\N
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.roles (key, description) FROM stdin;
HR-USER	\N
MANAGER	\N
ROOT	Super user
HR-ADMIN	Admin for HR application
\.


--
-- Data for Name: settings; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.settings (identifier, mailsmtphost, mailsmtpport, mailsmtpauth, mailsmtpstarttlsenable, mailusername, mailpassword, linkelntoken) FROM stdin;
69609916-442b-413d-a439-c3e505b9d000	smtp.gmail.com	587	t	t	mariano.juri@gmail.com	H4ngth3DJ	\N
\.


--
-- Data for Name: shareddocument_profile; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.shareddocument_profile (profile_identifier, document_identifier) FROM stdin;
727910f3-4c49-4b13-b781-efc654044e29	42ad4a0c-dcb6-488c-8d0f-04966aa9af08
7bf56177-8a14-4f07-8d23-246949d65955	987a17e7-4d3a-4f46-ae59-64e6611d24a7
7bf56177-8a14-4f07-8d23-246949d65955	592a417f-d9a8-44a8-95df-2fac6b23fa0c
7bf56177-8a14-4f07-8d23-246949d65955	bdc53017-6316-478c-883f-98ab79b127ca
7bf56177-8a14-4f07-8d23-246949d65955	99987918-8932-420a-84f1-b5114a7e7068
727910f3-4c49-4b13-b781-efc654044e29	77578fe6-20c0-4dff-a867-36d5988cd3a6
7bf56177-8a14-4f07-8d23-246949d65955	77578fe6-20c0-4dff-a867-36d5988cd3a6
7bf56177-8a14-4f07-8d23-246949d65955	e2b7fa6a-44b1-4f7a-87ac-9520355190a7
727910f3-4c49-4b13-b781-efc654044e29	4d674bb3-b5f5-4977-8238-a47119c681a6
\.


--
-- Data for Name: supporttickets; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.supporttickets (identifier, title, body, profile_identifier, account_identifier, status, answer, date, type, moddate) FROM stdin;
50116086-cb75-464a-a798-172c5d6a1cb1	test	<p>test</p>	727910f3-4c49-4b13-b781-efc654044e29	69609916-442b-413d-a439-c3e505b9d000	\N	\N	\N	Technical Support	\N
6bec2b0a-78e3-4787-91bd-ca207d7cdbc6	test 2	<p>test</p>	727910f3-4c49-4b13-b781-efc654044e29	69609916-442b-413d-a439-c3e505b9d000	REQUESTED	\N	2024-04-27 14:47:51.426	Technical Support	2024-04-27 14:47:51.426
96e956e5-d713-439b-8d1c-f03788f4bff2	Ticket 3	<p>bla bla bla</p>	7bf56177-8a14-4f07-8d23-246949d65955	69609916-442b-413d-a439-c3e505b9d000	REQUESTED	\N	2024-04-27 14:56:49.847	Sales	2024-04-27 14:56:49.847
\.


--
-- Data for Name: todo; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.todo (identifier, profile_identifier, type, text, done, read, date_created, mod_date, status, title) FROM stdin;
f7276579-aa1a-46cc-a30d-3bbf12d02df6	014a37d3-453c-4138-8787-83a5f17a6fa6	TASK	\N	f	t	2024-04-08	\N	PENDING	hello Nia
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.users (user_name, password) FROM stdin;
jordan@gmail.com	password1
jorgep@gmail.com	password1
johndoe@gmail.com	0X2sDum4
misstheresa@gmail.com	dQ9nEXYI
nahuelbarrios@casla.com	BuZGPXFi
mariano.juri@yahoo.co.uk	password1
javier@libertadavanza.com.ar	M1lei@2023
javier3@libertadavanza.com.ar	M1lei@2023
javier555@libertadavanza.com.ar	M1lei@2023
javier6543@libertadavanza.com.ar	M1lei@2023
javier999999@libertadavanza.com.ar	M1lei@2023
jaasdfasdfasdfasdvier@libertadavanza.com.ar	M1lei@2023
alejandro.fantino6666@neura.com.ar	password1
alejandro.fantino7777@neura.com.ar	password1
alejandro.fantino2024@neura.com.ar	password1
pepe@parada93.com	password1
florence@ndthemachine.com	password3
\.


--
-- Name: accesses_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.accesses
    ADD CONSTRAINT accesses_pkey PRIMARY KEY (token);


--
-- Name: accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (identifier);


--
-- Name: annoucement_audience_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.annoucement_audience
    ADD CONSTRAINT annoucement_audience_pkey PRIMARY KEY (annoucement_identifier, profile_identifier);


--
-- Name: annoucement_comments_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.annoucement_comments
    ADD CONSTRAINT annoucement_comments_pkey PRIMARY KEY (identifier);


--
-- Name: annoucement_group_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.annoucement_group
    ADD CONSTRAINT annoucement_group_pkey PRIMARY KEY (annoucement_identifier, group_identifier);


--
-- Name: annoucements_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.annoucements
    ADD CONSTRAINT annoucements_pkey PRIMARY KEY (identifier);


--
-- Name: contracts_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.contracts
    ADD CONSTRAINT contracts_pkey PRIMARY KEY (identifier);


--
-- Name: documents_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_pkey PRIMARY KEY (identifier);


--
-- Name: event_attendee_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_pkey PRIMARY KEY (event_identifier, profile_identifier);


--
-- Name: events_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (identifier);


--
-- Name: goals_performance_reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.goal_performance_review
    ADD CONSTRAINT goals_performance_reviews_pkey PRIMARY KEY (goal_identifier, performance_review_identifier);


--
-- Name: goals_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.goals
    ADD CONSTRAINT goals_pkey PRIMARY KEY (identifier);


--
-- Name: groups_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (identifier);


--
-- Name: holidays_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.holidays
    ADD CONSTRAINT holidays_pkey PRIMARY KEY (identifier);


--
-- Name: invoice_item_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoice_item
    ADD CONSTRAINT invoice_item_pkey PRIMARY KEY (identifier);


--
-- Name: invoice_payment_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoice_payment
    ADD CONSTRAINT invoice_payment_pkey PRIMARY KEY (invoice_identifier, payment_identifier);


--
-- Name: invoices_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_pkey PRIMARY KEY (identifier);


--
-- Name: leads_email_key; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT leads_email_key UNIQUE (email);


--
-- Name: leads_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT leads_pkey PRIMARY KEY (identifier);


--
-- Name: notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (identifier);


--
-- Name: payments_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (identifier);


--
-- Name: performance_reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.performance_reviews
    ADD CONSTRAINT performance_reviews_pkey PRIMARY KEY (identifier);


--
-- Name: profile_group_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profile_group
    ADD CONSTRAINT profile_group_pkey PRIMARY KEY (profile_identifier, group_identifier);


--
-- Name: profile_role_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profile_role
    ADD CONSTRAINT profile_role_pkey PRIMARY KEY (profile_identifier, role_key);


--
-- Name: profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_pkey PRIMARY KEY (identifier);


--
-- Name: role_positions_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.role_positions
    ADD CONSTRAINT role_positions_pkey PRIMARY KEY (identifier);


--
-- Name: roles_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (key);


--
-- Name: settings_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_pkey PRIMARY KEY (identifier);


--
-- Name: shareddocument_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.shareddocument_profile
    ADD CONSTRAINT shareddocument_profile_pkey PRIMARY KEY (profile_identifier, document_identifier);


--
-- Name: supporttickets_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.supporttickets
    ADD CONSTRAINT supporttickets_pkey PRIMARY KEY (identifier);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_name);


--
-- Name: annoucement_audience_annoucement_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.annoucement_audience
    ADD CONSTRAINT annoucement_audience_annoucement_identifier_fkey FOREIGN KEY (annoucement_identifier) REFERENCES public.annoucements(identifier);


--
-- Name: annoucement_audience_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.annoucement_audience
    ADD CONSTRAINT annoucement_audience_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: annoucement_comments_annoucement_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.annoucement_comments
    ADD CONSTRAINT annoucement_comments_annoucement_identifier_fkey FOREIGN KEY (annoucement_identifier) REFERENCES public.annoucements(identifier);


--
-- Name: annoucement_comments_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.annoucement_comments
    ADD CONSTRAINT annoucement_comments_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: annoucement_group_annoucement_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.annoucement_group
    ADD CONSTRAINT annoucement_group_annoucement_identifier_fkey FOREIGN KEY (annoucement_identifier) REFERENCES public.annoucements(identifier);


--
-- Name: annoucement_group_group_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.annoucement_group
    ADD CONSTRAINT annoucement_group_group_identifier_fkey FOREIGN KEY (group_identifier) REFERENCES public.groups(identifier);


--
-- Name: contracts_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.contracts
    ADD CONSTRAINT contracts_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: documents_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: event_attendee_event_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_event_identifier_fkey FOREIGN KEY (event_identifier) REFERENCES public.events(identifier);


--
-- Name: event_attendee_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: events_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: goals_performance_reviews_goal_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.goal_performance_review
    ADD CONSTRAINT goals_performance_reviews_goal_identifier_fkey FOREIGN KEY (goal_identifier) REFERENCES public.goals(identifier);


--
-- Name: goals_performance_reviews_performance_review_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.goal_performance_review
    ADD CONSTRAINT goals_performance_reviews_performance_review_identifier_fkey FOREIGN KEY (performance_review_identifier) REFERENCES public.performance_reviews(identifier);


--
-- Name: goals_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.goals
    ADD CONSTRAINT goals_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: holidays_authorized_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.holidays
    ADD CONSTRAINT holidays_authorized_by_fkey FOREIGN KEY (authorized_by) REFERENCES public.profiles(identifier);


--
-- Name: holidays_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.holidays
    ADD CONSTRAINT holidays_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: invoice_item_invoice_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoice_item
    ADD CONSTRAINT invoice_item_invoice_identifier_fkey FOREIGN KEY (invoice_identifier) REFERENCES public.invoices(identifier);


--
-- Name: invoice_payment_invoice_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoice_payment
    ADD CONSTRAINT invoice_payment_invoice_identifier_fkey FOREIGN KEY (invoice_identifier) REFERENCES public.invoices(identifier);


--
-- Name: invoice_payment_payment_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoice_payment
    ADD CONSTRAINT invoice_payment_payment_identifier_fkey FOREIGN KEY (payment_identifier) REFERENCES public.payments(identifier);


--
-- Name: invoices_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: leads_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT leads_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: leads_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT leads_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: notifications_owner_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_owner_identifier_fkey FOREIGN KEY (owner_identifier) REFERENCES public.profiles(identifier);


--
-- Name: notifications_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: organization_chart_child_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.organization_chart
    ADD CONSTRAINT organization_chart_child_identifier_fkey FOREIGN KEY (child_identifier) REFERENCES public.profiles(identifier);


--
-- Name: organization_chart_parent_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.organization_chart
    ADD CONSTRAINT organization_chart_parent_identifier_fkey FOREIGN KEY (parent_identifier) REFERENCES public.profiles(identifier);


--
-- Name: performance_reviews_employee_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.performance_reviews
    ADD CONSTRAINT performance_reviews_employee_identifier_fkey FOREIGN KEY (employee_identifier) REFERENCES public.profiles(identifier);


--
-- Name: performance_reviews_reviewer_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.performance_reviews
    ADD CONSTRAINT performance_reviews_reviewer_identifier_fkey FOREIGN KEY (reviewer_identifier) REFERENCES public.profiles(identifier);


--
-- Name: profile_group_group_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profile_group
    ADD CONSTRAINT profile_group_group_identifier_fkey FOREIGN KEY (group_identifier) REFERENCES public.groups(identifier);


--
-- Name: profile_group_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profile_group
    ADD CONSTRAINT profile_group_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: profile_group_profile_identifier_fkey1; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profile_group
    ADD CONSTRAINT profile_group_profile_identifier_fkey1 FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: profile_role_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profile_role
    ADD CONSTRAINT profile_role_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: profile_role_role_key_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profile_role
    ADD CONSTRAINT profile_role_role_key_fkey FOREIGN KEY (role_key) REFERENCES public.roles(key);


--
-- Name: profiles_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: role_positions_assigned_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.role_positions
    ADD CONSTRAINT role_positions_assigned_fkey FOREIGN KEY (assigned) REFERENCES public.profiles(identifier);


--
-- Name: role_positions_requested_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.role_positions
    ADD CONSTRAINT role_positions_requested_by_fkey FOREIGN KEY (requested_by) REFERENCES public.profiles(identifier);


--
-- Name: settings_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_identifier_fkey FOREIGN KEY (identifier) REFERENCES public.accounts(identifier);


--
-- Name: shareddocument_profile_document_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.shareddocument_profile
    ADD CONSTRAINT shareddocument_profile_document_identifier_fkey FOREIGN KEY (document_identifier) REFERENCES public.documents(identifier);


--
-- Name: shareddocument_profile_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.shareddocument_profile
    ADD CONSTRAINT shareddocument_profile_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: supporttickets_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.supporttickets
    ADD CONSTRAINT supporttickets_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: supporttickets_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.supporttickets
    ADD CONSTRAINT supporttickets_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: mvelasco
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM mvelasco;
GRANT ALL ON SCHEMA public TO mvelasco;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

