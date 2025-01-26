<?php
    $required_user = ['citizen'];
    include 'login_redirect.php';
    $page_title = 'Create Booking';
    $js_scripts = ['form_utils.js','logout.js', 'book_car.js'];
    include __DIR__ . '/header.php';

    $car = [
        'id' => htmlspecialchars($_GET['id']),
        'brand' => htmlspecialchars($_GET['brand']),
        'model' => htmlspecialchars($_GET['model'])
    ]
    
?>

<script>
    // Embed PHP session data as JavaScript variables
    var car_id = "<?php echo htmlspecialchars($car['id']); ?>";
    var citizen_afm = "<?php echo htmlspecialchars($_SESSION['user_afm']); ?>";
</script>

<h2>For <?= $car['brand'] ?> <?= $car['model'] ?></h2>

<form id="bookingForm" class="user-form">

    <label for="date">Starting Date:</label>
    <input type="date" id="date" name="date" required>    
    <span class="error-message" id="date-error"></span><br><br>
    
    <label for="days">Number of Days:</label>
    <input type="number" id="days" name="days" min="1" required>
    <span class="error-message" id="days-error"></span><br><br>
    <span class="error-message" id="availability-error"></span><br><br>

    <p id="bookingMessage" style="text-align: center; display: none;"></p>
    <button type="button" id="bookingBtn">Book it</button>
</form>


<?php
    include __DIR__ . '/footer.php';
?>
