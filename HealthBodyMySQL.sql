drop database if exists healthbody;
create database healthbody;
use healthbody;

create table if not exists MetaData (
  id_metadata int not null,
  lastsynch datetime null,
  primary key (id_metadata),
  unique index id_metadata_unique (id_metadata asc));
  
create table if not exists Roles (
  id_role int not null,
  name nvarchar(45) not null,
  description nvarchar(180) null,
  primary key (id_role),
  unique index name_unique (name asc),
  unique index id_role_unique (id_role asc));

create table if not exists Users (
  id_user int not null,
  login nvarchar(45) not null,
  password nvarchar(45) not null,
  email nvarchar(45) null,
  firstname nvarchar(45) null,
  lastname nvarchar(45) null,
  gender nvarchar(45) null,
  weight double null,
  age int null,
  health nvarchar(45) null,
  avatar nvarchar(45) null,
  googledata nvarchar(180) null,
  id_role int not null,
  primary key (id_user),
  unique index login_unique (login asc),
  unique index id_user_unique (id_user asc),
  index fk_user_roles_idx (id_role asc),
  constraint fk_user_roles
    foreign key (id_role)
    references Roles (id_role));

create table if not exists Groups (
  id_group int not null,
  name nvarchar(45) not null,
  description nvarchar(180) null,
  status nvarchar(45) not null,
  primary key (id_group),
  unique index id_group_unique (id_group asc));

create table if not exists Criterias (
  id_criteria int not null,
  name nvarchar(45) not null,
  metrics double null,
  getgoogle nvarchar(180) null,
  primary key (id_criteria),
  unique index id_criteria_unique (id_criteria asc));

create table if not exists Competitions (
  id_competition int not null,
  name nvarchar(45) not null,
  description nvarchar(45) null,
  start datetime not null,
  end datetime not null,
  id_criteria int not null,
  primary key (id_competition),
  unique index id_competition_unique (id_competition asc),
  index fk_competition_criterias_idx (id_criteria asc),
  constraint fk_competition_criterias
    foreign key (id_criteria)
    references Criterias (id_criteria));
	
create table if not exists UserGroups (
  id_usergroups int not null,
  id_user int not null,
  id_group int not null,
  primary key (id_usergroups),
  unique index id_usergroups_unique (id_usergroups asc),
  index fk_usergroup_users_idx (id_user asc),
  index fk_usergroup_groups_idx (id_group asc),
  constraint fk_usergroup_users
    foreign key (id_user)
    references Users (id_user),
  constraint fk_usergroup_groups
    foreign key (id_group)
    references Groups (id_group));

create table if not exists UserCompetitions (
  id_usercompetition int not null,
  id_user int not null,
  id_competition int not null,
  userscore double null,
  primary key (id_usercompetition),
  unique index id_usercompetition_unique (id_usercompetition asc),
  index fk_UserCompetition_Users_idx (id_user asc),
  index fk_UserCompetition_Competitions_idx (id_competition asc),
  constraint fk_usercompetition_users
    foreign key (id_user)
    references Users (id_user),
  constraint fk_usercompetition_competitions
    foreign key (id_competition)
    references Competitions (id_competition));

create table if not exists groupcompetitions (
  id_groupcompetition int not null,
  id_group int not null,
  id_competition int not null,
  primary key (id_groupcompetition),
  unique index id_groupcompetition_unique (id_groupcompetition asc),
  index fk_groupcompetition_groups_idx (id_group asc),
  index fk_groupcompetition_competitions_idx (id_competition asc),
  constraint fk_groupcompetition_groups
    foreign key (id_group)
    references Groups (id_group),
  constraint fk_groupcompetition_competitions
    foreign key (id_competition)
    references Competitions (id_competition))