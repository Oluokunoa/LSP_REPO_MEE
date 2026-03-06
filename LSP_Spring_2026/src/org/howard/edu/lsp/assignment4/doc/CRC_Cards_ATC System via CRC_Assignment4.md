# CRC Card 1  
**Class:** TransponderPacketReceiver

**Responsibilities:**
- Receive high-density transponder packets from arriving aircraft.
- Validate packet arrival format at the communication boundary.
- Forward packets for decoding.
- Report malformed or incomplete packets.

**Collaborators:**
- PacketDecoder

**Assumption:**
- Each arriving aircraft periodically transmits a transponder packet to the ATC ground station.

# CRC Card 2  
**Class:** PacketDecoder

**Responsibilities:**
- Unpack high-density transponder packets.
- Extract aircraft identity, aircraft type, and flight-state fields.
- Convert raw packet content into structured aircraft-state data.
- Reject corrupt or unsupported packet contents.

**Collaborators:**
- Aircraft
- FlightData
- AircraftTrackRepository

**Assumption:**
- The packet format contains aircraft identity, aircraft type, and current flight data.

# CRC Card 3  
**Class:** Aircraft

**Responsibilities:**
- Represent a unique aircraft in the ATC domain.
- Maintain aircraft identity and type information.
- Associate the aircraft with its latest flight data.
- Provide aircraft summary information for display.
- Provide aircraft detail information for controller query.

**Collaborators:**
- FlightData
- AircraftTrackRepository
- ControllerQueryService

# CRC Card 4  
**Class:** FlightData

**Responsibilities:**
- Store dynamic flight-state attributes.
- Represent position, altitude, speed, heading, and timestamp.
- Update aircraft state from decoded packet data.
- Provide current state data.

**Collaborators:**
- Aircraft
- HazardDetector
- RadarDisplay

# CRC Card 5  
**Class:** AircraftTrackRepository

**Responsibilities:**
- Store aircraft records.
- Retrieve aircraft records.
- Create a new aircraft track when an unknown aircraft appears.
- Update an existing aircraft track with new flight data.
- Provide tracked aircraft data for display.
- Provide tracked aircraft data for hazard analysis.
- Provide aircraft lookup for controller queries.

**Collaborators:**
- Aircraft
- FlightData
- RadarDisplay
- HazardDetector
- ControllerQueryService

**Assumption:**
- This class represents the aircraft database at the CRC design level.

# CRC Card 6  
**Class:** RadarDisplay

**Responsibilities:**
- Build a display from stored aircraft data.
- Refresh the display every 10 seconds.
- Render aircraft symbols and labels.
- Highlight aircraft with active alerts.
- Accept controller selection of an aircraft on screen.

**Collaborators:**
- AircraftTrackRepository
- Aircraft
- FlightData
- ConflictAlert
- ControllerQueryService

**Assumption:**
- The graphics display is updated every 10 seconds using the latest stored aircraft states.

# CRC Card 7  
**Class:** HazardDetector

**Responsibilities:**
- Analyze stored aircraft data for dangerous situations.
- Detect separation conflicts.
- Detect altitude conflicts.
- Detect abnormal flight-state conditions.
- Generate alerts for hazardous situations.

**Collaborators:**
- AircraftTrackRepository
- Aircraft
- FlightData
- ConflictAlert

**Assumption:**
- Dangerous situations include at least separation conflicts, altitude conflicts, and abnormal flight-state conditions.

# CRC Card 8  
**Class:** ConflictAlert

**Responsibilities:**
- Represent a detected dangerous situation.
- Record alert type, affected aircraft, severity, and time.
- Provide alert information for display.
- Provide alert information for controller queries.

**Collaborators:**
- HazardDetector
- RadarDisplay
- ControllerQueryService
- Aircraft

# CRC Card 9  
**Class:** ControllerQueryService

**Responsibilities:**
- Accept controller requests for aircraft details.
- Retrieve details for a selected aircraft.
- Retrieve associated alerts for the selected aircraft.
- Present detailed aircraft information to the controller.

**Collaborators:**
- AircraftTrackRepository
- Aircraft
- ConflictAlert
- RadarDisplay

**Assumption:**
- The controller can query any aircraft currently visible on the display.