$(document).ready(function () {
  // Submit updated car data
  $("#updateCarBtn").on("click", function () {
    $.ajax({
      url: `http://localhost:8080/car/${car_id}`,
      type: "PATCH",
      contentType: "application/json",
      headers: {
        afm: dealership_afm,
      },
      data: JSON.stringify({ count: parseInt($("#count").val()) }),
      success: function () {
        window.location.href = `/primeride/frontend/php/my_cars.php`;
      },
      error: function (xhr) {
        $("#updateCarMessage")
          .text("Failed to update car details. Please try again.")
          .show();
      },
    });
  });
});
