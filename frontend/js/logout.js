$(document).ready(function () {
    $("#logoutLink").on("click", function (event) {
      event.preventDefault();
  
      if (confirm("Are you sure you want to log out?")) {
        window.location.href = "/primeride/frontend/php/end_session.php";
      }
    });
  });