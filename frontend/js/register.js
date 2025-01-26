$(document).ready(function () {
  // Toggle form fields based on the checkbox
  $("#isDealership").on("change", function () {
    const isDealership = $(this).is(":checked");
    $("#citizenFields").toggle(!isDealership);
    $("#dealershipFields").toggle(isDealership);

    // Update required form fields
    if (isDealership) {
      $("#dname").attr("required", true);
      $("#owner").attr("required", true);
      $("#fname").removeAttr("required");
      $("#lname").removeAttr("required");
    } else {
      $("#fname").attr("required", true);
      $("#lname").attr("required", true);
      $("#dname").removeAttr("required");
      $("#owner").removeAttr("required");
    }
  });

  // Submit form
  $("#registerBtn").on("click", function () {
    // Prevent default form submission behavior
    event.preventDefault();

    const userType = $("#isDealership").is(":checked")
      ? "dealership"
      : "citizen";

    var formData =
      userType === "dealership"
        ? {
            afm: $("#afm").val(),
            dname: $("#dname").val(),
            owner: $("#owner").val(),
            email: $("#email").val(),
            password: $("#password").val(),
          }
        : {
            afm: $("#afm").val(),
            fname: $("#fname").val(),
            lname: $("#lname").val(),
            email: $("#email").val(),
            password: $("#password").val(),
          };

    $("#registerForm input").css("color", "black");
    $(".error-message").html("");
    $("#registerMessage").html("");

    $.ajax({
      url: "http://localhost:8080/user/" + userType,
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(formData),
      success: function () {
        $("#registerMessage")
          .html(
            "<p>Registration complete. Procees to <strong>Login</strong>!</p>"
          )
          .css("color", "green");
        $("#registerForm")[0].reset();
      },
      error: function (xhr) {
        var response = JSON.parse(xhr.responseText);

        for (var field in response.details) {
          $("#" + field).css("color", "red");
          $("#" + field + "-error").html(response.details[field]);
        }

        $("#registerMessage")
          .html("<p>Registration failed. <strong>Try again</strong>!</p>")
          .css("color", "red");
      },
    });
  });
});
