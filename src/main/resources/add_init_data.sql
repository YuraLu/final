
INSERT INTO `tutor_system`.`roles` (`name`) VALUES ('ADMIN');
INSERT INTO `tutor_system`.`roles` (`name`) VALUES ('STUDENT');
INSERT INTO `tutor_system`.`roles` (`name`) VALUES ('TUTOR');
INSERT INTO `tutor_system`.`roles` (`name`) VALUES ('GUEST');

INSERT INTO `tutor_system`.`questions` (`text`) VALUES ('What is the color of the Sun?');

INSERT INTO `tutor_system`.`questions` (`text`) VALUES ('Boiling water temperature?');

INSERT INTO `tutor_system`.`questions` (`text`) VALUES ('Normal body temperature?');

INSERT INTO `tutor_system`.`questions` (`text`) VALUES ('How much seasons in the year?');


INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('Yellow', '1');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('Red', '0');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('Orange', '1');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('White', '0');

INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('98', '0');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('99', '0');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('101', '0');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('100', '1');

INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('34', '0');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('33', '0');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('36.6', '1');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('37', '1');

INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('1', '0');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('5', '0');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('4', '1');
INSERT INTO `tutor_system`.`answers` (`text`, `correct`) VALUES ('8', '0');


INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('1', '1');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('2', '1');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('3', '1');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('4', '1');

INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('5', '2');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('6', '2');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('7', '2');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('8', '2');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('9', '3');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('10', '3');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('11', '3');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('12', '3');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('13', '4');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('14', '4');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('15', '4');
INSERT INTO `tutor_system`.`answergroups` (`answerId`, `questionId`) VALUES ('16', '4');


INSERT INTO `tutor_system`.`subjects` (`name`) VALUES ('Common');

INSERT INTO `tutor_system`.`tests` (`title`, `description`, `subjectId`, `authorId`) VALUES ('Common Test', 'Common questions about life', '1', '3');

INSERT INTO `tutor_system`.`testquestions` (`testId`, `questionId`) VALUES ('1', '1');

INSERT INTO `tutor_system`.`testquestions` (`questionId`, `testId`) VALUES ('2', '1');
INSERT INTO `tutor_system`.`testquestions` (`questionId`, `testId`) VALUES ('3', '1');
INSERT INTO `tutor_system`.`testquestions` (`questionId`, `testId`) VALUES ('4', '1');
