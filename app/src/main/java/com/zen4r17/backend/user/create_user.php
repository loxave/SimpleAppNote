<?php


    require_once('help_me.php');

    $nama = $_POST['nama'];

    $query = "INSERT INTO user(nama) VALUES ('$nama')";

    $sql = mysqli_query($db_connect, $query);


    if($sql) {
  
        echo json_encode(array('Message' => 'Created'));

    } else {
        echo json_encode(array('Message' => 'Created Failed'));

        
    }


?>