-- Drop the database 'DatabaseCourse'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Uncomment the ALTER DATABASE statement below to set the database to SINGLE_USER mode if the drop database command fails because the database is in use.
ALTER DATABASE DatabaseCourse SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
-- Drop the database if it exists
IF EXISTS (
    SELECT [name]
        FROM sys.databases
        WHERE [name] = N'DatabaseCourse'
)
DROP DATABASE DatabaseCourse
GO

-- Create a new database called 'DatabaseCourse'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT [name]
        FROM sys.databases
        WHERE [name] = N'DatabaseCourse'
)
CREATE DATABASE DatabaseCourse
GO

USE DatabaseCourse
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Get_Questions_Followed'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Get_Questions_Followed
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Get_Topics_Followed'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Get_Topics_Followed
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Search_Question'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Search_Question
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Search_Topic'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Search_Topic
GO

DROP TYPE IF EXISTS SEARCH_QUERY;
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Get_Number_of_Answers_Accepted'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Get_Number_of_Answers_Accepted
GO

DROP VIEW IF EXISTS NUMBER_OF_ANSWERS_ACCEPTED;

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Accept_Answer'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Accept_Answer
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Create_Topic'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Create_Topic
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Update_Password'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Update_Password
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Create_Question'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Create_Question
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Follow_Topic'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Follow_Topic
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Follow_Question'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Follow_Question
GO

IF EXISTS (
    SELECT *
        FROM INFORMATION_SCHEMA.ROUTINES
    WHERE SPECIFIC_SCHEMA = N'dbo'
        AND SPECIFIC_NAME = N'Identification_Authentication_Authorization'
        AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Identification_Authentication_Authorization
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Write_Answer'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Write_Answer
GO

IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Create_Account'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Create_Account
GO

/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2005                    */
/* Created on:     5/16/2019 7:02:59 PM                         */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANSWERS') and o.name = 'FK_ANSWERS_ANSWER_OF_QUESTION')
alter table ANSWERS
   drop constraint FK_ANSWERS_ANSWER_OF_QUESTION
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANSWERS') and o.name = 'FK_ANSWERS_WRITING_A_ACCOUNTS')
alter table ANSWERS
   drop constraint FK_ANSWERS_WRITING_A_ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANSWERS___ANSWER_COMMENTS___COMMENTERS') and o.name = 'FK_ANSWERS__ANSWERS___ANSWERS')
alter table ANSWERS___ANSWER_COMMENTS___COMMENTERS
   drop constraint FK_ANSWERS__ANSWERS___ANSWERS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANSWERS___ANSWER_COMMENTS___COMMENTERS') and o.name = 'FK_ANSWERS__ANSWERS___ACCOUNTS')
alter table ANSWERS___ANSWER_COMMENTS___COMMENTERS
   drop constraint FK_ANSWERS__ANSWERS___ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANSWER_COMMENT_VOTE') and o.name = 'FK_ANSWER_C_ANSWER_CO_ACCOUNTS')
alter table ANSWER_COMMENT_VOTE
   drop constraint FK_ANSWER_C_ANSWER_CO_ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANSWER_IMAGES') and o.name = 'FK_ANSWER_I_ANSWERS___ANSWERS')
alter table ANSWER_IMAGES
   drop constraint FK_ANSWER_I_ANSWERS___ANSWERS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANSWER_VOTE') and o.name = 'FK_ANSWER_V_ANSWER_VO_ACCOUNTS')
alter table ANSWER_VOTE
   drop constraint FK_ANSWER_V_ANSWER_VO_ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANSWER_VOTE') and o.name = 'FK_ANSWER_V_ANSWER_VO_ANSWERS')
alter table ANSWER_VOTE
   drop constraint FK_ANSWER_V_ANSWER_VO_ANSWERS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('FOLLOWING') and o.name = 'FK_FOLLOWIN_FOLLOWING_ACCOUNTS2')
alter table FOLLOWING
   drop constraint FK_FOLLOWIN_FOLLOWING_ACCOUNTS2
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('FOLLOWING') and o.name = 'FK_FOLLOWIN_FOLLOWING_ACCOUNTS3')
alter table FOLLOWING
   drop constraint FK_FOLLOWIN_FOLLOWING_ACCOUNTS3
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('FOLLOWING_QUESTION') and o.name = 'FK_FOLLOWIN_FOLLOWING_ACCOUNTS')
alter table FOLLOWING_QUESTION
   drop constraint FK_FOLLOWIN_FOLLOWING_ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('FOLLOWING_QUESTION') and o.name = 'FK_FOLLOWIN_FOLLOWING_QUESTION')
alter table FOLLOWING_QUESTION
   drop constraint FK_FOLLOWIN_FOLLOWING_QUESTION
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('FOLLOWING_TOPIC') and o.name = 'FK_FOLLOWIN_FOLLOWING_TOPICS')
alter table FOLLOWING_TOPIC
   drop constraint FK_FOLLOWIN_FOLLOWING_TOPICS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('FOLLOWING_TOPIC') and o.name = 'FK_FOLLOWIN_FOLLOWING_ACCOUNTS4')
alter table FOLLOWING_TOPIC
   drop constraint FK_FOLLOWIN_FOLLOWING_ACCOUNTS4
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('FRIENDS') and o.name = 'FK_FRIENDS_FRIENDS_ACCOUNTS')
alter table FRIENDS
   drop constraint FK_FRIENDS_FRIENDS_ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('FRIENDS') and o.name = 'FK_FRIENDS_FRIENDS2_ACCOUNTS')
alter table FRIENDS
   drop constraint FK_FRIENDS_FRIENDS2_ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('QUESTIONS') and o.name = 'FK_QUESTION_CREATING__ACCOUNTS')
alter table QUESTIONS
   drop constraint FK_QUESTION_CREATING__ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('QUESTIONS') and o.name = 'FK_QUESTION_LAST_MODI_ACCOUNTS')
alter table QUESTIONS
   drop constraint FK_QUESTION_LAST_MODI_ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('QUESTIONS') and o.name = 'FK_QUESTION_TOPIC_INC_TOPICS')
alter table QUESTIONS
   drop constraint FK_QUESTION_TOPIC_INC_TOPICS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('QUESTIONS___QUESTION_COMMENTS___COMMENTERS') and o.name = 'FK_QUESTION_QUESTIONS_QUESTION')
alter table QUESTIONS___QUESTION_COMMENTS___COMMENTERS
   drop constraint FK_QUESTION_QUESTIONS_QUESTION
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('QUESTIONS___QUESTION_COMMENTS___COMMENTERS') and o.name = 'FK_QUESTION_QUESTIONS_ACCOUNTS')
alter table QUESTIONS___QUESTION_COMMENTS___COMMENTERS
   drop constraint FK_QUESTION_QUESTIONS_ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('QUESTION_COMMENT_VOTE') and o.name = 'FK_QUESTION_QUESTION__ACCOUNTS2')
alter table QUESTION_COMMENT_VOTE
   drop constraint FK_QUESTION_QUESTION__ACCOUNTS2
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('QUESTION_IMAGES') and o.name = 'FK_QUESTION_QUESTIONS_QUESTION2')
alter table QUESTION_IMAGES
   drop constraint FK_QUESTION_QUESTIONS_QUESTION2
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('QUESTION_VOTE') and o.name = 'FK_QUESTION_QUESTION__QUESTION')
alter table QUESTION_VOTE
   drop constraint FK_QUESTION_QUESTION__QUESTION
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('QUESTION_VOTE') and o.name = 'FK_QUESTION_QUESTION__ACCOUNTS')
alter table QUESTION_VOTE
   drop constraint FK_QUESTION_QUESTION__ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SENDERS___RECEIVERS___PERSONAL_MESSAGES') and o.name = 'FK_SENDERS__SENDERS___ACCOUNTS2')
alter table SENDERS___RECEIVERS___PERSONAL_MESSAGES
   drop constraint FK_SENDERS__SENDERS___ACCOUNTS2
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SENDERS___RECEIVERS___PERSONAL_MESSAGES') and o.name = 'FK_SENDERS__SENDERS___ACCOUNTS')
alter table SENDERS___RECEIVERS___PERSONAL_MESSAGES
   drop constraint FK_SENDERS__SENDERS___ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SESSION_IDENTIFIERS') and o.name = 'FK_SESSION__HAVING_CO_ACCOUNTS')
alter table SESSION_IDENTIFIERS
   drop constraint FK_SESSION__HAVING_CO_ACCOUNTS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TOPICS') and o.name = 'FK_TOPICS_INDEX_INC_TOPIC_IN')
alter table TOPICS
   drop constraint FK_TOPICS_INDEX_INC_TOPIC_IN
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ACCOUNTS')
            and   type = 'U')
   drop table ACCOUNTS
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ANSWERS')
            and   name  = 'ANSWER_OF_QUESTION_FK'
            and   indid > 0
            and   indid < 255)
   drop index ANSWERS.ANSWER_OF_QUESTION_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ANSWERS')
            and   name  = 'WRITING_ANSWER_FK'
            and   indid > 0
            and   indid < 255)
   drop index ANSWERS.WRITING_ANSWER_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ANSWERS')
            and   type = 'U')
   drop table ANSWERS
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('FOLLOWING_QUESTION')
            and   name  = 'FOLLOWING_QUESTION2_FK'
            and   indid > 0
            and   indid < 255)
   drop index FOLLOWING_QUESTION.FOLLOWING_QUESTION2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('FOLLOWING_QUESTION')
            and   name  = 'FOLLOWING_QUESTION_FK'
            and   indid > 0
            and   indid < 255)
   drop index FOLLOWING_QUESTION.FOLLOWING_QUESTION_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('FOLLOWING_QUESTION')
            and   type = 'U')
   drop table FOLLOWING_QUESTION
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('FOLLOWING_TOPIC')
            and   name  = 'FOLLOWING_TOPIC2_FK'
            and   indid > 0
            and   indid < 255)
   drop index FOLLOWING_TOPIC.FOLLOWING_TOPIC2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('FOLLOWING_TOPIC')
            and   name  = 'FOLLOWING_TOPIC_FK'
            and   indid > 0
            and   indid < 255)
   drop index FOLLOWING_TOPIC.FOLLOWING_TOPIC_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('FOLLOWING_TOPIC')
            and   type = 'U')
   drop table FOLLOWING_TOPIC
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('QUESTIONS')
            and   name  = 'LAST_MODIFYING_QUESTION_FK'
            and   indid > 0
            and   indid < 255)
   drop index QUESTIONS.LAST_MODIFYING_QUESTION_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('QUESTIONS')
            and   name  = 'CREATING_QUESTION_FK'
            and   indid > 0
            and   indid < 255)
   drop index QUESTIONS.CREATING_QUESTION_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('QUESTIONS')
            and   name  = 'TOPIC_INCLUDING_QUESTION_FK'
            and   indid > 0
            and   indid < 255)
   drop index QUESTIONS.TOPIC_INCLUDING_QUESTION_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('QUESTIONS')
            and   type = 'U')
   drop table QUESTIONS
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SESSION_IDENTIFIERS')
            and   name  = 'HAVING_COOKIE_FK'
            and   indid > 0
            and   indid < 255)
   drop index SESSION_IDENTIFIERS.HAVING_COOKIE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SESSION_IDENTIFIERS')
            and   type = 'U')
   drop table SESSION_IDENTIFIERS
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('TOPICS')
            and   name  = 'INDEX_INCLUDING_TOPIC_FK'
            and   indid > 0
            and   indid < 255)
   drop index TOPICS.INDEX_INCLUDING_TOPIC_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TOPICS')
            and   type = 'U')
   drop table TOPICS
go

if exists(select 1 from systypes where name='LARGE_SIZE_TEXT')
   drop type LARGE_SIZE_TEXT
go

if exists(select 1 from systypes where name='SERIAL_NUMBER')
   drop type SERIAL_NUMBER
go

/*==============================================================*/
/* Domain: LARGE_SIZE_TEXT                                      */
/*==============================================================*/
create type LARGE_SIZE_TEXT
   from nvarchar(max)
go

/*==============================================================*/
/* Domain: SERIAL_NUMBER                                        */
/*==============================================================*/
create type SERIAL_NUMBER
   from NUMERIC not null
go

/*==============================================================*/
/* Table: ACCOUNTS                                              */
/*==============================================================*/
create table ACCOUNTS (
   ACCOUNT_ID           SERIAL_NUMBER        not null    identity,
   EMAIL_ADDRESS        nvarchar(511)        not null,
   USERNAME             nvarchar(511)        not null,
   HASHED_PASSWORD      varchar(511)         not null,
   DATE___TIME_CREATED  datetime             not null,
   constraint PK_ACCOUNTS primary key nonclustered (ACCOUNT_ID),
   CONSTRAINT USER_AK_EMAIL_ADDRESS UNIQUE (EMAIL_ADDRESS),
   CONSTRAINT USER_AK_USERNAME UNIQUE (USERNAME)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Accounts',
   'user', @CurrentUser, 'table', 'ACCOUNTS'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Account ID',
   'user', @CurrentUser, 'table', 'ACCOUNTS', 'column', 'ACCOUNT_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Email Address',
   'user', @CurrentUser, 'table', 'ACCOUNTS', 'column', 'EMAIL_ADDRESS'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Username',
   'user', @CurrentUser, 'table', 'ACCOUNTS', 'column', 'USERNAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Hashed Password',
   'user', @CurrentUser, 'table', 'ACCOUNTS', 'column', 'HASHED_PASSWORD'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Date & Time Created',
   'user', @CurrentUser, 'table', 'ACCOUNTS', 'column', 'DATE___TIME_CREATED'
go

/*==============================================================*/
/* Table: ANSWERS                                               */
/*==============================================================*/
create table ANSWERS (
   QUESTION_ID          SERIAL_NUMBER        not null,
   ANSWER_ID            SERIAL_NUMBER        not null    IDENTITY,
   ACCOUNT_ID           SERIAL_NUMBER        not null,
   ANSWER_TEXT          LARGE_SIZE_TEXT      not null,
   ANSWER_DATE___TIME   datetime             not null,
   ACCEPTED             bit                  not null,
   constraint PK_ANSWERS primary key nonclustered (QUESTION_ID, ANSWER_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Answers',
   'user', @CurrentUser, 'table', 'ANSWERS'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Question ID',
   'user', @CurrentUser, 'table', 'ANSWERS', 'column', 'QUESTION_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Answer ID',
   'user', @CurrentUser, 'table', 'ANSWERS', 'column', 'ANSWER_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Account ID',
   'user', @CurrentUser, 'table', 'ANSWERS', 'column', 'ACCOUNT_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Answer Text',
   'user', @CurrentUser, 'table', 'ANSWERS', 'column', 'ANSWER_TEXT'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Answer Date & Time',
   'user', @CurrentUser, 'table', 'ANSWERS', 'column', 'ANSWER_DATE___TIME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Accepted',
   'user', @CurrentUser, 'table', 'ANSWERS', 'column', 'ACCEPTED'
go

/*==============================================================*/
/* Index: WRITING_ANSWER_FK                                     */
/*==============================================================*/
create index WRITING_ANSWER_FK on ANSWERS (
ACCOUNT_ID ASC
)
go

/*==============================================================*/
/* Index: ANSWER_OF_QUESTION_FK                                 */
/*==============================================================*/
create index ANSWER_OF_QUESTION_FK on ANSWERS (
QUESTION_ID ASC
)
go

/*==============================================================*/
/* Table: FOLLOWING_QUESTION                                    */
/*==============================================================*/
create table FOLLOWING_QUESTION (
   ACCOUNT_ID           SERIAL_NUMBER        not null,
   QUESTION_ID          SERIAL_NUMBER        not null,
   constraint PK_FOLLOWING_QUESTION primary key (ACCOUNT_ID, QUESTION_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Following Question',
   'user', @CurrentUser, 'table', 'FOLLOWING_QUESTION'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Account ID',
   'user', @CurrentUser, 'table', 'FOLLOWING_QUESTION', 'column', 'ACCOUNT_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Question ID',
   'user', @CurrentUser, 'table', 'FOLLOWING_QUESTION', 'column', 'QUESTION_ID'
go

/*==============================================================*/
/* Index: FOLLOWING_QUESTION_FK                                 */
/*==============================================================*/
create index FOLLOWING_QUESTION_FK on FOLLOWING_QUESTION (
ACCOUNT_ID ASC
)
go

/*==============================================================*/
/* Index: FOLLOWING_QUESTION2_FK                                */
/*==============================================================*/
create index FOLLOWING_QUESTION2_FK on FOLLOWING_QUESTION (
QUESTION_ID ASC
)
go

/*==============================================================*/
/* Table: FOLLOWING_TOPIC                                       */
/*==============================================================*/
create table FOLLOWING_TOPIC (
   TOPIC_ID             SERIAL_NUMBER        not null,
   ACCOUNT_ID           SERIAL_NUMBER        not null,
   constraint PK_FOLLOWING_TOPIC primary key (TOPIC_ID, ACCOUNT_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Following Topic',
   'user', @CurrentUser, 'table', 'FOLLOWING_TOPIC'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Topic ID',
   'user', @CurrentUser, 'table', 'FOLLOWING_TOPIC', 'column', 'TOPIC_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Account ID',
   'user', @CurrentUser, 'table', 'FOLLOWING_TOPIC', 'column', 'ACCOUNT_ID'
go

/*==============================================================*/
/* Index: FOLLOWING_TOPIC_FK                                    */
/*==============================================================*/
create index FOLLOWING_TOPIC_FK on FOLLOWING_TOPIC (
TOPIC_ID ASC
)
go

/*==============================================================*/
/* Index: FOLLOWING_TOPIC2_FK                                   */
/*==============================================================*/
create index FOLLOWING_TOPIC2_FK on FOLLOWING_TOPIC (
ACCOUNT_ID ASC
)
go

/*==============================================================*/
/* Table: QUESTIONS                                             */
/*==============================================================*/
create table QUESTIONS (
   QUESTION_ID          SERIAL_NUMBER        not null    IDENTITY,
   ACCOUNT_ID           SERIAL_NUMBER        not null,
   TOPIC_ID             SERIAL_NUMBER        not null,
   QUESTION_TITLE       nvarchar(511)        not null,
   QUESTION_TEXT        LARGE_SIZE_TEXT      not null,
   QUESTION_DATE___TIME datetime             not null,
   constraint PK_QUESTIONS primary key nonclustered (QUESTION_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Questions',
   'user', @CurrentUser, 'table', 'QUESTIONS'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Question ID',
   'user', @CurrentUser, 'table', 'QUESTIONS', 'column', 'QUESTION_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Account ID',
   'user', @CurrentUser, 'table', 'QUESTIONS', 'column', 'ACCOUNT_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Topic ID',
   'user', @CurrentUser, 'table', 'QUESTIONS', 'column', 'TOPIC_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Question Title',
   'user', @CurrentUser, 'table', 'QUESTIONS', 'column', 'QUESTION_TITLE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Question Text',
   'user', @CurrentUser, 'table', 'QUESTIONS', 'column', 'QUESTION_TEXT'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Question Date & Time',
   'user', @CurrentUser, 'table', 'QUESTIONS', 'column', 'QUESTION_DATE___TIME'
go

/*==============================================================*/
/* Index: TOPIC_INCLUDING_QUESTION_FK                           */
/*==============================================================*/
create index TOPIC_INCLUDING_QUESTION_FK on QUESTIONS (
TOPIC_ID ASC
)
go

/*==============================================================*/
/* Index: CREATING_QUESTION_FK                                  */
/*==============================================================*/
create index CREATING_QUESTION_FK on QUESTIONS (
ACCOUNT_ID ASC
)
go

/*==============================================================*/
/* Table: SESSION_IDENTIFIERS                                   */
/*==============================================================*/
create table SESSION_IDENTIFIERS (
   ACCOUNT_ID           SERIAL_NUMBER        not null,
   SESSION_TOKEN        varchar(511)         not null,
   DATE___TIME_LOGGING_IN datetime             not null,
   constraint PK_SESSION_IDENTIFIERS primary key nonclustered (ACCOUNT_ID, SESSION_TOKEN)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Session Identifiers',
   'user', @CurrentUser, 'table', 'SESSION_IDENTIFIERS'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Account ID',
   'user', @CurrentUser, 'table', 'SESSION_IDENTIFIERS', 'column', 'ACCOUNT_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Session Token',
   'user', @CurrentUser, 'table', 'SESSION_IDENTIFIERS', 'column', 'SESSION_TOKEN'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Date & Time Logging In',
   'user', @CurrentUser, 'table', 'SESSION_IDENTIFIERS', 'column', 'DATE___TIME_LOGGING_IN'
go

/*==============================================================*/
/* Index: HAVING_COOKIE_FK                                      */
/*==============================================================*/
create index HAVING_COOKIE_FK on SESSION_IDENTIFIERS (
ACCOUNT_ID ASC
)
go

/*==============================================================*/
/* Table: TOPICS                                                */
/*==============================================================*/
create table TOPICS (
   TOPIC_ID             SERIAL_NUMBER        not null    ,
   TOPIC_TITLE          nvarchar(511)        not null,
   TOPIC_DESCRIPTION    LARGE_SIZE_TEXT      null,
   constraint PK_TOPICS primary key nonclustered (TOPIC_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Topics',
   'user', @CurrentUser, 'table', 'TOPICS'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Topic ID',
   'user', @CurrentUser, 'table', 'TOPICS', 'column', 'TOPIC_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Topic Title',
   'user', @CurrentUser, 'table', 'TOPICS', 'column', 'TOPIC_TITLE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Topic Description',
   'user', @CurrentUser, 'table', 'TOPICS', 'column', 'TOPIC_DESCRIPTION'
go

/*==============================================================*/
/* Index: INDEX_INCLUDING_TOPIC_FK                              */
/*==============================================================*/

alter table ANSWERS
   add constraint FK_ANSWERS_ANSWER_OF_QUESTION foreign key (QUESTION_ID)
      references QUESTIONS (QUESTION_ID)
go

alter table ANSWERS
   add constraint FK_ANSWERS_WRITING_A_ACCOUNTS foreign key (ACCOUNT_ID)
      references ACCOUNTS (ACCOUNT_ID)
go

alter table FOLLOWING_QUESTION
   add constraint FK_FOLLOWIN_FOLLOWING_ACCOUNTS foreign key (ACCOUNT_ID)
      references ACCOUNTS (ACCOUNT_ID)
go

alter table FOLLOWING_QUESTION
   add constraint FK_FOLLOWIN_FOLLOWING_QUESTION foreign key (QUESTION_ID)
      references QUESTIONS (QUESTION_ID)
go

alter table FOLLOWING_TOPIC
   add constraint FK_FOLLOWIN_FOLLOWING_TOPICS foreign key (TOPIC_ID)
      references TOPICS (TOPIC_ID)
go

alter table FOLLOWING_TOPIC
   add constraint FK_FOLLOWIN_FOLLOWING_ACCOUNTS4 foreign key (ACCOUNT_ID)
      references ACCOUNTS (ACCOUNT_ID)
go

alter table QUESTIONS
   add constraint FK_QUESTION_CREATING__ACCOUNTS foreign key (ACCOUNT_ID)
      references ACCOUNTS (ACCOUNT_ID)
go

alter table QUESTIONS
   add constraint FK_QUESTION_TOPIC_INC_TOPICS foreign key (TOPIC_ID)
      references TOPICS (TOPIC_ID)
go

alter table SESSION_IDENTIFIERS
   add constraint FK_SESSION__HAVING_CO_ACCOUNTS foreign key (ACCOUNT_ID)
      references ACCOUNTS (ACCOUNT_ID)
go

-------------------------------------split-rule-----------------------

-- Create a new stored procedure called 'Accept_Answer' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Accept_Answer'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Accept_Answer
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Accept_Answer
    @question_id SERIAL_NUMBER,
    @answer_id SERIAL_NUMBER,
    @question_creater_id SERIAL_NUMBER
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;

    BEGIN TRANSACTION
        IF EXISTS (
            -- Select rows from a Table or View '[QUESTIONS]' in schema '[dbo]'
            SELECT [QUESTION_ID], [ACCOUNT_ID] FROM [dbo].[QUESTIONS]
            WHERE [QUESTION_ID] = @question_id AND [ACCOUNT_ID] = @question_creater_id
        ) AND EXISTS (
            -- Select rows from a Table or View '[ANSWERS]' in schema '[dbo]'
            SELECT [QUESTION_ID], [ANSWER_ID] FROM [dbo].[ANSWERS]
            WHERE [QUESTION_ID] = @question_id AND [ANSWER_ID] = @answer_id
        )
        BEGIN
                -- Update rows in table '[ANSWERS]' in schema '[dbo]'
                UPDATE [dbo].[ANSWERS]
                SET
                    [ACCEPTED] = 0
                    -- Add more columns and values here
                WHERE [QUESTION_ID] = @question_id;

                UPDATE [dbo].[ANSWERS]
                SET
                    [ACCEPTED] = 1
                    -- Add more columns and values here
                WHERE [ANSWER_ID] = @answer_id;

                SELECT 1
        END;
        ELSE
            SELECT 1 FROM [ANSWERS] WHERE 1 = 0
    COMMIT TRANSACTION;
GO

DROP VIEW IF EXISTS NUMBER_OF_ANSWERS_ACCEPTED;
GO
CREATE VIEW NUMBER_OF_ANSWERS_ACCEPTED
AS
    SELECT [ACCOUNT_ID], SUM(CAST (ACCEPTED AS INT)) AS [NUMBER_ACCEPTED] FROM [ANSWERS]
    GROUP BY ACCOUNT_ID;
GO

-- Create a new stored procedure called 'Get_Number_of_Answers_Accepted' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Get_Number_of_Answers_Accepted'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Get_Number_of_Answers_Accepted
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Get_Number_of_Answers_Accepted
    @account_id SERIAL_NUMBER
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;

    BEGIN TRANSACTION
        IF @account_id IN (SELECT [ACCOUNT_ID] FROM [ACCOUNTS])
            SELECT [NUMBER_ACCEPTED] FROM [NUMBER_OF_ANSWERS_ACCEPTED]
            WHERE [ACCOUNT_ID] = @account_id;
    COMMIT TRANSACTION;
GO

-- Create a new stored procedure called 'Create_Account' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Create_Account'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Create_Account
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Create_Account
    @email_address NVARCHAR(511),
    @username NVARCHAR(511),
    @hashed_password VARCHAR(511)
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;
    
    BEGIN TRANSACTION
        IF NOT EXISTS (
            SELECT * FROM [ACCOUNTS]
            WHERE [EMAIL_ADDRESS] = @email_address
                OR [USERNAME] = @username
        )
        BEGIN
            -- Insert rows into table 'ACCOUNTS' in schema '[dbo]'
            INSERT INTO [dbo].[ACCOUNTS]
            ( -- Columns to insert data into
            [EMAIL_ADDRESS], [USERNAME], [HASHED_PASSWORD], [DATE___TIME_CREATED]
            )
            VALUES
            ( -- First row: values for the columns in the list above
            @email_address, @username, @hashed_password, CURRENT_TIMESTAMP
            );
            COMMIT TRANSACTION;
            RETURN CAST (1 AS BIT);
        END

        ELSE
        BEGIN
            COMMIT TRANSACTION;
            RETURN CAST (0 AS BIT);
        END
GO

-- Create a new stored procedure called 'Create_Question' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Create_Question'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Create_Question
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Create_Question
    @account_id SERIAL_NUMBER,
    @topic_id SERIAL_NUMBER,
    @question_title NVARCHAR(511),
    @question_text LARGE_SIZE_TEXT,
    @timestamp DATETIME
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;
    
    BEGIN TRANSACTION
        -- Insert rows into table 'Questions' in schema '[dbo]'
        INSERT INTO [dbo].[Questions]
        ( -- Columns to insert data into
        [ACCOUNT_ID], [TOPIC_ID], [QUESTION_TITLE], [QUESTION_TEXT], [QUESTION_DATE___TIME]
        )
        VALUES
        ( -- First row: values for the columns in the list above
        @account_id, @topic_id, @question_title, @question_text, @timestamp
        );
    COMMIT TRANSACTION;
GO

-- Create a new stored procedure called 'Create_Topic' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Create_Topic'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Create_Topic
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Create_Topic
    @topic_title NVARCHAR(511),
    @topic_description LARGE_SIZE_TEXT
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;
    
    BEGIN TRANSACTION
        -- Insert rows into table 'TOPICS' in schema '[dbo]'
        INSERT INTO [dbo].[TOPICS]
        ( -- Columns to insert data into
        [TOPIC_TITLE], [TOPIC_DESCRIPTION]
        )
        VALUES
        ( -- First row: values for the columns in the list above
        @topic_title, @topic_description
        );
    COMMIT TRANSACTION;
GO

-- Create a new stored procedure called 'Follow_Topic' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Follow_Topic'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Follow_Topic
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Follow_Topic
    @topic_id /*parameter name*/ SERIAL_NUMBER,
    @account_id /*parameter name*/ SERIAL_NUMBER,
    @does_follow bit
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;

    -- body of the stored procedure
    BEGIN TRANSACTION
        DECLARE @has_followed bit = IIF (
            EXISTS (
                SELECT [TOPIC_ID], [ACCOUNT_ID] FROM [FOLLOWING_TOPIC]
                WHERE [TOPIC_ID] = @topic_id AND [ACCOUNT_ID] = @account_id
            ),
            1, 0
        );
        IF @does_follow = 1 AND @has_followed = 0
            INSERT INTO [FOLLOWING_TOPIC] ([TOPIC_ID], [ACCOUNT_ID])
            VALUES (@topic_id, @account_id);
        IF @does_follow = 0 AND @has_followed = 1
            -- Delete rows from table '[FOLLOWING_TOPIC]' in schema '[dbo]'
            DELETE FROM [dbo].[FOLLOWING_TOPIC]
            WHERE [TOPIC_ID] = @topic_id AND [ACCOUNT_ID] = @account_id;
    COMMIT TRANSACTION;
GO

-- Create a new stored procedure called 'Follow_Question' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Follow_Question'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Follow_Question
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Follow_Question
    @question_id /*parameter name*/ SERIAL_NUMBER,
    @account_id /*parameter name*/ SERIAL_NUMBER,
    @does_follow bit
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;

    -- body of the stored procedure
    BEGIN TRANSACTION
        DECLARE @has_followed bit = IIF (
            EXISTS (
                SELECT [QUESTION_ID], [ACCOUNT_ID] FROM [FOLLOWING_QUESTION]
                WHERE [QUESTION_ID] = @question_id AND [ACCOUNT_ID] = @account_id
            ),
            1, 0
        );
        IF @does_follow = 1 AND @has_followed = 0
            INSERT INTO [FOLLOWING_QUESTION] ([QUESTION_ID], [ACCOUNT_ID])
            VALUES (@question_id, @account_id);
        IF @does_follow = 0 AND @has_followed = 1
            -- Delete rows from table '[FOLLOWING_TOPIC]' in schema '[dbo]'
            DELETE FROM [dbo].[FOLLOWING_QUESTION]
            WHERE [QUESTION_ID] = @question_id AND [ACCOUNT_ID] = @account_id;
    COMMIT TRANSACTION;
GO

-- Create a new stored procedure called 'Get_Topics_Followed' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Get_Topics_Followed'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Get_Topics_Followed
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Get_Topics_Followed
    @account_id SERIAL_NUMBER
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON
    -- body of the stored procedure
    SELECT t2.[TOPIC_ID], t2.[TOPIC_TITLE], t2.[TOPIC_DESCRIPTION]
    FROM [FOLLOWING_TOPIC] AS t1 JOIN [TOPICS] AS t2
    ON t1.[TOPIC_ID] = t2.[TOPIC_ID]
    WHERE t1.[ACCOUNT_ID] = @account_id;
GO

-- Create a new stored procedure called 'Get_Questions_Followed' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Get_Questions_Followed'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Get_Questions_Followed
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Get_Questions_Followed
    @account_id SERIAL_NUMBER
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON
    -- body of the stored procedure
    SELECT [QUESTION_ID] FROM [FOLLOWING_QUESTION]
    WHERE [ACCOUNT_ID] = @account_id;
GO

-- Create a new stored procedure called 'Identification_Authentication_Authorization' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
    SELECT *
        FROM INFORMATION_SCHEMA.ROUTINES
    WHERE SPECIFIC_SCHEMA = N'dbo'
        AND SPECIFIC_NAME = N'Identification_Authentication_Authorization'
        AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Identification_Authentication_Authorization
GO

-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Identification_Authentication_Authorization
    @username /*parameter name*/ NVARCHAR(511), /*datatype_for_param1*/ /*= 0*/ /*default_value_for_param1*/
    @hashed_password /*parameter name*/ NVARCHAR(511), /*datatype_for_param1*/ /*= 0*/ /*default_value_for_param2*/
    @session_token VARCHAR(511)
-- add more stored procedure parameters here
AS
    -- body of the stored procedure
    SET NOCOUNT ON;

    -- Select rows from a Table or View '[ACCOUNTS]' in schema '[dbo]'
    BEGIN TRANSACTION
    IF EXISTS (
        SELECT USERNAME, HASHED_PASSWORD
            FROM [dbo].[ACCOUNTS]
        WHERE @username = USERNAME
            AND @hashed_password = HASHED_PASSWORD/* add search conditions here */
    )
    BEGIN
        DECLARE @account_id SERIAL_NUMBER = (-- Select rows from a Table or View '[ACCOUNTS]' in schema '[dbo]'
                SELECT [ACCOUNT_ID] FROM [dbo].[ACCOUNTS]
                WHERE [USERNAME] = @username/* add search conditions here */
            );
        -- Update rows in table '[SESSION_IDENTIFIERS]' in schema '[dbo]'
        IF EXISTS (
            SELECT [ACCOUNT_ID] FROM [SESSION_IDENTIFIERS]
            WHERE ACCOUNT_ID = @account_id
        )
            UPDATE [dbo].[SESSION_IDENTIFIERS]
            SET
                [SESSION_TOKEN] = @session_token,
                [DATE___TIME_LOGGING_IN] = CURRENT_TIMESTAMP
                -- Add more columns and values here
            WHERE [ACCOUNT_ID] = @account_id; /* add search conditions here */
        ELSE
            -- Insert rows into table 'SESSION_IDENTIFIERS' in schema '[dbo]'
            INSERT INTO [dbo].[SESSION_IDENTIFIERS]
            ( -- Columns to insert data into
             [ACCOUNT_ID], [SESSION_TOKEN], [DATE___TIME_LOGGING_IN]
            )
            VALUES
            ( -- First row: values for the columns in the list above
             @account_id, @session_token, CURRENT_TIMESTAMP
            );
        COMMIT TRANSACTION;
        RETURN CAST (1 AS BIT);
    END;

    ELSE
    BEGIN
        COMMIT TRANSACTION;
        RETURN CAST (0 AS BIT);
    END
GO

DROP TYPE IF EXISTS SEARCH_QUERY;
GO
CREATE TYPE SEARCH_QUERY
AS TABLE (
    KEYWORD NVARCHAR(MAX) NOT NULL,
    INCLUDING BIT NOT NULL
);
GO

-- Create a new stored procedure called 'Search_Question' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Search_Question'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Search_Question
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Search_Question
    @search_query SEARCH_QUERY READONLY
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;
    BEGIN TRANSACTION
    BEGIN
        INSERT INTO [tttt_user]
        SELECT [QUESTION_ID] FROM [QUESTIONS]
        WHERE NOT EXISTS (
            SELECT * FROM @search_query
            WHERE [INCLUDING] = 0
                AND ([QUESTIONS].[QUESTION_TITLE] LIKE ('%' + [KEYWORD] + '%')
                OR [QUESTIONS].[QUESTION_TEXT] LIKE ('%' + [KEYWORD] + '%'))
            UNION
            SELECT * FROM @search_query
            WHERE [INCLUDING] = 1
                AND ([QUESTIONS].[QUESTION_TITLE] NOT LIKE ('%' + [KEYWORD] + '%')
                AND [QUESTIONS].[QUESTION_TEXT] NOT LIKE ('%' + [KEYWORD] + '%'))
        )
    END;
    COMMIT TRANSACTION;
GO

-- Create a new stored procedure called 'Search_Topic' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Search_Topic'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Search_Topic
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Search_Topic
    @search_query SEARCH_QUERY READONLY
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;
    BEGIN TRANSACTION
    BEGIN
        INSERT INTO [ssss_user]
        SELECT [TOPIC_TITLE], [TOPIC_DESCRIPTION] FROM [TOPICS]
        WHERE NOT EXISTS (
            SELECT * FROM @search_query
            WHERE [INCLUDING] = 0
                AND ([TOPICS].[TOPIC_TITLE] LIKE ('%' + [KEYWORD] + '%')
                OR [TOPICS].[TOPIC_DESCRIPTION] LIKE ('%' + [KEYWORD] + '%'))
            UNION
            SELECT * FROM @search_query
            WHERE [INCLUDING] = 1
                AND ([TOPICS].[TOPIC_TITLE] NOT LIKE ('%' + [KEYWORD] + '%')
                AND [TOPICS].[TOPIC_DESCRIPTION] NOT LIKE ('%' + [KEYWORD] + '%'))
        )
    END;
    COMMIT TRANSACTION;
GO

-- Create a new stored procedure called 'Update_Password' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Update_Password'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Update_Password
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Update_Password
    @account_id SERIAL_NUMBER,
    @old_hashed_password VARCHAR(511),
    @new_hashed_password VARCHAR(511)
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;

    BEGIN TRANSACTION
        IF EXISTS (
            -- Select rows from a Table or View '[ACCOUNTS]' in schema '[dbo]'
            SELECT [ACCOUNT_ID], [HASHED_PASSWORD] FROM [dbo].[ACCOUNTS]
            WHERE [ACCOUNT_ID] = @account_id AND [HASHED_PASSWORD] = @old_hashed_password
        )
            -- Update rows in table '[ACCOUNTS]' in schema '[dbo]'
            UPDATE [dbo].[ACCOUNTS]
            SET
                [HASHED_PASSWORD] = @new_hashed_password
            WHERE [ACCOUNT_ID] = @account_id;
    COMMIT TRANSACTION;
GO

-- Create a new stored procedure called 'Write_Answer' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Write_Answer'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Write_Answer
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.Write_Answer
    @question_id SERIAL_NUMBER,
    @account_id /*parameter name*/ SERIAL_NUMBER,
    @answer_text /*parameter name*/ LARGE_SIZE_TEXT
-- add more stored procedure parameters here
AS
    SET NOCOUNT ON;

    -- body of the stored procedure
    BEGIN TRANSACTION
    IF NOT EXISTS (
        SELECT [ACCOUNT_ID] FROM [ANSWERS]
        WHERE [ACCOUNT_ID] = @account_id AND
            [QUESTION_ID] = @question_id
    ) AND EXISTS (
        SELECT [QUESTION_ID] FROM [QUESTIONS]
        WHERE QUESTION_ID = @question_id
    )
    BEGIN
        -- Insert rows into table 'ANSWERS' in schema '[dbo]'
        INSERT INTO [dbo].[ANSWERS]
        ( -- Columns to insert data into
         [QUESTION_ID], [ACCOUNT_ID], [ANSWER_TEXT], [ANSWER_DATE___TIME], [ACCEPTED]
        )
        VALUES
        ( -- First row: values for the columns in the list above
         @question_id, @account_id, @answer_text, CURRENT_TIMESTAMP, 0
        );
        COMMIT TRANSACTION;
    END;
    ELSE IF EXISTS (
        SELECT [QUESTION_ID] FROM [QUESTIONS]
        WHERE QUESTION_ID = @question_id
    )
    BEGIN
        UPDATE [dbo].[ANSWERS]
        SET
            [ANSWER_TEXT] = @answer_text
        WHERE [QUESTION_ID] = @question_id AND [ACCOUNT_ID] = @account_id
        COMMIT TRANSACTION;
    END;
    ELSE
        COMMIT TRANSACTION;
GO