# hr-fullstack-test

## Full Stack Technical Test: Scrum Poker
 
 
Scenario: Imagine a team of developers in the conference room doing their sprint planning meeting.
 
### Functional Requirements
 
-	User can login with his e-mail address.
-	User can choose a story from a list of predefined items (ie, implement chat feature, add social login, etc) which would become the “Active Story”. At any moment there is only one “Active Story" in the system.
-	Users can assign a story point estimate to the current story from the following selection:
    * 1
    * 2
    * 3
    * 5
    * 8
    * 13
    * 21
    * no_idea,
    * resign.
-	After all logged-in users have given story points to the current story, all the story points given will be revealed (e.g. A gave 2 points, B gave 3 points etc.) 
-	After that any user can choose a new “Active Story”.
 
### Non Functional Requirements
 
-	Java Spring Boot, Database Optional
-	ReactJS based frontend, additional frontend technologies/frameworks optional
-	A README good enough that tells us how to start your app or deploy on any cloud.
-	BONUS: Use a push mechanism to notify frontend of changes (instead of polling http requests)
