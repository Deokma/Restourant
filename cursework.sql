--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: menu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menu (
    dishid integer NOT NULL,
    dishname character varying(50),
    price integer,
    image character varying(450),
    categoryid integer
);


ALTER TABLE public.menu OWNER TO postgres;

--
-- Name: menu_dishid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.menu_dishid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.menu_dishid_seq OWNER TO postgres;

--
-- Name: menu_dishid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.menu_dishid_seq OWNED BY public.menu.dishid;


--
-- Name: menuorder; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menuorder (
    orderid integer NOT NULL,
    dishid integer,
    quentity integer,
    userid integer,
    dishname character varying(45),
    price integer
);


ALTER TABLE public.menuorder OWNER TO postgres;

--
-- Name: menuorder_orderid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.menuorder_orderid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.menuorder_orderid_seq OWNER TO postgres;

--
-- Name: menuorder_orderid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.menuorder_orderid_seq OWNED BY public.menuorder.orderid;


--
-- Name: order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."order" (
    orderid integer NOT NULL,
    userid integer,
    totalsum integer,
    status integer
);


ALTER TABLE public."order" OWNER TO postgres;

--
-- Name: order_orderid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_orderid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_orderid_seq OWNER TO postgres;

--
-- Name: order_orderid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_orderid_seq OWNED BY public."order".orderid;


--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    roleid integer NOT NULL,
    name character varying(15)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    userid integer NOT NULL,
    login character varying(50),
    roleid integer,
    password character varying(100),
    firstname character varying(50),
    lastname character varying(50)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_userid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_userid_seq OWNER TO postgres;

--
-- Name: users_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;


--
-- Name: menu dishid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu ALTER COLUMN dishid SET DEFAULT nextval('public.menu_dishid_seq'::regclass);


--
-- Name: menuorder orderid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menuorder ALTER COLUMN orderid SET DEFAULT nextval('public.menuorder_orderid_seq'::regclass);


--
-- Name: order orderid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order" ALTER COLUMN orderid SET DEFAULT nextval('public.order_orderid_seq'::regclass);


--
-- Name: users userid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);


--
-- Data for Name: menu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.menu (dishid, dishname, price, image, categoryid) FROM stdin;
1	Chicken Burger	3	https://statickfc.cdnvideo.ru/products/medium/product_2196_632970723.png	1
2	Sheph Burger	7	https://statickfc.cdnvideo.ru/products/medium/product_967_1334786084.png	1
3	Sheph Burger Delux	8	https://statickfc.cdnvideo.ru/products/medium/product_916_1538550399.png	1
4	Sanders Burger 	10	https://statickfc.cdnvideo.ru/products/medium/product_2186_29036248.png	1
5	Sanders Burger Delux	11	https://statickfc.cdnvideo.ru/products/medium/product_2188_530993911.png	1
6	Party Basket	6	https://statickfc.cdnvideo.ru/products/medium/product_1614_891725575.png	1
7	Sanders Basket	9	https://statickfc.cdnvideo.ru/products/medium/product_468_1705959245.png	1
8	Original Basket	19	https://statickfc.cdnvideo.ru/products/medium/product_1696_1475962227.png	1
9	Busket Duet Original	20	https://statickfc.cdnvideo.ru/products/medium/product_372_1039009411.png	1
10	Busket S (12 wings)	17	https://statickfc.cdnvideo.ru/products/medium/product_371_1576551084.png	1
11	French fries small	3	https://statickfc.cdnvideo.ru/products/medium/product_376_189222180.png	1
12	Rustic potatoes	3	https://statickfc.cdnvideo.ru/products/medium/product_753_813500048.png	1
13	French fries standard	4	https://statickfc.cdnvideo.ru/products/medium/product_375_1466606504.png	1
14	Rustic potatoes standard	4	https://statickfc.cdnvideo.ru/products/medium/product_414_933432418.png	1
15	French basket	6	https://statickfc.cdnvideo.ru/products/medium/product_374_22152981.png	1
16	Soft drink 0.5	4	https://statickfc.cdnvideo.ru/products/medium/product_394_197715402.png	2
17	Mojito Lemonade 0.3	4	https://statickfc.cdnvideo.ru/products/medium/product_2199_696162699.png	2
18	Cappuccino coffee 0.3	5	https://statickfc.cdnvideo.ru/products/medium/product_383_888599585.png	2
19	Latte coffee 0.3	5	https://statickfc.cdnvideo.ru/products/medium/product_384_703473299.png	2
20	Strawberry Milkshake 0.4	5	https://statickfc.cdnvideo.ru/products/medium/product_759_1651163291.png	2
\.


--
-- Data for Name: menuorder; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.menuorder (orderid, dishid, quentity, userid, dishname, price) FROM stdin;
62	5	1	15	Sanders Burger Delux	11
\.


--
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."order" (orderid, userid, totalsum, status) FROM stdin;
63	19	8	2
65	19	17	2
61	14	4	1
60	14	19	1
64	19	10	1
59	14	6	2
57	14	4	1
58	14	10	1
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (roleid, name) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (userid, login, roleid, password, firstname, lastname) FROM stdin;
14	deokma	1	$2a$10$Lf4x13FZ7Z68rU9etWhat.7DRHR2FWYCJgnKc/hEGTQdubOdiSv7.	Denis	Popolamov
15	denis	2	$2a$10$nQt3yNB0LUQEy5AXGs9m1eNDV32xrB6wL7IVPYjMbjukISRRJpKlW	Denis	Denis
17	denissad	1	$2a$10$mO2U/cnXzCLpvVk1aEDOiOSq3FIyj4PM7/FqX/y.sjE3vT97zSCAe	asdasd	asdasd
18	deokma123	1	$2a$10$j1ncisT9HYBVvsfim50BTuILqBy/hmKBatj7SU1YQSQd7FR87Gdku	Denis	Denis
19	Denis	1	$2a$10$y/fB8K5LPGbmTSt.yGkElehrrEgfSSuaNG7aOqI.8QN7ZkbKzqOA2	Denis	Denis
20	denis.denison4ik	1	$2a$10$XC2jUCZTLjQhXgoLe8en4.oSjxJdyWmQz6STN.0e1/lskHuOMbkyy	wqeqw	wqeqw
21	10467824	1	$2a$10$UZUJ7EmaIc8L1khNSIOAq.AFpQt1qDhjgoX0wgnsgcpiItOBvZS4q	Денис	Денис
22	ыуепуцк	1	$2a$10$ZCxRmwFb3lztfmVggqf0WO4MQ/jqvABKBcnnvThDC7g6Xrj1GKvhi	sadad	sadad
23	dsadxzc	1	$2a$10$A.wFzRuIWevjL8AJORJiQ.RF.StOctx34LF399dSdgtiJOAKpTYle	wq4ewr	wq4ewr
\.


--
-- Name: menu_dishid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.menu_dishid_seq', 5, true);


--
-- Name: menuorder_orderid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.menuorder_orderid_seq', 65, true);


--
-- Name: order_orderid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_orderid_seq', 1, true);


--
-- Name: users_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_userid_seq', 23, true);


--
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (dishid);


--
-- Name: menuorder menuorder_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menuorder
    ADD CONSTRAINT menuorder_pkey PRIMARY KEY (orderid);


--
-- Name: order order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (orderid);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (roleid);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);


--
-- Name: menuorder foreign_key_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menuorder
    ADD CONSTRAINT foreign_key_name FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: menuorder menuorder_dishid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menuorder
    ADD CONSTRAINT menuorder_dishid_fkey FOREIGN KEY (dishid) REFERENCES public.menu(dishid);


--
-- Name: order order_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: roles roles_roleid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_roleid_fkey FOREIGN KEY (roleid) REFERENCES public.users(userid);


--
-- PostgreSQL database dump complete
--

