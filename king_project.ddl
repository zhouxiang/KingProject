
DROP TABLE IF EXISTS king_project.t_sys_user;


DROP TABLE IF EXISTS king_project.t_sys_user;

CREATE TABLE IF NOT EXISTS king_project.t_sys_user
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
    user_name character varying(20) ,
    gender character varying(2) ,
    login_name character varying(20) ,
    login_pwd character varying(64) ,
    last_login_time date,
    birth_day date,
    is_frozen character varying(2) ,
    is_active character varying(2) ,
    create_time date,
    update_time date,
    CONSTRAINT t_sys_user_pkey PRIMARY KEY (id)
)
;


DROP TABLE IF EXISTS king_project.t_sys_menu;
CREATE TABLE IF NOT EXISTS king_project.t_sys_menu
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
    menu_name character varying(20) ,
    parent_id bigint,
    is_active character varying(2) ,
    create_time date,
    update_time date,
    CONSTRAINT t_sys_menu_pkey PRIMARY KEY (id)
)
;