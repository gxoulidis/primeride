<?php
    session_start();

    if (isset($_SESSION['user_type']) && isset($_SESSION['user_name'])) {
            header('Location: /primeride/frontend/php/dashboard.php');
            exit();
    }
    
    $page_title = 'Home';
    include __DIR__ .'/frontend/php/header.php';
?>

<main>
    <h2>Welcome to PrimeRide</h2>
</main>

<?php
    include __DIR__ .'/frontend/php/footer.php';
?>