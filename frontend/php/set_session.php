<?php
    session_start();

    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        if (isset($_POST['user_type'])) {
            $_SESSION['user_type'] = $_POST['user_type'];
            $_SESSION['user_name'] = $_POST['user_name'];
            $_SESSION['user_afm'] = $_POST['user_afm'];
            echo json_encode(['status' => 'success']);
            exit();
        }
    }

    http_response_code(400);
    echo json_encode(['status' => 'error', 'message' => 'Invalid session data']);
?>