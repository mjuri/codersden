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


SET default_tablespace = '';

SET default_with_oids = false;

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
    start_date date NOT NULL,
    cont_service date NOT NULL,
    contract_end_date date,
    onboard_date date,
    onboard_contract character varying(50),
    benefits_start date,
    review_date date,
    job_type character varying(120),
    country character varying(100),
    office_role character varying(120),
    location character varying(100),
    department character varying(120),
    team character varying(120),
    cost_centre character varying(120),
    line_manager character varying(50),
    right_to_work character varying(100),
    right_to_work_expiry date
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
    personal_email character varying(20),
    personal_mobile character varying(20),
    home_phone character varying(20),
    contract_identifier character varying(50)
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
    header character varying(400) NOT NULL
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
dbb40746-3bf0-419a-ac1f-af48a20aa4d0	gordo@yahoo.com	2022-10-24 16:07:13.85	\N
451127b7-d134-425d-a869-ef86162e6649	gordo@yahoo.com	2022-09-15 16:08:02.624	2022-09-15 16:08:08.683
de17ba57-4c15-4d0e-9c84-ac1c23d16df5	gordo@yahoo.com	2022-10-25 10:10:39.842	\N
c14af957-8022-4483-ad0d-2003a4f362fb	gordo@yahoo.com	2022-09-14 21:58:38.021	\N
4f4d0143-2966-42a8-8656-d33c0a03db1e	gordo@yahoo.com	2022-09-14 22:00:32.004	\N
49b7b3ee-6aa0-4a05-bf52-9a5d3d53db41	gordo@yahoo.com	2022-09-14 22:02:18.78	\N
ca5c0917-1781-450a-ba3f-9a1dd96a5dd1	gordo@yahoo.com	2022-09-14 22:02:59.747	\N
9bc32e6a-031c-45b9-8b24-36bf4b9aa499	gordo@yahoo.com	2022-09-15 13:15:47.549	\N
784b9338-5610-4076-b79f-97fe524fa846	gordo@yahoo.com	2022-09-15 13:20:44.647	\N
7fc6a7f9-f983-4d8e-9a2a-69bd2bd15d82	gordo@yahoo.com	2022-09-15 13:24:25.895	\N
02dbb6a5-c6b0-4ca5-a61a-30255c204fea	gordo@yahoo.com	2022-09-15 13:25:05.046	\N
31ad58ef-36c5-45b6-ad62-b86490ea03a3	gordo@yahoo.com	2022-09-15 13:26:24.854	\N
756d609d-378d-45fd-ac31-445daf5cc8b5	gordo@yahoo.com	2022-09-15 16:25:00.753	2022-09-15 16:25:19.848
c8b0e282-4cc5-459c-88dc-6c19ddd0f89b	gordo@yahoo.com	2022-09-15 13:28:09.961	2022-09-15 13:28:49.251
4c579807-7c22-4459-b578-bbaa85f8f2fc	gordo@yahoo.com	2022-09-18 16:04:37.065	\N
b78c46b1-2009-4f7b-938d-c1904c4c7f47	gordo@yahoo.com	2022-09-15 13:28:56.381	2022-09-15 13:29:15.954
f384a605-7d3f-454d-9a23-934db1f47645	gordo@yahoo.com	2022-09-15 13:29:52.528	\N
142973f8-0944-42fd-b5fa-8e03ec74b6d4	gordo@yahoo.com	2022-09-15 13:30:34.505	\N
8c61530e-3072-4e3d-a653-ef753aea8d8c	gordo@yahoo.com	2022-09-15 13:33:12.862	\N
b6dde347-04d4-4dcd-a8ed-fed6f9d3fd10	gordo@yahoo.com	2022-09-15 13:42:17.576	\N
577bcc1d-9a6f-4b8b-8783-700a56297798	gordo@yahoo.com	2022-09-15 13:42:26.321	\N
c9a45270-4870-47aa-b062-3190943e6d39	gordo@yahoo.com	2022-09-15 13:42:31.257	\N
6fe33afd-e7af-41f6-94d9-8ca38671bbc1	gordo@yahoo.com	2022-09-15 13:42:45.924	\N
d1019c6e-a9e3-4734-9bca-d8a4606dd84f	gordo@yahoo.com	2022-09-15 13:49:12.959	\N
61e27137-4680-4008-aecf-507ad46115f2	gordo@yahoo.com	2022-09-15 13:49:27.72	\N
a12b808c-24d1-4ec4-94fb-5cab466c8b2d	gordo@yahoo.com	2022-09-15 13:49:38.959	\N
76159425-89df-476e-aaee-b4e6ef7c36df	gordo@yahoo.com	2022-09-15 13:49:56.893	\N
ebdfaf26-64c9-4d08-b02e-afd76b47a6c9	gordo@yahoo.com	2022-09-15 13:51:33.352	\N
9eab7ab2-54a6-4527-908c-b1a1517e4095	gordo@yahoo.com	2022-09-15 13:52:38.55	\N
0b7021fc-b45d-42eb-85d7-4e9e2b53e506	gordo@yahoo.com	2022-09-15 14:09:19.254	\N
1b37521c-bb2e-4ab8-8684-be5e7ab18b09	gordo@yahoo.com	2022-09-15 14:09:39.387	\N
da072cec-7bcd-4959-a1f4-c3396a4be34e	gordo@yahoo.com	2022-09-15 14:43:53.202	\N
408f7449-221c-4dfd-abfb-480d53fc6630	gordo@yahoo.com	2022-09-15 14:50:55.819	\N
6b8cddfc-b3cf-4d31-a6ec-ecfe462ce2bb	gordo@yahoo.com	2022-09-15 14:53:34.474	\N
b20570b5-9f5a-4ccb-a843-cdaf4fb4864e	gordo@yahoo.com	2022-09-15 14:54:27.921	\N
e50204e9-d952-4f80-8745-0d2b7e044a0f	gordo@yahoo.com	2022-09-15 14:55:44.598	\N
17c65e70-95ef-4c06-888f-5fa59a19a7ef	gordo@yahoo.com	2022-09-15 14:58:38.083	\N
54c48fc7-0160-4bab-82a2-0a7ac9749e40	gordo@yahoo.com	2022-09-15 15:02:02.305	\N
86f4b939-be07-4da0-896c-18617cbe4004	gordo@yahoo.com	2022-09-15 15:38:21.891	\N
38662cbf-b641-4f2a-a321-8f321dec34cd	gordo@yahoo.com	2022-09-18 16:04:45.381	\N
de0434d8-d9a6-4878-b375-f804c55f3844	gordo@yahoo.com	2022-09-15 15:39:54.642	2022-09-15 15:40:06.29
a734ad71-2a0b-4c77-8866-48fca66bcf19	gordo@yahoo.com	2022-09-15 15:40:16.174	\N
5e1a38ab-bb45-42fa-b32a-218fecc24374	gordo@yahoo.com	2022-09-15 15:41:02.604	\N
7a13d22a-94e3-4bb0-92d4-9fb5e443dc89	gordo@yahoo.com	2022-09-15 15:42:11.812	\N
79728b6c-2fd7-45d9-907e-20c7cae79943	gordo@yahoo.com	2022-09-15 15:44:52.255	\N
ed5fc784-e393-4ccb-84ef-ee533cf6e6a0	gordo@yahoo.com	2022-09-18 16:04:59.939	\N
b586a894-a3fc-4cc8-9673-2aa6deff6c19	gordo@yahoo.com	2022-09-15 15:48:35.132	2022-09-15 15:49:35.393
8b8c1a83-d16c-494f-bf1b-32c47485553b	gordo@yahoo.com	2022-09-15 15:49:51.275	\N
28ee79f6-4c6d-4992-b457-ffc2ff541442	gordo@yahoo.com	2022-09-18 16:05:13.199	\N
e5b06aa4-ff95-456b-9e5e-494099a7cab8	gordo@yahoo.com	2022-09-15 15:54:26.513	2022-09-15 16:05:16.549
1a522e6f-d34f-4937-92c3-ad53075ce5df	gordo@yahoo.com	2022-09-18 16:06:43.478	\N
29992977-f9a8-49c3-95dd-1578d5f641fd	gordo@yahoo.com	2022-09-15 16:06:23.484	2022-09-15 16:06:29.215
abed3a67-dd52-4aaf-a6ed-373a5b542528	gordo@yahoo.com	2022-09-18 16:08:25.22	\N
256e094b-6494-4ee9-b62b-ddb6612a71df	gordo@yahoo.com	2022-09-18 16:10:12.317	\N
ab05bc12-c202-4abb-a18d-a638d80161e2	gordo@yahoo.com	2022-09-18 16:12:13.978	\N
e8c78c5d-b9e9-4247-a0e2-076344d774dc	gordo@yahoo.com	2022-09-18 16:15:38.664	\N
50d150ca-3a23-4f9a-854c-e625a93047c2	gordo@yahoo.com	2022-10-25 10:10:48.515	\N
f972695e-609d-4d6f-87d9-53539f2b4c50	gordo@yahoo.com	2022-09-18 16:16:15.738	2022-09-18 16:37:55.221
b160048b-6dfb-41fb-a3f8-93a4d5ebc45b	gordo@yahoo.com	2022-09-18 16:41:19.084	\N
be6291f2-1108-4649-b888-c33c93dd45a2	gordo@yahoo.com	2022-09-20 10:56:32.621	\N
a85ff408-6adf-4f68-b9e2-62a0e32015af	gordo@yahoo.com	2022-09-20 10:57:21.219	\N
77e92fd5-c623-4d59-863b-2b26a4ff3310	gordo@yahoo.com	2022-09-20 11:03:25.185	\N
7ebe6c7d-3779-4b63-85d5-2a731081f677	gordo@yahoo.com	2022-09-20 11:03:38.81	\N
7488378e-075c-46b6-8b64-70a68f31b171	gordo@yahoo.com	2022-09-20 13:38:08.789	\N
f581ee97-e87b-4fdd-befb-bf047e670aaa	gordo@yahoo.com	2022-10-10 14:52:43.959	\N
b5e4de9a-6d65-4e49-9fc9-5255822c5a3c	gordo@yahoo.com	2022-10-10 14:52:46.765	\N
35eb854e-3e8e-4776-8fdb-f6c9e9be306c	gordo@yahoo.com	2022-10-10 14:53:05.294	\N
c0da7c66-9c94-4572-b9d2-32705431f445	gordo@yahoo.com	2022-10-25 10:10:49.161	\N
ffbeaaae-24a9-4eb8-89ad-2e8339ec50e6	gordo@yahoo.com	2022-09-20 14:26:07.855	2022-10-10 15:12:16.732
c00b13f1-9487-4f6b-8de1-8247b9afe1c5	gordo@yahoo.com	2022-10-10 15:37:07.063	\N
51adfb83-655f-47e5-8263-80febcc48f47	gordo@yahoo.com	2022-10-10 15:42:47.399	\N
a1f9b3e5-e3ff-4e59-917f-c1fb317ad904	gordo@yahoo.com	2022-10-10 15:43:31.958	\N
75cccdd2-6e00-4d8a-a971-23fc6cb97a44	gordo@yahoo.com	2022-10-10 15:43:32.62	\N
dacac914-78cd-4f49-8ab7-8cdbf4c67b30	gordo@yahoo.com	2022-10-10 15:43:32.823	\N
ee9da073-c430-4101-ba94-10c590c7507f	gordo@yahoo.com	2022-10-10 15:51:14.321	\N
797a7b2e-b0d5-4cde-9f48-ae5a65a9293d	gordo@yahoo.com	2022-10-11 15:49:07.378	\N
f99fb871-267f-453b-b0b8-85da7ed75784	gordo@yahoo.com	2022-10-11 15:50:25.188	\N
4e0e4531-e2d7-4543-a8d1-0558fad15623	gordo@yahoo.com	2022-10-25 10:11:48.601	\N
f4922196-8d6c-42b1-94f3-9cd167fbf982	gordo@yahoo.com	2022-10-25 10:14:00.473	\N
7a885a42-fc6a-4966-8cbb-97bab7c99b24	gordo@yahoo.com	2022-10-25 10:14:42.844	\N
6d33324f-6f88-47ee-9f00-e1173f0d6b6b	jorgep@gmail.com	2023-03-24 14:46:19.871	\N
06865b47-e5fa-4d74-89b6-e974a6c780fb	jorgep@gmail.com	2023-03-24 15:22:19.506	\N
8c0028b8-4883-45ea-99e2-268160628f2c	jorgep@gmail.com	2023-03-24 15:23:40.492	\N
60c0bca2-2733-4679-92d8-287d7315c82f	jorgep@gmail.com	2023-03-24 15:29:35.409	\N
cee5d85d-3470-4c5a-aa64-bb784c59bf8d	jorgep@gmail.com	2023-03-27 13:01:35.406	\N
b61cbc64-2eb2-4899-92dc-ce2649dc74bb	jorgep@gmail.com	2023-03-27 13:05:03.125	\N
f98ea7aa-27ac-45be-b881-d5e6de3a0783	jorgep@gmail.com	2023-03-29 14:44:57.979	\N
\.


--
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.accounts (identifier, name, address, postcode, country, telephone, number_of_employees, users_limit) FROM stdin;
a06dc590-8690-431a-9de0-167905011b47	Sky News	\N	\N	\N	\N	51-100	5
b81c2aba-b0ee-4642-9f90-7cc435ba2bcf	jorge.pontoriero@gmail.com	\N	\N	\N	\N	\N	5
\.


--
-- Data for Name: contracts; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.contracts (identifier, start_date, cont_service, contract_end_date, onboard_date, onboard_contract, benefits_start, review_date, job_type, country, office_role, location, department, team, cost_centre, line_manager, right_to_work, right_to_work_expiry) FROM stdin;
\.


--
-- Data for Name: documents; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.documents (identifier, img, profile_identifier, name, date_created) FROM stdin;
\.


--
-- Data for Name: holidays; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.holidays (identifier, start, end_date, comments, type, status, profile_identifier, authorized_by, date_created, mod_date, includesaturday, includesunday, halfdaystart, halfdayend) FROM stdin;
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
\.


--
-- Data for Name: profiles; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.profiles (identifier, firstname, lastname, email, deleted, account_identifier, dob, entitlement_absence, avatar, title, known_as, address, gender, gender_identity, preferred_pronoun, marital_status, employee_number, work_phone, work_extn, work_mobile, personal_email, personal_mobile, home_phone, contract_identifier) FROM stdin;
ffbbd930-53d3-4000-95b4-bf79840a0491	Jordan	Peterson	jordan@gmail.com	f	a06dc590-8690-431a-9de0-167905011b47	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
edb0a07a-544e-4f5a-8140-06a77b6f8d1a	Jorge	Pontoriero	jorgep@gmail.com	f	b81c2aba-b0ee-4642-9f90-7cc435ba2bcf	\N	0	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: role_positions; Type: TABLE DATA; Schema: public; Owner: mvelasco
--

COPY public.role_positions (identifier, requested_by, grade, salary_level, job_description, contract_type, date_created, start_date, header) FROM stdin;
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
-- Name: payments_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (identifier);


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
-- Name: shareddocument_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.shareddocument_profile
    ADD CONSTRAINT shareddocument_profile_pkey PRIMARY KEY (profile_identifier, document_identifier);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_name);


--
-- Name: documents_profile_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_profile_identifier_fkey FOREIGN KEY (profile_identifier) REFERENCES public.profiles(identifier);


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
-- Name: profiles_contract_identifier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_contract_identifier_fkey FOREIGN KEY (contract_identifier) REFERENCES public.contracts(identifier);


--
-- Name: role_positions_requested_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mvelasco
--

ALTER TABLE ONLY public.role_positions
    ADD CONSTRAINT role_positions_requested_by_fkey FOREIGN KEY (requested_by) REFERENCES public.profiles(identifier);


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
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: mvelasco
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM mvelasco;
GRANT ALL ON SCHEMA public TO mvelasco;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

