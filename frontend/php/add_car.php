<?php
    $required_user = ['dealership'];
    include 'login_redirect.php';
    $page_title = 'Add New Car';
    $js_scripts = ['add_car.js', 'form_utils.js', 'logout.js'];
    include __DIR__ . '/header.php';
?>
<script>
    // Embed PHP session data as JavaScript variables
    var user_afm = "<?php echo htmlspecialchars($_SESSION['user_afm']); ?>";
</script>
<h2>Introduce new car for test driving and/or purchase</h2>

<form id="addCarForm" class="user-form">
    <p>Fields with <strong>(*)</strong> are <strong>required</strong></p>

    <label for="brand">Brand <strong>(*)</strong> :</label>
    <input type="text" id="brand" name="brand" required>
    <span class="error-message" id="brand-error"></span><br><br>

    <label for="model">Model <strong>(*)</strong> :</label>
    <input type="text" id="model" name="model" required>
    <span class="error-message" id="model-error"></span><br><br>

    <label for="fuel">Fuel Type <strong>(*)</strong> :</label>
    <select id="fuel" name="fuel" required>
        <option value="" disabled selected>Select fuel type</option>
        <option value="DIESEL">Diesel</option>
        <option value="PETROL">Petrol</option>
        <option value="ELECTRIC">Electric</option>
        <option value="HYBRID">Hybrid</option>
    </select>
    <span class="error-message" id="fuel-error"></span><br><br>

    <label for="engine">Engine Type <strong>(*)</strong> :</label>
    <select id="engine" name="engine" required>
        <option value="" disabled selected>Select engine type</option>
        <option value="INLINE4">Inline 4</option>
        <option value="INLINE6">Inline 6</option>
        <option value="V6">V6</option>
        <option value="V8">V8</option>
        <option value="ELECTRIC">Electric</option>
        <option value="HYBRID">Hybrid</option>
    </select>
    <span class="error-message" id="engine-error"></span><br><br>

    <label for="seats">Number of Seats <strong>(*)</strong> :</label>
    <input type="number" id="seats" name="seats" min="1" required>    
    <span class="error-message" id="seats-error"></span><br><br>

    <label for="price">Price in € <strong>(*)</strong> :</label>
    <input type="number" step="1.0" id="price" name="price" required>
    <span class="error-message" id="price-error"></span><br><br>

    <label for="info">Additional Information:</label>
    <textarea id="info" name="info" rows="4"></textarea>
    <span class="error-message" id="info-error"></span><br><br>

    <label for="count">Number of Cars <strong>(*)</strong> :</label>
    <input type="number" id="count" step="1" name="count" min="1" required>    
    <span class="error-message" id="count-error"></span><br><br>

    <span class="error-message" id="afm-error"></span><br><br>
    <div id="addCarMessage" style="margin-top: 20px;"></div>
    <button type="button" id="addCarBtn">Add Car</button>
</form>



<?php
    include __DIR__ . '/footer.php';
?>