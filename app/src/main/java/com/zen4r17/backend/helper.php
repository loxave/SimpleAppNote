<?php

    define('HOST','Localhost');
    define('USER','root');
    define('PASSWORD','');
    define('DB','learn_crud');

    $db_connect = mysqli_connect(HOST, USER, PASSWORD, DB) or die ('Unable connect');
    
    
    header('Content-Type: application/json');

?>