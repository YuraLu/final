create schema if not exists tutorsystem collate utf8_general_ci;
USE `tutorsystem` ;

create table if not exists answers
(
    id int auto_increment
        primary key,
    text varchar(45) not null,
    correct tinyint default 0 not null
);

create table if not exists questions
(
    id int auto_increment
        primary key,
    text varchar(100) not null
);

create table if not exists answergroups
(
    id int auto_increment
        primary key,
    answerId int not null,
    questionId int not null,
    constraint fk_answerGroup_answer1
        foreign key (answerId) references answers (id)
            on delete cascade,
    constraint fk_answerGroup_questions1
        foreign key (questionId) references questions (id)
            on delete cascade
);

create index fk_answerGroup_answer1_idx
    on answergroups (answerId);

create index fk_answerGroup_questions1_idx
    on answergroups (questionId);

create table if not exists roles
(
    id int auto_increment
        primary key,
    name varchar(45) not null,
    constraint name_UNIQUE
        unique (name)
);

create table if not exists subjects
(
    id int auto_increment
        primary key,
    name varchar(45) not null,
    constraint name_UNIQUE
        unique (name)
);

create table if not exists users
(
    id int auto_increment
        primary key,
    name varchar(45) not null,
    email varchar(45) not null,
    login varchar(45) not null,
    password varchar(45) not null,
    roleId int not null,
    banned tinyint default 0 null,
    constraint email_UNIQUE
        unique (email),
    constraint login_UNIQUE
        unique (login),
    constraint fk_users_roles1
        foreign key (roleId) references roles (id)
);

create table if not exists tests
(
    id int auto_increment
        primary key,
    title varchar(45) not null,
    description varchar(110) not null,
    subjectId int null,
    authorId int not null,
    constraint title_UNIQUE
        unique (title),
    constraint fk_tests_subjects
        foreign key (subjectId) references subjects (id),
    constraint fk_tests_users
        foreign key (authorId) references users (id)
);

create table if not exists assignments
(
    id int auto_increment
        primary key,
    testId int not null,
    studentId int not null,
    constraint fk_assignments_tests1
        foreign key (testId) references tests (id),
    constraint fk_assignments_users1
        foreign key (studentId) references users (id)
);

create index fk_assignments_tests1_idx
    on assignments (testId);

create index fk_assignments_users1_idx
    on assignments (studentId);

create table if not exists replies
(
    id int auto_increment
        primary key,
    assigmentId int not null,
    answerId int not null,
    questionId int not null,
    constraint fk_replies_answers1
        foreign key (answerId) references answers (id),
    constraint fk_replies_assignments1
        foreign key (assigmentId) references assignments (id),
    constraint fk_replies_questions1
        foreign key (questionId) references questions (id)
);

create index fk_replies_answers1_idx
    on replies (answerId);

create index fk_replies_assignments1_idx
    on replies (assignmentId);

create index fk_replies_questions1_idx
    on replies (questionId);

create table if not exists testquestions
(
    id int auto_increment
        primary key,
    testId int not null,
    questionId int not null,
    constraint fk_testQuestionsBank_questions1
        foreign key (questionId) references questions (id),
    constraint fk_testQuestionsBank_tests1
        foreign key (testId) references tests (id)
);

create index fk_testQuestionsBank_questions1_idx
    on testquestions (questionId);

create index fk_testQuestionsBank_tests1_idx
    on testquestions (testId);

create index fk_tests_subjects_idx
    on tests (subjectId);

create index fk_tests_users_idx
    on tests (authorId);

create index fk_users_roles1_idx
    on users (roleId);