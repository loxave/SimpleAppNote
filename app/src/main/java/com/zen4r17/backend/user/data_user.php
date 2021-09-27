<?php


    require_once('help_me.php');

    $query = "SELECT * from user ORDER BY id DESC";

    $sql = mysqli_query($db_connect, $query);


    if($sql) {
  
    $res = array();
    while($row = mysqli_fetch_array($sql)) {

        array_push($res, array(

            'id' => $row['id'],
            'nama' => $row['nama'],

        ));

    }

    echo json_encode(array('Tabel User ' => $res));

    }


?>