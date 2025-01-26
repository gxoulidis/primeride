$(document).ready(function () {
  // Fetch all available cars on page load
  fetchAllCars()
    .then((dealerships) => {
      // Populate the dropdown
      let dealershipDropdown = $("#dealership");
      dealershipDropdown.empty();
      dealershipDropdown.append(
        '<option value="" selected>Select a Dealership</option>'
      );

      for (let name in dealerships) {
        let afm = dealerships[name];
        dealershipDropdown.append(`<option value="${afm}">${name}</option>`);
      }
    })
    .catch((error) => {
      console.error(error);
      alert("Failed to load dealership options.");
    });

  $("#toggleSearchForm").on("click", function () {
    const isVisible = $("#searchForm").is(":visible");
    $("#searchForm").toggle();
    $(this).text(isVisible ? "Open Search" : "Close Search");
  });

  $("#searchBtn").on("click", function () {
    $("#allCarDetails").empty();
    const criteria = {};

    // Gather form inputs with values
    $("#searchForm")
      .serializeArray()
      .forEach((input) => {
        if (input.value.trim() !== "") {
          criteria[input.name] = input.value.trim();
        }
      });

    // Build query string from criteria
    const queryString = $.param(criteria);

    request_path = queryString === "" ? "all" : `search?${queryString}`;
    // Perform search request
    $.ajax({
      url: `http://localhost:8080/car/` + request_path,
      type: "GET",
      dataType: "json",
      success: function (data, status, xhr) {
        const tableBody = $("#allCarsTable tbody");

        if (xhr.status === 200) {
          $("#allCarsMessage").hide();
          $("#allCarsTable").show();
          tableBody.empty();

          $.each(data, function (index, car) {
            const row = `<tr class="car-row" data-car='${JSON.stringify(car)}'>
                          <td>${car.brand}</td>
                                <td>${car.model}</td>
                                <td>${car.price} €</td>
                                <td>${car.dealership.dname}</td>
                                <td>${car.count}</td>
                        </tr>`;
            tableBody.append(row);

            $(".car-row").on("click", function () {
              displayCarDetails(JSON.parse($(this).attr("data-car")));
            });
          });
        } else if (xhr.status === 204) {
          $("#allCarsMessage").text("No cars meet this criteria.").show();
          $("#allCarsTable").hide();
          $("#allCarDetails").empty();
        }
      },
      error: function (xhr) {
        $("#allCarsMessage")
          .text("Failed to perform search. Please try again.")
          .show();
        $("#allCarsTable").hide();
      },
    });
  });

  function fetchAllCars() {
    return new Promise((resolve, reject) => {
      $.ajax({
        url: "http://localhost:8080/car/all",
        type: "GET",
        dataType: "json",
        success: function (data, status, xhr) {
          var tableBody = $("#allCarsTable tbody");

          if (xhr.status === 200) {
            $("#allCarsMessage").hide();
            $("#allCarsTable").show();
            tableBody.empty();

            $.each(data, function (index, car) {
              var row = `<tr class="car-row" data-car='${JSON.stringify(car)}'>
                                <td>${car.brand}</td>
                                <td>${car.model}</td>
                                <td>${car.price} €</td>
                                <td>${car.dealership.dname}</td>
                                <td>${car.count}</td>
                            </tr>`;
              tableBody.append(row);
            });

            $(".car-row").on("click", function () {
              displayCarDetails(JSON.parse($(this).attr("data-car")));
            });

            // Extract distinct dealerships
            let dealerships = {};
            data.forEach((car) => {
              if (!dealerships[car.dealership.dname]) {
                dealerships[car.dealership.dname] = car.dealership.afm;
              }
              resolve(dealerships);
            });
          } else if (xhr.status === 204) {
            $("#allCarsMessage").text("There are no available cars.").show();
            $("#allCarsTable").hide();
          }
        },
        error: function (xhr) {
          console.error("Error fetching cars:", xhr);
          reject("Failed to fetch cars.");
        },
      });
    });
  }

  function displayCarDetails(car) {
    var carDetails = `
            <h3>${car.brand} ${car.model} at ${car.price}€ from ${car.dealership.dname}</h3>
            <p><strong>Fuel Type:</strong> ${car.fuel}</p>
            <p><strong>Engine Type:</strong> ${car.engine}</p>
            <p><strong>Seats:</strong> ${car.seats}</p>`;

    if (car["info"] !== "")
      carDetails += `<p><strong>Additional Info:</strong> ${car.info}</p>
          `;

    carDetails += `<br>
        <button class="create-booking-btn">
          Book a Test Drive
        </button>`;

    $("#allCarDetails").html(carDetails).show();

    $(".create-booking-btn").on("click", function () {
      const car_details = {};
      car_details["id"] = car.id;
      car_details["brand"] = car.brand;
      car_details["model"] = car.model;

      var queryString = $.param(car_details);
      window.location.href = `/primeride/frontend/php/book_car.php?${queryString}`;
    });
  }
});
