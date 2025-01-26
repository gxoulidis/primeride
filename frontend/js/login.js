$(document).ready(function () {
  $("#loginBtn").on("click", function () {
    // Prevent default form submission behavior
    event.preventDefault();

    var loginData = {
      afm: $("#afm").val(),
      password: $("#password").val(),
    };

    $("#loginForm input").css("color", "black");
    $(".error-message").html("");
    $("#loginMessage").html("");

    $.ajax({
      url: "http://localhost:8080/user/login",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(loginData),
      success: function (response, status, xhr) {
        var userType = xhr.getResponseHeader("user_type");
        var userName = xhr.getResponseHeader("user_name");
        $.post(
          "/primeride/frontend/php/set_session.php",
          {
            user_type: userType,
            user_name: userName,
            user_afm: response,
          },
          function () {
            window.location.href = "/primeride/frontend/php/dashboard.php";
          }
        );
      },
      error: function (xhr) {
        var response = JSON.parse(xhr.responseText);
        $("#loginMessage").html("<p>Login Failed</p>").css("color", "red");

        for (var field in response.details) {
          $("#" + field).css("color", "red");
          $("#" + field + "-error").html(response.details[field]);
        }
      },
    });
  });
});
