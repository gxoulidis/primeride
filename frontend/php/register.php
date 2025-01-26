<?php
    include 'dashboard_redirect.php';
    
    $page_title = 'Register';
    $js_scripts = ['register.js', 'form_utils.js']; 
    include __DIR__ . '/header.php';
?>

<form id="registerForm" class="user-form"> 
    <p>All fields are <strong>required</strong></p>

    <label>
        <input type="checkbox" id="isDealership" name="isDealership">
        I represent a dealership
    </label><br><br>

    <!-- Default fields for AFM, email, and password -->
    <label for="afm">AFM:</label>
    <input type="text" id="afm" name="afm" required>
    <span class="error-message" id="afm-error"></span><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    <span class="error-message" id="email-error"></span><br><br>

    <!-- Fields for citizen -->
    <div id="citizenFields">
        <label for="fname">First Name:</label>
        <input type="text" id="fname" name="fname" required>
        <span class="error-message" id="fname-error"></span><br><br>

        <label for="lname">Last Name:</label>
        <input type="text" id="lname" name="lname" required>
        <span class="error-message" id="lname-error"></span><br><br>
    </div>

    <!-- Fields for dealership -->
    <div id="dealershipFields" style="display: none;">
        <label for="dname">Dealership Name:</label>
        <input type="text" id="dname" name="dname">
        <span class="error-message" id="dname-error"></span><br><br>

        <label for="owner">Owner's Name:</label>
        <input type="text" id="owner" name="owner">
        <span class="error-message" id="owner-error"></span><br><br>
    </div>

    <label for="password">Password:</label>
    <input type="password" name="password" id="password" required>
    <span class="error-message" id="password-error"></span>
    <br>
    <button type="button" class="toggle-password">Show Password</button><br><br>
    <div id="registerMessage" style="margin-top: 20px;"></div>

    <button type="button" type="submit" id="registerBtn">Register</button>
</form>

    
<?php
    include __DIR__ . '/footer.php';
?>
