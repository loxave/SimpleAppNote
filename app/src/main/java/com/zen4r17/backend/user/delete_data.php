<?php


    require_once('help_me.php');

    parse_str(file_get_contents('php://input'),$value); 

    $id = $value ['id'];
   

    $query = "DELETE FROM user WHERE id = '$id'";

    $sql = mysqli_query($db_connect, $query);


    if($sql) {
  
        echo json_encode(array('Message' => 'Was deleted'));

    } else {
        echo json_encode(array('Message' => 'Delete fail'));

        
    }


?>