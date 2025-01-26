$(document).ready(function () {
  // Toggle visibility of password
  $(".toggle-password").on("click", function () {
    const passwordField = $("#password");
    const type =
      passwordField.attr("type") === "password" ? "text" : "password";
    passwordField.attr("type", type);
    $(this).text(type === "password" ? "Show Password" : "Hide Password");
  });

  // Toggle input text to black
  $(".user-form input").on("focus", function () {
    if ($(this).css("color") === "rgb(255, 0, 0)") {
      $(this).css("color", "black");
    }
  });
});
