<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="PrimeRide Car Dealership System">
    <title><?php echo $page_title;?></title>
    <link rel="stylesheet" href="/primeride/frontend/css/styles.css">

    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <!-- Include page-specific JS -->
    <?php
        if (isset($js_scripts)) {
            foreach ($js_scripts as $js_file) {
                echo '<script src="/primeride/frontend/js/' . $js_file . '"></script>';
            }
        }
    ?>
</head>
<body>
<nav>
        <ul>
            <?php
                // GET THE NAME OF CURRENT PAGE
                $current_page = basename($_SERVER['PHP_SELF']);

                if (isset($_SESSION['user_type']) && isset($_SESSION['user_name'])) {
                    echo '<li><a href="/primeride/frontend/php/dashboard.php">Dashboard</a></li>';
                    if ($_SESSION['user_type'] === 'dealership'){
                        echo '<li><a href="/primeride/frontend/php/my_cars.php">My Cars</a></li>';
                        echo '<li><a href="/primeride/frontend/php/add_car.php">Add Car</a></li>';
                        echo '<li><a href="/primeride/frontend/php/car_bookings.php">Car Bookings</a></li>';
                    }else if($_SESSION['user_type'] === 'citizen'){
                        echo '<li><a href="/primeride/frontend/php/all_cars.php">Available Cars</a></li>';
                        echo '<li><a href="/primeride/frontend/php/my_bookings.php">My Bookings</a></li>';
                    }
                    echo '<li><a href="#" id="logoutLink">Logout</a></li>';
                }else{
                    // Display navigation options based on current page
                    if ($current_page !== 'index.php') {
                        echo '<li><a href="/primeride/index.php">Home</a></li>';
                    }
                    if ($current_page !== 'login.php') {
                        echo '<li><a href="/primeride/frontend/php/login.php">Login</a></li>';
                    }
                    if ($current_page !== 'register.php') {
                        echo '<li><a href="/primeride/frontend/php/register.php">Register</a></li>';
                    }
                }

            ?>
        </ul>
    </nav>
    <h1><?php echo $page_title?></h1>
 
