
CREATE SCHEMA IF NOT EXISTS `HealthBody`;
USE `HealthBody` ;

CREATE TABLE IF NOT EXISTS `MetaData` (
  `id_MetaData` INT NOT NULL,
  `LastSynch` DATETIME NULL,
  PRIMARY KEY (`id_MetaData`),
  UNIQUE INDEX `id_MetaData_UNIQUE` (`id_MetaData` ASC))

CREATE TABLE IF NOT EXISTS `Roles` (
  `id_Role` INT NOT NULL,
  `Name` NVARCHAR(45) NOT NULL,
  `Description` NVARCHAR(180) NULL,
  PRIMARY KEY (`id_Role`),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC),
  UNIQUE INDEX `id_Role_UNIQUE` (`id_Role` ASC))

CREATE TABLE IF NOT EXISTS `Users` (
  `id_User` INT NOT NULL,
  `Login` NVARCHAR(45) NOT NULL,
  `Password` NVARCHAR(45) NOT NULL,
  `Email` NVARCHAR(45) NULL,
  `FirstName` NVARCHAR(45) NULL,
  `LastName` NVARCHAR(45) NULL,
  `Gender` NVARCHAR(45) NULL,
  `Weight` DOUBLE NULL,
  `Age` INT NULL,
  `Health` NVARCHAR(45) NULL,
  `Avatar` NVARCHAR(45) NULL,
  `GoogleData` NVARCHAR(180) NULL,
  `id_Role` INT NOT NULL,
  PRIMARY KEY (`id_User`),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC),
  UNIQUE INDEX `id_User_UNIQUE` (`id_User` ASC),
  INDEX `fk_User_Roles_idx` (`id_Role` ASC),
  CONSTRAINT `fk_User_Roles`
    FOREIGN KEY (`id_Role`)
    REFERENCES `Roles` (`id_Role`))

CREATE TABLE IF NOT EXISTS `Groups` (
  `id_Group` INT NOT NULL,
  `Name` NVARCHAR(45) NOT NULL,
  `Description` NVARCHAR(180) NULL,
  `Status` NVARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_Group`),
  UNIQUE INDEX `id_Group_UNIQUE` (`id_Group` ASC))

CREATE TABLE IF NOT EXISTS `UserGroups` (
  `id_UserGroups` INT NOT NULL,
  `id_User` INT NOT NULL,
  `id_Group` INT NOT NULL,
  PRIMARY KEY (`id_UserGroups`),
  UNIQUE INDEX `id_UserGroups_UNIQUE` (`id_UserGroups` ASC),
  INDEX `fk_UserGroup_Users_idx` (`id_User` ASC),
  INDEX `fk_UserGroup_Groups_idx` (`id_Group` ASC),
  CONSTRAINT `fk_UserGroup_Users`
    FOREIGN KEY (`id_User`)
    REFERENCES `Users` (`id_User`),
  CONSTRAINT `fk_UserGroup_Groups`
    FOREIGN KEY (`id_Group`)
    REFERENCES `Groups` (`id_Group`))

CREATE TABLE IF NOT EXISTS `Criterias` (
  `id_Criteria` INT NOT NULL,
  `Name` NVARCHAR(45) NOT NULL,
  `Metrics` DOUBLE NULL,
  `GetGoogle` NVARCHAR(180) NULL,
  PRIMARY KEY (`id_Criteria`),
  UNIQUE INDEX `id_Criteria_UNIQUE` (`id_Criteria` ASC))

CREATE TABLE IF NOT EXISTS `Competitions` (
  `id_Competition` INT NOT NULL,
  `Name` NVARCHAR(45) NOT NULL,
  `Description` NVARCHAR(45) NULL,
  `Start` DATETIME NOT NULL,
  `End` DATETIME NOT NULL,
  `id_Criteria` INT NOT NULL,
  PRIMARY KEY (`id_Competition`),
  UNIQUE INDEX `id_Competition_UNIQUE` (`id_Competition` ASC),
  INDEX `fk_Competition_Criterias_idx` (`id_Criteria` ASC),
  CONSTRAINT `fk_Competition_Criterias`
    FOREIGN KEY (`id_Criteria`)
    REFERENCES `Criterias` (`id_Criteria`))

CREATE TABLE IF NOT EXISTS `UserCompetitions` (
  `id_UserCompetition` INT NOT NULL,
  `id_User` INT NOT NULL,
  `id_Competition` INT NOT NULL,
  `UserScore` DOUBLE NULL,
  PRIMARY KEY (`id_UserCompetition`),
  UNIQUE INDEX `id_UserCompetition_UNIQUE` (`id_UserCompetition` ASC),
  INDEX `fk_UserCompetition_Users_idx` (`id_User` ASC),
  INDEX `fk_UserCompetition_Competitions_idx` (`id_Competition` ASC),
  CONSTRAINT `fk_UserCompetition_Users`
    FOREIGN KEY (`id_User`)
    REFERENCES `Users` (`id_User`),
  CONSTRAINT `fk_UserCompetition_Competitions`
    FOREIGN KEY (`id_Competition`)
    REFERENCES `Competitions` (`id_Competition`))

CREATE TABLE IF NOT EXISTS `GroupCompetitions` (
  `id_GroupCompetition` INT NOT NULL,
  `id_Group` INT NOT NULL,
  `id_Competition` INT NOT NULL,
  PRIMARY KEY (`id_GroupCompetition`),
  UNIQUE INDEX `id_GroupCompetition_UNIQUE` (`id_GroupCompetition` ASC),
  INDEX `fk_GroupCompetition_Groups_idx` (`id_Group` ASC),
  INDEX `fk_GroupCompetition_Competitions_idx` (`id_Competition` ASC),
  CONSTRAINT `fk_GroupCompetition_Groups`
    FOREIGN KEY (`id_Group`)
    REFERENCES `Groups` (`id_Group`),
  CONSTRAINT `fk_GroupCompetition_Competitions`
    FOREIGN KEY (`id_Competition`)
    REFERENCES `Competitions` (`id_Competition`))

