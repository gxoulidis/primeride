$(document).ready(function () {
  $.ajax({
    url: `http://localhost:8080/booking/${user_afm}`,
    type: "GET",
    headers: {
      user_type: user_type,
    },
    dataType: "json",
    success: function (data, status, xhr) {
      var tableBody = $("#myBookingsTable tbody");
      if (xhr.status === 200) {
        $("#myBookingsMessage").hide();
        $("#myBookingsTable").show();
        tableBody.empty();

        $.each(data, function (index, booking) {
          const fromDate = formatDate(booking.date);
          const toDate = calculateEndDate(booking.date, booking.days);

          var row = `<tr class="booking-row" data-booking='${JSON.stringify(
            booking
          )}'>
                                  <td><strong>${booking.car.brand} ${
            booking.car.model
          }</strong></td>
                                  <td>${fromDate}</td>
                                  <td>${toDate}</td>
                                  <td>${booking.car.dealership.dname}</td>
                                </tr>`;
          tableBody.append(row);
        });

        $(".booking-row").on("click", function () {
          var booking = JSON.parse($(this).attr("data-booking"));
          var bookingDetails = `
                    <h3>You booked a ${booking.car.brand} ${
            booking.car.model
          } from ${booking.car.dealership.dname} for ${booking.days} days</h3>
                    
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
                    <h4>Car Details</h4>
                    <p><strong>Brand:</strong> ${
                      booking.car.brand
                    }&emsp;<strong>Model:</strong> ${
            booking.car.model
          }&emsp;<strong>Fuel Type:</strong> ${
            booking.car.fuel
          }&emsp;<strong>Engine Type:</strong> ${
            booking.car.engine
          }&emsp;<strong>Seats:</strong> ${booking.car.seats}</p>
                    <br>
                    <h4>Dealership Details</h4>
                    <p><strong>Dealership Name:</strong> ${
                      booking.car.dealership.dname
                    }&emsp;<strong>Owner's Name:</strong> ${
            booking.car.dealership.owner
          }&emsp;<strong>Email:</strong> ${booking.car.dealership.email}</p>
          <br>
        <button class="cancel-booking-btn">
          Cancel Booking
        </button>`;

          $("#myBookingsDetails").html(bookingDetails).show();

          $(".cancel-booking-btn").on("click", function () {
            if (
              confirm(
                `Are you sure you want to cancel booking for ${booking.car.brand} ${booking.car.model}?`
              )
            ) {
              $.ajax({
                url: `http://localhost:8080/booking/${booking.id}`,
                type: "DELETE",
                headers: {
                  afm: user_afm,
                  user_type: user_type,
                  purchase: "false",
                },
                success: function () {
                  alert(
                    `Booking for ${booking.car.brand} ${booking.car.model} has been canceled.`
                  );
                  location.reload();
                },
                error: function () {
                  alert("Failed to cancel the booking. Please try again.");
                },
              });
            }
          });
        });
      } else if (xhr.status === 204) {
        // Citizen has no bookings
        $("#myBookingsMessage")
          .text("You don't have any bookings yet...")
          .show();
        $("#myBookingsTable").hide();
      }
    },
    error: function (xhr) {},
  });
});
