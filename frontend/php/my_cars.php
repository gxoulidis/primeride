<?php
    $required_user = ['dealership'];
    include 'login_redirect.php';
    $page_title = 'My Registered Cars';
    $js_scripts = ['logout.js','my_cars.js'];
    include __DIR__ . '/header.php';
?>
<script>
    // Embed PHP session data as JavaScript variables
    var user_afm = "<?php echo htmlspecialchars($_SESSION['user_afm']); ?>";
</script>

<h2 id="myCarsMessage" style="text-align: center; display: none;"></h2>
<table id="myCarsTable" class="styled-table" style="display: none;">
    <thead>
        <tr>
            <th>Brand</th>
            <th>Model</th>
            <th>Number of Available Cars</th>
            <th>Number of Booked Cars</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="3">Loading cars...</td>
        </tr>
    </tbody>
</table>
<div id="myCarsDetails" style="display: none; margin-top: 20px; border: 1px solid #ccc; padding: 15px; background-color: #f9f9f9;"></div>


<?php
    include __DIR__ . '/footer.php';
?>