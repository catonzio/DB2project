When the user submits the questionnaire one or more trigger compute the gamification points to assign to the user for the specific questionnaire, according to the following rule:
1. One point is assigned for every answered question of section 1 (remember that the number of questions can vary in different questionnaires).
2. Two points are assigned for every answered optional question of section 2.


CREATE TRIGGER AssignPoints
AFTER INSERT ON USERANSWER
FOR EACH ROW
BEGIN

	DECLARE p1,p2 INTEGER;

	SELECT NUMMARKETINGQUESTIONS INTO p1
	FROM QUESTIONNAIRE
	WHERE ID = new.RELATEDQUESTIONNAIRE_ID;

	SELECT count(*) INTO p2
	FROM UserAnswer_ANSWERS JOIN USERANSWER ON 
	UserAnswer_ANSWERS.UserAnswer_ID = USERANSWER.ID
	WHERE USERANSWER.ID = new.ID AND 
		( SELECT * 
			FROM QUESTION
			WHERE UserAnswer_ANSWERS.answers_KEY = ID AND TYPE = 'FIXED');


	UPDATE USERANSWER
	SET POINTSEARNED = POINTSEARNED + p1 + p2
	WHERE ID = new.ID;
END




CREATE TRIGGER AssignPoints
AFTER INSERT ON UserAnswer_ANSWERS
FOR EACH ROW
BEGIN

	DECLARE p1,p2 INTEGER;

	SELECT count(*) INTO p1
	FROM QUESTION
	WHERE ID = new.answers_KEY TYPE = 'FIXED';

	SELECT NUMMARKETINGQUESTIONS INTO p2
	FROM QUESTIONNAIRE
	WHERE ID = (SELECT RELATEDQUESTIONNAIRE_ID
			FROM USERANSWER
			WHERE ID = new.UserAnswer_ID);

	UPDATE USERANSWER
	SET POINTSEARNED = POINTSEARNED + 2*p1 + p2
	WHERE ID = new.ID;
END