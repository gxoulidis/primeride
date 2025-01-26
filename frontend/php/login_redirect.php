<?php
    session_start();
    if (!(isset($_SESSION['user_type']) && isset($_SESSION['user_name']) && isset($_SESSION['user_afm']))) {
            header('Location: /primeride/frontend/php/login.php');
            
    } else {
        if (!in_array($_SESSION['user_type'], $required_user)){
            echo "<script>
                    alert('You don\'t have authorization for this page.');
                    window.location.href = '/primeride/frontend/php/dashboard.php';
                </script>";
            exit();
        }
    }
?>