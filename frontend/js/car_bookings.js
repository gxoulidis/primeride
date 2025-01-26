$(document).ready(function () {
  $.ajax({
    url: `http://localhost:8080/booking/${user_afm}`,
    type: "GET",
    headers: {
      user_type: user_type,
    },
    dataType: "json",
    success: function (data, status, xhr) {
      var tableBody = $("#carBookingsTable tbody");
      if (xhr.status === 200) {
        $("#carBookingsMessage").hide();
        $("#carBookingsTable").show();
        tableBody.empty();

        $.each(data, function (index, booking) {
          const fromDate = formatDate(booking.date);
          const toDate = calculateEndDate(booking.date, booking.days);

          var row = `<tr class="car-booking-row" data-car-booking='${JSON.stringify(
            booking
          )}'>
                    <td>${booking.citizen.fname} ${booking.citizen.lname}</td>
                    <td><strong>${booking.car.brand} ${
            booking.car.model
          }</strong></td>
                                      <td>${fromDate}</td>
                                      <td>${toDate}</td>
                                    </tr>`;
          tableBody.append(row);
        });

        $(".car-booking-row").on("click", function () {
          var booking = JSON.parse($(this).attr("data-car-booking"));
          var bookingDetails = `
                        <h3>${booking.citizen.fname} ${
            booking.citizen.lname
          } booked a ${booking.car.brand} ${booking.car.model} for ${
            booking.days
          } days</h3>
                        
                        <h4>Booking Details</h4>
                        <p><strong>Days:</strong> ${
                          booking.days
                        }&emsp;<strong>Starting Date:</strong> ${formatDate(
            booking.date
          )}&emsp;<strong>Ending Date:</strong> ${calculateEndDate(
            booking.date,
            booking.days
          )}</p>
                        <br>
                        <h4>Citizen Details</h4>
                        <p><strong>First Name:</strong> ${
                          booking.citizen.fname
                        }&emsp;<strong>Last Name:</strong> ${
            booking.citizen.lname
          }&emsp;<strong>Email:</strong> ${booking.citizen.email}</p>
          <br>
        <button class="end-booking-btn">
          End of Test Drive
        </button>`;

          $("#carBookingsDetails").html(bookingDetails).show();
          $(".end-booking-btn").on("click", function () {
            const booking_details = {};
            booking_details["id"] = booking.id;
            booking_details[
              "car"
            ] = `${booking.car.brand} ${booking.car.model}`;
            booking_details[
              "citizen"
            ] = `${booking.citizen.fname} ${booking.citizen.lname}`;
            var queryString = $.param(booking_details);
            window.location.href = `/primeride/frontend/php/end_booking.php?${queryString}`;

          });
        });
      } else if (xhr.status === 204) {
        // Dealership's cars have no bookings
        $("#carBookingsMessage")
          .text("Your cars have not been booked yet...")
          .show();
        $("#carBookingsTable").hide();
      }
    },
    error: function (xhr) {},
  });
});
