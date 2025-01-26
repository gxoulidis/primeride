<?php
    $required_user = ['dealership'];
    include 'login_redirect.php';
    $page_title = 'End of Test Drive';
    $js_scripts = ['end_booking.js','logout.js'];
    include __DIR__ . '/header.php';

    $booking = [
        'id' => htmlspecialchars($_GET['id']),
        'car' => htmlspecialchars($_GET['car']),
        'citizen' => htmlspecialchars($_GET['citizen'])
    ]
?>
<script>
    var user_afm = "<?php echo htmlspecialchars($_SESSION['user_afm']); ?>";
    var booking_id = "<?php echo htmlspecialchars($booking["id"]); ?>";
    var user_type = "<?php echo htmlspecialchars($_SESSION['user_type']); ?>";

</script>

<h2>Did <?= $booking['citizen'] ?> bought <?= $booking['car'] ?> after the test drive?</h2>
<form id="endBookingForm" class="user-form">

    <label>
        <input type="checkbox" id="carBought" name="carBought">
        Purchase made
    </label><br><br>
    
    <p id="endBookingMessage" style="text-align: center; display: none;"></p>
    <button type="button" id="endBookingBtn">Delete booking</button>
</form>

<?php
    include __DIR__ . '/footer.php';
?>