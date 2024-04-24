/*function authenticate() {
    const username = document.getElementById("email");
    const password = document.getElementById("password");
    alert("username " + username + " | password: " + password);
    $.ajax(
        {
            type: "POST",
            url: "/login",
            data: {
                username : username,
                password : password
            },
            success: function (result) {
                if(result != null && result === "unauthorized") {
                    alert("Incorrect username and password");
                } else if (result != null && result === "authorize") {
                    alert("Correct");
                }
            },
            error: function (error) {
                alert("Error Occurred, Please look console");
                console.log("Error - " + error);
            }
        }
    );
}*/

$(document).ready(function() {
    $('#loginForm').submit(function(event) {
        event.preventDefault(); // Prevent default form submission

        var formData = {
            username: $('#email').val(),
            password: $('#password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/authenticate',
            data: {
                username : formData.username,
                password : formData.password
            },
            success: function(response) {
                if(response != null && response === "Invalid username and password") {
                    this.error(response);
                } else {
                    window.location.href = '/home'; // Redirect to home page on success
                }
            },
            error: function(response) {
                $('#alertMessage').text(response).show();
            }
        });
    });
    $('#registerForm').submit(function(event) {
        event.preventDefault();

        var formData = {
            firstName: $('#fname').val(),
            lastName: $('#lname').val(),
            mobileNo: $('#mobile').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            type: $('#type').val()
        };

        $.ajax({
            type: 'POST',
            url: '/saveUser',
            data: {
                firstName : formData.firstName,
                lastName : formData.lastName,
                mobileNo : formData.mobileNo,
                email : formData.email,
                password : formData.password,
                type : formData.type
            },
            success: function(response) {
                if(response != null && response === "success") {
                    window.location.href = '/login';
                } else {
                    this.error(response);
                }
            },
            error: function(xhr) {
                $('#alertMessage').text(xhr).show();
            }
        });
    });
    /*$('#organizerRegisterForm').submit(function(event) {
        event.preventDefault();

        var formData = {
            firstName: $('#fname').val(),
            lastName: $('#lname').val(),
            mobileNo: $('#mobile').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            type: $('#type').val()
        };

        $.ajax({
            type: 'POST',
            url: '/saveUser',
            data: {
                firstName : formData.firstName,
                lastName : formData.lastName,
                mobileNo : formData.mobileNo,
                email : formData.email,
                password : formData.password,
                type : formData.type
            },
            success: function(response) {
                if(response != null && response === "success") {
                    window.location.href = '/login';
                } else {
                    this.error(response);
                }
            },
            error: function(xhr) {
                $('#alertMessage').text(xhr).show();
            }
        });
    });*/
    $('#createEventForm').submit(function(event) {
        event.preventDefault();
        var formData = {
            eventName: $('#eventName').val(),
            eventDescription: $('#eventDescription').val(),
            eventDate: $('#eventDate').val(),
            city: $('#city').val(),
            location: $('#location').val(),
            category: $('#category').val()
        };

        $.ajax({
            type: 'POST',
            url: '/saveEvent',
            data: {
                eventName : formData.eventName,
                eventDescription : formData.eventDescription,
                eventDate : formData.eventDate,
                city : formData.city,
                location : formData.location,
                category : formData.category
            },
            success: function(response) {
                if(response != null && response === "success") {
                    alert("Event Posted Successfully");
                    window.location.href = '/home';
                } else {
                    this.error(response);
                }
            },
            error: function(response) {
                $('#alertMessage').text(response).show();
            }
        });
    });
});
