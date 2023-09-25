--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

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
-- Name: accesses; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.accesses (
    token character varying(50) NOT NULL,
    user_name character varying(50) NOT NULL,
    start timestamp without time zone DEFAULT now() NOT NULL,
    ends timestamp without time zone
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
-- Name: contracts; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.contracts (
    identifier character varying(50) NOT NULL,
    start_date timestamp without time zone NOT NULL,
    cont_service timestamp without time zone NOT NULL,
    contract_end_date timestamp without time zone,
    onboard_date timestamp without time zone,
    onboard_contract character varying(50),
    benefits_start timestamp without time zone,
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
    right_to_work_expires timestamp without time zone,
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
    date_created date DEFAULT ('now'::text)::date NOT NULL
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
    date_created date DEFAULT CURRENT_DATE NOT NULL,
    mod_date date DEFAULT CURRENT_DATE NOT NULL,
    includesaturday boolean DEFAULT false,
    includesunday boolean DEFAULT false,
    all_day boolean DEFAULT false,
    title character varying(300)
);


ALTER TABLE public.events OWNER TO mvelasco;

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
    halfdayend boolean DEFAULT false
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
    home_phone character varying(20)
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
    contract_type character varying(10),
    date_created date DEFAULT now(),
    start_date date DEFAULT now(),
    header character varying(400) NOT NULL,
    status character varying(20) DEFAULT 'REQUESTED'::character varying,
    assigned character varying(50)
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
-- Name: shareddocument_profile; Type: TABLE; Schema: public; Owner: mvelasco
--

CREATE TABLE public.shareddocument_profile (
    profile_identifier character varying(50) NOT NULL,
    document_identifier character varying(50) NOT NULL
);


ALTER TABLE public.shareddocument_profile OWNER TO mvelasco;

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

COPY public.accesses (token, user_name, start, ends) FROM stdin;
7d6ca95b-896a-4163-addb-bf76fc3edede	johndoe@gmail.com	2023-04-20 16:25:29.266	\N
dd208ce6-5d8e-4077-bbba-bca01bf73c95	mariano.juri@yahoo.co.uk	2023-04-20 16:27:19.788	\N
0c53482b-fe2b-44d9-8e78-38fdd46c5483	florence@ndthemachine.com	2023-04-26 21:04:54.548	\N
e3b34185-539e-4d96-84ed-91eaa9c9f750	florence@ndthemachine.com	2023-04-26 21:05:03.533	\N
ac703011-97be-40bd-a2d9-84ae64a208d9	florence@ndthemachine.com	2023-04-26 21:05:48.204	\N
2e1b5e43-ee5e-45f8-a459-f738bcde0116	mariano.juri@yahoo.co.uk	2023-05-07 17:25:37.903	\N
93586011-a949-4c44-ba53-335140f2fe83	mariano.juri@yahoo.co.uk	2023-05-07 17:26:19.704	\N
b162801e-507a-4e20-925d-eb6ce6c84c7f	florence@ndthemachine.com	2023-05-07 17:28:23.916	\N
3856b995-1361-465d-a03c-0dfb5b1342f3	jorgep@gmail.com	2023-05-07 17:29:16.907	\N
24fd549c-16d6-4566-b75a-1cbeeadddd18	mariano.juri@yahoo.co.uk	2023-05-07 17:31:28.721	\N
4e6766cd-0097-4048-94bf-7f4e0a4fde9f	florence@ndthemachine.com	2023-04-26 20:46:07.371	\N
a4c98aa0-ca13-49e9-a7e6-20a017fb719e	florence@ndthemachine.com	2023-04-26 20:46:23.318	\N
50767bd7-6e79-409b-81b1-0212d13257aa	florence@ndthemachine.com	2023-04-26 21:28:14.568	\N
b8ffcab4-cf02-4821-ada8-468d1cf959c3	mariano.juri@yahoo.co.uk	2023-04-26 21:29:30.058	\N
1a54606e-91d3-4b8f-adf6-58e07b50833b	mariano.juri@yahoo.co.uk	2023-05-30 16:28:52.304	\N
ed4c6bcc-311c-4b06-85b4-672eda7c368d	johndoe@gmail.com	2023-04-20 16:23:52.878	\N
86e00511-9839-415a-af1c-97b868a7c807	mariano.juri@yahoo.co.uk	2023-06-16 16:30:55.549	\N
497a27c0-5f2b-4a39-80ee-bfad62a48df3	mariano.juri@yahoo.co.uk	2023-06-16 16:31:12.221	\N
d9533c3e-a691-40a8-9184-ff05f5a17cc6	mariano.juri@yahoo.co.uk	2023-06-16 16:31:23.473	\N
2878517c-69ff-42f5-a6dd-3c12791cbb32	mariano.juri@yahoo.co.uk	2023-06-16 16:37:21.192	\N
66451fa4-f2cb-4c30-90e3-512251f51387	mariano.juri@yahoo.co.uk	2023-06-16 16:37:30.422	\N
20ce002a-3ccc-4b22-aa49-7f273a5d236f	mariano.juri@yahoo.co.uk	2023-06-16 16:38:46.458	\N
6236f37b-c6df-4abf-844b-0ea5d3a470fe	mariano.juri@yahoo.co.uk	2023-06-16 16:39:26.866	\N
9b5e9cab-8f8d-4e0a-8856-960b37f90b49	mariano.juri@yahoo.co.uk	2023-06-16 16:40:35.571	\N
ca13422c-7e8c-46e8-abb8-75b872ab7f94	mariano.juri@yahoo.co.uk	2023-06-16 16:40:40.579	\N
c864ce6b-01b9-494b-aa1e-81729ffa4131	mariano.juri@yahoo.co.uk	2023-06-16 16:41:11.055	\N
ff67e71b-fdb1-4ad3-9c68-35ff9c78b9e1	mariano.juri@yahoo.co.uk	2023-06-16 16:42:35.166	\N
31255e58-d57c-4a8e-85a1-feb3fe723789	mariano.juri@yahoo.co.uk	2023-06-16 16:42:51.787	\N
fb78b6dc-3497-4ab0-99f3-3f4ebd5cbc02	mariano.juri@yahoo.co.uk	2023-06-16 16:46:46.186	\N
105c9a54-edbd-4a57-bd08-24fdef8ca1ea	mariano.juri@yahoo.co.uk	2023-06-16 17:01:59.36	\N
344a1c4c-51e9-482d-89e1-7b82a85df11f	mariano.juri@yahoo.co.uk	2023-06-16 17:06:57.833	\N
04c96449-5797-4d90-b3ae-035abe6a4430	mariano.juri@yahoo.co.uk	2023-06-16 17:07:02.456	\N
0c79fead-f8fc-4f13-87e1-f3962e7b86a5	mariano.juri@yahoo.co.uk	2023-06-16 17:07:15.703	\N
529f9d61-c6d9-421b-ac49-f79c1fd3cda3	mariano.juri@yahoo.co.uk	2023-06-16 17:07:24.082	\N
bb4c901b-352b-4889-a667-91916a108558	mariano.juri@yahoo.co.uk	2023-06-16 17:07:30.193	\N
21d7b825-b6fa-47a3-a94f-9594a7cebae8	mariano.juri@yahoo.co.uk	2023-06-16 17:07:54.826	\N
a8c98529-0de0-4538-98cc-b9c1dbf45e7a	mariano.juri@yahoo.co.uk	2023-06-16 17:08:47.254	\N
75294417-3c20-4bc0-a0ab-80f175710c52	mariano.juri@yahoo.co.uk	2023-06-16 17:09:41.071	\N
2c779ead-682d-46dd-81ac-c51942b9035a	mariano.juri@yahoo.co.uk	2023-06-28 11:40:17.881	2023-06-28 13:41:51.409
3ead7f19-c379-4146-9660-c462be5d34e3	mariano.juri@yahoo.co.uk	2023-06-28 13:42:18.423	2023-06-28 13:42:46.417
e1b1f86f-9460-4ce6-907a-e0efa927830c	mariano.juri@yahoo.co.uk	2023-06-16 17:11:34.973	2023-06-16 18:05:02.846
0fb2b011-93ee-4d5b-8e65-d3928c601a9f	mariano.juri@yahoo.co.uk	2023-06-16 18:06:14.96	2023-06-16 18:06:25.355
f6e278f3-ca6b-4569-93d6-2385d870312b	mariano.juri@yahoo.co.uk	2023-06-16 18:07:00.89	2023-06-16 18:07:13.51
c6e94925-5425-4b26-a756-1d90d49c84ea	mariano.juri@yahoo.co.uk	2023-06-16 18:08:09.298	2023-06-16 18:14:45.079
e3bbbab5-2c81-4c05-bac2-dc5f53f2fc82	mariano.juri@yahoo.co.uk	2023-06-16 18:14:58.748	2023-06-16 18:15:11.356
84a909b0-a962-49af-9392-2c64e047af1e	mariano.juri@yahoo.co.uk	2023-06-16 18:15:29.023	2023-06-16 18:17:11.535
8af87da8-175c-465a-8cfc-d6194e5b6f92	mariano.juri@yahoo.co.uk	2023-06-16 18:48:54.087	2023-06-16 18:50:37.941
ea3a80c1-3638-4194-9e9b-926541b752f6	florence@ndthemachine.com	2023-06-16 18:50:55.784	2023-06-16 18:53:46.697
a8c3da6f-1a90-43c2-bf77-25fe36318038	mariano.juri@yahoo.co.uk	2023-06-16 18:53:58.193	2023-06-16 19:25:37.484
366715e5-7e0a-4b75-b0df-590ae5267ebc	florence@ndthemachine.com	2023-06-16 19:25:45.348	2023-06-16 19:27:53.291
cddc5e5e-a451-4ea8-bbaa-3fb179d31856	mariano.juri@yahoo.co.uk	2023-06-16 19:28:04.228	2023-06-16 19:43:26.042
feb7ace9-0a60-42a9-bdda-dd1b7be57b49	mariano.juri@yahoo.co.uk	2023-06-16 19:43:36.138	2023-06-28 09:55:36.707
37a17868-2ff8-4f6e-bda4-14d53baba193	florence@ndthemachine.com	2023-06-28 09:55:46.482	2023-06-28 09:56:38.628
7b905d1b-4401-46fa-b587-72eef8072e47	mariano.juri@yahoo.co.uk	2023-06-28 09:56:49.727	2023-06-28 10:30:18.943
d89a4a50-a889-4b9e-8f41-6ba02e87868d	florence@ndthemachine.com	2023-06-28 10:30:28.097	2023-06-28 11:40:04.146
82586e09-212a-4f3c-a8d3-d564b0478b47	florence@ndthemachine.com	2023-06-28 13:42:54.131	2023-06-28 13:43:15.777
39efa7a7-d45c-4254-a8bc-a5a7a1e451ac	mariano.juri@yahoo.co.uk	2023-06-28 13:43:33.635	\N
9595afca-2ebb-4c05-86bf-e2626cb9541a	mariano.juri@yahoo.co.uk	2023-08-16 16:43:30.529	\N
fa30fe4f-72e6-4f62-b5e5-8663ef8a7743	alejandro.fantino6666@neura.com.ar	2023-08-16 17:01:44.935	\N
4c6c86cc-c307-449a-aede-3ee21b2e07e2	alejandro.fantino7777@neura.com.ar	2023-08-16 17:49:11.698	2023-08-18 12:15:05.545
5088b090-d33c-4f0a-8c63-922ae9f9de74	mariano.juri@yahoo.co.uk	2023-08-23 11:09:57.104	2023-08-23 14:45:38.215
2438431d-9e59-40d7-9506-5f584cd0c2e5	mariano.juri@yahoo.co.uk	2023-08-23 14:45:43.367	2023-08-23 15:15:42.669
0765d99e-099b-4707-8769-7151e79e8e7b	mariano.juri@yahoo.co.uk	2023-08-23 15:19:51.047	2023-09-06 09:41:59.523
6dc5760c-a854-4a89-b11d-0b47559583bd	mariano.juri@yahoo.co.uk	2023-09-06 09:44:13.848	\N
cadfbdce-b247-41c6-8711-bf406b179887	mariano.juri@yahoo.co.uk	2023-09-08 12:25:59.383	\N
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
\.


--
-- Data for Name: contracts; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.contracts (identifier, start_date, cont_service, contract_end_date, onboard_date, onboard_contract, benefits_start, review_date, job_type, country, office_role, location, department, team, cost_centre, line_manager, right_to_work, profile_identifier, length_of_service, notice_period, hours_per_week, days_per_week, fte, work_pattern, my_line_manager, right_to_work_expires, holiday_entitlement, holiday_brought_forward) FROM stdin;
9c5968c3-7f88-49e4-8ece-11c4e6204eb6	1990-01-01 00:00:00	1990-01-01 00:00:00	\N	\N	\N	1990-01-01 00:00:00	\N			\N			\N	\N	\N		\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
df7b8005-ff16-4948-9647-a9dd8723e76a	1990-01-01 00:00:00	1990-01-01 00:00:00	\N	\N	\N	1990-01-01 00:00:00	\N			\N			\N	\N	\N		\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
bdff0272-a623-4c9f-b1d8-f453aee03e05	1990-01-01 00:00:00	1990-01-01 00:00:00	\N	\N	\N	1990-01-01 00:00:00	\N		Endland	\N			\N	\N	\N		\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
17ba9a47-2fde-40fb-aa84-b8ce4b1469fb	2022-03-02 00:00:00	2013-01-01 00:00:00	\N	\N	\N	2013-01-01 00:00:00	\N	Singer	United Kingdom	\N	South London	Music	\N	\N	\N	d	\N	50	3 months	0	0	0	d	d	2013-01-01 00:00:00	0	0
972d58cc-009b-4a2e-9a0f-2ab3d4ffc750	2022-03-02 00:00:00	2013-01-01 00:00:00	\N	\N	\N	2013-01-01 00:00:00	\N	Singer	United Kingdom	\N	South London	Music	\N	\N	\N	d	7bf56177-8a14-4f07-8d23-246949d65955	50	3 months	0	0	0	d	lll	2013-01-01 00:00:00	0	0
cb55e7a0-d4dc-43a4-8eb4-d66c77bb3c90	2023-08-29 08:10:12	2023-08-30 08:10:12	\N	\N	\N	2023-08-29 00:00:00	\N	12	12	\N	12	12	\N	\N	\N	12	727910f3-4c49-4b13-b781-efc654044e29	1	12	0	0	0	12		\N	30	5
\.


--
-- Data for Name: documents; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.documents (identifier, img, profile_identifier, name, date_created) FROM stdin;
\.


--
-- Data for Name: event_attendee; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.event_attendee (event_identifier, profile_identifier, status) FROM stdin;
200bd6b6-ac24-4f4c-9c3b-e9328ab6aea0	727910f3-4c49-4b13-b781-efc654044e29	\N
200bd6b6-ac24-4f4c-9c3b-e9328ab6aea0	7bf56177-8a14-4f07-8d23-246949d65955	\N
d2d922c4-ae03-4a28-8978-33440fac49b3	7bf56177-8a14-4f07-8d23-246949d65955	\N
fc6dfdcf-2509-46da-9b3a-d0ba4c888257	727910f3-4c49-4b13-b781-efc654044e29	\N
22a6547c-2cb0-492e-ab53-81b12ff67e08	727910f3-4c49-4b13-b781-efc654044e29	\N
22a6547c-2cb0-492e-ab53-81b12ff67e08	7bf56177-8a14-4f07-8d23-246949d65955	\N
\.


--
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.events (identifier, start, end_date, description, location, url, label, status, profile_identifier, date_created, mod_date, includesaturday, includesunday, all_day, title) FROM stdin;
200bd6b6-ac24-4f4c-9c3b-e9328ab6aea0	2023-07-24 08:30:00	2023-07-26 09:00:00	Test with Attendees 33	\N	\N	Important	\N	727910f3-4c49-4b13-b781-efc654044e29	2023-07-10	2023-07-20	f	f	f	Test with Attendees 44
d2d922c4-ae03-4a28-8978-33440fac49b3	2023-07-17 09:00:00	2023-07-17 10:00:00	Test with attendees #3	\N	\N	Important	\N	727910f3-4c49-4b13-b781-efc654044e29	2023-07-10	2023-07-10	f	f	f	Test with attendees #3
fc6dfdcf-2509-46da-9b3a-d0ba4c888257	2023-07-14 09:00:00	2023-07-21 09:30:00	test1	\N	https://teams.microsoft.com/dl/launcher/launcher.html?url=%2F_%23%2Fl%2Fmeetup-join%2F19%3Ameeting_MDY2YTg5YTgtZjczOS00ZGMzLThkYmEtNWQ5YzBkNzhmODhh%40thread.v2%2F0%3Fcontext%3D%257b%2522Tid%2522%253a%252235c7930b-3d66-461d-9cf9-b0f8b54754a9%2522%252c%2522Oid%2522%253a%2522096eefcc-0762-421d-8078-c7b0e17d07b0%2522%257d%26anon%3Dtrue&type=meetup-join&deeplinkId=87dd5432-46a1-4525-94e3-9b9a50f8f43e&directDl=true&msLaunch=true&enableMobilePage=true&suppressPrompt=true	Important	\N	727910f3-4c49-4b13-b781-efc654044e29	2023-07-14	2023-07-14	f	f	f	Title
22a6547c-2cb0-492e-ab53-81b12ff67e08	2023-07-14 10:00:00	2023-07-26 10:00:00	test	\N	\N	Business	\N	727910f3-4c49-4b13-b781-efc654044e29	2023-07-14	2023-07-14	f	f	t	Title 35678
\.


--
-- Data for Name: goals; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.goals (identifier, name, target, deadline, profile_identifier, progress, description) FROM stdin;
0be1c20f-e1c7-42d5-9218-6086ae7cd984	Goal 2	50	2023-09-01	727910f3-4c49-4b13-b781-efc654044e29	15	\N
a72fa753-dcb6-4307-952c-6af15a0b42ab	MD JURI VELASCO	50	2023-10-31	727910f3-4c49-4b13-b781-efc654044e29	20	\N
b84985b8-d2b0-4e7c-b2c3-cb62a4dc1264	TEST GOAL	50	2023-11-08	727910f3-4c49-4b13-b781-efc654044e29	16	<p>This is a<strong> description! </strong>Can you see it?</p>
1719275f-62ab-483b-bd73-429c91e9a757	TEST 123	70	2023-09-23	727910f3-4c49-4b13-b781-efc654044e29	45	<p style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">Black Friday is almost here, and we want to help you outsell your competition.</p>\n<p style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">We want 2023 to be the year when you&nbsp;<strong>break your revenue record</strong>&hellip;</p>\n<p style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">So, we put together 4 resources that will help you make this a reality:</p>\n<ul style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; margin: 0px 0px 20px 40px; padding: 0px;">\n<li style="margin-left: 15px;"><a class="m_-3194041646948202551intercom-content-link" style="line-height: 19.5px; margin: 0px; padding: 0px; color: #1251ba !important;" href="https://optinmonster.intercom-clicks.com/via/e?ob=sxZzrHwLIwsfd%2FJShL%2Bnm2L7XOXHIjGthZED5I9kjuQurYgU6wtaV91SQnV0ecNMXXL9IGFjeAhkd9rwdmGdciry1ShjrRUAZa8MaN7eIG7Y6aoytSGd7C5nzQH2XxXkHRrhsVHwx%2F6DVSAwXA3xZoT%2BUayqrFdcOB7x8pEJpZIeEQgAOuLVIPc0vuQNoWF8cc4oXZgxyjA9C9EFiQS7Pw%3D%3D&amp;h=4fc7ea4b07e343d4180aec46d962dab30783f1a6-yot32p6u_87845615541892&amp;l=7b2d8ed2b22ee7334751a558ed5f0867b2319cb0-82716564" target="_blank" rel="noopener" data-saferedirecturl="https://www.google.com/url?q=https://optinmonster.intercom-clicks.com/via/e?ob%3DsxZzrHwLIwsfd%252FJShL%252Bnm2L7XOXHIjGthZED5I9kjuQurYgU6wtaV91SQnV0ecNMXXL9IGFjeAhkd9rwdmGdciry1ShjrRUAZa8MaN7eIG7Y6aoytSGd7C5nzQH2XxXkHRrhsVHwx%252F6DVSAwXA3xZoT%252BUayqrFdcOB7x8pEJpZIeEQgAOuLVIPc0vuQNoWF8cc4oXZgxyjA9C9EFiQS7Pw%253D%253D%26h%3D4fc7ea4b07e343d4180aec46d962dab30783f1a6-yot32p6u_87845615541892%26l%3D7b2d8ed2b22ee7334751a558ed5f0867b2319cb0-82716564&amp;source=gmail&amp;ust=1695672879445000&amp;usg=AOvVaw1YPX8r-oSl3So7-tobJWFy">Black Friday &amp; Cyber Monday Marketing 2023: Week-by-Week Guide</a></li>\n</ul>\n<ul style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; margin: 0px 0px 20px 40px; padding: 0px;">\n<li style="margin-left: 15px;"><a class="m_-3194041646948202551intercom-content-link" style="line-height: 19.5px; margin: 0px; padding: 0px; color: #1251ba !important;" href="https://optinmonster.intercom-clicks.com/via/e?ob=57DuWS02Fb73bfHWcyIIvMTVIUYdIrWdwIrGmYFUtjpmNCOahAcbTiUGs9%2B%2BkwuP6TBkMAcrOVF9VlIFvk1gN0vyTZZM7H1WW4kxEef2rfaTz1pXQB4kYZ%2F9XHjVeeadam9H4xIU7TiEjDZzZjdoSrXtUSRMpGsS7JD0g6mtjO9xEcPGv4h09BO8kDoqFYN6TOrCdnH6PkbgJoHE6TZ0qw%3D%3D&amp;h=a7fbd7ea0001131fb1ec598102f23707c95c739b-yot32p6u_87845615541892&amp;l=2a81e3fbbe1f25231e9aa0f3cddc25602d07a6ab-82716568" target="_blank" rel="noopener" data-saferedirecturl="https://www.google.com/url?q=https://optinmonster.intercom-clicks.com/via/e?ob%3D57DuWS02Fb73bfHWcyIIvMTVIUYdIrWdwIrGmYFUtjpmNCOahAcbTiUGs9%252B%252BkwuP6TBkMAcrOVF9VlIFvk1gN0vyTZZM7H1WW4kxEef2rfaTz1pXQB4kYZ%252F9XHjVeeadam9H4xIU7TiEjDZzZjdoSrXtUSRMpGsS7JD0g6mtjO9xEcPGv4h09BO8kDoqFYN6TOrCdnH6PkbgJoHE6TZ0qw%253D%253D%26h%3Da7fbd7ea0001131fb1ec598102f23707c95c739b-yot32p6u_87845615541892%26l%3D2a81e3fbbe1f25231e9aa0f3cddc25602d07a6ab-82716568&amp;source=gmail&amp;ust=1695672879445000&amp;usg=AOvVaw3SuviWGz8HwCdbFwFwc17_">Best 9 Black Friday Marketing Tools</a></li>\n</ul>\n<ul style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; margin: 0px 0px 20px 40px; padding: 0px;">\n<li style="margin-left: 15px;"><a class="m_-3194041646948202551intercom-content-link" style="line-height: 19.5px; margin: 0px; padding: 0px; color: #1251ba !important;" href="https://optinmonster.intercom-clicks.com/via/e?ob=D3k2v5IxG8QElPrflPhBeeYc9DfJSvzUxFTGYEiX19U8HUP6RK%2FyYNhU3%2BrfXaD2pEkjmySfPmBKTTO9aYKdrVXPFeeE3%2FNvnY8ejbSU%2BPJMe8meXvD5y2SpA8TN8QypwYFGUecAAx42yRKvbgWVnhfnRGS9ZcAfdArJ0TE8z%2B1aT9KACMXYz%2Bn%2FFzsPozPg&amp;h=4ea7f2c8559dcabc5a09f15cca715e34c69aaeff-yot32p6u_87845615541892&amp;l=8115b647b0502e370a7a2bb55c8c42be677c71be-82716570" target="_blank" rel="noopener" data-saferedirecturl="https://www.google.com/url?q=https://optinmonster.intercom-clicks.com/via/e?ob%3DD3k2v5IxG8QElPrflPhBeeYc9DfJSvzUxFTGYEiX19U8HUP6RK%252FyYNhU3%252BrfXaD2pEkjmySfPmBKTTO9aYKdrVXPFeeE3%252FNvnY8ejbSU%252BPJMe8meXvD5y2SpA8TN8QypwYFGUecAAx42yRKvbgWVnhfnRGS9ZcAfdArJ0TE8z%252B1aT9KACMXYz%252Bn%252FFzsPozPg%26h%3D4ea7f2c8559dcabc5a09f15cca715e34c69aaeff-yot32p6u_87845615541892%26l%3D8115b647b0502e370a7a2bb55c8c42be677c71be-82716570&amp;source=gmail&amp;ust=1695672879445000&amp;usg=AOvVaw2cV4vIGQyyDWN7zPm72JiL">Black Friday Marketing Ideas That Actually Work</a></li>\n</ul>\n<ul style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; margin: 0px 0px 20px 40px; padding: 0px;">\n<li style="margin-left: 15px;"><a class="m_-3194041646948202551intercom-content-link" style="line-height: 19.5px; margin: 0px; padding: 0px; color: #1251ba !important;" href="https://optinmonster.intercom-clicks.com/via/e?ob=In5rzaAaGQX%2B1BQqwYvopIVm631I6oUH2el7LwQ62FvYh5WeYenDspNTk96%2BcyOlCJKmRJTDrBk2gpyOFPCxLTwjf11HDpqmroFQ8nlbLh4vgG67ODAKHEWxXLB29uyvtU28KAreRfS464vIu5xoRWLKDCDfdMHLBE41guXr%2B0pac4Hb7hxxLlQ%2FjZiYoT5h&amp;h=239537e687c6d482127587dcd74d0943965a78ca-yot32p6u_87845615541892&amp;l=6e11e7632d0195dc5327eb3b39ff8ca03629075c-82716579" target="_blank" rel="noopener" data-saferedirecturl="https://www.google.com/url?q=https://optinmonster.intercom-clicks.com/via/e?ob%3DIn5rzaAaGQX%252B1BQqwYvopIVm631I6oUH2el7LwQ62FvYh5WeYenDspNTk96%252BcyOlCJKmRJTDrBk2gpyOFPCxLTwjf11HDpqmroFQ8nlbLh4vgG67ODAKHEWxXLB29uyvtU28KAreRfS464vIu5xoRWLKDCDfdMHLBE41guXr%252B0pac4Hb7hxxLlQ%252FjZiYoT5h%26h%3D239537e687c6d482127587dcd74d0943965a78ca-yot32p6u_87845615541892%26l%3D6e11e7632d0195dc5327eb3b39ff8ca03629075c-82716579&amp;source=gmail&amp;ust=1695672879445000&amp;usg=AOvVaw2S0dymtLUx_2DNIquy4buq">Black Friday Emails: Best Examples, Top Subject Lines, and Key Takeaways</a></li>\n</ul>\n<p style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">Implement whatever you can, and&nbsp;<em>have a record-breaking Black Friday sale</em>!</p>\n<p style="font-size: 15px; color: #525252; font-family: Helvetica, Arial, sans-serif; background-color: #fefefe; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">If you have any questions, just reply to this email.</p>\n<p style="font-size: 15px; color: #525252; background-color: #fefefe; font-family: sans-serif; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">Talk again soon,</p>\n<p style="font-size: 15px; color: #525252; background-color: #fefefe; font-family: sans-serif; line-height: 1.5; margin: 0px 0px 17px; padding: 0px;" align="left">Angie Meeker<br />General Manager,<br />OptinMonster</p>
\.


--
-- Data for Name: holidays; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.holidays (identifier, start, end_date, comments, type, status, profile_identifier, authorized_by, date_created, mod_date, includesaturday, includesunday, halfdaystart, halfdayend) FROM stdin;
5006bd32-b202-43de-9388-f96a05f32bff	2023-04-27 00:00:00	2023-04-28 00:00:00	I would need some days off	ANNUAL_LEAVE	REQUESTED	7bf56177-8a14-4f07-8d23-246949d65955	727910f3-4c49-4b13-b781-efc654044e29	\N	\N	f	f	f	f
83cb9928-99e5-4f88-a3d4-e7701b2a3784	2023-06-04 00:00:00	2023-06-08 00:00:00	\N	\N	APPROVED	727910f3-4c49-4b13-b781-efc654044e29	\N	\N	\N	f	f	f	f
8cfa1c3a-48e8-4ad0-8e5d-740fa89e092e	2023-07-02 00:00:00	2023-07-06 00:00:00	\N	\N	APPROVED	727910f3-4c49-4b13-b781-efc654044e29	\N	\N	\N	f	f	f	f
c43e733f-0620-4299-bd13-11b2990dd783	2023-06-18 00:00:00	2023-07-01 00:00:00	Please please please I need holiday	ANNUAL_LEAVE	APPROVED	7bf56177-8a14-4f07-8d23-246949d65955	727910f3-4c49-4b13-b781-efc654044e29	\N	\N	t	t	f	f
06bbf436-0149-490b-bca8-c778ab4d7663	2023-07-09 00:00:00	2023-07-14 00:00:00	Holidays for Florence	\N	REQUESTED	7bf56177-8a14-4f07-8d23-246949d65955	727910f3-4c49-4b13-b781-efc654044e29	\N	\N	f	f	f	f
493d37a3-3867-410d-a0f0-12d22a10d838	2023-08-06 00:00:00	2023-08-11 00:00:00	Vacaciones de flor	\N	REJECTED	7bf56177-8a14-4f07-8d23-246949d65955	727910f3-4c49-4b13-b781-efc654044e29	\N	\N	f	f	f	f
d773ed98-e91b-4079-8500-5893ccb882b3	2023-09-04 00:00:00	2023-09-09 00:00:00	test	ANNUAL_LEAVE	APPROVED	727910f3-4c49-4b13-b781-efc654044e29	\N	\N	\N	f	f	f	f
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
-- Data for Name: organization_chart; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.organization_chart (parent_identifier, child_identifier, relationship_type, authorize_holiday) FROM stdin;
727910f3-4c49-4b13-b781-efc654044e29	7bf56177-8a14-4f07-8d23-246949d65955	Manager	t
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
-- Data for Name: profile_role; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.profile_role (profile_identifier, role_key) FROM stdin;
edb0a07a-544e-4f5a-8140-06a77b6f8d1a	HR-ADMIN
41dc513b-d1f3-444e-bd79-32413fe34572	HR-ADMIN
d08d7425-d5bf-465a-b98e-12ae2589e171	HR-ADMIN
af8c81df-5bb4-4184-acf4-149700a75711	HR-ADMIN
727910f3-4c49-4b13-b781-efc654044e29	HR-ADMIN
94ce30b3-ecce-44aa-bb48-f6da32921514	HR-ADMIN
27866d58-2de6-45dd-83a9-4fd5fdad5412	HR-ADMIN
e1034681-4874-48b0-98f1-c9e7d41d09e2	HR-ADMIN
057c7b5b-bebd-4ef5-bf56-f3cc43457869	HR-ADMIN
68a00048-2a7b-41db-a29f-4e18154c6236	HR-ADMIN
fcaa2341-e98e-4021-a983-db0960b8acd0	HR-ADMIN
e4442e63-7a74-47ad-9ab6-047ed7e6e2c6	HR-ADMIN
b81b6de3-6efe-4d2e-8c08-d1c4769e120f	HR-ADMIN
\.


--
-- Data for Name: profiles; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.profiles (identifier, firstname, lastname, email, deleted, account_identifier, dob, entitlement_absence, avatar, title, known_as, address, gender, gender_identity, preferred_pronoun, marital_status, employee_number, work_phone, work_extn, work_mobile, personal_email, personal_mobile, home_phone) FROM stdin;
804b3839-9e29-45f7-a60e-e8aabe87d06f	Alisa	Davis	alisa.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Dr		430 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Single	182				alisa.davis@gmail.com		
c8de4b3f-daa6-4fc1-9a0e-aa6bdf8b1658	Alisa	Garcia	alisa.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	24	\N	Mr		450 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Male			In a relationship	83				alisa.garcia@gmail.com		
d6f3051f-ebb1-4029-9b14-1f4754a1d45a	Alisa	Gonzalez	alisa.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Dr		477 456 Elm St 123 Main St 321 Maple Ave 789 Oak St 654 Pine St	Male			Widowed	869				alisa.gonzalez@gmail.com		
e78a3279-0b15-40a2-95e0-fa10ee81b222	Alisa	Johnson	alisa.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Miss		920 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Male			Married	658				alisa.johnson@gmail.com		
f6ce5190-ab6f-4d2c-b2da-9f71cf7e99ea	Alisa	Jones	alisa.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	4	\N	Dr		719 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			In a relationship	149				alisa.jones@gmail.com		
fd2977e9-10d5-4173-a4b6-21bb09b17b38	Marisa	Brown	alisa.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-03-28	0	\N	Miss		737 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female	\N		\N	335	\N	\N	\N	alisa.brown@gmail.com	111111	\N
727910f3-4c49-4b13-b781-efc654044e29	Mariano	Juri	mariano.juri@yahoo.co.uk	f	69609916-442b-413d-a439-c3e505b9d000	\N	0	\N	Mr	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
ffbbd930-53d3-4000-95b4-bf79840a0491	Jordan	Peterson	jordan@gmail.com	f	a06dc590-8690-431a-9de0-167905011b47	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
edb0a07a-544e-4f5a-8140-06a77b6f8d1a	Jorge	Pontoriero	jorgep@gmail.com	f	b81c2aba-b0ee-4642-9f90-7cc435ba2bcf	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
907fbf29-c720-42f2-9b2a-cc30d4971f3e	Alisa	Miller	alisa.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-02	0	\N	Miss		711 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Male	\N		\N	187	\N	\N	\N	alisa.miller@gmail.com	123132131231	\N
b75ae32d-d4c0-4805-b3ef-2e17c45e7ec1	Charles	Johnson	charles.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-05	17	\N	Miss		16 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Divorced	804				charles.johnson@gmail.com		
41dc513b-d1f3-444e-bd79-32413fe34572	John	Doe	johndoe@gmail.com	f	8fddb239-8f82-4154-9098-ecf16345a853	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
d08d7425-d5bf-465a-b98e-12ae2589e171	John	Doe	johndoe@gmail.com	f	8fddb239-8f82-4154-9098-ecf16345a853	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
af8c81df-5bb4-4184-acf4-149700a75711	John	Doe	johndoe@gmail.com	f	8fddb239-8f82-4154-9098-ecf16345a853	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
9acdbc78-03ef-4dc8-87f0-8237517b57fc	Alisa	Martinez	alisa.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mrs		122 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Divorced	571				alisa.martinez@gmail.com		
f6590305-dae7-445e-9704-4f2a98d711a8	Alisa	Rodriguez	alisa.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	8	\N	Mr		485 123 Main St 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St	Male			Widowed	406				alisa.rodriguez@gmail.com		
3fa489f5-e1fd-48b4-b133-0a9af55f531c	Alisa	Smith	alisa.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	28	\N	Mr		154 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Male			Single	872				alisa.smith@gmail.com		
60dd53d7-49be-4a51-873a-9edd30e1babb	Charles	Brown	charles.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	29	\N	Mr		788 321 Maple Ave 123 Main St 456 Elm St 789 Oak St 654 Pine St	Male			Married	909				charles.brown@gmail.com		
de8e455b-8401-49ec-916a-52cd0c72d023	Charles	Davis	charles.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	23	\N	Mrs		240 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Male			Single	951				charles.davis@gmail.com		
c31d63c8-cb10-4cc3-a50a-f183b9d39be4	Charles	Garcia	charles.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	23	\N	Mr		574 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Single	59				charles.garcia@gmail.com		
0b463d13-182e-4739-9f1f-ff23386ba2f8	Charles	Gonzalez	charles.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Mrs		480 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Divorced	633				charles.gonzalez@gmail.com		
1ecd0b43-239f-46c8-b174-ea1b00d58536	Charles	Jones	charles.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	26	\N	Miss		528 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Single	768				charles.jones@gmail.com		
de7ee51a-70d7-46c3-9d34-96b5806b9c5d	Charles	Martinez	charles.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	9	\N	Miss		838 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			In a relationship	411				charles.martinez@gmail.com		
48499941-b60c-4efc-ac90-ba3abd377a09	Charles	Miller	charles.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	1	\N	Mrs		702 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Female			In a relationship	182				charles.miller@gmail.com		
d814a647-c650-423e-af8a-f33080aec6a6	Charles	Rodriguez	charles.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	13	\N	Dr		746 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Male			Married	761				charles.rodriguez@gmail.com		
fd41c0dc-f9c0-4b4c-9741-08387eb91045	Charles	Smith	charles.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Miss		212 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Female			Married	740				charles.smith@gmail.com		
7ed11d84-40d6-4188-9368-29c68600b8b6	David	Brown	david.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	14	\N	Miss		71 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Female			Married	908				david.brown@gmail.com		
472446eb-fa49-488d-80a9-6bfecf32202b	David	Davis	david.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mrs		81 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Male			Widowed	783				david.davis@gmail.com		
540f58b2-a202-419f-b8f1-456365aed565	David	Garcia	david.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	21	\N	Dr		921 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Widowed	667				david.garcia@gmail.com		
877e88ee-460b-47de-ad7b-17b8c2389300	David	Gonzalez	david.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	3	\N	Mrs		859 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			In a relationship	70				david.gonzalez@gmail.com		
0ce7effd-0bd1-4aca-9b36-df627cc42ab3	David	Johnson	david.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	8	\N	Dr		88 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Married	158				david.johnson@gmail.com		
17d84458-a97c-4dd2-8399-f815ca848930	David	Jones	david.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	16	\N	Mr		466 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Married	546				david.jones@gmail.com		
d9aafddd-bcbb-4ea2-88ee-f92e085d743d	David	Martinez	david.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Mrs		881 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Married	802				david.martinez@gmail.com		
93149061-396c-48b6-a88b-5837b45dc4e9	David	Miller	david.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mrs		352 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Male			In a relationship	210				david.miller@gmail.com		
2194a720-cfc2-42b4-94bb-3d1f697ddb87	David	Rodriguez	david.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	26	\N	Mrs		832 789 Oak St 456 Elm St 123 Main St 321 Maple Ave 654 Pine St	Male			Single	919				david.rodriguez@gmail.com		
1f6bb8a7-aa6e-4c14-a8fb-64fce11a6f9e	David	Smith	david.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	12	\N	Mr		473 789 Oak St 654 Pine St 321 Maple Ave 456 Elm St 123 Main St	Female			Single	939				david.smith@gmail.com		
fbd6b472-efc7-4e49-996b-aa73238f370c	Diana	Brown	diana.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	15	\N	Mrs		366 456 Elm St 789 Oak St 123 Main St 654 Pine St 321 Maple Ave	Female			In a relationship	247				diana.brown@gmail.com		
a53c6bc4-5f9a-45a4-b3d0-8b0617f8736a	Diana	Davis	diana.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	3	\N	Mr		726 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Male			Divorced	77				diana.davis@gmail.com		
460f7f4d-5bfc-4387-96b9-1d7f18309829	Diana	Garcia	diana.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	12	\N	Mrs		512 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Divorced	344				diana.garcia@gmail.com		
3fcb5f54-f395-48d7-9a00-6fe987474fbe	Diana	Gonzalez	diana.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	19	\N	Mrs		399 123 Main St 654 Pine St 456 Elm St 789 Oak St 321 Maple Ave	Male			Married	872				diana.gonzalez@gmail.com		
4839e156-342e-4470-9214-1b32bc05a50b	Diana	Johnson	diana.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	14	\N	Dr		625 321 Maple Ave 654 Pine St 123 Main St 456 Elm St 789 Oak St	Male			Married	991				diana.johnson@gmail.com		
e25d603a-ea43-4492-9a6d-519dbbbfcdb9	Diana	Jones	diana.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	3	\N	Mr		355 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Married	80				diana.jones@gmail.com		
7ff15af1-7f00-4b62-a878-e15b4bfd73f2	Diana	Martinez	diana.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	24	\N	Mr		787 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Divorced	300				diana.martinez@gmail.com		
3a27ebb0-2b72-4f8c-9a48-a0be99a07c93	Diana	Miller	diana.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	0	\N	Miss		22 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			In a relationship	421				diana.miller@gmail.com		
c78d016c-9987-4ad3-9906-74b4c6794029	Diana	Rodriguez	diana.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	7	\N	Mrs		786 789 Oak St 654 Pine St 321 Maple Ave 456 Elm St 123 Main St	Female			Single	411				diana.rodriguez@gmail.com		
3a1dbfe6-4aa8-4715-adec-6c4efe7341fd	Diana	Smith	diana.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	12	\N	Mrs		491 789 Oak St 456 Elm St 654 Pine St 123 Main St 321 Maple Ave	Female			Widowed	845				diana.smith@gmail.com		
b1382743-b915-4d0f-87be-57a1c9ca2cfc	Ellen	Brown	ellen.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Dr		159 789 Oak St 123 Main St 654 Pine St 321 Maple Ave 456 Elm St	Female			Single	946				ellen.brown@gmail.com		
5d5599ef-5388-4bcf-9044-690678b16b73	Ellen	Davis	ellen.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	15	\N	Mr		506 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			In a relationship	527				ellen.davis@gmail.com		
3576bd7a-cd25-4457-9672-3ce571f08035	Ellen	Garcia	ellen.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	24	\N	Dr		978 654 Pine St 123 Main St 321 Maple Ave 789 Oak St 456 Elm St	Male			Married	765				ellen.garcia@gmail.com		
968766b5-5efe-41f8-9167-0818789cf158	Ellen	Gonzalez	ellen.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	10	\N	Mr		548 123 Main St 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St	Female			Divorced	39				ellen.gonzalez@gmail.com		
9ce5bd88-5e51-4420-a2b3-6c615776ebf1	Ellen	Johnson	ellen.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	6	\N	Dr		10 789 Oak St 123 Main St 654 Pine St 321 Maple Ave 456 Elm St	Male			In a relationship	167				ellen.johnson@gmail.com		
ba994b5d-8bd2-433d-a76d-f8687f1a641c	Ellen	Jones	ellen.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	26	\N	Mr		855 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Married	361				ellen.jones@gmail.com		
82dbac84-574f-4e14-a38b-111ef7e0e5f9	Ellen	Martinez	ellen.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	7	\N	Dr		198 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Male			In a relationship	176				ellen.martinez@gmail.com		
b6cb2f43-966c-4935-80c2-37fe35f943d0	Ellen	Miller	ellen.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Dr		894 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Single	442				ellen.miller@gmail.com		
c50cd87e-c5bd-4e8b-8d7b-5d3a1f801b81	Ellen	Rodriguez	ellen.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	20	\N	Mrs		444 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			Married	453				ellen.rodriguez@gmail.com		
321fbf57-82e5-491f-9159-ef74acd95941	Ellen	Smith	ellen.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Miss		962 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Widowed	817				ellen.smith@gmail.com		
557367d9-70a7-475f-8ec6-54f3a72d04c9	Iryna	Brown	iryna.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mr		470 456 Elm St 654 Pine St 321 Maple Ave 789 Oak St 123 Main St	Male			Single	667				iryna.brown@gmail.com		
bb69508b-de40-445d-8204-b9b73ccd157a	Iryna	Davis	iryna.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Dr		501 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Widowed	395				iryna.davis@gmail.com		
3da6f403-50c1-4349-93a6-d74bb94d0970	Iryna	Garcia	iryna.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Dr		590 456 Elm St 654 Pine St 321 Maple Ave 789 Oak St 123 Main St	Female			Married	34				iryna.garcia@gmail.com		
82c6e43a-b14c-4c41-83be-d8ffe68ade57	Iryna	Gonzalez	iryna.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	20	\N	Mrs		597 789 Oak St 123 Main St 456 Elm St 654 Pine St 321 Maple Ave	Female			Single	559				iryna.gonzalez@gmail.com		
c3a7fccc-2aaa-48a1-8f0a-35caaf84dc32	Iryna	Johnson	iryna.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Mrs		432 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Divorced	901				iryna.johnson@gmail.com		
a48b5d30-d3be-4473-8079-4d302fb9909d	Iryna	Jones	iryna.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	18	\N	Miss		683 456 Elm St 654 Pine St 321 Maple Ave 789 Oak St 123 Main St	Female			Divorced	184				iryna.jones@gmail.com		
280d98c4-3889-4e2f-8a47-c4962c5053e2	Iryna	Martinez	iryna.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	6	\N	Miss		927 321 Maple Ave 654 Pine St 789 Oak St 456 Elm St 123 Main St	Male			Divorced	517				iryna.martinez@gmail.com		
96927dd3-3004-4f1d-aa6e-2222b5be146c	Iryna	Miller	iryna.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	22	\N	Miss		236 123 Main St 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St	Male			Single	832				iryna.miller@gmail.com		
3423c243-7841-41b6-bd15-497e0b1b98b4	Iryna	Rodriguez	iryna.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	4	\N	Mrs		220 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St 123 Main St	Female			Widowed	650				iryna.rodriguez@gmail.com		
3cd4a123-d441-4885-8ecb-f1128313644b	Iryna	Smith	iryna.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	9	\N	Miss		220 654 Pine St 789 Oak St 456 Elm St 123 Main St 321 Maple Ave	Female			Single	902				iryna.smith@gmail.com		
37db7de6-e93c-4aa2-a139-ad21d1346049	James	Brown	james.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	16	\N	Mr		991 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Female			Widowed	918				james.brown@gmail.com		
28a0b592-4b6f-4720-b8d0-0c463ee0be54	James	Davis	james.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mrs		554 789 Oak St 456 Elm St 123 Main St 654 Pine St 321 Maple Ave	Female			In a relationship	789				james.davis@gmail.com		
763ce1ce-fde2-4b6f-b0e5-f0bea495f8fa	James	Garcia	james.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	0	\N	Miss		532 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Male			Single	751				james.garcia@gmail.com		
39308f01-017e-4d04-95ca-95a875e0de4e	James	Gonzalez	james.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	23	\N	Mrs		108 654 Pine St 321 Maple Ave 789 Oak St 123 Main St 456 Elm St	Male			In a relationship	326				james.gonzalez@gmail.com		
59e18f10-7031-4f2e-8825-fe8b66832252	James	Johnson	james.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mr		533 456 Elm St 321 Maple Ave 654 Pine St 123 Main St 789 Oak St	Female			Married	524				james.johnson@gmail.com		
a8d9c349-12c4-4fc3-be94-1486a013dc3f	James	Jones	james.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	29	\N	Mrs		190 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Male			In a relationship	743				james.jones@gmail.com		
5c698ce2-8124-4843-b7ac-4250870bf039	James	Martinez	james.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	26	\N	Miss		144 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			Single	675				james.martinez@gmail.com		
4b5a7d24-0ca4-4d1c-8c7b-9f9c3fffa03a	James	Miller	james.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Mrs		251 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			In a relationship	357				james.miller@gmail.com		
3f52dc6a-fcdb-4d44-86f4-da00c3c0d88e	James	Rodriguez	james.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	23	\N	Miss		120 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			In a relationship	652				james.rodriguez@gmail.com		
371b7409-bf41-4dd3-90f6-4270cc99305f	James	Smith	james.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	6	\N	Mr		815 456 Elm St 123 Main St 789 Oak St 321 Maple Ave 654 Pine St	Male			Widowed	4				james.smith@gmail.com		
4a71e349-65ec-40b6-8ac9-29f2a12c224e	John	Brown	john.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	14	\N	Miss		188 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Married	331				john.brown@gmail.com		
8bba2c46-8e97-4679-ba24-57923bc749ca	John	Davis	john.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	13	\N	Mr		342 123 Main St 654 Pine St 456 Elm St 789 Oak St 321 Maple Ave	Female			Divorced	592				john.davis@gmail.com		
80d4517a-d93e-413a-95ab-3e8657f9c9ba	John	Garcia	john.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	7	\N	Dr		628 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Widowed	747				john.garcia@gmail.com		
f500ed07-c7e6-488c-8ef9-abfbe24f6025	John	Gonzalez	john.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	16	\N	Mrs		367 123 Main St 321 Maple Ave 654 Pine St 789 Oak St 456 Elm St	Female			Divorced	182				john.gonzalez@gmail.com		
c9f5c618-4e3c-41ef-80d2-07b170b83cfc	John	Johnson	john.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Mr		292 321 Maple Ave 654 Pine St 123 Main St 789 Oak St 456 Elm St	Female			In a relationship	479				john.johnson@gmail.com		
74809a86-1a76-48d7-9f16-25ac86079df6	John	Jones	john.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	6	\N	Dr		328 123 Main St 456 Elm St 654 Pine St 321 Maple Ave 789 Oak St	Female			In a relationship	669				john.jones@gmail.com		
7c3762af-6592-4ff4-8438-84daddf0d090	John	Martinez	john.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	7	\N	Mrs		831 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Married	459				john.martinez@gmail.com		
78a2671f-5cb1-4285-ae99-7b7047b2e595	John	Miller	john.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Mr		450 123 Main St 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St	Male			Widowed	817				john.miller@gmail.com		
d57bca63-490a-470b-a02a-a32efb809ffe	John	Rodriguez	john.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	5	\N	Dr		180 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Male			Single	470				john.rodriguez@gmail.com		
38cab2ee-f788-4f5a-90dd-abe27d5106fc	John	Smith	john.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	3	\N	Dr		141 123 Main St 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St	Male			Widowed	468				john.smith@gmail.com		
833d1844-aeda-4d06-8ccb-45bf5bd006b9	Joseph	Brown	joseph.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	17	\N	Mr		265 123 Main St 789 Oak St 321 Maple Ave 654 Pine St 456 Elm St	Female			Divorced	96				joseph.brown@gmail.com		
416953a1-b447-4b7c-805d-fe5758327e8f	Joseph	Davis	joseph.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	18	\N	Miss		885 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Widowed	335				joseph.davis@gmail.com		
cb2c4830-3609-404c-82a8-2681934b1f3d	Joseph	Garcia	joseph.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	11	\N	Mr		36 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Female			Single	215				joseph.garcia@gmail.com		
6cc67b9f-a8fc-4537-b0a8-2adaca870b4e	Joseph	Gonzalez	joseph.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	10	\N	Miss		15 654 Pine St 123 Main St 321 Maple Ave 789 Oak St 456 Elm St	Male			Widowed	155				joseph.gonzalez@gmail.com		
484ac8f0-0e4b-47b4-874c-acae6490185a	Joseph	Johnson	joseph.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	11	\N	Miss		801 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			Widowed	66				joseph.johnson@gmail.com		
98ca2b5e-3735-439d-b8f4-57a2ccec6e29	Joseph	Jones	joseph.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	4	\N	Mr		132 123 Main St 654 Pine St 321 Maple Ave 789 Oak St 456 Elm St	Female			Widowed	17				joseph.jones@gmail.com		
4f654698-7ad9-4dcf-8ecd-d4adc91a8c60	Joseph	Martinez	joseph.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	14	\N	Mrs		92 456 Elm St 123 Main St 654 Pine St 321 Maple Ave 789 Oak St	Female			Divorced	127				joseph.martinez@gmail.com		
4a04f12c-8c18-4046-a98c-da763ee734da	Joseph	Miller	joseph.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	19	\N	Mrs		344 321 Maple Ave 654 Pine St 123 Main St 456 Elm St 789 Oak St	Female			Single	358				joseph.miller@gmail.com		
cede8108-cd5b-4d76-a859-a8cba9e86288	Joseph	Rodriguez	joseph.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	19	\N	Miss		264 321 Maple Ave 654 Pine St 789 Oak St 456 Elm St 123 Main St	Male			Single	64				joseph.rodriguez@gmail.com		
454eb5d5-63cc-4c46-84f0-97ac8d699d64	Joseph	Smith	joseph.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	27	\N	Mr		830 789 Oak St 654 Pine St 321 Maple Ave 123 Main St 456 Elm St	Male			Married	962				joseph.smith@gmail.com		
0d45bc2b-f4b9-4177-870a-5042d3c357d2	Kate	Brown	kate.brown@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	0	\N	Dr		73 321 Maple Ave 654 Pine St 123 Main St 456 Elm St 789 Oak St	Male			Married	163				kate.brown@gmail.com		
841b1fd3-7f03-41e0-8795-1ce6ba69d768	Kate	Davis	kate.davis@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Mrs		753 321 Maple Ave 123 Main St 456 Elm St 789 Oak St 654 Pine St	Male			Widowed	97				kate.davis@gmail.com		
7fd7fe7c-d723-4f0a-966b-d93f582f6d59	Kate	Garcia	kate.garcia@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	19	\N	Mrs		957 789 Oak St 654 Pine St 123 Main St 456 Elm St 321 Maple Ave	Male			In a relationship	221				kate.garcia@gmail.com		
7b14ad12-d0b0-4283-a706-e3716acc650f	Kate	Gonzalez	kate.gonzalez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	3	\N	Mrs		420 654 Pine St 123 Main St 456 Elm St 789 Oak St 321 Maple Ave	Male			Divorced	250				kate.gonzalez@gmail.com		
f94d7f3a-2c12-422c-93cc-2767929543b6	Kate	Johnson	kate.johnson@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	21	\N	Mr		909 456 Elm St 789 Oak St 321 Maple Ave 654 Pine St 123 Main St	Female			Single	982				kate.johnson@gmail.com		
f5853dca-2b68-4337-956c-8b5a80d8bca6	Kate	Jones	kate.jones@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	15	\N	Mr		561 456 Elm St 654 Pine St 321 Maple Ave 789 Oak St 123 Main St	Male			Divorced	314				kate.jones@gmail.com		
36c34ed6-097b-4e82-8694-1da551f64910	Kate	Martinez	kate.martinez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	2	\N	Mrs		358 789 Oak St 321 Maple Ave 654 Pine St 123 Main St 456 Elm St	Female			In a relationship	315				kate.martinez@gmail.com		
8cc8a41a-1308-4231-829e-a502704fd790	Kate	Miller	kate.miller@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	25	\N	Miss		448 123 Main St 654 Pine St 456 Elm St 789 Oak St 321 Maple Ave	Female			In a relationship	867				kate.miller@gmail.com		
f109ae06-a73b-4c29-a1e9-1d62f66c4bd9	Kate	Rodriguez	kate.rodriguez@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	7	\N	Mr		30 456 Elm St 123 Main St 654 Pine St 789 Oak St 321 Maple Ave	Female			Married	938				kate.rodriguez@gmail.com		
4fb50b52-7c02-42f4-b648-6d6426a32db5	Kate	Smith	kate.smith@example.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2023-04-06	15	\N	Dr		884 654 Pine St 321 Maple Ave 789 Oak St 123 Main St 456 Elm St	Male			Single	445				kate.smith@gmail.com		
c20656d9-f1db-41ef-8225-8b57335dc60d	Nahuel	Barrios	nahuelbarrios@casla.com	f	8fddb239-8f82-4154-9098-ecf16345a853	2000-01-01	0	\N	Mr	Perrito	\N	\N	\N	\N	\N	5	\N	\N	\N	nahuelbarrios@casla.com	07761318029	\N
8b3f6664-14c1-4f8c-81cc-9a005ddc0d04	Jorge	Ibañez	jorge@casla.com	f	8fddb239-8f82-4154-9098-ecf16345a853	1959-01-01	0	\N	Mr	El Pedo	\N	\N	\N	\N	\N	5	\N	\N	\N	jorge@casla.com	07761318029	\N
4cabff7a-51e8-48c0-9948-eb590caca53d	Nahuela	Barries	nahuelbarrios@casla.com	f	8fddb239-8f82-4154-9098-ecf16345a853	1959-01-01	0	\N	Miss	Perrito	\N	\N	\N	\N	\N	5	\N	\N	\N	nahuelbarrios@casla.com	07761318029	\N
0d4b0f80-be05-41c5-aa03-97e0de184ecf	Theresa	May	may@theresa.com	f	8fddb239-8f82-4154-9098-ecf16345a853	1959-01-01	0	\N	Miss	Theresita	\N	\N	\N	\N	\N	90	\N	\N	\N	may@theresa.com	134rt35432345	\N
7bf56177-8a14-4f07-8d23-246949d65955	Florence	Welch	florence@ndthemachine.com	f	69609916-442b-413d-a439-c3e505b9d000	1983-01-01	0	\N	Miss	Flo	SULBY COTTAGE\n6 ASHLEY ROAD	female	\N	she/her	\N	22	\N	\N	\N	florence@ndthemachine.com	07966614392	\N
94ce30b3-ecce-44aa-bb48-f6da32921514	Javier	 Milei	javier@libertadavanza.com.ar	f	2f7dd967-4367-4794-94de-9306612a7854	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
27866d58-2de6-45dd-83a9-4fd5fdad5412	Javier 3	Milei 3	javier3@libertadavanza.com.ar	f	b2b55608-535e-4274-aff3-332c9bcf5cb5	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
e1034681-4874-48b0-98f1-c9e7d41d09e2	Javier	 Milei	javier555@libertadavanza.com.ar	f	5164fbf6-b966-4a47-bced-6769713c65ab	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
057c7b5b-bebd-4ef5-bf56-f3cc43457869	Javier	 Milei	javier6543@libertadavanza.com.ar	f	cda2ddd2-d9ee-4df0-bd3f-e4c679bb8c55	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
68a00048-2a7b-41db-a29f-4e18154c6236	Javier	 Milei	javier999999@libertadavanza.com.ar	f	0dfae1ed-16cb-46b9-aaf5-b5df563cc90a	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
fcaa2341-e98e-4021-a983-db0960b8acd0	Javier	 Milei	jaasdfasdfasdfasdvier@libertadavanza.com.ar	f	fea594ef-19f3-490b-a9dc-172d663f874b	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
e4442e63-7a74-47ad-9ab6-047ed7e6e2c6	Alejandro	 Fantino	alejandro.fantino6666@neura.com.ar	f	04a01fa8-f9d2-4a3a-a7c1-e73de7f1ebca	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
b81b6de3-6efe-4d2e-8c08-d1c4769e120f	Alejandro	 Fantino	alejandro.fantino7777@neura.com.ar	f	f950ca75-e7c6-44a5-8fcf-28313e727609	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: role_positions; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.role_positions (identifier, requested_by, grade, salary_level, job_description, contract_type, date_created, start_date, header, status, assigned) FROM stdin;
2e619330-d0d0-47aa-83b0-73dba533b612	727910f3-4c49-4b13-b781-efc654044e29	\N	3	<p>This is a Job Description test</p>	3	\N	2023-07-17	this is a test of Header	REQUESTED	7bf56177-8a14-4f07-8d23-246949d65955
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
-- Data for Name: shareddocument_profile; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.shareddocument_profile (profile_identifier, document_identifier) FROM stdin;
\.


--
-- Data for Name: todo; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.todo (identifier, profile_identifier, type, text, done, read, date_created, mod_date, status, title) FROM stdin;
009fe340-5385-47cc-a7c5-41663898fc65	ca9994ee-ffe9-4c0a-8466-a321011d9e64	TASK	\N	t	t	\N	\N	PENDING	Do this demo
7dc33a88-edc8-45ec-af68-06d1d82754bd	ca9994ee-ffe9-4c0a-8466-a321011d9e64	TASK	\N	t	t	\N	\N	PENDING	Do another task
78ce1830-280e-485f-8928-558c5412455c	ca9994ee-ffe9-4c0a-8466-a321011d9e64	TASK	\N	t	t	\N	\N	PENDING	Meeting with my manager at 12...
0309c649-04a9-43ff-8c15-1cc56b79b1e6	ca9994ee-ffe9-4c0a-8466-a321011d9e64	TASK	\N	t	t	\N	\N	PENDING	Chatting with Emma now
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
florence@ndthemachine.com	lvemszzQ
javier@libertadavanza.com.ar	M1lei@2023
javier3@libertadavanza.com.ar	M1lei@2023
javier555@libertadavanza.com.ar	M1lei@2023
javier6543@libertadavanza.com.ar	M1lei@2023
javier999999@libertadavanza.com.ar	M1lei@2023
jaasdfasdfasdfasdvier@libertadavanza.com.ar	M1lei@2023
alejandro.fantino6666@neura.com.ar	password1
alejandro.fantino7777@neura.com.ar	password1
\.


--
-- Name: accesses accesses_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.accesses
    ADD CONSTRAINT accesses_pkey PRIMARY KEY (token);


--
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (identifier);


--
-- Name: contracts contracts_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.contracts
    ADD CONSTRAINT contracts_pkey PRIMARY KEY (identifier);


--
-- Name: documents documents_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_pkey PRIMARY KEY (identifier);


--
-- Name: event_attendee event_attendee_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_pkey PRIMARY KEY (event_identifier, profile_identifier);


--
-- Name: events events_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (identifier);


--
-- Name: goals goals_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.goals
    ADD CONSTRAINT goals_pkey PRIMARY KEY (identifier);


--
-- Name: holidays holidays_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.holidays
    ADD CONSTRAINT holidays_pkey PRIMARY KEY (identifier);


--
-- Name: invoice_item invoice_item_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoice_item
    ADD CONSTRAINT invoice_item_pkey PRIMARY KEY (identifier);


--
-- Name: invoice_payment invoice_payment_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoice_payment
    ADD CONSTRAINT invoice_payment_pkey PRIMARY KEY (invoice_identifier, payment_identifier);


--
-- Name: invoices invoices_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_pkey PRIMARY KEY (identifier);


--
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (identifier);


--
-- Name: profile_role profile_role_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profile_role
    ADD CONSTRAINT profile_role_pkey PRIMARY KEY (profile_identifier, role_key);


--
-- Name: profiles profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_pkey PRIMARY KEY (identifier);


--
-- Name: role_positions role_positions_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.role_positions
    ADD CONSTRAINT role_positions_pkey PRIMARY KEY (identifier);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (key);


--
-- Name: shareddocument_profile shareddocument_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.shareddocument_profile
    ADD CONSTRAINT shareddocument_profile_pkey PRIMARY KEY (profile_identifier, document_identifier);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_name);


--
-- Name: contracts contracts_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.contracts
    ADD CONSTRAINT contracts_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: documents documents_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: event_attendee event_attendee_event_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_event_identifier_fkey FOREIGN KEY (event_identifier) REFERENCES public.events(identifier);


--
-- Name: event_attendee event_attendee_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.event_attendee
    ADD CONSTRAINT event_attendee_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: events events_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: goals goals_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.goals
    ADD CONSTRAINT goals_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: holidays holidays_authorized_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.holidays
    ADD CONSTRAINT holidays_authorized_by_fkey FOREIGN KEY (authorized_by) REFERENCES public.profiles(identifier);


--
-- Name: holidays holidays_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.holidays
    ADD CONSTRAINT holidays_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: invoice_item invoice_item_invoice_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoice_item
    ADD CONSTRAINT invoice_item_invoice_identifier_fkey FOREIGN KEY (invoice_identifier) REFERENCES public.invoices(identifier);


--
-- Name: invoice_payment invoice_payment_invoice_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoice_payment
    ADD CONSTRAINT invoice_payment_invoice_identifier_fkey FOREIGN KEY (invoice_identifier) REFERENCES public.invoices(identifier);


--
-- Name: invoice_payment invoice_payment_payment_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoice_payment
    ADD CONSTRAINT invoice_payment_payment_identifier_fkey FOREIGN KEY (payment_identifier) REFERENCES public.payments(identifier);


--
-- Name: invoices invoices_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: organization_chart organization_chart_child_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.organization_chart
    ADD CONSTRAINT organization_chart_child_identifier_fkey FOREIGN KEY (child_identifier) REFERENCES public.profiles(identifier);


--
-- Name: organization_chart organization_chart_parent_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.organization_chart
    ADD CONSTRAINT organization_chart_parent_identifier_fkey FOREIGN KEY (parent_identifier) REFERENCES public.profiles(identifier);


--
-- Name: profile_role profile_role_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profile_role
    ADD CONSTRAINT profile_role_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- Name: profile_role profile_role_role_key_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profile_role
    ADD CONSTRAINT profile_role_role_key_fkey FOREIGN KEY (role_key) REFERENCES public.roles(key);


--
-- Name: profiles profiles_account_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_account_identifier_fkey FOREIGN KEY (account_identifier) REFERENCES public.accounts(identifier);


--
-- Name: role_positions role_positions_assigned_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.role_positions
    ADD CONSTRAINT role_positions_assigned_fkey FOREIGN KEY (assigned) REFERENCES public.profiles(identifier);


--
-- Name: role_positions role_positions_requested_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.role_positions
    ADD CONSTRAINT role_positions_requested_by_fkey FOREIGN KEY (requested_by) REFERENCES public.profiles(identifier);


--
-- Name: shareddocument_profile shareddocument_profile_document_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.shareddocument_profile
    ADD CONSTRAINT shareddocument_profile_document_identifier_fkey FOREIGN KEY (document_identifier) REFERENCES public.documents(identifier);


--
-- Name: shareddocument_profile shareddocument_profile_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.shareddocument_profile
    ADD CONSTRAINT shareddocument_profile_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


--
-- PostgreSQL database dump complete
--

