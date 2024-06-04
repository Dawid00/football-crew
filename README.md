I used to play football in amateur league, so I will show you some local rules regarding every league match.
As a coach I need to be sure who can play in next league match. 
Before I set the team I need to check exclusions.
If someone plays with exclusion the team will lose 0-3.
It has no matter if it was aware or unaware.
What is more a footballer might be injured and can not be able to play.

LEAGUE MATCH RULES:
minimum 2 players with young status(age <= 21)
player can not be excluded
minimum 7 players on the pitch

EXCLUSION RULES:
player is excluded for 1 match after 4 and 8 yellow cards
player is excluded for 2 match after 12 yellow cards
player is excluded for 1 match after direct red card
player can be excluded for any matches after decision

CASES:
Player got 4th yellow card -> Player is excluded for one next league match
Player got 8th yellow card -> Player is excluded for one next league match
Player got 12th yellow card -> Player is excluded for two next league matches
Player got direct red card -> Player is excluded for minimum 1 next league matches, it requires external decision
Player is injured
There must be at least 2 youth players ( Youth = age < 22)

Aggregate candidates:
team
player
match
league

DDD STRATEGIC -> Event Storming(Big Picture: strategy, Process-Level: Bounded Context, Design-Level: Aggregates) ->
Domains and subdomains
We are looking for events.(Verbs) Past simple
Commands Vs Events VS Views
-----------------------TEAM CATALOG
player add
player added
player remove
player removed
match ended -> CurrentTeam CREATED
update cards
CurrentTeam SUBMITTED
CurrentTeam NotAccepted

if(card exclusion == 0 ) remove card exclusion
player included

----------------------
CurrentTeam - AGGREGATE

List<Player> players

Player - Entity
VO - Card

League Match team submit policies:

POLICIES:

yellow card given
red card given
injured, excluded, included

Domain

Aggregate - Current Team
Current team is responsible for ensuring proper choice of footballers for further match.
It is important to submit team before next match.
It protects from walk-over due to excluded participant.
Behaviors:
submit footballers for the next match
update footballers after the match/assign minute
[//]: # ( - there is maximum 5 yellow cards allowed without additional fee)
Policies:
exclusionPolicy: any player must not be excluded
youthPolicy: minimum 2 players on the pitch with young status(age <= 21)
countPolicy: minimum 7 players on the pitch, maximum 11 players on the pitch
subsPolicy: maximum 7 substitutes on the pitch

Entity - Footballer
Behaviors:
take yellow card
take red card
get injury
update exclusion after match
Policies:
Penalty Policy - RedCardPolicy, YellowCardPolicy
VO:
Card - includes type(YELLOW/RED) and quantity
Exclusion - The footballer with at least one exclusion must not play the match. It may cause by injury, cards, absence,
or without any specific reason.
