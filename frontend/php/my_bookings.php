<?php
    $required_user = ['citizen'];
    include 'login_redirect.php';
    $page_title = 'My Bookings';
    $js_scripts = ['date_utils.js','my_bookings.js','logout.js'];
    include __DIR__ . '/header.php';
?>
<script>
    // Embed PHP session data as JavaScript variables
    var user_afm = "<?php echo htmlspecialchars($_SESSION['user_afm']); ?>";
    var user_type = "<?php echo htmlspecialchars($_SESSION['user_type']); ?>";
</script>

<h2 id="myBookingsMessage" style="text-align: center; display: none;"></h2>
<table id="myBookingsTable" class="styled-table" style="display: none;">
    <thead>
        <tr>
            <th>Car</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Dealership</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="3">Loading bookings...</td>
        </tr>
    </tbody>
</table>
<div id="myBookingsDetails" style="display: none; margin-top: 20px; border: 1px solid #ccc; padding: 15px; background-color: #f9f9f9;"></div>


<?php
    include __DIR__ . '/footer.php';
?>