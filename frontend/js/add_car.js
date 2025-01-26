$(document).ready(function () {
  $("#addCarBtn").on("click", function () {
    // Prevent default form submission behavior
    event.preventDefault();

    const formData = {
      brand: $("#brand").val(),
      model: $("#model").val(),
      fuel: $("#fuel").val(),
      engine: $("#engine").val(),
      seats: $("#seats").val(),
      price: $("#price").val(),
      info: $("#info").val(),
      count: $("#count").val(),
    };

    $("#addCarForm input").css("color", "black");
    $(".error-message").html("");
    $("#addCarMessage").html("");

    $.ajax({
      url: "http://localhost:8080/car",
      type: "POST",
      contentType: "application/json",
      headers: {
        afm: user_afm,
      },
      data: JSON.stringify(formData),
      success: function () {
        $("#addCarMessage")
          .html("<p><strong>Car registration complete!</strong></p>")
          .css("color", "green");
        $("#addCarForm")[0].reset();
      },
      error: function (xhr) {
        const response = JSON.parse(xhr.responseText);

        for (var field in response.details) {
          if (!(field == "fuel" || field == "engine")) {
            $("#" + field).css("color", "red");
          }
          $("#" + field + "-error").html(response.details[field]);
        }

        $("#addCarMessage")
          .html("<p>Car registration failed. <strong>Try again</strong>!</p>")
          .css("color", "red");
      },
    });
  });
});
