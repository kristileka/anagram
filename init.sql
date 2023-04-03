--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0 (Debian 14.0-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

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
-- Name: stateful_word; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.stateful_word (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    letter_count integer,
    predicate character varying(255),
    value character varying(255)
);


ALTER TABLE public.stateful_word OWNER TO "user";

--
-- Data for Name: stateful_word; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.stateful_word (id, created_at, letter_count, predicate, value) FROM stdin;
\.


--
-- Name: stateful_word stateful_word_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.stateful_word
    ADD CONSTRAINT stateful_word_pkey PRIMARY KEY (id);


--
-- Name: stateful_word uk_tnugqsrasrybduwc0eh7u7lfp; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.stateful_word
    ADD CONSTRAINT uk_tnugqsrasrybduwc0eh7u7lfp UNIQUE (value);


--
-- Name: letter_count_index; Type: INDEX; Schema: public; Owner: user
--

CREATE INDEX letter_count_index ON public.stateful_word USING btree (letter_count);


--
-- PostgreSQL database dump complete
--

