<?php
    session_start();
    if (isset($_SESSION['user_type']) && isset($_SESSION['user_name']) && isset($_SESSION['user_afm'])) {
            header('Location: /primeride/frontend/php/dashboard.php');
            exit();
    }
?>
