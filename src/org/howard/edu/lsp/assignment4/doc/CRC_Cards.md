CRC Card

Class: AircraftTransponderPacket
Responsibilities:
Encapsulate raw high-density broadcast data
Provide access to encoded aircraft identifier, type, and flight data
Represent standardized transponder packet structure

Collaborators (if any):
ATCSignalReceiver
TransponderPacketDecoder

Assumptions (if any):
Each packet contains aircraft identifier, type, altitude, speed, heading, and position.

CRC Card

Class: ATCSignalReceiver
Responsibilities:
Receive broadcast signals from aircraft transponders
Instantiate AircraftTransponderPacket objects
Forward packets to TransponderPacketDecoder
Handle continuous signal reception

Collaborators (if any):
AircraftTransponderPacket
TransponderPacketDecoder

Assumptions (if any):
Aircraft within range broadcast continuously.

CRC Card

Class: TransponderPacketDecoder
Responsibilities:
Decode high-density packet format
Extract aircraft identifier, type, and flight data
Create new TrackedAircraft when necessary
Update existing TrackedAircraft records
Persist aircraft data in AircraftRepository

Collaborators (if any):
AircraftTransponderPacket
TrackedAircraft
AircraftRepository

Assumptions (if any):
Decoding rules are predefined and consistent.

CRC Card

Class: TrackedAircraft
Responsibilities:
Maintain aircraft identifier and type
Maintain current flight data (altitude, speed, heading, position)
Update flight state when new data arrives
Provide current flight state upon request

Collaborators (if any):
AircraftRepository

Assumptions (if any):
Each aircraft has a unique identifier.

CRC Card

Class: AircraftRepository
Responsibilities:
Store and manage TrackedAircraft objects
Add new aircraft records
Update existing aircraft records
Retrieve aircraft by identifier
Provide aircraft data to requesting services

Collaborators (if any):
TrackedAircraft
RadarDisplaySystem
AircraftConflictDetectionService
AirTrafficControllerConsole

Assumptions (if any):
Repository storage is in-memory for this design.

CRC Card

Class: RadarDisplaySystem
Responsibilities:
Retrieve aircraft data from AircraftRepository
Construct graphical representation of aircraft positions
Render aircraft information on screen
Refresh display when triggered

Collaborators (if any):
AircraftRepository
DisplayUpdateScheduler

Assumptions (if any):
All tracked aircraft are displayed.

CRC Card

Class: DisplayUpdateScheduler
Responsibilities:
Trigger radar display refresh every 10 seconds
Notify RadarDisplaySystem to update

Collaborators (if any):
RadarDisplaySystem

Assumptions (if any):
Uses timer-based scheduling.

CRC Card

Class: AircraftConflictDetectionService
Responsibilities:
Retrieve aircraft data from AircraftRepository
Analyze flight states for unsafe proximity
Detect abnormal altitude or speed conditions
Generate conflict notifications

Collaborators (if any):
AircraftRepository
ControllerAlertService

Assumptions (if any):
Conflict detection uses rule-based thresholds.

CRC Card

Class: ControllerAlertService
Responsibilities:
Receive conflict notifications
Format alert messages
Deliver alerts to AirTrafficControllerConsole

Collaborators (if any):
AircraftConflictDetectionService
AirTrafficControllerConsole

Assumptions (if any):
Alerts must be displayed immediately.

CRC Card

Class: AirTrafficControllerConsole
Responsibilities:
Display radar interface to controller
Display alert notifications
Accept controller aircraft queries
Request aircraft data from AircraftRepository
Present selected aircraft details

Collaborators (if any):
RadarDisplaySystem
AircraftRepository
ControllerAlertService

Assumptions (if any):
Controller interaction occurs through a graphical interface.
