<?php
    $required_user = ['citizen'];
    include 'login_redirect.php';
    $page_title = 'Available Cars';
    $js_scripts = ['all_cars.js','logout.js'];
    include __DIR__ . '/header.php';
?>
<script>
    // Embed PHP session data as JavaScript variables
    var user_afm = "<?php echo htmlspecialchars($_SESSION['user_afm']); ?>";
</script>

<button id="toggleSearchForm" class="toggle-button">Open Search</button>
<form id="searchForm" class="user-form" style="display: none; margin-top: 20px;">
    <p>Fill in the fields you want to use as search criteria:</p>
    <label for="dealership">Dealership:</label>
    <select id="dealership" name="dealership"></select><br><br>

    <label for="brand">Brand:</label>
    <input type="text" id="brand" name="brand"><br><br>

    <label for="model">Model:</label>
    <input type="text" id="model" name="model"><br><br>

    <label for="fuel">Fuel Type:</label>
    <select id="fuel" name="fuel">
        <option value="" selected>Select fuel type</option>
        <option value="DIESEL">Diesel</option>
        <option value="PETROL">Petrol</option>
        <option value="ELECTRIC">Electric</option>
        <option value="HYBRID">Hybrid</option>
    </select><br><br>

    <label for="engine">Engine Type:</label>
    <select id="engine" name="engine">
        <option value="" selected>Select engine type</option>
        <option value="INLINE4">Inline 4</option>
        <option value="INLINE6">Inline 6</option>
        <option value="V6">V6</option>
        <option value="V8">V8</option>
        <option value="ELECTRIC">Electric</option>
        <option value="HYBRID">Hybrid</option>
    </select><br><br>

    <label for="seats">Number of Seats:</label>
    <input type="number" id="seats" step="1" name="seats" min="1"><br><br>

    <label for="min-price">Min Price (€):</label>
    <input type="number" step="500.0" id="min-price" name="min-price"><br><br>

    <label for="max-price">Max Price (€):</label>
    <input type="number" step="500.0" id="max-price" name="max-price"><br><br>

    <button type="button" id="searchBtn">Search</button>
</form>

<h2 id="allCarsMessage" style="text-align: center; display: none;"></h2>
<table id="allCarsTable" class="styled-table" style="display: none;">
    <thead>
        <tr>
            <th>Brand</th>
            <th>Model</th>
            <th>Price</th>
            <th>Dealership</th>
            <th>Availability</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="3">Loading cars...</td>
        </tr>
    </tbody>
</table>
<div id="allCarDetails" style="display: none; margin-top: 20px; border: 1px solid #ccc; padding: 15px; background-color: #f9f9f9;"></div>

<?php
    include __DIR__ . '/footer.php';
?>