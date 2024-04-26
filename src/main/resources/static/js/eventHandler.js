$(document).ready(
    function() {

        // SUBMIT FORM
        $("#fetchByCity").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            fetchByCity();
        });

        function fetchByCity() {
            const city = document.getElementById("location").value;
            // PREPARE FORM DATA
            // DO GET
            $.ajax({
                type : "GET",
                url : "/allEventsAtCity",
                data: { city: city },
                success : function(result) {
                        $(".row").empty();
                        result.forEach(function(event) {
                            var cardHtml =
                                '<div class="column">' +
                                '<div class="card">' +
                                '<img src="./images/' + event.promoImage + '" alt="" class="card-img">' +
                                '<div class="event-info">' +
                                '<p class="event-name">' + event.eventName + '</p>' +
                                '<p class="event-time">' + event.eventDate + '</p>' +
                                '<p class="event-loc">' + event.location + '</p>' +
                                '<a class="event-reg-btn" ' +
                                ' th:data-user-id="${session.user_id}" th:data-event-name="${event.eventId}" ' +
                                ' th:onclick="|saveRegistrationDetails(this.getAttribute(\'data-user-id\'), this.getAttribute(\'data-event-name\'))|">' +
                                ' Register ' +
                                ' </a>' +
                                '<a class="event-reg-update-btn" ' +
                                'th:data-user-id="${session.user_id}" th:data-event-name="${event.eventId}" ' +
                                'th:onclick="|updateRegistrationDetails(this.getAttribute(\'data-user-id\'), this.getAttribute(\'data-event-name\'))|">' +
                                'Not Interested' +
                                '</a>' +
                                '</div>' +
                                '</div>' +
                                '</div>';

                            $(".row").append(cardHtml);
                        });
                       //Update event list and render it

                    console.log(result);
                },
                error : function(e) {
                    alert("Error!")
                    console.log("ERROR: ", e);
                }
            });

        }

    });

function saveRegistrationDetails(user_id, eventId) {
    $.ajax(
        {
            type: "GET",
            url: "/rsvpEvent",
            data: {
                userId : user_id,
                eventId : eventId,
                response : "Accepted"
            },
            success: function (result) {
                if(result != null && result === "success") {
                    alert("RSVP Completed");
                } else if (result != null && result === "error") {
                    alert("Something went wrong");
                } else if (result != null && result === "already rsvp") {
                    alert("Already RSVP for this event");
                }
            },
            error: function (error) {
                alert("Error Occurred, Please look console");
                console.log("Error - " + error);
            }
        }
    );
}

function updateRegistrationDetails(user_id, eventId) {
    alert("userID: " + user_id + " | eventId: " + eventId);
    $.ajax(
        {
            type: "GET",
            url: "/updateRsvpEvent",
            data: {
                userId : user_id,
                eventId : eventId,
                response : "Declined"
            },
            success: function (result) {
                if(result != null && result === "No RSVP") {
                    alert("No RSVP found for this event");
                } else if (result != null && result === "error") {
                    alert("Something went wrong");
                } else if (result != null && result === "success") {
                    alert("RSVP Details update");
                }
            },
            error: function (error) {
                alert("Error Occurred, Please look console");
                console.log("Error - " + error);
            }
        }
    );
}

function fetchByCategory(category) {
    // PREPARE FORM DATA
    // DO GET
    $.ajax({
        type : "GET",
        url : "/eventByCategory",
        data: { category: category },
        success : function(result) {
            $(".row").empty();
            result.forEach(function(event) {
                var cardHtml =
                    '<div class="column">' +
                    '<div class="card">' +
                    '<img src="./images/' + event.promoImage + '" alt="" class="card-img">' +
                    '<div class="event-info">' +
                    '<p class="event-name">' + event.eventName + '</p>' +
                    '<p class="event-time">' + event.eventDate + '</p>' +
                    '<p class="event-loc">' + event.location + '</p>' +
                    '<a class="event-reg-btn" th:data-user-id="${session.user_id}" th:data-event-name="${event.eventId}" ' +
                    'th:onclick="|saveRegistrationDetails(this.getAttribute(\'data-user-id\'), this.getAttribute(\'data-event-name\'))|">Register</a>' +
                    '<a class="event-reg-update-btn" th:data-user-id="${session.user_id}" th:data-event-name="${event.eventId}" ' +
                    'th:onclick="|updateRegistrationDetails(this.getAttribute(\'data-user-id\'), this.getAttribute(\'data-event-name\'))|">Not Interested</a>' +
                    '</div>' +
                    '</div>' +
                    '</div>';

                $(".row").append(cardHtml);
            });
            //Update event list and render it

            console.log(result);
        },
        error : function(e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });

}
