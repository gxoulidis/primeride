<?php
    session_start();
    session_unset(); 
    session_destroy(); 
    header('Location: /primeride/index.php');
    exit();
?>