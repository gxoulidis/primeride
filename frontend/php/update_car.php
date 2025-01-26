<?php
    $required_user = ['dealership'];
    include 'login_redirect.php';
    $page_title = 'Update Availability';
    $js_scripts = ['logout.js', 'update_car.js'];
    include __DIR__ . '/header.php';

    $car = [
        'id' => htmlspecialchars($_GET['id']),
        'brand' => htmlspecialchars($_GET['brand']),
        'model' => htmlspecialchars($_GET['model']),
        'count' => htmlspecialchars($_GET['count'])
    ]
    
?>

<script>
    // Embed PHP session data as JavaScript variables
    var car_id = "<?php echo htmlspecialchars($car['id']); ?>";
    var dealership_afm = "<?php echo htmlspecialchars($_SESSION['user_afm']); ?>";
</script>

<h2>For <?= $car['brand'] ?> <?= $car['model'] ?></h2>

<form id="updateCarForm" class="user-form">

    <label for="count">Number of Available Cars:</label>
    <input type="number" id="count" step="1" name="count" min="1" value="<?php echo $car['count']; ?>">    
    <span class="error-message" id="count-error"></span><br><br>
    
    <p id="updateCarMessage" style="text-align: center; display: none;"></p>
    <button type="button" id="updateCarBtn">Update</button>
</form>


<?php
    include __DIR__ . '/footer.php';
?>
