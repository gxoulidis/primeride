$(document).ready(function () {
  $("#bookingBtn").on("click", function () {
    event.preventDefault();

    const formData = {
      date: $("#date").val(),
      days: $("#days").val(),
    };

    $("#bookingForm input").css("color", "black");
    $(".error-message").html("");
    $("#bookingMessage").hide();

    $.ajax({
      url: "http://localhost:8080/booking",
      type: "POST",
      contentType: "application/json",
      headers: {
        afm: citizen_afm,
        car_id: car_id,
      },
      data: JSON.stringify(formData),
      success: function () {
        window.location.href = `/primeride/frontend/php/my_bookings.php`;
      },
      error: function (xhr) {
        const response = JSON.parse(xhr.responseText);
        for (var field in response.details) {
          $("#" + field).css("color", "red");
          $("#" + field + "-error").html(response.details[field]);
        }

        $("#bookingMessage")
          .html("<p>Booking failed. <strong>Try again</strong>!</p>")
          .css("color", "red");
      },
    });
  });
});
