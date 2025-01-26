<?php
    include 'dashboard_redirect.php';

    $page_title = 'Login';
    $js_scripts = ['login.js', 'form_utils.js']; 
    include __DIR__ . '/header.php';
?>

<form id="loginForm"class="user-form">
    <label for="afm">AFM:</label>
    <input type="text" name="afm" id="afm" required>
    <span class="error-message" id="afm-error"></span><br><br>

    <label for="password">Password:</label>
    <input type="password" name="password" id="password" required>
    <span class="error-message" id="password-error"></span>
    <button type="button" class="toggle-password">Show Password</button><br><br>
    <div id="loginMessage" style="margin-top: 20px;"></div>

    <button class="btn" type="submit" id="loginBtn">Login</button>
</form>
<?php
    include __DIR__ . '/footer.php';
?>
