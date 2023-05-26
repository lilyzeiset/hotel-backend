# hotel-backend

# API Endpoints:

|| Endpoint | Function |
|------|----------|----------|
|| GET /guests |	finds all guests |
|| GET /guests/{id} | finds guest by {id} |
|| POST /guests	| creates new guest entry and returns it |
|\*| POST /guests/login	| performs user authentication |
|| PUT /guests/{id} |	updates the guest entry for the {id} and returns it |
|| DELETE /guests/{id} |	deletes guest with {id} |
|| GET /roomtypes |	finds all room types |
|| GET /roomtypes/{id} |	finds room type by {id} |
|| POST /roomtypes |	creates a new room type and returns it |
|| PUT /roomtypes/{id} |	updates the roomtype entry for the {id} and returns it |
|| DELETE /roomtypes/{id}	| deletes the room type with {id} |
|| GET /rooms |	finds all rooms|
|| GET /rooms/{id} |	finds the room by {id} |
|\*| GET /rooms/available |	finds a page of available rooms using Search Params |
|\*| GET /rooms/available/total |	finds total number of available rooms using Search Params |
|| POST /rooms |	creates a new room entry and returns it |
|| PUT /rooms/{id} |	updates the room entry for the {id} and returns it |
|| DELETE /rooms/{id} |	deletes the room with {id} |
|| GET /reservations |	finds all the reservations |
|| GET /reservations/{id} |	finds the reservation by {id} |
|\*| GET /reservations/guest/{guest_id} |	finds all reservations by {guest_id} |
|| POST /reservations |	creates a new reservation and returns it |
|| PUT /reservations/{id} | updates the reservation entry for {id} and returns it |
|| DELETE /reservations/{id} |	deletes the reservation with {id} |
|| GET /rooms/available | finds all available rooms |
|| GET /reservations/guest/{guestid} | finds all reservations for a particular guest based on {guestid} |
|| POST /guests/login | login authentication |
