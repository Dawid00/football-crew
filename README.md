# Work in progress!
## Tech stack
### Java, Spring Boot, Spring Data JPA, Spring Data Jdbc, H2Database
## About project
This repo is to show my approach to coding, trying to DDD, Clean Code, Clean Archtecture.
I am junior software developer and a lot of work ahead of me.
I used to play football in amateur league, so I will show you some local rules regarding every league match.
As a coach I need to be sure who can play in next league match.
Before I set the team I need to check exclusions.
If someone plays with exclusion the team will lose 0-3.
It has no matter if it was aware or unaware.
What is more a footballer might be injured and not be able to play.

### League match rules:
- minimum 2 footballer with youth status(age <= 21)
- minimum 7 footballers on the pitch
- a footballer must not be excluded

### Exclusion rules:
- a footballer is excluded for 1 match after 4 and 8 yellow cards
- a footballer is excluded for 2 match after 12 yellow cards
- a footballer is excluded for 1 match after direct red card
- a footballer can be excluded for any matches after decision
- a footballer is injured

----------------------
## Domain
----------------------
### Looking for events(Verbs, Past simple)
* yellow card given,
* red card given,
* footballer injured, excluded, included
* match ended
* cards updated
* team for next match created

### Current team - AGGREGATE
**Behaviors:**
Update footballers exclusions after the match by assigning minute.

### Team for specific match - AGGREGATE
Team for specific match is responsible for ensuring proper choice of footballers for further match.
It is important to submit team before next match.
It protects from walk-over due to excluded participant.
Behaviors:
submit footballers for the next match
Policies:
exclusionPolicy: any player must not be excluded
youthPolicy: minimum 2 players on the pitch with young status(age <= 21)
countPolicy: minimum 7 players on the pitch, maximum 11 players on the pitch
subsPolicy: maximum 7 substitutes on the pitch
### Policies:
### Penalty Policy:
- RedCardPolicy
- YellowCardPolicy

### Fee policy:
* Maximum 5 yellow cards allowed without additional fee in a single match.
### Entity - Footballer
### Behaviors:
* take yellow card
* take red card
* get injury
* resolve exclusions after match held
### Value Object - Card (YELLOW/RED, quantity)
### Entity - Exclusion
It may be caused by injury, cards, absence, or without any specific reason.


# TODO:
* persistence
* api
* packages/modules
* events handling/producing