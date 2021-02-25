When the user submits the questionnaire one or more trigger compute the gamification points to assign to the user for the specific questionnaire, according to the following rule:
1. One point is assigned for every answered question of section 1 (remember that the number of questions can vary in different questionnaires).
2. Two points are assigned for every answered optional question of section 2.



CREATE TRIGGER AssignPoints
AFTER INSERT ON UserAnswer_ANSWERS
FOR EACH ROW
BEGIN
	IF( EXISTS (SELECT *
		FROM QUESTION
		WHERE new.answers_KEY = ID AND TYPE = 'FIXED'))
	THEN	
		UPDATE USERANSWER
		SET POINTSEARNED = POINTSEARNED + 2
		WHERE ID = new.UserAnswer_ID;
	ELSE 
		UPDATE USERANSWER
		SET POINTSEARNED = POINTSEARNED + 1
		WHERE ID = new.UserAnswer_ID;
	END IF;
END#




