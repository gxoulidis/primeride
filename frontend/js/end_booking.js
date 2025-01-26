$(document).ready(function () {
  $("#endBookingBtn").on("click", function (event) {
    event.preventDefault();

    const carBought = $("#carBought").is(":checked");

    $("#endBookingMessage").hide().text("");

    $.ajax({
      url: `http://localhost:8080/booking/${booking_id}`,
      type: "DELETE",
      headers: {
        afm: user_afm,
        user_type: user_type,
        purchase: carBought.toString(),
      },
      success: function (data, status, xhr) {
        if (xhr.status === 200) {
          window.location.href = `/primeride/frontend/php/car_bookings.php`;
        }
      },
      error: function (xhr) {
        $("#endBookingMessage")
          .html("<p>End of booking failed. <strong>Try again</strong>!</p>")
          .css("color", "red");
      },
    });
  });
});
