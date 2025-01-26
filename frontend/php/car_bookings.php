<?php
    $required_user = ['dealership'];
    include 'login_redirect.php';
    $page_title = 'Car Bookings';
    $js_scripts = ['date_utils.js','car_bookings.js','logout.js'];
    include __DIR__ . '/header.php';
?>
<script>
    // Embed PHP session data as JavaScript variables
    var user_afm = "<?php echo htmlspecialchars($_SESSION['user_afm']); ?>";
    var user_type = "<?php echo htmlspecialchars($_SESSION['user_type']); ?>";
</script>

<h2 id="carBookingsMessage" style="text-align: center; display: none;"></h2>
<table id="carBookingsTable" class="styled-table" style="display: none;">
    <thead>
        <tr>
            <th>Citizen</th>
            <th>Car</th>
            <th>Start Date</th>
            <th>End Date</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="3">Loading bookings...</td>
        </tr>
    </tbody>
</table>
<div id="carBookingsDetails" style="display: none; margin-top: 20px; border: 1px solid #ccc; padding: 15px; background-color: #f9f9f9;"></div>


<?php
    include __DIR__ . '/footer.php';
?>