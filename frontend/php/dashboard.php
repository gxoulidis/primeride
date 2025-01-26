<?php
    $required_user = ['citizen','dealership'];
    include 'login_redirect.php';

    $page_title = 'Dashboard';
    $js_scripts = ['logout.js'];
    include __DIR__ . '/header.php';
?>

<h1>Welcome, <?php echo $_SESSION['user_name']?>!</h1>
<?php
    include __DIR__ . '/footer.php';
?>