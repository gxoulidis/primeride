$(document).ready(function () {
  $.ajax({
    url: `http://localhost:8080/car/${user_afm}`,
    type: "GET",
    dataType: "json",
    success: function (data, status, xhr) {
      var tableBody = $("#myCarsTable tbody");

      if (xhr.status === 200) {
        // Dealership has cars
        $("#myCarsMessage").hide();
        $("#myCarsTable").show();
        tableBody.empty();

        $.each(data, function (index, car) {
          var row = `<tr class="car-row" data-car='${JSON.stringify(car)}'>
                        <td>${car.brand}</td>
                        <td>${car.model}</td>
                        <td><strong>${car.count}</strong></td>`;

          $.ajax({
            url: `http://localhost:8080/booking/car/${car.id}`,
            type: "GET",
            headers: {
              afm: user_afm,
            },
            dataType: "json",
            success: function (data, status, xhr) {
              if (xhr.status === 200) {
                row += `<td>${data}</td></tr>`;
              } else if (xhr.status === 204) {
                row += `<td>0</td></tr>`;
              }
              tableBody.append(row);
            },
            error: function (xhr) {
              row += `<td>Error fetching count</td></tr>`;
              tableBody.append(row);
            },
          });
        });

        $(document).on("click", ".car-row", function () {
          var car = JSON.parse($(this).attr("data-car"));
          var carDetails = `
            <p><strong>Number of available cars:</strong> ${car.count}</p>
            <p><strong>Brand:</strong> ${car.brand}</p>
            <p><strong>Model:</strong> ${car.model}</p>
            <p><strong>Price:</strong> ${car.price}</p>
            <p><strong>Fuel Type:</strong> ${car.fuel}</p>
            <p><strong>Engine Type:</strong> ${car.engine}</p>
            <p><strong>Seats:</strong> ${car.seats}</p>`;

          if (car["info"] !== "")
            carDetails += `<p><strong>Additional Info:</strong> ${car.info}</p>
          `;

          carDetails += `<button class="update-btn" data-car='${JSON.stringify(
            car
          )}'>Update Availability</button>`;

          $("#myCarsDetails").html(carDetails).show();

          $(".update-btn").on("click", function () {
            const car_details = {};
            car_details["id"] = car.id;
            car_details["brand"] = car.brand;
            car_details["model"] = car.model;
            car_details["count"] = car.count;

            var queryString = $.param(car_details);
            window.location.href = `/primeride/frontend/php/update_car.php?${queryString}`;
          });
        });
      } else if (xhr.status === 204) {
        // Dealership has no cars
        $("#myCarsMessage")
          .text("You have not registered any cars yet.")
          .show();
        $("#myCarsTable").hide();
      }
    },
    error: function (xhr) {
      if (xhr.status === 400) {
        // Invalid AFM
        var response = JSON.parse(xhr.responseText);
        $("#myCarsMessage").text(response.details.afm).show();
      } else {
        // General error handling
        $("#myCarsMessage")
          .text("Failed to load cars. Please try again.")
          .show();
      }
      $("#myCarsTable").hide();
    },
  });
});
