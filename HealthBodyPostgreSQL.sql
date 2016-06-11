drop database if exists healthbody;
create database healthbody;
\c healthbody;

create table if not exists MetaData (
  id_metadata int not null,
  lastsynch timestamp null,
  primary key (id_metadata));
  create unique index id_metadata_unique on MetaData (id_metadata asc);
  
create table if not exists Roles (
  id_role int not null,
  name varchar(45) not null,
  description varchar(180) null,
  primary key (id_role));
  create unique index name_unique on Roles (name asc);
  create unique index id_role_unique on Roles (id_role asc);

create table if not exists Users (
  id_user int not null,
  login varchar(45) not null,
  password varchar(45) not null,
  email varchar(45) null,
  firstname varchar(45) null,
  lastname varchar(45) null,
  gender varchar(45) null,
  weight double precision null,
  age int null,
  health varchar(45) null,
  avatar varchar(45) null,
  googledata varchar(180) null,
  id_role int not null,
  primary key (id_user),
  constraint fk_user_roles
    foreign key (id_role)
    references Roles (id_role));
	create unique index login_unique on Users (login asc);
	create unique index id_user_unique on Users (id_user asc);
	create index fk_user_roles_idx on Users (id_role asc);

create table if not exists Groups (
  id_group int not null,
  name varchar(45) not null,
  description varchar(180) null,
  status varchar(45) not null,
  primary key (id_group));
  create unique index id_group_unique on Groups (id_group asc);

create table if not exists Criterias (
  id_criteria int not null,
  name varchar(45) not null,
  metrics double precision null,
  getgoogle varchar(180) null,
  primary key (id_criteria));
  create unique index id_criteria_unique on Criterias (id_criteria asc);

create table if not exists Competitions (
  id_competition int not null,
  name varchar(45) not null,
  description varchar(45) null,
  start timestamp not null,
  finish timestamp not null,
  id_criteria int not null,
  primary key (id_competition),
  constraint fk_competition_criterias
    foreign key (id_criteria)
    references Criterias (id_criteria));
	create unique index id_competition_unique on Competitions (id_competition asc);
	create index fk_competition_criterias_idx on Competitions (id_criteria asc);
	
create table if not exists UserGroups (
  id_usergroups int not null,
  id_user int not null,
  id_group int not null,
  primary key (id_usergroups),
  constraint fk_usergroup_users
    foreign key (id_user)
    references Users (id_user),
  constraint fk_usergroup_groups
    foreign key (id_group)
    references Groups (id_group));
	create unique index id_usergroups_unique on UserGroups (id_usergroups asc);
	create index fk_usergroup_users_idx on UserGroups (id_user asc);
	create index fk_usergroup_groups_idx on UserGroups (id_group asc);

create table if not exists UserCompetitions (
  id_usercompetition int not null,
  id_user int not null,
  id_competition int not null,
  userscore double precision null,
  primary key (id_usercompetition),
  constraint fk_usercompetition_users
    foreign key (id_user)
    references Users (id_user),
  constraint fk_usercompetition_competitions
    foreign key (id_competition)
    references Competitions (id_competition));
	create unique index id_usercompetition_unique on UserCompetitions (id_usercompetition asc);
	create index fk_UserCompetition_Users_idx on UserCompetitions (id_user asc);
	create index fk_UserCompetition_Competitions_idx on UserCompetitions (id_competition asc);

create table if not exists GroupCompetitions (
  id_groupcompetition int not null,
  id_group int not null,
  id_competition int not null,
  primary key (id_groupcompetition),
  constraint fk_groupcompetition_groups
    foreign key (id_group)
    references Groups (id_group),
  constraint fk_groupcompetition_competitions
    foreign key (id_competition)
    references Competitions (id_competition));
	create unique index id_groupcompetition_unique on GroupCompetitions (id_groupcompetition asc);
	create index fk_groupcompetition_groups_idx on GroupCompetitions (id_group asc);
	create index fk_groupcompetition_competitions_idx on GroupCompetitions (id_competition asc);