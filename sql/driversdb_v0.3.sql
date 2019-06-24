--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4
-- Dumped by pg_dump version 11.4

-- Started on 2019-06-23 20:27:31

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
-- TOC entry 6 (class 2615 OID 16394)
-- Name: driversdb; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA driversdb;


ALTER SCHEMA driversdb OWNER TO postgres;

--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA driversdb; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA driversdb IS 'Тестовая БД поиска водителей';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 199 (class 1259 OID 16405)
-- Name: cars; Type: TABLE; Schema: driversdb; Owner: postgres
--

CREATE TABLE driversdb.cars (
    type character varying(50) NOT NULL,
    model character varying(50),
    plate character varying(12) NOT NULL,
    id_driver integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE driversdb.cars OWNER TO postgres;

--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 199
-- Name: TABLE cars; Type: COMMENT; Schema: driversdb; Owner: postgres
--

COMMENT ON TABLE driversdb.cars IS 'Автомобили';


--
-- TOC entry 203 (class 1259 OID 16458)
-- Name: cars_id_seq; Type: SEQUENCE; Schema: driversdb; Owner: postgres
--

CREATE SEQUENCE driversdb.cars_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE driversdb.cars_id_seq OWNER TO postgres;

--
-- TOC entry 2856 (class 0 OID 0)
-- Dependencies: 203
-- Name: cars_id_seq; Type: SEQUENCE OWNED BY; Schema: driversdb; Owner: postgres
--

ALTER SEQUENCE driversdb.cars_id_seq OWNED BY driversdb.cars.id;


--
-- TOC entry 198 (class 1259 OID 16400)
-- Name: cities; Type: TABLE; Schema: driversdb; Owner: postgres
--

CREATE TABLE driversdb.cities (
    name character varying(50) NOT NULL,
    id integer NOT NULL
);


ALTER TABLE driversdb.cities OWNER TO postgres;

--
-- TOC entry 2857 (class 0 OID 0)
-- Dependencies: 198
-- Name: TABLE cities; Type: COMMENT; Schema: driversdb; Owner: postgres
--

COMMENT ON TABLE driversdb.cities IS 'Города';


--
-- TOC entry 201 (class 1259 OID 16436)
-- Name: cities_id_seq; Type: SEQUENCE; Schema: driversdb; Owner: postgres
--

CREATE SEQUENCE driversdb.cities_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE driversdb.cities_id_seq OWNER TO postgres;

--
-- TOC entry 2858 (class 0 OID 0)
-- Dependencies: 201
-- Name: cities_id_seq; Type: SEQUENCE OWNED BY; Schema: driversdb; Owner: postgres
--

ALTER SEQUENCE driversdb.cities_id_seq OWNED BY driversdb.cities.id;


--
-- TOC entry 197 (class 1259 OID 16395)
-- Name: drivers; Type: TABLE; Schema: driversdb; Owner: postgres
--

CREATE TABLE driversdb.drivers (
    family_name character varying(50) NOT NULL,
    first_name character varying(50) NOT NULL,
    second_name character varying(50),
    id_city integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE driversdb.drivers OWNER TO postgres;

--
-- TOC entry 2859 (class 0 OID 0)
-- Dependencies: 197
-- Name: TABLE drivers; Type: COMMENT; Schema: driversdb; Owner: postgres
--

COMMENT ON TABLE driversdb.drivers IS 'Водители';


--
-- TOC entry 202 (class 1259 OID 16449)
-- Name: drivers_id_seq; Type: SEQUENCE; Schema: driversdb; Owner: postgres
--

CREATE SEQUENCE driversdb.drivers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE driversdb.drivers_id_seq OWNER TO postgres;

--
-- TOC entry 2860 (class 0 OID 0)
-- Dependencies: 202
-- Name: drivers_id_seq; Type: SEQUENCE OWNED BY; Schema: driversdb; Owner: postgres
--

ALTER SEQUENCE driversdb.drivers_id_seq OWNED BY driversdb.drivers.id;


--
-- TOC entry 200 (class 1259 OID 16410)
-- Name: users; Type: TABLE; Schema: driversdb; Owner: postgres
--

CREATE TABLE driversdb.users (
    username character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    fullname character varying(50) NOT NULL,
    id integer NOT NULL
);


ALTER TABLE driversdb.users OWNER TO postgres;

--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 200
-- Name: TABLE users; Type: COMMENT; Schema: driversdb; Owner: postgres
--

COMMENT ON TABLE driversdb.users IS 'Пользователи';


--
-- TOC entry 204 (class 1259 OID 16472)
-- Name: users_id_seq; Type: SEQUENCE; Schema: driversdb; Owner: postgres
--

CREATE SEQUENCE driversdb.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE driversdb.users_id_seq OWNER TO postgres;

--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: driversdb; Owner: postgres
--

ALTER SEQUENCE driversdb.users_id_seq OWNED BY driversdb.users.id;


--
-- TOC entry 2706 (class 2604 OID 16460)
-- Name: cars id; Type: DEFAULT; Schema: driversdb; Owner: postgres
--

ALTER TABLE ONLY driversdb.cars ALTER COLUMN id SET DEFAULT nextval('driversdb.cars_id_seq'::regclass);


--
-- TOC entry 2705 (class 2604 OID 16438)
-- Name: cities id; Type: DEFAULT; Schema: driversdb; Owner: postgres
--

ALTER TABLE ONLY driversdb.cities ALTER COLUMN id SET DEFAULT nextval('driversdb.cities_id_seq'::regclass);


--
-- TOC entry 2704 (class 2604 OID 16451)
-- Name: drivers id; Type: DEFAULT; Schema: driversdb; Owner: postgres
--

ALTER TABLE ONLY driversdb.drivers ALTER COLUMN id SET DEFAULT nextval('driversdb.drivers_id_seq'::regclass);


--
-- TOC entry 2707 (class 2604 OID 16474)
-- Name: users id; Type: DEFAULT; Schema: driversdb; Owner: postgres
--

ALTER TABLE ONLY driversdb.users ALTER COLUMN id SET DEFAULT nextval('driversdb.users_id_seq'::regclass);


--
-- TOC entry 2843 (class 0 OID 16405)
-- Dependencies: 199
-- Data for Name: cars; Type: TABLE DATA; Schema: driversdb; Owner: postgres
--

COPY driversdb.cars (type, model, plate, id_driver, id) FROM stdin;
Lada	Granta	T957AO49\n	13	14
Lada	Vesta SW Cross	T299OM92\n	14	15
ГАЗ	2410 "Волга"	E534MC69\n	1	16
Land Rover	Defender\n	M447KB91\n	2	17
Mercedes	Geländewagen 460	C966OP87\n	3	18
Porsche	Panamera	H641KB16\n	4	19
Toyota\n	Corolla 2	C822AE84\n	1	2
Toyota	Land Cruiser 200 Anniversary	A474OK11\n	2	3
Toyota\n	Land Cruiser Prado	K441HA154\n	3	4
Toyota\n	Vista	T721BC72\n	4	5
Ford	Expedition	A674BA58\n	5	6
Ford	Explorer	E938KB11\n	6	7
Nissan	Sunny	A363KP15\n	7	8
Nissan	Pathfinder	E331BA34\n	8	9
Chevrolet	Camaro	B257KA61\n	9	10
Chevrolet	Tahoe	P936OM68\n	10	11
Lexus	SX330	H995BM33\n	11	12
Lexus	RX530h	O481OP58\n	12	13
\.


--
-- TOC entry 2842 (class 0 OID 16400)
-- Dependencies: 198
-- Data for Name: cities; Type: TABLE DATA; Schema: driversdb; Owner: postgres
--

COPY driversdb.cities (name, id) FROM stdin;
Воронеж	1
Новосибирск	2
Пыть-Ях	3
Бурмистрово	4
Париж	5
Новгород	6
Псков	7
Ленинабад	8
Тайшет	9
Братск	10
\.


--
-- TOC entry 2841 (class 0 OID 16395)
-- Dependencies: 197
-- Data for Name: drivers; Type: TABLE DATA; Schema: driversdb; Owner: postgres
--

COPY driversdb.drivers (family_name, first_name, second_name, id_city, id) FROM stdin;
Иванов	Сергей	Петрович	1	1
Сидоров	Фёдор	Тимофеевич	2	2
Леопард	Анна	Ивановна	3	3
Синица	Феоктист	Кириллович	4	4
Иванов	Бром	Петрович	1	5
Хайфуллин	Виктор	Абрамович	6	6
Локотко	Эмилия	Кларковна	7	7
Врангель	Иосиф	Францевич	9	8
Голубева	Инна	Яковлевна	7	9
Филателистов	Семион	Эрастович	4	10
Горбунь	Орест	Кристианович	8	11
Неприходько	Кристина	Евгеньевна	9	12
Громов	Ромуальд	Иванович	3	13
Викторина	Юлия	Сергеевна	2	14
\.


--
-- TOC entry 2844 (class 0 OID 16410)
-- Dependencies: 200
-- Data for Name: users; Type: TABLE DATA; Schema: driversdb; Owner: postgres
--

COPY driversdb.users (username, password, fullname, id) FROM stdin;
user1	password	Иван Иванович	1
user2	password	Афанасий Павлович	2
\.


--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 203
-- Name: cars_id_seq; Type: SEQUENCE SET; Schema: driversdb; Owner: postgres
--

SELECT pg_catalog.setval('driversdb.cars_id_seq', 19, true);


--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 201
-- Name: cities_id_seq; Type: SEQUENCE SET; Schema: driversdb; Owner: postgres
--

SELECT pg_catalog.setval('driversdb.cities_id_seq', 10, true);


--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 202
-- Name: drivers_id_seq; Type: SEQUENCE SET; Schema: driversdb; Owner: postgres
--

SELECT pg_catalog.setval('driversdb.drivers_id_seq', 14, true);


--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: driversdb; Owner: postgres
--

SELECT pg_catalog.setval('driversdb.users_id_seq', 2, true);


--
-- TOC entry 2714 (class 2606 OID 16466)
-- Name: cars cars_pkey; Type: CONSTRAINT; Schema: driversdb; Owner: postgres
--

ALTER TABLE ONLY driversdb.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);


--
-- TOC entry 2712 (class 2606 OID 16443)
-- Name: cities cities_pkey; Type: CONSTRAINT; Schema: driversdb; Owner: postgres
--

ALTER TABLE ONLY driversdb.cities
    ADD CONSTRAINT cities_pkey PRIMARY KEY (id);


--
-- TOC entry 2709 (class 2606 OID 16457)
-- Name: drivers drivers_pkey; Type: CONSTRAINT; Schema: driversdb; Owner: postgres
--

ALTER TABLE ONLY driversdb.drivers
    ADD CONSTRAINT drivers_pkey PRIMARY KEY (id);


--
-- TOC entry 2717 (class 2606 OID 16479)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: driversdb; Owner: postgres
--

ALTER TABLE ONLY driversdb.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2710 (class 1259 OID 16420)
-- Name: fki_idCity; Type: INDEX; Schema: driversdb; Owner: postgres
--

CREATE INDEX "fki_idCity" ON driversdb.drivers USING btree (id_city);


--
-- TOC entry 2715 (class 1259 OID 16426)
-- Name: fki_idDriver; Type: INDEX; Schema: driversdb; Owner: postgres
--

CREATE INDEX "fki_idDriver" ON driversdb.cars USING btree (id_driver);


--
-- TOC entry 2718 (class 2606 OID 16444)
-- Name: drivers idCity; Type: FK CONSTRAINT; Schema: driversdb; Owner: postgres
--

ALTER TABLE ONLY driversdb.drivers
    ADD CONSTRAINT "idCity" FOREIGN KEY (id_city) REFERENCES driversdb.cities(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2719 (class 2606 OID 16467)
-- Name: cars idDriver; Type: FK CONSTRAINT; Schema: driversdb; Owner: postgres
--

ALTER TABLE ONLY driversdb.cars
    ADD CONSTRAINT "idDriver" FOREIGN KEY (id_driver) REFERENCES driversdb.drivers(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


-- Completed on 2019-06-23 20:27:31

--
-- PostgreSQL database dump complete
--

