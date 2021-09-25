<?php

//Create

require_once('helper.php');

    $note = $_POST['note'];


    $query = "INSERT INTO * notes(note) VALUES ('$note') ";

    $sql = mysqli_query($db_connect, $query);


    if($sql) {

        echo json_encode(array('Message:' =>  'Created!')); 

    } else {
        echo json_encode(array('Message:' =>  'Error!')); 

    }

    

?>